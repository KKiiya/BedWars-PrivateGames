package me.notlewx.pgames.util;

import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import me.notlewx.pgames.PrivateGames;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Utility {
    public static String colorizedString(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String getMSGLang(Player p, String path) {
        if (PrivateGames.isBwproxy()) {
            return com.andrei1058.bedwars.proxy.language.Language.getMsg(p, path);
        }
        return Language.getMsg(p, path);
    }

    public static List<String> getListLang(Player p, String path) {
        if (PrivateGames.isBwproxy()) {
            return BedWarsProxy.getAPI().getLanguageUtil().getList(p, path);
        }
        return Language.getList(p, path);
    }
}
