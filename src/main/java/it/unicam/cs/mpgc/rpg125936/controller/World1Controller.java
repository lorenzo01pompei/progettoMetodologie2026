package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mine;
import it.unicam.cs.mpgc.rpg125936.domain.location.World;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.mine.MineService;
import it.unicam.cs.mpgc.rpg125936.service.mine.MiningResultDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller che gestisce la schermata del mondo1, mostra lo status dell'utente
 * la lista di nemici con le loro informazioni e con il loro bottone per attarli
 * delega i compiti di fight e di mining
 */

public class World1Controller {

    @FXML private Label titleLabel;
    @FXML private Label feedbackLabel;
    @FXML private Label playerHpLabel;
    @FXML private Label playerLivesLabel;
    @FXML private VBox enemyList;
    @FXML private Button backBtn;

    private World world;
    private Mine mine;
    private Player player;
    private MineService mineService;


    /**inizializza la schermata del mondo:
     * imposta giocatore, mondo, miniera e servizio mining;
     * mostra nome del mondo, HP e vite del giocatore;
     * genera per ogni nemico una riga con label info e bottone "Combatti";
     * una volta sconfitto, il bottone cambia in "Sconfitto"
     *
     * @param player giocatore corrente
     * @param world  mondo da visualizzare (es. World1, World2, …)
     */
    public void init(Player player, World world) {
        this.player = player;
        this.world = world;
        this.mine = world.getMine();
        this.mineService = new MineService(mine);

        titleLabel.setText(world.getName());
        playerHpLabel.setText("HP: " + player.getHealthStatus().getHealth());
        playerLivesLabel.setText("Vite: " + player.getLives());

        //per ogni nemico viene mostrato lo status di salute e il pulsante per combatterlo
        //se il nemico è sconfitto viene disabilitato il bottone e cambiata la scritta
        for (Enemy enemy : world.getEnemies()) {
            String className = enemy.getClass().getSimpleName();
            Label info = new Label(className + " - " + enemy.getName() + " (HP: " + enemy.getHealthStatus().getHealth() + ")");
            info.setStyle(StyleConstants.ENEMY_LABEL);

            Button fightBtn = new Button("Combatti");
            fightBtn.setStyle(StyleConstants.FIGHT_RED);

            if (enemy.isDefeated()) {
                fightBtn.setDisable(true);
                fightBtn.setText("Sconfitto");
                fightBtn.setStyle(StyleConstants.DEFEATED_GRAY);
            } else {
                fightBtn.setOnAction(e -> handleFight(enemy));
            }


            HBox row = new HBox(15, info, fightBtn);
            row.setStyle(StyleConstants.ENEMY_ROW);
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
        FXMLLoader loader = SceneLoader.switchTo("/view/fight-view.fxml", backBtn);
        FightController fightController = loader.getController();
        fightController.startFight(player, world, enemy);
    }

    /**delega l'esecuzione di una singola scavata in miniera
     * e ne riporta il risultato
     */
    @FXML
    private void handleMine() {
        MiningResultDTO result = mineService.dig(player);
        feedbackLabel.setText(result.getMessage());
    }

    ///gestisce il ritorno alla lobby caricando mainView
    @FXML
    private void handleBack() {
        SceneLoader.switchTo("/view/main-view.fxml", backBtn);
    }
}
