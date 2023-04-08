package me.notlewx.pgames.api.interfaces;

import com.andrei1058.bedwars.api.arena.IArena;
import org.bukkit.entity.Player;

public interface IGame {
    boolean isArenaPrivate(IArena arena);
    void setArenaPrivate(IArena arena, boolean value);
    void setPrivateArenaOwner(IArena arena, Player player);
    Player getOwnerOfPrivateArena(IArena arena);
    boolean isOwnerOfArena(Player player, IArena arena);
}
