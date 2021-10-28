package animalgame.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Responsible for setting and launching the first scene as well as holding
 * the rest of the scenes.
 */
public class Gui extends Application {
    private static Stage primaryStage;
    private static Game currentGame;

    public static void setGameObject(Game game) {
        currentGame = game;
    }

    public static Game getGameObject() {
        return currentGame;

    }
    @Override
    public void start(Stage primaryStage) {
        try {
            // setting up the login scene
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/StartGameMenuScene.fxml")));
            Gui.primaryStage = primaryStage;
            Gui.primaryStage.setTitle("Animal Game");
            var scene = new javafx.scene.Scene(root, 768, 450);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the stage where we are setting the scene
     */
    public static Stage getStage() {
        return primaryStage;
    }
}
