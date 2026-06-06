package it.unicam.cs.mpgc.rpg125936.core;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.DatabaseSeeder;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetupService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    ///carica la schermata del menu principale dal file FXML e la mostra nella finestra.
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu-view.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game");
        primaryStage.show();
    }

    ///salva il giocatore corrente nel database quando la finestra viene chiusa.
   @Override
   public void stop(){
        PlayerRepository playerRepository = new PlayerRepository();
        Player player = GameSetupService.getInstance().getPlayer();
        if(player!=null){
            playerRepository.save(player);
        }
   }

    ///Entry point dell'applicazione. Inizializza il database e avvia il runtime JavaFX.
    public static void main(String[] args) {
        DatabaseSeeder.seed();
        launch(args);
    }
}
