package me.notlewx.privategames.messaging.redis;

import com.google.gson.JsonObject;
import com.tomkeuper.bedwars.api.events.communication.RedisMessageEvent;
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

public class ArenasListener implements Listener {
    @EventHandler
    public void onMessageCreate(RedisMessageEvent e) {
        if (!e.getAddonName().equals("private-games")) return;

        JsonObject json = e.getMessage();

        if (!json.has("action")) return;

        ArenasMessageReceiveEvent event = new ArenasMessageReceiveEvent(json, "redis", 0);
        Bukkit.getPluginManager().callEvent(event);
    }
}
