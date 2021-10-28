package animalgame.game;

import animalgame.fileutilities.FilesUtils;
import javafx.application.Application;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Starts the game scene and keeps track of the games current standings
 * and sets up for each players turn.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class Game implements Serializable {
    private ArrayList<Player> myPlayerList;
    private int turns;
    private int currentTurn = 1;
    private int currentPlayerIndex = 0;
    private String currentTab, currentScene;
    private ArrayList<Player> resultOrder;

    public Game(String[] args) {
        myPlayerList = new ArrayList<>();
        resultOrder = new ArrayList<>();
        main(args);
    }

    /**
     * Launches the first scene.
     * @param args args
     */
    public void main(String[] args) {
        Gui.setGameObject(this);
        Application.launch(Gui.class, args);
    }

    /**
     * Creates an object of the GameData class and sets its fields to the current status
     * of the fields in the game. It then creates a file containing that object.
     * @param gameName the name that the saved file will have.
     */
    public void saveGame(String gameName) {
        FilesUtils.writeFile(gameName, this);
    }

    /**
     * Loads old game.
     * @param fileName name of saved file
     */
    public void loadOldFile(String fileName) {
        Game savedGame = (Game) FilesUtils.readFile(fileName);
        try {
            this.myPlayerList = savedGame.getMyPlayerList();
            this.turns = savedGame.getTurns();
            this.currentTurn = savedGame.getCurrentTurn();
            this.currentPlayerIndex = savedGame.getCurrentPlayerIndex();
            this.resultOrder = savedGame.getResultOrder();
            this.currentScene = savedGame.getCurrentScene();
        } catch (Exception ignore) {}
    }

    /**
     * Adds a player to the Games list of players.
     * @param player a Player object that will be used to play the game.
     */
    public void addPlayer(Player player) {
        myPlayerList.add(player);
    }

    //Not sure this is such a great idea. We probably still want a player that's
    //out of the game to be in the list for final scores.
    /**
     * Deletes the sent player object argument from the games list of players.
     * @param player a Player object that will be used to play the game.
     */
    public void deletePlayer(Player player) {
        this.myPlayerList.remove(player);
    }

    /**
     * checks if all turns have been played, if so ends the game
     * sets currentPlayerIndex to next in line
     */
    public void nextPlayer() throws Exception {
        if (this.currentPlayerIndex == this.myPlayerList.size() - 1) {
            // Last player in the list, last turn:
            if (this.currentTurn == this.turns) {
                this.nextScene("/scenes/AfterGameMenuScene.fxml");
                return;
            } else {
                // Last player in the list, not last turn:
                // increase currentTurn, set currentPlayerIndex to 0
                this.currentTurn++;
                this.currentPlayerIndex = 0;
            }
        } else {
            // Not last player in the list
            // increase the currentPlayerIndex
            this.currentPlayerIndex++;
        }
        // Then go to next scene
        this.nextScene("/scenes/PlayerTurnMenuScene.fxml");
    }

    /**
     * Adds a player to a list of players which keeps track of the players placement
     * in reverse order.
     * @param currentPlayer the player whose turn it is.
     */
    public void addPlayerToResultOrder(Player currentPlayer) {
        resultOrder.add(currentPlayer);
    }

    /**
     * Provides a list of all players that are still in the game.
     * @return a list containing all players that are in the game.
     */
    public ArrayList<Player> getMyPlayerList() {
        return myPlayerList;
    }

    /**
     * Returns the total number of turns the game is to be played
     * @return int turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Sets the total number of turns the game is to be played
     * @param numberOfTurns int number of turns
     */
    public void setTurns(int numberOfTurns) {
        turns = numberOfTurns;
    }

    /**
     * Returns the current turn
     * @return int currentTurn
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Returns the list of players in the game
     * @return ArrayList of Players
     */
    public Player getCurrentPlayer() {
        return myPlayerList.get(this.currentPlayerIndex);
    }

    /**
     * Returns the current Player
     * @return currentPlayer
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * @return String representation of the tab that the store has that the user wants to open.
     */
    public String getCurrentTab() {
        return currentTab;
    }

    /**
     * Sets the tab for the part of the store that the user wants to open.
     * @param currentTab String representation of the tab we want to display.
     */
    public void setCurrentTab(String currentTab) {
        this.currentTab = currentTab;
    }

    /**
     * Provides the order in which the players placed according to the game in reversed order.
     * Last is first and first is last in the list.
     * @return a list of player objects in reverse turn to their placement.
     */
    public ArrayList<Player> getResultOrder() {
        return resultOrder;
    }

    /**
     * Sets a list containing players and their placement for the game in reverse order.
     * @param resultOrder a list of Player objects according to their placement in reverse order.
     */
    public void setResultOrder(ArrayList<Player> resultOrder) {
        this.resultOrder = resultOrder;
    }

    /**
     * updates current scene and launches next scene
     */
    public void nextScene(String nextScene) throws Exception {
        this.currentScene = nextScene;
        SceneCreator.launchScene(nextScene);
    }

    /**
     * @return the scene that is showing.
     */
    public String getCurrentScene() {
        return this.currentScene;
    }
}
