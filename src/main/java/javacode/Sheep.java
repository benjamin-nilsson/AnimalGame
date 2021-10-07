package javacode;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the sheep.
 */

/**
 * Sets the max age of the sheep.
 */
public class Sheep extends Animal {
    private Animal Sheep;
    private int maxAge = 10;

    public Sheep (String name, Gender gender) {// constructor stores name & gender
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
        return 15;
    }

    @Override
    public Animal getAnimalType() {
        return Sheep;
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
