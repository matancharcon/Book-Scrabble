package Model.Logic;
import java.io.*;
import java.util.Arrays;

public class BookScrabbleHandler implements ClientHandler {

    BufferedReader in;
    PrintWriter out;
    public void handleClient(InputStream inFromclient, OutputStream outToClient){
        DictionaryManager dm=DictionaryManager.get();
        in =new BufferedReader(new InputStreamReader(inFromclient));
        out=new PrintWriter(outToClient,true);
        String line;
        String[] s;
        String [] books;
        try {
            line = in.readLine();
            s=line.split(",");
            if(s[0].equals("Q")){
                if(dm.query(Arrays.copyOfRange(s,1,s.length))){
                    out.println("true"+ "\n");
                }
                else out.println("false"+"\n");
            }
            else {
                if(dm.challenge(Arrays.copyOfRange(s,1,s.length))){
                    out.println("true"+ "\n");
                }
                else out.println("false"+"\n");
            }
        }
        catch (IOException e){}

    }
    public void close(){
   try {
       in.close();
       out.close();
   }catch (IOException e){}

    }
}
