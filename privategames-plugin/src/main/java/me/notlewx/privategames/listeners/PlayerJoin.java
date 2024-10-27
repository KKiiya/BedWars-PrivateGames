package me.notlewx.privategames.listeners;

import com.andrei1058.bedwars.api.server.ServerType;
import com.andrei1058.bedwars.lobbysocket.LoadedUser;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.notlewx.privategames.PrivateGames.*;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e == null) return;

        Player p = e.getPlayer();
        if (p == null) return;
        IPrivatePlayer pp = api.getPrivatePlayer(p);

        Utility.debug("PlayerJoin: " + p.getName() + " has joined the server. Creating data...");
        try {
            database.createPlayerData(p);
        } catch (Exception ex) {
            Utility.info("Failed to create player data for " + p.getName() + "! This will not affect if the player has loaded data before.");
        }

        p.setHealth(20.0);
        p.setMaxHealth(20.0);
        p.setHealthScale(20.0);

        p.getActivePotionEffects().clear();

        switch (support) {
            case BEDWARS1058:
                if (api.getBedWars1058API().getServerType() == ServerType.BUNGEE) {
                    LoadedUser user = LoadedUser.getPreLoaded(p.getUniqueId());
                    if (user == null) {
                        pp.getPlayerSettings().setPrivateGameDisabled(false);
                    }
                }
            break;
            case BEDWARS2023:
                if (api.getBedWars2023API().getServerType() == com.tomkeuper.bedwars.api.server.ServerType.BUNGEE) {
                    com.tomkeuper.bedwars.connectionmanager.LoadedUser user = com.tomkeuper.bedwars.connectionmanager.LoadedUser.getPreLoaded(p.getUniqueId());

                    if (user == null) {
                        pp.getPlayerSettings().setPrivateGameDisabled(false);
                    }
                }
            default:
                break;
        }
    }
}
