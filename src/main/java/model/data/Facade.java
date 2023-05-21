package model.data;

public interface Facade {
       int GetPlayerScore();
       int TryPlaceWord(Word word);
       void SubmitWord(Word word);
       Player GetWinner();



}
