package me.notlewx.pgames.listeners;

import static me.notlewx.pgames.listeners.PlayerArenaJoin.join;
import static me.notlewx.pgames.listeners.PlayerArenaJoin.rblock;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerArenaLeave implements Listener {
    @EventHandler
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {
        join = false;
        e.getPlayer().getInventory().removeItem(rblock);
    }
}
