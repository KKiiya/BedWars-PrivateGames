package me.notlewx.privategames.messaging.socket.tasks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.events.ArenasMessageReceiveEvent;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxySocketTask implements Runnable {

    private final Socket clientSocket;
    private BufferedReader in;
    private static final boolean compute = true;

    public ProxySocketTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ignored) {

        }
    }
    @Override
    public void run() {
        while (compute && clientSocket.isConnected()) {
            String inputLine;

            try {
                inputLine = in.readLine();
            } catch (IOException e) {
                Utility.warn("Disconnected: " + clientSocket);
                break;
            }

            if (inputLine == null) continue;
            if (inputLine.isEmpty()) continue;

            JsonObject json;

            json = new JsonParser().parse(inputLine).getAsJsonObject();

            if (json == null) continue;
            if (!json.has("action")) continue;

            ArenasMessageReceiveEvent event = new ArenasMessageReceiveEvent(json, "socket-" + clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());
            Bukkit.getPluginManager().callEvent(event);
        }
    }
}
