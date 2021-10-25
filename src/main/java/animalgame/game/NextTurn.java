package animalgame.game;

import animalgame.animals.abstractmodels.Animal;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.ArrayList;

public class NextTurn {
    // todo: fix so that it does what we want it to do when we switch between turns.

    public static void nextPlayer() throws Exception {
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
            // ageAnimals(currentPlayer);
        }
        else if (currentPlayer != lastPlayer) {
            var currentPlayerIndex = Game.getCurrentPlayerIndex();
            Game.setCurrentPlayerIndex(++currentPlayerIndex);
            Game.setCurrentPlayer(myPlayerList.get(currentPlayerIndex));
            // ageAnimals(currentPlayer);
        }

        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }

    public static void ageAnimals(Player currentPlayer) {
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        ArrayList<String> healthUpdate = new ArrayList<>();
        for (Animal animal : currentPlayer.getMyAnimals()) {
            var health = animal.getHealth();
            animal.endOfTurn();

            healthUpdate.add(animal.getName()
                    + " lost " + (health - animal.getHealth()) + " and now has " + animal.getHealth());

            if (animal.getHealth() <= 0) {
                deadAnimals.add(animal);
            }
        }

        Alert healthUpdateAlert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
        if (!currentPlayer.getMyAnimals().isEmpty()) {
            StringBuilder updateHealth = new StringBuilder();
            for (String update : healthUpdate) {
                updateHealth.append(update + "\n");
            }

            healthUpdateAlert.setContentText(updateHealth.toString());

            healthUpdateAlert.showAndWait();

            if (healthUpdateAlert.getResult() == ButtonType.OK) {
                healthUpdateAlert.close();
            }
        }

        // todo: fix like above with all animals
        if (!deadAnimals.isEmpty()) {
            Alert deathAlert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
            for (Animal a : deadAnimals) {
                deathAlert.setContentText(a.getName() + " died! \n");
            }
            deathAlert.showAndWait();

            if (deathAlert.getResult() == ButtonType.OK) {
                deathAlert.close();
            }
        }
    }

    /**
     * Removes a player that don't have enough money to buy an animal and doesn't have any animals.
     * Aan alert window notifies the player and depending on the game's list of players the game's data will
     * either be updated or simply do nothing.
     * @param currentPlayer the player whose turn it is.
     * @param lastPlayer last player in the arraylist of this game's players.
     */
    public static void haveLost(Player currentPlayer, Player lastPlayer) {
        int cheapestAnimalItem = 5;
        if (currentPlayer.getMyMoney() < cheapestAnimalItem && currentPlayer.getMyAnimals().isEmpty()) {
            Game.deletePlayer(currentPlayer);
            Game.addPlayerToResultOrder(currentPlayer);

            Alert alert = new Alert(Alert.AlertType.NONE, currentPlayer.getMyName() + " you lost the game!", ButtonType.OK);
            alertStyle(alert);

            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();

                ArrayList<Player> myPlayerList = Game.getMyPlayerList();

                boolean onePlayerLeft = myPlayerList.size() == 1;
                if (onePlayerLeft) {
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

    /**
     * Sets a determined style for the alert window.
     * @param alert takes an Alert object
     */
    private static void alertStyle(Alert alert) {
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
}
