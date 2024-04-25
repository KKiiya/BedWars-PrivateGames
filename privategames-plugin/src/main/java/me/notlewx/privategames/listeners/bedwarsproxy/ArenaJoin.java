package me.notlewx.privategames.listeners.bedwarsproxy;

import com.andrei1058.bedwars.proxy.api.CachedArena;
import com.andrei1058.bedwars.proxy.api.event.PlayerArenaJoinEvent;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;

public class ArenaJoin implements Listener {

    private static final List<CachedArena> privateProxyArenas = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerArenaJoinEvent e) {
        CachedArena arena = e.getArena();
        IPrivatePlayer player = api.getPrivatePlayer(e.getPlayer());
        IPlayerSettings settings = player.getPlayerSettings();
        if (arena == null) return;

        if (arena.getCurrentPlayers() >= arena.getMaxPlayers()) return;
        if (!settings.isPrivateGameEnabled()) return;
        if (privateProxyArenas.contains(arena)) return;
        privateProxyArenas.add(arena);
    }

    public static List<CachedArena> getPrivateProxyArenas() {
        return privateProxyArenas;
    }
}
