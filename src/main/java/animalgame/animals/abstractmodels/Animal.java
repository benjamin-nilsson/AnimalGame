package animalgame.animals.abstractmodels;

import animalgame.food.abstractmodels.Food;
import animalgame.game.Player;
import animalgame.enums.Gender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;




/**
 * This class declares the animal attributes.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public abstract class Animal implements Serializable {
    private Player owner;
    private Gender gender;
    private String name, species;
    private int age, health, basicValue, maxAge, litterSize, amountEaten;
    private ArrayList<String> foods;

    public Animal(String name, String species, Gender gender, int maxAge, int basicValue, int litterSize, int amountEaten, String ... args) {
        super();
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.health = 100;//Perfect health.
        this.age = 0; //Newborn
        this.foods = new ArrayList<>();
        this.foods.addAll(Arrays.asList(args));
        this.maxAge = maxAge;
        this.basicValue = basicValue;
        this.litterSize = litterSize;
        this.amountEaten = amountEaten;
    }

    /**
     * Method that uses the math.random function to randomize the age from a store bought animal, maxAge / 2.
     */
    public void ageFromStore(){
        this.age = (int) (1 + (Math.random() * this.getMaxAge() / 2));
    }

    /**
     * Animal eats amountEaten kg of the offered food and its healh
     * is increased by 10 (to a maximum of 100)
     * @param food
     */
    public void eat(Food food){
        food.setMyWeight(food.getMyWeight()-this.getAmountEaten());
        this.health = Math.min(this.health + 10, 100);
    }

    /**
     * Method that is called whenever a game turn ends
     * health drops by 10-30%, age increases by 1 Turn.
     * The animal may die.
     * method returns an update
     */
    public String endOfTurn() {
        String healthUpdate = this.name + " lost ";
        int healthLoss = (int) (20 * Math.random() + 11);
        this.health -= healthLoss;
        healthUpdate += healthLoss + ",and is now at " + this.health + " health.";
        if (this.health < 1) {
            healthUpdate = this.name + " died from starvation!";
        }
        this.age++;
        if(this.age > this.maxAge){
            this.health = 0;
            healthUpdate = this.name + " died from old age!";
        }
        return healthUpdate;
    }

    /**
     * checks if this animal can eat the food proposed.
     * @param food
     * @return boolean
     */
    public boolean canEat(Food food){
        return this.foods.contains(food.getMyName());
    }

    /**
     * When an animal dies, it is removed from its owners animals.
     */
    public void dies(){
        this.owner.removeAnimal(this);
    }

    public int getLitterSize() {
        return litterSize;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public String getName() {
        return name;
    }

    public int getAmountEaten() {
        return amountEaten;
    }

    public int getAge(){
        return this.age;
    }

    public int getMaxAge(){
        return this.maxAge;
    }

    public int getValue(){
        return (this.getBasicValue() * this.getHealth())/100;
    }

    public int getBasicValue(){
        return this.basicValue;
    }

    public int getHealth(){
        return this.health;
    }

    public String getSpecies(){
        return this.species;
    }

    public Player getOwner() {
        return owner;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean canMateWith(Animal animal){
        return animal.getSpecies().equals(this.species) && !(animal.getGender() == this.gender);
    }

    /**
     * Abstract methods that the subclass animals inherit.
     * @return the value of animal attributes.
     */

    public abstract int mateWith(Animal animal);


}
