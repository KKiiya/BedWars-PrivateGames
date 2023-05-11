package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.events.player.PlayerFirstSpawnEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.data.database.MySQL;
import me.notlewx.pgames.data.database.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private static final MySQL mySQL = new MySQL();
    private static final SQLite sqLite = new SQLite();
    @EventHandler
    public static void onPlayerJoinServer(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPermission("pg")) return;
        String path = e.getPlayer().getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.createPlayerData(path);
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.createPlayerData(path);
            } else {
                sqLite.createPlayerData(path);
            }
        }
    }
}
