package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.EnemyRepository;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.fight.FightService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
    private EnemyRepository enemyRepository;
    private PlayerRepository playerRepository;
    private Player player;
    private Enemy enemy;
    private Mondo currentMondo;

    /**inizializza le variabili d'ambiente prima di iniziare il combattimento;
     * se il player ha almeno 1 vita viene delegato il lavoro al fightService e
     * vengono aggiornati e mostrati status e inventario del player.
     * viene mostrato il bottone per ritirarsi dallo scontro
     *
     * @param player: giocatore che sceglie di iniziare il combattimento
     * @param enemy: nemico da combattere
     */
    public void startFight(Player player, Mondo currentMondo, Enemy enemy) {
        this.player = player;
        this.currentMondo = currentMondo;
        this.enemy = enemy;
        this.fightService = new FightService();
        this.playerRepository = new PlayerRepository();
        this.enemyRepository = new EnemyRepository();

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

    ///aggiorna lo status di salute del player e dell'enemy
    private void updateHp() {
        playerHpLabel.setText("HP: " + fightService.getBattlePlayer().getHealthStatus().getHealth());
        playerLivesLabel.setText("Vite: " + fightService.getBattlePlayer().getLives());
        enemyHpLabel.setText("HP: " + fightService.getBattleEnemy().getHealthStatus().getHealth());
    }

    /**recupera e mostra l'inventario di armi del player offrendo dei bottoni
     * per utilizzarle nel combattimento
     */
    private void showPlayerWeapons() {
        weaponList.getChildren().clear();
        List<FightItem> items = new ArrayList<>();

        if (items.isEmpty()) {
            weaponList.getChildren().add(new Label("Nessuna arma disponibile!"));
        }

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
                btn.setStyle(StyleConstants.WEAPON_BLUE);
                btn.setOnAction(e -> attack(actualIndex));
                weaponList.getChildren().add(btn);
            }
        }

    }

    /**delega l'attacco effettivo al fightService e ne gestisce l'esito
     *
     * @param weaponIndex: indice relativo all'arma scelta per l'attacco
     */
    private void attack(int weaponIndex) {

        String roundLog = fightService.playRound(weaponIndex);

        if (roundLog == null) {
            weaponList.setDisable(true);
            backButton.setVisible(true);
            backButton.setManaged(true);

            if (fightService.getBattleEnemy().getHealthStatus().getHealth() <= 0) {
                updateHp();
                feedbackLabel.setText("VITTORIA!");
                giveUpLabel.setVisible(false);
                giveUpButton.setVisible(false);
                giveUpButton.setManaged(false);
                handleVictory();
            } else if (fightService.getBattlePlayer().getHealthStatus().getHealth() <= 0) {
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

        player = playerRepository.save(player);
        enemyRepository.save(enemy);

    }

    /**gestisce la vittoria del player
     * imposta il nemico come sconfitto
     * recupera l'inventario del nemico e ne trasferisce una copia nell'inventario dell'utente
     */
    private void handleVictory() {
        Enemy enemy = fightService.getBattleEnemy();
        enemy.setDefeated(true);
        enemyRepository.save(enemy);
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem fi) {
                player.addItem(fi.copy());
                feedbackLabel.setText(feedbackLabel.getText() + "\nHai raccolto: " + fi.getName());
            }
        }
        player = playerRepository.save(player);
    }

    /**gestisce il ritorno alla schermata precedente;
     * se il player non ha vite, viene mostrata la mainView, altrimenti
     * viene mostrata mondo1View
     */
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

    /**gestisce la resa del player:
     * viene tolta una vita al player
     * viene mostrata mondo1View
     */
    @FXML
    private void giveUp(){
        player.decreaseLives();
        player = playerRepository.save(player);
        FXMLLoader loader = SceneLoader.switchTo("/view/mondo1-view.fxml", giveUpButton);
        Mondo1Controller mc = loader.getController();
        mc.init(player, currentMondo);
    }

}
