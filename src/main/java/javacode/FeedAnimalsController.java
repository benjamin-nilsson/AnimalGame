package javacode;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FeedAnimalsController implements Initializable {
    @FXML
    private ComboBox<String> animalsThatCanEatDropDownList, eatableFoodDropDownList;

    @FXML
    private Button findFoodButton, feedButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player currentPlayer = Game.getCurrentPlayer();
        ArrayList<Animal> animalsList = currentPlayer.canEat();
        for (Animal animal : animalsList) {
            animalsThatCanEatDropDownList.getItems().add(animal.getName());
        }

        BooleanBinding noSelectedAnimal = animalsThatCanEatDropDownList.getSelectionModel().selectedItemProperty().isNull();
        findFoodButton.disableProperty().bind(noSelectedAnimal);

        findFoodButton.setOnMouseClicked(event -> {
                    int selectedAnimal = animalsThatCanEatDropDownList.getSelectionModel().getSelectedIndex();
                    findFood(currentPlayer, animalsList.get(selectedAnimal));
                }
        );

        var noFoodIsSelected = eatableFoodDropDownList.getSelectionModel().selectedItemProperty().isNull();
        feedButton.disableProperty().bind(noFoodIsSelected);

        feedButton.setOnMouseClicked(event -> {
                    int animalToFeedIndex = animalsThatCanEatDropDownList.getSelectionModel().getSelectedIndex();
                    int foodToFeedAnimalIndex = eatableFoodDropDownList.getSelectionModel().getSelectedIndex();
                    Animal animalToFeedObject = animalsList.get(animalToFeedIndex);
                    var foodForAnimal = findFoodForAnimal(currentPlayer, animalToFeedObject);
                    Food foodToFeedAnimalObject = foodForAnimal.get(foodToFeedAnimalIndex);
                    animalToFeedObject.eat(foodToFeedAnimalObject);
                    feedAnimal();
                }
        );
    }

    private void feedAnimal() {
        try {
            SceneCreator.launchScene("/scenes/FeedAnimalsScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findFood(Player currentPlayer, Animal animal) {
        eatableFoodDropDownList.getItems().clear();

        for (Food food : currentPlayer.getMyFood()) {
            if (animal.canEat(food) && animal.getAmountEaten() <= food.getMyWeight()/*ensure that the food is added only if we have enough weight of it*/) {
                eatableFoodDropDownList.getItems().add(food.getMyName());
            }
        }
    }

    private ArrayList<Food> findFoodForAnimal(Player currentPlayer, Animal animal) {
        ArrayList<Food> eatableFood = new ArrayList<>();
        for (Food food : currentPlayer.getMyFood()) {
            if (animal.canEat(food) && animal.getAmountEaten() <= food.getMyWeight()) {
                eatableFood.add(food);
            }
        }

        return eatableFood;
    }

    public void openStoreAfterMoveScene(ActionEvent actionEvent) {

    }

    // todo: see if we should have it on everyone of the menus? Maybe make it as static in main?
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
