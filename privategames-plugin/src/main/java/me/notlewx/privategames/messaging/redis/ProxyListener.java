package me.notlewx.privategames.messaging.redis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.api.ArenaStatus;
import com.tomkeuper.bedwars.proxy.api.CachedArena;
import com.tomkeuper.bedwars.proxy.api.event.RedisMessageEvent;
import com.tomkeuper.bedwars.proxy.arenamanager.ArenaManager;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class ProxyListener implements Listener {
    @EventHandler
    public void onMessageCreate(RedisMessageEvent e) {
        if (!e.getAddonName().equals("private-games")) return;
        if (!e.getMessage().has("action")) return;

        JsonObject json = new JsonParser().parse(e.getMessage().toString()).getAsJsonObject();

        switch (json.get("action").getAsString()) {
            case "privateArenaCreation":
                Utility.debug("Arena creation from redis addons channel (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
                IPrivatePlayer host = api.getPrivatePlayer(UUID.fromString(json.get("host").getAsString()));
                String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                new PrivateArena(host, playersList.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString());
                break;
            case "privateArenaUpdate":
                Utility.debug("Arena update from redis addons channel (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
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
                Utility.debug("Arena deletion from redis addons channel (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
                IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                privateArena.destroyData();
                break;
            case "privateArenaJoinRequest":
                UUID requester = UUID.fromString(json.get("requester").getAsString());
                UUID requested = UUID.fromString(json.get("requested").getAsString());
                String response = json.get("response").getAsString();
                Utility.debug("Arena join request from redis addons channel (requester: " + requester + ", requested: " + requested + ", response: " + response + ")");
                OfflinePlayer player = Bukkit.getOfflinePlayer(requester);
                if (!player.isOnline()) return;
                Player p = player.getPlayer();
                if (p == null) return;
                switch (response) {
                    case "accept":
                        p.sendMessage(Utility.getMsg(p, PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));

                        CachedArena arena = null;
                        for (CachedArena a : ArenaManager.getArenas()) {
                            if (!api.getPrivateArenaUtil().isArenaPrivate(a.getRemoteIdentifier())) continue;
                            if (a.getCurrentPlayers() >= a.getMaxPlayers()) continue;

                            if (api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getRemoteIdentifier()).getPrivateArenaHost().getPlayer().getUniqueId().toString().equals(requested.toString())) {
                                arena = a;
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
