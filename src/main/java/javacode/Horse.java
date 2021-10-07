package javacode;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the horse.
 */

/**
 * Sets the max age of the horse.
 */

public class Horse extends Animal {
    private Animal Horse;
    private int maxAge = 25;

    public Horse (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }

    @Override
    public int getAge(int age) {
        return 0;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 25;
    }

    @Override
    public Animal getAnimalType() {
        return Horse;
    }

    @Override
    public int getHunger(int hunger) {
        return 0;
    }
    @Override
    public int getMaxAge() {
        return maxAge;
    }
}
