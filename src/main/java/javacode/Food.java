package javacode;

public abstract class Food {
    private String myName;
    private String favoriteFood;
    private int myValue, myWeight;

    public abstract String getMyName();

    public abstract void setMyName(String myName);

    public abstract int getMyValue();

    public abstract void setMyValue(int myValue);

    public abstract int getMyWeight();

    public abstract void setMyWeight(int myWeight);

    public abstract void setFavoriteFood();

    public abstract void getFavoriteFood();
    }
