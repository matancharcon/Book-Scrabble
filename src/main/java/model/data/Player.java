package model.data;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int score;
    int id;
    List<Tile> hand;
    public Player(int id) {
        this.id = id;
        this.score = 0;
        hand=new ArrayList<>();
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
