package me.notlewx.privategames.listeners;

import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import static me.notlewx.privategames.PrivateGames.api;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        IPrivatePlayer pp = api.getPrivatePlayer(e.getPlayer());
        pp.clearRequests();
        Utility.debug("PlayerLeave: " + e.getPlayer().getName() + " has left the server.");
        if (!pp.hasPermission()) return;
        if (!pp.getPlayerSettings().isPrivateGameEnabled()) return;
    }
}
