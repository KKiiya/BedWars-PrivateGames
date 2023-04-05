package me.notlewx.pgames.api;

import com.andrei1058.bedwars.api.BedWars;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.interfaces.Party;
import me.notlewx.pgames.data.PlayerData;
import org.bukkit.Bukkit;

public class PGamesAPI {
    public static boolean isProxy() {
        return PrivateGames.isBwproxy();
    }
    public static PlayerData getPlayerData() {
        return PrivateGames.getPlayerData();
    }
    public static BedWars getBwApi() {
        return Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
    }
    public static com.andrei1058.bedwars.proxy.api.BedWars getBwProxyApi() {
        return Bukkit.getServicesManager().getRegistration(com.andrei1058.bedwars.proxy.api.BedWars.class).getProvider();
    }
    public static Party getPartyUtil() {
        return PrivateGames.getPartyUtil();
    }
}
