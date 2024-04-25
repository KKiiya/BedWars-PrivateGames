package me.notlewx.privategames.listeners.bedwars1058;

import com.google.gson.JsonObject;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.events.ArenasMessageReceiveEvent;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.UUID;

import static me.notlewx.privategames.PrivateGames.api;

public class ArenaReceiveListener implements Listener {

    @EventHandler
    public void onMessage(ArenasMessageReceiveEvent e) {
        JsonObject json = e.getMessage();
        if (!json.has("action")) return;

        switch (json.get("action").getAsString()) {
            case "privateArenaJoinRequest":
                if (!json.has("requester")) return;
                if (!json.has("requested")) return;
                if (!json.has("response")) return;
                OfflinePlayer requester = Bukkit.getOfflinePlayer(UUID.fromString(json.get("requester").getAsString()));
                OfflinePlayer requested = Bukkit.getOfflinePlayer(UUID.fromString(json.get("requested").getAsString()));

                if (json.get("response").getAsString().isEmpty()) {
                    Utility.debug("Received join request from" + requested.getName() + " for " + requester.getName());
                    if (!requested.isOnline()) {
                        Utility.debug("Requested player is not online, sending error message");
                        MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("error", requester.getUniqueId(), requested.getUniqueId()));
                        return;
                    }

                    IPrivatePlayer requestedPlayer = api.getPrivatePlayer(requested.getUniqueId());
                    api.getBedWars1058API().getArenaUtil().getArenas().add(api.getBedWars1058API().getArenaUtil().getArenaByIdentifier(Objects.requireNonNull(requestedPlayer.getArena()).getArenaIdentifier()));
                    requestedPlayer.addRequest(requester.getUniqueId());

                    Utility.sendJoinRequestMessage(requested.getPlayer(), requester.getUniqueId());
                } else if (json.get("response").getAsString().equals("expired")) {
                    if (!requested.isOnline()) return;

                    Utility.debug("Join request expired from" + requested.getName() + " for " + requester.getName());
                    IPrivatePlayer requestedPlayer = api.getPrivatePlayer(requested.getUniqueId());
                    requestedPlayer.removeRequest(requester.getUniqueId());
                }
                break;
        }
    }
}
