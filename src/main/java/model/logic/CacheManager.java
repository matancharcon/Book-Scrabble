package test;
import java.util.HashSet;
public class CacheManager {
int max_size;
int counter;
public HashSet<String> w;
CacheReplacementPolicy m_crp;
public CacheManager(int size,CacheReplacementPolicy crp){
  max_size=size;
  w=new HashSet<>(size);
  if(crp instanceof LFU){
      m_crp=new LFU();
  }
  else m_crp=new LRU();
  counter=0;
}

boolean query(String word){
    return w.contains(word);
}
void add(String s){
    String ToRemove;
    if(counter==max_size){
        ToRemove=m_crp.remove();
        w.remove(ToRemove);
        m_crp.add(s);
        w.add(s);
    }
    else {
        w.add(s);
        m_crp.add(s);
        counter++;
    }


}



}
