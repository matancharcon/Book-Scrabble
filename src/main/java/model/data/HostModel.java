package model.data;
import java.io.*;
import java.net.*;

import javafx.beans.Observable;
import model.logic.DictionaryManager;

import java.util.HashMap;

public class HostModel  extends PlayerModel{
    HashMap<Integer,Player> Current_Players;
    DictionaryManager dm;
    Board board;
    Tile.Bag bag;
    ServerSocket serverSocket;
    boolean stop;
    ServerSocket server;
    HostClientHandler hc;

    int currentGuestPlayres;



    public HostModel(Player player,String ip,int port,HostClientHandler hc) {
        super(player,ip,port);
        currentGuestPlayres=0;
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
                    Socket aClient = server.accept();//Blocking call
                    hc.handleClient(aClient.getInputStream(), aClient.getOutputStream());

                    aClient.close();
                } catch (SocketTimeoutException s) {}
            }
            server.close();
        } catch (IOException e) {e.printStackTrace();}
    }






}