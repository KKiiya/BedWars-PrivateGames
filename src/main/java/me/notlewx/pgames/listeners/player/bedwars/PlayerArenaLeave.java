package me.notlewx.pgames.listeners.player.bedwars;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerArenaLeave implements Listener {
    private static final IGame game = PrivateGames.getGameUtil();
    @EventHandler
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {

    }
}
