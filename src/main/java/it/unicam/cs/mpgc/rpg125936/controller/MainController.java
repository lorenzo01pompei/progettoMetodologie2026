package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.ambiente.Lobby;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class MainController {

    @FXML private BorderPane rootPane;
    @FXML private Button lobbyBtn;
    @FXML private Button mondo1Btn;
    @FXML private Button mondo2Btn;
    @FXML private Button mondo3Btn;
    @FXML private Label locationLabel;
    @FXML private Label healthLabel;
    @FXML private Label livesLabel;
    @FXML private Label moneyLabel;
    @FXML private Label locationStatusLabel;

    private GameSetup gameSetup;
    private Player player;
    private Lobby lobby;
    private List<Mondo> worlds;

    @FXML
    public void initialize() {
        gameSetup = new GameSetup();
        player = gameSetup.getPlayer();
        lobby = gameSetup.getLobby();
        worlds = gameSetup.getWorlds();

        updateStatusBar();
        updateWorldButtons();
    }

    @FXML
    private void handleLobby() {
        locationLabel.setText("Lobby Iniziale");
        updateStatusBar();
    }

    @FXML
    private void handleMondo1() {
        Mondo mondo = worlds.get(0);
        if (!mondo.isUnlocked()) {
            locationLabel.setText("Mondo 1 bloccato!");
            return;
        }
        locationLabel.setText("Mondo 1");
        updateStatusBar();
    }

    @FXML
    private void handleMondo2() {
        Mondo mondo = worlds.get(1);
        if (!mondo.isUnlocked()) {
            locationLabel.setText("Mondo 2 bloccato!");
            return;
        }
        locationLabel.setText("Mondo 2");
        updateStatusBar();
    }

    @FXML
    private void handleMondo3() {
        Mondo mondo = worlds.get(2);
        if (!mondo.isUnlocked()) {
            locationLabel.setText("Mondo 3 bloccato!");
            return;
        }
        locationLabel.setText("Mondo 3");
        updateStatusBar();
    }

    private void updateStatusBar() {
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
                btn.setDisable(!unlocked);
            }
        }
    }
}
