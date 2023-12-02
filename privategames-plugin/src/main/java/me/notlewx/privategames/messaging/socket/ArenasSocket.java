package me.notlewx.privategames.messaging.socket;

import me.notlewx.privategames.utils.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

public class ArenasSocket {
    private Socket clientSocket;
    private static final LinkedList<ArenasSocket> sockets = new LinkedList<>();
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        try {
            Utility.warn("Connecting to " + ip + ":" + port);
            clientSocket = new Socket(ip, port);
            Utility.info("Connected to " + ip + ":" + port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sockets.add(this);
        } catch (IOException ignored) {

        }

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
