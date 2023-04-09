package me.notlewx.pgames.listeners.arena;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArenaListener implements Listener {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    private static final IGame game = PrivateGames.getGameUtil();

    @EventHandler
    public static void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()));
            if (isArenaPrivate) {
                if (playerData.isOHOKEnabled(game.getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager())))) {
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()) == null) return;
                    if (!PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getPlayers().contains(game.getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager())))) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).isSpectator((Player) e.getDamager())) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.waiting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.starting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.restarting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).isReSpawning((Player) e.getEntity())) return;
                    ((Player) e.getEntity()).setHealth(0.5);
                }
            }
        }
    }

    @EventHandler
    public static void onPlayerSpawn(TeamAssignEvent e) {
        if (game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()))) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                if (playerData.isNDEnabled(game.getOwnerOfPrivateArena((e.getArena())))) {
                    for (IGenerator generator : e.getArena().getOreGenerators()) {
                        if (generator.getType() == GeneratorType.DIAMOND) {
                            generator.disable();
                        }
                    }
                }
                if (playerData.isNEEnabled(game.getOwnerOfPrivateArena(e.getArena()))) {
                    for (IGenerator generator : e.getArena().getOreGenerators()) {
                        if (generator.getType() == GeneratorType.EMERALD) {
                            generator.disable();
                        }
                    }
                }
                if (playerData.isLGEnabled(game.getOwnerOfPrivateArena(e.getArena()))) {
                    for (Player player : e.getArena().getPlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2, false, true), true);
                    }
                }
                if (playerData.isMTUEnabled(game.getOwnerOfPrivateArena(e.getArena()))) {
                    for (ITeam team : e.getArena().getTeams()) {
                        team.getTeamUpgradeTiers().put("upgrade-forge", 3);
                        team.getTeamUpgradeTiers().put("upgrade-miner", 2);
                        team.getTeamUpgradeTiers().put("upgrade-heal-pool", 1);
                    }
                }
                switch (playerData.getHBLevel(game.getOwnerOfPrivateArena(e.getArena()))) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1, false, false));
                        }
                        break;
                    case 3:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 2, false, false));
                        }
                        break;
                }
                switch (playerData.getSpeedLevel(game.getOwnerOfPrivateArena(e.getArena()))) {
                    case 0:
                        break;
                    case 1:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        }
                        break;
                    case 2:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
                        }
                        break;
                    case 3:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false));
                        }
                        break;
                }
            }, 35L);
        }
    }

    @EventHandler
    public static void onReSpawn(PlayerReSpawnEvent e) {
        boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()));
        if (isArenaPrivate) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                if (playerData.isLGEnabled(game.getOwnerOfPrivateArena(e.getArena()))) {
                    for (Player player : e.getArena().getPlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2, false, true), true);
                    }
                }
                switch (playerData.getHBLevel(game.getOwnerOfPrivateArena(e.getArena()))) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 1, false, false));
                        }
                        break;
                    case 3:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 2, false, false));
                        }
                        break;
                }
                switch (playerData.getSpeedLevel(game.getOwnerOfPrivateArena(e.getArena()))) {
                    case 0:
                        break;
                    case 1:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
                        }
                        break;
                    case 2:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
                        }
                        break;
                    case 3:
                        for (Player player : e.getArena().getPlayers()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false));
                        }
                        break;
                }
            }, 35L);
        }
    }
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
        IArena arena = PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer());
        boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()));
        if (isArenaPrivate) {
            if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(arena))) {
                if (arena.getStatus() == GameState.waiting) return;
                if (arena.getStatus() == GameState.starting) return;
                if (arena.getStatus() == GameState.restarting) return;
                if (e.getBlock().getType() == Material.ENDER_CHEST) return;
                if (e.getBlock().getType() == Material.CHEST) return;
                if (arena.isReSpawning(e.getPlayer())) return;
                if (arena.isSpectator(e.getPlayer())) return;
                for (IGenerator generator : arena.getOreGenerators()) {
                    if (generator.getLocation() == e.getBlock().getLocation()) {
                        e.setCancelled(true);
                    }
                }
                for (ITeam team : arena.getTeams()) {
                    for (IGenerator generator : team.getGenerators()) {
                        if (generator.getLocation() == e.getBlock().getLocation()) {
                            e.setCancelled(true);
                        }
                    }
                }
                e.setCancelled(false);
                PrivateGames.getVersionUtil().cancelMessageTo(e.getPlayer());
            }
        }
    }
    @EventHandler
    public static void onPlayerDeath(PlayerKillEvent e) {
        boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getVictim()));
        if (isArenaPrivate) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                switch (playerData.getRETLevel(game.getOwnerOfPrivateArena(e.getArena()))) {
                    case 0:
                    case 2:
                        break;
                    case 1:
                        e.getArena().startReSpawnSession(e.getVictim(), 1);
                        break;
                    case 3:
                        e.getArena().startReSpawnSession(e.getVictim(), 10);
                        break;
                }
            }, 1L);
        }
    }
}
