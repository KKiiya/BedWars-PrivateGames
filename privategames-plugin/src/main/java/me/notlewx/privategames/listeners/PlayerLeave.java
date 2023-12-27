package me.notlewx.privategames.listeners;

import com.andrei1058.bedwars.api.server.ServerType;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.support;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        IPrivatePlayer pp = api.getPrivatePlayer(e.getPlayer());
        pp.clearRequests();
        Utility.debug("PlayerLeave: " + e.getPlayer().getName() + " has left the server.");
        if (!pp.hasPermission()) return;
        if (!pp.getPlayerSettings().isPrivateGameEnabled()) return;

        switch (support){
            case BEDWARS1058:
                if (api.getBedWars1058API().getServerType() == ServerType.MULTIARENA || api.getBedWars1058API().getServerType() == ServerType.SHARED) {
                    pp.getPlayerSettings().setPrivateGameDisabled(true);
                }
                break;
            case BEDWARS2023:
                if (api.getBedWars2023API().getServerType() == com.tomkeuper.bedwars.api.server.ServerType.MULTIARENA || api.getBedWars2023API().getServerType() == com.tomkeuper.bedwars.api.server.ServerType.SHARED) {
                    pp.getPlayerSettings().setPrivateGameDisabled(true);
                }
                break;
            default:
                break;
        }
    }
}
