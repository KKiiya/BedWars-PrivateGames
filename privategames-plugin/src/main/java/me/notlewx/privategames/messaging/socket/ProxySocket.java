package me.notlewx.privategames.messaging.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static me.notlewx.privategames.PrivateGames.api;

public class ProxySocket {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private Boolean compute = true;

    public void start(int port) throws IOException {
        System.out.println("Starting socket on port " + port);
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting for client...");
        clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.toString());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Output stream created: " + out);
        in = new Scanner(clientSocket.getInputStream());
        System.out.println("Input stream created: " + in);


        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            while (compute) {
                if (!in.hasNext()) disable();
                else {
                    String inputLine = in.next();
                    final JsonObject json;

                    if (inputLine == null || inputLine.isEmpty()) continue;

                    try {
                        json = new JsonParser().parse(inputLine).getAsJsonObject();
                    } catch (Exception e) {
                        Utility.info("Error while parsing json: " + inputLine);
                        continue;
                    }

                    if (json == null) continue;
                    if (!json.has("action")) continue;

                    switch (json.get("action").getAsString()) {
                        case "privateArenaCreation":
                            IPrivatePlayer host = api.getPrivatePlayer(Bukkit.getPlayer(UUID.fromString(json.get("host").getAsString())));
                            Player[] players = (Player[]) Arrays.stream(json.get("players").getAsString().replace("[", "").replace("]", "").split(",")).map(uuid -> Bukkit.getPlayer(UUID.fromString(uuid))).toArray();
                            List<Player> playersList = new ArrayList<>(Arrays.asList(players));
                            new PrivateArena(host, playersList, json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                            break;
                        case "privateArenaDeletion":
                            IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                            privateArena.destroyData();
                            break;
                    }
                }
            }
        });
}

    @SuppressWarnings("UnusedReturnValue")
    public boolean sendMessage(String message) {
        if (clientSocket == null) {
            disable();
            return false;
        }
        if (isClosed()) {
            disable();
            return false;
        }
        if (out == null) {
            disable();
            return false;
        }
        if (in == null) {
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
        in.close();
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
