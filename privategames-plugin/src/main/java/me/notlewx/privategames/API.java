package me.notlewx.privategames;

import com.tomkeuper.bedwars.api.BedWars;
import me.notlewx.privategames.api.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.player.PrivatePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class API implements PrivateGames {
    @Override
    public Database getDatabase() {
        return me.notlewx.privategames.PrivateGames.database;
    }

    @Override
    public IPrivatePlayer getPrivatePlayer(Player player) {
        return new PrivatePlayer(player);
    }

    @Override
    public IPrivateArenaUtil getPrivateArenaUtil() {
        return new PrivateArenaUtil();
    }

    private static class PrivateArenaUtil implements IPrivateArenaUtil {

        @Override
        public List<IPrivateArena> getPrivateArenas() {
            return PrivateArena.privateArenas;
        }

        @Override
        public IPrivateArena getPrivateArenaByPlayer(Player player) {
            return PrivateArena.privateArenaByPlayer.get(player);
        }

        @Override
        public IPrivateArena getPrivateArenaByName(String arenaName) {
            return PrivateArena.privateArenaByArenaName.get(arenaName);
        }

        @Override
        public boolean isArenaPrivate(String arenaName) {
            return getPrivateArenas().stream().map(IPrivateArena::getArenaName).collect(Collectors.toList()).contains(arenaName);
        }
    }

    @Override
    public BedWars getBedWars2023API() {
        return me.notlewx.privategames.PrivateGames.getBw2023Api();
    }

    @Override
    public com.andrei1058.bedwars.api.BedWars getBedWars1058API() {
        return me.notlewx.privategames.PrivateGames.getBw1058Api();
    }

    @Override
    public com.andrei1058.bedwars.proxy.api.BedWars getBedWarsProxyAPI() {
        return me.notlewx.privategames.PrivateGames.getBwProxyApi();
    }
}
