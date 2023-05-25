package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuestModel extends Player implements Facade {


    Socket socket;
    BufferedReader in;
    PrintWriter outToServer;
    StringBuilder word;

    public GuestModel(String ip,int port) throws IOException {
super(ip,port);

        socket =new Socket( ip,port);
        outToServer = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


    }
    public void Connect_Player(String name){
        StringBuilder out = new StringBuilder("con_"+name);
        outToServer.println(out);
    }

    public int TryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {

        try {
            StringBuilder out = new StringBuilder("try");
            out.append(name+"_" + row + "_" + col + "_" + vertical);
            for (char c : _tiles) {
                out.append("_" + c);
            }
            outToServer.println(out);

            String result=in.readLine();

            return Integer.parseInt(result);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

        public int Challenge(String name, int row, int col, boolean vertical, char[] _tiles) {
            try {
                StringBuilder out = new StringBuilder("cha");
                out.append(name+"_" + row + "_" + col + "_" + vertical);
                for (char c : _tiles) {
                    out.append("_" + c);
                }
                outToServer.println(out);

                String result=in.readLine();
                if(result.equals("-5"))
                    System.out.println("Challenge Unsuccessful");
                return Integer.parseInt(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    public void PassTurn() {
        try {
            StringBuilder out = new StringBuilder("pas");
            outToServer.println(out);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void StartGame() {
        try {
            StringBuilder out = new StringBuilder("sta");
            outToServer.println(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void EndGame() {
        try {
            StringBuilder out = new StringBuilder("end");
            outToServer.println(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }








}
