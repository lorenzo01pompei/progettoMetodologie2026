package it.unicam.cs.mpgc.rpg125936.controller;

import it.unicam.cs.mpgc.rpg125936.repository.HibernateUtil;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
       dopodiché viene fatto un reset generale su GameSetup
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
        GameSetup.reset();
        loadGameView();
    }

    //carica i salvataggi da GameSetup e invoca la game view per portare il player alla lobby
    @FXML
    private void handleContinue() {
        GameSetup.loadFromSave();
        loadGameView();
    }

    //carica la lobby del gioco
    private void loadGameView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = (Stage) continueBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
