package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPlayerOptions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class PlayerOptions implements IPlayerOptions {

    private final OfflinePlayer p;
    private final UUID uuid;
    private Database db;

    public PlayerOptions(OfflinePlayer p) {
        this.db = PrivateGames.database;
        this.p = p;
        this.uuid = p.getUniqueId();
    }

    public PlayerOptions(UUID uuid) {
        this.p = Bukkit.getOfflinePlayer(uuid);
        this.uuid = uuid;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return p;
    }

    @Override
    public boolean isAllowJoin() {
        return Boolean.parseBoolean(db.getData(p, "allowJoin"));
    }

    @Override
    public void setAllowJoin(boolean allowJoin) {
        db.setData(p, "allowJoin", String.valueOf(allowJoin));
    }

    @Override
    public boolean isAutoStart() {
        return Boolean.parseBoolean(db.getData(p, "autoStart"));
    }

    @Override
    public void setAutoStart(boolean autoStart) {
        db.setData(p, "autoStart", String.valueOf(autoStart));
    }
}
