package model.data;

import model.logic.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HostClientHandler extends Thread {
        private Socket clientSocket;
        private InputStream inputStream;
        private OutputStream outputStream;
        public MyServer myServer;

        public HostClientHandler(Socket clientSocket, MyServer myServer) throws IOException {
                this.clientSocket = clientSocket;
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                this.myServer = myServer;
        }

        @Override
        public void run() {
                try {
                        // Handle the client connection in a separate thread
                        myServer.ch.handleClient(inputStream, outputStream);
                } finally {
                        // Close the client socket
                        try {
                                clientSocket.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}
