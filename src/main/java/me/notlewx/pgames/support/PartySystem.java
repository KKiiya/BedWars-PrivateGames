package me.notlewx.pgames.support;

import org.bukkit.plugin.java.JavaPlugin;

public class PartySystem extends JavaPlugin {
    public static String partypl;
    public PartySystem() {

    }

    public void start() {
        if (getServer().getPluginManager().getPlugin("Parties") != null) {
            if (getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
                partypl = "Parties";
                getLogger().severe("Using parties system o Parties by AlessioDP");
            }
            else if (getServer().getPluginManager().getPlugin("PartyAndFriendsExtended").isEnabled()) {
                partypl = "PAFE";
                getLogger().severe("Using parties system of Party and Friends Extended");
            }
            else {
                partypl = "Default";
                getLogger().severe("Using default parties system of BedWars1058");
            }
        }
    }
}
