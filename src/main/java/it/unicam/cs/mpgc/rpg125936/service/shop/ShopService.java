package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.ambiente.Shop;
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
    public Map<String, Double> getMaterialPrice() {
        return shop.getMaterialPrices();
    }

    /**
     * Acquista un'arma per il giocatore.
     *
     * @param player   il giocatore acquirente
     * @param weaponId id dell'arma da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buyWeapon(Player player, String weaponId) {
        WeaponOffer offer = shop.findWeaponById(weaponId);
        if (offer == null) {
            return new PurchaseDTO(false, "Arma non trovata nel catalogo.");
        }
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
    }

    /**
     * Acquista un'arma per il giocatore.
     *
     * @param player  il giocatore acquirente
     * @param spellId id dell'incantesimo da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buySpell(Player player, String spellId) {
        SpellOffer offer = shop.findSpellById(spellId);
        if (offer == null) {
            return new PurchaseDTO(false, "Incantesimo non trovato nel catalogo.");
        }
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createSpell());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
    }



    /**
     * Acquista uno strumento per il giocatore.
     *
     * @param player il giocatore acquirente
     * @param toolId id dello strumento da acquistare
     * @return {@link PurchaseDTO} con esito e messaggio descrittivo
     */
    public PurchaseDTO buyTool(Player player, String toolId) {
        ToolOffer offer = shop.findToolById(toolId);
        if (offer == null) {
            return new PurchaseDTO(false, "Strumento non trovato nel catalogo.");
        }
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " per " + offer.getPrice() + " monete.");
    }


    /**
     * Vende una quantità di materiale per il giocatore.
     *
     * @param player       il giocatore venditore
     * @param materialName nome del materiale da vendere
     * @param quantity     quantità da vendere
     * @return {@link SellDTO} con esito, messaggio e importo guadagnato
     */
    public SellDTO sellMaterial(Player player, String materialName, int quantity) {
        if (quantity <= 0) {
            return new SellDTO(false, "La quantità deve essere maggiore di zero.", 0);
        }
        Double rate = shop.getMaterialPrices().get(materialName);
        if (rate == null) {
            return new SellDTO(false, "Materiale \"" + materialName + "\" non vendibile.", 0);
        }
        int available = player.countMaterial(materialName);
        if (available < quantity) {
            return new SellDTO(false, "Hai solo " + available + " " + materialName + ", ne servono " + quantity + ".", 0);
        }
        player.removeMaterials(materialName, quantity);
        double earned = quantity * rate;
        player.addMoney(earned);
        return new SellDTO(true, "Venduti " + quantity + "x " + materialName + " per " + earned + " monete.", earned);
    }
}
