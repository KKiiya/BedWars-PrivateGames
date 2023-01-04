package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static me.notlewx.pgames.main.usingdb;

public class PlayerArenaJoin implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    @EventHandler
    public static void onPlayerJoin(PlayerJoinArenaEvent e) {
        String path = e.getPlayer().getName();
        if (usingdb) {
            mySQL.getBooleanData(path, "privateGameEnabled");
        }
        else {
            sqLite.getBooleanData(path, "privateGameEnabled");
        }
    }
}
