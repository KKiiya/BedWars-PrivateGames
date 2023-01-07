package me.notlewx.pgames.listeners.party;

import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostJoinEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostLeaveEvent;
import com.alessiodp.parties.api.interfaces.Party;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.List;
import java.util.UUID;
import static me.notlewx.pgames.main.bwproxy;
import static me.notlewx.pgames.main.usingdb;

public class PartiesPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    public static List<UUID> members;
    public static Party party;
    @EventHandler
    public static void onPartyJoin(BukkitPartiesPlayerPostJoinEvent e) {
        String path = e.getPartyPlayer().getName();
        party = (Party) e.getParty().getMembers();
        members = (List<UUID>) e.getParty().getMembers();
        mySQL = new MySQL();
        sqLite = new SQLite();
        if (!bwproxy) {
            if (usingdb) {
                mySQL.setBooleanData(path, "playerInParty", true);
            } else {
                sqLite.setBooleanData(path, "playerInParty", true);
            }
        }
        else {
            mySQL.setBooleanData(path, "playerInParty", true);
        }
    }
    @EventHandler
    public static void onPartyLeave(BukkitPartiesPlayerPostLeaveEvent e) {
        mySQL = new MySQL();
        sqLite = new SQLite();
        if (!bwproxy) {
            for (UUID uid : members) {
                String names = Bukkit.getPlayer(uid).getName();
                if (usingdb) {
                    mySQL.setBooleanData(names, "playerInParty", false);
                } else {
                    sqLite.setBooleanData(names, "playerInParty", false);
                }
            }
        }
        else {
            for (UUID uid : members) {
                String names = Bukkit.getPlayer(uid).getName();
                mySQL.setBooleanData(names, "playerInParty", false);
            }
        }
    }
}
