package javacode;


enum Gender {
    MALE, FEMALE
}

public abstract class Animal {
    private String name;
    private int age;
    private Gender gender;
    private int hunger; //Health
    private Animal animalType;
    private String favoriteFood;


    public Animal(String name, Gender gender) {
        super();
        this.name = name;
        this.gender = gender;
        this.hunger = 0; //Sets the hunger to 0 = 100 health.
        this.age = 0; //Sets the start age to 0.
    }

    public void roundPassed() {
        hunger++; //Adds 1 hunger after every round.
        age++; //Adds 1 age after every round.
    }


    public double mateWith(Animal animal) {

        if (!animal.getClass().equals(animal.getClass()))   //Animals are of different kind, no chance.
            return 0;

        if (!animal.getAnimalType().equals(animal.getAnimalType()))  //Animals have different types, no chance.
            return 0;

        if (animal.getGender().equals(animal.getGender()))  //Animals have the same gender, no chance.
            return 0;

        if (!animal.getGender().equals(animal.getGender()))  //Animals of different gender have 50% chance of breeding.
            return 0.5;
    }


    public void feedAnimal (Animal animal, Food food) {
        if (animal.getFavoriteFood().equals(food.getMyName())) {
            animal.setHunger(animal.getHunger() + -1);
        }
    }

    private Animal getAnimalType() {
        return animalType;
    }


    public String getFavoriteFood(){
        return favoriteFood;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    private int getHunger(){
        return hunger;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public abstract int getValue();
}
