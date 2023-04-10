package me.notlewx.pgames.listeners.player;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.Listener;

public class PlayerLeave implements Listener {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent e) {
        playerData.setPrivateGameDisabled(e.getPlayer());
    }
}
