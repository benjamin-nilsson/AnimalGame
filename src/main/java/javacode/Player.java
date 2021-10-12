package javacode;

import java.util.ArrayList;

public class Player {
    private ArrayList<Animal> myAnimals = new ArrayList<>();
    private ArrayList<Food> myFood = new ArrayList<>();
    private int myMoney;
    private String myName;

    public Player(String myName) {
        this.myMoney = 100; //AnimalBucks
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
               statusReport += " " + animal.getSpecies() + " of age " + animal.getAge() + ".\n";
            }
        }
        return statusReport;
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
