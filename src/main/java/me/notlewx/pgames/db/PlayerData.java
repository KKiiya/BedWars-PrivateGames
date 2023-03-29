package me.notlewx.pgames.db;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.events.PrivateGameDisableEvent;
import me.notlewx.pgames.api.events.PrivateGameEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerData {
    MySQL mySQL = new MySQL();
    SQLite sqLite = new SQLite();
    public boolean isPlayerInParty(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "playerInParty"));
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "playerInParty"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "playerInParty"));
            }
        }
        return false;
    }
    public void setPlayerInParty(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "playerInParty", String.valueOf(value));
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "playerInParty", String.valueOf(value));
            } else {
                sqLite.setData(path, "playerInParty", String.valueOf(value));
            }
        }
    }
    public boolean isPrivateGameEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "playerInParty"));
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "playerInParty"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "playerInParty"));
            }
        }
        return false;
    }
    public boolean setPrivateGameEnabled(Player player) {
        String path = player.getUniqueId().toString();
        PrivateGameEnableEvent event = new PrivateGameEnableEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "privateGameEnabled", String.valueOf(true));
                return true;
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
               mySQL.setData(path, "playerInParty", String.valueOf(true));
            } else {
                sqLite.setData(path, "playerInParty", String.valueOf(true));
            }
            return true;
        }
        return false;
    }
    public boolean setPrivateGameDisabled(Player player) {
        String path = player.getUniqueId().toString();
        PrivateGameDisableEvent event = new PrivateGameDisableEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "privateGameEnabled", String.valueOf(true));
                return true;
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "playerInParty", String.valueOf(true));
            } else {
                sqLite.setData(path, "playerInParty", String.valueOf(true));
            }
            return true;
        }
        return false;
    }

}
