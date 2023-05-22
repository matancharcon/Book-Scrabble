package model.data;

import model.logic.DictionaryManager;

import java.util.HashMap;

public class HostModel extends PlayerModel{
    HashMap<Integer,Player> Current_Players;
    DictionaryManager dm;
    Board board;
    Tile.Bag bag;


    public HostModel(Player player) {
        super(player);

    }
}
