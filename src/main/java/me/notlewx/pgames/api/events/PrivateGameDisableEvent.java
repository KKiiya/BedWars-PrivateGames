package me.notlewx.pgames.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class PrivateGameDisableEvent extends Event {
    public static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    public static boolean priv = false;
    public PrivateGameDisableEvent(Player p) {
        this.player = p;
        priv = false;
    }
    public Player getPlayer() {
        return player;
    }
}
