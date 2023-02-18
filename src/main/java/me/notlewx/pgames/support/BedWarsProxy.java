package me.notlewx.pgames.support;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.db.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import static me.notlewx.pgames.PrivateGames.bwproxydb;

public class BedWarsProxy extends JavaPlugin {
    public static FileConfiguration proxyconfig;
    public HikariDataSource db;
    public BedWarsProxy() {}
    public void start() {
        proxyconfig = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig();
        bwproxydb = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.enable");

        if (bwproxydb) {
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
}
