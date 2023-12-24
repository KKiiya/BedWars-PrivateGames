package me.notlewx.privategames.listeners;

import me.notlewx.privategames.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.notlewx.privategames.PrivateGames.database;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e == null) return;

        Player p = e.getPlayer();
        if (p == null) return;

        Utility.debug("PlayerJoin: " + p.getName() + " has joined the server. Creating data...");
        try {
            database.createPlayerData(p);
        } catch (Exception ex) {
            Utility.info("Failed to create player data for " + p.getName() + "! This will not affect if the player has loaded data before.");
        }

        p.setHealth(20.0);
        p.setMaxHealth(20.0);
        p.setHealthScale(20.0);
    }
}
