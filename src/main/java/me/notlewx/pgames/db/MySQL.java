package me.notlewx.pgames.db;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
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
        db.addDataSourceProperty("port", Integer.valueOf(this.port));
        db.addDataSourceProperty("user", this.user);
        db.addDataSourceProperty("password", this.pass);
        return db;
    }
    public void createTables() {

    }
}
