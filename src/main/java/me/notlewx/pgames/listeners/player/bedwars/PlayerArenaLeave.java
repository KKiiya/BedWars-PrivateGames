package me.notlewx.pgames.listeners.player.bedwars;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import me.notlewx.pgames.api.PGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.notlewx.pgames.listeners.player.bedwars.PlayerArenaJoin.privateGameOwner;

public class PlayerArenaLeave implements Listener {
    @EventHandler
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {
        if (e.getPlayer() == privateGameOwner.get(e.getArena())) {
            PGamesAPI.getBwApi().getArenaUtil().getArenas().add(e.getArena());
        }
    }
}
