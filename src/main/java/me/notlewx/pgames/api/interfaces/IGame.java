package me.notlewx.pgames.api.interfaces;

import com.andrei1058.bedwars.api.arena.IArena;
import org.bukkit.entity.Player;

import java.util.List;

public interface IGame {
    /**
     *  Checks if the arena is a private arena
     * @return boolean
     */
    boolean isArenaPrivate(IArena arena);

    /**
     * Set a selected arena private or not
     * It will be removed from the arenas
     * GUI.
     */
    void setArenaPrivate(IArena arena, boolean value);

    /**
     * Set the owner of the private arena
     *
     */
    void setPrivateArenaOwner(IArena arena, Player player);

    /**
     * Get the owner of the arena to get his data
     * with the IPlayerData interface
     * @return the owner of that private arena
     */
    Player getOwnerOfPrivateArena(IArena arena);

    /**
     * Check if the selected player is
     * the owner of the selected private arena
     * @return boolean
     */
    boolean isOwnerOfArena(Player player, IArena arena);
    /**
     * Get the actual arenas that are in
     * a private state
     * @return list of arenas
     */
    List<IArena> getPrivateGames();
}
