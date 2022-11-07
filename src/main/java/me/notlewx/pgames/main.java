package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.language.Messages;
import me.notlewx.pgames.commands.pgames;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.listeners.PlayerArenaJoin;
import me.notlewx.pgames.listeners.PlayerArenaLeave;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class main extends JavaPlugin {
    public FileConfiguration config = getConfig();
    private static boolean bungee = false;
    private static BedWars bedWars;
    private static MainConfig mainConfig;
    private static main instance;
    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        else {
            System.out.println("[BedWars1058-PrivateGames] BedWars1058 found! Hooking...");
        }
        new pgames();
        bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
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

    public static Plugin getInstance() {
        return instance;
    }

}
