package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import me.notlewx.pgames.listeners.DefaultPartyJoinAndLeave;
import me.notlewx.pgames.listeners.PartiesPartyJoinAndLeave;
import me.notlewx.pgames.listeners.PlayerArenaJoin;
import me.notlewx.pgames.listeners.PlayerArenaLeave;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class main extends JavaPlugin {
    private static BedWars bedWars;
    public static ConfigManager bwconfig;
    private static MainConfig mainConfig;
    public HikariDataSource msql;
    public HikariDataSource db;
    public static boolean bwproxy;
    public static boolean parties;
    private static main instance;
    public static boolean usingdb;
    @Override
    public void onEnable() {
        instance = this;
        bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
        usingdb = bwconfig.getBoolean("database.enable");

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
            getLogger().info("This addon have been developed by NotLew_x#9207");
        }
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            if (usingdb) {
                getLogger().severe("Connecting to MySQL");
                this.db = (new MySQL()).connect();
                (new MySQL()).createTables();
                getLogger().severe("Connected to database!");
            }
            else {
                getLogger().severe("Connection to SQLite");
                (new SQLite()).getConnection();
                getLogger().severe("Connected to database!");
            }
            getLogger().info("BedWars1058 found! Hooking...");
            getLogger().info("This addon have been developed by NotLew_x#9207");
        }
        if (getServer().getPluginManager().getPlugin("Parties") != null) {
            if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                parties = true;
                getLogger().severe("Using parties system of Parties");
            }
            else {
                parties = false;
                getLogger().severe("Using default parties system of BedWars1058");
            }
        }
        bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        mainConfig = new MainConfig(this, "config", bedWars.getAddonsPath().getPath()+ File.separator+"PrivateGames");
        mainConfig.reload();
        new MessagesData();
        if (parties) {
            getServer().getPluginManager().registerEvents(new PartiesPartyJoinAndLeave(), this);
        }
        getServer().getPluginManager().registerEvents(new PlayerArenaJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerArenaLeave(), this);
        new pgames();
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
