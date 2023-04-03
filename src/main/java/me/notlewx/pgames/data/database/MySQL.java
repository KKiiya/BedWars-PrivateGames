package me.notlewx.pgames.data.database;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.PrivateGames;
import org.bukkit.Bukkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static me.notlewx.pgames.PrivateGames.bwproxy;

public class MySQL {
    String host;
    String database;
    String user;
    String pass;
    int port;
    HikariDataSource db;

    public HikariDataSource connect() {
        if (!bwproxy) {
            this.host = PrivateGames.bwconfig.getString("database.host");
            this.database = PrivateGames.bwconfig.getString("database.database");
            this.user = PrivateGames.bwconfig.getString("database.user");
            this.pass = PrivateGames.bwconfig.getString("database.pass");
            this.port = PrivateGames.bwconfig.getInt("database.port");;
        }
        else {
            this.host = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.host");
            this.database = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.database");
            this.user = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.user");
            this.pass = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.pass");
            this.port = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getInt("database.port");;
        }
        db = new HikariDataSource();
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
            Connection c = (PrivateGames.getPlugins()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS bw1058_private_games(player varchar(200), privateGameEnabled boolean, oneHitOneKill boolean, lowGravity boolean, speed int, bedInstaBreak boolean, maxTeamUpgrades boolean, allowMapBreak boolean, noDiamonds boolean, noEmeralds boolean, respawnEventTime int, healthBuffLevel int, eventsTime int)");
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
    public void createPlayerData(String path) {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            try {
                Connection c = (PrivateGames.getPlugins()).db.getConnection();
                try {
                    String sql = "INSERT INTO bw1058_private_games(player, privateGameEnabled, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowBreakMap, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1, path);
                    ps.setBoolean(2, false);
                    ps.setBoolean(3, false);
                    ps.setBoolean(4, false);
                    ps.setInt(5, 1);
                    ps.setBoolean(6, false);
                    ps.setBoolean(7, false);
                    ps.setBoolean(8, false);
                    ps.setBoolean(9, false);
                    ps.setBoolean(10, false);
                    ps.setInt(11, 1);
                    ps.setInt(12, 1);
                    ps.setInt(13, 1);
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
        });
    }
    public String setData(String path, String type, String value) {
        try {
            Connection c = (PrivateGames.getPlugins()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("UPDATE bw1058_private_games SET " + type + "=? WHERE player=?");
                ps.setString(1, value);
                ps.setString(2, path);
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
        return null;
    }

    public String getData(String path, String type) {
        try {
            Connection c = (PrivateGames.getPlugins()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("SELECT " + type + " FROM bw1058_private_games WHERE player=?");
                ps.setString(1, path);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String str = rs.getString(type);
                    if (c != null)
                        c.close();
                    return str;
                }
                rs.close();
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
            e.printStackTrace();
        }
        return null;
    }
}
