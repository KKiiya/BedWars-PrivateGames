package me.notlewx.pgames.listeners.arena;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent;
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.OreGenerator;
import com.andrei1058.bedwars.stats.PlayerStats;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import java.time.Instant;
import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Collectors;

public class ArenaListener implements Listener {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    private static final IGame game = PrivateGames.getGameUtil();

    @EventHandler
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
                    ((Player) e.getEntity()).setHealth(0.5);
                }
            }
        }
    }

    @EventHandler
    public static void onPlayerSpawn(TeamAssignEvent e) {
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()) == null) return;
        if (game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getArenaName())) {
            if (playerData.isNEEnabled(e.getPlayer())) {
                for (IGenerator gen : e.getArena().getOreGenerators().stream().filter(g -> g.getType() == GeneratorType.EMERALD).collect(Collectors.toList())) {
                    gen.disable();
                }
            }
            if (playerData.isNDEnabled(e.getPlayer())) {
                for (IGenerator gen : e.getArena().getOreGenerators().stream().filter(g -> g.getType() == GeneratorType.DIAMOND).collect(Collectors.toList())) {
                    gen.disable();
                }
            }
            if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                if (!PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().getBoolean("allow-map-break")) {
                    PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().set("allow-map-break", true);
                    PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().save();
                }
            }
            else {
                if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().getBoolean("allow-map-break")) {
                    PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().set("allow-map-break", false);
                    PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getConfig().save();
                }
            }
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                switch (playerData.getETLevel(e.getPlayer())) {
                    case 0:
                    case 2:
                        break;
                    case 1:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
                if (playerData.isLGEnabled(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    for (ITeam team : e.getArena().getTeams()) {
                        team.addTeamEffect(PotionEffectType.JUMP, 2, Integer.MAX_VALUE);
                    }
                }
                if (playerData.isMTUEnabled(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    for (ITeam team : e.getArena().getTeams()) {
                        team.getTeamUpgradeTiers().put("upgrade-forge", 3);
                        team.getTeamUpgradeTiers().put("upgrade-miner", 1);
                        team.getTeamUpgradeTiers().put("upgrade-armor", 3);
                        team.getTeamUpgradeTiers().put("upgrade-swords", 0);
                        team.getTeamUpgradeTiers().put("upgrade-heal-pool", 0);
                        team.getTeamUpgradeTiers().put("upgrade-dragon", 0);
                        team.setDragons(2);
                        team.addTeamEffect(PotionEffectType.FAST_DIGGING, 1, Integer.MAX_VALUE);
                        team.addArmorEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        team.addSwordEnchantment(Enchantment.DAMAGE_ALL, 1);
                        team.addBaseEffect(PotionEffectType.REGENERATION, 0, Integer.MAX_VALUE);
                        for (IGenerator gen : team.getGenerators().stream().filter(g -> g.getType() == GeneratorType.IRON).collect(Collectors.toList())) {
                            gen.setDelay(1);
                            gen.setAmount(3);
                            gen.setSpawnLimit(64);
                        }
                        for (IGenerator gen : team.getGenerators().stream().filter(g -> g.getType() == GeneratorType.GOLD).collect(Collectors.toList())) {
                            gen.setDelay(2);
                            gen.setAmount(1);
                            gen.setSpawnLimit(32);
                        }
                        for (Location loc : e.getArena().getConfig().getArenaLocations("Team." +  team.getName() + ".Emerald")) {
                            OreGenerator gen = new OreGenerator(loc, e.getArena(), GeneratorType.CUSTOM, team);
                            gen.setOre(new ItemStack(Material.EMERALD));
                            gen.setType(GeneratorType.EMERALD);
                            gen.setAmount(1);
                            gen.setDelay(5);
                            gen.setSpawnLimit(12);
                        }
                    }
                }
                switch (playerData.getHBLevel(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.ABSORPTION, 4, Integer.MAX_VALUE);
                        }
                        break;
                    case 3:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.ABSORPTION, 9, Integer.MAX_VALUE);
                        }
                        break;
                }
                switch (playerData.getSpeedLevel(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 0, Integer.MAX_VALUE);
                        }
                        break;
                    case 3:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 1, Integer.MAX_VALUE);
                        }
                        break;
                    case 4 :
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 2, Integer.MAX_VALUE);
                        }
                        break;
                }
            }, 20L);
        }
    }
    @EventHandler
    public static void onPlayerDeath(PlayerKillEvent e) {
            PlayerStats victimStats = BedWars.getStatsManager().get(e.getVictim().getUniqueId());
            if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getVictim()) == null) return;
            boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getVictim()).getArenaName());
            if (isArenaPrivate) {
                switch (playerData.getRETLevel(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    case 0:
                    case 2:
                        break;
                    case 1:
                        e.getArena().startReSpawnSession(e.getVictim(), 2);
                        break;
                    case 3:
                        e.getArena().startReSpawnSession(e.getVictim(), 10);
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
                    victimStats.setDeaths(victimStats.getDeaths() - 1);
                    if (e.getKiller() != null) {
                        PlayerStats killerStats = BedWars.getStatsManager().get(e.getKiller().getUniqueId());
                        if (killerStats.getFinalKills() == 0) killerStats.setKills(killerStats.getKills());
                        else killerStats.setKills(killerStats.getKills() - 1);
                    }
                }
            }
    }
    @EventHandler
    public void onBedBreak(PlayerBedBreakEvent event) {
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
            PlayerStats stats = BedWars.getStatsManager().get(event.getPlayer().getUniqueId());
            if (stats.getBedsDestroyed() == 0) stats.setBedsDestroyed(stats.getBedsDestroyed());
            else stats.setBedsDestroyed(stats.getBedsDestroyed() - 1);
        }
    }
    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
            Iterator var2 = event.getWinners().iterator();

            while (var2.hasNext()) {
                UUID uuid = (UUID) var2.next();
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
            if (event.getArena().getConfig().getBoolean("allow-map-break")) {
                event.getArena().getConfig().set("allow-map-break", false);
                event.getArena().getConfig().save();
            }
            game.setArenaPrivate(event.getArena().getArenaName(), false);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onArenaLeave(PlayerLeaveArenaEvent event) {
        Player player = event.getPlayer();
        ITeam team = event.getArena().getExTeam(player.getUniqueId());
        boolean isArenaPrivate = game.isArenaPrivate(event.getArena().getArenaName());
        if (isArenaPrivate) {
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
                                    if (playerStats.getLosses() == 0) playerStats.setLosses(playerStats.getLosses() - 1);
                                    else playerStats.setLosses(playerStats.getLosses() - 1);
                                }

                                damager = event.getLastDamager();
                                killerTeam = event.getArena().getTeam(damager);
                                if (damager != null && event.getArena().isPlayer(damager) && killerTeam != null) {
                                    damagerStats = BedWars.getStatsManager().get(damager.getUniqueId());
                                    damagerStats.setFinalKills(damagerStats.getFinalKills() - 1);
                                    event.getArena().addPlayerKill(damager, true, player);
                                }
                            } else {
                                damager = event.getLastDamager();
                                killerTeam = event.getArena().getTeam(damager);
                                if (event.getLastDamager() != null && event.getArena().isPlayer(damager) && killerTeam != null) {
                                    playerStats.setDeaths(playerStats.getDeaths() - 1);
                                    event.getArena().addPlayerDeath(player);
                                    event.getArena().addPlayerKill(damager, false, player);
                                    damagerStats = BedWars.getStatsManager().get(damager.getUniqueId());
                                    damagerStats.setKills(damagerStats.getKills() - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public static void onPlayerLeave(PlayerLeaveArenaEvent e) {
        if (game.isArenaPrivate(e.getArena().getArenaName())) {
            if (e.getArena().getPlayers().size() <= 1) {
                if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    if (e.getArena().getConfig().getBoolean("allow-map-break")) {
                        e.getArena().getConfig().set("allow-map-break", false);
                        e.getArena().getConfig().save();
                    }
                }
            }
        }
    }
}
