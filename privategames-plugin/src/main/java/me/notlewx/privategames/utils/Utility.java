package me.notlewx.privategames.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.support.Support;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.stream.Collectors;
import static me.notlewx.privategames.PrivateGames.support;

public class Utility {
    public static void info(String infoLog) {
        PrivateGames.getPlugins().getLogger().info(c(infoLog));
    }
    public static void warn(String warnLog) {
        PrivateGames.getPlugins().getLogger().info(c(warnLog));
    }
    public static String c(String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }
    public static String p(Player p, String value) {
        return c(PlaceholderAPI.setPlaceholders(p, value));
    }
    public static List<String> p(Player p, List<String> value) {
        return value.stream().map(s -> c(PlaceholderAPI.setPlaceholders(p, s))).collect(Collectors.toList());
    }
    public static String getMsg(Player player, String path) {
        if (support == Support.BEDWARSPROXY) return p(player, PrivateGames.getBwProxyApi().getLanguageUtil().getMsg(player, path));
        else if (support == Support.BEDWARS1058) return p(player, PrivateGames.getBw1058Api().getPlayerLanguage(player).getString(path));
        else if (support == Support.BEDWARS2023) return p(player, PrivateGames.getBw2023Api().getPlayerLanguage(player).getString(path));
        else return null;
    }

    public static List<String> getList(Player player, String path) {
        if (support == Support.BEDWARSPROXY) return p(player, PrivateGames.getBwProxyApi().getLanguageUtil().getList(player, path));
        else if (support == Support.BEDWARS1058) return p(player, PrivateGames.getBw1058Api().getPlayerLanguage(player).getList(path));
        else if (support == Support.BEDWARS2023) return p(player, PrivateGames.getBw2023Api().getPlayerLanguage(player).getList(path));
        else return null;
    }

    public static void setItemId(ItemStack item, String customId) {
        ItemMeta meta = item.getItemMeta();
        meta.setLocalizedName(customId);
        item.setItemMeta(meta);
    }
}
