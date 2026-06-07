package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.location.Miniera;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.mine.MinieraService;
import it.unicam.cs.mpgc.rpg125936.service.mine.MiningResultDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Mondo1Controller {

    @FXML private Label titleLabel;
    @FXML private Label feedbackLabel;
    @FXML private Label playerHpLabel;
    @FXML private Label playerLivesLabel;
    @FXML private Label materialLabel;
    @FXML private VBox enemyList;
    @FXML private Button backBtn;
    @FXML private Button mineBtn;

    private Mondo mondo;
    private Miniera miniera;
    private Player player;
    private MinieraService minieraService;


    /**inizializza la schermata del mondo:
     * imposta giocatore, mondo, miniera e servizio mining;
     * mostra nome del mondo, HP e vite del giocatore;
     * genera per ogni nemico una riga con label info e bottone "Combatti";
     * una volta sconfitto, il bottone cambia in "Sconfitto"
     *
     * @param player giocatore corrente
     * @param mondo  mondo da visualizzare (es. Mondo1, Mondo2, …)
     */
    public void init(Player player, Mondo mondo) {
        this.player = player;
        this.mondo = mondo;
        this.miniera = mondo.getMiniera();
        this.minieraService = new MinieraService(miniera);

        titleLabel.setText(mondo.getName());
        playerHpLabel.setText("HP: " + player.getHealthStatus().getHealth());
        playerLivesLabel.setText("Vite: " + player.getLives());

        //per ogni nemico viene mostrato lo status di salute e il pulsante per combatterlo
        //se il nemico è sconfitto viene disabilitato il bottone e cambiata la scritta
        for (Enemy enemy : mondo.getEnemies()) {
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
        fightController.startFight(player, mondo, enemy);
    }

    /**delega l'esecuzione di una singola scavata in miniera
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
        SceneLoader.switchTo("/view/main-view.fxml", backBtn);
    }
}
