package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.ToolItem;
import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.World;
import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetupService;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller della schermata della lobby, mostra le informazioni relative all'utente
 * ed i bottoni per entrare nei vari mondi
 */
public class MainController {

    @FXML private Label feedbackLabel;
    @FXML private Label playerNameLabel;
    @FXML private Label healthLabel;
    @FXML private Label livesLabel;
    @FXML private Label moneyLabel;
    @FXML private VBox inventoryPanel;
    @FXML private VBox materialPanel;
    @FXML private Button world1Btn;
    @FXML private Button world2Btn;
    @FXML private Button world3Btn;
    @FXML private ShopController shopController;

    private GameSetupService gameSetup;
    private Player player;
    private Lobby lobby;
    private ShopService shopService;
    private List<World> worlds;

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

        shopController.init(player, shopService, this);


        loadInventory();
        loadMaterials();
        updateProfile();
        updateWorldButtons();
    }

    ///aggiorna gli elementi della UI dopo un acquisto/vendita nello shop
    public void refreshUI() {
        loadInventory();
        updateProfile();
        loadMaterials();
    }

    ///mostra un messaggio nella label di feedback
    public void showFeedback(String msg) {
        feedbackLabel.setText(msg);
    }


    ///gestisce le richiesta di trasferimento al mondo1 caricando world1View
    @FXML
    private void handleWorld1() {
        try {
            FXMLLoader loader = SceneLoader.switchTo("/view/world1-view.fxml", world1Btn);
            World1Controller mc = loader.getController();
            mc.init(player, worlds.get(0));
        } catch (Exception e) {
            feedbackLabel.setText("Errore: " + e.getMessage());
        }
    }

    ///gestisce le richieste di trasferimento al world2;    /// placeholder per il secondo mondo
    @FXML
    private void handleWorld2() {
        feedbackLabel.setText("Mondo 2 bloccato.");
    }

    ///gestisce le richiesta di trasferimento al world3;    /// placeholder per il terzo mondo
    @FXML
    private void handleWorld3() {
        feedbackLabel.setText("Mondo 3 bloccato.");
    }

    ///carica l'inventario di item del player in una label che viene inserita nell'inventoryPanel
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

    ///carica l'inventario di materiali del player in una label che viene inserita nell'inventoryPanel
    private void loadMaterials() {
        materialPanel.getChildren().clear();
        for (var entry : player.getMaterials().entrySet()) {
            Label lbl = new Label("\u2022 " + entry.getKey() + ": " + entry.getValue().size());
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

    private void updateWorldButtons() {
        for (int i = 0; i < worlds.size(); i++) {
            Button btn = switch (i) {
                case 0 -> world1Btn;
                case 1 -> world2Btn;
                case 2 -> world3Btn;
                default -> null;
            };
            if (btn != null) {
                if(worlds.get(i).isUnlocked()){
                    btn.setStyle(StyleConstants.WORLD_UNLOCKED);
                }else{
                    btn.setStyle(StyleConstants.WORLD_LOCKED);
                }
            }
        }
    }
}
