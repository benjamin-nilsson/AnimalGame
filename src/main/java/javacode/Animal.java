package javacode;

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
    private Gender gender;
    private String name;
    private int age;
    private int hunger; //Health
    private Animal animalType;
    private int ageFromStore;
    private int maxAge;


    /**
     * Animal constructor that forwards the name and gender of the animals.
     * Also sets the base hunger and age to 0.
     * @param name of the animal.
     * @param gender of the animal.
     */
    public Animal(String name, Gender gender) {
        super();
        this.name = name;
        this.gender = gender;
        this.hunger = 0; //Sets the hunger to 0 = 100 health.
        this.age = 0; //Sets the start age to 0.
    }

    /**
     * Method gives us access to set the max age of the animal.
     * @return the max age.
     */
    public int getMaxAge(){
        return this.maxAge;
    }

    /**
     * Method that uses the math.random function to randomize the age from a store bought animal, maxAge / 2.
     */
    public void ageFromStore(){
        this.age = (int) (1 + (Math.random() * this.getMaxAge() / 2));
    }

    public int getAge(){
        return this.age;
    }

/*    public void turnPassed() {
        hunger++; //Adds 1 hunger after every round.
        age++; //Adds 1 age after every round.
    }

    public double mateWith(Animal animal) {
        if (!animal.getAnimalType().equals(animal.getAnimalType()))  //Animals have different types, no chance.
            return 0;
        if (animal.getGender().equals(animal.getGender()))  //Animals have the same gender, no chance.
            return 0;
        if (!animal.getGender().equals(animal.getGender()))  //Animals of different gender have 50% chance of breeding.
            return 0.5;
    }

    public void feedAnimal (Animal animal, Food food) {
        if (food.getFavoriteFood().equals(food.getMyName())) {
            animal.setHunger(animal.getHunger() -1);
        }
    }*/

    /**
     * Abstract methods that the subclass animals inherit.
     * @return the value of animal attributes.
     */
    public abstract int getAge(int age);

    public abstract int getValue();

    public abstract int getPrice();

    public abstract Animal getAnimalType();

    public abstract int getHunger(int hunger);
}
