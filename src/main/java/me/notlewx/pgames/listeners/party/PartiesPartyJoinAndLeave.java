package me.notlewx.pgames.listeners.party;

import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostJoinEvent;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PartiesPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    @EventHandler
    public static void onPartyJoin(BukkitPartiesPlayerPostJoinEvent e) {
        String path = e.getPartyPlayer().getName();
    }
}
