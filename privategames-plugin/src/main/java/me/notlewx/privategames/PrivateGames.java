package me.notlewx.privategames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.database.DatabaseType;
import me.notlewx.privategames.config.MainConfig;
import me.notlewx.privategames.database.providers.MySQL;
import me.notlewx.privategames.listeners.*;
import me.notlewx.privategames.support.bedwars1058.BedWars1058;
import me.notlewx.privategames.support.bedwars2023.BedWars2023;
import me.notlewx.privategames.support.bedwars2023.BedWarsProxy2023;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.AdvancedPie;
import org.bstats.charts.DrilldownPie;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public final class PrivateGames extends JavaPlugin {
    public static ConfigManager bw1058config;
    public static com.tomkeuper.bedwars.api.configuration.ConfigManager bw2023config;
    public static me.notlewx.privategames.config.ConfigManager mainConfig;
    public static FileConfiguration bwProxyConfig;
    public static Support support;
    public static DatabaseType databaseType;
    public static BedWars bedWars1058API;
    public static com.tomkeuper.bedwars.api.BedWars bedWars2023API;
    public static boolean isBedWarsServer;
    public static Database database;
    public static API api;

    private Map<String, Integer> isEnabled(String path) {
        me.notlewx.privategames.config.ConfigManager mainConfig = PrivateGames.mainConfig;
        Map<String, Integer> map = new HashMap<>();
        map.put("enabled", mainConfig.getBoolean(path) ? 1 : 0);
        map.put("disabled", mainConfig.getBoolean(path) ? 0 : 1);
        return map;
    }

    @Override
    public void onEnable() {
        int pluginId = 19967;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new SimplePie("database_provider", () -> databaseType.toString()));
        metrics.addCustomChart(new SimplePie("bedwars_provider", () -> support.toString()));

        api = new API();
        Bukkit.getServicesManager().register(me.notlewx.privategames.api.PrivateGames.class, api, this, ServicePriority.Highest);
        loadMainListeners();
        loadSupport();

        metrics.addCustomChart(new DrilldownPie("features_enabled", () -> {
            HashMap<String, Map<String, Integer>> map = new HashMap<>();
            map.put("Speed", isEnabled("enabled-modifiers.speed"));
            map.put("One Hit One Kill", isEnabled("enabled-modifiers.one-hit-one-kill"));
            map.put("Health Buff", isEnabled("enabled-modifiers.health-buff"));
            map.put("Long Jump", isEnabled("enabled-modifiers.long-jump"));
            map.put("Bed Insta Break", isEnabled("enabled-modifiers.bed-insta-break"));
            map.put("Respawn Time", isEnabled("enabled-modifiers.respawn-time"));
            map.put("Events Time", isEnabled("enabled-modifiers.events-time"));
            map.put("Max Team Upgrades", isEnabled("enabled-modifiers.max-team-upgrades"));
            map.put("No Diamonds", isEnabled("enabled-modifiers.no-diamonds"));
            map.put("No Emeralds", isEnabled("enabled-modifiers.no-emeralds"));
            map.put("Gamemode Changer", isEnabled("enabled-modifiers.gamemode-changer"));
            map.put("Start Game", isEnabled("enabled-modifiers.start-game"));
            map.put("Options", isEnabled("enabled-modifiers.options"));
            return map;
        }));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PrivateGames getPlugins() {
        return PrivateGames.getPlugin(PrivateGames.class);
    }
    public static BedWars getBw1058Api() {
        return bedWars1058API;
    }
    public static com.tomkeuper.bedwars.api.BedWars getBw2023Api() {
        return bedWars2023API;
    }
    public static com.andrei1058.bedwars.proxy.api.BedWars getBwProxyApi() {
        return BedWarsProxy.getAPI();
    }
    public static com.tomkeuper.bedwars.proxy.api.BedWars getBwProxy2023Api() {
        return com.tomkeuper.bedwars.proxy.BedWarsProxy.getAPI();
    }
    private void loadSupport() {
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            isBedWarsServer = true;
            new BedWars1058(this);
        } else if (Bukkit.getPluginManager().getPlugin("BedWars2023") != null) {
            isBedWarsServer = true;
            new BedWars2023(this);
        } else if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            isBedWarsServer = true;
            new me.notlewx.privategames.support.bedwars1058.BedWarsProxy(this);
        } else if (Bukkit.getPluginManager().getPlugin("BWProxy2023") != null) {
            isBedWarsServer = true;
            new BedWarsProxy2023(this);
        } else {
            Utility.warn("No BedWars plugin was found! Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    private void loadMainListeners() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
        getServer().getPluginManager().registerEvents(new PartyDisband(), this);
    }
}