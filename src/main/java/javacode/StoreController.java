package javacode;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = Game.getCurrentPlayer();
        moneyText.setText(String.valueOf(currentPlayer.getMyMoney()) + "AB");

        selectTab();
        buyOptions(currentPlayer);
        ensureFieldsAreFilledOut();

    }

    private void ensureFieldsAreFilledOut() {
        BooleanBinding unchekedButtons = gender.selectedToggleProperty().isNull()
                .or(animals.selectedToggleProperty().isNull());
        buyAnimalButton.disableProperty().bind(
                Bindings.isEmpty(nameOfAnimalField.textProperty()).or(unchekedButtons)
        );
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

    public void buyOptions(Player currentPlayer) {
        if (currentPlayer.getMyMoney() < 5) {
            horseBox.setDisable(true);
            cowBox.setDisable(true);
            pigBox.setDisable(true);
            sheepBox.setDisable(true);
            dogBox.setDisable(true);
            buyAnimalButton.setDisable(true);
        }
        else if (currentPlayer.getMyMoney() < 10) {
            horseBox.setDisable(true);
            cowBox.setDisable(true);
            pigBox.setDisable(true);
            sheepBox.setDisable(true);
        }
        else if (currentPlayer.getMyMoney() < 20) {
            horseBox.setDisable(true);
            cowBox.setDisable(true);
        }
        else if (currentPlayer.getMyMoney() < 25) {
            horseBox.setDisable(true);
        }
    }


    public void openStoreScene(ActionEvent actionEvent) throws Exception {
        //: todo: put store items in gridpanes that we then can make invisible.
        // todo: maybe a tracker of which move was chosen making the other moves unavilable


    }

    public void openStoreAfterMoveScene(ActionEvent actionEvent) throws Exception {

        Player currentPlayer = Game.getCurrentPlayer();
        String name = nameOfAnimalField.getText();
        Animal animal = null;
        if (pigBox.isSelected()) {
            animal = new Pig(name, setGender());
        }
        else if (cowBox.isSelected()) {
            animal = new Cow(name, setGender());
        }
        else if (dogBox.isSelected()) {
            animal =  new Dog(name, setGender());
        }
        else if (horseBox.isSelected()) {
            animal = new Horse(name, setGender());
        }
        else if (sheepBox.isSelected()) {
            animal = new Sheep(name, setGender());
        }
            Store.buyAnimal(currentPlayer, animal);
        if (buyAnimalButton.isDisabled()) {
            errorEmptyFieldText.setVisible(true);
        } else {
            SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
        }
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

    public void openTurnScene(ActionEvent actionEvent) throws Exception{
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
            SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
            return;
        }

        var currentPlayerIndex = Game.getCurrentPlayerIndex();
        Game.setCurrentPlayerIndex(++currentPlayerIndex);
        Game.setCurrentPlayer(myPlayerList.get(currentPlayerIndex));

        SceneCreator.launchScene("/scenes/PlayerTurnMenuScene.fxml");
    }
}
