package model.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class  Player extends Observable {
    int score;
    String ip;
    int port;
    static int numofplayers;
    String name;

    Tile first_letter;
    List<Tile> hand;
    public Player(String ip,int port) {
        numofplayers++;
        this.name="player"+numofplayers;
        this.ip=ip;
        this.port=port;
        this.score = 0;
        hand=new ArrayList<>();
    }


    public void setScore(int score) {
        this.score = score;
    }
    
    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }
    public void UpdateScore(int s){
        score+=s;
    }
    public List<Tile> getHand() {
        return hand;
    }
    public void AddTileToHand(Tile tile){
        hand.add(tile);
    }
}
