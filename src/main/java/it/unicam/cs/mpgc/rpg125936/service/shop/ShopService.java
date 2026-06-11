package it.unicam.cs.mpgc.rpg125936.service.shop;

import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Offer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.List;
import java.util.Map;

/**
 * Classe che gestisce le operazioni del negozio.
 * Gestisce acquisto di armi, acquisto di strumenti e vendita di materiali
 */
public class ShopService {

    private final Shop shop;

    public ShopService(Shop shop) {
        this.shop = shop;
    }

    ///Restituisce la lista di armi acquistabili nel negozio.

    public List<WeaponOffer> getWeaponCatalog() {
        return shop.getWeapons();
    }

    ///Restituisce la lista di strumenti acquistabili nel negozio.

    public List<ToolOffer> getToolCatalog() {
        return shop.getTools();
    }

    /// Restituisce la lista di incantesimi acquistabili nel negozio.

    public List<SpellOffer> getSpellCatalog() {
        return shop.getSpells();
    }


    /// Delega a buyOffer l'acquisto di un'arma per il giocatore.

    public PurchaseDTO buyWeapon(Player player, WeaponOffer offer) {
        return buyOffer(player, offer, "arma");
    }

    ///Delega a buyOffer l'acquisto di un incantesimo per il giocatore.

    public PurchaseDTO buySpell(Player player, SpellOffer offer) {
        return buyOffer(player, offer, "incantesimo");
    }

    ///Delega a buyOffer l'acquisto di uno strumento per il giocatore.

    public PurchaseDTO buyTool(Player player, ToolOffer offer) {
        return buyOffer(player, offer, "strumento");
    }

    /// esegue l'acquisto effettivo: verifica il saldo, scala il denaro e aggiunge l'item al player
    private PurchaseDTO buyOffer(Player player, Offer offer, String tipo) {
        if (player.getMoney() < offer.getPrice()) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + offer.getPrice() + " monete.");
        }
        player.addMoney(-offer.getPrice());
        player.addItem(offer.createItem());
        return new PurchaseDTO(true, "Hai acquistato " + offer.getName() + " (" + tipo + ") per " + offer.getPrice() + " monete.");
    }

    ///Acquista una vita per il giocatore.
    public PurchaseDTO buyLives(Player player) {
        int price = player.getHealthStatus().getLivesPrice();
        if (player.getMoney() < price) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + price + " monete.");
        }
        if (player.getLives() < Player.MAX_LIVES) {
            player.addMoney(-price);
            player.setLives(player.getLives()+1);
            return new PurchaseDTO(true, "Hai acquistato una vita per " + price + " monete.");
        }else{
            return new PurchaseDTO(false, "Non puoi comprare vite, ne hai già il massimo numero.");
        }
    }

    /// Rigenera la salute del player.
    public PurchaseDTO refillLife(Player player) {
        int price = player.getHealthStatus().getHpPrice();
        if (player.getMoney() < price) {
            return new PurchaseDTO(false, "Soldi insufficienti. Servono " + price + " monete.");
        }
        if (price > 0) {
            player.addMoney(-price);
            player.setHealth(player.getHealthStatus().getInitialHealth());
            return new PurchaseDTO(true, "Hai ripristinato la salute per " + price + " monete.");
        }else{
            return new PurchaseDTO(false, "Non puoi aggiungere salute, sei già al massimo.");
        }
    }

    ///Vende tutti i materiali in possesso del player.

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

    /// calcola il valore di una lista di materiali
    private int calculateMaterialValue(String materialName, int quantity) {
        int materialPrice = shop.getMaterialPrices().get(materialName);
        return quantity * materialPrice;
    }
}
