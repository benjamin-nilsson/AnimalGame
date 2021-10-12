package javacode;

import java.util.ArrayList;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class declares the animal attributes and Enum Genders.
 */

enum Gender {
    MALE, FEMALE
}

/**
 * Field variables for different animal attributes.
 */
public abstract class Animal {
    private Player owner;
    private Gender gender;
    private String name, species;
    private int age, health, basicValue, maxAge, litterSize;
    private ArrayList<String> foods;


    public Animal(String name, String species, Gender gender, int maxAge, int basicValue, int litterSize) {
        super();
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.health = 100;//Perfect health.
        this.age = 0; //Newborn
        this.foods = new ArrayList<>();
        this.maxAge = maxAge;
        this.basicValue = basicValue;
        this.litterSize = litterSize;
    }

    /**
     * Method that uses the math.random function to randomize the age from a store bought animal, maxAge / 2.
     */
    public void ageFromStore(){
        this.age = (int) (1 + (Math.random() * this.getMaxAge() / 2));
    }

    /**
     * Method that is called whenever a game turn ends
     * health drops by 10-30%, age increases by 1 Turn.
     * The animal may die.
     */
    public void endOfTurn() {
        this.health -= (int) (20 * Math.random() + 11);
        this.age++;
        if(this.age > this.maxAge){
            this.dies();
        }
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

    public String getName() {
        return name;
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

    public abstract boolean mateWith(Animal animal);


}
