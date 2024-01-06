package me.notlewx.privategames.messaging.socket;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.messaging.socket.tasks.ProxySocketTask;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;

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
    private Runnable task;
    private static final boolean compute = true;

    public void startConnection(String ip, int port) throws IOException {
        try {
            Utility.warn("Connecting to " + ip + ":" + port);
            clientSocket = new Socket(ip, port);
            Utility.info("Connected to " + ip + ":" + port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            task = new ProxySocketTask(clientSocket);
            Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), task);
            sockets.add(this);
        } catch (IOException ignored) {
            Utility.warn("Couldn't connect to " + ip + ":" + port);
        }

    }

    public void sendMessage(String msg) throws IOException {
        for (ArenasSocket socket : sockets) {
            socket.out.println(msg);
        }
    }

    public Runnable getTask() {
        return task;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
