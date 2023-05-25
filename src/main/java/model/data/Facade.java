package model.data;

public interface Facade {

       int TryPlaceWord(String name,int row,int col,boolean vertical,char[] _tiles);
       int Challenge(String name,int row,int col,boolean vertical,char[] _tiles);
       void PassTurn();
       void StartGame();
       void EndGame();





}
