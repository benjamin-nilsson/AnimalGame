package javacode;

import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> myPlayerList = new ArrayList<Player>();  //arraylist for players
    private int turns; // tracks each turn
    private int maxTurns; // declare maximum turns here ? 5-30 turns


    public Game() {  // remove ?
    }

    public void addPlayer(Player player){  // adds each player into the arraylist
        this.myPlayerList.add(player);
    }

    public void newTurn(int turns){

    }

    public void playerTurn(Player player){

    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void saveGame(){
    }

    public void loadGame(){

    }


}
