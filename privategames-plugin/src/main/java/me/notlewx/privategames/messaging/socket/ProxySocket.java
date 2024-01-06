package me.notlewx.privategames.messaging.socket;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.messaging.socket.tasks.ArenaSocketTask;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxySocket {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public static Boolean compute = true;
    private static ProxySocket instance;

    public void start(int port) throws IOException {
        if (instance != null) {
            throw new RuntimeException("Socket already started!");
        }
        Utility.warn("Starting socket on port " + port);
        serverSocket = new ServerSocket(port);

        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            while (compute) {
                try {
                    clientSocket = serverSocket.accept();
                    if (clientSocket == null) continue;
                    Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), new ArenaSocketTask(clientSocket));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    Utility.info("Client connected: " + clientSocket.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        instance = this;
}

    @SuppressWarnings("UnusedReturnValue")
    public boolean sendMessage(String message) {
        if (instance.clientSocket == null || isClosed() || instance.out == null || instance.in == null) {
            disable();
            return false;
        }
        if (instance.out.checkError()) {
            disable();
            return false;
        }
        instance.out.println(message);
        return true;
    }

    private void disable() {
        compute = false;
        Utility.info("Disabling socket: " + instance.clientSocket.toString());
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing socket: " + instance.clientSocket.toString(), e);
        }
        try {
            instance.in.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing input stream: " + instance.clientSocket.toString(), e);
        }
        instance.out.close();
        instance.in = null;
        instance.out = null;
    }

    public boolean isConnected() {
        return instance.clientSocket.isConnected();
    }

    public boolean isClosed() {
        return instance.clientSocket.isClosed();
    }

    public static ProxySocket getInstance() {
        return instance;
    }
}
