package me.notlewx.pgames.listeners.party;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DefaultPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    @EventHandler
    public static void onPartyJoin(PlayerJoinArenaEvent e) {
        String path = e.getPlayer().getName();

    }
}
