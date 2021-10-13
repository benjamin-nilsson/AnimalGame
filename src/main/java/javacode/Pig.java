package javacode;

import java.util.ArrayList;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the pig.
 */

public class Pig extends Animal {
    private Player owner;
    private Gender gender;
    private String name, species;
    private int age, health, basicValue, maxAge, litterSize;
    private ArrayList<String> foods;

    /**
     * Constructor - calls the supers constructor and sets maxAge, basicValue , foods & litterSize according
     * to the values for the subclass.
     * @param name
     * @param gender
     */
    public Pig (String name, Gender gender) {
        super(name, "Pig", gender, 8, 10, 7, 5);
        foods = new ArrayList<>();
        this.foods.add("Corn and soybeans");
        this.foods.add("Mixed Grain");
    }

    /**
     * If a player attempts to have two animals mate there is 50% chance that up to litterSize new animals will
     * be added to the players animals.
     * @param animal
     */
    public boolean mateWith(Animal animal){
        boolean successful = false;
        if(this.canMateWith(animal) && Math.random()<0.50){
            int litter = (int) (this.litterSize * Math.random() + 1);
            for(int i = 0; i < litter; i++) {
                Gender newBornsGender = Gender.FEMALE;
                String nameEnding = "dottir";
                if(Math.random()<0.5) {
                    newBornsGender = Gender.MALE;
                    nameEnding = "sson";
                }
                this.owner.addAnimal(new Pig(this.name + nameEnding, newBornsGender));
                successful = true;
            }
        }

        return successful;
    }

}