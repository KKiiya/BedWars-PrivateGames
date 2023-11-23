package me.notlewx.privategames.api;

import com.tomkeuper.bedwars.api.BedWars;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface PrivateGames {

    /**
     * Get the database utils
     * @return - database
     */
    Database getDatabase();

    /**
     * Get info of a specific player
     * @param player - Player
     * @return - IPrivatePlayer
     */
    IPrivatePlayer getPrivatePlayer(Player player);

    /**
     * Get the private arena util
     * @return - Private arena util
     */
    IPrivateArenaUtil getPrivateArenaUtil();

    /**
     * Get the menu util
     * @return - Menu Util
     */
    IMenuUtil getMenuUtil();

    interface IMenuUtil {
        /**
         * Open the settings menu
         */
        void openSettingsMenu(Player p);

        /**
         * Open the health buff menu
         */
        void openHealthBuffMenu(Player p);

        /**
         * Open the events time menu
         */
        void openEventsTimeMenu(Player p);

        /**
         * Open the respawn time menu
         */
        void openRespawnTimeMenu(Player p);

        /**
         * Open the options menu
         */
        void openOptionsMenu(Player p);
    }


    interface IPrivateArenaUtil {
        /**
         * Get the private arenas that
         * are currently playing
         * @return - List of privates arnas
         */
        List<IPrivateArena> getPrivateArenas();

        /**
         * Get a private arena via player
         * @param player - Player you want to get the arena from
         * @return - Private arena
         */
        IPrivateArena getPrivateArenaByPlayer(Player player);

        /**
         * Get a private arena via arena world name
         * @param worldName - World name (remoteIdentifier) of an arena you want to get the arena from
         * @return - Private arena
         */
        IPrivateArena getPrivateArenaByIdentifier(String worldName);

        /**
         * Check if an arena is private
         * @return - boolean
         */
        boolean isArenaPrivate(String arenaName);

        /**
         * Check if a player is playing
         * @param uuid - UUID of the player
         * @return - boolean
         */
        boolean isPlaying(UUID uuid);

        /**
         * Check if a player is playing
         * @param player - Player
         * @return - boolean
         */
        boolean isPlaying(Player player);
    }

    /**
     * Get the BedWars2023 API
     * @return - BedWars2023 API
     */
    BedWars getBedWars2023API();

    /**
     * Get the BedWars1058 API
     * @return - BedWars1058 API
     */
    com.andrei1058.bedwars.api.BedWars getBedWars1058API();

    /**
     * Get the BedWarsProxy API
     * @return - BedWarsProxy API
     */
    com.andrei1058.bedwars.proxy.api.BedWars getBedWarsProxyAPI();
    /**
     * Get the BedWarsProxy2023 API
     * @return - BedWarsProxy2023 API
     */
    com.tomkeuper.bedwars.proxy.api.BedWars getBedWarsProxy2023API();
}
