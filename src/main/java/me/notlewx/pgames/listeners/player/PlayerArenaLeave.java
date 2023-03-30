package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerArenaLeave implements Listener {
    @EventHandler
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {
        String path = e.getPlayer().getName();
    }
}
