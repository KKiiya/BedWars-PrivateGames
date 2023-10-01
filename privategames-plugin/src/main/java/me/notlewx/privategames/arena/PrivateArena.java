package me.notlewx.privategames.arena;

import com.andrei1058.bedwars.api.arena.IArena;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.support.Support;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import static me.notlewx.privategames.PrivateGames.support;

public class PrivateArena implements IPrivateArena {
    private IPrivatePlayer host;
    private List<Player> players;
    private String arenaName;
    public static HashMap<String, IPrivateArena> privateArenaByArenaName;
    public static HashMap<Player, IPrivateArena> privateArenaByPlayer = new HashMap<>();
    public static LinkedList<IPrivateArena> privateArenas = new LinkedList<>();
    public PrivateArena(IPrivatePlayer host, List<Player> players, String arenaName) {
        this.host = host;
        this.players = players;
        this.arenaName = arenaName;

        privateArenaByPlayer.put(host.getPlayer(), this);
        for (Player p : players) {
            privateArenaByPlayer.put(p, this);
        }
        privateArenaByArenaName.put(arenaName, this);
        privateArenas.add(this);

        switch (support) {
            case BEDWARS1058:
                PrivateGames.getBw1058Api().getArenaUtil().getArenas().remove(PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(arenaName));
                break;
            case BEDWARS2023:
                PrivateGames.getBw2023Api().getArenaUtil().getArenas().remove(PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(arenaName));
                break;
        }

    }

    public PrivateArena() {
        if (privateArenaByArenaName == null) privateArenaByArenaName = new HashMap<>();
        if (privateArenaByPlayer == null) privateArenaByPlayer = new HashMap<>();
        if (privateArenas == null) privateArenas = new LinkedList<>();
    }
    @Override
    public IPrivatePlayer getPrivateArenaHost() {
        return host;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String getArenaName() {
        return arenaName;
    }

    @Override
    public void stopGame() {
        if (support == Support.BEDWARS1058) {
            for (Player player : PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(arenaName).getPlayers()) {
                PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(arenaName).abandonGame(player);
            }
        } else if (support == Support.BEDWARS2023) {
            for (Player player : PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(arenaName).getPlayers()) {
                PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(arenaName).abandonGame(player);
            }
        }
    }

    @Override
    public boolean isFull() {
        if (support == Support.BEDWARS1058) {
            IArena arena = PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(arenaName);
            return arena.getPlayers().size() == arena.getMaxPlayers();
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.IArena arena = PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(arenaName);
            return arena.getPlayers().size() == arena.getMaxPlayers();
        }
        return false;
    }

    @Override
    public void destroyData() {
        host = null;
        privateArenaByArenaName.remove(arenaName);
        arenaName = null;
        for (Player p : players) {
            privateArenaByPlayer.remove(p);
        }
        players = null;
        privateArenas.remove(this);

    }
}
