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
}
