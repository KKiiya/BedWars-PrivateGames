package me.notlewx.pgames;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.support.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrivateGames extends JavaPlugin {
    public static boolean bwproxy;
    private static PrivateGames instance;
    public HikariDataSource db;
    public static boolean isDatabaseEnabled;

    @Override
    public void onEnable() {
        instance = this;
        // BedWars1058 / BedWarsProxy search
        if ((Bukkit.getPluginManager().getPlugin("BedWars1058")) == null && (Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null)) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Start();

        getLogger().info("This addon has been developed by Kiiya#9207");
    }

    @Override
    public void onDisable() {}

    public static PrivateGames plugin() {
        return getPlugin(PrivateGames.class);
    }
    public static void Start() {
        // BedWarsProxy hook
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            bwproxy = true;
            new BedWarsProxy().start();
        }
        // BedWars1058 hook
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            new BedWars1058().start();
        }
    }
    public void enableCommands() {
        new pgames();
    }
}
