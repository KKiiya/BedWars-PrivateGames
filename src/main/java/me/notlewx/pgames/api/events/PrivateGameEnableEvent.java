package me.notlewx.pgames.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrivateGameEnableEvent extends Event implements Cancellable {
    public static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private final Player player;
    public PrivateGameEnableEvent(Player p) {
        this.player = p;
    }
    public Player getPlayer() {
        return player;
    }
    public boolean isCancelled(){return cancelled;}
    public void setCancelled(boolean cancelled){this.cancelled = cancelled;}
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
