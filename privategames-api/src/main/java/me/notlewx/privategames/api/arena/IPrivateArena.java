package me.notlewx.privategames.api.arena;

import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPrivateArena {
    IPrivatePlayer getPrivateArenaHost();
    List<Player> getPlayers();
    String getArenaName();
    String getDefaultGroup();
    void stopGame();
    boolean isFull();
    void destroyData();
}
