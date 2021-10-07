package javacode;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the dog.
 */

/**
 * Sets the max age of the dog.
 */

public class Dog extends Animal {
    private Animal Dog;  // dog extends animal klass
    private int maxAge = 10;

    public Dog (String name, Gender gender) { // constructor stores name & gender
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
        return 10;
    }

    @Override
    public Animal getAnimalType() {
        return Dog;
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
