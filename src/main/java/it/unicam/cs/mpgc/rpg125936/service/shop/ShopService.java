package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

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

    public ShopService(Shop shop) {
        this.shop = shop;
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
    public PurchaseDTO buyWeapon(Player player, WeaponOffer offer) {
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
    }

    public PurchaseDTO buySpell(Player player, SpellOffer offer) {
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
    }

    public PurchaseDTO buyTool(Player player, ToolOffer offer) {
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
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
        Map<String, List<Material>> materialMap = player.getMaterials();
        if (materialMap.isEmpty()) {
            return new SellDTO(false, "Non hai materiali da vendere", 0);
        }

        int earned = 0;
        for (var entry : materialMap.entrySet()) {
            earned += calculateMaterialValue(entry.getKey(), entry.getValue().size());
        }
        player.removeMaterials();
        player.addMoney(earned);

        return new SellDTO(true, "Materiali venduti per: " + earned + " monete.", earned);
    }

    ///calcola il valore di una lista di materiali
    private int calculateMaterialValue(String materialName, int quantity){
        int materialPrice = shop.getMaterialPrices().get(materialName);
        return quantity * materialPrice;
    }
}
