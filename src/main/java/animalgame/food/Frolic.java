package animalgame.food;

import animalgame.food.abstractmodels.Food;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * This class extends the food class.
 */
public class Frolic extends Food {
    private String myName;
    private int myPrice, myWeight;

    public Frolic () {
        super("Frolic" , 4, 5);
    }

}
