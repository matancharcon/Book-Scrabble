package test;
import java.util.Random;
import java.util.Objects;
public class Tile {

    public final char letter;
    public final int score;
    public int  max_amount;
    Object rand;

    private Tile(char letter, int score,int max_amount) {
        super();
        this.letter = letter;
        this.score = score;
        this.max_amount=max_amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        return letter == other.letter && score == other.score;
    }

    public static class Bag{
        private static Bag b=null;
        final Tile[] tiles=new Tile[26];
        int[] amount=new int[26];
        int counter=98;

        private Bag() {
            amount[0]=9;
            amount[1]=2;
            amount[2]=2;
            amount[3]=4;
            amount[4]=12;
            amount[5]=2;
            amount[6]=3;
            amount[7]=2;
            amount[8]=9;
            amount[9]=1;
            amount[10]=1;
            amount[11]=4;
            amount[12]=2;
            amount[13]=6;
            amount[14]=8;
            amount[15]=2;
            amount[16]=1;
            amount[17]=6;
            amount[18]=4;
            amount[19]=6;
            amount[20]=4;
            amount[21]=2;
            amount[22]=2;
            amount[23]=1;
            amount[24]=2;
            amount[25]=1;

            tiles[0]=new Tile('A',1,9);
            tiles[1]=new Tile('B',3,2);
            tiles[2]=new Tile('C',3,2);
            tiles[3]=new Tile('D',2,4);
            tiles[4]=new Tile('E',1,12);
            tiles[5]=new Tile('F',4,2);
            tiles[6]=new Tile('G',2,3);
            tiles[7]=new Tile('H',4,2);
            tiles[8]=new Tile('I',1,9);
            tiles[9]=new Tile('J',8,1);
            tiles[10]=new Tile('K',5,1);
            tiles[11]=new Tile('L',1,4);
            tiles[12]=new Tile('M',3,2);
            tiles[13]=new Tile('N',1,6);
            tiles[14]=new Tile('O',1,8);
            tiles[15]=new Tile('P',3,2);
            tiles[16]=new Tile('Q',10,1);
            tiles[17]=new Tile('R',1,6);
            tiles[18]=new Tile('S',1,4);
            tiles[19]=new Tile('T',1,6);
            tiles[20]=new Tile('U',1,4);
            tiles[21]=new Tile('V',4,2);
            tiles[22]=new Tile('W',4,2);
            tiles[23]=new Tile('X',8,1);
            tiles[24]=new Tile('Y',4,2);
            tiles[25]=new Tile('Z',10,1);
        }
        public Tile getRand(){

            Random rand = new Random();
            int n;
            if (counter<=0)
                return null;
            do{
                n =rand.nextInt(26);

            }while(amount[n]<=0&&counter>0);
            counter--;
            amount[n]--;
            return tiles[n];
        }
        public Tile getTile(char z) {
            int asci;
            if(counter<=0)
                return null;
            for(Tile t:tiles) {
                if(Character.compare(t.letter,z)==0) {
                    asci= z;
                    if(amount[asci-'A']<=0)
                        return null;
                    amount[asci-'A']--;
                    return t;
                }
            }
            return null;
        }
        public void put(Tile t) {
            int a=t.letter;
            if(amount[a-65]==tiles[a-65].max_amount)
                return;
            else amount[a-65]++;

        }
        public int size() {
            return counter;
        }
        public int[] getQuantities() {
            int [] arr=new int [amount.length];
            for(int i=0;i<arr.length;i++) {
                arr[i]=amount[i];
            }
            return arr;
        }
        public static Bag getBag() {
            if(b==null)
                b=new Bag();
            return b;

        }



    }

}
