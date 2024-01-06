package me.notlewx.privategames.support.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.server.ServerType;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.database.DatabaseType;
import me.notlewx.privategames.commands.bedwars1058.MainCommand;
import me.notlewx.privategames.config.MainConfig;
import me.notlewx.privategames.config.bedwars1058.MessagesData;
import me.notlewx.privategames.database.providers.MySQL;
import me.notlewx.privategames.database.providers.SQLite;
import me.notlewx.privategames.listeners.bedwars1058.*;
import me.notlewx.privategames.messaging.socket.ArenasSocket;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

import static me.notlewx.privategames.PrivateGames.*;

public class BedWars1058 {
    private final Plugin pl;

    public BedWars1058(Plugin plugin) {
        this.pl = plugin;
        start();
    }

    private void start() {
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            Utility.info("&aLoading Private Games addon...");

            support = Support.BEDWARS1058;
            bedWars1058API = pl.getServer().getServicesManager().getRegistration(BedWars.class).getProvider();
            bw1058config = bedWars1058API.getConfigs().getMainConfig();
            Utility.info("&eCreating main config...");
            mainConfig = new MainConfig(pl, "config", bedWars1058API.getAddonsPath().getPath() + File.separator + "PrivateGames");
            Utility.info("&aMain config created successfully!");
            Utility.info("&eLoading messages...");
            new MessagesData();
            Utility.info("&aMessages loaded successfully!");
            registerCommands();
            loadDatabase();
            loadListeners();
        }
    }

    private void loadDatabase() {
        Utility.info("&eConnecting to database...");
        if (bw1058config.getBoolean("database.enable")) {
            Utility.info("&eUsing &cMySQL &eas database provider...");
            database = new MySQL();
            databaseType = DatabaseType.MySQL;
        } else {
            Utility.info("&eUsing &cSQLite &eas database provider...");
            database = new SQLite();
            databaseType = DatabaseType.SQLite;
        }
        Utility.info("&aYour database is ready!");
    }

    private void loadListeners() {
        Utility.info("&eLoading listeners...");
        pl.getServer().getPluginManager().registerEvents(new ArenaJoin(), pl);
        pl.getServer().getPluginManager().registerEvents(new ArenaLeave(), pl);
        pl.getServer().getPluginManager().registerEvents(new PrivateArenaListener(), pl);
        pl.getServer().getPluginManager().registerEvents(new StatsListener(), pl);
        pl.getServer().getPluginManager().registerEvents(new ScoreboardListener(), pl);
        pl.getServer().getPluginManager().registerEvents(new CommandListener(), pl);
        if (api.getBedWars1058API().getServerType() == ServerType.BUNGEE) {
            for (String connection : mainConfig.getList("lobby-sockets")) {
                String[] split = connection.split(":");
                try {
                    new ArenasSocket().startConnection(split[0], Integer.parseInt(split[1]));
                } catch (Exception e) {
                    Utility.info("&cError while connecting to socket: " + e.getMessage());
                }
            }
        }
        Utility.info("&aListeners loaded successfully");
    }

    private void registerCommands() {
        Utility.info("&eRegistering commands...");
        PrivateGames.getPlugins().getCommand("pg").setExecutor(new MainCommand());
        Utility.info("&aCommands registered successfully!");
    }
}
