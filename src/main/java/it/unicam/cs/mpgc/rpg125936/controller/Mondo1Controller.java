package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.location.Miniera;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo1;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetupService;
import it.unicam.cs.mpgc.rpg125936.service.mine.MinieraService;
import it.unicam.cs.mpgc.rpg125936.service.mine.MiningResultDTO;
import it.unicam.cs.mpgc.rpg125936.service.mine.MinieraService;
import it.unicam.cs.mpgc.rpg125936.service.mine.MiningResultDTO;
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
    @FXML private Label feedbackLabel;
    @FXML private Label playerHpLabel;
    @FXML private Label playerLivesLabel;
    @FXML private Label materialLabel;
    @FXML private VBox enemyList;
    @FXML private Button backBtn;
    @FXML private Button mineBtn;

    private Mondo1 mondo1;
    private Miniera miniera;
    private Player player;
    private MinieraService minieraService;


    /**inizializza il mondo:
      carica lo status di salute del player
      carica i nemici con relativo status di salute
      configura i bottoni per combattere i relativi nemici ancora imbattuti
     */
    @FXML
    public void initialize() {
        GameSetupService gameSetup = GameSetupService.getInstance();
        player = gameSetup.getPlayer();
        mondo1 = (Mondo1) gameSetup.getWorlds().get(0);
        miniera = gameSetup.getWorlds().get(0).getMiniera();
        minieraService = new MinieraService(miniera);

        titleLabel.setText("Mondo 1");
        playerHpLabel.setText("HP: " + player.getHealthStatus().getHealth());
        playerLivesLabel.setText("Vite: " + player.getLives());

        for (Enemy enemy : mondo1.getEnemies()) {
            String className = enemy.getClass().getSimpleName();
            Label info = new Label(className + " - " + enemy.getName() + " (HP: " + enemy.getHealthStatus().getHealth() + ")");
            info.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

            Button fightBtn = new Button("Combatti");
            fightBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 20;");

            if (enemy.isDefeated()) {
                fightBtn.setDisable(true);
                fightBtn.setText("Sconfitto");
                fightBtn.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 8 20;");
            } else {
                fightBtn.setOnAction(e -> handleFight(enemy));
            }

            HBox row = new HBox(15, info, fightBtn);
            row.setStyle("-fx-border-color: #ccc; -fx-border-width: 0 0 1 0; -fx-padding: 12 0;");
            enemyList.getChildren().add(row);
        }
    }

    /**gestisce l'ingaggio del combattimento
      carica la schermata di fightView
      delega il combattimento al fightController
     */
    private void handleFight(Enemy enemy) {
        boolean hasWeapon = false;
        for (var item : player.getInventory()) {
            if (item instanceof FightItem) {
                hasWeapon = true;
                break;
            }
        }
        if (!hasWeapon) {
            feedbackLabel.setText("Non hai armi nell'inventario! Acquistane una dal negozio.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fight-view.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            FightController fightController = loader.getController();
            fightController.startFight(player, enemy);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** delega l'esecuzione di una singola scavata in miniera
     * e ne riporta il risultato
     */
    @FXML
    private void handleMine() {
        MiningResultDTO result = minieraService.dig(player);
        feedbackLabel.setText(result.getMessage());
    }

    ///gestisce il ritorno alla lobby caricando mainView
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
