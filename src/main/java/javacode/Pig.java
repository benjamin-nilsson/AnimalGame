package javacode;

public class Pig extends Animal {


    private Object Pig;

    public Pig(String name, Gender gender) {
        super(name, gender);
    }

    @Override
    public Animal getAnimalType() {
        return (Animal) Pig;
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
        return 5;
    }
}
