package me.notlewx.privategames.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PrivateGameJoinRequestSendEvent extends Event {
    public static final HandlerList handlers = new HandlerList();

    private final UUID requested;
    private final UUID requester;
    private boolean cancelled = false;

    public PrivateGameJoinRequestSendEvent(UUID requested, UUID requester) {
        this.requested = requested;
        this.requester = requester;

    }

    public UUID getRequested() {
        return requested;
    }

    public UUID getRequester() {
        return requester;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
