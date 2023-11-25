package me.notlewx.privategames.messaging.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;

public class ProxySocket {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Boolean compute = true;

    public void start(int port) throws IOException {
        Utility.warn("Starting socket on port " + port);
        serverSocket = new ServerSocket(port);
        Utility.warn("Waiting for client...");
        clientSocket = serverSocket.accept();
        Utility.info("Client connected: " + clientSocket.toString());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            while (compute) {
                String inputLine;

                try {
                    inputLine = in.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (inputLine == null) continue;
                if (inputLine.isEmpty()) continue;

                final JsonObject json;

                json = new JsonParser().parse(inputLine).getAsJsonObject();

                if (json == null) continue;
                if (!json.has("action")) continue;

                switch (json.get("action").getAsString()) {
                    case "privateArenaCreation":
                        IPrivatePlayer host = api.getPrivatePlayer(UUID.fromString(json.get("host").getAsString()));
                        String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").split(",");
                        List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                        new PrivateArena(host, playersList.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                        break;
                    case "privateArenaDeletion":
                        IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                        privateArena.destroyData();
                        break;
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
