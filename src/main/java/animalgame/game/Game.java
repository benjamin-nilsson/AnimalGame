package animalgame.game;

import animalgame.fileutilities.FilesUtils;
import javafx.application.Application;
import java.util.ArrayList;

public class Game {
    //todo: Should it be static, or should each controller hold the necessary fields that will be made static, required and then we
    // just move between scenes calling the other scenes? Or should we just make a class that holds the game data that is static?
    //game should NOT be static.

    private  ArrayList<Player> myPlayerList;
    private  int turns;
    private  int currentTurn = 1;
    private int currentPlayerIndex = 0;
    private  String currentTab;
    private  ArrayList<Player> resultOrder;
    private String currentScene;

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
        Gui.setGameObject(this);
        Application.launch(Gui.class, args);
    }

    /**
     * Creates an object of the GameData class and sets its fields to the current status
     * of the fields in the game. It then creates a file containing that object.
     * @param gameName the name that the saved file will have.
     */
    public void saveGame(String gameName) {
        /*
        GameData standings = new GameData(myPlayerList, turns, currentTurn,
                currentPlayer, currentPlayerIndex, resultOrder);
        */
        FilesUtils.writeFile(gameName, this);
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
     * Sets the list of Players we have in the game.
     * @param myPlayerList takes a list of Player's that are in the game.
     */
    public void setMyPlayerList(ArrayList<Player> myPlayerList) {
        this.myPlayerList = myPlayerList;
    }

    /**
     * Provides the user with the specified number of turns.
     * @return number of turns to be played.
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Sets the number of turns that the game will have.
     * @param numberOfTurns number of turns the game will be played.
     */
    public void setTurns(int numberOfTurns) {
        turns = numberOfTurns;
    }

    /**
     * Provides the turn that the game is currently on.
     * @return a number representing what turn the game is currently on.
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**2
     * Sets the turn that the game will be on.
     * @param currentTurn the number of the turn that the game is on.
     */
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Provides the user with the player whose turn it is.
     * @return the Player object whose turn it is.
     */
    public Player getCurrentPlayer() {
        return myPlayerList.get(this.currentPlayerIndex);
    }

    /**
     * @return the index of the player whose turn it is in regards to the list of players.
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Sets the index of the player whose turn it is in regards to the list of players.
     * @param currentPlayerIndex the player index of the player whose turn it is.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * @return String representation of the tab that the store has that the user wants to open.
     */
    public String getCurrentTab() {
        return currentTab;
    }

    //Why have a special case here? Why not have 3 different scenes??
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
        //Depending on what the
        this.currentScene = nextScene;
        SceneCreator.launchScene(nextScene);
    }

    public String getCurrentScene() {
        return this.currentScene;
    }
}
