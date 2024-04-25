package me.notlewx.privategames.player;

import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.party.Party;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class PrivatePlayer implements IPrivatePlayer {

    private static final LinkedHashMap<OfflinePlayer, UUID> lastJoinRequest = new LinkedHashMap<>();
    private static final LinkedHashMap<OfflinePlayer, List<UUID>> requests = new LinkedHashMap<>();
    private static final HashMap<String, IPrivatePlayer> privatePlayers = new HashMap<>();

    private OfflinePlayer player;
    private PlayerSettings playerSettings;
    private PlayerOptions playerOptions;

    public PrivatePlayer(OfflinePlayer player) {
        Utility.debug("Creating PrivatePlayer for " + player.getName());
        this.player = player;
        this.playerSettings = new PlayerSettings(player);
        this.playerOptions = new PlayerOptions(player);
        if (!requests.containsKey(player)) requests.put(player, new ArrayList<>());
        Utility.debug("PrivatePlayer for " + player.getName() + " has been created.");
    }

    public PrivatePlayer(UUID uuid) {
        this(Bukkit.getOfflinePlayer(uuid));
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
        return playerSettings;
    }

    @Override
    public IPlayerOptions getPlayerOptions() {
        return playerOptions;
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
        for (IPrivateArena arena : PrivateArena.privateArenaByPlayer.values()) {
            if (arena.getPlayers().contains(player)) {
                return true;
            }
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
        return ((Player) player).hasPermission("pg.vip");
    }

    @Override
    public void destroy() {
        Utility.debug("Destroying PrivatePlayer for " + player.getName());
        clearRequests();
        playerSettings.save();
        playerOptions.save();
        playerOptions = null;
        playerSettings = null;
        privatePlayers.remove(player.getUniqueId().toString());
        Utility.debug("PrivatePlayer for " + player.getName() + " has been destroyed.");
        player = null;
    }

    public static IPrivatePlayer getPrivatePlayer(OfflinePlayer player) {
        return privatePlayers.computeIfAbsent(player.getUniqueId().toString(), k -> new PrivatePlayer(player));
    }

    public static IPrivatePlayer getPrivatePlayer(UUID uuid) {
        return privatePlayers.computeIfAbsent(uuid.toString(), k -> new PrivatePlayer(uuid));
    }
}
