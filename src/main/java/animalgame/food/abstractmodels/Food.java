package animalgame.food.abstractmodels;

/**
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 * The food class stores the basic attributes of the animal foods.
 */

public abstract class Food {

    private String myName;
    private int myPrice, myWeight;


    public Food(String myName, int myPrice, int myWeight) {
        this.myName = myName;
        this.myPrice = myPrice;
        this.myWeight = myWeight;
    }

    public void setMyWeight(int myWeight) {
        this.myWeight = myWeight;
    }

    public String getMyName() {
        return myName;
    }

    public int getMyPrice() {
        return myPrice;
    }

    public int getMyWeight() {
        return myWeight;
    }

    /**
     * Returns the value for the amount and type of food in this object
     * @return
     */
    public int getValue() {
        return this.myPrice * this.myWeight;
    }
}

