package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo1;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mondo1Controller {

    @FXML private Label titleLabel;
    @FXML private VBox enemyList;
    @FXML private Button backBtn;
    @FXML private Button mineBtn;

    private Mondo1 mondo1;
    private Player player;

    @FXML
    public void initialize() {
        GameSetup gameSetup = new GameSetup();
        player = gameSetup.getPlayer();
        mondo1 = (Mondo1) gameSetup.getWorlds().get(0);

        titleLabel.setText("Mondo 1");

        for (Enemy enemy : mondo1.getEnemies()) {
            String className = enemy.getClass().getSimpleName();
            Label info = new Label(className + " - " + enemy.getName() + " (HP: " + (int) enemy.getHealth() + ")");
            info.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

            Button fightBtn = new Button("Combatti");
            fightBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 20;");
            fightBtn.setOnAction(e -> handleFight(enemy));

            HBox row = new HBox(15, info, fightBtn);
            row.setStyle("-fx-border-color: #ccc; -fx-border-width: 0 0 1 0; -fx-padding: 12 0;");
            enemyList.getChildren().add(row);
        }
    }

    private void handleFight(Enemy enemy) {
        // logica combattimento
    }

    @FXML
    private void handleMine() {
        // logica miniera
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
