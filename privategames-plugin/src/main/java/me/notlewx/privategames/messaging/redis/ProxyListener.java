package me.notlewx.privategames.messaging.redis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.api.event.RedisMessageEvent;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

        JsonObject json = new JsonParser().parse(e.getMessage().toString()).getAsJsonObject();
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
