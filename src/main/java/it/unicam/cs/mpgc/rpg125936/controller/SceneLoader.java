package it.unicam.cs.mpgc.rpg125936.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneLoader {

    private SceneLoader() {}

    public static FXMLLoader switchTo(String fxml, Node source) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxml));
            Scene scene = new Scene(loader.load(), 1024, 768);
            ((Stage) source.getScene().getWindow()).setScene(scene);
            return loader;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
