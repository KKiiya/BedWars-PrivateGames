package me.notlewx.pgames.listeners.player;

import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.notlewx.pgames.main.bwproxy;
import static me.notlewx.pgames.main.usingdb;

public class PlayerJoin implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    @EventHandler
    public static void onPlayerJoinServer(PlayerJoinEvent e) {
        String player = e.getPlayer().getName();
        if (!bwproxy) {
            if (usingdb) {
                mySQL.createPlayerData(player);
            } else {
                sqLite.createPlayerData(player);
            }
        }
        else {
            mySQL.createPlayerData(player);
        }
    }
}
