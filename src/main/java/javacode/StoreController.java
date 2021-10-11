package javacode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {

    @FXML
    private Pane specificationsWindow;

    @FXML
    private Text moneyText, errorEmptyFieldText, errorCheckedText;

    @FXML
    private TextField nameOfAnimalField;

    @FXML
    private ImageView pigImage,cowImage, dogImage, horseImage, sheepImage, meatImage, hayImage,
            linenImage, grassImage, weedImage;

    @FXML
    private Button buyAnimalButton, buyMeatButton,
            butHayButton, buyLinenButton, buyGrassButton, buyWeedButton, completeButton;

    @FXML
    private RadioButton pigBox, cowBox, dogBox, horseBox, sheepBox, maleBox, femaleBox;

    @FXML
    private ToggleGroup animals, gender;

    @FXML
    private TabPane store;

    @FXML
    private Tab buyAnimalsTab, buyFoodTab, sellAnimalsTab;

    //private specis animal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyText.setText(String.valueOf(Game.getCurrentPlayer().getMyMoney()) + "AB");

        selectTab();

       // var myAnimals = Game.getCurrentPlayer().getMyAnimals();

    }

    private void selectTab() {
        var currentTab = Game.getCurrentTab();
        var selectionModel = store.getSelectionModel();
        if (currentTab.equals("buyAnimals")) {
            selectionModel.select(buyAnimalsTab);
            buyFoodTab.setDisable(true);
            sellAnimalsTab.setDisable(true);
        } else if (Game.getCurrentTab().equals("buyFood")) {
            selectionModel.select(buyFoodTab);
            buyAnimalsTab.setDisable(true);
            sellAnimalsTab.setDisable(true);
        } else {
            selectionModel.select(sellAnimalsTab);
            buyAnimalsTab.setDisable(true);
            buyFoodTab.setDisable(true);
        }
    }


    public void openStoreScene(ActionEvent actionEvent) throws Exception {
        //: todo: put store items in gridpanes that we then can make invisible.
        // todo: maybe a tracker of which move was chosen making the other moves unavilable


    }

    public void openStoreAfterMoveScene(ActionEvent actionEvent) throws Exception {
        if (animals.getSelectedToggle() == null || gender.getSelectedToggle() == null || nameOfAnimalField.getText().isEmpty()) {
            errorEmptyFieldText.setVisible(true);
            return;
        }

        Player currentPlayer = Game.getCurrentPlayer();
        if (pigBox.isSelected()) {
            Store.buyAnimal(currentPlayer, new Pig(nameOfAnimalField.getText(), setGender()));
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
        else if (cowBox.isSelected()) {
            Store.buyAnimal(currentPlayer, new Cow(nameOfAnimalField.getText(), setGender()));
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
        else if (dogBox.isSelected()) {
            Store.buyAnimal(currentPlayer, new Dog(nameOfAnimalField.getText(), setGender()));
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
        else if (horseBox.isSelected()) {
            Store.buyAnimal(currentPlayer, new Horse(nameOfAnimalField.getText(), setGender()));
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
        else if (sheepBox.isSelected()) {
            Store.buyAnimal(currentPlayer, new Sheep(nameOfAnimalField.getText(), setGender()));
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
    }

    private void setAnimalNameAndGender() {
    }

    private Gender setGender() {
        Gender gender;
        if (maleBox.isSelected()) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        return gender;
    }

    private Cow typeOfAnimal(String name, Gender gender) {
        return new Cow(name, gender);
    }

    public void openTurnScene(ActionEvent actionEvent) {
    }
}
