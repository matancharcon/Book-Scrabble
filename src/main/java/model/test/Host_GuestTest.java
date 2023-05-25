package model.test;

import model.data.GuestModel;
import model.data.HostClientHandler;
import model.data.HostModel;
import model.data.Player;

import java.io.IOException;

public class Host_GuestTest {

    public static void main(String[] args) throws IOException {

        Player p1=new Player(1);


        HostModel hm=new HostModel(p1,"localhost",8080,new HostClientHandler());

        Player p2=new Player(2);
        Player p3=new Player(3);
        Player p4=new Player(4);
        Player p5=new Player(5);

        GuestModel gm=new GuestModel(p2,"localhost",8080);



    }

}
