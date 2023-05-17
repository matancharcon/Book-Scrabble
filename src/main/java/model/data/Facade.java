package model.data;

public interface Facade {
       int GetPlayerScore();
       boolean TryPlaceWord(Word word);
       void SubmitWord(Word word);
       Player GetWinner();



}
