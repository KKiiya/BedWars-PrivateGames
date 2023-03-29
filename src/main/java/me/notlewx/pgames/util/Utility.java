package me.notlewx.pgames.util;

import org.bukkit.ChatColor;

public class Utility {
    public static String colorizedString(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
