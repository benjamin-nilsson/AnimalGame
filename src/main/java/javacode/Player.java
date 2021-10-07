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

    public ArrayList<Animal> getMyAnimals() {
        return myAnimals;
    }

    public void setMyAnimals(ArrayList<Animal> myAnimals) {
        this.myAnimals = myAnimals;
    }

    public ArrayList<Food> getMyFood() {
        return myFood;
    }

    public void setMyFood(ArrayList<Food> myFood) {
        this.myFood = myFood;
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

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void addAnimal(Animal animal) {
        this.myAnimals.add(animal);
    }

    public void addFood(Food food) {
        //Om samma typ av mat redan finns i myFood, öka mängden i stället för att lägga till ett nytt element
        String foodName = food.getMyName();
        for (Food foodInList:this.myFood) {
            if(foodName.equals(foodInList.getMyName())){
                foodInList.setMyWeight(foodInList.getMyWeight() + food.getMyWeight());
            }
        }
    }
}
