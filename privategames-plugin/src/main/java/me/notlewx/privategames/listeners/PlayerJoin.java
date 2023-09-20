package me.notlewx.privategames.listeners;

import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.database;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        IPrivatePlayer pp = api.getPrivatePlayer(e.getPlayer());
        if (pp.hasPermission()) {
            database.createPlayerData(e.getPlayer());
        }
    }
}
