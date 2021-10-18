package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.animals.Cow;
import animalgame.animals.Pig;
import animalgame.enums.Gender;
import animalgame.game.Player;

/**
 * Code for testing parts of the program.
 */
public class TerminalTest {
    public static void main(String[] args) {
    System.out.println("TerminalTest is running:");
    TerminalTest.testMating();
    //Player player = new Player("Fredrik");
    //testAllAnimals();
    //testAnimal();

    }
/*
    static void testAllAnimals(){
        cow1 = new Cow("Bella", Gender.FEMALE);
        cow2 = new Cow("Ferdinand", Gender.MALE);

    }*/
    static void testMating(){
        Player player1 = new Player("Player One");
        System.out.println("Name: " + player1.getMyName());
        System.out.println("AnimalBucks: " + player1.getMyMoney());
        player1.addAnimal(new Pig("Piglet", Gender.MALE));
        player1.addAnimal((new Pig("Miss Piggy", Gender.FEMALE)));
        if(player1.getMyAnimals().get(0).mateWith(player1.getMyAnimals().get(1))){
            System.out.println("Mating successful, you now have the following animals:");
            for (Animal animal: player1.getMyAnimals()) {
                String stringOut = animal.getSpecies() + ": ";
                stringOut += animal.getName();
                System.out.println(stringOut);
            }
        } else {
            System.out.println("Miss Piggy has a headache!");
        }
    }


    static void testAnimal(Animal animal){
        Cow cow1 = new Cow("Bella", Gender.FEMALE);
        System.out.println("Name: " + animal.getName());
        System.out.println("Species: " + animal.getSpecies());
        System.out.println("Age: " + animal.getAge());
        System.out.println("Health: " + animal.getHealth());
        System.out.println("Base Value: " + animal.getBasicValue());
        System.out.println("Actual Value: " + animal.getValue());
        System.out.println("Max Age: " + animal.getMaxAge());
        animal.ageFromStore();
        System.out.println("Age: " + animal.getAge());

    }

   /* static void testCows(){
        Cow cow1 = new Cow("Rosa", Gender.FEMALE);
        Cow cow2 = new Cow("Ferdinand", Gender.MALE);
        TerminalTest.testCow();
        if(cow1.canMateWith(cow2)){
            System.out.println(cow1.getName() + " and " + cow2.getName() +" gets it on!");
        }

    }*/
}
