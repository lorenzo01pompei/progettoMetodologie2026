package it.unicam.cs.mpgc.rpg125936.service.mine;

import it.unicam.cs.mpgc.rpg125936.domain.location.Mine;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.material.MaterialNames;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.Random;

/// classe che delega l'azione di scavo nella Mine.

public class MineService {

    private final Random random;
    private final Mine mine;


    public MineService(Mine mine) {
        this.random = new Random();
        this.mine = mine;
    }

    /**esegue uno scavo: cerca un piccone nell'inventario del giocatore,
     * lo consuma di un'unità di durabilità e cerca di ottenere un materiale.
     *
     * @param player il giocatore che esegue lo scavo
     * @return MiningResultDTO contenente esito, messaggio descrittivo, materiale trovato (se presente)
     * e durabilità rimasta del piccone
     */
    public MiningResultDTO dig(Player player) {
        Pickaxe tool = findPickaxe(player);
        if (tool == null) {
            return new MiningResultDTO(false, "Non hai un piccone utilizzabile! Torna alla Lobby per comprarne uno.", null, 0);
        }

        if (tool.interact(mine)) {
            Material found = rollMaterial();
            String message = (found != null) ? "Hai trovato " + found.getName() : "Nulla di fatto, solo sassi...";
            
            if (found != null) {
                player.addMaterial(found);
            }

            if (tool.getDurability() <= 0) {
                player.getInventory().remove(tool);
                message += "\nIl tuo piccone si è rotto!";
            } else {
                message += "\nUtilizzi piccone rimasti: " + tool.getDurability();
            }

            return new MiningResultDTO(true, message, found, tool.getDurability());
        }
        
        return new MiningResultDTO(false, "Non è stato possibile scavare.", null, tool.getDurability());
    }

    /**cerca un piccone con ancora della durabilità rimasta nell'inventario del giocatore.
     *
     * @param player il giocatore
     * @return il primo piccone utilizzabile trovato, oppure null se non c'è
     */
    public Pickaxe findPickaxe(Player player) {
        for (Item item : player.getInventory()) {
            if (item instanceof Pickaxe p && p.getDurability() > 0) {
                return p;
            }
        }
        return null;
    }

    /**determina tramite probabilità cumulative quale materiale viene estratto.
     *
     * @return il materiale trovato, oppure null se lo scavo non ha prodotto nulla
     */
    public Material rollMaterial() {
        double chance = random.nextDouble() * 100;
        if (chance < mine.getGoldProb()) {
            return new Material(MaterialNames.GOLD.getDisplayName(), MaterialNames.GOLD.getValue());
        }
        if (chance < mine.getGoldProb() + mine.getSilverProb()) {
            return new Material(MaterialNames.SILVER.getDisplayName(), MaterialNames.SILVER.getValue());
        }
        if (chance < mine.getGoldProb() + mine.getSilverProb() + mine.getCopperProb()) {
            return new Material(MaterialNames.COPPER.getDisplayName(), MaterialNames.COPPER.getValue());
        }
        return null;
    }
}
