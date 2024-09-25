package me.notlewx.privategames.database.providers;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static me.notlewx.privategames.PrivateGames.isBedWarsServer;
import static me.notlewx.privategames.PrivateGames.support;

public class MySQL implements Database {
    private String host;
    private String database;
    private String user;
    private String pass;
    private int port;
    private HikariDataSource db;
    private boolean ssl = false;

    public MySQL() {
        Utility.info("&eConnecting...");
        connect();
        Utility.info("&aConnected successfully to your database!");
        Utility.info("&eCreating tables...");
        createTables();
        Utility.info("&aTables created successfully!");
    }

    public void connect() {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        if (!isBedWarsServer) {
            this.host = PrivateGames.mainConfig.getString("database.host");
            this.database = PrivateGames.mainConfig.getString("database.database");
            this.user = PrivateGames.mainConfig.getString("database.username");
            this.pass = PrivateGames.mainConfig.getString("database.password");
            this.port = PrivateGames.mainConfig.getInt("database.port");
            this.ssl = PrivateGames.mainConfig.getBoolean("database.ssl");
        } else {
            if (support == Support.BEDWARS1058) {
                this.host = PrivateGames.bw1058config.getString("database.host");
                this.database = PrivateGames.bw1058config.getString("database.database");
                this.user = PrivateGames.bw1058config.getString("database.user");
                this.pass = PrivateGames.bw1058config.getString("database.pass");
                this.port = PrivateGames.bw1058config.getInt("database.port");
                this.ssl = PrivateGames.bw1058config.getBoolean("database.ssl");
            } else if (support == Support.BEDWARSPROXY) {
                this.host = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.host");
                this.database = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.database");
                this.user = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.user");
                this.pass = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getString("database.pass");
                this.port = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getInt("database.port");
                this.ssl = Bukkit.getPluginManager().getPlugin("BedWarsProxy").getConfig().getBoolean("database.ssl");
            } else if (support == Support.BEDWARSPROXY2023) {
                this.host = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getString("database.host");
                this.database = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getString("database.database");
                this.user = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getString("database.user");
                this.pass = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getString("database.pass");
                this.port = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getInt("database.port");
                this.ssl = Bukkit.getPluginManager().getPlugin("BWProxy2023").getConfig().getBoolean("database.ssl");
            } else if (support == Support.BEDWARS2023) {
                this.host = PrivateGames.bw2023config.getString("database.host");
                this.database = PrivateGames.bw2023config.getString("database.database");
                this.user = PrivateGames.bw2023config.getString("database.user");
                this.pass = PrivateGames.bw2023config.getString("database.pass");
                this.port = PrivateGames.bw2023config.getInt("database.port");
                this.ssl = PrivateGames.bw2023config.getBoolean("database.ssl");
            }

            db = new HikariDataSource();
            db.setPoolName("PrivateGames-Pool");
            db.setMaximumPoolSize(10);

            if (version.contains("v1_8") || version.contains("v1_12")) db.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            else db.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");

            db.addDataSourceProperty("serverName", this.host);
            db.addDataSourceProperty("databaseName", this.database);
            db.addDataSourceProperty("port", this.port);
            db.addDataSourceProperty("user", this.user);
            db.addDataSourceProperty("password", this.pass);
            db.addDataSourceProperty("useSSL", this.ssl);
        }
    }

    public void createTables() {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS bedwars_private_games(player varchar(200), privateGameEnabled varchar(200), oneHitOneKill varchar(200), lowGravity varchar(200), speed int, bedInstaBreak varchar(200), maxTeamUpgrades varchar(200), allowMapBreak varchar(200), noDiamonds varchar(200), noEmeralds varchar(200), respawnEventTime int, healthBuffLevel int, eventsTime int, allowJoin varchar(200), autoStart varchar(200))");
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPlayerData(OfflinePlayer player) {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getInstance(), () -> {
            try {
                Connection c = db.getConnection();
                PreparedStatement check = c.prepareStatement("SELECT player FROM bedwars_private_games WHERE player=?");
                check.setString(1, player.getUniqueId().toString());
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    String str = rs.getString("player");
                    if (str != null) return;
                    c.close();
                }
                else {
                    String sql = "INSERT INTO bedwars_private_games(player, privateGameEnabled, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowMapBreak, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime, allowJoin, autoStart) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                    ps.setString(14, "true");
                    ps.setString(15, "true");
                    ps.executeUpdate();
                    ps.close();
                    c.close();
                }
            } catch(SQLException e){
                throw new RuntimeException(e);
            }
        });
    }

    public void setData(OfflinePlayer player, String column, String value) {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE bedwars_private_games SET " + column + "=? WHERE player=?");
            ps.setString(1, value);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(OfflinePlayer player, String column) {
        try {
            Connection c = db.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT " + column + " FROM bedwars_private_games WHERE player=?");
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
            throw new RuntimeException(e);
        }
        return null;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = db.getConnection();
        } catch (SQLException e) {
            try {
                connection = db.getConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return connection;
    }
}
