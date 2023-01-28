package me.notlewx.pgames.api.events;

import static me.notlewx.pgames.api.events.PrivateGameDisableEvent.priv;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class PrivateGameEnableEvent extends Event {
    public static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private Player player;
    public PrivateGameEnableEvent(Player p) {
        this.player = p;
        priv = true;
    }
    public Player getPlayer() {
        return player;
    }
    public boolean isCancelled(){return cancelled;}
    public void setCancelled(boolean cancelled){this.cancelled = cancelled;}
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
