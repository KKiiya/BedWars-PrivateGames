package me.notlewx.privategames.messaging.redis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.api.event.RedisMessageEvent;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;

public class ProxyListener implements Listener {
    @EventHandler
    public void onMessageCreate(RedisMessageEvent e) {
        if (!e.getAddonName().equals("private-games")) return;
        if (!e.getMessage().has("action")) return;

        JsonObject json = e.getMessage();
        switch (json.get("action").getAsString()) {
            case "privateArenaCreation":
                IPrivatePlayer host = api.getPrivatePlayer(Bukkit.getPlayer(UUID.fromString(json.get("host").getAsString())));
                String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").split(",");
                List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                new PrivateArena(host, playersList.stream().map(Bukkit::getPlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                break;
            case "privateArenaDeletion":
                IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                privateArena.destroyData();
                break;
        }
    }
}
