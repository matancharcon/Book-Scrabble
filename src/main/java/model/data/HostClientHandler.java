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

    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        try {

            PrintWriter out = new PrintWriter(outToClient, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(inFromclient));

            String[] data=in.readLine().split("_");

           switch (data[0]){
               case "try":
                   int result=tryPlaceWord(data[1],data[2],data[3],)


               case "cha":

               case "sta":

               case  "end":


           }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int tryPlaceWord(String name,int row,int col,boolean vertical,char[] _tiles) {


    }




}
