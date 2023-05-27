package Model.Test;

import Model.Data.GuestModel;
import Model.Data.HostClientHandler;
import Model.Data.HostModel;
import Model.Data.Tile;

import java.io.IOException;
import java.util.List;

public class GuestModelTest {
    public static void main(String[] args) throws IOException {
        HostModel hm =new HostModel("localhost",8084,new HostClientHandler());
        GuestModel gm2 =new GuestModel("localhost",8084);
        GuestModel gm3 =new GuestModel("localhost",8084);
        GuestModel gm4 =new GuestModel("localhost",8084);

        if(!hm.getName().equals("player1")){
            System.out.println("problem with starting a game - name1");
        }
        if(!gm2.getName().equals("player2")){
            System.out.println("problem with starting a game - name2");
        }
        if(!gm3.getName().equals("player3")){
            System.out.println("problem with starting a game - name3");
        }
        if(!gm4.getName().equals("player4")){
            System.out.println("problem with starting a game - name4");
        }
        hm.StartGame();
        gm2.StartGame();
        gm3.StartGame();
        gm4.StartGame();

        List<Tile> hand1= hm.GetHand(hm.getName());
        List<Tile> hand2= gm2.GetHand(hm.getName());
        List<Tile> hand3= gm3.GetHand(hm.getName());
        List<Tile> hand4= gm4.GetHand(hm.getName());

        if (hand1.size()!=7)
            System.out.println("problem with starting a game - hand1");
        if (hand2.size()!=7)
            System.out.println("problem with starting a game - hand2");
        if (hand3.size()!=7)
            System.out.println("problem with starting a game - hand3");
        if (hand4.size()!=7)
            System.out.println("problem with starting a game - hand4");

        hm.EndGame();

    }
}
