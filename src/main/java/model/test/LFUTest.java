package model.test;

import model.logic.CacheReplacementPolicy;
import model.logic.LFU;

public class LFUTest {
    public static void testLFU() {
        CacheReplacementPolicy lfu=new LFU();
        lfu.add("a");
        lfu.add("b");
        lfu.add("b");
        lfu.add("c");
        lfu.add("a");

        if(!lfu.remove().equals("c"))
            System.out.println("wrong implementation for LFU (-10)");
    }
}
