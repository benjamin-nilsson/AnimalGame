package animalgame.game;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;

import java.util.ArrayList;

/**
 * The Store Class contains the static methods of buying and selling animals, and buying food
 * @author grupp1
 */
public class Store {

    /**
     * Adds the animal and removes the AnimalBucks for it to and from player.
     * @param player
     * @param animal
     */
    public static void buyAnimal(Player player, Animal animal){
        animal.ageFromStore();
        player.addAnimal(animal);
        player.setMyMoney(player.getMyMoney() - animal.getValue());
    }

    /**
     * Removes the animal from player, and adds AnimalBucks to player.
     * @param player
     * @param animal
     */
    public static void sellAnimal(Player player, Animal animal){
        player.removeAnimal(animal);
        player.setMyMoney(player.getMyMoney() + animal.getValue());
    }

    /**
     * removes all animals from a player, and adds AnimalBucks to player.
     * @param player
     */
    public static void sellAllAnimals(Player player){
        ArrayList<Animal> animals = player.getMyAnimals();
        for (Animal animal : animals) {
            sellAnimal(player, animal);
        }
    }

    /**
     * Adds the food to the player and removes the AnimalBucks for it.
     * @param player
     * @param food
     */
    public static void buyFood(Player player, Food food){
        player.addFood(food);
        player.setMyMoney(player.getMyMoney() - food.getValue());
    }
}
