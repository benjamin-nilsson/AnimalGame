package javacode;

public class Sheep extends Animal {

    public Sheep (String name, Gender gender) {// constructor stores name & gender
        super(name, gender);
    }

    @Override
    public int getValue() {
        return 0;
    }
}
