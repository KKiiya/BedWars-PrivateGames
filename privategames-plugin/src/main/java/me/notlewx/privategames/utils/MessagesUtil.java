package me.notlewx.privategames.utils;

import com.andrei1058.bedwars.api.server.ServerType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.BedWarsProxy;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.messaging.socket.ArenasSocket;
import me.notlewx.privategames.messaging.socket.ProxySocket;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.Arrays;

import static me.notlewx.privategames.PrivateGames.*;

public class MessagesUtil {
    private static void sendMsg(String message) throws IOException {
        switch (support) {
            case BEDWARS2023:
                if (api.getBedWars2023API().getServerType() == com.tomkeuper.bedwars.api.server.ServerType.BUNGEE) {
                    switch (bw2023config.getString("bungeecord-settings.messaging-protocol")) {
                        case "redis":
                            api.getBedWars2023API().getRedisClient().sendMessage(new JsonParser().parse(message).getAsJsonObject(), "private-games");
                            break;
                        case "socket":
                            new ArenasSocket().sendMessage(message);
                            break;
                    }
                }
                break;
            case BEDWARSPROXY2023:
                switch (bwProxyConfig.getString("bungeecord-settings.messaging-protocol")) {
                    case "redis":
                        BedWarsProxy.getRedisConnection().sendMessage(new JsonParser().parse(message).getAsJsonObject(), "private-games");
                        break;
                    case "socket":
                        new ProxySocket().sendMessage(message);
                        break;
                }
                break;
            case BEDWARS1058:
                if (getBw1058Api().getServerType() == ServerType.BUNGEE) {
                    new ArenasSocket().sendMessage(message);
                }
                break;
            case BEDWARSPROXY:
                new ProxySocket().sendMessage(message);
                break;
        }
    }

    public static void sendMessage(String message) {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            try {
                sendMsg(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static String formatPrivateArena(IPrivateArena arena) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "privateArenaCreation");
        jsonObject.addProperty("host", arena.getPrivateArenaHost().getPlayer().getUniqueId().toString());
        jsonObject.addProperty("players", Arrays.toString(arena.getPlayers().stream().map(player -> player.getUniqueId().toString()).toArray(String[]::new)));
        jsonObject.addProperty("arenaIdentifier", arena.getArenaIdentifier());
        jsonObject.addProperty("defaultGroup", arena.getDefaultGroup());
        return jsonObject.toString();
    }
}
