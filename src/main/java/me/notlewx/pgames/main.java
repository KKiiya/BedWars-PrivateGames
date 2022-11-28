package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.api.language.Messages;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import me.notlewx.pgames.listeners.PlayerArenaJoin;
import me.notlewx.pgames.listeners.PlayerArenaLeave;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class main extends JavaPlugin {
    private static BedWars bedWars;
    public static ConfigManager bwconfig;
    private static MainConfig mainConfig;
    public HikariDataSource msql;
    public HikariDataSource db;
    public static boolean bwproxy;
    private static main instance;
    public static boolean usingdb;
    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null) {
            if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null) {
                getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            bwproxy = true;
            if (Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.enable")) {
                    this.db = (new MySQL()).connect();
                    getLogger().severe("Connected to database!");
            }
            else {
                getLogger().severe("If you are using BedWarsProxy you need to enable the database");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            getLogger().info("BedWarsProxy found! Hooking...");
        }
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            if (usingdb) {
                getLogger().severe("Connecting to MySQL");
                this.db = (new MySQL()).connect();
                (new MySQL()).createTables();
                getLogger().severe("Connected to database!");
            }
            else {
                (new SQLite()).getConnection();
            }
            getLogger().info("BedWars1058 found! Hooking...");
        }
        new pgames();
        bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
        usingdb = bwconfig.getBoolean("database.enable");
        mainConfig = new MainConfig(this, "config", bedWars.getAddonsPath().getPath()+ File.separator+"PrivateGames");
        mainConfig.reload();
        new MessagesData();

        getServer().getPluginManager().registerEvents(new PlayerArenaJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerArenaLeave(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Object getPlugins() {
        return getPlugin(main.class);
    }
    public static main plugin() {main plugin = getPlugin(main.class); return plugin;}

    public static Plugin getInstance() {
        return instance;
    }

}
