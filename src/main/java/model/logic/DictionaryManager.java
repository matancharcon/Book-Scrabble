package model.logic;
import java.util.*;

public class DictionaryManager {

    Map<String,Dictionary> search=new HashMap<>();
    private static DictionaryManager dm=null;

     public boolean query(String... args){
         String[] s=new String[args.length-1];
         for(int i=0;i< args.length-1;i++)
             s[i]=args[i];

         Dictionary d=new Dictionary(s);
         for(int i=0;i<s.length;i++){
             if(!search.containsKey(s[i]));
             search.put(s[i],d);
         }
         boolean flag=false;
         for(int i=0;i< args.length-1;i++){
             if(search.get(args[i]).query(args[args.length-1])){
                 flag=true;
             }
         }
         return flag;
     }
     public boolean challenge(String... args){
         String[] s=new String[args.length-1];
         for(int i=0;i< args.length-1;i++)
             s[i]=args[i];
         Dictionary d=new Dictionary(s);
         for(int i=0;i<s.length;i++){
             if(!search.containsKey(s[i]));
             search.put(s[i],d);
         }

         boolean flag=false;
         for(int i=0;i< args.length-1;i++){
             if(search.get(args[i]).challenge(args[args.length-1])){
                 flag=true;
             }
         }
         return flag;
     }

     public int getSize(){
return search.size();

     }
    public static DictionaryManager get() {
        if(dm==null) {
            dm=new DictionaryManager();
        }
        return dm;
    }
}
