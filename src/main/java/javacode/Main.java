package javacode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Parent root;
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // setting up the login scene
            root = FXMLLoader.load(getClass().getResource("/scenes/MainMenuScenes.fxml"));
            Main.primaryStage = primaryStage;
            primaryStage.setTitle("Name");
            var scene = new Scene(root, 768, 450);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param root stores a scene based which is based on the .fxml file
     */
    static void setRoot(Parent root) {
        Main.root = root;
    }

    /**
     * @return the .fxml based scene that we want to display to the user
     */
    static Parent getRoot() {
        return root;
    }

    /**
     * @return the stage where we are setting the scene
     */
    static Stage getStage() {
        return primaryStage;
    }
}