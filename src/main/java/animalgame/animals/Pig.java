package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extens the Animal class and holds the characteristics of the pig.
 */

public class Pig extends Animal {

    /**
     * Constructor - calls the supers constructor and sets maxAge, basicValue , foods & litterSize according
     * to the values for the subclass.
     * @param name
     * @param gender
     */
    public Pig (String name, Gender gender) {
        super(name, "Pig", gender, 8, 200, 7, 5, "Corn and soybeans", "Mixed Grain");
    }

    /**
     * If a player attempts to have two animals mate there is 50% chance that up to litterSize new animals will
     * be added to the players animals. returns # of offspring.
     * @param animal
     */
    public int mateWith(Animal animal){
        int litter = 0;
        Gender newBornsGender;
        if(this.canMateWith(animal) && Math.random()<0.50) {
            litter = (int) (this.getLitterSize() * Math.random() + 1);
            for(int i = 0; i < litter; i++) {
                newBornsGender = Math.random()<0.5 ? Gender.FEMALE: Gender.MALE;
                this.getOwner().addAnimal(new Cow("Piglet", newBornsGender));
            }
        }
        return litter;
    }

}