package javacode;

public class Dog extends Animal {  // dog extends animal klass

    public Dog (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }
    @Override
    public int getValue() {
        return 0;
    }
}
