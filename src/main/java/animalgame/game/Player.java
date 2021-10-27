package animalgame.game;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<Animal> myAnimals = new ArrayList<>();
    private ArrayList<Food> myFood = new ArrayList<>();
    private int myMoney;
    private String myName;

    public Player(String myName) {
        this.myMoney = 2000; //AnimalBucks
        this.myName = myName;
    }

    /**
     * Reports the players current status in the game, as a string to be displayed to the player to
     * allow the player make an informed decision about his move.
     * @return
     */
    public String reportStatus(){
        String statusReport = "You have: ";
        for (Food food:this.getMyFood()) {
            statusReport += food.getMyWeight() + " kg of " + food.getMyName();
            if(this.getMyFood().indexOf(food) < this.getMyFood().size()-1){
                statusReport += ", ";
            } else {
                statusReport += ".\n";
            }
        }

        if(this.getMyAnimals().size() > 0){
            statusReport += "The animals you own are:\n";
            for (Animal animal:this.getMyAnimals()) {
               statusReport +=  animal.getName() + ", a " + animal.getGender();
               statusReport += " " + animal.getSpecies() + " of age " + animal.getAge();
               statusReport += " with a health of " + animal.getHealth() + ".\n";
            }
        }

        return statusReport;
    }


    /**
     * returns all animals in a players collection that can and needs to eat.
     * @return
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

    public String reportStatusAnimals() {
        String statusReport = "The animals you own are:\n";
        if(this.getMyAnimals().size() > 0){
            for (Animal animal:this.getMyAnimals()) {
                statusReport +=  animal.getName() + ", a " + animal.getGender();
                statusReport += " " + animal.getSpecies() + " of age " + animal.getAge() + " with a health of " +
                       animal.getHealth() + " and a value of " + animal.getValue() + "AB.\n";
            }
        }

        return statusReport;
    }

    public void ageAnimals() {
        // change health of all animals animal.endOfTurn()

        ArrayList<Animal> deadAnimals = new ArrayList<>();
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
        for (Animal animal:this.getMyAnimals()) {
            if(animal.getHealth() < 1){
                this.removeAnimal(animal);
            }
        }
    }

    /**
     * Returns all animals in a players collection that can be mated.
     * @return
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
     * @param animal1
     * @return
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
     * @param food
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
     * @param animal
     */
    public void removeAnimal(Animal animal) {
        this.myAnimals.remove(animal);
    }

    public ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }

    public ArrayList<Food> getMyFood() {
        return myFood;
    }

    public int getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(int myMoney) {
        this.myMoney = myMoney;
    }

    public String getMyName() {
        return myName;
    }

    public void addAnimal(Animal animal) {
        animal.setOwner(this);
        this.myAnimals.add(animal);
    }

}
