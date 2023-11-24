package me.notlewx.privategames.messaging.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ArenasSocket {
    private Socket clientSocket;
    private final LinkedList<ArenasSocket> sockets = new LinkedList<>();
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        System.out.println("Connecting to " + ip + ":" + port);
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
        sockets.add(this);
        out.write("Hello from " + clientSocket.getLocalSocketAddress());
    }

    public void sendMessage(String msg) throws IOException {
        for (ArenasSocket socket : sockets) {
            socket.out.println(msg);
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
