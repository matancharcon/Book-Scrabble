package model.data;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
   int id;
   int score;
   List <Tile> hand;

    public Player(int id){
        hand = new ArrayList<>();
        score=0;
        this.id=id;
    }
    public void AddTile(Tile tile){
        hand.add(tile);
    }
    public boolean FullHand(){
        return hand.size()==7;
    }
    int GetScore(){
        return score;
    }
    void SetScore(int score){
        this.score=score;
    }
}
