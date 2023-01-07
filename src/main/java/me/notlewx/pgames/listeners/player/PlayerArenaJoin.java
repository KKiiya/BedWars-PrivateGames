package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;
import static me.notlewx.pgames.config.MainConfig.MATERIAL;
import static me.notlewx.pgames.main.*;
import static me.notlewx.pgames.listeners.party.PartiesPartyJoinAndLeave.*;
import static me.notlewx.pgames.listeners.party.DefaultPartyJoinAndLeave.*;

public class PlayerArenaJoin implements Listener {
    private static MySQL mySQL;
    private static SQLite sqLite;
    private static YamlConfiguration yml;
    @EventHandler
    public static void onPlayerJoin(PlayerJoinArenaEvent e) {
        String path = e.getPlayer().getName();
        boolean enabledPGames;
        if (!bwproxy) {
            if (usingdb) {
                enabledPGames = mySQL.getBooleanData(path, "privateGameEnabled");
            } else {
                enabledPGames = sqLite.getBooleanData(path, "privateGameEnabled");
            }
        }
        else {
            enabledPGames = mySQL.getBooleanData(path, "privateGameEnabled");
        }
        if (enabledPGames) {
            if (parties) {
                if (!members.contains(e.getPlayer().getUniqueId())) {
                    e.getArena().removePlayer(e.getPlayer(), true);
               }
                else {
                    e.getArena().addPlayer(e.getPlayer(), true);
                    UUID lead = party.getLeader();
                    Player p = Bukkit.getPlayer(lead);
                    ItemStack item = yml.getItemStack(yml.getString(MATERIAL));
                    item.setAmount(1);
                    String leader = Bukkit.getPlayer(lead).getName();
                    boolean isLeader = mySQL.getBooleanData(leader, "privateGameEnabled");
                    if (isLeader) {
                        p.getInventory().addItem(item);
                    }
                }
            }
            else {
                if (!membersdef.contains(e.getPlayer())) {
                    e.getArena().removePlayer(e.getPlayer(), true);
                }
                else {
                    e.getArena().addPlayer(e.getPlayer(), true);
                    Player p = defparty.getMembers(e.getPlayer()).get(1);
                    ItemStack item = yml.getItemStack(yml.getString(MATERIAL));
                    item.setAmount(1);
                    String leader = p.getName();
                    boolean isLeader = mySQL.getBooleanData(leader, "privateGameEnabled");
                    if (isLeader) {
                        p.getInventory().addItem(item);
                    }
                }
            }
        }
    }
}
