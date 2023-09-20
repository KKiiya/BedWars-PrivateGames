package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.gameplay.NextEventChangeEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.stats.PlayerStats;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.Utility;
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

import java.util.ArrayList;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;

public class PrivateArenaListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            IPrivatePlayer pp = api.getPrivatePlayer((Player) e.getDamager());

            if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(pp.getPlayer()) == null) return;

            IArena arena = api.getBedWars1058API().getArenaUtil().getArenaByPlayer(pp.getPlayer());

            if (pp.getPlayerSettings().isOneHitOneKillEnabled()) {
                if (arena.isSpectator((Player) e.getDamager())) return;
                if (arena.getStatus() != GameState.playing) return;
                if (arena.isReSpawning((Player) e.getEntity())) return;
                if (arena.isReSpawning((Player) e.getDamager())) return;
                if (arena.getTeam((Player) e.getDamager()).getMembers().contains((Player) e.getEntity())) return;
                ((Player) e.getEntity()).setLastDamage(600.0);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerReSpawnEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) return;
        IPrivatePlayer pp = api.getPrivatePlayer(e.getPlayer());

        Utility.giveLongJump(pp.getPlayer());
        Utility.giveHealthBuff(pp.getPlayer());
        Utility.giveSpeedLevel(pp.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerSpawn(GameStateChangeEvent e) {
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByName(e.getArena().getDisplayName()).getPrivateArenaHost();
        IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByName(e.getArena().getArenaName());

        if (e.getNewState() != GameState.playing) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) return;

        privateArena.getPlayers().forEach(Utility::giveLongJump);
        privateArena.getPlayers().forEach(Utility::giveHealthBuff);
        privateArena.getPlayers().forEach(Utility::giveSpeedLevel);

        if (pp.getPlayerSettings().isNoEmeraldsEnabled()) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.EMERALD) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.EMERALD);
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_II");
        }

        if (pp.getPlayerSettings().isNoDiamondsEnabled()) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.DIAMOND) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.DIAMOND);
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_II");
        }

        if (pp.getPlayerSettings().isMaxTeamUpgradesEnabled()) {

        }

        if (pp.getPlayerSettings().isAllowMapBreakEnabled()) {
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
    public void onGameEnd(GameEndEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) return;
        IArena a = e.getArena();
        IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByName(a.getArenaName());

        privateArena.getPlayers().forEach(p -> p.setMaxHealth(20.0));
        privateArena.getPlayers().forEach(p -> p.setHealth(20.0));
        privateArena.getPlayers().forEach(p -> p.setHealthScale(20.0));

        if (a.getConfig().getBoolean("allow-map-break")) {
            a.getConfig().set("allow-map-break", false);
        }
        privateArena.destroyData();
    }

    @EventHandler
    public void onPlayerDeath(PlayerKillEvent e) {
        if (api.getBedWars1058API().getArenaUtil().getArenaByPlayer(e.getVictim()) == null) return;
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByName(e.getArena().getArenaName()).getPrivateArenaHost();
        switch (pp.getPlayerSettings().getRespawnTimeLevel()) {
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
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        Player p = e.getPlayer();

        if (api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) {
            p.setMaxHealth(20.0);
            p.setHealth(20.0);
            p.setHealthScale(20.0);

            IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByPlayer(p).getPrivateArenaHost();
            if (e.getArena().getPlayers().size() <= 1) {
                if (pp.getPlayerSettings().isAllowMapBreakEnabled()) {
                    if (e.getArena().getConfig().getBoolean("allow-map-break")) {
                        e.getArena().getConfig().set("allow-map-break", false);
                        e.getArena().getConfig().save();
                    }
                }
                api.getPrivateArenaUtil().getPrivateArenaByName(e.getArena().getArenaName()).destroyData();
            }
        }
    }

    @EventHandler
    public void onNextEvent(NextEventChangeEvent e) {
        if (e.getArena() == null) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getArenaName())) return;
        modifyEventTime((Arena) e.getArena());
    }

    @EventHandler
    public void onBedPunch(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Player player = e.getPlayer();
        if (!api.getBedWars1058API().getArenaUtil().isPlaying(player)) return;
        IArena a = Arena.getArenaByPlayer(player);
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByName(a.getArenaName()).getPrivateArenaHost();
        if (!api.getPrivateArenaUtil().isArenaPrivate(a.getArenaName())) return;
        if (pp.getPlayerSettings().isAllowMapBreakEnabled() && !e.getClickedBlock().getType().toString().contains("BED")) {
            a.addPlacedBlock(e.getClickedBlock());
        }
        if (pp.getPlayerSettings().isBedInstaBreakEnabled() && e.getClickedBlock().getType().toString().contains("BED") && this.getBedLocations(e.getClickedBlock().getLocation()).stream().noneMatch(l -> l.getBlock().getLocation().equals((Object)a.getTeam(player).getBed().getBlock().getLocation()))) {
            Bukkit.getPluginManager().callEvent(new BlockBreakEvent(e.getClickedBlock(), player));
            e.getClickedBlock().setType(Material.AIR);
        }
    }

    private void modifyEventTime(Arena arena) {
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByName(arena.getArenaName()).getPrivateArenaHost();
        switch (pp.getPlayerSettings().getEventsTimeLevel()) {
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
