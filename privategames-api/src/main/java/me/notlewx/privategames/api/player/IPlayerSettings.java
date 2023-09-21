package me.notlewx.privategames.api.player;

import org.bukkit.entity.Player;

public interface IPlayerSettings {
    /**
     * Get the player
     * @return - Player
     */
    Player getPlayer();

    /**
     * Check if the player has private
     * games enabled
     * @return - boolean
     */
    boolean isPrivateGameEnabled();

    /**
     * Check if the player has one hit one kill
     * enabled
     * @return - boolean
     */
    boolean isOneHitOneKillEnabled();

    /**
     * Check if the player has low gravity
     * enabled
     * @return - boolean
     */
    boolean isLowGravityEnabled();

    /**
     * Check if the player has bed insta break
     * enabled
     * @return - boolean
     */
    boolean isBedInstaBreakEnabled();

    /**
     * Check if the player has max team upgrades
     * enabled
     * @return - boolean
     */
    boolean isMaxTeamUpgradesEnabled();

    /**
     * Check if the player has allowed map
     * break in their games
     * @return - boolean
     */
    boolean isAllowMapBreakEnabled();

    /**
     * Check if the player has disabled
     * diamond generators in its games
     * @return - boolean
     */
    boolean isNoDiamondsEnabled();

    /**
     * Check if the player has disabled
     * emerald generators in its games
     * @return - boolean
     */
    boolean isNoEmeraldsEnabled();

    /**
     * Enable private games for this player
     * @return - Check if the private games has been enabled (cancellable)
     */
    boolean setPrivateGameEnabled();

    /**
     * Disable private games for this player
     * @return - Check if the private games has been disabled (cancellable)
     */
    boolean setPrivateGameDisabled(boolean leaving);

    /**
     * Get the respawn time level
     *
     * @return - 0-Disabled, 1-1Second, 2-5Seconds, 3-10Seconds
     */
    int getRespawnTimeLevel();

    /**
     * Get the health buff level
     *
     * @return - 0-Disabled, 1-Normal, 2-Double, 3-Triple
     */
    int getHealthBuffLevel();

    /**
     * Get the respawn time level
     *
     * @return - 0-Disabled, 1-Slow0.5, 2-Normal1.0, 3-Fast2.0, 4-Faster4.0
     */
    int getEventsTimeLevel();

    /**
     * Get the respawn time level
     *
     * @return - 0-Disabled, 1-Normal, 2-Speed1, 3-Speed2, 4-Speed3
     */
    int getSpeedLevel();

    /**
     * Enable or disable one hit one kill
     * @param value - boolean
     */
    void setOneHitOneKillEnabled(boolean value);

    /**
     * Enable or disable low gravity
     * @param value - boolean
     */
    void setLowGravityEnabled(boolean value);

    /**
     * Enable or disable bed instant break
     * @param value - boolean
     */
    void setBedInstaBreakEnabled(boolean value);

    /**
     * Enable or disable max team upgrades
     * @param value - boolean
     */
    void setMaxTeamUpgradesEnabled(boolean value);

    /**
     * Enable or disable map break
     * @param value - boolean
     */
    void setAllowMapBreakEnabled(boolean value);

    /**
     * Enable or disable diamond generators
     * @param value - boolean
     */
    void setNoDiamondsEnabled(boolean value);

    /**
     * Enable or disable emerald generators
     * @param value - boolean
     */
    void setNoEmeraldsEnabled(boolean value);

    /**
     * Set the respawn time value
     * 0-Disabled, 1-1Second, 2-5Seconds, 3-10Seconds
     * <p>
     * @param value - int
     * @throws IndexOutOfBoundsException - Value lower than 0 or higher than 3
     */
    void setRespawnTimeLevel(int value) throws IndexOutOfBoundsException;

    /**
     * Set the respawn time value
     * 0-Disabled, 1-Normal, 2-Double, 3-Triple
     * <p>
     * @param value - int
     * @throws IndexOutOfBoundsException - Value lower than 0 or higher than 3
     */
    void setHealthBuffLevel(int value) throws IndexOutOfBoundsException;

    /**
     * Set the respawn time value
     * 0-Disabled, 1-Slow0.5, 2-Normal1.0, 3-Fast2.0, 4-Faster4.0
     * <p>
     * @param value - int
     * @throws IndexOutOfBoundsException - Value lower than 0 or higher than 4
     */
    void setEventsTimeLevel(int value) throws IndexOutOfBoundsException;

    /**
     * Set the respawn time value
     * 0-Disabled, 1-Normal, 2-Speed1, 3-Speed2, 4-Speed3
     * <p>
     * @param value - int
     * @throws IndexOutOfBoundsException - Value lower than 0 or higher than 4
     */
    void setSpeedLevel(int value) throws IndexOutOfBoundsException;
}
