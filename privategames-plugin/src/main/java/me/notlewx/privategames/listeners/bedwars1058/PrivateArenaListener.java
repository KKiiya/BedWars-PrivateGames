package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.GeneratorType;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.gameplay.GeneratorUpgradeEvent;
import com.andrei1058.bedwars.api.events.gameplay.NextEventChangeEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.arena.OreGenerator;
import com.google.gson.JsonObject;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.GeneratorProperties;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;

public class PrivateArenaListener implements Listener {

    @EventHandler
    public void onGameStart(GameStateChangeEvent e) {
        if (e.getNewState() != GameState.playing) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName()).getPrivateArenaHost();


        for (Player p : e.getArena().getPlayers()) {
            List<String> message = Utility.getList(p, PRIVATE_GAME_ENABLED_MODIFIERS);
            List<String> finalMessage = new ArrayList<>();
            for (String m : message) {
                if (m.equals("{modifiers}")) {
                    if (pp.getPlayerSettings().isOneHitOneKillEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, ONE_HIT_ONE_KILL_MEANING)));
                    }
                    switch (pp.getPlayerSettings().getHealthBuffLevel()) {
                        case 0:
                        case 1:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, HEALTH_BUFF_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, NORMAL_HEALTH_MEANING)));
                            break;
                        case 2:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, HEALTH_BUFF_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, DOUBLE_HEALTH_MEANING)));
                            break;
                        case 3:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, HEALTH_BUFF_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, TRIPLE_HEALTH_MEANING)));
                            break;
                    }
                    if (pp.getPlayerSettings().isLowGravityEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, ONE_HIT_ONE_KILL_MEANING)));
                    }
                    switch (pp.getPlayerSettings().getSpeedLevel()) {
                        case 0:
                        case 2:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, SPEED_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, NO_SPEED_MEANING)));
                            break;
                        case 1:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, SPEED_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, SPEED_I_MEANING)));
                            break;
                        case 3:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, SPEED_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, SPEED_II_MEANING)));
                            break;
                        case 4:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, SPEED_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, SPEED_III_MEANING)));
                            break;
                    }
                    switch (pp.getPlayerSettings().getRespawnTimeLevel()) {
                        case 0:
                        case 2:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, RESPAWN_EVENT_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, RESPAWN_EVENT_TIME_II_MEANING)));
                            break;
                        case 1:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, RESPAWN_EVENT_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, RESPAWN_EVENT_TIME_I_MEANING)));
                            break;
                        case 3:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, RESPAWN_EVENT_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, RESPAWN_EVENT_TIME_III_MEANING)));
                            break;
                    }
                    switch (pp.getPlayerSettings().getEventsTimeLevel()) {
                        case 0:
                        case 2:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, EVENTS_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, EVENTS_TIME_NORMAL_MEANING)));
                            break;
                        case 1:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, EVENTS_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, EVENTS_TIME_SLOWER_MEANING)));
                            break;
                        case 3:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, EVENTS_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, EVENTS_TIME_FAST_MEANING)));
                            break;
                        case 4:
                            finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT)
                                    .replace("{modifier}", Utility.getMsg(p, EVENTS_TIME_MEANING))
                                    .replace("{selected}", Utility.getMsg(p, EVENTS_TIME_FASTER_MEANING)));
                            break;
                    }
                    if (pp.getPlayerSettings().isNoEmeraldsEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, NO_EMERALDS_MEANING)));
                    }
                    if (pp.getPlayerSettings().isNoDiamondsEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, NO_DIAMONDS_MEANING)));
                    }
                    if (pp.getPlayerSettings().isAllowMapBreakEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, ALLOW_MAP_BREAK_MEANING)));
                    }
                    if (pp.getPlayerSettings().isBedInstaBreakEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, BED_INSTA_BREAK_MEANING)));
                    }
                    if (pp.getPlayerSettings().isMaxTeamUpgradesEnabled()) {
                        finalMessage.add(Utility.getMsg(p, PRIVATE_GAME_MODIFIERS_FORMAT).replace("{modifier}", Utility.getMsg(p, MAX_TEAM_UPGRADES_MEANING)));
                    }
                } else {
                    finalMessage.add(m.replace("{player}", ((Player) pp.getPlayer()).getDisplayName()));
                }
            }
            for (String m : finalMessage) {
                p.sendMessage(m);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGeneratorUpgrade(GeneratorUpgradeEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getGenerator().getArena().getWorldName())) return;

        IPrivateArena a = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getGenerator().getArena().getWorldName());

        if (a == null) return;
        if (GeneratorProperties.getGeneratorProperties(a.getPrivateArenaHost()) == null) return;
        if (GeneratorProperties.getGeneratorProperties(a.getPrivateArenaHost()).getProperties(e.getGenerator()) == null) return;

        GeneratorProperties.Properties props = GeneratorProperties.getGeneratorProperties(a.getPrivateArenaHost()).getProperties(e.getGenerator());
        IGenerator gen = e.getGenerator();
        gen.setDelay(props.getDelay());
        gen.setAmount(props.getAmount());
        gen.setSpawnLimit(props.getSpawnLimit());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            IArena arena = api.getBedWars1058API().getArenaUtil().getArenaByPlayer((Player) e.getDamager());
            if (arena == null) {
                return;
            }
            IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByPlayer((Player) e.getDamager());

            if (privateArena == null) return;
            if (api.getPrivateArenaUtil().isArenaPrivate(arena.getWorldName())) {
                if (privateArena.getPrivateArenaHost().getPlayerSettings().isOneHitOneKillEnabled()) {
                    if (arena.getStatus() != GameState.playing) return;
                    if (arena.isSpectator((Player) e.getDamager())) return;
                    if (arena.isReSpawning((Player) e.getEntity())) return;
                    if (arena.isReSpawning((Player) e.getDamager())) return;
                    if (arena.getTeam((Player) e.getDamager()).getMembers().contains((Player) e.getEntity())) return;
                    e.setDamage(600.0D);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerReSpawnEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        IPrivatePlayer pp = api.getPrivatePlayer(e.getPlayer());

        Utility.giveLongJump(((Player) pp.getPlayer()));
        Utility.giveHealthBuff(((Player) pp.getPlayer()));
        Utility.giveSpeedLevel(((Player) pp.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerSpawn(GameStateChangeEvent e) {
        if (e.getNewState() != GameState.playing) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;

        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName()).getPrivateArenaHost();
        IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName());

        privateArena.getPlayers().forEach(p -> Utility.giveLongJump((Player) p));
        privateArena.getPlayers().forEach(p -> Utility.giveHealthBuff((Player) p));
        privateArena.getPlayers().forEach(p -> Utility.giveSpeedLevel((Player) p));

        if (pp.getPlayerSettings().isNoEmeraldsEnabled()) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.EMERALD) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.EMERALD);
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("EMERALD_GENERATOR_TIER_III");
        }

        if (pp.getPlayerSettings().isNoDiamondsEnabled()) {
            for (IGenerator gen : e.getArena().getOreGenerators()) {
                if (gen.getType() != GeneratorType.DIAMOND) continue;
                gen.destroyData();
            }
            e.getArena().getOreGenerators().removeIf(g -> g.getType() == GeneratorType.DIAMOND);
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_II");
            e.getArena().getNextEvents().remove("DIAMOND_GENERATOR_TIER_III");
        }

        if (pp.getPlayerSettings().isMaxTeamUpgradesEnabled()) {
            upgradeTeams(e.getArena());
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

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGameEnd(GameEndEvent e) {
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        IArena a = e.getArena();
        IPrivateArena privateArena = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());

        if (privateArena == null) return;
        privateArena.getPlayers().forEach(p -> ((Player) p).setMaxHealth(20.0));
        privateArena.getPlayers().forEach(p -> ((Player) p).setHealth(20.0));
        privateArena.getPlayers().forEach(p -> ((Player) p).setHealthScale(20.0));

        if (a.getConfig().getBoolean("allow-map-break")) {
            a.getConfig().set("allow-map-break", false);
        }
        privateArena.destroyData();

        JsonObject object = new JsonObject();
        object.addProperty("action", "privateArenaDeletion");
        object.addProperty("arenaIdentifier", e.getArena().getWorldName());

        MessagesUtil.sendMessage(object.toString());

        if (GeneratorProperties.getGeneratorProperties(privateArena.getPrivateArenaHost()) != null) {
            GeneratorProperties.removeGeneratorProperties(privateArena.getPrivateArenaHost());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerKillEvent e) {
        if (api.getBedWars1058API().getArenaUtil().getArenaByPlayer(e.getVictim()) == null) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        if (e.getArena().isSpectator(e.getVictim())) return;
        IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName());
        if (e.getCause().isFinalKill()) pa.removePlayer(e.getVictim());
        IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName()).getPrivateArenaHost();
        if (!pa.getPlayers().contains(e.getVictim())) return;
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

        if (api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) {
            p.setMaxHealth(20.0);
            p.setHealth(20.0);
            p.setHealthScale(20.0);
        }
    }

    @EventHandler
    public void onNextEvent(NextEventChangeEvent e) {
        if (e.getArena() == null) return;
        if (!api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) return;
        modifyEventTime((Arena) e.getArena());
    }

    @EventHandler
    public void onBedPunch(PlayerInteractEvent e) {
        try {
            if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;
            Player player = e.getPlayer();
            if (!api.getBedWars1058API().getArenaUtil().isPlaying(player)) return;
            IArena a = Arena.getArenaByPlayer(player);
            IPrivatePlayer pp = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName()).getPrivateArenaHost();
            if (api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName()) == null) return;
            if (!api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) return;

            if (pp.getPlayerSettings().isAllowMapBreakEnabled() && !e.getClickedBlock().getType().toString().contains("BED")) {
                a.addPlacedBlock(e.getClickedBlock());
            }
            if (pp.getPlayerSettings().isBedInstaBreakEnabled() && e.getClickedBlock().getType().toString().contains("BED") && this.getBedLocations(e.getClickedBlock().getLocation()).stream().noneMatch(l -> l.getBlock().getLocation().equals(a.getTeam(player).getBed().getBlock().getLocation()))) {
                Bukkit.getPluginManager().callEvent(new BlockBreakEvent(e.getClickedBlock(), player));
                e.getClickedBlock().setType(Material.AIR);
            }
        } catch (Exception ex) {
            // PREVENT A USELESS ERROR
        }
    }

    private void modifyEventTime(Arena arena) {
        IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(arena.getWorldName());
        if (pa == null) return;
        IPrivatePlayer pp = pa.getPrivateArenaHost();

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

    public void upgradeTeams(IArena a) {
        for (ITeam team : a.getTeams()) {
            ConfigManager upgradesConfig = api.getBedWars1058API().getConfigs().getUpgradesConfig();
            ConfigurationSection conf = upgradesConfig.getYml().getConfigurationSection(a.getGroup() + "-upgrades-settings");

            if (conf == null) {
                conf = api.getBedWars1058API().getConfigs().getUpgradesConfig().getYml().getConfigurationSection("default-upgrades-settings");
            }

            for (Object up : conf.getList("menu-content")) {
                String upgrade = ((String) up).split(",")[0];
                if (!upgrade.startsWith("upgrade-")) continue;
                Set<String> tiers = upgradesConfig.getYml().getConfigurationSection(upgrade).getKeys(false);
                for (String tier : tiers) {
                    int level = Integer.parseInt(tier.split("-")[1]);
                    if (level != tiers.size()) continue;
                    team.getTeamUpgradeTiers().put(upgrade, level-1);

                    for (String recieve : upgradesConfig.getYml().getStringList(upgrade + "." + tier + ".receive")) {
                        String[] r = recieve.split(":");
                        switch (r[0]) {
                            case "generator-edit":
                                switch (r[1].replace(" ", "").split(",")[0]) {
                                    case "iron":
                                        team.getGenerators().stream().filter(g -> g.getType() == GeneratorType.IRON).forEach(g -> {
                                            g.setDelay(Integer.parseInt(r[1].replace(" ", "").split(",")[1]));
                                            g.setAmount(Integer.parseInt(r[1].replace(" ", "").split(",")[2]));
                                            g.setSpawnLimit(Integer.parseInt(r[1].replace(" ", "").split(",")[3]));
                                        });
                                        break;
                                    case "gold":
                                        team.getGenerators().stream().filter(g -> g.getType() == GeneratorType.GOLD).forEach(g -> {
                                            g.setDelay(Integer.parseInt(r[1].replace(" ", "").split(",")[1]));
                                            g.setAmount(Integer.parseInt(r[1].replace(" ", "").split(",")[2]));
                                            g.setSpawnLimit(Integer.parseInt(r[1].replace(" ", "").split(",")[3]));
                                        });
                                        break;
                                    case "emerald":
                                        IGenerator g = getiGenerator(team, a, Material.EMERALD, r);
                                        team.getGenerators().add(g);
                                        break;
                                    case "diamond":
                                        IGenerator g2 = getiGenerator(team, a, Material.DIAMOND, r);
                                        team.getGenerators().add(g2);
                                        break;
                                }
                                break;
                            case "player-effect":
                                switch (r[1].replace(" ", "").split(",")[3]) {
                                    case "base":
                                        PotionEffectType type = PotionEffectType.getByName(r[1].replace(" ", "").split(",")[0]);
                                        team.addBaseEffect(type, Integer.parseInt(r[1].replace(" ", "").split(",")[1]), Integer.MAX_VALUE);
                                        break;
                                    case "team":
                                        PotionEffectType type2 = PotionEffectType.getByName(r[1].replace(" ", "").split(",")[0]);
                                        team.addTeamEffect(type2, Integer.parseInt(r[1].replace(" ", "").split(",")[1]), Integer.MAX_VALUE);
                                        break;
                                }
                                break;
                            case "enchant-item":
                                Enchantment ecnh =  Enchantment.getByName(r[1].split(",")[0].replace(" ", ""));
                                switch (r[1].split(",")[2]) {
                                    case "armor":
                                        team.addArmorEnchantment(ecnh, Integer.parseInt(r[1].split(",")[1]));
                                        break;
                                    case "sword":
                                        team.addSwordEnchantment(ecnh, Integer.parseInt(r[1].split(",")[1]));
                                        break;
                                }
                                break;
                            case "dragon":
                                team.setDragons(Integer.parseInt(r[1].replace(" ", "")));
                                break;
                        }
                    }
                }
            }
        }
    }

    private static @NotNull IGenerator getiGenerator(ITeam team, IArena a, Material emerald, String[] r) {
        IGenerator g = new OreGenerator(team.getGenerators().get(0).getLocation(), a, GeneratorType.CUSTOM, team);
        g.setOre(new ItemStack(emerald));
        g.setDelay(Integer.parseInt(r[1].replace(" ", "").split(",")[1]));
        g.setAmount(Integer.parseInt(r[1].replace(" ", "").split(",")[2]));
        g.setSpawnLimit(Integer.parseInt(r[1].replace(" ", "").split(",")[3]));
        return g;
    }
}
