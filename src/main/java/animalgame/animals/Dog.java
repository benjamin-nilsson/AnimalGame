package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the dog.
 */

public class Dog extends Animal {

    /**
     * Constructor - calls the supers constructor and sets maxAge, basicValue, foods & litterSize according
     * to the values for the subclass.
     * @param name
     * @param gender
     */
    public Dog (String name, Gender gender) {
        super(name, "Dog", gender, 10, 5, 6, 2, "Wellness Dry Dog Food", "Frolic");
    }

    /**
     * If a player attempts to have two animals mate there is 50% chance that up to litterSize new animals will
     * be added to the players animals.
     * @param animal
     */
    public boolean mateWith(Animal animal){
        boolean successful = false;
        if(this.canMateWith(animal) && Math.random()<0.50){
            int litter = (int) (this.getLitterSize() * Math.random() + 1);
            for(int i = 0; i < litter; i++) {
                Gender newBornsGender = Gender.FEMALE;
                String nameEnding = "dottir";
                if(Math.random()<0.5) {
                    newBornsGender = Gender.MALE;
                    nameEnding = "sson";
                }
                this.getOwner().addAnimal(new Dog(this.getName() + nameEnding, newBornsGender));
                successful = true;
            }
        }

        return successful;
    }
}