package animalgame.game;

import animalgame.fileutilities.FilesUtils;
import javafx.application.Application;
import java.util.ArrayList;

public class Game {
    //todo: Should it be static, or should each controller hold the necessary fields that will be made static, required and then we
    // just move between scenes calling the other scenes? Or should we just make a class that holds the game data that is static?
    private static ArrayList<Player> myPlayerList;
    private static int turns;
    private static int currentTurn = 1;
    private static Player currentPlayer;
    private static int currentPlayerIndex = 0;
    private static String currentTab;
    private static ArrayList<Player> resultOrder;

    public Game(String[] args) {
        myPlayerList = new ArrayList<>();
        resultOrder = new ArrayList<>();
        main(args);
    }

    /**
     * Launches the first scene.
     * @param args
     */
    public void main(String[] args) {
        Application.launch(Gui.class, args);
    }

    /**
     * Creates an object of the GameData class and sets its fields to the current status
     * of the fields in the game. It then creates a file containing that object.
     * @param gameName the name that the saved file will have.
     */
    public static void saveGame(String gameName) {
        GameData standings = new GameData(myPlayerList, turns, currentTurn,
                currentPlayer, currentPlayerIndex, resultOrder);
        FilesUtils.writeFile(gameName, standings);
    }

    /**
     * Adds a player to the Games list of players.
     * @param player a Player object that will be used to play the game.
     */
    public static void addPlayer(Player player) {
        myPlayerList.add(player);
    }

    /**
     * Deletes the sent player object argument from the games list of players.
     * @param player a Player object that will be used to play the game.
     */
    public static void deletePlayer(Player player) {
        myPlayerList.remove(player);
    }

    /**
     * Adds a player to a list of players which keeps track of the players placement
     * in reverse order.
     * @param currentPlayer the player whose turn it is.
     */
    public static void addPlayerToResultOrder(Player currentPlayer) {
        resultOrder.add(currentPlayer);
    }

    /**
     * Provides a list of all players that are still in the game.
     * @return a list containing all players that are in the game.
     */
    public static ArrayList<Player> getMyPlayerList() {
        return myPlayerList;
    }

    /**
     * Sets the list of Players we have in the game.
     * @param myPlayerList takes a list of Player's that are in the game.
     */
    public static void setMyPlayerList(ArrayList<Player> myPlayerList) {
        Game.myPlayerList = myPlayerList;
    }

    /**
     * Provides the user with the specified number of turns.
     * @return number of turns to be played.
     */
    public static int getTurns() {
        return turns;
    }

    /**
     * Sets the number of turns that the game will have.
     * @param numberOfTurns number of turns the game will be played.
     */
    public static void setTurns(int numberOfTurns) {
        turns = numberOfTurns;
    }

    /**
     * Provides the turn that the game is currently on.
     * @return a number representing what turn the game is currently on.
     */
    public static int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets the turn that the game will be on.
     * @param currentTurn the number of the turn that the game is on.
     */
    public static void setCurrentTurn(int currentTurn) {
        Game.currentTurn = currentTurn;
    }

    /**
     * Provides the user with the player whose turn it is.
     * @return the Player object whose turn it is.
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the player whose turn it will be.
     * @param currentPlayer the Player object whose turn it is.
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        Game.currentPlayer = currentPlayer;
    }

    /**
     * @return the index of the player whose turn it is in regards to the list of players.
     */
    public static int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Sets the index of the player whose turn it is in regards to the list of players.
     * @param currentPlayerIndex the player index of the player whose turn it is.
     */
    public static void setCurrentPlayerIndex(int currentPlayerIndex) {
        Game.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * @return String representation of the tab that the store has that the user wants to open.
     */
    public static String getCurrentTab() {
        return currentTab;
    }

    /**
     * Sets the tab for the part of the store that the user wants to open.
     * @param currentTab String representation of the tab we want to display.
     */
    public static void setCurrentTab(String currentTab) {
        Game.currentTab = currentTab;
    }

    /**
     * Provides the order in which the players placed according to the game in reversed order.
     * Last is first and first is last in the list.
     * @return a list of player objects in reverse turn to their placement.
     */
    public static ArrayList<Player> getResultOrder() {
        return resultOrder;
    }

    /**
     * Sets a list containing players and their placement for the game in reverse order.
     * @param resultOrder a list of Player objects according to their placement in reverse order.
     */
    public static void setResultOrder(ArrayList<Player> resultOrder) {
        Game.resultOrder = resultOrder;
    }
}
