package model.data;
import java.util.HashMap;
import java.util.ArrayList;
public class Board {

    private static Board b=null;
    public Tile[][] tiles=new Tile[15][15];
    private static HashMap<String, String> boardScores;

    public Board(){
        this.initBoardScores();
        for(int i=0;i<15;i++)
            for(int j=0;j<15;j++)
                tiles[i][j]=null;
    }
    private void initBoardScores() {
        Board.boardScores = new HashMap<String, String>();
        boardScores.put("00", "3W");
        boardScores.put("70", "3W");
        boardScores.put("07", "3W");
        boardScores.put("014", "3W");
        boardScores.put("140", "3W");
        boardScores.put("147", "3W");
        boardScores.put("714", "3W");
        boardScores.put("1414", "3W");


        //DOUBLE WORD
        boardScores.put("11", "2W");
        boardScores.put("22", "2W");
        boardScores.put("33", "2W");
        boardScores.put("44", "2W");
        boardScores.put("113", "2W");
        boardScores.put("212", "2W");
        boardScores.put("311", "2W");
        boardScores.put("410", "2W");
        boardScores.put("131", "2W");
        boardScores.put("122", "2W");
        boardScores.put("113", "2W");
        boardScores.put("104", "2W");
        boardScores.put("1010", "2W");
        boardScores.put("1111", "2W");
        boardScores.put("1212", "2W");
        boardScores.put("1313", "2W");


        //TRIPLE LETTER
        boardScores.put("51", "3L");
        boardScores.put("91", "3L");
        boardScores.put("15", "3L");
        boardScores.put("55", "3L");
        boardScores.put("95", "3L");
        boardScores.put("135", "3L");
        boardScores.put("19", "3L");
        boardScores.put("59", "3L");
        boardScores.put("99", "3L");
        boardScores.put("139", "3L");
        boardScores.put("513", "3L");
        boardScores.put("913", "3L");

        //DOUBLE LETTER
        boardScores.put("30", "2L");
        boardScores.put("110", "2L");
        boardScores.put("62", "2L");
        boardScores.put("82", "2L");
        boardScores.put("03", "2L");
        boardScores.put("73", "2L");
        boardScores.put("143", "2L");
        boardScores.put("26", "2L");
        boardScores.put("66", "2L");
        boardScores.put("86", "2L");
        boardScores.put("126", "2L");
        boardScores.put("37", "2L");
        boardScores.put("117", "2L");
        boardScores.put("28", "2L");
        boardScores.put("68", "2L");
        boardScores.put("88", "2L");
        boardScores.put("128", "2L");
        boardScores.put("011", "2L");
        boardScores.put("711", "2L");
        boardScores.put("1411", "2L");
        boardScores.put("612", "2L");
        boardScores.put("812", "2L");
        boardScores.put("314", "2L");
        boardScores.put("1114", "2L");
    }

    public int getScore(Word word) {
        int row=word.getRow();
        int col=word.getCol();
        String r,c,chack;
        int letter_sum=0;
        int word_sum=0;
        int mult_word=0;
        for(int i=0;i<word.getTiels().length;i++) {
            r=Integer.toString(row);
            c=Integer.toString(col);
            chack=r+c;
            letter_sum=this.tiles[row][col].score;
            if(Board.boardScores.containsKey(chack)&&word.getTiels()[i]!=null)
            {
                switch(boardScores.get(chack)) {
                    case "2W":
                        mult_word=mult_word+2;
                        break;
                    case "3W":
                        mult_word=mult_word+3;
                        break;
                    case "2L":
                        letter_sum=letter_sum*2;
                        break;
                    case "3L":
                        letter_sum=letter_sum*3;
                }
            }

            word_sum=word_sum+letter_sum;
            if(word.getVertical()==true)
                row++;
            else col++;
        }


        if(mult_word>0)
            word_sum=word_sum*mult_word;
        return word_sum;

    }

    public static Board getBoard() {
        if(b==null) {
            b=new Board();
        }
        return b;
    }

