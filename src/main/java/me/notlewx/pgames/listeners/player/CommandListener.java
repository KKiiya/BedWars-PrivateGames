package me.notlewx.pgames.listeners.player;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.util.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    private static IGame game = PrivateGames.getGameUtil();
    @EventHandler
    public static void onCommandSending(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().hasPermission("pg.join") || e.getPlayer().isOp()) return;
        if (game.getPrivateGames() == null) return;
        if (game.getPrivateGames().isEmpty()) return;
        for (String arenaName : game.getPrivateGames()) {
            if (e.getMessage().equalsIgnoreCase("/bw join " + arenaName)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Utility.getMSGLang(e.getPlayer(), "cmd-join-not-found")
                        .replace("{name}", arenaName)
                        .replace("{prefix}", Utility.getMSGLang(e.getPlayer(), "prefix")
                ));
            }
        }
    }
}
