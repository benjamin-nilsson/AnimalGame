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
     * Add food to player. If the same type of food already is in myFood, just add upp the weights.
     * @param food
     */
    public void addFood(Food food) {
        //Om samma typ av mat redan finns i myFood, öka mängden i stället för att lägga till ett nytt element
        String foodName = food.getMyName();
        for (Food foodInList:this.myFood) {
            if(foodName.equals(foodInList.getMyName())){
                foodInList.setMyWeight(foodInList.getMyWeight() + food.getMyWeight());
            }
        }
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
