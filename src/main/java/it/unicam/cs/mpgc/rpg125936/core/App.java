package it.unicam.cs.mpgc.rpg125936.core;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.DatabaseSeeder;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.game.GameSetup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu-view.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RPG Game");
        primaryStage.show();
    }

   @Override
   public void stop(){
        PlayerRepository playerRepository = new PlayerRepository();
        Player player = GameSetup.getInstance().getPlayer();
        if(player!=null){
            playerRepository.save(player);
        }
   }

    public static void main(String[] args) {
        DatabaseSeeder.seed();
        launch(args);
    }
}
