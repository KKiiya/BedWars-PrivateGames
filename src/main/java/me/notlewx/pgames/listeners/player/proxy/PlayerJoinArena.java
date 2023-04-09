package me.notlewx.pgames.listeners.player.proxy;

import com.andrei1058.bedwars.proxy.api.event.PlayerArenaJoinEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.interfaces.Party;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinArena implements Listener {
    private static final Party party = PrivateGames.getPartyUtil();
    @EventHandler
    public static void onArenaJoin(PlayerArenaJoinEvent e) {
        for (Player members : party.getPartyMembers(e.getPlayer())) {
            e.getArena().addPlayer(members, e.getPlayer().getName());
        }
        if (!(party.getPartyMembers(e.getPlayer()).contains(e.getPlayer()))) e.setCancelled(true);
    }
}
