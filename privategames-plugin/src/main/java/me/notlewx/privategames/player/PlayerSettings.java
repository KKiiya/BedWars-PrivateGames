package me.notlewx.privategames.player;

import me.notlewx.privategames.PrivateGames;
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
    boolean privateGameEnabled = false;
    boolean oneHitOneKill = false;
    boolean lowGravity = false;
    boolean bedInstaBreak = false;
    boolean maxTeamUpgrades = false;
    boolean allowMapBreak = false;
    boolean noDiamonds = false;
    boolean noEmeralds = false;
    int respawnTime = 0;
    int healthBuff = 0;
    int eventsTime = 0;
    int speed = 0;

    public PlayerSettings(OfflinePlayer player)  {
        this.player = player;
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getInstance(), () -> {
            privateGameEnabled = Boolean.parseBoolean(database.getData(player, "privateGameEnabled"));
            oneHitOneKill = Boolean.parseBoolean(database.getData(player, "oneHitOneKill"));
            lowGravity = Boolean.parseBoolean(database.getData(player, "lowGravity"));
            bedInstaBreak = Boolean.parseBoolean(database.getData(player, "bedInstaBreak"));
            maxTeamUpgrades = Boolean.parseBoolean(database.getData(player, "maxTeamUpgrades"));
            allowMapBreak = Boolean.parseBoolean(database.getData(player, "allowMapBreak"));
            noDiamonds = Boolean.parseBoolean(database.getData(player, "noDiamonds"));
            noEmeralds = Boolean.parseBoolean(database.getData(player, "noEmeralds"));
            respawnTime = Integer.parseInt(database.getData(player, "respawnEventTime"));
            healthBuff = Integer.parseInt(database.getData(player, "healthBuffLevel"));
            eventsTime = Integer.parseInt(database.getData(player, "eventsTime"));
            speed = Integer.parseInt(database.getData(player, "speed"));

        });
    }

    @Override
    public OfflinePlayer getPlayer() {
        return player;
    }

    @Override
    public boolean isPrivateGameEnabled() {
        return privateGameEnabled;
    }

    @Override
    public boolean isOneHitOneKillEnabled() {
        return oneHitOneKill;
    }

    @Override
    public boolean isLowGravityEnabled() {
        return lowGravity;
    }

    @Override
    public boolean isBedInstaBreakEnabled() {
        return bedInstaBreak;
    }

    @Override
    public boolean isMaxTeamUpgradesEnabled() {
        return maxTeamUpgrades;
    }

    @Override
    public boolean isAllowMapBreakEnabled() {
        return allowMapBreak;
    }

    @Override
    public boolean isNoDiamondsEnabled() {
        return noDiamonds;
    }

    @Override
    public boolean isNoEmeraldsEnabled() {
        return noEmeralds;
    }

    @Override
    public boolean setPrivateGameEnabled() {
        if (!player.isOnline()) return false;
        PrivateGameEnableEvent event = new PrivateGameEnableEvent((Player) player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        privateGameEnabled = true;
        return true;
    }

    @Override
    public boolean setPrivateGameDisabled(boolean leaving) {
        if (!player.isOnline()) return false;
        PrivateGameDisableEvent event = new PrivateGameDisableEvent((Player) player, leaving);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        privateGameEnabled = false;
        return true;
    }

    @Override
    public int getRespawnTimeLevel() {
        return respawnTime;
    }

    @Override
    public int getHealthBuffLevel() {
        return healthBuff;
    }

    @Override
    public int getEventsTimeLevel() {
        return eventsTime;
    }

    @Override
    public int getSpeedLevel() {
        return Integer.parseInt(database.getData(player, "speed"));
    }

    @Override
    public void setOneHitOneKillEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.ONE_HIT_ONE_KILL, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        oneHitOneKill = value;
    }

    @Override
    public void setLowGravityEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.LOW_GRAVITY, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        lowGravity = value;
    }

    @Override
    public void setBedInstaBreakEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.INSTANT_BED_BREAK, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        bedInstaBreak = value;
    }

    @Override
    public void setMaxTeamUpgradesEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.MAX_TEAM_UPGRADES, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        maxTeamUpgrades = value;
    }

    @Override
    public void setAllowMapBreakEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.ALLOW_MAP_BREAK, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        allowMapBreak = value;
    }

    @Override
    public void setNoDiamondsEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.NO_DIAMONDS, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        noDiamonds = value;
    }

    @Override
    public void setNoEmeraldsEnabled(boolean value) {
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.NO_EMERALDS, null, value);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        noEmeralds = value;
    }

    @Override
    public void setRespawnTimeLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 3) throw new IndexOutOfBoundsException("Value out of bounds! The respawn time value cannot be higher than 3 or lower than 0");
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.RESPAWN_TIME, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        respawnTime = value;
    }

    @Override
    public void setHealthBuffLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 3) throw new IndexOutOfBoundsException("Value out of bounds! The health buff value cannot be higher than 3 or lower than 0");
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.HEALTH_BUFF, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        healthBuff = value;
    }

    @Override
    public void setEventsTimeLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 4) throw new IndexOutOfBoundsException("Value out of bounds! The events time value cannot be higher than 4 or lower than 0");
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.EVENTS_TIME, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        eventsTime = value;
    }

    @Override
    public void setSpeedLevel(int value) throws IndexOutOfBoundsException {
        if (value < 0 || value > 4) throw new IndexOutOfBoundsException("Value out of bounds! The speed value cannot be higher than 4 or lower than 0");
        boolean enabled = value != 0 && value != 1;
        PrivateSettingUpdateEvent event = new PrivateSettingUpdateEvent((Player) player, ModifierType.SPEED, value, enabled);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        speed = value;
    }

    @Override
    public void save() {
        Bukkit.getScheduler().runTaskAsynchronously(PrivateGames.getInstance(), () -> {
            database.setData(player, "privateGameEnabled", String.valueOf(privateGameEnabled));
            database.setData(player, "oneHitOneKill", String.valueOf(oneHitOneKill));
            database.setData(player, "lowGravity", String.valueOf(lowGravity));
            database.setData(player, "bedInstaBreak", String.valueOf(bedInstaBreak));
            database.setData(player, "maxTeamUpgrades", String.valueOf(maxTeamUpgrades));
            database.setData(player, "allowMapBreak", String.valueOf(allowMapBreak));
            database.setData(player, "noDiamonds", String.valueOf(noDiamonds));
            database.setData(player, "noEmeralds", String.valueOf(noEmeralds));
            database.setData(player, "respawnEventTime", String.valueOf(respawnTime));
            database.setData(player, "healthBuffLevel", String.valueOf(healthBuff));
            database.setData(player, "eventsTime", String.valueOf(eventsTime));
            database.setData(player, "speed", String.valueOf(speed));
        });
    }
}
