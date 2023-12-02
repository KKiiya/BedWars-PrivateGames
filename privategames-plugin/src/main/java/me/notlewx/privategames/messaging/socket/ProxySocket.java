package me.notlewx.privategames.messaging.socket;

import me.notlewx.privategames.PrivateGames;
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

    public void start(int port) throws IOException {
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
}

    @SuppressWarnings("UnusedReturnValue")
    public boolean sendMessage(String message) {
        if (clientSocket == null || isClosed() || out == null || in == null) {
            disable();
            return false;
        }
        if (out.checkError()) {
            disable();
            return false;
        }
        out.println(message);
        return true;
    }

    private void disable() {
        compute = false;
        Utility.info("Disabling socket: " + clientSocket.toString());
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing socket: " + clientSocket.toString(), e);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing input stream: " + clientSocket.toString(), e);
        }
        out.close();
        in = null;
        out = null;
    }

    public boolean isConnected() {
        return clientSocket.isConnected();
    }

    public boolean isClosed() {
        return clientSocket.isClosed();
    }
}
