package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.GunRepository;
import it.unicam.cs.mpgc.rpg125936.repository.PickaxeRepository;
import it.unicam.cs.mpgc.rpg125936.repository.SpellRepository;

import java.util.List;
import java.util.Map;

/**
 * Servizio applicativo che gestisce le operazioni del negozio.
 * Gestisce acquisto di armi, acquisto di strumenti e vendita di materiali,
 * eseguendo tutte le validazioni necessarie e aggiornando lo stato del giocatore
 * (denaro, inventario, materiali).
 * <p>
 * Restituisce oggetti {@link PurchaseDTO} e {@link SellDTO}
 */
public class ShopService {

    private final Shop shop;
    private final GunRepository gunRepository;
    private final SpellRepository spellRepository;
    private final PickaxeRepository pickaxeRepository;

    public ShopService(Shop shop) {
        this.shop = shop;
        this.gunRepository = new GunRepository();
        this.spellRepository = new SpellRepository();
        this.pickaxeRepository = new PickaxeRepository();
    }

    /**
     * Restituisce la lista di armi acquistabili nel negozio.
     *
     * @return lista di {@link WeaponOffer}
     */
    public List<WeaponOffer> getWeaponCatalog() {
        return shop.getWeapons();
    }

    /**
     * Restituisce la lista di strumenti acquistabili nel negozio.
     *
     * @return lista immutabile di {@link ToolOffer}
     */
    public List<ToolOffer> getToolCatalog() {
        return shop.getTools();
    }

    /**
     * Restituisce la lista di strumenti acquistabili nel negozio.
     *
     * @return lista immutabile di {@link ToolOffer}
     */
    public List<SpellOffer> getSpellCatalog() {
        return shop.getSpells();
    }

    /**
     * Restituisce la legenda per la conversione dei materiali in monete.
     *
     * @return mappa (nome materiale &rarr; valore unitario in monete)
     */
    public Map<String, Integer> getMaterialPrice() {
        return shop.getMaterialPrices();
    }

    /**
     * Acquista un'arma per il giocatore.
     *
     * @param player   il giocatore acquirente
     * @param weaponId id dell'arma da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buyWeapon(Player player, long weaponId) {
        Gun gun = gunRepository.findById(weaponId);
        if (gun == null) {
            return new PurchaseDTO(false, "Arma non trovata.");
        }
        if (player.getMoney() < gun.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + gun.getPrice() + " monete.");
        }
        player.addMoney(-gun.getPrice());
        player.addItem(gun.copy());
        return new PurchaseDTO(true, "Hai acquistato " + gun.getName() + " per " + gun.getPrice() + " monete.");
    }

    /**
     * Acquista un incantesimo per il giocatore.
     *
     * @param player  il giocatore acquirente
     * @param spellId id dell'incantesimo da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buySpell(Player player, long spellId) {
        Spell spell = spellRepository.findById(spellId);
        if (spell == null) {
            return new PurchaseDTO(false, "Incantesimo non trovato.");
        }
        if (player.getMoney() < spell.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + spell.getPrice() + " monete.");
        }
        player.addMoney(-spell.getPrice());
        player.addItem(spell.copy());
        return new PurchaseDTO(true, "Hai acquistato " + spell.getName() + " per " + spell.getPrice() + " monete.");
    }



    /**
     * Acquista uno strumento per il giocatore.
     *
     * @param player il giocatore acquirente
     * @param toolId id dello strumento da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buyTool(Player player, long toolId) {
        Pickaxe pickaxe = pickaxeRepository.findById(toolId);
        if (pickaxe == null) {
            return new PurchaseDTO(false, "Strumento non trovato.");
        }
        if (player.getMoney() < pickaxe.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + pickaxe.getPrice() + " monete.");
        }
        player.addMoney(-pickaxe.getPrice());
        player.addItem(pickaxe.copy());
        return new PurchaseDTO(true, "Hai acquistato " + "Piccone" + " per " + pickaxe.getPrice() + " monete.");
    }

    /**
     * Acquista una vita per il giocatore.
     *
     * @param player  il giocatore acquirente
     * @return @PurchaseDTO con esito e messaggio descrittivo
     */
    public PurchaseDTO buyLives(Player player) {
        int price = player.getHealthStatus().getLivesPrice();
        if (player.getMoney() < price) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + price + " monete.");
        }
        if(player.getLives()<3){
            player.addMoney(-price);
            player.setLives(player.getLives()+1);
            return new PurchaseDTO(true, "Hai acquistato una vita per " + price + " monete.");
        }else{
            return new PurchaseDTO(false, "Non puoi comprare vite, ne hai già il massimo numero.");
        }
    }

    /**Rigenera la vita del player.
     *
     * @param player  il giocatore acquirente
     * @return @PurchaseDTO con esito e messaggio descrittivo
     */
    public PurchaseDTO refillLife(Player player) {
        int price = player.getHealthStatus().getHpPrice();
        if (player.getMoney() < price) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + price + " monete.");
        }
        if(price>0){
            player.addMoney(-price);
            player.setHealth(player.getHealthStatus().getInitialHealth());
            return new PurchaseDTO(true, "Hai ripristinato la salute per " + price + " monete.");
        }else{
            return new PurchaseDTO(false, "Non puoi aggiungere salute, sei già al massimo.");
        }
    }

    /**vende tutti i materiali in possesso del player.
     *
     * @param player       il giocatore venditore
     * @return {@link SellDTO} con esito, messaggio e importo guadagnato
     */
    public SellDTO sellMaterial(Player player) {
        if (player.getMaterials().size() <= 0) {
            return new SellDTO(false, "Non hai materiali da vendere", 0);
        }

        int earned =0;
        for(List<Material> materials : player.getMaterials()){
            earned += calculateMaterialValue(materials);
        }
        player.removeMaterials();
        player.addMoney(earned);

        return new SellDTO(true, "Materiali venduti per: " + earned + " monete.", earned);
    }

    ///calcola il valore di una lista di materiali
    public int calculateMaterialValue(List<Material> materials){
        int quantity = materials.size();
        int materialPrice = shop.getMaterialPrices().get(materials.getFirst().getName());
        return quantity*materialPrice;
    }
}
