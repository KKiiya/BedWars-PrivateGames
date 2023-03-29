package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static me.notlewx.pgames.config.MainConfig.POSITION;

public class PlayerArenaJoin implements Listener {
    private static final PlayerData playerData = new PlayerData();
    @EventHandler
    public static void onPlayerJoin(PlayerJoinArenaEvent e) {
        Player player = e.getPlayer();
        if (playerData.isPlayerInParty(player) && playerData.isPrivateGameEnabled(player)) {
            player.getInventory().setHeldItemSlot(PrivateGames.getPlugins().getConfig().getInt(POSITION));
        }
    }
}
