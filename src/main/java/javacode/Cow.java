package javacode;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the cow.
 */

/**
 * Sets the max age of the cow.
 */

public class Cow extends Animal {
    private Animal Cow;
    private int maxAge = 20;

    public Cow (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }


    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public Animal getAnimalType() {
        return Cow;
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
