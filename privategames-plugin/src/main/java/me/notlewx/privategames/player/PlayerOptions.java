package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.database.Database;
import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerOptions implements IPlayerOptions {

    private final OfflinePlayer p;
    private final Database db;
    private boolean allowJoin;
    private boolean autoStart;

    public PlayerOptions(OfflinePlayer p) {
        this.db = PrivateGames.database;
        this.p = p;
        Utility.debug("Creating PlayerOptions for " + p.getName());
        Bukkit.getScheduler().runTask(PrivateGames.getInstance(), () -> {
            allowJoin = Boolean.parseBoolean(db.getData(p, "allowJoin"));
            autoStart = Boolean.parseBoolean(db.getData(p, "autoStart"));
            Utility.debug("allowJoin: " + allowJoin + " autoStart: " + autoStart);
        });
        Utility.debug("PlayerOptions for " + p.getName() + " has been created.");
    }

    @Override
    public OfflinePlayer getPlayer() {
        return p;
    }

    @Override
    public boolean isAllowJoin() {
        return allowJoin;
    }

    @Override
    public void setAllowJoin(boolean allowJoin) {
        this.allowJoin = allowJoin;
    }

    @Override
    public boolean isAutoStart() {
        return autoStart;
    }

    @Override
    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    @Override
    public void save() {
        Utility.debug("Saving PlayerOptions for " + p.getName());
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getInstance(), () -> {
            db.setData(p, "allowJoin", String.valueOf(allowJoin));
            db.setData(p, "autoStart", String.valueOf(autoStart));
            Utility.debug("allowJoin: " + allowJoin + " autoStart: " + autoStart);
            Utility.debug("PlayerOptions for " + p.getName() + " has been saved.");
        });
    }
}
