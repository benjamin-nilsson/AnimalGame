package javacode;

public class Horse extends Animal {

    public Horse (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }

    @Override
    public int getValue() {
        return 0;
    }
}
