package animalgame.controllers;

import animalgame.game.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Shows the player's information as well as providing the player with different options depending
 * on the player situation as well as the game's rules.
 * Removes a player if the player lost the game.
 */
public class PlayerTurnController implements Initializable {

    @FXML
    private Text turn, playerText, moneyText;

    @FXML
    private TextArea farmInformation;

    @FXML
    private Button nextPlayerOrTurnButton, buyAnimalButton, buyFoodButton, feedAnimalsButton,
            mateAnimalsButton, sellAnimalsButton;

    private int cheapestAnimalItem = 5;
    private int cheapestFoodItem = 25;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // todo: implement message telling the player his animal died.
        this.game = Gui.getGameObject();
        int numberOfPlayers = this.game.getMyPlayerList().size();
        ArrayList<Player> myPlayerList = this.game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);
        Player currentPlayer = this.game.getCurrentPlayer();

        currentPlayer.ageAnimals();
        if(stillInGame(currentPlayer)){
            displayPlayerInformation(currentPlayer, lastPlayer);
            availableOptions(currentPlayer);
        } else {
            //simply skip to next
            try {
                game.nextPlayer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean stillInGame(Player currentPlayer) {
        return currentPlayer.getMyAnimals().size() > 0 || currentPlayer.getMyMoney() > 0;
    }

    /**
     * Sets the players  information such as whose turn it is, current turn, player's money,
     * player's food and animal inventory.
     * Displays if the game will move on the next player or show the result.
     * @param currentPlayer the player whose turn it is.
     * @param lastPlayer last player in the arraylist of this game's players.
     */
    private void displayPlayerInformation(Player currentPlayer, Player lastPlayer) {
        int currentTurn = this.game.getCurrentTurn();
        turn.setText(String.valueOf(currentTurn) + " of " + this.game.getTurns());
        playerText.setText(currentPlayer.getMyName());
        moneyText.setText(String.valueOf(currentPlayer.getMyMoney()) + "AB");
        farmInformation.setText(currentPlayer.reportStatus());

        if (this.game.getCurrentTurn() == this.game.getTurns() && currentPlayer == lastPlayer) {
            nextPlayerOrTurnButton.setText("Get Result");
        } else {
            nextPlayerOrTurnButton.setText("Next Player");
        }
    }

    /**
     * Controls which options that are possible for the player to choose based on the player's situation and
     * the rules of the game.
     * The ability to select an option will be removed if the player don't meet the requirements.
     * @param currentPlayer currentPlayer the player whose turn it is.
     */
    private void availableOptions(Player currentPlayer) {
        boolean lastRound = this.game.getCurrentTurn() == this.game.getTurns();
        if (lastRound) {
            buyAnimalButton.setDisable(true);
            buyFoodButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
            mateAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyAnimals().isEmpty()) {
            mateAnimalsButton.setDisable(true);
            sellAnimalsButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyFood().isEmpty() || currentPlayer.canEat().isEmpty()) {
            feedAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < cheapestAnimalItem) {
            buyAnimalButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < cheapestFoodItem) {
            buyFoodButton.setDisable(true);
        }

        if (currentPlayer.canMate().isEmpty()) {
            mateAnimalsButton.setDisable(true);
        }
    }

    /**
     * Launches the PlayerTurnMenuScene for the next player.
     * @throws Exception
     */
    public void openTurnScene() throws Exception {
        game.nextPlayer();
    }

    /**
     * Sets the current tab to buyFood so that the buyAnimals tab will be opened when the store scene is launched.
     * Launches the SaveGameScene.
     * @throws Exception
     */
    public void openStoreWithAnimalsScene() throws Exception{
        this.game.setCurrentTab("buyAnimals");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    /**
     * Sets the current tab to buyFood so that the buyFood tab will be opened when the store scene is launched.
     * Launches the StoreMenuScene.
     * @throws Exception
     */
    public void openStoreWithFoodScene() throws Exception {
        this.game.setCurrentTab("buyFood");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");

    }

    /**
     * Launches the FeedAnimalsScene.
     * @throws Exception
     */
    public void openFeedAnimalsMenuScene() throws Exception {
        SceneCreator.launchScene("/scenes/FeedAnimalsScene.fxml");
    }

    /**
     * Launches the MateWithScene.
     * @throws Exception
     */
    public void openMatingScene() throws Exception {
        SceneCreator.launchScene("/scenes/MateWithScene.fxml");
    }

    /**
     * Sets the current tab to buyFood so that the sellAnimals tab will be opened when the store scene is launched.
     * Launches the SaveGameScene.
     * @throws Exception
     */
    public void openStoreWithSellScene() throws Exception {
        this.game.setCurrentTab("sellAnimals");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    /**
     * Launches the SaveGameScene.
     * @param actionEvent Action event represents a click on the start game button.
     * @throws Exception
     */
    public void saveGame(ActionEvent actionEvent) throws Exception {
        SceneCreator.launchScene("/scenes/SaveGameScene.fxml");
    }
}

