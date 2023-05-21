package model.data;

public class PlayerModel implements Facade{
    Player player;
    Board board;
    public PlayerModel(Player player) {
        this.player = player;
    }
    @Override
    public int GetPlayerScore() {
       return player.getScore();
    }

    @Override
    public int TryPlaceWord(Word word) {
       return board.tryPlaceWord(word);
    }

    @Override
    public void SubmitWord(Word word) {

    }

    @Override
    public Player GetWinner() {
        return null;
    }
}
