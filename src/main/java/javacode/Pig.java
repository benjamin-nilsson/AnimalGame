package javacode;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the pig.
 */

/**
 * Sets the max age of the pig.
 */

public class Pig extends Animal {
    private Animal Pig;
    private int maxAge = 8;

    public Pig(String name, Gender gender) {
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
        return 5;
    }

    @Override
    public Animal getAnimalType() {
        return Pig;
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
