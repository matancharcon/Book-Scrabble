package Model.Data;

import java.util.List;

public interface Facade {
       int tryPlaceWord(String name,int row,int col,boolean vertical,char[] _tiles);
       int Challenge(String name,int row,int col,boolean vertical,char[] _tiles);
       void PassTurn();
       void StartGame();
       void EndGame();
       Player GetWinner();
       int GetScore(String name);
       List<Tile> GetHand(String name);
       Tile GetFirstLetter(String name);

}
