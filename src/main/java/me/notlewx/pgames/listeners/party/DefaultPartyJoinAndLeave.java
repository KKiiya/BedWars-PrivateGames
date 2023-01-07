package me.notlewx.pgames.listeners.party;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.party.Party;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.List;
import static me.notlewx.pgames.main.bwproxy;
import static me.notlewx.pgames.main.usingdb;

public class DefaultPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    public static Party defparty;
    public static List<Player> membersdef;
    @EventHandler
    public static void onPartyJoin(PlayerJoinArenaEvent e) {
        Player player = e.getPlayer();
        membersdef = defparty.getMembers(player);
        if (defparty.partySize(player) >= 2) {
            if (!bwproxy) {
                for (Player player1 : defparty.getMembers(player)) {
                    if (usingdb) {
                        mySQL.setBooleanData(player1.getName(), "playerInParty", true);
                    } else {
                        sqLite.setBooleanData(player1.getName(), "playerInParty", true);
                    }
                }
            }
            else {
                for (Player player1 : defparty.getMembers(player)) {
                    mySQL.setBooleanData(player1.getName(), "playerInParty", true);
                }
            }
        }
        else {
            if (!bwproxy) {
                for (Player player1 : defparty.getMembers(player)) {
                    if (usingdb) {
                        mySQL.setBooleanData(player1.getName(), "playerInParty", false);
                    } else {
                        sqLite.setBooleanData(player1.getName(), "playerInParty", false);
                    }
                }
            }
            else {
                for (Player player1 : defparty.getMembers(player)) {
                    mySQL.setBooleanData(player1.getName(), "playerInParty", false);
                }
            }
        }
    }
}
