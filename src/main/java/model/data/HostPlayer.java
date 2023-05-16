package model.data;

import model.logic.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class HostPlayer {
    private int id;
    private ServerSocket serverSocket;
    private int guestCount;

    public HostPlayer(int id) {
        this.id = id;
        this.guestCount = 0;
    }

    public void openServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for guest players to connect...");

            while (guestCount < 3) {

                Socket guestSocket = serverSocket.accept();
                System.out.println("Guest player connected: " + guestSocket.getInetAddress().getHostAddress());

                Thread guestThread = new Thread(() -> handleGuestPlayer(guestSocket));
                guestThread.start();

                guestCount++;
            }

            System.out.println("Maximum number of guest players reached.");
        } catch (IOException e) {
            System.out.println("Error occurred while opening the server: " + e.getMessage());
        }
    }

    private void handleGuestPlayer(Socket guestSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(guestSocket.getInputStream()));
            PrintWriter out = new PrintWriter(guestSocket.getOutputStream(), true);

            // Send a welcome message to the guest player
            out.println("Welcome to the Scrabble game!");

            // Read and process guest player's messages
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received message from guest player: " + message);

                // Process the guest player's message and provide a response
                String response = processGuestPlayerMessage(message);

                // Send the response back to the guest player
                out.println(response);

                // Check for game termination condition (if applicable)
                if (response.equals("Game over")) {
                    break;
                }
            }

            // Close the connections and resources for the guest player
            in.close();
            out.close();
            guestSocket.close();
        } catch (IOException e) {
            System.out.println("Error occurred while handling guest player: " + e.getMessage());
        }
    }
    private String processGuestPlayerMessage(String message) {
        // Process the guest player's message and provide a response
        // Add your game logic and rules implementation here

        // Example: Echo the guest player's message as the response
        return "You said: " + message;
    }
    public static void main(String[] args) {

        HostPlayer hostPlayer = new HostPlayer(1);
        hostPlayer.openServer(1235);
    }

}

