package me.notlewx.privategames.messaging.redis;

import com.tomkeuper.bedwars.api.events.communication.RedisMessageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenasListener implements Listener {
    @EventHandler
    public void onMessageCreate(RedisMessageEvent e) {
        if (!e.getAddonName().equals("private-games")) return;

    }
}
