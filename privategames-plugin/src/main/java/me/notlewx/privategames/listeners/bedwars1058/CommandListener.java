package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.arena.IArena;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;

public class CommandListener implements Listener {
    @EventHandler
    public static void onCommandSending(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("pg.join") || e.getPlayer().isOp()) return;
        if (api.getPrivateArenaUtil().getPrivateArenas() == null) return;
        if (api.getPrivateArenaUtil().getPrivateArenas().isEmpty()) return;
        for (String arenaName : api.getPrivateArenaUtil().getPrivateArenas().stream().map(IPrivateArena::getArenaIdentifier).map(a -> api.getBedWars1058API().getArenaUtil().getArenaByIdentifier(a)).map(IArena::getWorldName).collect(Collectors.toList())) {
            if (e.getMessage().equalsIgnoreCase("/bw join " + arenaName)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Utility.getMsg(e.getPlayer(), "cmd-join-not-found")
                        .replace("{name}", arenaName)
                        .replace("{prefix}", Utility.getMsg(e.getPlayer(), "prefix")));
            }
        }
    }
}
