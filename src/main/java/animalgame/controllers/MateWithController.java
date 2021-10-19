package animalgame.controllers;

import animalgame.animals.abstractmodels.Animal;
import animalgame.game.Game;
import animalgame.game.Player;
import animalgame.game.SceneCreator;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import javax.annotation.processing.Generated;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MateWithController implements Initializable {
    @FXML
    private ComboBox<String> canMateDropDownList, willingAnimalsDropDownList;

    @FXML
    private Button findMatesButton, mateButton;

    @FXML
    private AnchorPane mateAnimalsPane;

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

                int nrPreMating, nrPostMating;
                nrPreMating = currentPlayer.getMyAnimals().size();
                animalToMateObject.mateWith(chosenMateObject);
                nrPostMating = currentPlayer.getMyAnimals().size();
                if (nrPreMating == nrPostMating) {
                    var alert = new Alert(Alert.AlertType.NONE, "Mating was unsuccessful!", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        openTurnScene();
                        return;
                    }
                }

                for(int i = nrPreMating; i < nrPostMating; i++) {
                    int animalIndex = i;
                    var pane = new Pane();
                    pane.setLayoutX(243);
                    pane.setLayoutY(93);
                    pane.setMinWidth(292);
                    pane.setMinHeight(200);
                    pane.setStyle("-fx-background-color: #ffffff;");
                    mateAnimalsPane.getChildren().add(pane);
                    Text text = new Text("Congratulations, you got " + (nrPostMating - nrPreMating) +
                            " new animal(s)! " + "Now set a name for your new animal(s)!");
                    text.setLayoutX(47);
                    text.setLayoutY(68);
                    text.setWrappingWidth(224.46356201171875);
                    TextField textField = new TextField();
                    textField.setPromptText("Name of child");
                    textField.setLayoutX(72);
                    textField.setLayoutY(100);
                    Button button = new Button("Ok");
                    button.setLayoutX(226);
                    button.setLayoutY(161);
                    button.setOnMouseClicked(e -> {
                        currentPlayer.getMyAnimals().get(animalIndex).setName(textField.getText());
                        mateAnimalsPane.getChildren().remove(pane);
                    });

                    //if we don't set openTurnScene in a method it gets executed before we can name the children
                    if (animalIndex == nrPreMating) {
                        button.setOnAction(event1 -> {
                            currentPlayer.getMyAnimals().get(animalIndex).setName(textField.getText());
                            openTurnScene();
                        });
                    }

                    pane.getChildren().addAll(text, textField, button);
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

    public void openTurnScene() {
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
        try {
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ageAnimals(Player currentPlayer) {
        for (Animal animal : currentPlayer.getMyAnimals()) {
            animal.endOfTurn();
        }
    }
}
