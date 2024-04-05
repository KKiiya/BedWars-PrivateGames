package me.notlewx.privategames.messaging.socket.tasks;

import com.andrei1058.bedwars.proxy.api.ArenaStatus;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;
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

            switch (json.get("action").getAsString()) {
                case "privateArenaCreation":
                    Utility.debug("Arena creation from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ", server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                    IPrivatePlayer host = api.getPrivatePlayer(UUID.fromString(json.get("host").getAsString()));
                    String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                    List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                    new PrivateArena(host, playersList.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                    break;
                case "privateArenaUpdate":
                    Utility.debug("Arena update from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ", server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                    IPrivateArena privateArena1 = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                    String[] players1 = json.get("players").getAsString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                    List<UUID> playersList1 = Arrays.stream(players1).map(UUID::fromString).collect(Collectors.toList());
                    if (privateArena1 == null) return;
                    PrivateArena.privateArenas.remove(privateArena1);
                    PrivateArena.privateArenaByIdentifier.remove(privateArena1.getArenaIdentifier());
                    for (OfflinePlayer pd : privateArena1.getPlayers()) {
                        PrivateArena.privateArenaByPlayer.remove(pd);
                    }
                    new PrivateArena(privateArena1.getPrivateArenaHost(), playersList1.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                    break;
                case "privateArenaDeletion":
                    Utility.debug("Arena deletion from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ", server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                    IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                    privateArena.destroyData();
                    break;
                case "privateArenaJoinRequest":
                    UUID requester = UUID.fromString(json.get("requester").getAsString());
                    UUID requested = UUID.fromString(json.get("requested").getAsString());
                    String response = json.get("response").getAsString();
                    Utility.debug("Arena join request from remote server (requester: " + requester + ", requested: " + requested + ", response: " + response + "; server: " + socket.getLocalAddress() + ":" + socket.getLocalPort() + ")");
                    OfflinePlayer player = Bukkit.getOfflinePlayer(requester);
                    Player p = player.getPlayer();

                    switch (response) {
                        case "accept":
                            p.sendMessage(Utility.getMsg(p, PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));

                            if (PrivateGames.support == Support.BEDWARSPROXY2023) {
                                CachedArena arena = null;
                                for (IPrivateArena a : api.getPrivateArenaUtil().getPrivateArenas()) {
                                    if (a.isFull()) continue;

                                    if (a.getPrivateArenaHost().getPlayer().getUniqueId().toString().equals(requested.toString())) {
                                        arena = ArenaManager.getArenaByIdentifier(a.getArenaIdentifier());
                                    }
                                }

                                if (arena == null) {
                                    p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                                    return;
                                }

                                if (arena.getStatus() == com.tomkeuper.bedwars.proxy.api.ArenaStatus.WAITING || arena.getStatus() == com.tomkeuper.bedwars.proxy.api.ArenaStatus.STARTING) {
                                    arena.addPlayer(p, null);
                                } else if (arena.getStatus() == com.tomkeuper.bedwars.proxy.api.ArenaStatus.RESTARTING) {
                                    p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                                } else {
                                    arena.addSpectator(p, null);
                                }


                            } else if (PrivateGames.support == Support.BEDWARSPROXY) {
                                com.andrei1058.bedwars.proxy.api.CachedArena arena = null;
                                for (IPrivateArena a : api.getPrivateArenaUtil().getPrivateArenas()) {
                                    if (a.isFull()) continue;

                                    if (a.getPrivateArenaHost().getPlayer().getUniqueId().toString().equals(requested.toString())) {
                                        arena = com.andrei1058.bedwars.proxy.arenamanager.ArenaManager.getArenaByIdentifier(a.getArenaIdentifier());
                                    }
                                }

                                if (arena == null) {
                                    p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                                    return;
                                }

                                if (arena.getStatus() == ArenaStatus.WAITING || arena.getStatus() == ArenaStatus.STARTING) {
                                    arena.addPlayer(p, null);
                                } else if (arena.getStatus() == ArenaStatus.RESTARTING) {
                                    p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                                } else {
                                    arena.addSpectator(p, null);
                                }
                            }
                            break;
                        case "deny":
                            p.sendMessage(Utility.getMsg(p, PRIVATE_ARENA_REQUEST_DENIED_REQUESTER).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                            api.getPrivatePlayer(requested).removeRequest(requester);
                            break;
                        case "error":
                            p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));
                            api.getPrivatePlayer(requested).removeRequest(requester);
                            break;
                        default:
                            break;
                    }
            }
        }
    }
}
