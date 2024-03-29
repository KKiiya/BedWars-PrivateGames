package me.notlewx.privategames.player;

import me.notlewx.privategames.api.events.PrivateGameDisableEvent;
import me.notlewx.privategames.api.events.PrivateGameEnableEvent;
import me.notlewx.privategames.api.events.PrivateSettingUpdateEvent;
import me.notlewx.privategames.api.modifiers.ModifierType;
import me.notlewx.privategames.api.player.IPlayerSettings;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import static me.notlewx.privategames.PrivateGames.database;

public class PlayerSettings implements IPlayerSettings {
    private final OfflinePlayer player;
    public PlayerSettings(OfflinePlayer player)  {
        this.player = player;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isPrivateGameEnabled() {
        return Boolean.parseBoolean(database.getData(player, "privateGameEnabled"));
    }

    @Override
    public boolean isOneHitOneKillEnabled() {
        return Boolean.parseBoolean(database.getData(player, "oneHitOneKill"));
    }

    @Override
    public boolean isLowGravityEnabled() {
        return Boolean.parseBoolean(database.getData(player, "lowGravity"));
    }

    @Override
    public boolean isBedInstaBreakEnabled() {
        return Boolean.parseBoolean(database.getData(player, "bedInstaBreak"));
    }

    @Override
    public boolean isMaxTeamUpgradesEnabled() {
        return Boolean.parseBoolean(database.getData(player, "maxTeamUpgrades"));
    }

    @Override
    public boolean isAllowMapBreakEnabled() {
        return Boolean.parseBoolean(database.getData(player, "allowMapBreak"));
    }

    @Override
    public boolean isNoDiamondsEnabled() {
        return Boolean.parseBoolean(database.getData(player, "noDiamonds"));
    }

    @Override
    public boolean isNoEmeraldsEnabled() {
        return Boolean.parseBoolean(database.getData(player, "noEmeralds"));
    }

    @Override
    public boolean setPrivateGameEnabled() {
        if (!player.isOnline()) return false;
        PrivateGameEnableEvent event = new PrivateGameEnableEvent((Player) player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        database.setData(player, "privateGameEnabled", "true");
        return true;
    }

    @Override
    public boolean setPrivateGameDisabled(boolean leaving) {
        if (!player.isOnline()) return false;
        PrivateGameDisableEvent event = new PrivateGameDisableEvent((Player) player, leaving);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        database.setData(player, "privateGameEnabled", "false");
        return true;
    }

    @Override
    public int getRespawnTimeLevel() {
        return Integer.parseInt(database.getData(player, "respawnEventTime"));
    }

    @Override
    public int getHealthBuffLevel() {
        return Integer.parseInt(database.getData(player, "healthBuffLevel"));
    }

    @Override
    public int getEventsTimeLevel() {
        return Integer.parseInt(database.getData(player, "eventsTime"));
    }

    @Override
    public int getSpeedLevel() {
        return Integer.parseInt(database.getData(player, "speed"));
    }

    @Override
    public void setOneHitOneKillEnabled(boolean value) {
        database.setData(player, "oneHitOneKill", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.ONE_HIT_ONE_KILL, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setLowGravityEnabled(boolean value) {
        database.setData(player, "lowGravity", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.LOW_GRAVITY, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setBedInstaBreakEnabled(boolean value) {
        database.setData(player, "bedInstaBreak", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.INSTANT_BED_BREAK, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setMaxTeamUpgradesEnabled(boolean value) {
        database.setData(player, "maxTeamUpgrades", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.MAX_TEAM_UPGRADES, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setAllowMapBreakEnabled(boolean value) {
        database.setData(player, "allowMapBreak", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.ALLOW_MAP_BREAK, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setNoDiamondsEnabled(boolean value) {
        database.setData(player, "noDiamonds", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.NO_DIAMONDS, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setNoEmeraldsEnabled(boolean value) {
        database.setData(player, "noEmeralds", String.valueOf(value));
        if (!player.isOnline()) return;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.NO_EMERALDS, null, value);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setRespawnTimeLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 3) throw new IndexOutOfBoundsException("Value out of bounds! The respawn time value cannot be higher than 3 or lower than 0");
        database.setData(player, "respawnEventTime", String.valueOf(value));
        if (!player.isOnline()) return;
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.RESPAWN_TIME, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setHealthBuffLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 3) throw new IndexOutOfBoundsException("Value out of bounds! The health buff value cannot be higher than 3 or lower than 0");
        database.setData(player, "healthBuffLevel", String.valueOf(value));
        if (!player.isOnline()) return;
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.HEALTH_BUFF, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setEventsTimeLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 4) throw new IndexOutOfBoundsException("Value out of bounds! The events time value cannot be higher than 4 or lower than 0");
        database.setData(player, "eventsTime", String.valueOf(value));
        if (!player.isOnline()) return;
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.EVENTS_TIME, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Override
    public void setSpeedLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 4) throw new IndexOutOfBoundsException("Value out of bounds! The speed value cannot be higher than 4 or lower than 0");
        database.setData(player, "speed", String.valueOf(value));
        if (!player.isOnline()) return;
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.SPEED, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
    }
}
