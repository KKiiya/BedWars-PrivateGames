package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.party.Party;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.support;

public class PrivatePlayer implements IPrivatePlayer {
    private final OfflinePlayer player;
    private static final LinkedHashMap<OfflinePlayer, UUID> lastJoinRequest = new LinkedHashMap<>();
    private static final LinkedHashMap<OfflinePlayer, List<UUID>> requests = new LinkedHashMap<>();
    public PrivatePlayer(OfflinePlayer player) {
        this.player = player;
        if (!requests.containsKey(player)) requests.put(player, new ArrayList<>());
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
    public UUID getLastJoinRequest() {
        return lastJoinRequest.get(player);
    }

    @Override
    public void addRequest(UUID player) {
        lastJoinRequest.put(this.player, player);
        if (!requests.get(this.player).stream().map(UUID::toString).collect(Collectors.toList()).contains(player.toString())) requests.get(this.player).add(player);
    }

    @Override
    public void removeRequest(UUID player) {
        requests.get(this.player).remove(player);

        if (lastJoinRequest.get(this.player) == null) return;
        if (lastJoinRequest.get(this.player).toString().equals(player.toString())) {
            lastJoinRequest.remove(this.player);
            if (!requests.get(this.player).isEmpty()) {
                lastJoinRequest.put(this.player, requests.get(this.player).get(0));
            }
        }
    }

    @Override
    public void clearRequests() {
        requests.get(this.player).clear();
        lastJoinRequest.remove(this.player);
    }

    @Override
    @Nullable
    public UUID getRequestByName(String name) {
        if (name == null) return null;
        if (name.isEmpty()) return null;
        if (requests.get(this.player).isEmpty()) return null;

        for (UUID uuid : requests.get(this.player)) {
            if (Bukkit.getOfflinePlayer(uuid).getName().equalsIgnoreCase(name)) {
                return uuid;
            }
        }
        return null;
    }

    @Override
    public void setLastJoinRequest(UUID player) {
        lastJoinRequest.put(this.player, player);
    }

    @Override
    public List<UUID> getRequests() {
        return requests.get(player);
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
        return PrivateArena.privateArenaByPlayer.get(player);
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
        return PrivateArena.privateArenaByPlayer.get(player) != null;
    }

    @Override
    public boolean hasPermission() {
        if (!player.isOnline()) return false;
        return ((Player) player).hasPermission("pg.vip") || ((Player) player).hasPermission("pg.*");
    }
}
