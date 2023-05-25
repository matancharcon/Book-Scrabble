package model.data;

import model.logic.ClientHandler;
import model.logic.MyServer;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HostClientHandler {


    HostModel hm;
    public void handleClient(InputStream inFromclient, OutputStream outToClient,HostModel hm) {

        this.hm=hm;
        try {
            PrintWriter out = new PrintWriter(outToClient, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(inFromclient));

            String[] data=in.readLine().split("_");
            char[] tiles = new char[data.length - 4];
            System.arraycopy(data, 4, tiles, 0, tiles.length);
            int result;
            Player p =hm.Current_Players.get(data[1]);
           switch (data[0]){

               case "con":
                   hm.Current_Players.put(data[1], new Player(hm.ip,hm.port));
                   out.println("you are connected");

               case "try":
                    result=hm.TryPlaceWord(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                    if(result==-1)
                        out.println("Not Found,challenge?");
                    else if(result==0)
                        out.println("Illegal move");
                    else out.println(result);
                        p.setScore(p.getScore()+result);

                   if(in.readLine()=="cha")
                       result =hm.Challenge(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                       out.println(result);
                   p.setScore(p.getScore()+result);

               case "cha":
                   result =hm.Challenge(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                   p.setScore(p.getScore()+result);

               case "sta":


               case  "end":


               case  "pas":

           }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
