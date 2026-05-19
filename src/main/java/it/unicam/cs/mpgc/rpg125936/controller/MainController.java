package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.ambiente.Lobby;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo;
import it.unicam.cs.mpgc.rpg125936.ambiente.Shop;
import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetup;
import it.unicam.cs.mpgc.rpg125936.service.shop.PurchaseDTO;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopService;
import it.unicam.cs.mpgc.rpg125936.service.shop.ToolOffer;
import it.unicam.cs.mpgc.rpg125936.service.shop.WeaponOffer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainController {

    @FXML private Label feedbackLabel;
    @FXML private Label playerNameLabel;
    @FXML private Label healthLabel;
    @FXML private Label livesLabel;
    @FXML private Label moneyLabel;
    @FXML private VBox weaponList;
    @FXML private VBox toolList;
    @FXML private VBox inventoryPanel;
    @FXML private Button mondo1Btn;
    @FXML private Button mondo2Btn;
    @FXML private Button mondo3Btn;

    private GameSetup gameSetup;
    private Player player;
    private Lobby lobby;
    private Shop shop;
    private ShopService shopService;
    private List<Mondo> worlds;

    @FXML
    public void initialize() {
        gameSetup = GameSetup.getInstance();
        player = gameSetup.getPlayer();
        lobby = gameSetup.getLobby();
        shop = lobby.getShop();
        shopService = new ShopService(shop);
        worlds = gameSetup.getWorlds();

        loadShop();
        loadInventory();
        updateProfile();
        updateWorldButtons();
    }


    @FXML
    private void handleLobby() {
        // già nella lobby, ricarica
        feedbackLabel.setText("Sei già nella Lobby.");
    }

    @FXML
    private void handleMondo1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mondo1-view.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = (Stage) mondo1Btn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            feedbackLabel.setText("Errore: " + e.getMessage());
        }
    }

    @FXML
    private void handleMondo2() {
        feedbackLabel.setText("Mondo 2 bloccato.");
    }

    @FXML
    private void handleMondo3() {
        feedbackLabel.setText("Mondo 3 bloccato.");
    }

    private void loadShop() {
        weaponList.getChildren().clear();
        for (WeaponOffer offer : shopService.getWeaponCatalog()) {
            Label info = new Label(offer.getName() + "  \u2022  Danno: " + (int) offer.getDamage());
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
            Label info = new Label(offer.getName() + "  \u2022  Usi: " + (int) offer.getMaxUses());
            info.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

            Button buyBtn = new Button("Compra (" + (int) offer.getPrice() + " monete)");
            buyBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 13;");
            buyBtn.setOnAction(e -> buyTool(offer));

            VBox row = new VBox(5, info, buyBtn);
            row.setStyle("-fx-border-color: #e0d5b0; -fx-border-width: 0 0 1 0; -fx-padding: 8 0;");
            toolList.getChildren().add(row);
        }
    }

    private void buyWeapon(WeaponOffer offer) {
        PurchaseDTO result = shopService.buyWeapon(player, offer.getId());
        feedbackLabel.setText(result.getMessage());
        loadInventory();
        updateProfile();
    }

    private void buyTool(ToolOffer offer) {
        PurchaseDTO result = shopService.buyTool(player, offer.getId());
        feedbackLabel.setText(result.getMessage());
        loadInventory();
        updateProfile();
    }

    private void loadInventory() {
        inventoryPanel.getChildren().clear();
        for (Item item : player.getInventory()) {
            Label lbl;
            if (item instanceof FightItem fi) {
                lbl = new Label("\u2022 " + fi.getName() + " (Danno: " + (int) fi.getDamage() + ")");
            } else {
                lbl = new Label("\u2022 " + item.getClass().getSimpleName());
            }
            lbl.setStyle("-fx-font-size: 14; -fx-padding: 2 0;");
            inventoryPanel.getChildren().add(lbl);
        }
        if (player.getInventory().isEmpty()) {
            inventoryPanel.getChildren().add(new Label("Nessun oggetto."));
        }
    }

    private void updateProfile() {
        playerNameLabel.setText(player.getName());
        healthLabel.setText("HP: " + (int) player.getHealth());
        livesLabel.setText("Vite: " + player.getLives());
        moneyLabel.setText("Monete: " + (int) player.getMoney());
    }

    private void updateWorldButtons() {
        for (int i = 0; i < worlds.size(); i++) {
            Button btn = switch (i) {
                case 0 -> mondo1Btn;
                case 1 -> mondo2Btn;
                case 2 -> mondo3Btn;
                default -> null;
            };
            if (btn != null) {
                boolean unlocked = worlds.get(i).isUnlocked();
                btn.setStyle(unlocked
                    ? "-fx-background-color: #27ae60; -fx-text-fill: white;"
                    : "-fx-background-color: #7f8c8d; -fx-text-fill: white;");
            }
        }
    }
}
