package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.google.gson.JsonObject;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.utils.MessagesUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.notlewx.privategames.PrivateGames.api;

public class ArenaLeave implements Listener {
    @EventHandler
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        IArena arena = e.getArena();
        if (arena == null) return;
        Player player = e.getPlayer();
        if (player == null) return;

        if (!api.getPrivateArenaUtil().isArenaPrivate(arena.getWorldName())) return;
        if (arena.isSpectator(player)) return;
        if (arena.getPlayers().size() > 1) {
            MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaUpdate", api.getPrivateArenaUtil().getPrivateArenaByIdentifier(arena.getWorldName())));
            return;
        }
        if (arena.getStatus() == GameState.playing || e.getArena().getStatus() == GameState.restarting) return;
        if (arena.getPlayers().size() <= 1) {
            IPrivateArena parena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(arena.getWorldName());
            JsonObject object = new JsonObject();
            object.addProperty("action", "privateArenaDeletion");
            object.addProperty("arenaIdentifier", arena.getWorldName());
            MessagesUtil.sendMessage(object.toString());

            if (parena.getPrivateArenaHost().getPlayerSettings().isAllowMapBreakEnabled()) {
                if (arena.isAllowMapBreak()) {
                    arena.setAllowMapBreak(false);
                }
            }
            parena.destroyData();

            api.getBedWars1058API().getArenaUtil().getArenas().add(arena);
        }
        player.setHealth(20);
        player.setHealthScale(20);
        player.setMaxHealth(20);
        player.getActivePotionEffects().clear();
    }
}