    public boolean boardLegal(Word w) {

        int row=w.getRow();
        int col=w.getCol();
        if(w.getRow()>14||w.getRow()<0||w.getCol()>14||w.getCol()<0)
            return false;
        if(w.getVertical()==true) {
            if(w.getRow()+w.getTiels().length>14)
                return false;
        }
        else {
            if(w.getCol()+w.getTiels().length>14)
                return false;
        }
        if(this.tiles[7][7]==null) {
            for(int i=0;i<w.getTiels().length;i++) {
                if(w.getVertical()==true) {
                    if(row==7&&col==7) {
                        return true;
                    }
                    row++;
                }
                else {
                    if(row==7&&col==7) {
                        return true;
                    }

                    col++;
                }
            }
            return false;
        }
        else {//no change of exciting tiles
            for(int i=0;i<w.getTiels().length;i++) {
                if(w.getVertical()==true) {
                    if(this.tiles[row+i][col]!=null&&w.getTiels()[i]!=null)
                        return false;

                }
                else {
                    if(this.tiles[row][col+i]!=null&&w.getTiels()[i]!=null)
                        return false;

                }
            }
            col=w.getCol();
            row=w.getRow();
            for(int i=0;i<w.getTiels().length;i++) {
                if(w.getVertical()==true) {
                    if(col>0) {
                        if(this.tiles[row][col-1]!=null)
                            return true;
                    }
                    if(col<14) {
                        if(this.tiles[row][col+1]!=null)
                            return true;
                    }
                    row++;
                }
                else {
                    if(row>0) {
                        if(this.tiles[row-1][col]!=null)
                            return true;
                    }
                    if(row<14) {
                        if(this.tiles[row+1][col]!=null)
                            return true;
                    }
                    col++;
                }
            }
            return false;


        }

    }
    public boolean dictionaryLegal(Word w) {


        return true;
    }

    public ArrayList<Word> getWords(Word w){
        int row=w.getRow();
        int col=w.getCol();
        int r,c;
        Word new_word;
        ArrayList<Word> words=new ArrayList<>();
        //new_word=createWord(row,col,w.getVertical())
        words.add(w);
        for(int i=0;i<w.getTiels().length;i++) {
            if(w.getVertical()==true) {
                if(col>0&&(this.tiles[row][col-1]!=null||this.tiles[row][col+1]!=null)  &&w.getTiels()[i]!=null) {
                    r=row;
                    c=col;
                    while(c>0&&this.tiles[r][c-1]!=null) {
                        c--;
                    }
                    new_word=createWord(r,c,!(w.getVertical()));
                    if(dictionaryLegal(new_word))
                        words.add(new_word);
                }
            }
            else {
                if(row>0&&(this.tiles[row-1][col]!=null||this.tiles[row+1][col]!=null)&&w.getTiels()[i]!=null) {
                    r=row;
                    c=col;
                    while(r>0&&this.tiles[r-1][c]!=null) {
                        r--;
                    }
                    new_word=createWord(r,c,!(w.getVertical()));
                    if(dictionaryLegal(new_word))
                        words.add(new_word);
                }
            }
            if(w.getVertical()==true)
                row++;
            else col++;
        }
        return words;

    }
    public Word createWord (int row,int col,boolean vertical) {
        Word new_word;
        int r=row;
        int c=col;
        int len=0;
        Tile[] t;
        if(vertical==false) {
            while(col<15&&this.tiles[r][c]!=null) {
                c++;
                len++;
            }
            t=new Tile[len];
            c=col;
            for(int i=0;i<len;i++) {
                t[i]=tiles[r][c];
                c++;
            }
        }
        else {
            while(r<15&&this.tiles[r][c]!=null)
            {
                r++;
                len++;
            }
            t=new Tile[len];
            r=row;
            for(int i=0;i<len;i++) {
                t[i]=tiles[r][c];
                r++;
            }
        }
        new_word=new Word(t,row,col,vertical);
        return new_word;
    }

    public int tryPlaceWord(Word w) {
        int sum_total=0;
        boolean first=false;
        ArrayList<Word> words=new ArrayList<>();
        if(!dictionaryLegal(w))
            return -1;
        if(boardLegal(w)) {
            int row=w.getRow();
            int col=w.getCol();

            for(int i=0;i<w.getTiels().length;i++) {
                if (w.getTiels()[i] != null) {
                    if (row == 7 && col == 7)
                        first = true;
                    this.tiles[row][col] = w.getTiels()[i];
                }
                if (w.getVertical() == true)
                    row++;
                else col++;
            }
            words=getWords(w);
            for(int i=0;i<words.size();i++) {
                sum_total+=getScore(words.get(i));
            }
            if(first==true)
                sum_total=sum_total*2;
            return sum_total;
        }
        return 0;
    }
}
