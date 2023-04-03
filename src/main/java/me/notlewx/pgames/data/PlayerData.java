package me.notlewx.pgames.data;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.events.PrivateGameDisableEvent;
import me.notlewx.pgames.api.events.PrivateGameEnableEvent;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.data.database.MySQL;
import me.notlewx.pgames.data.database.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerData implements IPlayerData {
    final MySQL mySQL = new MySQL();
    final SQLite sqLite = new SQLite();
    @Override
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
    public boolean isPlayerInParty(Player player) {
        Party party = new Party();
        return party.hasParty(player);
    }

    @Override
    public boolean OHOKisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean LGisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean BIBisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean MTUisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean AMBisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean NDisEnabled(Player player) {
        return false;
    }

    @Override
    public boolean NEisEnabled(Player player) {
        return false;
    }
    @Override
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
    @Override
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

    @Override
    public int getRETLevel() {
        return 0;
    }

    @Override
    public int getHBLevel() {
        return 0;
    }

    @Override
    public int getETLevel() {
        return 0;
    }

    @Override
    public int getSpeedLevel() {
        return 0;
    }

    @Override
    public void setOHOKEnabled(Player player, boolean value) {

    }

    @Override
    public void setLGEnabled(Player player, boolean value) {

    }

    @Override
    public void setBIBEnabled(Player player, boolean value) {

    }

    @Override
    public void setMTUEnabled(Player player, boolean value) {

    }

    @Override
    public void setAMBEnabled(Player player, boolean value) {

    }

    @Override
    public void setNDEnabled(Player player, boolean value) {

    }

    @Override
    public void setNEEnabled(Player player, boolean value) {

    }

    @Override
    public void setRETLevel(Player player, int value) {

    }

    @Override
    public void setHBLevel(Player player, int value) {

    }

    @Override
    public void setETLevel(Player player, int value) {

    }

    @Override
    public void setSpeedLevel(Player player, int value) {

    }
}