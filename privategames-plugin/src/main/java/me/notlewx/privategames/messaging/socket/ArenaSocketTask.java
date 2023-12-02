package me.notlewx.privategames.messaging.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.messaging.socket.ProxySocket.compute;

public class ArenaSocketTask implements Runnable {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ArenaSocketTask(Socket socket) {
        this.socket = socket;
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
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

            if (json == null) continue;
            if (!json.has("action")) continue;

            switch (json.get("action").getAsString()) {
                case "privateArenaCreation":
                    IPrivatePlayer host = api.getPrivatePlayer(UUID.fromString(json.get("host").getAsString()));
                    String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").split(",");
                    List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                    new PrivateArena(host, playersList.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                    break;
                case "privateArenaUpdate":
                    IPrivateArena privateArena1 = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                    String[] players1 = json.get("players").getAsString().replace("[", "").replace("]", "").split(",");
                    List<UUID> playersList1 = Arrays.stream(players1).map(UUID::fromString).collect(Collectors.toList());
                    PrivateArena.privateArenas.remove(privateArena1);
                    PrivateArena.privateArenaByIdentifier.remove(privateArena1.getArenaIdentifier());
                    for (OfflinePlayer pd : privateArena1.getPlayers()) {
                        PrivateArena.privateArenaByPlayer.remove(pd);
                    }
                    new PrivateArena(privateArena1.getPrivateArenaHost(), playersList1.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                    break;
                case "privateArenaDeletion":
                    IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                    privateArena.destroyData();
                    break;
            }
        }
    }
}
