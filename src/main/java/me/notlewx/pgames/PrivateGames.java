package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PrivateGames extends JavaPlugin {
    private static MainConfig mainConfig;
    private static BedWars bedWars;
    public static ConfigManager bwconfig;
    public static FileConfiguration proxyconfig;
    public static boolean bwproxy = false;
    private static PrivateGames instance;
    public HikariDataSource db;
    private static boolean isDatabaseEnabled;
    private static boolean isProxyDatabaseEnabled;

    @Override
    public void onEnable() {
        instance = this;
        // BedWars1058 / BedWarsProxy search
        if ((Bukkit.getPluginManager().getPlugin("BedWars1058")) == null && (Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null)) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        // BedWarsProxy hook
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            bwproxy = true;
            proxyconfig = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig();
            isProxyDatabaseEnabled = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.enable");

            if (isProxyDatabaseEnabled) {
                getLogger().severe("Connecting to MySQL");
                this.db = (new MySQL()).connect();
                (new MySQL()).createTables();
                getLogger().severe("Connected to database!");
            }
            else {
                getLogger().severe("If you are using BedWarsProxy you need to enable the database");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            getLogger().info("BedWarsProxy found! Hooking...");
            getLogger().info("Enabling listeners...");
            getLogger().info("Creating config files...");
        }


        // BedWars1058 hook
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
            isDatabaseEnabled = bwconfig.getBoolean("database.enable");

            if (isDatabaseEnabled) {
                getLogger().severe("Connecting to MySQL");
                this.db = (new MySQL()).connect();
                (new MySQL()).createTables();
                getLogger().severe("Connected to database!");
            }
            else {
                getLogger().severe("Connecting to SQLite");
                (new SQLite()).load();
                (new SQLite()).getConnection();
                getLogger().severe("Connected to database!");
            }
            getLogger().info("BedWars1058 found! Hooking...");
            getLogger().info("Enabling listeners...");

            getLogger().info("Creating config files...");

            bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
            mainConfig = new MainConfig(this, "config", bedWars.getAddonsPath().getPath() + File.separator + "PrivateGames");
            mainConfig.reload();
            new MessagesData();
        }


        getLogger().info("This addon has been developed by Kiiya#9207");
    }

    @Override
    public void onDisable() {}

    public static PrivateGames getPlugins() {
        return getPlugin(PrivateGames.class);
    }
    public static boolean isDatabaseEnabled() {
        return isDatabaseEnabled;
    }
    public static boolean isProxyDatabaseEnabled() {
        return isProxyDatabaseEnabled;
    }
    public static boolean isBwproxy() {
        return bwproxy;
    }
}
