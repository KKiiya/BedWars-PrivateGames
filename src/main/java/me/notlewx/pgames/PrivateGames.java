package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.support.BedWars1058;
import me.notlewx.pgames.support.BedWarsProxy;
import me.notlewx.pgames.support.PartySystem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrivateGames extends JavaPlugin {
    public static ConfigManager bwconfig;
    public static FileConfiguration proxyconfig;
    public static boolean bwproxy;
    private static PrivateGames instance;
    public static boolean bw1058db;
    public static boolean bwproxydb;
    @Override
    public void onEnable() {
        instance = this;
        bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
        proxyconfig = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig();
        bw1058db = bwconfig.getBoolean("database.enable");
        bwproxydb = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.enable");

        // BedWars1058 / BedWarsProxy search
        if ((Bukkit.getPluginManager().getPlugin("BedWars1058")) == null && (Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null)) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // BedWarsProxy hook
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            bwproxy = true;
            new BedWarsProxy().start();
        }
        // BedWars1058 hook
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            new BedWars1058().start();
        }
        new PartySystem().start();

        new pgames();
    }

    @Override
    public void onDisable() {}

    public static Object getPlugins() {
        return getPlugin(PrivateGames.class);
    }
    public static Plugin getInstance() {
        return instance;
    }
    public static PrivateGames plugin() {
        return getPlugin(PrivateGames.class);
    }
    public void enableCommands() {
        new pgames();
    }
}
