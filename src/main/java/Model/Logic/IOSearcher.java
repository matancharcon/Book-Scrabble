package Model.Logic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class IOSearcher {


    public static boolean search(String s, String... FileName)  {

        for(String name:FileName){
            File f=new File(name);
            try {
                Scanner in = new Scanner(f);
                while (in.hasNext()){
                    String w=in.next();
                    if(w.equals(s)) {
                        in.close();
                        return true;
                    }
                }
                in.close();
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
