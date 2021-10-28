package animalgame.game;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import java.util.ArrayList;

/**
 * The Store Class contains the static methods of buying and selling animals, and buying food
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class Store {

    /**
     * Adds the animal and removes the AnimalBucks for it to and from player.
     * @param player Player that buys the animal
     * @param animal Animal bough
     */
    public static void buyAnimal(Player player, Animal animal){
        animal.ageFromStore();
        player.addAnimal(animal);
        player.setMyMoney(player.getMyMoney() - animal.getValue());
    }

    /**
     * Removes the animal from player, and adds AnimalBucks to player.
     * @param player Player selling animal.
     * @param animal Animal sold.
     */
    public static void sellAnimal(Player player, Animal animal){
        player.removeAnimal(animal);
        player.setMyMoney(player.getMyMoney() + animal.getValue());
    }

    /**
     * removes all animals from a player, and adds AnimalBucks to player.
     * @param player Player to remove all animals from.
     */
    public static void sellAllAnimals(Player player){
        ArrayList<Animal> animals = player.getMyAnimals();
        for (Animal animal : animals)
            player.setMyMoney(player.getMyMoney() + animal.getValue());
            player.getMyAnimals().clear();
    }

    /**
     * Adds the food to the player and removes the AnimalBucks for it.
     * @param player Player buying food.
     * @param food Food bought.
     */
    public static void buyFood(Player player, Food food){
        player.addFood(food);
        player.setMyMoney(player.getMyMoney() - food.getValue());
    }
}
