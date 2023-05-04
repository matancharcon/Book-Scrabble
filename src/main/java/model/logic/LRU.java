package model.logic;
import model.logic.CacheReplacementPolicy;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.String;
public class LRU implements CacheReplacementPolicy {
    public Deque<String> doublyQueue;
    public HashSet<String> hashSet;
    public LRU(){
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
    }

    public void add(String word){
      if(hashSet.contains(word)){
          if(word.equals(doublyQueue.getLast())){
              doublyQueue.removeLast();
              doublyQueue.addFirst(word);
          }
          else doublyQueue.addFirst(word);
      }
      else {
          hashSet.add(word);
          doublyQueue.addFirst(word);
      }
    }
    public String remove(){
        String s= doublyQueue.removeLast();
        hashSet.remove(s);
        return s;
    }


}
