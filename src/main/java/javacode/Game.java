package javacode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {
    private static ArrayList<Player> myPlayerList = new ArrayList<>();
    private static int turns;
    private static int maxTurns;
    static Parent root;
    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // setting up the login scene
            root = FXMLLoader.load(getClass().getResource("/scenes/StartGameMenuScene.fxml"));
            Game.primaryStage = primaryStage;
            primaryStage.setTitle("Name");
            var scene = new javafx.scene.Scene(root, 768, 450);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPlayer(Player player){
        this.myPlayerList.add(player);
    }

    public void newTurn(int turns){

    }

    public void playerTurn(Player player){

    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void saveGame(){
    }

    public void loadGame(){

    }

    static void setTurns(int numberOfTurns) {
        turns = numberOfTurns;
    }

    static int getTurns() {
        return turns;
    }

    /**
     * @param root stores a scene based which is based on the .fxml file
     */
    static void setRoot(Parent root) {
        Game.root = root;
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
