package javacode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerTurnController implements Initializable {
    @FXML
    private Text turn, playerText, moneyText;

    @FXML
    private Button nextPlayerOrTurnButton;

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
}

