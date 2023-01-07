package me.notlewx.pgames.db;

import com.zaxxer.hikari.HikariDataSource;
import me.notlewx.pgames.main;
import org.bukkit.Bukkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static me.notlewx.pgames.main.bwconfig;
import static me.notlewx.pgames.main.bwproxy;

public class MySQL {
    String host;
    String database;
    String user;
    String pass;
    int port;
    HikariDataSource db;

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
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("CREATE TABLE IF NOT EXISTS bw1058_private_games(name varchar(200), privateGameEnabled boolean, playerInParty boolean, oneHitOneKill boolean, lowGravity boolean, speed int, bedInstaBreak boolean, maxTeamUpgrades boolean, allowBreakMap boolean, noDiamonds boolean, noEmeralds boolean, respawnEventTime int, healthBuffLevel int, eventsTime int)");
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
        Bukkit.getScheduler().runTaskAsynchronously(main.plugin(), () -> {
            try {
                Connection c = (main.plugin()).db.getConnection();
                try {
                    String sql = "INSERT INTO bw1058_private_games(name, privateGameEnabled, playerInParty, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowBreakMap, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1, path);
                    ps.setBoolean(2, false);
                    ps.setBoolean(3, false);
                    ps.setBoolean(4, false);
                    ps.setBoolean(5, false);
                    ps.setInt(6, 1);
                    ps.setBoolean(7, false);
                    ps.setBoolean(8, false);
                    ps.setBoolean(9, false);
                    ps.setBoolean(10, false);
                    ps.setBoolean(11, false);
                    ps.setInt(12, 1);
                    ps.setInt(13, 1);
                    ps.setInt(14, 1);
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
    public String setStringData(String path, String type, String string) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("UPDATE bw1058_private_games SET " + type + "=? WHERE name=?");
                ps.setString(1, string);
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
    public String setIntData(String path, String type, int amount) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("UPDATE bw1058_private_games SET " + type + "=? WHERE name=?");
                ps.setInt(1, amount);
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

    public String setBooleanData(String path, String type, boolean value) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("UPDATE bw1058_private_games SET " + type + "=? WHERE name=?");
                ps.setBoolean(1, value);
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
    public boolean getBooleanData(String path, String type) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("SELECT " + type + " FROM bw1058_private_games WHERE name=?");
                ps.setString(1, path);
                ps.setString(2, type);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    boolean str = rs.getBoolean(type);
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
        return false;
    }
    public String getStringData(String path, String type) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("SELECT " + type + " FROM bw1058_private_games WHERE name=?");
                ps.setString(1, path);
                ps.setString(2, type);
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

    public int getIntData(String path, String type) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("SELECT " + type + " FROM bw1058_private_games WHERE name=?");
                ps.setString(1, path);
                ps.setString(2, type);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int str = rs.getInt(type);
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
        return 0;
    }
}
