package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.domain.shop.SpellOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.domain.shop.WeaponOffer;
import it.unicam.cs.mpgc.rpg125936.service.shop.PurchaseDTO;
import it.unicam.cs.mpgc.rpg125936.service.shop.SellDTO;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class ShopController {

    @FXML private VBox weaponList;
    @FXML private VBox toolList;
    @FXML private VBox spellList;
    @FXML private VBox lifeList;
    @FXML private VBox hpRefill;
    @FXML private VBox materialSell;

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

    ///delega il caricamento del contenuto dello shop all'interno della lobbby
    private void loadShop() {
        loadWeapons();
        loadTools();
        loadSpells();
        loadSelling();
        loadLife();
        loadHp();
    }

    /**elabora l'esito di un acquisto e notifica la UI
     *
     * @param result esito dell'operazione di acquisto
     */
    private void buyItem(PurchaseDTO result) {
        if (onFeedback != null) onFeedback.accept(result.getMessage());
        if (onPurchase != null) onPurchase.run();
    }

    /**elabora l'esito di una vendita e notifica la UI
     *
     * @param result esito dell'operazione di acquisto
     */
    private void sellMaterial(SellDTO result) {
        if (onFeedback != null) onFeedback.accept(result.getMessage());
        if (onPurchase != null) onPurchase.run();
    }


    /**carica le armi nello shop ne consente l'acquisto tramite bottoni
     che delegano il lavoro agli appositi metodi
     */
    private void loadWeapons() {
        weaponList.getChildren().clear();
        for (WeaponOffer offer : shopService.getWeaponCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Danno: " + offer.getDamage());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyItem(shopService.buyWeapon(player, offer.getId())));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            weaponList.getChildren().add(row);
        }
    }

    /**carica gli attrezzi nello shop e ne consente l'acquisto tramite bottoni
     che delegano il lavoro agli appositi metodi
     */
    private void loadTools() {
        toolList.getChildren().clear();
        for (ToolOffer offer : shopService.getToolCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Usi: " + offer.getMaxUses());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyItem(shopService.buyTool(player, offer.getId())));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            toolList.getChildren().add(row);
        }
    }

    /**carica gli incantesimi nello shop e ne consente l'acquisto tramite bottoni
     che delegano il lavoro agli appositi metodi
     */
    private void loadSpells() {
        spellList.getChildren().clear();
        for (SpellOffer offer : shopService.getSpellCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Danno: " + offer.getDamage());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyItem(shopService.buySpell(player, offer.getId())));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            spellList.getChildren().add(row);
        }
    }

    /**carica una vita nello shop e ne consente l'acquisto tramite bottone
     che delega il lavoro all'apposito metodo
     */
    private void loadLife(){
        lifeList.getChildren().clear();
        int lifePrice = player.getHealthStatus().getLivesPrice();
        Button buyLifeBtn = new Button("Compra una vita (" + lifePrice + " monete)");
        buyLifeBtn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-size: 13;");
        buyLifeBtn.setOnAction(e -> buyItem(shopService.buyLives(player)));
        lifeList.getChildren().add(buyLifeBtn);
    }

    /**consente il ripristino della salute tramite bottone
     che delega il lavoro all'apposito metodo
     */
    private void loadHp(){
        hpRefill.getChildren().clear();
        int hpPrice = player.getHealthStatus().getHpPrice();
        Button buyHpBtn = new Button("Rigenera salute (" + hpPrice + " monete)");
        buyHpBtn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-size: 13;");
        buyHpBtn.setOnAction(e -> buyItem(shopService.refillLife(player)));
        hpRefill.getChildren().add(buyHpBtn);
    }

    private void loadSelling(){
        materialSell.getChildren().clear();
        Button sellMaterialBtn = new Button("Vendi i tuoi materiali");
        sellMaterialBtn.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-size: 13;");
        sellMaterialBtn.setOnAction(e -> sellMaterial(shopService.sellMaterial(player)));
        materialSell.getChildren().add(sellMaterialBtn);

    }

}
