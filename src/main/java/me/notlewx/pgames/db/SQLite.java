package me.notlewx.pgames.db;

import com.andrei1058.bedwars.api.BedWars;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import me.notlewx.pgames.main;

public class SQLite {
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void load() {
        this.connection = getConnection();
        try {
            Statement s = this.connection.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS bw1058_private_games (`name` varchar(200) NOT NULL,`oneHitOneKill` boolean(11) NOT NULL,`lowGravity` boolean(11) NOT NULL,`speed` boolean(11) NOT NULL,`bedInstaBreak` boolean(11) NOT NULL,`maxTeamUpgrades` boolean(11) NOT NULL,`allowBreakMap` boolean(11) NOT NULL,`noDiamonds` boolean(11) NOT NULL,`noEmeralds` boolean(11) NOT NULL,`respawnEventTime` int(11) NOT NULL,`healthBuffLevel` int(11), `eventsTime` int(11) NOT NULL, NOT NULL,PRIMARY KEY (`name`));");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = getConnection();
        try {
            ps = this.connection.prepareStatement("SELECT * FROM bw1058_private_games WHERE name = ?");
            rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException e) {
            main.plugin().getLogger().log(Level.SEVERE, "Couldn't connect! Try restarting the server or connect to the developer (NotLew_x#9207.");
            throw new RuntimeException(e);
        }
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        File dataFolder = new File("" + Bukkit.getServicesManager().getRegistration(BedWars.class).getPlugin().getDataFolder() + "/Cache/", "bw1058_private_games.db");
        if (!dataFolder.exists())
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        try {
            if (this.connection != null && !this.connection.isClosed())
                return this.connection;
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return this.connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(String path, String type) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM bw1058_private_games WHERE name = '" + path + "';");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public String setData(String path, String type, String value) {
        try {
            Connection c = (main.plugin()).db.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement("UPDATE bw1058_private_games SET " + type + "=? WHERE name=?");
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
    public void createPlayerData(String path) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO bw1058_private_games(name, privateGameEnabled, playerInParty, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowBreakMap, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
