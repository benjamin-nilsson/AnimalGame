package javacode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public final class Game extends Application {
    private static ArrayList<Player> myPlayerList = new ArrayList<>();
    private static int turns;
    private static Parent root;
    private static Stage primaryStage;
    private static int currentTurn = 1;
    private static Player currentPlayer;
    private static int currentPlayerIndex = 0;
    private static int numberOfPlayers;
    private static String currentTab;
    private static ArrayList<Player> resultOrder;

    private Game() {
        myPlayerList = new ArrayList<>();
        resultOrder = new ArrayList<>();
    }

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

 /*   public void newTurn(int turns){
    }

    public void playerTurn(Player player){

    }*/

    public static void saveGame(String gameName) {
       // FilesUtils.writeFile(gameName, this);
    }

   /* public void loadGame(){

    }*/

    public static void addPlayerToResultOrder(Player currentPlayer) {
        resultOrder.add(currentPlayer);
    }

    public static ArrayList<Player> getResultOrder() {
        return resultOrder;
    }

    public static String getCurrentTab() {
        return currentTab;
    }

    public static void setCurrentTab(String currentTab) {
        Game.currentTab = currentTab;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static void setNumberOfPlayers(int numberOfPlayer) {
        Game.numberOfPlayers = numberOfPlayer;
    }

    public static int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public static void setCurrentPlayerIndex(int currentPlayerIndex) {
        Game.currentPlayerIndex = currentPlayerIndex;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    public static int getCurrentTurn() {
        return currentTurn;
    }

    public static void setCurrentTurn(int currentTurn) {
        Game.currentTurn = currentTurn;
    }

    public static void addPlayer(Player player) {
        myPlayerList.add(player);
    }

    public static ArrayList<Player> getMyPlayerList() {
        return myPlayerList;
    }

    public static void deletePlayer(Player player) {
        myPlayerList.remove(player);
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
