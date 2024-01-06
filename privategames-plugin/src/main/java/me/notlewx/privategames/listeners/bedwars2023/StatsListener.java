package me.notlewx.privategames.listeners.bedwars2023;

import com.tomkeuper.bedwars.BedWars;
import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.team.ITeam;
import com.tomkeuper.bedwars.api.events.player.PlayerKillEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.tomkeuper.bedwars.api.stats.IPlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.time.Instant;

import static me.notlewx.privategames.PrivateGames.api;

public class StatsListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerDeath(PlayerKillEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;

        IPlayerStats victimStats = BedWars.getStatsManager().get(e.getVictim().getUniqueId());

        if (e.getCause().isFinalKill()) {
            if (victimStats.getFinalDeaths() == 0) victimStats.setFinalDeaths(victimStats.getFinalDeaths());
            else victimStats.setFinalDeaths(victimStats.getFinalDeaths()-1);
            if (victimStats.getLosses() == 0) victimStats.setLosses(victimStats.getLosses());
            else victimStats.setLosses(victimStats.getLosses()-1);
            if (victimStats.getGamesPlayed() == 0) victimStats.setGamesPlayed(victimStats.getGamesPlayed());
            else victimStats.setLosses(victimStats.getLosses()-1);
            if (e.getKiller() != null) {
                IPlayerStats killerStats = BedWars.getStatsManager().get(e.getKiller().getUniqueId());
                if (killerStats.getFinalKills() == 0) killerStats.setFinalKills(killerStats.getFinalKills());
                else killerStats.setFinalKills(killerStats.getFinalKills() - 1);
            }
        } else {
            if (victimStats.getDeaths() == 0) victimStats.setDeaths(victimStats.getDeaths());
            else victimStats.setDeaths(victimStats.getDeaths() - 1);
            if (e.getKiller() != null) {
                IPlayerStats killerStats = BedWars.getStatsManager().get(e.getKiller().getUniqueId());
                if (killerStats.getFinalKills() == 0) killerStats.setKills(killerStats.getKills());
                else killerStats.setKills(killerStats.getKills() - 1);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        Player player = e.getPlayer();
        ITeam team = e.getArena().getExTeam(player.getUniqueId());
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        if (team != null) {
            if (e.getArena().getStatus() != GameState.starting && e.getArena().getStatus() != GameState.waiting) {
                IPlayerStats playerStats = BedWars.getStatsManager().get(player.getUniqueId());
                Instant now = Instant.now();
                playerStats.setLastPlay(now);
                if (playerStats.getFirstPlay() == null) {
                    playerStats.setFirstPlay(now);
                }

                if (e.getArena().getStatus() == GameState.playing) {
                    Player damager;
                    ITeam killerTeam;
                    IPlayerStats damagerStats;
                    if (team.isBedDestroyed()) {
                        if (e.getArena().isPlayer(player)) {
                            if (playerStats.getFinalDeaths() == 0) playerStats.setFinalDeaths(playerStats.getFinalDeaths());
                            else playerStats.setFinalDeaths(playerStats.getFinalDeaths() - 1);
                            if (playerStats.getLosses() == 0) playerStats.setLosses(playerStats.getLosses());
                            else playerStats.setLosses(playerStats.getLosses() - 1);
                        }

                        damager = e.getLastDamager();
                        killerTeam = e.getArena().getTeam(damager);
                        if (damager != null && e.getArena().isPlayer(damager) && killerTeam != null) {
                            damagerStats = BedWars.getStatsManager().get(damager.getUniqueId());
                            if (damagerStats.getFinalKills() == 0) damagerStats.setFinalKills(damagerStats.getFinalKills());
                            else damagerStats.setFinalKills(damagerStats.getFinalKills() - 1);
                            e.getArena().addPlayerKill(damager, true, player);
                        }
                    } else {
                        damager = e.getLastDamager();
                        killerTeam = e.getArena().getTeam(damager);
                        if (damager != null && e.getArena().isPlayer(damager) && killerTeam != null) {
                            if (playerStats.getDeaths() == 0) playerStats.setDeaths(playerStats.getDeaths());
                            else playerStats.setDeaths(playerStats.getDeaths() - 1);
                            e.getArena().addPlayerDeath(player);
                            e.getArena().addPlayerKill(damager, false, player);
                            damagerStats = BedWars.getStatsManager().get(damager.getUniqueId());
                            if (damagerStats.getKills() == 0) damagerStats.setKills(damagerStats.getKills());
                            else damagerStats.setKills(damagerStats.getKills() - 1);
                        }
                    }
                }
            }
        }
    }
}
