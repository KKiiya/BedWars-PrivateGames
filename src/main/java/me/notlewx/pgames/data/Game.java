package me.notlewx.pgames.data;

import com.andrei1058.bedwars.api.arena.IArena;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Game implements IGame {
    private final HashMap<IArena, Boolean> privArena = new HashMap<>();
    private final HashMap<IArena, Player> arenaOwner = new HashMap<>();
    @Override
    public boolean isArenaPrivate(IArena arena) {
        return privArena.get(arena);
    }

    @Override
    public void setArenaPrivate(IArena arena, boolean value) {
        privArena.put(arena, value);
        if (value) {
            PGamesAPI.getBwApi().getArenaUtil().getArenas().remove(arena);
        } else {
            PGamesAPI.getBwApi().getArenaUtil().getArenas().add(arena);
        }
    }

    @Override
    public void setPrivateArenaOwner(IArena arena, Player player) {
        arenaOwner.put(arena, player);
    }

    @Override
    public Player getOwnerOfPrivateArena(IArena arena) {
        return arenaOwner.get(arena);
    }

    @Override
    public boolean isOwnerOfArena(Player player, IArena arena) {
        return arenaOwner.get(arena) == player;
    }
}
