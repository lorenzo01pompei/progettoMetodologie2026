package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.shop.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class ShopController {

    @FXML private VBox weaponList;
    @FXML private VBox toolList;
    @FXML private VBox spellList;

    private Player player;
    private ShopService shopService;
    private Runnable onPurchase;
    private Consumer<String> onFeedback;

    public void setOnPurchase(Runnable onPurchase) {
        this.onPurchase = onPurchase;
    }

    public void setOnFeedback(Consumer<String> onFeedback) {
        this.onFeedback = onFeedback;
    }

    public void init(Player player, ShopService shopService) {
        this.player = player;
        this.shopService = shopService;
        loadShop();
    }

    /**
     Usato per caricare il contenuto dello shop all'interno della lobbby
     e consentire l'acquisto di oggetti al suo interno tramite bottoni
     che delegano il lavoro agli appositi metodi
     */
    private void loadShop() {
        weaponList.getChildren().clear();
        for (WeaponOffer offer : shopService.getWeaponCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Danno: " + offer.getDamage());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyWeapon(offer));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            weaponList.getChildren().add(row);
        }

        toolList.getChildren().clear();
        for (ToolOffer offer : shopService.getToolCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Usi: " + offer.getMaxUses());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyTool(offer));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            toolList.getChildren().add(row);
        }

        spellList.getChildren().clear();
        for (SpellOffer offer : shopService.getSpellCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Danno: " + offer.getDamage());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buySpell(offer));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            spellList.getChildren().add(row);
        }
    }

    /**
     * usato per comprare un arma dallo shop ed aggiungerla nell'inventario
     * @param offer: arma che si vuole comprare
     */
    private void buyWeapon(WeaponOffer offer) {
        PurchaseDTO result = shopService.buyWeapon(player, offer.getId());
        if (onFeedback != null) onFeedback.accept(result.getMessage());
        if (onPurchase != null) onPurchase.run();
    }

    /**
     * usato per comprare un incantesimo dallo shop ed aggiungerlo nell'inventario
     * @param offer: arma che si vuole comprare
     */
    private void buySpell(SpellOffer offer) {
        PurchaseDTO result = shopService.buySpell(player, offer.getId());
        if (onFeedback != null) onFeedback.accept(result.getMessage());
        if (onPurchase != null) onPurchase.run();
    }


    /**
     * usato per comprare un attrezzo dallo shop ed aggiungerlo nell'inventario
     * @param offer: attrezzo che si vuole comprare
     */
    private void buyTool(ToolOffer offer) {
        PurchaseDTO result = shopService.buyTool(player, offer.getId());
        if (onFeedback != null) onFeedback.accept(result.getMessage());
        if (onPurchase != null) onPurchase.run();
    }
}
