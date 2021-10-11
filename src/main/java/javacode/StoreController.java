package javacode;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
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
    private Button buyPigButton, buyCowButton, buyDogButton, buySheepButton, buyHorseButton, buyMeatButton,
            butHayButton, buyLinenButton, buyGrassButton, buyWeedButton, completeButton;

    @FXML
    private CheckBox maleBox, femaleBox;

    @FXML
    private TabPane store;

    @FXML
    private Tab buyAnimalsTab, buyFoodTab, sellAnimalsTab;

    //private specis animal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyText.setText(String.valueOf(Game.getCurrentPlayer().getMyMoney()) + "AB");

        selectTab();

        String species;
        buyCowButton.setOnMouseClicked(event -> {
       //     species = "cow";
            specificationsWindow.setVisible(true);
        });

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
        if (maleBox.isSelected() && femaleBox.isSelected()) {
            errorEmptyFieldText.setVisible(false);
            errorCheckedText.setVisible(true);
            return;
        }
        else if (!maleBox.isSelected() && !femaleBox.isSelected() || nameOfAnimalField.getText().isEmpty()) {
            errorCheckedText.setVisible(false);
            errorEmptyFieldText.setVisible(true);
            return;
        }

        Gender gender;
        if (maleBox.isSelected()) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        //todo: maybe setters and getters for gender and name, to avoid having to create 10 of these methods;

        //todo: alternatively add two more fields to the grid with one being a textfield and one genderField;

        //animal.setName(name)
        //animal.setGender()
        //addAnimal(this animal field)



      //  animal = new Cow(nameOfAnimalField.getText(), gender);
       // Store.buyAnimal(Game.getCurrentPlayer(), animal);
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    private Cow typeOfAnimal(String name, Gender gender) {
        return new Cow(name, gender);
    }

    public void openTurnScene(ActionEvent actionEvent) {
    }
}
