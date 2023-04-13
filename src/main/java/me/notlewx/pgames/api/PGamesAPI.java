package me.notlewx.pgames.api;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.Party;
import me.notlewx.pgames.data.PrivateSettings;
import org.bukkit.Bukkit;

public class PGamesAPI {
    public static boolean isProxy() {
        return PrivateGames.isBwproxy();
    }
    public static PrivateSettings getPlayerData() {
        return PrivateGames.getPlayerData();
    }
    public static BedWars getBwApi() {
        return Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
    }
    public static com.andrei1058.bedwars.proxy.api.BedWars getBwProxyApi() {
        return BedWarsProxy.getAPI();
    }
    public static Party getPartyUtil() {
        return PrivateGames.getPartyUtil();
    }
    public static IGame getGameUtil() {
        return PrivateGames.getGameUtil();
    }
}
