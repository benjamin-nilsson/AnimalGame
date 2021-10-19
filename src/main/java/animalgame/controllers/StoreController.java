package animalgame.controllers;

import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.*;
import animalgame.game.Game;
import animalgame.game.Player;
import animalgame.game.SceneCreator;
import animalgame.game.Store;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreController implements Initializable {

    @FXML
    private Text moneyText, errorEmptyFieldText;

    @FXML
    private TextField nameOfAnimalField;

    @FXML
    private TextArea allAnimalsInfoText;

    @FXML
    private ImageView pigImage,cowImage, dogImage, horseImage, sheepImage, meatImage, hayImage,
            linenImage, grassImage, weedImage;

    @FXML
    private Button buyAnimalButton, buyCornAndSoyButton, buyHayButton, buyFrolicButton,
            buyGrassAndWeedsButton, buyMixedGrain, buyDogFoodButton, sellAnimalButton, sellAllAnimalsButton;

    @FXML
    private RadioButton pigBox, cowBox, dogBox, horseBox, sheepBox, maleBox;

    @FXML
    private ToggleGroup animals, gender;

    @FXML
    private TabPane store;

    @FXML
    private Tab buyAnimalsTab, buyFoodTab, sellAnimalsTab;

    @FXML
    private ComboBox<String> allAnimalsDropDownList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = Game.getCurrentPlayer();
        setMoney(currentPlayer);
        selectTabAndPerformItsFunctions(currentPlayer);
        buyFoodOptions(currentPlayer);
    }

    private void buyFood(Player currentPlayer) {
        buyCornAndSoyButton.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new CornAndSoybeans());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );

        buyHayButton.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new BaledHay());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );

        buyFrolicButton.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new Frolic());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );

        buyGrassAndWeedsButton.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new GrassAndWeeds());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );

        buyMixedGrain.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new MixedGrain());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );

        buyDogFoodButton.setOnMouseClicked(event -> {
                    Store.buyFood(currentPlayer, new WellnesDryDogFood());
                    setMoney(currentPlayer);
                    buyFoodOptions(currentPlayer);
                }
        );
    }

    private void setMoney(Player currentPlayer) {
        moneyText.setText(String.valueOf(currentPlayer.getMyMoney()) + "AB");
    }

    private void buyFoodOptions(Player currentPlayer) {
        if (currentPlayer.getMyMoney() < 25) {
            buyCornAndSoyButton.setDisable(true);
            buyFrolicButton.setDisable(true);
            buyGrassAndWeedsButton.setDisable(true);
            buyMixedGrain.setDisable(true);
            buyDogFoodButton.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < 100) {
            buyHayButton.setDisable(true);
        }
    }

    private void noMissingAnimalFields() {
        BooleanBinding unchekedButtons = gender.selectedToggleProperty().isNull()
                .or(animals.selectedToggleProperty().isNull());
        buyAnimalButton.disableProperty().bind(
                Bindings.isEmpty(nameOfAnimalField.textProperty()).or(unchekedButtons)
        );
    }

    private void selectTabAndPerformItsFunctions(Player currentPlayer) {
        var currentTab = Game.getCurrentTab();
        var selectionModel = store.getSelectionModel();
        if (currentTab.equals("buyAnimals")) {
            selectionModel.select(buyAnimalsTab);
            buyFoodTab.setDisable(true);
            sellAnimalsTab.setDisable(true);
            buyAnimalsOptions(currentPlayer);
            noMissingAnimalFields();
        } else if (Game.getCurrentTab().equals("buyFood")) {
            selectionModel.select(buyFoodTab);
            buyAnimalsTab.setDisable(true);
            sellAnimalsTab.setDisable(true);
            buyFood(currentPlayer);
        }
        else if (Game.getCurrentTab().equals("sellAnimals")){
            selectionModel.select(sellAnimalsTab);
            buyAnimalsTab.setDisable(true);
            buyFoodTab.setDisable(true);

            allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());

            ArrayList<Animal> myAnimals = currentPlayer.getMyAnimals();
            populateAnimalDropDownList(myAnimals);

            BooleanBinding noAnimalSelected = allAnimalsDropDownList.getSelectionModel().selectedItemProperty().isNull();
            sellAnimalButton.disableProperty().bind(noAnimalSelected);
            ObservableList<String> animals = allAnimalsDropDownList.getItems();
            sellAllAnimalsButton.disableProperty().bind(Bindings.isEmpty(animals));

            sellAnimalButton.setOnMouseClicked(event -> sellAnimal(currentPlayer, myAnimals));
            // todo: fix the sellAllAnimalsProblem
            sellAllAnimalsButton.setOnMouseClicked(event -> {
                Store.sellAllAnimals(currentPlayer);
                allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());
            });
        }
    }

    private void sellAnimal(Player currentPlayer, ArrayList<Animal> myAnimals) {
        int selectedAnimal = allAnimalsDropDownList.getSelectionModel().getSelectedIndex();
        Animal animal = myAnimals.get(selectedAnimal);
        Store.sellAnimal(currentPlayer, animal);
        setMoney(currentPlayer);
        allAnimalsDropDownList.getItems().clear();
        populateAnimalDropDownList(myAnimals);
        allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());
    }

    private void populateAnimalDropDownList(ArrayList<Animal> myAnimals) {
        for (Animal a : myAnimals) {
            allAnimalsDropDownList.getItems().add(a.getName() + " sells for " + a.getValue() + "AB");
        }
    }

    private void buyAnimalsOptions(Player currentPlayer) {
        if (currentPlayer.getMyMoney() < 5) {
            dogBox.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < 10) {
            pigBox.setDisable(true);
            sheepBox.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < 20) {
            cowBox.setDisable(true);
        }

        if (currentPlayer.getMyMoney() < 25) {
            horseBox.setDisable(true);
        }
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
