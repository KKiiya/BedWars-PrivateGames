package me.notlewx.privategames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.notlewx.privategames.PrivateGames.database;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        database.createPlayerData(e.getPlayer());
        e.getPlayer().setHealth(20.0);
        e.getPlayer().setMaxHealth(20.0);
        e.getPlayer().setHealthScale(20.0);
    }
}
