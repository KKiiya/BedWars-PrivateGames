package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.party.Party;
import org.bukkit.entity.Player;
import javax.annotation.Nullable;
import java.util.UUID;

import static me.notlewx.privategames.PrivateGames.support;

public class PrivatePlayer implements IPrivatePlayer {
    private final Player player;
    public PrivatePlayer(Player player) {
        this.player = player;
    }
    @Override
    public Player getPlayer() {
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
    @Nullable
    public IParty getPlayerParty() {
        return Party.getPartyPlayer(player);
    }

    @Override
    public IPrivateArena getArena() {
        return PrivateArena.privateArenaByPlayer.get(player);
    }

    @Override
    public boolean isPlaying() {
        switch (support) {
            case BEDWARS1058:
                return PrivateGames.getBw1058Api().getArenaUtil().isPlaying(player);
            case BEDWARS2023:
                return PrivateGames.getBw2023Api().getArenaUtil().isPlaying(player);
        }
        return false;
    }

    @Override
    public boolean isInPrivateArena() {
        return PrivateArena.privateArenaByPlayer.get(player) != null;
    }

    @Override
    public boolean hasPermission() {
        return player.hasPermission("pg.vip") || player.hasPermission("pg.*");
    }
}
