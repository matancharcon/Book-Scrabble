package model.data;

import model.logic.MyServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class GuestPlayer {
    private int id;

    public GuestPlayer(int id) {
        this.id = id;
    }

    public void connectToServer(String ipAddress, int port) {
        try {
            Socket socket = new Socket(ipAddress, port);
            System.out.println("Connected to the server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread receiveThread = new Thread(() -> receiveMessages(in));
            receiveThread.start();


        } catch (IOException e) {
            System.out.println("Error occurred while connecting to the server: " + e.getMessage());
        }
    }

    private void receiveMessages(BufferedReader in) {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message from server: " + message);
            }
        } catch (SocketException e) {

            System.out.println("Connection reset. Server closed the connection.");
        } catch (IOException e) {
            System.out.println("Error occurred while receiving messages from the server: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // Create and connect multiple guest players (clients)
        GuestPlayer guestPlayer1 = new GuestPlayer(1);
        guestPlayer1.connectToServer("localhost", 1235);
        Thread.sleep(1000);
        GuestPlayer guestPlayer2 = new GuestPlayer(2);
        guestPlayer2.connectToServer("localhost", 1235);
        Thread.sleep(1000);
        GuestPlayer guestPlayer3 = new GuestPlayer(3);
        guestPlayer3.connectToServer("localhost", 1235);
        Thread.sleep(5000);
        GuestPlayer guestPlayer4 = new GuestPlayer(4);
        guestPlayer3.connectToServer("localhost", 1235);
    }
}