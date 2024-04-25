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
import java.util.UUID;

import static me.notlewx.privategames.PrivateGames.*;

public class MessagesUtil {
    private static void sendMsg(String message) throws IOException {
        switch (support) {
            case BEDWARS2023:
                if (getBw2023Api().getServerType() == com.tomkeuper.bedwars.api.server.ServerType.BUNGEE) {
                    api.getBedWars2023API().getRedisClient().sendMessage(new JsonParser().parse(message).getAsJsonObject(), "private-games");
                }

                break;
            case BEDWARSPROXY2023:
                BedWarsProxy.getRedisConnection().sendMessage(new JsonParser().parse(message).getAsJsonObject(), "private-games");
                break;
            case BEDWARS1058:
                if (getBw1058Api().getServerType() == ServerType.BUNGEE) {
                    new ArenasSocket().sendMessage(message);
                }
                break;
            case BEDWARSPROXY:
                ProxySocket.getInstance().sendMessage(message);
                break;
        }
    }

    public static void sendMessage(String message) {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getInstance(), () -> {
            try {
                sendMsg(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static String formatPrivateArena(String action, IPrivateArena arena) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", action);
        jsonObject.addProperty("host", arena.getPrivateArenaHost().getPlayer().getUniqueId().toString());
        jsonObject.addProperty("players", Arrays.toString(arena.getPlayers().stream().map(player -> player.getUniqueId().toString()).toArray(String[]::new)));
        jsonObject.addProperty("arenaIdentifier", arena.getArenaIdentifier());
        jsonObject.addProperty("defaultGroup", arena.getDefaultGroup());
        return new JsonParser().parse(jsonObject.toString()).getAsJsonObject().toString().replace("\\", "");
    }

    public static String formatJoinRequest(String response, UUID requester, UUID requested) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", "privateArenaJoinRequest");
        jsonObject.addProperty("requester", requester.toString());
        jsonObject.addProperty("requested", requested.toString());
        jsonObject.addProperty("response", response);
        return new JsonParser().parse(jsonObject.toString()).getAsJsonObject().toString().replace("\\", "");
    }
}
