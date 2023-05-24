package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuestModel extends PlayerModel {


    Socket socket;
    BufferedReader in;
    PrintWriter outToServer;
    StringBuilder word;
    public GuestModel(Player player,String ip,int port) throws IOException {
        super(player,ip,port);

        socket =new Socket( ip,port);
        outToServer = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


    }
    public int TryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {

        try {
            StringBuilder out = new StringBuilder("try");
            out.append("_" + row + "_" + col + "_" + vertical);
            for (char c : _tiles) {
                out.append("_" + c);
            }
            outToServer.println(out);

        } catch (Exception e) {

        }
    }

        public int Challenge(String name, int row, int col, boolean vertical, char[] _tiles) {
            try {
                StringBuilder out = new StringBuilder("cha");
                out.append("_" + row + "_" + col + "_" + vertical);
                for (char c : _tiles) {
                    out.append("_" + c);
                }
                outToServer.println(out);

            } catch (Exception e) {

            }
        }
    public void PassTurn() {
        try {
            StringBuilder out = new StringBuilder("pas");
            outToServer.println(out);

        }

    }
    public void startGame() {
        try {
            StringBuilder out = new StringBuilder("sta");
            outToServer.println(out);
        }

    }
    public void EndGame() {
        try {
            StringBuilder out = new StringBuilder("end");
            outToServer.println(out);
        }
    }








}
