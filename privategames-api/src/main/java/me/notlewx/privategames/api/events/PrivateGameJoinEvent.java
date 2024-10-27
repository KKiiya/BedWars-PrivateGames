package me.notlewx.privategames.api.events;

import me.notlewx.privategames.api.arena.IPrivateArena;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PrivateGameJoinEvent extends Event implements Cancellable {

    public static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final IPrivateArena privateArena;
    private boolean cancelled = false;

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
