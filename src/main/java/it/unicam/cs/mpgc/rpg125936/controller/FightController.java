package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.fight.FightService;
import it.unicam.cs.mpgc.rpg125936.service.fight.LootService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML private Button backButton;
    @FXML private Button giveUpButton;
    @FXML private Label giveUpLabel;

    private FightService fightService;
    private LootService lootService;
    private Player player;
    private Enemy enemy;

    public void startFight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.fightService = new FightService();
        this.lootService = new LootService();

        playerNameLabel.setText(player.getName());
        enemyNameLabel.setText(enemy.getName());
        playerLivesLabel.setText("Vite: " + player.getLives());

        if (player.getLives() <= 0) {
            feedbackLabel.setText("Hai esaurito le tue vite, corri allo shop per comprarne altre!!");
            weaponList.setDisable(true);
            backButton.setVisible(true);
            backButton.setManaged(true);
            giveUpButton.setVisible(false);
            giveUpButton.setManaged(false);
        } else {
            fightService.startFight(player, enemy);
            updateHp();
            showPlayerWeapons();
            giveUpButton.setVisible(true);
            giveUpButton.setManaged(true);
        }


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

        for (int i = 0; i < fightService.getBattlePlayer().getInventory().size(); i++) {
            Item item = fightService.getBattlePlayer().getInventory().get(i);
            if (item instanceof FightItem fi) {
                int actualIndex = i;
                Button btn = new Button(fi.getName() + " (Danno: " + fi.getDamage() + ")");
                btn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20;");
                btn.setOnAction(e -> attack(actualIndex));
                weaponList.getChildren().add(btn);
            }
        }

        if (items.isEmpty()) {
            weaponList.getChildren().add(new Label("Nessuna arma disponibile!"));
        }
    }

    private void attack(int weaponIndex) {
        if (fightService.getBattlePlayer().getHealth() <= 0 || fightService.getBattleEnemy().getHealth() <= 0) {
            feedbackLabel.setText("Il combattimento \u00E8 gi\u00E0 finito.");
            return;
        }

        String roundLog = fightService.playRound(weaponIndex);

        if (roundLog == null) {
            weaponList.setDisable(true);
            backButton.setVisible(true);
            backButton.setManaged(true);

            if (fightService.getBattleEnemy().getHealth() <= 0) {
                updateHp();
                feedbackLabel.setText("VITTORIA!");
                giveUpLabel.setVisible(false);
                giveUpButton.setVisible(false);
                giveUpButton.setManaged(false);
                handleVictory();
            } else if (fightService.getBattlePlayer().getHealth() <= 0) {
                updateHp();
                feedbackLabel.setText("SCONFITTA!");
                giveUpLabel.setVisible(false);
                giveUpButton.setVisible(false);
                giveUpButton.setManaged(false);
            }
        } else {
            updateHp();
            showPlayerWeapons();
            feedbackLabel.setText(roundLog);
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

    @FXML
    private void goBack() {
        String view;
        if (player.getLives() <= 0) {
            view = "/view/main-view.fxml";
        } else {
            view = "/view/mondo1-view.fxml";
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void giveUp(){
        String view = "/view/mondo1-view.fxml";
        player.decreaseLives();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = (Stage) giveUpButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
