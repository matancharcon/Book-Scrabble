package model.logic;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MyServer {
    int port;
    ClientHandler ch;
    boolean stop;

    public MyServer(int p, ClientHandler ch) {
        this.port = p;
        this.ch = ch;

    }

    public void start() {
        stop = false;
        new Thread(() -> startServer()).start();
    }

    private void startServer() {

        try {
            ServerSocket Server = new ServerSocket(port);
            Server.setSoTimeout(1000);
            while (!stop) {
                try {
                    Socket Client = Server.accept();
                    ch.handleClient(Client.getInputStream(), Client.getOutputStream());
                    ch.close();
                    Client.close();
                } catch (SocketTimeoutException e) {}
            }
            Server.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void close() {
        stop=true;
    }
}
