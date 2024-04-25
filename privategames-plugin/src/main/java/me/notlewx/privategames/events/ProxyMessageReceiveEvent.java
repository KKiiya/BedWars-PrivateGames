package me.notlewx.privategames.events;

import com.google.gson.JsonObject;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ProxyMessageReceiveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final JsonObject message;
    private final String server;
    private final int port;

    public ProxyMessageReceiveEvent(JsonObject message, String server, int port) {
        this.message = message;
        this.server = server;
        this.port = port;
    }

    public JsonObject getMessage() {
        return message;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
