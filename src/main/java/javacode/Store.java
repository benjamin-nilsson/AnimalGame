package javacode;

public class Store {
    /**
     * Adds the animal and removes the AnimalBucks for it to and from player.
     * @param player
     * @param animal
     */
    public void buyAnimal(Player player, Animal animal){
        player.addAnimal(animal);
        player.setMyMoney(player.getMyMoney() - animal.getValue());
    }

    /**
     * Adds the food to the player and removes the AnimalBucks for it.
     * @param player
     * @param food
     */
    public void buyFood(Player player, Food food){
        player.addFood(food);
        player.setMyMoney(player.getMyMoney() - food.getValue());
    }
}
