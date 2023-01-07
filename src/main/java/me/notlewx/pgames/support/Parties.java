package me.notlewx.pgames.support;

import me.notlewx.pgames.listeners.party.DefaultPartyJoinAndLeave;
import me.notlewx.pgames.listeners.party.PartiesPartyJoinAndLeave;
import org.bukkit.plugin.java.JavaPlugin;
import static me.notlewx.pgames.main.*;

public class Parties extends JavaPlugin {
    @Override
    public void onEnable() {
        if (parties) {
            getServer().getPluginManager().registerEvents(new PartiesPartyJoinAndLeave(), this);
        }
        else {
            getServer().getPluginManager().registerEvents(new DefaultPartyJoinAndLeave(), this);
        }
    }
}
