package animalgame.controllers;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import animalgame.game.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Enables the player to feed animals, updating the players food list accordingly as well
 * as the health of the fed animal.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class FeedAnimalsController implements Initializable {
    private Game game;

    @FXML
    private ComboBox<String> animalsThatCanEatDropDownList, eatableFoodDropDownList;
    @FXML
    private Button feedButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = Gui.getGameObject();
        Player currentPlayer = this.game.getCurrentPlayer();

        ArrayList<Animal> animalsThatCanEat = setAnimalAndEatableFoodLists(currentPlayer);
        haveEatableFood();
        feedAnimal(currentPlayer, animalsThatCanEat);
    }

    /**
     /**
     * Sets the dropdown list for all the animals that the player has food for and all the food
     * that the player has for an animal when an item from the animalsThatCanEatDropDownList is selected.
     * Provides a list of the animals that the player has food for.
     * @param currentPlayer the player whose turn it is.
     * @return a list containing all the animals that the current player has food for.
     */
    private ArrayList<Animal> setAnimalAndEatableFoodLists(Player currentPlayer) {
        ArrayList<Animal> animalsList = currentPlayer.canEat();
        for (Animal animal : animalsList) {
            animalsThatCanEatDropDownList.getItems().add(animal.getName());
        }

        animalsThatCanEatDropDownList.setOnAction(event -> {
            int selectedAnimal = animalsThatCanEatDropDownList.getSelectionModel().getSelectedIndex();

            findFood(currentPlayer, animalsList.get(selectedAnimal));
        });

        return animalsList;
    }

    /**
     * Clears the previous set drop down list of all the set food and sets it to a new list
     * containing food that match the specifications for the selected animal.
     * @param currentPlayer the player whose turn it is.
     * @param animal the animal that we want to mate.
     */
    private void findFood(Player currentPlayer, Animal animal) {
        eatableFoodDropDownList.getItems().clear();

        for (Food food : currentPlayer.getMyFood()) {
            if (animal.canEat(food) && animal.getAmountEaten() <= food.getMyWeight()/*ensure that the food is added only if we have enough weight of it*/) {
                eatableFoodDropDownList.getItems().add(food.getMyName());
            }
        }
    }

    /**
     * Takes away the function to feed animals unless a food item is selected from the
     * eatableFoodDropDownList.
     */
    private void haveEatableFood() {
        var noFoodIsSelected = eatableFoodDropDownList.getSelectionModel().selectedItemProperty().isNull();
        feedButton.disableProperty().bind(noFoodIsSelected);
    }

    /**
     * Feeds the selected animal with the selected food and updates the food list as well as the
     * health of the animal.
     * Launches the current scene again.
     * @param currentPlayer the player whose turn it is.
     * @param animalsThatCanEat a list of animals that the player has food for.
     */
    private void feedAnimal(Player currentPlayer, ArrayList<Animal> animalsThatCanEat) {
        feedButton.setOnMouseClicked(event -> {
            int animalToFeedIndex = animalsThatCanEatDropDownList.getSelectionModel().getSelectedIndex();
            int foodToFeedAnimalIndex = eatableFoodDropDownList.getSelectionModel().getSelectedIndex();
            Animal animalToFeedObject = animalsThatCanEat.get(animalToFeedIndex);
            var foodForAnimal = findFoodForAnimal(currentPlayer, animalToFeedObject);
            Food foodToFeedAnimalObject = foodForAnimal.get(foodToFeedAnimalIndex);
            animalToFeedObject.eat(foodToFeedAnimalObject);
            feedAnimalScene();
            }
        );
    }

    /**
     * Provides a list of food that the selected animal can eat.
     * @param currentPlayer the player whose turn it is.
     * @param animal the animal that we want to mate.
     * @return a list of food that the selected animal can eat.
     */
    private ArrayList<Food> findFoodForAnimal(Player currentPlayer, Animal animal) {
        ArrayList<Food> eatableFood = new ArrayList<>();
        for (Food food : currentPlayer.getMyFood()) {
            if (animal.canEat(food) && animal.getAmountEaten() <= food.getMyWeight()) {
                eatableFood.add(food);
            }
        }

        return eatableFood;
    }

    /**
     * Opens the current scene again.
     */
    private void feedAnimalScene() {
        try {
            SceneCreator.launchScene("/scenes/FeedAnimalsScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the PlayerTurnMenuScene for the next player.
     * @throws Exception
     */
    public void openTurnScene() throws Exception {
        game.nextPlayer();
    }
}
