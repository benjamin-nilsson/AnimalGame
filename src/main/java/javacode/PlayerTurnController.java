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
        if (currentPlayer == null)
            currentPlayer = myPlayerList.get(Game.getCurrentPlayerIndex());

        if (currentPlayer == lastPlayer) {
            nextPlayerOrTurnButton.setText("Next Turn");
        }
        else
            nextPlayerOrTurnButton.setText("Next Player");

        playerText.setText(currentPlayer.getMyName());
        int currentTurn = Game.getCurrentTurn();
        turn.setText(String.valueOf(currentTurn));
    }

    public void openTurnScene() throws Exception {
        // todo: fix the problem with it being one player to little
        int numberOfPlayers = Game.getMyPlayerList().size();
        ArrayList<Player> myPlayerList = Game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);
        Player currentPlayer = Game.getCurrentPlayer();

        if (currentPlayer == lastPlayer) {
            int currentTurn = Game.getCurrentTurn();
            Game.setCurrentTurn(++currentTurn);
            Game.setCurrentPlayer(myPlayerList.get(0));
            Game.setCurrentPlayerIndex(0);
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
            return;
        }
        else if (Game.getCurrentTurn() == Game.getTurns()) {
            // launch a summery of all players wealth to see who won.
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

    public void openMatingScene() {

    }

    public void openSellMenuScene() {

    }

    public void openStoreScene(ActionEvent actionEvent) {
    }
}

