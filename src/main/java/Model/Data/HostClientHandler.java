package Model.Data;

import java.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HostClientHandler {
    HostModel hm;
    public void handleClient(InputStream inFromClient, OutputStream outToClient,HostModel hm) {
        this.hm = hm;
        try {
            PrintWriter out = new PrintWriter(outToClient, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
            ObjectOutputStream ob=new ObjectOutputStream(outToClient);


            String[] data=in.readLine().split("_");
            char[] tiles = new char[data.length - 4];
            System.arraycopy(data, 4, tiles, 0, tiles.length);
            int result;
            Player p =hm.players.get(data[1]);
           switch (data[0]){

               case "con":
                   hm.players.get(data[1]);
                   out.println("you are connected");

               case "try":
                    result=hm.tryPlaceWord(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                    if(result==-1)
                        out.println("Not Found,challenge?");
                    else if(result==0)
                        out.println("Illegal move");
                    else out.println(result);


                   if(in.readLine()=="cha")
                       result =hm.Challenge(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                       out.println(result);


               case "cha":
                   result =hm.Challenge(data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]),Boolean.getBoolean(data[4]),tiles);
                   out.println(result);

               case "sta":
                    out.println("game is starting right away");

               case  "end":
                   hm.turns.remove(hm.players.get(data[1]));
                   hm.players.remove(data[1]);

               case  "pas":
                   hm.PassTurn();

               case  "sco":
                   out.println(hm.GetScore(data[1]));

               case  "han":
                   ob.writeObject(hm.GetHand(data[1]));

               case "fir":
                   ob.writeObject(hm.GetFirstLetter(data[1]));

               case "win":
                   ob.writeObject(hm.GetWinner());
           }

        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
