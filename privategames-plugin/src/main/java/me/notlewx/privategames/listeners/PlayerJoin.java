package me.notlewx.privategames.listeners;

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

        database.createPlayerData(p);

        p.setHealth(20.0);
        p.setMaxHealth(20.0);
        p.setHealthScale(20.0);
    }
}
