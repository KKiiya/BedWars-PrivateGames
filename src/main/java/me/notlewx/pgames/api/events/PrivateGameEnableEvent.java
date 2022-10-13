package me.notlewx.pgames.api.events;

import static me.notlewx.pgames.api.events.PrivateGameDisableEvent.priv;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class PrivateGameEnableEvent extends Event {
    public static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    public PrivateGameEnableEvent(Player p) {
        this.player = p;
        priv = true;
    }
    public Player getPlayer() {
        return player;
    }
}
