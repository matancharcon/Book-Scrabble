package Model.Test;

import Model.Data.HostClientHandler;
import Model.Data.HostModel;
import Model.Data.Player;

public class HostModelTest {
    public static void main(String[] args) {
        HostModel hm =new HostModel("localhost",8080,new HostClientHandler());
        hm.StartGame();
        if(hm.GetHand(hm.getName()).size()!=7 ){
            System.out.println("problem with starting a game - hand");
        }
        if(!hm.getName().equals("player1")){
            System.out.println("problem with starting a game - name");
        }
        if(hm.GetFirstLetter(hm.getName())==null){
            System.out.println("problem with starting a game - first-letter");
        }
        char[] t ={'t','h','i','s'};
        if (hm.tryPlaceWord(hm.getName(),7,7,true,t) <1){
            System.out.println("problem with placing word ");
        }
        hm.EndGame();
    }
}

