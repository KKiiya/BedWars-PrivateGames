package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.sidebar.SidebarService;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER;

public class ScoreboardListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent e) {
        for (Player p : e.getArena().getPlayers()) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                ISidebar sidebar = SidebarService.getInstance().getSidebar(p);
                sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                    if (api.getPrivateArenaUtil().isPlaying(p)) {
                        return Utility.getMsg(p, PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                    } else {
                        return "";
                    }
                }));
                sidebar.getHandle().refreshPlaceholders();
            }, 20L);
        }
    }
}
