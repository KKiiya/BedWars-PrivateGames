package me.notlewx.pgames.listeners.arena;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.arena.OreGenerator;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.support.ProtocolLib;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
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
                            team.addTeamEffect(PotionEffectType.HEALTH_BOOST, 1, Integer.MAX_VALUE);
                        }
                        break;
                    case 3:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.HEALTH_BOOST, 2, Integer.MAX_VALUE);
                        }
                        break;
                }
                switch (playerData.getSpeedLevel(game.getOwnerOfPrivateArena(e.getArena().getArenaName()))) {
                    case 0:
                        break;
                    case 1:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 0, Integer.MAX_VALUE);
                        }
                        break;
                    case 2:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 1, Integer.MAX_VALUE);
                        }
                        break;
                    case 3:
                        for (ITeam team : e.getArena().getTeams()) {
                            team.addTeamEffect(PotionEffectType.SPEED, 2, Integer.MAX_VALUE);
                        }
                        break;
                }
            }, 35L);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onBlockBreak(BlockBreakEvent e) {
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()) == null) return;
        IArena arena = PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer());
        boolean isArenaPrivate = game.isArenaPrivate(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(e.getPlayer()).getArenaName());
        if (isArenaPrivate) {
            if (playerData.isAMBEnabled(game.getOwnerOfPrivateArena(arena.getArenaName()))) {
                if (arena.getStatus() == GameState.waiting) e.setCancelled(true);
                if (arena.getStatus() == GameState.starting) e.setCancelled(true);
                if (arena.getStatus() == GameState.restarting) e.setCancelled(true);
                if (e.getBlock().getType() == Material.ENDER_CHEST) e.setCancelled(true);
                if (e.getBlock().getType() == Material.CHEST) e.setCancelled(true);
                if (e.getBlock().getType() == Material.BED) e.setCancelled(true);
                if (arena.isReSpawning(e.getPlayer())) e.setCancelled(true);
                if (arena.isSpectator(e.getPlayer())) e.setCancelled(true);
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
                ProtocolLib.cancelMessageTo(e.getPlayer());
            }
        }
    }
    @EventHandler
    public static void onPlayerDeath(PlayerKillEvent e) {
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
        }
    }
}
