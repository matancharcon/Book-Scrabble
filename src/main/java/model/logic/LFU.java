package test;
import java.util.*;
import java.lang.String;
import java.util.LinkedHashSet;

import java.util.Comparator;
public class LFU implements CacheReplacementPolicy {

    public HashMap<String,Integer> m;
    public LinkedList<String> l;

    public LFU(){
        m=new HashMap<>();
       l=new LinkedList<>();
    }
    public void add(String word) {
        int index = 0;
        if (!m.containsKey((word))) {
            m.put(word, 1);
            if (l.isEmpty() == true)
                l.add(word);
            else {
                while (index < l.size() &&  m.get(l.get(index)) == 1) {
                    index++;
                }
                l.add(index, word);
            }

        } else {
//            m.replace(word,m.get(()))
            m.put(word, m.get(word)+1);
            l.remove(word);
            while (index < l.size()&&m.get(l.get(index)) <= m.get(word) ) {
                index++;
            }
            l.add(index, word);
        }
    }
        public String remove() {
        m.put(l.getFirst(),m.get(l.getFirst())-1);
        if(m.get(l.getFirst())==0){
            m.remove(l.getFirst());
            return l.removeFirst();
        }
        return l.getFirst();

      }

    }


