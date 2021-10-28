package animalgame.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds the required data for the Game.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class GameData implements Serializable {
    private ArrayList<Player> myPlayerList;
    private int turns;
    private int currentTurn = 1;
    private Player currentPlayer;
    private int currentPlayerIndex = 0;
    private ArrayList<Player> resultOrder;
    private final String currentScene;

    public GameData(ArrayList<Player> myPlayerList, int turn, int currentTurn, Player currentPlayer,
                    int currentPlayerIndex, ArrayList<Player> resultOrder, String currentScene) {
        this.myPlayerList = myPlayerList;
        this.turns = turn;
        this.currentTurn = currentTurn;
        this.currentPlayer = currentPlayer;
        this.currentPlayerIndex = currentPlayerIndex;
        this.resultOrder = resultOrder;
        this.currentScene = currentScene;

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
     * @param turns number of turns the game will be played.
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }

    /**
     * Provides the turn that the game is currently on.
     * @return a number representing what turn the game is currently on.
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
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
        return currentPlayer;
    }

    /**
     * Sets the player whose turn it will be.
     * @param currentPlayer the Player object whose turn it is.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the index of the player whose turn it is in regards to the list of players.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Sets the index of the player whose turn it is in regards to the list of players.
     * @param currentPlayerIndex the player index of the player whose turn it is.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
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

    public String getCurrentScene() {
        return currentScene;
    }
}
