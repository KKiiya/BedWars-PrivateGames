package me.notlewx.pgames.listeners.arena;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.stats.PlayerStats;
import me.notlewx.pgames.api.PGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class StatsListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerDeath(PlayerKillEvent e) {
        PlayerStats victimStats = BedWars.getStatsManager().get(e.getVictim().getUniqueId());
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getVictim()) == null) return;
        boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getVictim()).getArenaName());
            switch (playerData.getRETLevel(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                case 0:
                case 2:
                    break;
                case 1:
                    e.getArena().getRespawnSessions().put(e.getVictim(), 1);
                    break;
                case 3:
                    e.getArena().getRespawnSessions().put(e.getVictim(), 10);
                    break;
            }
			if (e.getCause().isFinalKill()) {
                if (victimStats.getFinalDeaths() == 0) victimStats.setFinalDeaths(victimStats.getFinalDeaths());
                else victimStats.setFinalDeaths(victimStats.getFinalDeaths()-1);
                if (victimStats.getLosses() == 0) victimStats.setLosses(victimStats.getLosses());
                else victimStats.setLosses(victimStats.getLosses()-1);
                if (victimStats.getGamesPlayed() == 0) victimStats.setGamesPlayed(victimStats.getGamesPlayed());
                else victimStats.setLosses(victimStats.getLosses()-1);
                if (e.getKiller() != null) {
                    PlayerStats killerStats = BedWars.getStatsManager().get(e.getKiller().getUniqueId());
                    if (killerStats.getFinalKills() == 0) killerStats.setFinalKills(killerStats.getFinalKills());
                    else killerStats.setFinalKills(killerStats.getFinalKills() - 1);
                }
            } else {
                if (victimStats.getDeaths() == 0) victimStats.setDeaths(victimStats.getDeaths());
                else victimStats.setDeaths(victimStats.getDeaths() - 1);
                if (e.getKiller() != null) {
                    PlayerStats killerStats = BedWars.getStatsManager().get(e.getKiller().getUniqueId());
                    if (killerStats.getFinalKills() == 0) killerStats.setKills(killerStats.getKills());
                    else killerStats.setKills(killerStats.getKills() - 1);
                }
            }
    }
}
