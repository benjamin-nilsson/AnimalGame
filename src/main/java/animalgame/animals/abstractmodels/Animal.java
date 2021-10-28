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
    private final Gender gender;
    private String name;
    private final String species;
    private int age;
    private int health;
    private final int basicValue;
    private final int maxAge;
    private final int litterSize;
    private final int amountEaten;
    private final ArrayList<String> foods;

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
     * @param food of Food class
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
     * @param food Food to check if this animal can eat.
     * @return boolean true if this animal can eat food.
     */
    public boolean canEat(Food food){
        return this.foods.contains(food.getMyName());
    }

    /**
     * Returns the maximum number of offspring a mating of two of this animal can have.
     * @return int litterSize
     */
    public int getLitterSize() {
        return litterSize;
    }

    /**
     * Return this animals name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of food this animal eats in each serving
     * Each serving is what is needed to increase its health with 10.
     * @return int amountEaten
     */
    public int getAmountEaten() {
        return amountEaten;
    }

    /**
     * Returns the animals age.
     * @return int age
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Returns the animals max age.
     * @return in maxAge
     */
    public int getMaxAge(){
        return this.maxAge;
    }

    /**
     * Returns the value of the animal at its current health.
     * @return in value
     */
    public int getValue(){
        return (this.getBasicValue() * this.getHealth())/100;
    }

    /**
     * Returns the animals basicValue
     * @return basicValue
     */
    public int getBasicValue(){
        return this.basicValue;
    }

    /**
     * Returns the animals current health
     * @return int health
     */
    public int getHealth(){
        return this.health;
    }

    /**
     * Return what species this animal is of
     * @return species
     */
    public String getSpecies(){
        return this.species;
    }

    /**
     * Returns the Player object that is the owner of this animal.
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Returns this animals gender.
     * @return gender
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets this animals name to name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets this animals owner.
     * @param owner of Player class
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Returns an ArrayList of all animals in its owners
     * @param animal of Animal class
     * @return ArrayList of Animal
     */
    public boolean canMateWith(Animal animal){
        if (this.health < 1) return false;
        if (animal.getHealth() < 0) return false;
        return animal.getSpecies().equals(this.species) && !(animal.getGender() == this.gender);
    }

    /**
     * Abstract methods that the subclass animals inherit.
     * @return the value of animal attributes.
     */
    public abstract int mateWith(Animal animal);


}
