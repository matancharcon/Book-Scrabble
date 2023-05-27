package Model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public  class Player {
    int score;
    String ip;
    int port;
    static int numofplayers=0;
    String name;
    Tile first_letter;
    List<Tile> hand;
    public Player() {
        numofplayers++;
        this.name="player"+numofplayers;
        this.score = 0;
        hand=new ArrayList<>();
        ip= String.valueOf(0);
        port=0;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void SetHand(List<Tile> hand) {
        this.hand = hand;
    }
    public int GetScore() {
        return score;
    }
    public void UpdateScore(int s){
        score+=s;
    }
    public void AddTileToHand(Tile tile){
        hand.add(tile);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Player GetPlayer(){return this;}
}
