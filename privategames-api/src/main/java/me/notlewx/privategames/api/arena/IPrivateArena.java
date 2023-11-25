package me.notlewx.privategames.api.arena;

import me.notlewx.privategames.api.player.IPrivatePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface IPrivateArena {
    IPrivatePlayer getPrivateArenaHost();
    List<OfflinePlayer> getPlayers();
    String getArenaIdentifier();
    String getDefaultGroup();
    void stopGame();
    void addPlayer(Player p);
    boolean isFull();
    void destroyData();
}
