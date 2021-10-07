package javacode;

public class Cow extends Animal {
    private Object Cow;

    public Cow (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }

    @Override
    public Animal getAnimalType() {
        return (Animal) Cow;
    }

    @Override
    public int setHunger(int hunger) {
        return 0;
    }

    @Override
    public String setName(String name) {
        return null;  //Set later?
    }

    @Override
    public int setAge(int age) {
        return 0;
    }

    @Override
    public Gender setGender() {
        return null;  //Set later?
    }

    @Override
    public int setValue() {
        return 20;  //Money?
    }

}
