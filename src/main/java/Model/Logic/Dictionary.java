package Model.Logic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Dictionary {

    CacheManager exist;
    CacheManager not_exist;
    BloomFilter b;
    String[] m_s;
    public Dictionary(String... s){
        exist=new CacheManager(400,new LRU());
        not_exist=new CacheManager(100,new LFU());
        b=new BloomFilter(256,"MD5","SHA1");
        m_s=s;
       for(String w: s){
           File f=new File(w);
           try {
               Scanner in = new Scanner(f);
               while (in.hasNext()){
                   String t=in.next();
                   exist.add(t);
                   b.add(t);
               }
               in.close();
           }
           catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       }
    }
    public boolean query(String To_chack){

        if(exist.query(To_chack))
            return  true;
        if(not_exist.query(To_chack))
            return false;

          if(!(b.contains(To_chack))){
              not_exist.add(To_chack);
              return false;
          }
          return false;
    }
    public boolean challenge(String To_chack){
        try {
            if (IOSearcher.search(To_chack, m_s)) {
                exist.add(To_chack);
                return true;
            }
            not_exist.add(To_chack);
            return false;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }






}
