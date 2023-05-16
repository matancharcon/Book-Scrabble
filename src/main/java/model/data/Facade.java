package model.data;

import java.util.List;

public interface Facade {

    void startGame();
    void placeTile(Tile tile, int row, int col);
    void passTurn();
    void exchangeTiles(List<Tile> tiles);
    void shuffleTiles();
    void recallTiles();
    void challengeWord(String word);
    Board getBoardState();
    int getTileBagSize();
    List<Player> getScores();
    boolean isGameOver();
    Player getWinner();
}
