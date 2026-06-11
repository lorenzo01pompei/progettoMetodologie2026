package it.unicam.cs.mpgc.rpg125936.service.mine;

import it.unicam.cs.mpgc.rpg125936.domain.location.Miniera;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.material.MaterialNames;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.Random;

/**classe di servizio che gestisce l'azione di scavo nella Miniera.
 * Restituisce oggetti @MiningResultDTOcon l'esito dell'operazione.
 */
public class MinieraService {

    private final Random random;
    private final Miniera miniera;

    /**crea un servizio associato a una specifica miniera.
     * @param miniera la miniera in cui si svolge l'azione di scavo
     */
    public MinieraService(Miniera miniera) {
        this.random = new Random();
        this.miniera = miniera;
    }

    /**esegue un'azione di scavo: cerca un piccone nell'inventario del giocatore,
     * lo consuma di un'unità di durabilità e cerca di ottenere un materiale.
     *
     * @param player il giocatore che esegue lo scavo
     * @return MiningResultDTO contenente esito, messaggio descrittivo, materiale trovato (se presente)
     *         e durabilità residua del piccone
     */
    public MiningResultDTO dig(Player player) {
        Pickaxe tool = findPickaxe(player);
        if (tool == null) {
            return new MiningResultDTO(false, "Non hai un piccone utilizzabile! Torna alla Lobby per comprarne uno.", null, 0);
        }

        if(tool.interact(miniera)){
            Material found = rollMaterial();
            if (found != null) {
                player.addMaterial(found);
                return new MiningResultDTO(true, "Hai trovato " + found.getName() + "\nUtilizzi piccone rimasti: "+tool.getDurability(), found, tool.getDurability());
            }
        };
        return new MiningResultDTO(true, "Nulla di fatto, solo sassi...\nUtilizzi piccone rimasti: "+tool.getDurability(), null, tool.getDurability());
    }

    /**cerca un piccone con durabilità residua nell'inventario del giocatore.
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
        if (chance < miniera.getGoldProb()) {
            return new Material(MaterialNames.GOLD.getDisplayName(), MaterialNames.GOLD.getValue());
        }
        if (chance < miniera.getGoldProb() + miniera.getSilverProb()) {
            return new Material(MaterialNames.SILVER.getDisplayName(), MaterialNames.SILVER.getValue());
        }
        if (chance < miniera.getGoldProb() + miniera.getSilverProb() + miniera.getCopperProb()) {
            return new Material(MaterialNames.COPPER.getDisplayName(), MaterialNames.COPPER.getValue());
        }
        return null;
    }
}
