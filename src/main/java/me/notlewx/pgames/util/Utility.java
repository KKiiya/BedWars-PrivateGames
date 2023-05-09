package me.notlewx.pgames.util;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPrivateSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.stream.Collectors;

public class Utility {
    private static IPrivateSettings ps = PGamesAPI.getPlayerData();
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
    public static void giveSpeedLevel(Player player) {
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player) == null) return;
        Player owner = PGamesAPI.getGameUtil().getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player).getArenaName());
        switch (ps.getSpeedLevel(owner)) {
            case 0:
            case 1:
                break;
            case 2:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 0, Integer.MAX_VALUE, true, false));
                },20L);
                break;
            case 3:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, Integer.MAX_VALUE, true, false));
                }, 20L);
                break;
            case 4:
                Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, Integer.MAX_VALUE, true, false));
                }, 20L);
                break;
        }
    }

    public static void giveHealthBuff(Player player) {
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player) == null) return;
        Player owner = PGamesAPI.getGameUtil().getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player).getArenaName());
        switch (ps.getHBLevel(owner)) {
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
        if (PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player) == null) return;
        Player owner = PGamesAPI.getGameUtil().getOwnerOfPrivateArena(PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player).getArenaName());
        if (ps.isLGEnabled(owner))
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2, Integer.MAX_VALUE));
            }, 20L);
    }
}
