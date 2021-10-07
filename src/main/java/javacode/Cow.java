package javacode;

public class Cow extends Animal {

    public Cow (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }
    @Override
    public int getValue() {
        return 0;
    }
}
