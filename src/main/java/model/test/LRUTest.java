package model.test;

import model.logic.CacheReplacementPolicy;
import model.logic.LRU;

public class LRUTest {
    public static void testLRU() {
        CacheReplacementPolicy lru = new LRU();
        lru.add("a");
        lru.add("b");
        lru.add("c");
        lru.add("a");

        if (!lru.remove().equals("b"))
            System.out.println("wrong implementation for LRU (-10)");
    }
}
