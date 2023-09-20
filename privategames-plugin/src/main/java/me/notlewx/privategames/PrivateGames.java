package me.notlewx.privategames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.listeners.InventoryListener;
import me.notlewx.privategames.listeners.PlayerJoin;
import me.notlewx.privategames.listeners.PlayerLeave;
import me.notlewx.privategames.support.BedWars1058;
import me.notlewx.privategames.support.BedWars2023;
import me.notlewx.privategames.support.Support;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrivateGames extends JavaPlugin {
    public static ConfigManager bw1058config;
    public static com.tomkeuper.bedwars.api.configuration.ConfigManager bw2023config;
    public static me.notlewx.privategames.config.ConfigManager mainConfig;
    public static Support support;
    public static BedWars bedWars1058API;
    public static com.tomkeuper.bedwars.api.BedWars bedWars2023API;
    public static Database database;
    public static API api;

    @Override
    public void onEnable() {
        api = new API();
        Bukkit.getServicesManager().register(me.notlewx.privategames.api.PrivateGames.class, api, this, ServicePriority.Highest);
        loadMainListeners();
        loadSupport();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PrivateGames getPlugins() {
        return PrivateGames.getPlugin(PrivateGames.class);
    }
    public static BedWars getBw1058Api() {
        return bedWars1058API;
    }
    public static com.tomkeuper.bedwars.api.BedWars getBw2023Api() {
        return bedWars2023API;
    }
    public static com.andrei1058.bedwars.proxy.api.BedWars getBwProxyApi() {
        return BedWarsProxy.getAPI();
    }
    private void loadSupport() {
        new BedWars1058(this);
        new BedWars2023(this);
        new me.notlewx.privategames.support.BedWarsProxy(this);
    }
    private void loadMainListeners() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
    }
}