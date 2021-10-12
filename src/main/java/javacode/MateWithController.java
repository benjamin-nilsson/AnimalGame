package javacode;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.naming.Binding;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MateWithController implements Initializable {
    @FXML
    private ComboBox<String> canMateDropDownList, willingAnimalsDropDownList;

    @FXML
    private Button findMatesButton, mateButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = Game.getCurrentPlayer();
        ArrayList<Animal> animalsList = currentPlayer.canMate();

        for (Animal animal : currentPlayer.canMate()) {
            canMateDropDownList.getItems().add(animal.getName());
        }

        findMatesButton.setOnMouseClicked(event -> {
            int selectedAnimal = canMateDropDownList.getSelectionModel().getSelectedIndex();
            findMates(currentPlayer, animalsList.get(selectedAnimal));
            }
        );

        mateButton.disableProperty().bind(Bindings.isEmpty(willingAnimalsDropDownList.getItems()));

        /*BooleanBinding unchekedButtons = gender.selectedToggleProperty().isNull()
                .or(animals.selectedToggleProperty().isNull());
        buyAnimalButton.disableProperty().bind(
                Bindings.isEmpty(nameOfAnimalField.textProperty()).or(unchekedButtons)*/

        mateButton.setOnMouseClicked(event -> {
                int animalToMateIndex = canMateDropDownList.getSelectionModel().getSelectedIndex();
                int chosenMateNameIndex = willingAnimalsDropDownList.getSelectionModel().getSelectedIndex();
                Animal animalToMateObject = animalsList.get(animalToMateIndex);
                Animal chosenMateObject = animalsList.get(chosenMateNameIndex);
                var successfulMating = animalToMateObject.mateWith(chosenMateObject);
                if (successfulMating) {

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

    public void openTurnScene(ActionEvent actionEvent) {
    }
}
