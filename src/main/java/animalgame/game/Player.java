package animalgame.game;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Keeps track of all information regarding a player such as players animals, food, money, and name
 * as well as holds the methods for implementing changes to them.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class Player implements Serializable {
    private final ArrayList<Animal> myAnimals = new ArrayList<>();
    private final ArrayList<Food> myFood = new ArrayList<>();
    private int myMoney;
    private final String myName;

    public Player(String myName) {
        this.myMoney = 2000; //AnimalBucks
        this.myName = myName;
    }

    /**
     * Reports the players current status in the game, as a string to be displayed to the player to
     * allow the player make an informed decision about his move.
     * @return String statusReport
     */
    public String reportStatus(){
        StringBuilder statusReport = new StringBuilder();
        if (!this.myFood.isEmpty()) {
            statusReport.append("You have: \n");
            for (Food food:this.getMyFood()) {
                statusReport.append(food.getMyWeight()).append(" kg of ").append(food.getMyName() + "\n");
            /*    if(this.getMyFood().indexOf(food) < this.getMyFood().size()-1){
                    statusReport.append(", ");
                } else {
                    statusReport.append(".\n");
                }*/
            }
        }

        if(this.getMyAnimals().size() > 0){
            statusReport.append("The animals you own are:\n");
            for (Animal animal:this.getMyAnimals()) {
               statusReport.append(animal.getName() + ", a " + animal.getGender() +
               " " + animal.getSpecies() + " of age " + animal.getAge() +
               " with a health of " + animal.getHealth() + ".\n");
            }
        }

        return statusReport.toString();
    }

    /**
     * returns all animals in a players collection that can and needs to eat.
     * @return ArrayList of Animal
     */
    public ArrayList<Animal> canEat() {
        ArrayList<Animal> returnList= new ArrayList<>();
        for (Animal animal:this.myAnimals) {
            for (Food food:this.myFood) {
                boolean test = animal.canEat(food);
                test &= animal.getAmountEaten() <= food.getMyWeight();
                test &= animal.getHealth() < 100;
                test &= !returnList.contains(animal);
                if (test) {
                    returnList.add(animal);
                }
            }
        }

        return returnList;
    }

    /**
     * Returns a string with the current status of all animals in players collection
     * @return String statusReport
     */
    public String reportStatusAnimals() {
        StringBuilder statusReport = new StringBuilder();
        statusReport.append("The animals you own are:\n");
        if(this.getMyAnimals().size() > 0){
            for (Animal animal:this.getMyAnimals()) {
                statusReport.append(animal.getName() + ", a " + animal.getGender());
                statusReport.append(" " + animal.getSpecies() + " of age " + animal.getAge() + " with a health of " +
                        animal.getHealth() + " and a value of " + animal.getValue() + "AB.\n");
            }
        }
        return statusReport.toString();
    }

    /**
     * Ages all animals in players collection.
     */
    public void ageAnimals() {
        // change health of all animals animal.endOfTurn()

        StringBuilder healthUpdate = new StringBuilder();
        for (Animal animal : this.getMyAnimals()) {
            healthUpdate.append(animal.endOfTurn());
            if (this.getMyAnimals().indexOf(animal)  < (this.getMyAnimals().size() + 1)) {
                healthUpdate.append("\n");
            }
        }
        // Pop-up to inform about the animals health update.

        if (!this.getMyAnimals().isEmpty()) {
            Alert healthUpdateAlert = new Alert(Alert.AlertType.NONE, "", ButtonType.OK);
            healthUpdateAlert.setContentText(healthUpdate.toString());
            healthUpdateAlert.showAndWait();
            if (healthUpdateAlert.getResult() == ButtonType.OK) {
                healthUpdateAlert.close();
            }
            this.removeDeadAnimals();
        }
    }

    /**
     * Function that cleans dead animals from myAnimals
     */
    private void removeDeadAnimals() {
        ArrayList<Animal> deadAnimals = new ArrayList<>();
        for (Animal animal:this.getMyAnimals()) {
            if(animal.getHealth() < 1){
                deadAnimals.add(animal);
            }
        }
        for (Animal animal :
                deadAnimals) {
            this.removeAnimal(animal);
        }
    }

    /**
     * Returns all animals in a players collection that can be mated.
     * @return ArrayList of Animal
     */
    public ArrayList<Animal> canMate(){
        ArrayList<Animal> returnList = new ArrayList<>();
        for (Animal animal1:this.myAnimals) {
            for (Animal animal2:this.myAnimals) {
                if(animal1.canMateWith(animal2) && !returnList.contains(animal1)){
                    returnList.add(animal1);
                }
            }
        }
        return returnList;
    }

    /**
     * returns all animals in a players collection that can mate with animal1
     * @param animal1 Animal to find mates for
     * @return ArrayList of Animal that can bat with animal1
     */
    public ArrayList<Animal> willMateWith(Animal animal1){
        ArrayList<Animal> returnList = new ArrayList<>();
        for (Animal animal2:this.myAnimals) {
                if(animal1.canMateWith(animal2)){
                    returnList.add(animal2);
                }
            }
        return returnList;
    }

    /**
     * Add food to player. If the same type of food already is in myFood, just add upp the weights.
     * @param food the food object to add to myFood
     */
    public void addFood(Food food) {
        String foodName = food.getMyName();
        for (Food foodInList:this.myFood) {
            if(foodName.equals(foodInList.getMyName())){
                foodInList.setMyWeight(foodInList.getMyWeight() + food.getMyWeight());
                return;
            }
        }
        this.myFood.add(food);
    }

    /**
     * removes animal from myAnimals
     * @param animal to remove
     */
    public void removeAnimal(Animal animal) {
        this.myAnimals.remove(animal);
    }

    /**
     * return ArrayList myAnimals
     * @return all animals this player owns
     */
    public ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }

    /**
     * Return ArrayList myFood
     * @return all food this player owns
     */
    public ArrayList<Food> getMyFood() {
        return myFood;
    }

    /**
     * Return int myMoney
     * @return amount of money this player has
     */
    public int getMyMoney() {
        return myMoney;
    }

    /**
     * sets int myMoney
     * @param myMoney the amount of money
     */
    public void setMyMoney(int myMoney) {
        this.myMoney = myMoney;
    }

    /**
     * returns String myName
     * @return the players name
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Adds animal to myAnimals
     * @param animal to add
     */
    public void addAnimal(Animal animal) {
        animal.setOwner(this);
        this.myAnimals.add(animal);
    }

}
