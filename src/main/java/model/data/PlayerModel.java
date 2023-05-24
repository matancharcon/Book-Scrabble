package model.data;
import java.io.*;
import java.net.*;

public class PlayerModel implements Facade{
    Player player;
    String ip;
    int port;
    public PlayerModel(Player player,String ip,int port) {

        this.player = player;
        this.ip=ip;
        this.port=port;
    }


    public String getIpAddress() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public int GetPlayerScore() {
        return 0;
    }

    @Override
    public int TryPlaceWord(String name, int row, int col, boolean vertical, char[] _tiles) {
        return 0;
    }

    @Override
    public int Challenge(String name, int row, int col, boolean vertical, char[] _tiles) {
        return 0;
    }

    @Override
    public void PassTurn() {

    }

    @Override
    public void StartGame() {

    }

    @Override
    public void EndGame() {

    }
}
