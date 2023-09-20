package me.notlewx.privategames.api;

import com.tomkeuper.bedwars.api.BedWars;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.entity.Player;

import java.util.List;

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
     * Get te private arena util
     * @return - Private arena util
     */
    IPrivateArenaUtil getPrivateArenaUtil();


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
         * Get a private arena via arena name
         * @param arenaName - Arena name of an arena you want to get the arena from
         * @return - Private arena
         */
        IPrivateArena getPrivateArenaByName(String arenaName);

        /**
         * Check if an arena is private
         * @return - boolean
         */
        boolean isArenaPrivate(String arenaName);
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
}