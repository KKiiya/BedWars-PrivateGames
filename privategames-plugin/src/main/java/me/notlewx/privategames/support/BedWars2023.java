package me.notlewx.privategames.support;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import static me.notlewx.privategames.PrivateGames.*;

public class BedWars2023 {
    private static Plugin pl;

    public BedWars2023(Plugin plugin) {
        pl = plugin;
        start();
    }

    private void start() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") != null) {
            support = Support.BEDWARS2023;
            bedWars2023API = pl.getServer().getServicesManager().getRegistration(com.tomkeuper.bedwars.api.BedWars.class).getProvider();
            bedWars2023API.getAddonsUtil().registerAddon(new BW2023Addon());
            bw2023config = bedWars2023API.getConfigs().getMainConfig();
        }
    }
}
