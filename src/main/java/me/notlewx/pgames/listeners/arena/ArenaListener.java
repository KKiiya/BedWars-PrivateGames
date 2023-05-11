package me.notlewx.pgames.listeners.arena;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.gameplay.NextEventChangeEvent;
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent;
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.stats.PlayerStats;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.IPrivateSettings;
import me.notlewx.pgames.util.Modifiers;
import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArenaListener implements Listener {
    private static final IPrivateSettings playerData = PGamesAPI.getPlayerData();
    private static final IGame game = PrivateGames.getGameUtil();

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()) == null) return;
            boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getArenaName());
            if (isArenaPrivate) {
                if (playerData.isOHOKEnabled(game.getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getArenaName()))) {
                    if (!PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getPlayers().contains(game.getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getArenaName()))) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).isSpectator((Player) e.getDamager())) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.waiting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.starting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getStatus() == GameState.restarting) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).isReSpawning((Player) e.getEntity())) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).isReSpawning((Player) e.getDamager())) return;
                    if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer((Player) e.getDamager()).getTeam((Player) e.getDamager()).getMembers().contains((Player) e.getEntity())) return;
                    ((Player) e.getEntity()).setLastDamage(600.0);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerReSpawnEvent e) {
        if (!game.isArenaPrivate(e.getArena().getArenaName())) return;
        Player p = game.getOwnerOfPrivateArena(e.getArena().getArenaName());
        Player player = e.getPlayer();
        if (playerData.isLGEnabled(p)) {
            Utility.giveLongJump(player);
        }
        Utility.giveHealthBuff(player);
        Utility.giveSpeedLevel(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerSpawn(GameStateChangeEvent e) {
        Player p = game.getOwnerOfPrivateArena(e.getArena().getArenaName());
        if (e.getNewState() != GameState.playing) return;
        if (!game.isArenaPrivate(e.getArena().getArenaName())) return;

        if (playerData.isLGEnabled(p)) {
            e.getArena().getPlayers().forEach(Utility::giveLongJump);
        }
        e.getArena().getPlayers().forEach(Utility::giveHealthBuff);
        e.getArena().getPlayers().forEach(Utility::giveSpeedLevel);
        if (playerData.isNEEnabled(p)) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.EMERALD) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.EMERALD);
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_II");
        }
        if (playerData.isNDEnabled(p)) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.DIAMOND) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.DIAMOND);
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_II");
        }
        if (playerData.isMTUEnabled(p)) {
            (new Modifiers()).printConfigurationSection();
        }
        if (playerData.isAMBEnabled(p)) {
            if (e.getArena().getConfig().getBoolean("allow-map-break")) {
                e.getArena().getConfig().set("allow-map-break", true);
            }
        } else {
            if (e.getArena().getConfig().getBoolean("allow-map-break")) {
                e.getArena().getConfig().set("allow-map-break", false);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBedBreak(PlayerBedBreakEvent event) {
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
            PlayerStats stats = BedWars.getStatsManager().get(event.getPlayer().getUniqueId());
            if (stats.getBedsDestroyed() == 0) stats.setBedsDestroyed(stats.getBedsDestroyed());
            else stats.setBedsDestroyed(stats.getBedsDestroyed() - 1);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onGameEnd(GameEndEvent event) {
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
            for (UUID uuid : event.getWinners()) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null && player.isOnline()) {
                    PlayerStats stats = BedWars.getStatsManager().get(uuid);
                    if (stats.getWins() == 0) stats.setWins(stats.getWins());
                    else stats.setWins(stats.getWins() - 1);
                    IArena playerArena = Arena.getArenaByPlayer(player);
                    if (playerArena != null && playerArena.equals(event.getArena())) {
                        stats.setGamesPlayed(stats.getGamesPlayed() + 1);
                    }
                }
            }
            if (playerData.getHBLevel(game.getOwnerOfPrivateArena(event.getArena().getArenaName())) > 1) {
                event.getArena().getPlayers().forEach(p -> p.setMaxHealth(20.0));
                event.getArena().getPlayers().forEach(p -> p.setHealth(20.0));
                event.getArena().getPlayers().forEach(p -> p.setHealthScale(20.0));
            }
            if (event.getArena().getConfig().getBoolean("allow-map-break")) {
                event.getArena().getConfig().set("allow-map-break", false);
            }
            game.setArenaPrivate(event.getArena().getArenaName(), false);
            game.setPrivateArenaOwner(event.getArena().getArenaName(), null);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onArenaLeave(PlayerLeaveArenaEvent event) {
        Player player = event.getPlayer();
        ITeam team = event.getArena().getExTeam(player.getUniqueId());
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
            if (playerData.getHBLevel(game.getOwnerOfPrivateArena(event.getArena().getArenaName())) > 1) {
                event.getArena().getPlayers().forEach(p -> p.setMaxHealth(20.0));
                event.getArena().getPlayers().forEach(p -> p.setHealth(20.0));
                event.getArena().getPlayers().forEach(p -> p.setHealthScale(20.0));
            }
            if (team != null) {
                if (event.getArena().getStatus() != GameState.starting && event.getArena().getStatus() != GameState.waiting) {
                    PlayerStats playerStats = BedWars.getStatsManager().get(player.getUniqueId());
                    if (playerStats != null) {
                        Instant now = Instant.now();
                        playerStats.setLastPlay(now);
                        if (playerStats.getFirstPlay() == null) {
                            playerStats.setFirstPlay(now);
                        }

                        if (event.getArena().getStatus() == GameState.playing) {
                            Player damager;
                            ITeam killerTeam;
                            PlayerStats damagerStats;
                            if (team.isBedDestroyed()) {
                                if (event.getArena().isPlayer(player)) {
                                    if (playerStats.getFinalDeaths() == 0) playerStats.setFinalDeaths(playerStats.getFinalDeaths());
                                    else playerStats.setFinalDeaths(playerStats.getFinalDeaths() - 1);
                                    if (playerStats.getLosses() == 0) playerStats.setLosses(playerStats.getLosses());
                                    else playerStats.setLosses(playerStats.getLosses() - 1);
                                }

                                damager = event.getLastDamager();
                                killerTeam = event.getArena().getTeam(damager);
                                if (damager != null && event.getArena().isPlayer(damager) && killerTeam != null) {
                                    damagerStats = BedWars.getStatsManager().get(damager.getUniqueId());
                                    if (damagerStats.getFinalKills() == 0) damagerStats.setFinalKills(damagerStats.getFinalKills());
                                    else damagerStats.setFinalKills(damagerStats.getFinalKills() - 1);
                                    event.getArena().addPlayerKill(damager, true, player);
                                }
                            } else {
                                damager = event.getLastDamager();
                                killerTeam = event.getArena().getTeam(damager);
                                if (event.getLastDamager() != null && event.getArena().isPlayer(damager) && killerTeam != null) {
                                    if (playerStats.getDeaths() == 0) playerStats.setDeaths(playerStats.getDeaths());
                                    else playerStats.setDeaths(playerStats.getDeaths() - 1);
                                    event.getArena().addPlayerDeath(player);
                                    event.getArena().addPlayerKill(damager, false, player);
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
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {
        if (game.isArenaPrivate(e.getArena().getArenaName())) {
            if (e.getArena().getPlayers().size() <= 1) {
                if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    if (e.getArena().getConfig().getBoolean("allow-map-break")) {
                        e.getArena().getConfig().set("allow-map-break", false);
                        e.getArena().getConfig().save();
                    }
                }
                game.setArenaPrivate(e.getArena().getArenaName(), false);
                game.setPrivateArenaOwner(e.getArena().getArenaName(), null);
            }
        }
    }

    @EventHandler
    public void onNextEvent(NextEventChangeEvent e) {
        if (e.getArena() == null) return;
        if (!game.isArenaPrivate(e.getArena().getArenaName())) return;
        this.modifyEventTime((Arena) e.getArena());
    }

    @EventHandler
    public void onGameStart(GameStateChangeEvent e) {
        if (e.getArena() == null) return;
        if (!game.isArenaPrivate(e.getArena().getArenaName())) return;
        if (e.getNewState() != GameState.playing) return;
        this.modifyEventTime((Arena) e.getArena());
    }
    private void modifyEventTime(Arena arena) {
        Player player = game.getOwnerOfPrivateArena(arena.getArenaName());
        switch (playerData.getETLevel(player)) {
            case 0:
            case 2:
                break;
            case 1:
                arena.upgradeDiamondsCount = (int) (arena.upgradeDiamondsCount * 2.0);
                arena.upgradeEmeraldsCount = (int) (arena.upgradeEmeraldsCount * 2.0);
                break;
            case 3:
                arena.upgradeDiamondsCount = (int) (arena.upgradeDiamondsCount * 0.5);
                arena.upgradeEmeraldsCount = (int) (arena.upgradeEmeraldsCount * 0.5);
                break;
            case 4:
                arena.upgradeDiamondsCount = (int) (arena.upgradeDiamondsCount * 0.25);
                arena.upgradeEmeraldsCount = (int) (arena.upgradeEmeraldsCount * 0.25);
                break;
        }
    }

    @EventHandler
    public void onBedPunch(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Player player = e.getPlayer();
        if (!PGamesAPI.getBwApi().getArenaUtil().isPlaying(player)) return;
        IArena a = Arena.getArenaByPlayer(player);
        if (!game.isArenaPrivate(a.getArenaName())) return;
        if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(a.getArenaName())) && !e.getClickedBlock().getType().toString().contains("BED")) {
            a.addPlacedBlock(e.getClickedBlock());
        }
        if (playerData.isBIBEnabled(game.getOwnerOfPrivateArena(a.getArenaName())) && e.getClickedBlock().getType().toString().contains("BED") && this.getBedLocations(e.getClickedBlock().getLocation()).stream().noneMatch(l -> l.getBlock().getLocation().equals((Object)a.getTeam(player).getBed().getBlock().getLocation()))) {
            Bukkit.getPluginManager().callEvent(new BlockBreakEvent(e.getClickedBlock(), player));
            e.getClickedBlock().setType(Material.AIR);
        }
    }

    public List<Location> getBedLocations(Location loc) {
        List<Location> locationList = new ArrayList<>();
        if (!loc.getBlock().getType().toString().contains("BED")) return locationList;
        locationList.add(loc);
        if (loc.clone().add(0.0, 0.0, 1.0).getBlock().getType().toString().contains("BED")) {
            locationList.add(loc.clone().add(0.0, 0.0, 1.0));
        } else if (loc.clone().subtract(0.0, 0.0, 1.0).getBlock().getType().toString().contains("BED")) {
            locationList.add(loc.clone().subtract(0.0, 0.0, 1.0));
        } else if (loc.clone().add(1.0, 0.0, 0.0).getBlock().getType().toString().contains("BED")) {
            locationList.add(loc.clone().add(1.0, 0.0, 0.0));
        } else if (loc.clone().subtract(1.0, 0.0, 0.0).getBlock().getType().toString().contains("BED")) {
            locationList.add(loc.clone().subtract(1.0, 0.0, 0.0));
        }
        return locationList;
    }
}
