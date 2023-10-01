package me.notlewx.privategames.database.providers;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static me.notlewx.privategames.PrivateGames.support;

public class MySQL implements Database {
    private static String s;
    String host;
    String database;
    String user;
    String pass;
    int port;
    HikariDataSource db;

    public MySQL() {
        Utility.info("&eConnecting...");
        connect();
        Utility.info("&aConnected successfully to your database!");
        Utility.info("&eCreating tables...");
        createTables();
        Utility.info("&aTables created successfully!");
    }

    public void connect() {
        if (support == Support.BEDWARS1058) {
            s = "bedwars";
            this.host = PrivateGames.bw1058config.getString("database.host");
            this.database = PrivateGames.bw1058config.getString("database.database");
            this.user = PrivateGames.bw1058config.getString("database.user");
            this.pass = PrivateGames.bw1058config.getString("database.pass");
            this.port = PrivateGames.bw1058config.getInt("database.port");
        }
        else if (support == Support.BEDWARSPROXY) {
            s = "bedwars";
            this.host = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.host");
            this.database = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.database");
            this.user = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.user");
            this.pass = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.pass");
            this.port = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getInt("database.port");
        } else if (support == Support.BEDWARS2023) {
            s = "bedwars";
            this.host = PrivateGames.bw2023config.getString("database.host");
            this.database = PrivateGames.bw2023config.getString("database.database");
            this.user = PrivateGames.bw2023config.getString("database.user");
            this.pass = PrivateGames.bw2023config.getString("database.pass");
            this.port = PrivateGames.bw2023config.getInt("database.port");
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
    }

    public void createTables() {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS " + s + "_private_games(player varchar(200), privateGameEnabled varchar(200), oneHitOneKill varchar(200), lowGravity varchar(200), speed int, bedInstaBreak varchar(200), maxTeamUpgrades varchar(200), allowMapBreak varchar(200), noDiamonds varchar(200), noEmeralds varchar(200), respawnEventTime int, healthBuffLevel int, eventsTime int)");
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPlayerData(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getPlugins(), () -> {
            try {
                Connection c = db.getConnection();
                PreparedStatement check = c.prepareStatement("SELECT player FROM " + s + "_private_games WHERE player=?");
                check.setString(1, player.getUniqueId().toString());
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    String str = rs.getString("player");
                    if (str != null) return;
                    c.close();
                }
                else {
                    String sql = "INSERT INTO " + s + "_private_games(player, privateGameEnabled, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowMapBreak, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1, player.getUniqueId().toString());
                    ps.setString(2, "false");
                    ps.setString(3, "false");
                    ps.setString(4, "false");
                    ps.setInt(5, 0);
                    ps.setString(6, "false");
                    ps.setString(7, "false");
                    ps.setString(8, "false");
                    ps.setString(9, "false");
                    ps.setString(10, "false");
                    ps.setInt(11, 0);
                    ps.setInt(12, 0);
                    ps.setInt(13, 0);
                    ps.executeUpdate();
                    ps.close();
                    c.close();
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        });
    }

    public void setData(Player player, String column, String value) {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE " + s + "_private_games SET " + column + "=? WHERE player=?");
            ps.setString(1, value);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(Player player, String column) {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT " + column + " FROM " + s + "_private_games WHERE player=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String str = rs.getString(column);
                c.close();
                return str;
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = db.getConnection();
        } catch (SQLException e) {
            try {
                connection = db.getConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }
}
