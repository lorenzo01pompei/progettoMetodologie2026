package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.fight.FightService;
import it.unicam.cs.mpgc.rpg125936.service.fight.LootService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class FightController {

    @FXML private Label playerNameLabel;
    @FXML private Label playerHpLabel;
    @FXML private Label playerLivesLabel;
    @FXML private Label enemyNameLabel;
    @FXML private Label enemyHpLabel;
    @FXML private VBox weaponList;
    @FXML private Label feedbackLabel;

    private FightService fightService;
    private LootService lootService;
    private Player player;
    private Enemy enemy;

    public void startFight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.fightService = new FightService();
        this.lootService = new LootService();

        fightService.startFight(player, enemy);

        playerNameLabel.setText(player.getName());
        enemyNameLabel.setText(enemy.getName());
        updateHp();
        showPlayerWeapons();
    }

    private void updateHp() {
        playerHpLabel.setText("HP: " + fightService.getBattlePlayer().getHealth());
        playerLivesLabel.setText("Vite: " + fightService.getBattlePlayer().getLives());
        enemyHpLabel.setText("HP: " + fightService.getBattleEnemy().getHealth());
    }

    private void showPlayerWeapons() {
        weaponList.getChildren().clear();
        List<FightItem> items = new ArrayList<>();
        for (var i : fightService.getBattlePlayer().getInventory()) {
            if (i instanceof FightItem fi) {
                items.add(fi);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            FightItem fi = items.get(i);
            int index = i;
            Button btn = new Button(fi.getName() + " (Danno: " + fi.getDamage() + ")");
            btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20;");
            btn.setOnAction(e -> attack(index));
            weaponList.getChildren().add(btn);
        }

        if (items.isEmpty()) {
            weaponList.getChildren().add(new Label("Nessuna arma disponibile!"));
        }
    }

    private void attack(int weaponIndex) {
        if (fightService.getBattlePlayer().getHealth() <= 0 || fightService.getBattleEnemy().getHealth() <= 0) {
            feedbackLabel.setText("Il combattimento è già finito.");
            return;
        }

        boolean continua = fightService.playRound(weaponIndex);
        updateHp();

        if (!continua) {
            if (fightService.getBattleEnemy().getHealth() <= 0) {
                feedbackLabel.setText("Hai vinto! Il nemico è stato sconfitto.");
                handleVictory();
            } else if (fightService.getBattlePlayer().getHealth() <= 0) {
                if (player.getLives() <= 0) {
                    feedbackLabel.setText("Sei morto! Game Over - non hai pi\u00f9 vite.");
                } else {
                    feedbackLabel.setText("Sei morto! Hai perso una vita. (Rimaste: " + player.getLives() + ")");
                }
            }
            weaponList.setDisable(true);
            if (player.getLives() <= 0) {
                goBackToLobby();
            } else {
                goBackToMondo1();
            }
        } else {
            feedbackLabel.setText("Colpo sferrato! Turno del nemico completato.");
        }
    }

    private void handleVictory() {
        Enemy originalEnemy = fightService.getOriginalEnemy();
        List<FightItem> loot = lootService.getDroppableItems(originalEnemy);
        for (FightItem item : loot) {
            lootService.collectItem(player, item);
            feedbackLabel.setText(feedbackLabel.getText() + "\nHai raccolto: " + item.getName());
        }
    }

    private void goBackToMondo1() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mondo1-view.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                Stage stage = (Stage) weaponList.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        delay.play();
    }

    private void goBackToLobby() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
                Scene scene = new Scene(loader.load(), 1024, 768);
                Stage stage = (Stage) weaponList.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        delay.play();
    }
}
