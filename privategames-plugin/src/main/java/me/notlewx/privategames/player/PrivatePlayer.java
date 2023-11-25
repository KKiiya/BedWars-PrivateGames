package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.party.Party;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import javax.annotation.Nullable;
import java.util.UUID;

import static me.notlewx.privategames.PrivateGames.support;

public class PrivatePlayer implements IPrivatePlayer {
    private final OfflinePlayer player;
    public PrivatePlayer(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return player;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public IPlayerSettings getPlayerSettings() {
        return new PlayerSettings(player);
    }

    @Override
    public IPlayerOptions getPlayerOptions() {
        return new PlayerOptions(player);
    }

    @Override
    @Nullable
    public IParty getPlayerParty() {
        if (!player.isOnline()) return null;
        return Party.getPartyPlayer((Player) player);
    }

    @Override
    @Nullable
    public IPrivateArena getArena() {
        if (!player.isOnline()) return null;
        return PrivateArena.privateArenaByPlayer.get((Player) player);
    }

    @Override
    public boolean isPlaying() {
        if (!player.isOnline()) return false;
        switch (support) {
            case BEDWARS1058:
                return PrivateGames.getBw1058Api().getArenaUtil().isPlaying((Player) player);
            case BEDWARS2023:
                return PrivateGames.getBw2023Api().getArenaUtil().isPlaying((Player) player);
        }
        return false;
    }

    @Override
    public boolean isInPrivateArena() {
        if (!player.isOnline()) return false;
        return PrivateArena.privateArenaByPlayer.get((Player) player) != null;
    }

    @Override
    public boolean hasPermission() {
        if (!player.isOnline()) return false;
        return ((Player) player).hasPermission("pg.vip") || ((Player) player).hasPermission("pg.*");
    }
}
