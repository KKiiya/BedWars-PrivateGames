package me.notlewx.privategames.support;

import com.tomkeuper.bedwars.api.addon.Addon;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.commands.bedwars2023.MainCommand;
import me.notlewx.privategames.config.bedwars2023.MainConfig;
import me.notlewx.privategames.config.bedwars2023.MessagesData;
import me.notlewx.privategames.database.providers.MySQL;
import me.notlewx.privategames.database.providers.SQLite;
import me.notlewx.privategames.listeners.bedwars2023.ArenaJoin;
import me.notlewx.privategames.listeners.bedwars2023.ArenaLeave;
import me.notlewx.privategames.listeners.bedwars2023.PrivateArenaListener;
import me.notlewx.privategames.listeners.bedwars2023.StatsListener;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.plugin.Plugin;
import java.io.File;
import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.PrivateGames.database;

public class BW2023Addon extends Addon {

    private static Plugin pl;

    public BW2023Addon() {
        pl = PrivateGames.getPlugins();
    }

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
        PrivateGames.getPlugins().getCommand("pg").setExecutor(new MainCommand());
        Utility.info("&aCommands registered successfully!");
    }
}

