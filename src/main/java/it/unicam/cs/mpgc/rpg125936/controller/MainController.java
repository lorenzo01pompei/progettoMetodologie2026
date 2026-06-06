package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.ToolItem;
import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetupService;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopService;
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
    @FXML private VBox inventoryPanel;
    @FXML private VBox materialPanel;
    @FXML private Button mondo1Btn;
    @FXML private Button mondo2Btn;
    @FXML private Button mondo3Btn;
    @FXML private ShopController shopController;

    private GameSetupService gameSetup;
    private Player player;
    private Lobby lobby;
    private ShopService shopService;
    private List<Mondo> worlds;

    /**inizializza il gioco caricando:
        gameSetup, player, lobby, shopService, worlds
        Carica il contenuto dello shop, l'inventario e lo status del player
        Adegua il comportamento dei bottoni nella mainView
     */
    @FXML
    public void initialize() {
        gameSetup = GameSetupService.getInstance();
        player = gameSetup.getPlayer();
        lobby = gameSetup.getLobby();
        shopService = new ShopService(lobby.getShop());
        worlds = gameSetup.getWorlds();

        shopController.init(player, shopService);
        shopController.setOnPurchase(() -> { loadInventory(); updateProfile(); loadMaterials(); });
        shopController.setOnFeedback(msg -> feedbackLabel.setText(msg));

        loadInventory();
        loadMaterials();
        updateProfile();
        updateWorldButtons();
    }

    ///gestisce le richiesta di trasferimento al mondo1 caricando mondo1View
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

    ///gestisce le richiesta di trasferimento al mondo2; non ancora implementato
    @FXML
    private void handleMondo2() {
        feedbackLabel.setText("Mondo 2 bloccato.");
    }

    ///gestisce le richiesta di trasferimento al mondo3; non ancora implementato
    @FXML
    private void handleMondo3() {
        feedbackLabel.setText("Mondo 3 bloccato.");
    }

    ///carica l'inventario del player in una label che viene inserita nell'inventoryPanel
    private void loadInventory() {
        inventoryPanel.getChildren().clear();
        for (Item item : player.getInventory()) {
            Label lbl;
            if (item instanceof FightItem fi) {
                lbl = new Label("\u2022 " + fi.getName() + " (Danno: " + fi.getDamage() + ")");
            } else if(item instanceof ToolItem fi) {
                lbl = new Label("\u2022 " + fi.getName() + " (Utilizzi rimasti: " + fi.getDurability() + ")");

            }else{
                lbl = new Label("\u2022 " + item.getClass().getSimpleName());
            }
            lbl.setStyle(StyleConstants.ITEM_LABEL);
            inventoryPanel.getChildren().add(lbl);
        }
        if (player.getInventory().isEmpty()) {
            inventoryPanel.getChildren().add(new Label("Nessun oggetto."));
        }
    }

    ///carica l'inventario del player in una label che viene inserita nell'inventoryPanel
    private void loadMaterials() {
        materialPanel.getChildren().clear();
        for (List<Material> materials : player.getMaterials()) {
            Label lbl;
            lbl = new Label("\u2022 " + materials.getFirst().getName() + ": " + materials.size());
            lbl.setStyle(StyleConstants.ITEM_LABEL);
            materialPanel.getChildren().add(lbl);
        }
        if (player.getMaterials().isEmpty()) {
            materialPanel.getChildren().add(new Label("Nessun oggetto."));
        }
    }


    ///aggiorna lo status di salute del player
    private void updateProfile() {
        playerNameLabel.setText(player.getName());
        healthLabel.setText("HP: " + player.getHealthStatus().getHealth());
        livesLabel.setText("Vite: " + player.getLives());
        moneyLabel.setText("Monete: " + (int) player.getMoney());
    }

    ///aggiorna il funzionamento dei bottoni relativi ai mondi controllando se sono sbloccati o meno
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
                    ? StyleConstants.WORLD_UNLOCKED
                    : StyleConstants.WORLD_LOCKED);
            }
        }
    }
}
