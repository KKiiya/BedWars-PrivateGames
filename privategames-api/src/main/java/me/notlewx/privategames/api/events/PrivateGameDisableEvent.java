package me.notlewx.privategames.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PrivateGameDisableEvent extends Event implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final boolean isLeaving;
    private boolean cancelled = false;

    public PrivateGameDisableEvent(Player player, boolean isLeaving) {
        this.player = player;
        this.isLeaving = isLeaving;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isLeaving() {
        return isLeaving;
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
