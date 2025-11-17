package me.notlewx.privategames.api.arena;

import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPrivateArena {
    /**
     * Get the host of the arena
     *
     * @return The host of the arena
     */
    IPrivatePlayer getPrivateArenaHost();

    /**
     * Get the players in the arena
     *
     * @return The players in the arena
     */
    List<OfflinePlayer> getPlayers();

    /**
     * Get the arena identifier
     *
     * @return The arena identifier
     */
    String getArenaIdentifier();

    /**
     * Get the arena's default group
     *
     * @return The arena's default group
     */
    String getDefaultGroup();

    /**
     * Get the arena's max players
     *
     * @return The arena's max players
     */
    int getDefaultMaxInTeam();

    /**
     * Stop the game completely
     */
    void stopGame();

    /**
     * Add a player to the arena
     * @param p The player to add
     * @param callEvent Whether to call the event {@link me.notlewx.privategames.api.events.PrivateGameJoinEvent}
     */
    void addPlayer(Player p, boolean callEvent);

    /**
     * Remove a player from the arena
     * @param p The player to remove
     */
    void removePlayer(Player p);

    /**
     * Check if the arena is full
     * @return Whether the arena is full
     */
    boolean isFull();

    /**
     * Destroy the arena data
     */
    void destroyData();
}
