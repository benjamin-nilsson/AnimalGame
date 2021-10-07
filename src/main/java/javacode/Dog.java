package javacode;

public class Dog extends Animal {
    private Object Dog;  // dog extends animal klass

    public Dog (String name, Gender gender) { // constructor stores name & gender
        super(name, gender);
    }

    @Override
    public Animal getAnimalType() {
        return (Animal) Dog;
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
        return 10;
    }

}
