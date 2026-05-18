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

    }

    private void updateHp() {
        playerHpLabel.setText("HP: " + fightService.getBattlePlayer().getHealth());
        enemyHpLabel.setText("HP: " + fightService.getBattleEnemy().getHealth());
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
}
