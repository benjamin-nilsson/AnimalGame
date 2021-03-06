package animalgame.controllers;

import animalgame.animals.abstractmodels.Animal;
import animalgame.game.Game;
import animalgame.game.Player;
import animalgame.game.SceneCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
        // todo: implement message telling the player his animal died.

        int numberOfPlayers = Game.getMyPlayerList().size();
        ArrayList<Player> myPlayerList = Game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);

        Player currentPlayer = Game.getCurrentPlayer();
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

        haveLost(currentPlayer, lastPlayer);
    }

    private void haveLost(Player currentPlayer, Player lastPlayer) {
        if (currentPlayer.getMyMoney() < 5 && currentPlayer.getMyAnimals().isEmpty()) {

            Game.deletePlayer(currentPlayer);
            Game.addPlayerToResultOrder(currentPlayer);

            var numberOfPlayers = Game.getMyPlayerList().size();
            System.out.println(numberOfPlayers);
            ArrayList<Player> myPlayerList = Game.getMyPlayerList();

            Alert alert = new Alert(Alert.AlertType.NONE, currentPlayer.getMyName() + " you lost the game!", ButtonType.OK);
            alertStyle(alert);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();

                if (Game.getMyPlayerList().size() == 1) {
                    return;
                }
                else if (currentPlayer == lastPlayer) {
                    int currentTurn = Game.getCurrentTurn();
                    Game.setCurrentTurn(++currentTurn);
                    Game.setCurrentPlayer(myPlayerList.get(0));
                    Game.setCurrentPlayerIndex(0);
                    //ageAnimals(currentPlayer);
                }
                else if (currentPlayer != lastPlayer) {
                    var currentPlayerIndex = Game.getCurrentPlayerIndex();
                    Game.setCurrentPlayerIndex(++currentPlayerIndex);
                    Game.setCurrentPlayer(myPlayerList.get(currentPlayerIndex));
                    // ageAnimals(currentPlayer);
                }
            }
        }
    }

    public void openTurnScene() throws Exception {
        int numberOfPlayers = Game.getMyPlayerList().size();
        ArrayList<Player> myPlayerList = Game.getMyPlayerList();
        Player lastPlayer = myPlayerList.get(numberOfPlayers -1);
        Player currentPlayer = Game.getCurrentPlayer();


        if (Game.getMyPlayerList().size() == 1) {
            SceneCreator.launchScene("/scenes/AfterGameMenuScene.fxml");
            return;
        }
        else if (currentPlayer == lastPlayer) {
            if (Game.getCurrentTurn() == Game.getTurns()) {
                SceneCreator.launchScene("/scenes/AfterGameMenuScene.fxml");
                return;
            }
            int currentTurn = Game.getCurrentTurn();
            Game.setCurrentTurn(++currentTurn);
            Game.setCurrentPlayer(myPlayerList.get(0));
            Game.setCurrentPlayerIndex(0);
            ageAnimals(currentPlayer);
        }
        else if (currentPlayer != lastPlayer) {
            var currentPlayerIndex = Game.getCurrentPlayerIndex();
            Game.setCurrentPlayerIndex(++currentPlayerIndex);
            Game.setCurrentPlayer(myPlayerList.get(currentPlayerIndex));
            ageAnimals(currentPlayer);
        }

        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }

    private void ageAnimals(Player currentPlayer) {
        for (Animal animal : currentPlayer.getMyAnimals()) {
            animal.endOfTurn();
        }
    }

    private void availableOptions(Player currentPlayer) {
        int cheapestAnimalItem = 5;
        int cheapestFoodItem = 25;

        if (Game.getCurrentTurn() == Game.getTurns()) {
            buyAnimalButton.setDisable(true);
            buyFoodButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
            mateAnimalsButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < cheapestAnimalItem) {
            buyAnimalButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < cheapestFoodItem) {
            buyFoodButton.setDisable(true);
        }

        if (currentPlayer.getMyAnimals().isEmpty()) {
            mateAnimalsButton.setDisable(true);
            sellAnimalsButton.setDisable(true);
            feedAnimalsButton.setDisable(true);
        }

        if (currentPlayer.canMate().isEmpty()) {
            mateAnimalsButton.setDisable(true);
        }

        if (currentPlayer.canEat().isEmpty())
            System.out.println("is empty");

        if (currentPlayer.getMyFood().isEmpty() || currentPlayer.canEat().isEmpty()) {
            feedAnimalsButton.setDisable(true);
        }
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
        SceneCreator.launchScene("/scenes/FeedAnimalsScene.fxml");
    }

    public void openMatingScene() throws Exception {
        SceneCreator.launchScene("/scenes/MateWithScene.fxml");
    }

    public void openStoreWithSellScene() throws Exception {
        Game.setCurrentTab("sellAnimals");
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    public void openStoreScene(ActionEvent actionEvent) {
    }

    /**
     * Sets a determined style for the alert window.
     *
     * @param alert takes an Alert object
     */
    private void alertStyle(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #000000;");
        dialogPane.lookup(".content.label").setStyle("-fx-font-size: 12px; "
                + "-fx-font-weight: bold; -fx-text-fill: #ffffff;");

        ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-color: #a51414;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor:hand;"));
    }

    public void saveGame(ActionEvent actionEvent) throws Exception {
        SceneCreator.launchScene("/scenes/SaveGameScene.fxml");
    }
}

