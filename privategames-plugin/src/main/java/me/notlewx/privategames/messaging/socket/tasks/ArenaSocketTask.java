package me.notlewx.privategames.messaging.socket.tasks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.events.ProxyMessageReceiveEvent;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static me.notlewx.privategames.messaging.socket.ProxySocket.compute;

public class ArenaSocketTask implements Runnable {

    private final Socket socket;
    private BufferedReader in;

    public ArenaSocketTask(Socket socket) {
        this.socket = socket;

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ignored) {

        }
    }
    @Override
    public void run() {
        while (compute && socket.isConnected()) {
            String inputLine;

            try {
                inputLine = in.readLine();
            } catch (IOException e) {
                Utility.warn("Client disconnected: " + socket);
                break;
            }

            if (inputLine == null) continue;
            if (inputLine.isEmpty()) continue;

            JsonObject json;

            json = new JsonParser().parse(inputLine).getAsJsonObject();

            if (json == null) {
                Utility.debug("Received null message from remote server (server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                continue;
            }
            if (!json.has("action")) {
                Utility.debug("Received message without action from remote server (server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                continue;
            }

            ProxyMessageReceiveEvent event = new ProxyMessageReceiveEvent(json, "socket-" + socket.getInetAddress().getHostAddress(), socket.getPort());
            Bukkit.getPluginManager().callEvent(event);
        }
    }
}
