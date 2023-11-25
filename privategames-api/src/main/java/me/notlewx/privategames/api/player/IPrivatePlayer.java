package me.notlewx.privategames.api.player;

import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import javax.annotation.Nullable;
import java.util.UUID;

public interface IPrivatePlayer {

    /**
     * Get the player
     * @return - Player
     */
    OfflinePlayer getPlayer();

    /**
     * Get the player's unique ID (UUID)
     * @return - Unique ID
     */
    UUID getUniqueId();

    /**
     * Get the player private settings
     * for private matches
     * <p>
     * Might give null values if the player
     * doesn't have permission to use
     * the private games feature
     *
     * @return - player settings
     */
    IPlayerSettings getPlayerSettings();

    /**
     * Get the player's options
     * @return - Player options
     */
    IPlayerOptions getPlayerOptions();

    /**
     * Get the player's party
     * @return - Party info
     */
    IParty getPlayerParty();

    /**
     * Get the player's private arena
     * Might be null if the player isn't playing
     * @return - Private arena
     */
    @Nullable
    IPrivateArena getArena();

    /**
     * Check if the player is playing
     * in any match
     * @return boolean
     */
    boolean isPlaying();

    /**
     * Check if the player is playing
     * in a private match
     * @return boolean
     */
    boolean isInPrivateArena();

    /**
     * Check if the player has permission to
     * use the private games feature
     * Permission: "pg.vip" or "pg.*"
     * @return boolean
     */
    boolean hasPermission();
}
