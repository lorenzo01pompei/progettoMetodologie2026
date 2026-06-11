package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.repository.HibernateUtil;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetupService;
import it.unicam.cs.mpgc.rpg125936.service.game.SaveGameService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MenuController {

    @FXML private Button continueBtn;

    /**inizializza il controller, se il player non ha partite salvate
      il bottone "Continua" viene disabilitato e cambia testo in "Nessun salvataggio"
     */
    @FXML
    public void initialize() {
        PlayerRepository repo = new PlayerRepository();
        if (!repo.hasSavedGame()) {
            continueBtn.setDisable(true);
            continueBtn.setText("Nessun salvataggio");
        }
    }

    /**si apre una sessione don il db e viene svuotato dai contenuti
       relativi ai salvataggi precedenti;
       viene fatto un reset generale su GameSetupService
       invoca la game view per portare il player alla lobby
     */
    @FXML
    private void handleNewGame() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.createNativeMutationQuery("DELETE FROM items WHERE user_id IS NOT NULL OR material_player_id IS NOT NULL").executeUpdate();
            session.createMutationQuery("DELETE FROM Enemy").executeUpdate();
            session.createMutationQuery("DELETE FROM Player").executeUpdate();
            t.commit();
        }
        GameSetupService.reset();
        loadGameView();
    }

    ///carica i salvataggi tramite SaveGameService e invoca la game view per portare il player alla lobby
    @FXML
    private void handleContinue() {
        new SaveGameService().loadFromSave(GameSetupService.getInstance());
        loadGameView();
    }

    ///carica la lobby del gioco
    private void loadGameView() {
        SceneLoader.switchTo("/view/main-view.fxml", continueBtn);
    }
}
