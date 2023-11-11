package me.notlewx.privategames.listeners.bedwars2023;

import com.tomkeuper.bedwars.api.events.player.PlayerLeaveArenaEvent;
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
            api.getBedWars2023API().getArenaUtil().getArenas().add(e.getArena());
        }
    }
}
