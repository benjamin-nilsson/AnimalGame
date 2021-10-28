package animalgame.animals;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

/**
 * This class extens the Animal class and holds the characteristics of the dog.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class Dog extends Animal {

    /**
     * Constructor - calls the supers constructor and sets maxAge, basicValue, foods & litterSize according
     * to the values for the subclass.
     * @param name Name of dog
     * @param gender Gender of dog
     */
    public Dog (String name, Gender gender) {
        super(name, "Dog", gender, 10, 100, 6, 2, "Wellness Dry Dog Food", "Frolic");
    }

    /**
     * If a player attempts to have two animals mate there is 50% chance that up to litterSize new animals will
     * be added to the players animals. returns # of offspring.
     * @param animal animal to mate with
     */
    public int mateWith(Animal animal){
        int litter = 0;
        Gender newBornsGender;
        if(this.canMateWith(animal) && Math.random()<0.50) {
            litter = (int) (this.getLitterSize() * Math.random() + 1);
            for(int i = 0; i < litter; i++) {
                newBornsGender = Math.random()<0.5 ? Gender.FEMALE: Gender.MALE;
                this.getOwner().addAnimal(new Dog("Pup", newBornsGender));
            }
        }
        return litter;
    }
}