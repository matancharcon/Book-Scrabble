package model.data;

public class PlayerModel implements Facade{
    Player player;
    public PlayerModel(Player player) {
        this.player = player;
    }
    @Override
    public int GetPlayerScore() {
       return player.getScore();
    }

    @Override
    public boolean TryPlaceWord(Word word) {
        return false;
    }

    @Override
    public void SubmitWord(Word word) {

    }

    @Override
    public Player GetWinner() {
        return null;
    }
}
