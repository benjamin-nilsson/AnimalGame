package animalgame.controllers;

import animalgame.animals.abstractmodels.Animal;
import animalgame.game.Game;
import animalgame.game.Player;
import animalgame.game.SceneCreator;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MateWithController implements Initializable {
    @FXML
    private ComboBox<String> canMateDropDownList, willingAnimalsDropDownList;

    @FXML
    private Button findMatesButton, mateButton;
    private ArrayList<Animal> willingAnimalsList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = Game.getCurrentPlayer();
        ArrayList<Animal> animalsToMateList = currentPlayer.canMate();
        System.out.println("Debug: # of Matable animals" + animalsToMateList.size());
        for (Animal animal : animalsToMateList) {
            canMateDropDownList.getItems().add(animal.getName());
        }

        BooleanBinding noSelectedAnimal = canMateDropDownList.getSelectionModel().selectedItemProperty().isNull();
        findMatesButton.disableProperty().bind(noSelectedAnimal);

        findMatesButton.setOnMouseClicked(event -> {
            int selectedAnimal = canMateDropDownList.getSelectionModel().getSelectedIndex();
            findMates(currentPlayer, animalsToMateList.get(selectedAnimal));
            }
        );
        BooleanBinding noSuitAbleMates = willingAnimalsDropDownList.getSelectionModel().selectedItemProperty().isNull();
        mateButton.disableProperty().bind(noSuitAbleMates);

        mateButton.setOnMouseClicked(event -> {
                int animalToMateIndex = canMateDropDownList.getSelectionModel().getSelectedIndex();
            System.out.println(animalToMateIndex);
                int chosenMateNameIndex = willingAnimalsDropDownList.getSelectionModel().getSelectedIndex();
            System.out.println(chosenMateNameIndex);

                Animal animalToMateObject = animalsToMateList.get(animalToMateIndex);
                Animal chosenMateObject = currentPlayer.willMateWith(animalToMateObject).get(chosenMateNameIndex);
                //Remove this, write loop that asks player to name any added animals to currentPlayer.animalsList()
                boolean successfulMating = animalToMateObject.mateWith(chosenMateObject);
                if (successfulMating) {
                    System.out.println("Debug: Your mating attempt was successful!");
                } else {
                    System.out.println("Debug: Your animals are not interested in mating.");
                }


            }
        );
    }

    private void findMates(Player currentPlayer, Animal animal) {
        willingAnimalsDropDownList.getItems().clear();

        for (Animal willingAnimal : currentPlayer.willMateWith(animal)) {
            willingAnimalsDropDownList.getItems().add(willingAnimal.getName());
        }
    }

    public void openStoreAfterMoveScene(ActionEvent actionEvent) {
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
}
