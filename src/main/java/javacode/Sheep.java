package javacode;

public class Sheep extends Animal {

    private Object Sheep;

    public Sheep (String name, Gender gender) {// constructor stores name & gender
        super(name, gender);
    }

    @Override
    public Animal getAnimalType() {
        return (Animal) Sheep;
    }

    @Override
    public int setHunger(int hunger) {
        return 0;
    }

    @Override
    public String setName(String name) {
        return null;
    }

    @Override
    public int setAge(int age) {
        return 0;
    }

    @Override
    public Gender setGender() {
        return null;
    }

    @Override
    public int setValue() {
        return 15;
    }

}
