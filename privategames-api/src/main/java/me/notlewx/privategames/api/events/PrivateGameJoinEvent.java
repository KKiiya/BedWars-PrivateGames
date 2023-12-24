package me.notlewx.privategames.api.events;

import me.notlewx.privategames.api.arena.IPrivateArena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PrivateGameJoinEvent extends Event {
    public static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final IPrivateArena privateArena;

    public PrivateGameJoinEvent(Player p, IPrivateArena privateArena) {
        this.player = p;
        this.privateArena = privateArena;
    }

    public Player getPlayer() {
        return player;
    }
    public IPrivateArena getPrivateArena() {
        return privateArena;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancelled) {this.cancelled = cancelled;}
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
