package me.notlewx.privategames.listeners.bedwarsproxy;

import com.andrei1058.bedwars.proxy.api.ArenaStatus;
import com.andrei1058.bedwars.proxy.api.CachedArena;
import com.google.gson.JsonObject;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.events.ProxyMessageReceiveEvent;
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

public class ProxyReceiveListener implements Listener {

    @EventHandler
    public void onMessageCreate(ProxyMessageReceiveEvent e) {
        JsonObject json = e.getMessage();
        if (!json.has("action")) return;


        switch (json.get("action").getAsString()) {
            case "privateArenaCreation":
                Utility.debug("Arena creation from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
                IPrivatePlayer host = api.getPrivatePlayer(UUID.fromString(json.get("host").getAsString()));
                String[] players = json.get("players").getAsString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                List<UUID> playersList = Arrays.stream(players).map(UUID::fromString).collect(Collectors.toList());
                new PrivateArena(host, playersList.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString(), json.get("defaultMaxInTeam").getAsInt());
                break;
            case "privateArenaUpdate":
                Utility.debug("Arena update from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
                IPrivateArena privateArena1 = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                String[] players1 = json.get("players").getAsString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                List<UUID> playersList1 = Arrays.stream(players1).map(UUID::fromString).collect(Collectors.toList());
                if (privateArena1 == null) return;
                PrivateArena.privateArenas.remove(privateArena1);
                PrivateArena.privateArenaByIdentifier.remove(privateArena1.getArenaIdentifier());
                for (OfflinePlayer pd : privateArena1.getPlayers()) {
                    PrivateArena.privateArenaByPlayer.remove(pd);
                }
                new PrivateArena(privateArena1.getPrivateArenaHost(), playersList1.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toList()), json.get("arenaIdentifier").getAsString(), json.get("defaultGroup").getAsString(), json.get("defaultMaxInTeam").getAsInt());
                break;
            case "privateArenaDeletion":
                Utility.debug("Arena deletion from remote server (identifier: " + json.get("arenaIdentifier").getAsString() + ")");
                IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(json.get("arenaIdentifier").getAsString());
                privateArena.destroyData();
                ArenaJoin.getPrivateProxyArenas().removeIf(arena -> arena.getRemoteIdentifier().equals(json.get("arenaIdentifier").getAsString()));
                break;
            case "privateArenaJoinRequest":
                UUID requester = UUID.fromString(json.get("requester").getAsString());
                UUID requested = UUID.fromString(json.get("requested").getAsString());
                String response = json.get("response").getAsString();
                Utility.debug("Arena join request from remote server (requester: " + requester + ", requested: " + requested + ", response: " + response + ")");
                OfflinePlayer player = Bukkit.getOfflinePlayer(requester);
                Player p = player.getPlayer();

                switch (response) {
                    case "accept":
                        p.sendMessage(Utility.getMsg(p, PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER).replace("{player}", Bukkit.getOfflinePlayer(requested).getName()));

                        CachedArena arena = null;
                        for (IPrivateArena a : api.getPrivateArenaUtil().getPrivateArenas()) {
                            if (a.isFull()) continue;

                            if (a.getPrivateArenaHost().getPlayer().getUniqueId().toString().equals(requested.toString())) {
                                arena = ArenaJoin.getPrivateProxyArenas().stream().filter(arena1 -> arena1.getRemoteIdentifier().equals(a.getArenaIdentifier())).findFirst().orElse(null);
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
