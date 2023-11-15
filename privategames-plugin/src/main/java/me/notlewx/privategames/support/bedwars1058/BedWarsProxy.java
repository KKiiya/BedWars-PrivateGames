package me.notlewx.privategames.support.bedwars1058;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.commands.proxy.MainCommand;
import me.notlewx.privategames.config.MainConfig;
import me.notlewx.privategames.config.proxy.MessagesData;
import me.notlewx.privategames.database.providers.MySQL;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import static me.notlewx.privategames.PrivateGames.*;

public class BedWarsProxy {
    private Plugin pl;

    public BedWarsProxy(Plugin plugin) {
        this.pl = plugin;
        start();
    }

    private void start() {
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            Utility.info("&aLoading Private Games addon...");
            support = Support.BEDWARSPROXY;
            bwProxyConfig = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig();
            Utility.info("&eCreating main config...");
            mainConfig = new MainConfig(pl, "config", Bukkit.getWorldContainer().getPath() + "/plugins/BedWarsProxy/Addons/PrivateGames/");
            Utility.info("&aMain config created successfully!");
            Utility.info("&eLoading messages...");
            new MessagesData();
            registerCommands();
            loadDatabase();
        }
    }

    private void registerCommands() {
        Utility.info("&eRegistering commands...");
        PrivateGames.getPlugins().getCommand("pg").setExecutor(new MainCommand());
        Utility.info("&aCommands registered successfully!");
    }

    private void loadDatabase() {
        Utility.info("&eConnecting to database...");
        if (bwProxyConfig.getBoolean("database.enable")) {
            Utility.info("&eUsing &cMySQL &eas database provider...");
            database = new MySQL();
            Utility.info("&aYour database is ready!");
        } else {
            Utility.info("&ePlease, to use the plugin in PROXY MODE use a MySQL database...");
            Bukkit.getPluginManager().disablePlugin(pl);
        }
    }
}
