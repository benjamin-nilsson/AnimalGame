package javacode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerTurnController implements Initializable {
    @FXML
    private Text turn, playerText, moneyText;

    @FXML
    private TextArea farmInformation;

    @FXML
    private Button nextPlayerOrTurnButton, buyAnimalButton, buyFoodButton, feedAnimalsButton,
            mateAnimalsButton, sellAnimalsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        var numberOfPlayers = Game.getMyPlayerList().size();

        ArrayList<Player> myPlayerList = Game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);

        var currentPlayer = Game.getCurrentPlayer();
        if (currentPlayer == null) {
            Game.setCurrentPlayer(myPlayerList.get(Game.getCurrentPlayerIndex()));
            currentPlayer = Game.getCurrentPlayer();
        }


        if (Game.getCurrentTurn() == Game.getTurns() && currentPlayer == lastPlayer) {
            nextPlayerOrTurnButton.setText("Get Result");
        }
        else if (currentPlayer == lastPlayer) {
            nextPlayerOrTurnButton.setText("Next Turn");
        }
        else {
            nextPlayerOrTurnButton.setText("Next Player");
        }

        playerText.setText(currentPlayer.getMyName());
        int currentTurn = Game.getCurrentTurn();
        turn.setText(String.valueOf(currentTurn) + " of " + Game.getTurns());
        moneyText.setText(String.valueOf(currentPlayer.getMyMoney()) + "AB");
        farmInformation.setText(currentPlayer.reportStatus());

        availableOptions(currentPlayer);
    }

    private void availableOptions(Player currentPlayer) {
        int cheapestStoreItem = 5;

        if (Game.getCurrentTurn() == Game.getTurns()) {
            buyAnimalButton.setDisable(true);
            buyFoodButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
            mateAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < cheapestStoreItem) {
            buyAnimalButton.setDisable(true);
            buyFoodButton.setDisable(true);
        }

        if (currentPlayer.getMyAnimals().isEmpty() || currentPlayer.canMate().isEmpty()) {
            //todo: also add if they don't have a male or female of the animal
            mateAnimalsButton.setDisable(true);
            sellAnimalsButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyFood().isEmpty()) {
            feedAnimalsButton.setDisable(true);
        }
    }

    public void openTurnScene() throws Exception {
        int numberOfPlayers = Game.getMyPlayerList().size();
        ArrayList<Player> myPlayerList = Game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);
        Player currentPlayer = Game.getCurrentPlayer();

        if (currentPlayer == lastPlayer) {
            if (Game.getCurrentTurn() == Game.getTurns()) {
                Game.getStage().close();
                return;
            }
            int currentTurn = Game.getCurrentTurn();
            Game.setCurrentTurn(++currentTurn);
            Game.setCurrentPlayer(myPlayerList.get(0));
            Game.setCurrentPlayerIndex(0);
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
            return;
        }

        var currentPlayerIndex = Game.getCurrentPlayerIndex();
        Game.setCurrentPlayerIndex(++currentPlayerIndex);
        Game.setCurrentPlayer(myPlayerList.get(currentPlayerIndex));

        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }

    public void openStoreWithAnimalsScene() throws Exception{
        Game.setCurrentTab("buyAnimals");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    public void openStoreWithFoodScene() throws Exception {
        Game.setCurrentTab("buyFood");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");

    }

    public void openFeedAnimalsMenuScene() throws Exception {
        Game.setCurrentTab("sellAnimals");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    public void openMatingScene() throws Exception {
        SceneCreator.launchScene("/scenes/MateWithScene.fxml");
    }

    public void openSellMenuScene() {

    }

    public void openStoreScene(ActionEvent actionEvent) {
    }
}

