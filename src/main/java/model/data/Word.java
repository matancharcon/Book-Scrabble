package test;
import java.util.Arrays;
import java.util.Objects;
public class Word {

    private Tile[] tiles;
    private int row,col;
    private boolean  vertical;
    public Word(Tile[] tiles, int row, int col, Boolean vertical) {
        super();
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }
    public Tile[] getTiels() {
        return tiles;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public Boolean getVertical() {
        return vertical;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        return col == other.col && row == other.row && Arrays.equals(tiles, other.tiles)
                && Objects.equals(vertical, other.vertical);
    }

}
