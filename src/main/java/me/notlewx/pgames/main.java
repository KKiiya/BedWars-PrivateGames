package me.notlewx.pgames;

import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.listeners.PlayerArenaJoin;
import me.notlewx.pgames.listeners.PlayerArenaLeave;
import me.notlewx.pgames.api.config.config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {
    public FileConfiguration config = getConfig();
    private static boolean bungee = false;
    private static main instance;
    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null &&
        Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            getLogger().severe("BedWars1058 Found! Hooking...");
            bungee = false;
            return;
        }
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            getLogger().severe("BedWarsProxy Found! Hooking...");
            bungee = true;
            return;
        }
        saveDefaultConfig();
        new pgames();

        getServer().getPluginManager().registerEvents(new config(), this);
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

    public static Plugin getInstance() {
        return instance;
    }

}
