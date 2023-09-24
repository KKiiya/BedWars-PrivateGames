package me.notlewx.privategames.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.support.Support;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.List;
import java.util.stream.Collectors;
import static me.notlewx.privategames.PrivateGames.api;
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

    public static void giveSpeedLevel(Player player) {
        if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(player) == null) return;
        IPrivatePlayer owner = api.getPrivatePlayer(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
        switch (owner.getPlayerSettings().getSpeedLevel()) {
            case 0:
            case 1:
                break;
            case 2:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, true, false));
                },20L);
                break;
            case 3:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
                }, 20L);
                break;
            case 4:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true, false));
                }, 20L);
                break;
        }
    }

    public static void giveHealthBuff(Player player) {
        if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(player) == null) return;
        IPrivatePlayer owner = api.getPrivatePlayer(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
        switch (owner.getPlayerSettings().getHealthBuffLevel()) {
            case 0:
            case 1:
                player.setMaxHealth(20.0);
                player.setHealth(20.0);
                player.setHealthScale(20.0);
                break;
            case 2:
                player.setMaxHealth(40.0);
                player.setHealth(40.0);
                player.setHealthScale(40.0);
                break;
            case 3:
                player.setMaxHealth(60.0);
                player.setHealth(60.0);
                player.setHealthScale(60.0);
                break;
        }
    }

    public static void giveLongJump(Player player) {
        if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(player) == null) return;
        IPrivatePlayer owner = api.getPrivatePlayer(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
        if (owner.getPlayerSettings().isLowGravityEnabled())
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
            }, 20L);
    }
}
