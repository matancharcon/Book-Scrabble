package Model.Data;
import java.io.*;
import java.net.*;

import Model.Logic.DictionaryManager;

import java.util.*;

public class HostModel extends Player implements Facade{
    HashMap<String,Player> players;
    LinkedList<Player> turns;
    Board board;
    Tile.Bag bag;
    static boolean stop;
    ServerSocket server;
    HostClientHandler hc;
    int Guest_Count;

    public HostModel(String ip,int port,HostClientHandler hc) {
        this.hc=hc;
        turns= new LinkedList<>();
        players =new HashMap<>();
        players.put(this.getName(),this);
        Guest_Count=0;
        setIp(ip);
        setPort(port);
        hostGame();
    }
    public void hostGame(){
        stop=false;
        start();
    }
    public void start() {
        new Thread(() -> {
            try {
                startServer();
            } catch (Exception e) {e.printStackTrace();}
        }).start();
    }
    static void close(){
        stop=true;
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
    public List<Tile> Get_Tiles() {
        List<Tile> tiles=new ArrayList<>();
        for (int i = 0; i < 7; i++)
            tiles.add(Tile.Bag.getBag().getRand());
        return tiles;
    }
    @Override
    public int tryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {
        Tile[] _word=new Tile[_tiles.length-1];
        for(int i=0;i< _tiles.length;i++)
            for (Tile tile:players.get(name).hand)
                if(tile.letter==_tiles[i]) {
                    _word[i] = tile;
                }
        int result=board.tryPlaceWord(new Word(_word,row,col,vertical));
        if(result>0) {
            players.get(name).UpdateScore(result);
            for(int i=0;i< _word.length;i++){
                players.get(name).hand.remove(_word[i]);
                players.get(name).AddTileToHand(bag.getRand());
            }

        }
        return result;
    }
    @Override
    public int Challenge(String name, int row, int col, boolean vertical, char[] _tiles){
        int result=0;
        List<Character> temp=new ArrayList<>();
        String word="";
        for (char c:_tiles) {
            temp.add(c);
            word+=c;
        }
        for(Tile t:players.get(name).hand)
            if(temp.contains(t.letter))
                temp.remove(t.letter);

        if(temp.size()==0){
            try {
                Socket socket=new Socket("localhost",8080);
                PrintWriter out=new PrintWriter(socket.getOutputStream());
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("C,alice_in_wonderland.txt,Frank Herbert - Dune.txt,Harray Potter.txt,mobydick.txt,pg10.txt,shakespeare.txt,The Matrix.txt"+word);
                if(in.readLine().equals("True")) {
                    result+=5;
                   players.get(name).UpdateScore(result);
                   tryPlaceWord(name,col,row,vertical,_tiles);
                }
                else {
                    result-=5;
                    players.get(name).UpdateScore(result);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            result=-1;
        return result;
    }
    @Override
    public void PassTurn() {
        turns.offer(turns.remove());
    }
    @Override
    public void StartGame() {

        Queue<Player> order_players=new PriorityQueue<>((p1,p2)->p1.first_letter.letter-p2.first_letter.letter);
        for(String s:players.keySet()){
            players.get(s).first_letter=Tile.Bag.getBag().getRand();
           order_players.add(players.get(s));
        }
      while (!order_players.isEmpty())
            turns.addLast(order_players.poll());

        for(Player p:turns){
            p.hand.addAll(Get_Tiles());
        }

    }
    @Override
    public void EndGame() {
        close();
    }
    @Override
    public Player GetWinner() {
        int winscore=0;
        String winname="";
        for (String p: players.keySet())
            if(players.get(p).GetScore()>winscore){
                winscore=players.get(p).GetScore();
                winname=p;
            }
        return players.get(winname);
    }
    @Override
    public int GetScore(String name) {
        return players.get(name).GetScore();
    }
    @Override
    public List<Tile> GetHand(String name) {
        return players.get(name).hand;
    }
    @Override
    public Tile GetFirstLetter(String name) {
        return players.get(name).first_letter;
    }
}