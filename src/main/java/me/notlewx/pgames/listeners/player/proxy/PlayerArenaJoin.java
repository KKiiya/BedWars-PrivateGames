package me.notlewx.pgames.listeners.player.proxy;

import com.andrei1058.bedwars.proxy.api.BedWars;
import com.andrei1058.bedwars.proxy.api.event.PlayerArenaJoinEvent;
import com.andrei1058.bedwars.proxy.party.Internal;
import com.andrei1058.bedwars.proxy.party.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerArenaJoin implements Listener {
    private static final Party party = new Internal();
    @EventHandler
    public static void onArenaJoin(PlayerArenaJoinEvent e) {
        for (UUID uuid : party.getMembers(e.getPlayer().getUniqueId())) {
            Player members = Bukkit.getPlayer(uuid);
            e.getArena().addPlayer(members, e.getPlayer().getName());
        }
        if (!(party.getMembers(e.getPlayer().getUniqueId()).contains(e.getPlayer().getUniqueId()))) e.setCancelled(true);
    }
}
