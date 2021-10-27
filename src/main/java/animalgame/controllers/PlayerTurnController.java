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
 * If a player have no possible actions his/her turn is skipped.
 */
public class PlayerTurnController implements Initializable {

    @FXML
    private Text turn, playerText, moneyText;

    @FXML
    private TextArea farmInformation;

    @FXML
    private Button nextPlayerOrTurnButton, buyAnimalButton, buyFoodButton, feedAnimalsButton,
            mateAnimalsButton, sellAnimalsButton;

    // Debug - Don't love this - can we not somehow actually get these values?
    private int cheapestAnimalItem = 5;
    private int cheapestFoodItem = 25;

    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        Player currentPlayer = this.game.getCurrentPlayer();

        currentPlayer.ageAnimals();
        if(stillInGame(currentPlayer)){
            displayPlayerInformation(this.game.getCurrentPlayer());
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
     */
    private void displayPlayerInformation(Player currentPlayer) {
        int currentTurn = this.game.getCurrentTurn();
        turn.setText(currentTurn + " of " + this.game.getTurns());
        playerText.setText(currentPlayer.getMyName());
        moneyText.setText(currentPlayer.getMyMoney() + "AnimalBucks");
        farmInformation.setText(currentPlayer.reportStatus());
    }

    /**
     * Controls which options that are possible for the player to choose based on the player's situation and
     * the rules of the game.
     * The ability to select an option will be removed if the player don't meet the requirements.
     * @param currentPlayer currentPlayer the player whose turn it is.
     */
    private void availableOptions(Player currentPlayer) {

        if (currentPlayer.getMyAnimals().isEmpty()) {
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

