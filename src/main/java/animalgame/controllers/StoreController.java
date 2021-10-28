package animalgame.controllers;

import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.*;
import animalgame.game.*;
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

//Can we please split this scene and controller up? There is absolutely no point in having different tabs.

/**
 * Renders a store and depending on the selected tab it either enables the user to buy animals, buy food,
 * or sell animals.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class StoreController implements Initializable {

    @FXML
    private Text moneyText;

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
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        Player currentPlayer = this.game.getCurrentPlayer();

        selectTab(currentPlayer);
        setMoney(currentPlayer);
        buyFoodOptions(currentPlayer);
    }

    /**
     * Get the tab that the user has selected, which is either buyAnimals, buyFood, or sellAnimals,
     * and renders the selected tabs values and sets its functions.
     * @param currentPlayer the player whose turn it is.
     */
    private void selectTab(Player currentPlayer) {
        String currentTab = this.game.getCurrentTab();
        SingleSelectionModel<Tab> selectionModel = store.getSelectionModel();

        isBuyingAnimals(currentTab, selectionModel, currentPlayer);
        isBuyingFood(currentTab, selectionModel, currentPlayer);
        isSellingAnimals(currentTab, selectionModel, currentPlayer);
    }

    /**
     * Enables the player to buy one or more animals depending on the players money.
     * Ensures the required fields are filled out before buying an animal.
     * @param currentTab String representation of the tab we want to display.
     * @param selectionModel the tab we want to display in javafx format.
     * @param currentPlayer the player whose turn it is.
     */
    private void isBuyingAnimals(String currentTab, SingleSelectionModel<Tab> selectionModel, Player currentPlayer) {
        if (currentTab.equals("buyAnimals")) {
            selectionModel.select(buyAnimalsTab);

            buyFoodTab.setDisable(true);
            sellAnimalsTab.setDisable(true);

            buyAnimalsOptions(currentPlayer);
            noMissingAnimalFields();
        }
    }

    /**
     * Prevents the player from being able to buy animals it cannot afford.
     * @param currentPlayer the player whose turn it is.
     */
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

    /**
     * Takes away the function to buy an animal unless an animal is selected along with
     * a gender and name.
     */
    private void noMissingAnimalFields() {
        BooleanBinding unchekedButtons = gender.selectedToggleProperty().isNull()
                .or(animals.selectedToggleProperty().isNull());

        buyAnimalButton.disableProperty().bind(
                Bindings.isEmpty(nameOfAnimalField.textProperty()).or(unchekedButtons)
        );
    }

    /**
     * Enables the player to buy food for one or more animals depending on the players money.
     * @param currentTab String representation of the tab we want to display.
     * @param selectionModel the tab we want to display in javafx format.
     * @param currentPlayer the player whose turn it is.
     */
    private void isBuyingFood(String currentTab, SingleSelectionModel<Tab> selectionModel, Player currentPlayer) {
        if (currentTab.equals("buyFood")) {
            selectionModel.select(buyFoodTab);

            buyAnimalsTab.setDisable(true);
            sellAnimalsTab.setDisable(true);

            buyFood(currentPlayer);
        }
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
        moneyText.setText(currentPlayer.getMyMoney() + "AB");
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

    /**
     * Enables the player to sell one or more of its animals and displays the current status
     * of each of the player's owned animal.
     * @param currentTab String representation the tab we want to display.
     * @param selectionModel the tab we want to display in javafx format.
     * @param currentPlayer the player whose turn it is.
     */
    private void isSellingAnimals(String currentTab, SingleSelectionModel<Tab> selectionModel, Player currentPlayer) {
        if (currentTab.equals("sellAnimals")){
            selectionModel.select(sellAnimalsTab);

            buyAnimalsTab.setDisable(true);
            buyFoodTab.setDisable(true);

            allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());


            ArrayList<Animal> myAnimals = currentPlayer.getMyAnimals();
            populateAnimalDropDownList(myAnimals);

            canSellAnimals();
            sellAnimals(currentPlayer, myAnimals);
        }
    }

    /**
     * Populates a dropdown list containing all the player's animals.
     * @param myAnimals a list of a player's animals.
     */
    private void populateAnimalDropDownList(ArrayList<Animal> myAnimals) {
        for (Animal a : myAnimals) {
            allAnimalsDropDownList.getItems().add(a.getName() + " sells for " + a.getValue() + "AB");
        }
    }

    /**
     * Ensure that if the player wish to sell one animal that an animal from the player's dropdown list
     * must be selected and if the player want to sell all animals that the are animals to sell.
     */
    private void canSellAnimals() {
        BooleanBinding noAnimalSelected = allAnimalsDropDownList.getSelectionModel().selectedItemProperty().isNull();
        sellAnimalButton.disableProperty().bind(noAnimalSelected);
        //todo: fix so its grayed out after selling all animals.
        ObservableList<String> animals = allAnimalsDropDownList.getItems();
        sellAllAnimalsButton.disableProperty().bind(Bindings.isEmpty(animals));
    }

    /**
     * Enables the player to sell all its animals or the animal that was selected in
     * the dropdown list of its animals.
     * Updates the information displayed to the player after the sell.
     * @param currentPlayer the player whose turn it is.
     * @param myAnimals a list of a player's animals.
     */
    private void sellAnimals(Player currentPlayer, ArrayList<Animal> myAnimals) {
        sellAnimalButton.setOnMouseClicked(event -> sellAnimal(currentPlayer, myAnimals));

        sellAllAnimalsButton.setOnMouseClicked(event -> {
            Store.sellAllAnimals(currentPlayer);
            setMoney(currentPlayer);
            allAnimalsDropDownList.getItems().clear();
            allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());
        });
    }

    /**
     * Enables the user to sell the selected animal in the dropdown list and then updates
     * the information displayed to the player after the sell.
     * @param currentPlayer the player whose turn it is.
     * @param myAnimals a list of a player's animals.
     */
    private void sellAnimal(Player currentPlayer, ArrayList<Animal> myAnimals) {
        int selectedAnimal = allAnimalsDropDownList.getSelectionModel().getSelectedIndex();
        Animal animal = myAnimals.get(selectedAnimal);
        Store.sellAnimal(currentPlayer, animal);

        // Update store information.
        setMoney(currentPlayer);
        allAnimalsDropDownList.getItems().clear();
        populateAnimalDropDownList(myAnimals);
        allAnimalsInfoText.setText(currentPlayer.reportStatusAnimals());
    }

    /**
     * Handles the transaction of buying an animal and then creates the animal that
     * the player selected with the player's chosen gender and name.
     * Launches the StoreMenuScene when finished so the player can keep buying animals.
     * @param actionEvent Action event represents a click on the start game button.
     * @throws Exception
     */
    public void buyAnimal(ActionEvent actionEvent) throws Exception {
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

        Store.buyAnimal(this.game.getCurrentPlayer(), animal);
        SceneCreator.launchScene("/scenes/StoreMenuScene.fxml");
    }

    /**
     * Provides the user with the gender that it selected.
     * @return a String representation of the gender that the player selected.
     */
    private Gender setGender() {
        Gender gender;
        if (maleBox.isSelected()) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        return gender;
    }

    /**
     * Launches the PlayerTurnMenuScene for the next player.
     * @throws Exception
     */
    public void openTurnScene() throws Exception {
        this.game.nextPlayer();
    }
}
