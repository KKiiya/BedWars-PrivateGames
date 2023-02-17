package me.notlewx.pgames.support;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.db.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import static me.notlewx.pgames.PrivateGames.bwproxydb;

public class BedWarsProxy extends JavaPlugin {
    public HikariDataSource db;
    public BedWarsProxy() {

    }
    public void start() {
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
        getLogger().info("This addon have been developed by NotLew_x#9207");
    }
}
