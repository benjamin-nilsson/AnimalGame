package javacode;

import javafx.stage.Stage;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> myPlayerList = new ArrayList<Player>();
    private int turns;
    private int maxTurns;


    public Game() {
    }

    public void addPlayer(Player player){
        myPlayerList.add(player);
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
