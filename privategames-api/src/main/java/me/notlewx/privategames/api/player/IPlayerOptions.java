package me.notlewx.privategames.api.player;

import org.bukkit.OfflinePlayer;

public interface IPlayerOptions {
    /**
     * Get the player
     *
     * @return The player that this object is associated with.
     */
    OfflinePlayer getPlayer();

    /**
     * Check if the player has allowed others to join their game
     *
     * @return - boolean
     */
    boolean isAllowJoin();

    /**
     * Allow or disallow others to join the player's game
     *
     * @param allowJoin The player's options
     */
    void setAllowJoin(boolean allowJoin);

    /**
     * Check if the player has enabled auto start
     * @return - boolean
     */
    boolean isAutoStart();

    /**
     * Enable or disable auto start for the player
     *
     * @param autoStart The player's options
     */
    void setAutoStart(boolean autoStart);

    /**
     * Save the player's options to the database
     */
    void save();

}
