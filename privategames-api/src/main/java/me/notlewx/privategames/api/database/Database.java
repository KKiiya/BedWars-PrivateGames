package me.notlewx.privategames.api.database;

import org.bukkit.OfflinePlayer;

import java.sql.Connection;

public interface Database {

    /**
     * Get the data of a player
     * @param player - Player you want to get the data from
     * @param column - Column you are searching the data from
     * @return - String
     */
    String getData(OfflinePlayer player, String column);

    /**
     * Set the data of a player
     * @param player - Player you want to set the data for
     * @param column - Column you want to set the data for
     * @param value - Value you want to write
     */
    void setData(OfflinePlayer player, String column, String value);

    /**
     * Create the data of a player
     * This can only be done ONCE
     * <p>
     * Doing it more than once wont do anything
     * @param player - Player you want to create the data to
     */
    void createPlayerData(OfflinePlayer player);

    /**
     * Get the database connection
     * @return - Connection
     */
    Connection getConnection();
}
