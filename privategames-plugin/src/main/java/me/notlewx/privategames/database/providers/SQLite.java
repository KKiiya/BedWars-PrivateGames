package me.notlewx.privategames.database.providers;

import com.andrei1058.bedwars.api.BedWars;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static me.notlewx.privategames.PrivateGames.support;

public class SQLite implements Database {
    private String s;
    Connection connection;

    public SQLite() {
        connect();
    }
    public void connect() {
        if (support == Support.BEDWARS1058) s = "bedwars";
        else if (support == Support.BEDWARS2023) s = "bedwars";

        Utility.info("&aConnecting to your database...");
        this.connection = getConnection();
        Utility.info("&aConnected successfully to your database!");

        Utility.info("&eCreating tables...");
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + s + "_private_games (`player` varchar(200) NOT NULL, `privateGameEnabled` boolean(11) NOT NULL,`oneHitOneKill` boolean(11) NOT NULL,`lowGravity` boolean(11) NOT NULL,`speed` boolean(11) NOT NULL,`bedInstaBreak` boolean(11) NOT NULL,`maxTeamUpgrades` boolean(11) NOT NULL,`allowMapBreak` boolean(11) NOT NULL,`noDiamonds` boolean(11) NOT NULL,`noEmeralds` boolean(11) NOT NULL,`respawnEventTime` int(11) NOT NULL,`healthBuffLevel` int(11) NOT NULL, `eventsTime` int(11) NOT NULL, autoStart boolean(11) NOT NULL, allowJoin boolean(11) NOT NULL, PRIMARY KEY (`player`));");
            statement.close();
            Utility.info("&aTables created successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        File dataFolder = null;
        if (support == Support.BEDWARS1058) {
             dataFolder = new File(Bukkit.getServicesManager().getRegistration(BedWars.class).getPlugin().getDataFolder() + "/Cache/", s + "_private_games.db");
        } else if (support == Support.BEDWARS2023) {
            dataFolder = new File(Bukkit.getServicesManager().getRegistration(com.tomkeuper.bedwars.api.BedWars.class).getPlugin().getDataFolder() + "/Cache/", s + "_private_games.db");
        }
        if (!dataFolder.exists())
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        try {
            if (connection != null && !connection.isClosed())
                return connection;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(Player player, String column) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("SELECT * FROM " + s + "_private_games WHERE player = '" + player.getUniqueId().toString() + "';");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setData(Player player, String column, String value) {
        try {
            Connection c = getConnection();
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

    public void createPlayerData(Player p) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM " + s + "_private_games WHERE player = '" + p.getUniqueId().toString() + "'");
            ResultSet rs = ps.executeQuery();
            String player = null;
            if (rs.next())
                player = rs.getString("player");
            if (player != null) return;
            else {
                connection = getConnection();
                ps = connection.prepareStatement("INSERT INTO " + s + "_private_games(player, privateGameEnabled, oneHitOneKill, lowGravity, speed, bedInstaBreak, maxTeamUpgrades, allowMapBreak, noDiamonds, noEmeralds, respawnEventTime, healthBuffLevel, eventsTime, autoStart, allowJoin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
