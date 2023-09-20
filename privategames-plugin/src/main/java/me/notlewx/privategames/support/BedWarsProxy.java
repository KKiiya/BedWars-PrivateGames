package me.notlewx.privategames.support;

import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class BedWarsProxy {
    private Plugin pl;

    public BedWarsProxy(Plugin plugin) {
        this.pl = plugin;
        start();
    }

    private void start() {
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            registerCommands();
            loadDatabase();
        }
    }

    private void registerCommands() {
        Utility.info("&eRegistering commands...");
        Utility.info("&aCommands registered successfully!");
    }

    private void loadDatabase() {

    }
}
