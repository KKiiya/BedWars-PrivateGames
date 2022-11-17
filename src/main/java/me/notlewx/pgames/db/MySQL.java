package me.notlewx.pgames.db;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.main;
import org.bukkit.Bukkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static me.notlewx.pgames.main.bwconfig;
import static me.notlewx.pgames.main.bwproxy;

public class MySQL {
    String host;
    String database;
    String user;
    String pass;
    int port;

    public HikariDataSource connect() {
        if (!bwproxy) {
            this.host = bwconfig.getString("database.host");
            this.database = bwconfig.getString("database.database");
            this.user = bwconfig.getString("database.user");
            this.pass = bwconfig.getString("database.pass");
            this.port = bwconfig.getInt("database.port");;
        }
        else {
            this.host = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.host");
            this.database = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.database");
            this.user = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.user");
            this.pass = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.pass");
            this.port = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getInt("database.port");;
        }
        HikariDataSource db = new HikariDataSource();
        db.setPoolName("PrivateGames-Pool");
        db.setConnectionTimeout(480000000L);
        db.setMaximumPoolSize(10);
        db.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        db.addDataSourceProperty("serverName", this.host);
        db.addDataSourceProperty("databaseName", this.database);
        db.addDataSourceProperty("port", this.port);
        db.addDataSourceProperty("user", this.user);
        db.addDataSourceProperty("password", this.pass);
        return db;
    }
    public void createTables() {
        try {
            Connection c = (main.plugin()).msql.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS bw1058_private_games(name varchar(200), oneHitOneKill boolean, lowGravity boolean, speed int, bedInstaBreak boolean, maxTeamUpgrades boolean, allowBreakMap boolean, noDiamonds boolean, noEmeralds boolean, respawnEventTime int, healthBuffLevel int, eventsTime int)");
                ps.executeUpdate();
                ps.close();
                if (c != null)
                    c.close();
            } catch (Throwable throwable) {
                if (c != null)
                    try {
                        c.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
