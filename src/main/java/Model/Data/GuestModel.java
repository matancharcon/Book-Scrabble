package Model.Data;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class GuestModel extends Player implements Facade {
    Socket socket;
    BufferedReader in;
    PrintWriter outToServer;
    StringBuilder word;
    ObjectInputStream ob;

    public GuestModel(String ip,int port) throws IOException {
        socket =new Socket( ip,port);
        outToServer = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ob=new ObjectInputStream(socket.getInputStream());
        setIp(ip);
        setPort(port);

    }

    public int tryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {

        try {
            StringBuilder out = new StringBuilder("try");
            out.append(name+"_" + row + "_" + col + "_" + vertical);
            for (char c : _tiles) {
                out.append("_" + c);
            }
            outToServer.println(out);

            String result=in.readLine();
            return Integer.parseInt(result);

        } catch (Exception e) {throw new RuntimeException(e);}
    }
    @Override
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
        } catch (IOException e) {throw new RuntimeException(e);}
    }
    @Override
    public void PassTurn() {
        try {
            StringBuilder out = new StringBuilder("pas_");
            out.append(name);
            outToServer.println(out);

        } catch (Exception e) {throw new RuntimeException(e);}

    }
    @Override
    public void StartGame() {
        try {
            StringBuilder out = new StringBuilder("sta_");
            out.append(name);
            outToServer.println(out);
        } catch (Exception e) {throw new RuntimeException(e);}
    }
    public void EndGame() {
        try {
            StringBuilder out = new StringBuilder("end_");
            out.append(name);
            outToServer.println(out);
        } catch (Exception e) {throw new RuntimeException(e);}
    }
    @Override
    public Player GetWinner() {
        try {
            StringBuilder out = new StringBuilder("win_");
            out.append(name);
            return (Player) ob.readObject();
            } catch (IOException e) {throw new RuntimeException(e);}
              catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
    @Override
    public int GetScore(String name) {
        StringBuilder out = new StringBuilder("sco_");
        out.append(name);
        try {
            String score=in.readLine();
            return Integer.parseInt(score);
        } catch (IOException e) {throw new RuntimeException(e);}
    }
    @Override
    public List<Tile> GetHand(String name) {
        try {
            StringBuilder out = new StringBuilder("han_");
            out.append(name);
            return (List<Tile>) ob.readObject();
        } catch (IOException e) {throw new RuntimeException(e);}
          catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
    @Override
    public Tile GetFirstLetter(String name) {
        try {
            StringBuilder out = new StringBuilder("fir_");
            out.append(name);
            return (Tile) ob.readObject();
        } catch (IOException e) {throw new RuntimeException(e);}
        catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
}
