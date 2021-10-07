package javacode;

public class Store {
    public void buyAnimal(Player player, Animal animal){

        player.addAnimal(animal);
        player.setMyMoney(player.getMyMoney() - animal.getValue());
    }

}
