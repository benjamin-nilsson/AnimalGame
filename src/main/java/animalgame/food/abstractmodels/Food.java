package animalgame.food.abstractmodels;

import java.io.Serializable;

/**
 * The food class stores the basic attributes of the animal foods.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public abstract class Food implements Serializable {

    private final String myName;
    private final int myPrice;
    private int myWeight;


    public Food(String myName, int myPrice, int myWeight) {
        this.myName = myName;
        this.myPrice = myPrice;
        this.myWeight = myWeight;
    }

    /**
     * The weight of the food created.
     * @param myWeight the weight of the food.
     */
    public void setMyWeight(int myWeight) {
        this.myWeight = myWeight;
    }

    /**
     * @return the name of the food.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * @return the weight of the food.
     */
    public int getMyWeight() {
        return myWeight;
    }

    /**
     * Returns the value for the amount and type of food in this object
     * @return int myPrice * myWeight
     */
    public int getValue() {
        return this.myPrice * this.myWeight;
    }
}

