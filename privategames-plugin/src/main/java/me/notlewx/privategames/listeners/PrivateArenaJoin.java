package me.notlewx.privategames.listeners;

import me.notlewx.privategames.api.events.PrivateGameJoinEvent;
import me.notlewx.privategames.utils.MessagesUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PrivateArenaJoin implements Listener {
    @EventHandler
    public void onPrivateArenaJoin(PrivateGameJoinEvent e) {
        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaUpdate", e.getPrivateArena()));
    }
}
