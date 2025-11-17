package me.notlewx.privategames.listeners.bedwars2023;

import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.google.gson.JsonObject;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.utils.MessagesUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;

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
                if (arena.isAllowMapBreak()) arena.setAllowMapBreak(false);
            }
            if (!arena.getGroup().equalsIgnoreCase(parena.getDefaultGroup())) arena.setGroup(parena.getDefaultGroup());
            if (arena.getMaxInTeam() != parena.getDefaultMaxInTeam()) {
                try {
                    Field maxInTeamField = arena.getClass().getDeclaredField("maxInTeam");
                    maxInTeamField.setAccessible(true);
                    maxInTeamField.set(arena, parena.getDefaultMaxInTeam());
                } catch (Exception ex) {
                    throw new RuntimeException("Error while setting maxInTeam", ex);
                }
            }
            parena.destroyData();

            api.getBedWars2023API().getArenaUtil().getArenas().add(arena);
        }
        player.setHealth(20);
        player.setHealthScale(20);
        player.setMaxHealth(20);
        player.getActivePotionEffects().clear();
    }
}
