package me.notlewx.pgames.support;

import com.andrei1058.bedwars.api.BedWars;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import static me.notlewx.pgames.PrivateGames.bw1058db;

public class BedWars1058 extends JavaPlugin {
    public HikariDataSource db;
    private static MainConfig mainConfig;
    private static BedWars bedWars;
    public BedWars1058() {

    }
    public void start() {
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
        getLogger().info("This addon has been developed by NotLew_x#9207");

        bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        mainConfig = new MainConfig(this, "config", bedWars.getAddonsPath().getPath() + File.separator + "PrivateGames");
        mainConfig.reload();
        new MessagesData();
    }
}
