package me.notlewx.pgames.listeners.party;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.party.Party;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static me.notlewx.pgames.main.bwproxy;
import static me.notlewx.pgames.main.usingdb;

public class DefaultPartyJoinAndLeave implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    private static Party party;
    @EventHandler
    public static void onPartyJoin(PlayerJoinArenaEvent e) {
        Player player = e.getPlayer();
        String name = player.getName();
        if (party.partySize(player) >= 2) {
            if (!bwproxy) {
                if (usingdb) {
                    mySQL.setBooleanData(name, "playerInParty",true);
                }
                else {
                    sqLite.setBooleanData(name, "playerInParty",true);
                }
            }
            else {
                mySQL.setBooleanData(name, "playerInParty",true);
            }
        }
        else {
            if (!bwproxy) {
                if (usingdb) {
                    mySQL.setBooleanData(name, "playerInParty",false);
                }
                else {
                    sqLite.setBooleanData(name, "playerInParty",false);
                }
            }
            else {
                mySQL.setBooleanData(name, "playerInParty",false);
            }
        }
    }
}
