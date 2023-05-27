package model.data;
import java.io.*;
import java.net.*;

import model.logic.DictionaryManager;

import java.util.*;

public class HostModel extends Player implements Facade{
    HashMap<String,Player> Current_Players;
    Queue<Player> turns;
    DictionaryManager dm;
    Board board;
    Tile.Bag bag;
    ServerSocket serverSocket;
    boolean stop;
    ServerSocket server;
    HostClientHandler hc;
    int Guest_Count;



    public HostModel(Player player,String ip,int port,HostClientHandler hc) {
        super(ip, port);
        this.hc=hc;
        turns=new PriorityQueue<>((p1,p2)->p1.first_letter.letter-p2.first_letter.letter);
        Current_Players.put(player.name,player);
        Guest_Count=0;

    }
    public void hostGame(){
        stop=false;
        start();
    }


    public void start() {
        new Thread(() -> {
            try {
                startServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startServer() throws Exception {
        try{
            server = new ServerSocket(8080);
            server.setSoTimeout(1000);
            while (!stop) {
                try {
                    if(Guest_Count<4) {
                        Socket aClient = server.accept();//Blocking call
                        hc.handleClient(aClient.getInputStream(), aClient.getOutputStream(), this);
                        aClient.close();
                        Guest_Count++;
                    }
                } catch (SocketTimeoutException s) {}
            }
            server.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    public void addplayer(Player guest_player){
        Current_Players.put(guest_player.name,guest_player);
    }



    @Override
    public int TryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {

        Tile[] _word=new Tile[_tiles.length-1];
        for(int i=0;i< _tiles.length;i++)
            for (Tile tile:Current_Players.get(name).hand)
                if(tile.letter==_tiles[i]) {
                    _word[i] = tile;
                    tile=null;
                }

        int result=board.tryPlaceWord(new Word(_word,row,col,vertical));
        if (result>0) {
            setChanged();
            notifyObservers();
        }
        return result;
    }

    public int Challenge(String name, int row, int col, boolean vertical, char[] _tiles){
        return 0;
    }
    public void PassTurn() {
        turns.offer(turns.remove());
//        countTurns++;
//        if(countTurns==20*players.size())
//            endGame();

    }
    @Override
    public void StartGame() {
        Tile t;

        for(String s:Current_Players.keySet()){
            Current_Players.get(s).first_letter=Tile.Bag.getBag().getRand();
            turns.add(Current_Players.get(s));
        }
        for(Player p:turns){
            p.hand.addAll(Get_Tiles());
        }
    }

    public void EndGame() {



    }

    public List<Tile> Get_Tiles() {
        List<Tile> tiles=new ArrayList<>();
        for (int i = 0; i < 7; i++)
            tiles.add(Tile.Bag.getBag().getRand());
        return tiles;
    }









}