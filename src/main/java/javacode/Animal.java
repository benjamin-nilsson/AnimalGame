package javacode;


enum Gender {
    MALE, FEMALE
}

public abstract class Animal {
    private String name;
    private int age;
    private int health;
    private Gender gender;
    private int hunger; //Health

    public Animal(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hunger = hunger;
        hunger = 0; //Sets the hunger to 0.
    }

    public void roundPassed(){
        hunger++; //Adds 1 hunger after every round.
    }

    private int getHunger(){
        return hunger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
