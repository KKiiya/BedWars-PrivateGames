package me.notlewx.privategames.messaging.socket.tasks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.UUID;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.support;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED;

public class ProxySocketTask implements Runnable {

    private final Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private static final boolean compute = true;

    public ProxySocketTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
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

            switch (json.get("action").getAsString()) {
                case "privateArenaJoinRequest":
                    if (!json.has("requester")) continue;
                    if (!json.has("requested")) continue;
                    if (!json.has("response")) continue;
                    OfflinePlayer requester = Bukkit.getOfflinePlayer(UUID.fromString(json.get("requester").getAsString()));
                    OfflinePlayer requested = Bukkit.getOfflinePlayer(UUID.fromString(json.get("requested").getAsString()));

                    if (json.get("response").getAsString().isEmpty()) {
                        Utility.debug("Received join request from" + requested.getName() + " for " + requester.getName());
                        if (!requested.isOnline()) {
                            Utility.debug("Requested player is not online, sending error message");
                            MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("error", requester.getUniqueId(), requested.getUniqueId()));
                            continue;
                        }

                        IPrivatePlayer requestedPlayer = PrivateGames.api.getPrivatePlayer(requested.getUniqueId());
                        switch (support) {
                            case BEDWARS1058:
                                api.getBedWars1058API().getArenaUtil().getArenas().add(api.getBedWars1058API().getArenaUtil().getArenaByIdentifier(Objects.requireNonNull(requestedPlayer.getArena()).getArenaIdentifier()));
                                break;
                            case BEDWARS2023:
                                api.getBedWars2023API().getArenaUtil().getArenas().add(api.getBedWars2023API().getArenaUtil().getArenaByIdentifier(Objects.requireNonNull(requestedPlayer.getArena()).getArenaIdentifier()));
                                break;
                        }
                        requestedPlayer.addRequest(requester.getUniqueId());

                        Utility.sendJoinRequestMessage(requested.getPlayer(), requester.getUniqueId());
                    } else if (json.get("response").getAsString().equals("expired")) {
                        if (!requested.isOnline()) continue;

                        Utility.debug("Join request expired from" + requested.getName() + " for " + requester.getName());
                        IPrivatePlayer requestedPlayer = PrivateGames.api.getPrivatePlayer(requested.getUniqueId());
                        requestedPlayer.removeRequest(requester.getUniqueId());
                    }
                    break;
            }
        }
    }
}
