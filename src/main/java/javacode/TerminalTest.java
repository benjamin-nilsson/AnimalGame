package javacode;

/**
 * Code for testing parts of the program.
 */
public class TerminalTest {
    static void testPlayer(){
        Player player1 = new Player("Player One");
        System.out.println("Name: " + player1.getMyName());
        System.out.println("AnimalBucks: " + player1.getMyMoney());
    }
    static void testCow(Cow cow1){
        System.out.println("Name: " + cow1.getName());
        System.out.println("Species: " + cow1.getSpecies());
        System.out.println("Age: " + cow1.getAge());
        System.out.println("Health: " + cow1.getHealth());
        System.out.println("Base Value: " + cow1.getBasicValue());
        System.out.println("Actual Value: " + cow1.getValue());
        System.out.println("Max Age: " + cow1.getMaxAge());

    }

    static void testCows(){
        Cow cow1 = new Cow("Rosa", Gender.FEMALE);
        Cow cow2 = new Cow("Ferdinand", Gender.MALE);
        TerminalTest.testCow(cow1);
        if(cow1.canMateWith(cow2)){
            System.out.println(cow1.getName() + " and " + cow2 +" gets it on!");
        }

    }
}
