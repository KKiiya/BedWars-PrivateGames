package me.notlewx.pgames.support;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class BedWars1058 extends JavaPlugin {
    private static MainConfig mainConfig;
    private static BedWars bedWars;
    public static ConfigManager bwconfig;
    public HikariDataSource db;
    public static boolean bw1058db;

    public BedWars1058() {}
    public void start() {
        bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
        bw1058db = bwconfig.getBoolean("database.enable");

        if (bw1058db) {
            getLogger().severe("Connecting to MySQL");
            this.db = (new MySQL()).connect();
            (new MySQL()).createTables();
            getLogger().severe("Connected to database!");
        }
        else {
            getLogger().severe("Connecting to SQLite");
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
    public static void StartListeners() {

    }
}
