package me.notlewx.privategames.api.events;

import me.notlewx.privategames.api.modifiers.ModifierType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PrivateSettingUpdateEvent extends Event implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final ModifierType settingType;
    private final Integer level;
    private final boolean isEnabled;
    private boolean cancelled = false;

    public PrivateSettingUpdateEvent(@Nonnull Player player, @Nonnull ModifierType settingType, @Nullable Integer level, boolean isEnabled) {
        this.player = player;
        this.settingType = settingType;
        this.level = level;
        this.isEnabled = isEnabled;
    }

    public Player getPlayer() {
        return player;
    }

    public ModifierType getUpdatedSetting() {
        return settingType;
    }

    public Integer getNewSettingLevel() {
        return level;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
