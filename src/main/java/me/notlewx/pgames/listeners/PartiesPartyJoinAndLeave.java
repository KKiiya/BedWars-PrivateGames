package me.notlewx.pgames.listeners;

import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostJoinEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostLeaveEvent;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.notlewx.pgames.main.usingdb;

public class PartiesPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    @EventHandler
    public static void onPartyJoin(BukkitPartiesPlayerPostJoinEvent e) {
        String path = e.getPartyPlayer().getName();
        mySQL = new MySQL();
        sqLite = new SQLite();
        if (usingdb) {
            mySQL.setBooleanData(path, "playerInParty", true);
        }
        else {
            sqLite.setBooleanData(path, "playerInParty", true);
        }
    }
    @EventHandler
    public static void onPartyLeave(BukkitPartiesPlayerPostLeaveEvent e) {
        String player = e.getPartyPlayer().getName();
        mySQL = new MySQL();
        sqLite = new SQLite();
        if (usingdb) {
            mySQL.setBooleanData(player, "playerInParty", false);
        }
        else {
            sqLite.setBooleanData(player, "playerInParty", false);
        }
    }
}
