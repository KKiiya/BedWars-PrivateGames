package me.notlewx.pgames;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.Party;
import me.notlewx.pgames.commands.MainCommand;
import me.notlewx.pgames.config.MainConfig;
import me.notlewx.pgames.config.MessagesData;
import me.notlewx.pgames.data.Game;
import me.notlewx.pgames.data.PlayerData;
import me.notlewx.pgames.data.ProxyParty;
import me.notlewx.pgames.data.database.MySQL;
import me.notlewx.pgames.data.database.SQLite;
import me.notlewx.pgames.listeners.arena.ArenaListener;
import me.notlewx.pgames.listeners.player.InteractionEvent;
import me.notlewx.pgames.listeners.player.PlayerJoin;
import me.notlewx.pgames.listeners.player.bedwars.PlayerArenaJoin;
import me.notlewx.pgames.listeners.player.bedwars.PlayerArenaLeave;
import me.notlewx.pgames.support.IVersion;
import me.notlewx.pgames.support.version.*;
import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Arrays;

public final class PrivateGames extends JavaPlugin {
    private static IVersion version;
    public static MainConfig mainConfig;
    private static BedWars bedWars;
    public static ConfigManager bwconfig;
    private static Party party;
    public static FileConfiguration proxyconfig;
    public static PlayerData pd;
    private static IGame game;
    public HikariDataSource db;
    public static boolean bwproxy = false;
    private static boolean isDatabaseEnabled;
    private static boolean isProxyDatabaseEnabled;

    @Override
    public void onEnable() {
        String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        switch (ver) {
            case "v1_8_R3" :
                version = new v1_8_R3();
                break;
            case "v1_12_R1":
                version = new v1_12_R1();
                break;
            case "v1_16_R3":
                version = new v1_16_R3();
                break;
            case "v1_17_R1":
                version = new v1_17_R1();
                break;
            case "v1_18_R2" :
                version = new v1_18_R2();
                break;
            case "v1_19_R2":
                version = new v1_19_R2();
                break;
            default:
                getPlugins().getLogger().severe("I can't run on your server version (" + ver + "). DISABLING!");
                Bukkit.getPluginManager().disablePlugin(this);
                break;
        }
        pd = new PlayerData();
        // BedWars1058 / BedWarsProxy search
        if ((Bukkit.getPluginManager().getPlugin("BedWars1058")) == null && (Bukkit.getPluginManager().getPlugin("BedWarsProxy") == null)) {
            getLogger().severe("BedWars1058 or BedWarsProxy was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        // BedWarsProxy hook
        if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            bwproxy = true;
            proxyconfig = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig();
            isProxyDatabaseEnabled = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.enable");

            if (isProxyDatabaseEnabled) {
                getLogger().info(Utility.colorizedString("&aUsing MySQL"));
                getLogger().info(Utility.colorizedString("&eConnecting..."));
                try {
                    this.db = (new MySQL()).connect();
                    getLogger().info(Utility.colorizedString("&eCreating tables..."));
                    try {
                        (new MySQL()).createTables();
                    } catch (Throwable throwable) {
                        getLogger().severe(Utility.colorizedString("&cCouldn't create tables! Restart the server or check your connection"));
                        throwable.printStackTrace();
                    }
                } catch (Throwable throwable) {
                    getLogger().severe(Utility.colorizedString("&cMySQL connection failed! Check your config or try restarting the server"));
                }
            }
            else {
                getLogger().severe("If you are using BedWarsProxy you need to enable the database");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            getLogger().info("BedWarsProxy found! Hooking...");
            getLogger().info("Enabling listeners...");
            getLogger().info("Creating config files...");
        }


        // BedWars1058 hook
        else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            bwconfig = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider().getConfigs().getMainConfig();
            isDatabaseEnabled = bwconfig.getBoolean("database.enable");
            game = new Game();

            if (isDatabaseEnabled) {
                getLogger().info(Utility.colorizedString("&aUsing MySQL"));
                getLogger().info(Utility.colorizedString("&eConnecting..."));
                try {
                    this.db = (new MySQL()).connect();
                    getLogger().info(Utility.colorizedString("&eCreating tables..."));
                    try {
                        (new MySQL()).createTables();
                    } catch (Throwable throwable) {
                        getLogger().severe(Utility.colorizedString("&cCouldn't create tables! Restart the server or check your connection"));
                        throwable.printStackTrace();
                    }
                } catch (Throwable throwable) {
                    getLogger().severe(Utility.colorizedString("&cMySQL connection failed! Check your config or try restarting the server"));
                }
            }
            else {
                getLogger().info(Utility.colorizedString("&aUsing SQLite"));
                getLogger().info(Utility.colorizedString("&eConnecting..."));
                try {
                    (new SQLite()).getConnection();
                    getLogger().info(Utility.colorizedString("&aConnected successfully!"));
                    (new SQLite()).load();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            getLogger().info("BedWars1058 found! Hooking...");
            getLogger().info("Enabling listeners...");
            getLogger().info("Creating config files...");

            getCommand("pg").setExecutor(new MainCommand());
            getCommand("pg").setAliases(Arrays.asList("privategame", "private", "pgame"));
            bedWars = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
            mainConfig = new MainConfig(this, "config", bedWars.getAddonsPath().getPath() + File.separator + "PrivateGames");
            mainConfig.reload();
            getServer().getPluginManager().registerEvents(new PlayerArenaJoin(), this);
            getServer().getPluginManager().registerEvents(new PlayerArenaLeave(), this);
            getServer().getPluginManager().registerEvents(new ArenaListener(), this);
            new MessagesData();
        }

        getServer().getPluginManager().registerEvents(new InteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getLogger().info("Running on: " + ver);
        getLogger().info("This addon has been developed by Kiiya#9207");
    }

    @Override
    public void onDisable() {}

    public static PrivateGames getPlugins() {
        return getPlugin(PrivateGames.class);
    }
    public static boolean isDatabaseEnabled() {
        return isDatabaseEnabled;
    }
    public static boolean isProxyDatabaseEnabled() {
        return isProxyDatabaseEnabled;
    }
    public static boolean isBwproxy() {
        return bwproxy;
    }
    public static PlayerData getPlayerData() {
        return pd;
    }
    public static Party getPartyUtil() {
        if (isBwproxy()) {
            party = new ProxyParty();
        } else {
            party = new me.notlewx.pgames.data.Party();
        }
        return party;
    }
    public static IVersion getVersionUtil() {
        return version;
    }
    public static IGame getGameUtil() {
        return game;
    }
}
