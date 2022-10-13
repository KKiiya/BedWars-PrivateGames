package me.notlewx.pgames.api.interfaces;

import com.andrei1058.bedwars.api.arena.IArena;
import org.bukkit.entity.Player;
import java.util.List;

public interface IGame {
    Player getOwner();
    List<Player> getMembers();
    IArena getArena();
    void destroy();
    List<String> getSettingsMessage(Player arenaPlayers);
}
