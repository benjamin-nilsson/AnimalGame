package animalgame.controllers;

import animalgame.animals.abstractmodels.Animal;
import animalgame.game.*;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Enables the player to mate animals, adding all new animals to the players animal list
 * and allows the player to set names for the new animals.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class MateWithController implements Initializable {

    @FXML
    private ComboBox<String> canMateDropDownList, willingAnimalsDropDownList;

    @FXML
    private Button mateButton;

    @FXML
    private AnchorPane mateAnimalsPane;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        Player currentPlayer = this.game.getCurrentPlayer();

        ArrayList<Animal> animalsToMateList = setMateAndWillingAnimalsLists(currentPlayer);
        haveSuitableMates();
        mateAnimals(currentPlayer, animalsToMateList);
    }

    /**
     * Sets the dropdown list for all the animals that can mate and all the animals that are willing
     * to mate when an item from the canMateDropDownList is selected.
     * Provides a list of the animals that are able to mate.
     * @param currentPlayer the player whose turn it is.
     * @return the list of animals that are able to mate.
     */
    private ArrayList<Animal> setMateAndWillingAnimalsLists(Player currentPlayer) {
        ArrayList<Animal> animalsToMateList = currentPlayer.canMate();
        for (Animal animal : animalsToMateList) {
            canMateDropDownList.getItems().add(animal.getName());
        }
        canMateDropDownList.setOnAction(event -> {
            int selectedAnimal = canMateDropDownList.getSelectionModel().getSelectedIndex();
            findMates(currentPlayer, animalsToMateList.get(selectedAnimal));
        });

        return animalsToMateList;
    }

    /**
     * Clears the previous set drop down list of willing animals and sets it to a new list of
     * willing animals that match the specifications for the selected animal.
     * @param currentPlayer the player whose turn it is.
     * @param animal the animal that we want to mate.
     */
    private void findMates(Player currentPlayer, Animal animal) {
        willingAnimalsDropDownList.getItems().clear();

        for (Animal willingAnimal : currentPlayer.willMateWith(animal)) {
            willingAnimalsDropDownList.getItems().add(willingAnimal.getName());
        }
    }

    /**
     * Mates the two chosen animals and displays if the mating was successful or not. If successful,
     * it adds the animals to the players list of animals and allows the user to set the names for
     * the new animals.
     * In both cases launches the PlayerTurnMenuScene for the next player.
     * @param currentPlayer the player whose turn it is.
     * @param animalsToMateList a list of animals which can mate.
     */
    private void mateAnimals(Player currentPlayer, ArrayList<Animal> animalsToMateList) {
        mateButton.setOnMouseClicked(event -> {
                int animalToMateIndex = canMateDropDownList.getSelectionModel().getSelectedIndex();
                System.out.println(animalToMateIndex);
                int chosenMateNameIndex = willingAnimalsDropDownList.getSelectionModel().getSelectedIndex();
                System.out.println(chosenMateNameIndex);
                Animal animalToMateObject = animalsToMateList.get(animalToMateIndex);
                Animal chosenMateObject = currentPlayer.willMateWith(animalToMateObject).get(chosenMateNameIndex);
                int litter = animalToMateObject.mateWith(chosenMateObject);
                if ((litter == 0)) {
                    unsuccessfulMating();
                } else {
                    try {
                        successfulMating(currentPlayer, litter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }

    /**
     * Opens pop-ups that the mating is successful and to name the offspring
     * @param currentPlayer the active player
     * @param litter the number of offspring
     */
    private void successfulMating(Player currentPlayer, int litter) {
        int animalIndex = currentPlayer.getMyAnimals().size() - litter;
        for(int i = animalIndex; i < animalIndex + litter; i++) {

            var pane = new Pane();
            pane.setLayoutX(243);
            pane.setLayoutY(93);
            pane.setMinWidth(292);
            pane.setMinHeight(200);
            pane.setStyle("-fx-background-color: #ffffff;");

            // add the created pane to the anchorPane of the scene
            mateAnimalsPane.getChildren().add(pane);
            Text text = new Text("Congratulations, you got " + litter +
                    " new animal(s)! " + "Now set a name for your new animal(s)!");
            text.setLayoutX(47);
            text.setLayoutY(68);
            text.setWrappingWidth(224.46356201171875);
            TextField textField = new TextField();
            textField.setPromptText("Name of child");
            textField.setLayoutX(103);
            textField.setLayoutY(100);
            Text gender = new Text(currentPlayer.getMyAnimals().get(i).getGender().toString());
            gender.setLayoutX(47);
            gender.setLayoutY(117);
            Button button = new Button("Ok");
            button.setLayoutX(226);
            button.setLayoutY(161);
            int finalI = i;
            button.setOnMouseClicked(e -> {
                currentPlayer.getMyAnimals().get(finalI).setName(textField.getText());
                mateAnimalsPane.getChildren().remove(pane);
            });
            // if we don't set openTurnScene in a method it gets executed before we can name the children
            if (i == animalIndex){
                button.setOnAction(event1 -> {
                    currentPlayer.getMyAnimals().get(animalIndex).setName(textField.getText());
                    openTurnScene();
                });
            }
            // add the created text, textField, and button to the created pane
            pane.getChildren().addAll(text, gender, textField, button);
        }

    }



    /**
     * Displays an alert that the mating was unsuccessful.
     */
    private void unsuccessfulMating() {
            var alert = new Alert(Alert.AlertType.NONE, "Mating was unsuccessful!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK)
                openTurnScene();
    }

    /**
     * Takes away the function to mate animals unless an animal is selected from the
     * willingAnimalsDropDownList.
     */
    private void haveSuitableMates() {
        BooleanBinding noSuitAbleMates = willingAnimalsDropDownList.getSelectionModel().selectedItemProperty().isNull();
        mateButton.disableProperty().bind(noSuitAbleMates);
    }

    /**
     * Launches the PlayerTurnMenuScene for the next player.
     */
    public void openTurnScene() {
        try {
            this.game.nextPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
