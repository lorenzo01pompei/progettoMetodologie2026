package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.service.fight.FightResultService;
import it.unicam.cs.mpgc.rpg125936.service.fight.FightService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controller della schermata di combattimento.
 * Gestisce l'interfaccia utente e delega la logica a FightService
 * e gli esiti a FightResultService.
 */
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
    private FightResultService outcomeService;
    private Player player;
    private Enemy enemy;
    private Mondo currentMondo;

    /**
     * Avvia la schermata di combattimento.
     * Imposta giocatore e nemico; inizializza i servizi e aggiorna la UI.
     * Se il giocatore ha 0 vite, mostra un messaggio di blocco.
     */
    public void startFight(Player player, Mondo currentMondo, Enemy enemy) {
        this.player = player;
        this.currentMondo = currentMondo;
        this.enemy = enemy;
        this.fightService = new FightService();
        this.outcomeService = new FightResultService();

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

    /// aggiorna i label di HP e vite di giocatore e nemico dal FightService
    private void updateHp() {
        playerHpLabel.setText("HP: " + fightService.getBattlePlayer().getHealthStatus().getHealth());
        playerLivesLabel.setText("Vite: " + fightService.getBattlePlayer().getLives());
        enemyHpLabel.setText("HP: " + fightService.getBattleEnemy().getHealthStatus().getHealth());
    }

    /// mostra le armi del giocatore come bottoni; se non ci sono armi mostra un messaggio
    private void showPlayerWeapons() {
        weaponList.getChildren().clear();
        for (int i = 0; i < fightService.getBattlePlayer().getInventory().size(); i++) {
            Item item = fightService.getBattlePlayer().getInventory().get(i);
            if (item instanceof FightItem fi) {
                int index = i;
                Button btn = new Button(fi.getName() + " (Danno: " + fi.getDamage() + ")");
                btn.setStyle(StyleConstants.WEAPON_BLUE);
                btn.setOnAction(e -> attack(index));
                weaponList.getChildren().add(btn);
            }
        }
        if (weaponList.getChildren().isEmpty()) {
            weaponList.getChildren().add(new Label("Nessuna arma disponibile!"));
        }
    }

    /**
     * Esegue un round con l'arma all'indice scelto.
     * Se il round conclude la battaglia, invoca endBattle
     * altrimenti aggiorna la UI.
     */
    @FXML
    private void attack(int weaponIndex) {
        String roundLog = fightService.playRound(weaponIndex);
        if (roundLog == null) {
            endBattle();
        } else {
            updateHp();
            showPlayerWeapons();
            feedbackLabel.setText(roundLog);
        }
    }

    /// termina la battaglia: disabilita i controlli, mostra il pulsante di ritorno
    /// e gestisce vittoria o sconfitta tramite FightResultService
    private void endBattle() {
        weaponList.setDisable(true);
        backButton.setVisible(true);
        backButton.setManaged(true);
        hideGiveUpControls();

        if (fightService.getBattleEnemy().getHealthStatus().getHealth() <= 0) {
            updateHp();
            feedbackLabel.setText("VITTORIA!");
            player = outcomeService.handleVictory(player, enemy);
            appendLootFeedback();
        } else {
            updateHp();
            feedbackLabel.setText("SCONFITTA!");
            player = outcomeService.handleDefeat(player, enemy);
        }
    }

    /// nasconde label e bottone di resa
    private void hideGiveUpControls() {
        giveUpLabel.setVisible(false);
        giveUpButton.setVisible(false);
        giveUpButton.setManaged(false);
    }

    /// concatena al feedback i nomi delle armi raccolte dal nemico sconfitto
    private void appendLootFeedback() {
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem fi) {
                feedbackLabel.setText(feedbackLabel.getText() + "\nHai raccolto: " + fi.getName());
            }
        }
    }

    /// torna alla lobby se il giocatore ha 0 vite, altrimenti torna al mondo
    @FXML
    private void goBack() {
        if (player.getLives() <= 0) {
            SceneLoader.switchTo("/view/main-view.fxml", backButton);
        } else {
            FXMLLoader loader = SceneLoader.switchTo("/view/mondo1-view.fxml", backButton);
            Mondo1Controller mc = loader.getController();
            mc.init(player, currentMondo);
        }
    }

    /// gestisce la resa: toglie una vita e salva, poi mostra la schermata del mondo
    @FXML
    private void giveUp() {
        player = outcomeService.handleGiveUp(player);
        FXMLLoader loader = SceneLoader.switchTo("/view/mondo1-view.fxml", giveUpButton);
        Mondo1Controller mc = loader.getController();
        mc.init(player, currentMondo);
    }

}
