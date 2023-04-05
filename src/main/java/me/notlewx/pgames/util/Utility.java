package me.notlewx.pgames.util;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {
    public static String colorizedString(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String getMSGLang(Player p, String path) {
        if (PrivateGames.isBwproxy()) {
            return PGamesAPI.getBwProxyApi().getLanguageUtil().getMsg(p, path).replace("&", "§");
        }
        return PGamesAPI.getBwApi().getPlayerLanguage(p).getString(path).replace("&", "§");
    }

    public static List<String> getListLang(Player p, String path) {
        if (PrivateGames.isBwproxy()) {
            return PGamesAPI.getBwProxyApi().getLanguageUtil().getList(p, path).stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());
        }
        return PGamesAPI.getBwApi().getPlayerLanguage(p).getList(path).stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());
    }
}
