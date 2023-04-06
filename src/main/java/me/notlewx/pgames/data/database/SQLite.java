package me.notlewx.pgames.data.database;

import com.andrei1058.bedwars.api.BedWars;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.Bukkit;

public class SQLite {
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public void load() {
        this.connection = getConnection();
        try {
            Statement s = this.connection.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS bw1058_private_games (`player` varchar(200) NOT NULL, `privateGameEnabled` boolean(11) NOT NULL,`oneHitOneKill` boolean(11) NOT NULL,`lowGravity` boolean(11) NOT NULL,`speed` boolean(11) NOT NULL,`bedInstaBreak` boolean(11) NOT NULL,`maxTeamUpgrades` boolean(11) NOT NULL,`allowMapBreak` boolean(11) NOT NULL,`noDiamonds` boolean(11) NOT NULL,`noEmeralds` boolean(11) NOT NULL,`respawnEventTime` int(11) NOT NULL,`healthBuffLevel` int(11) NOT NULL, `eventsTime` int(11) NOT NULL, PRIMARY KEY (`player`));");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(String path, String type) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM bw1058_private_games WHERE player = '" + path + "';");
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
            Connection c = getConnection();
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
    public void createPlayerData(String path) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("INSERT INTO bw1058_private_games(player, privateGameEnabled, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowMapBreak, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, path);
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
