package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.notlewx.privategames.PrivateGames.api;

public class ArenaLeave implements Listener {
    @EventHandler
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) return;
        if (e.getArena().getPlayers().size() <= 1) {
            api.getPrivateArenaUtil().getPrivateArenas().remove(api.getPrivateArenaUtil().getPrivateArenaByName(e.getArena().getArenaName()));
            api.getBedWars1058API().getArenaUtil().getArenas().add(e.getArena());
        }
    }
}
