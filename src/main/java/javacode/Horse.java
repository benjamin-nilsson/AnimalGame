package javacode;

public class Horse extends Animal {

    private Object Horse;

    public Horse (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }

    @Override
    public Animal getAnimalType() {
        return (Animal) Horse;
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
        return 25;
    }

}
