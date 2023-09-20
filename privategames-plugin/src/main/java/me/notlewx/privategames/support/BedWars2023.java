package me.notlewx.privategames.support;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.config.bedwars2023.MainConfig;
import me.notlewx.privategames.config.bedwars2023.MessagesData;
import me.notlewx.privategames.database.providers.MySQL;
import me.notlewx.privategames.database.providers.SQLite;
import me.notlewx.privategames.listeners.bedwars2023.*;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import java.io.File;
import static me.notlewx.privategames.PrivateGames.*;

public class BedWars2023 {
    private static Plugin pl;

    public BedWars2023(Plugin plugin) {
        pl = plugin;
        start();
    }

    private void start() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") != null) {
            support = Support.BEDWARS2023;
            bedWars2023API = pl.getServer().getServicesManager().getRegistration(com.tomkeuper.bedwars.api.BedWars.class).getProvider();
            bedWars2023API.getAddonsUtil().loadAddon(new BedWars2023.Addon());
            bw2023config = bedWars2023API.getConfigs().getMainConfig();
        }
    }

    private static void loadDatabase() {
        Utility.info("&eConnecting to database...");
        if (bw2023config.getBoolean("database.enable")) {
            Utility.info("&eUsing &cMySQL &eas database provider...");
            database = new MySQL();
        } else {
            Utility.info("&eUsing &cSQLite &eas database provider...");
            database = new SQLite();
        }
        Utility.info("&aYour database is ready!");
    }

    private static void loadListeners() {
        Utility.info("&eLoading listeners...");
        pl.getServer().getPluginManager().registerEvents(new ArenaJoin(), pl);
        pl.getServer().getPluginManager().registerEvents(new ArenaLeave(), pl);
        pl.getServer().getPluginManager().registerEvents(new PrivateArenaListener(), pl);
        pl.getServer().getPluginManager().registerEvents(new StatsListener(), pl);
        Utility.info("&eListeners loaded successfully");
    }

    private static void registerCommands() {
        Utility.info("&eRegistering commands...");

        Utility.info("&aCommands registered successfully!");
    }


    public static class Addon extends com.tomkeuper.bedwars.api.addon.Addon {

        @Override
        public String getAuthor() {
            return "Kiiya";
        }

        @Override
        public Plugin getPlugin() {
            return pl;
        }

        @Override
        public String getVersion() {
            return PrivateGames.getPlugins().getDescription().getVersion();
        }

        @Override
        public String getName() {
            return "PrivateGames";
        }

        @Override
        public String getDescription() {
            return PrivateGames.getPlugins().getDescription().getDescription();
        }

        @Override
        public void load() {
            Utility.info("&aLoading Private Games addon...");

            Utility.info("&eCreating main config...");
            mainConfig = new MainConfig(pl, "config", bedWars2023API.getAddonsPath().getPath() + File.separator + "PrivateGames");
            Utility.info("&aMain config created successfully!");
            Utility.info("&eLoading messages...");
            new MessagesData();
            Utility.info("&aMessages loaded successfully!");
            registerCommands();
            loadListeners();
            loadDatabase();

            Utility.info("&aPrivate Games Addon loaded successfully! Enjoy!");
        }

        @Override
        public void unload() {
            Utility.info("&aUnloading Private Games addon...");
            pl.getServer().getPluginManager().disablePlugin(pl);
            Utility.info("&aPrivate Games Addon unloaded successfully!");
        }
    }
}
