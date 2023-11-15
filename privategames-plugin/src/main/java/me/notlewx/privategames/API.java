package me.notlewx.privategames;

import com.tomkeuper.bedwars.api.BedWars;
import me.notlewx.privategames.api.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.menus.submenus.EventsTimeMenu;
import me.notlewx.privategames.menus.submenus.HealthMenu;
import me.notlewx.privategames.menus.submenus.RespawnTimeMenu;
import me.notlewx.privategames.player.PrivatePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
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

    @Override
    public IMenuUtil getMenuUtil() {
        return new MenuUtil();
    }

    private static class MenuUtil implements IMenuUtil {

        @Override
        public void openSettingsMenu(Player p) {
            new SettingsMenu(p);
        }

        @Override
        public void openHealthBuffMenu(Player p) {
            new HealthMenu(p);
        }

        @Override
        public void openEventsTimeMenu(Player p) {
            new EventsTimeMenu(p);
        }

        @Override
        public void openRespawnTimeMenu(Player p) {
            new RespawnTimeMenu(p);
        }
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

        @Override
        public boolean isPlaying(UUID uuid) {
            return getPrivateArenaByPlayer(Bukkit.getPlayer(uuid)) != null;
        }

        @Override
        public boolean isPlaying(Player player) {
            return getPrivateArenaByPlayer(player) != null;
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

    @Override
    public com.tomkeuper.bedwars.proxy.api.BedWars getBedWarsProxy2023API() {
        return me.notlewx.privategames.PrivateGames.getBwProxy2023Api();
    }
}
