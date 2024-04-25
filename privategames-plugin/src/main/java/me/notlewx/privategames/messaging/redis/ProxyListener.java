package me.notlewx.privategames.messaging.redis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomkeuper.bedwars.proxy.api.event.RedisMessageEvent;
import me.notlewx.privategames.events.ProxyMessageReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ProxyListener implements Listener {
    @EventHandler
    public void onMessageCreate(RedisMessageEvent e) {
        if (!e.getAddonName().equals("private-games")) return;
        if (!e.getMessage().has("action")) return;

        JsonObject json = new JsonParser().parse(e.getMessage().toString()).getAsJsonObject();
        ProxyMessageReceiveEvent event = new ProxyMessageReceiveEvent(json, "redis", 0);
        Bukkit.getPluginManager().callEvent(event);
    }
}
