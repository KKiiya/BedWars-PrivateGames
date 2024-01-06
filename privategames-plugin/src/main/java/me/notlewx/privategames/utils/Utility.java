package me.notlewx.privategames.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.clip.placeholderapi.PlaceholderAPI;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.config.bedwars2023.MessagesData;
import me.notlewx.privategames.support.Support;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.*;

public class Utility {
    public static void info(String infoLog) {
        PrivateGames.getPlugins().getLogger().info(c(infoLog));
    }
    public static void warn(String warnLog) {
        PrivateGames.getPlugins().getLogger().warning(c(warnLog));
    }
    public static void debug(String debugLog) {
        if (!mainConfig.getBoolean("debug")) return;
        PrivateGames.getPlugins().getLogger().info(c("DEBUG: " + debugLog));
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
        else if (support == Support.BEDWARSPROXY2023) return p(player, PrivateGames.getBwProxy2023Api().getLanguageUtil().getMsg(player, path));
        else return c("&cMISSING");
    }

    public static void sendJoinRequestMessage(Player p, UUID requester) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(requester);
        TextComponent textComponent = new TextComponent("");

        TextComponent accept = new TextComponent(Utility.getMsg(p, MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT).replace("{requester}", op.getName()));
        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utility.getMsg(p, MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER).replace("{requester}", op.getName())).create()));
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pg accept " + op.getName()));

        TextComponent deny = new TextComponent(Utility.getMsg(p, MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_DENY).replace("{requester}", op.getName()));
        deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utility.getMsg(p, MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER).replace("{requester}", op.getName())).create()));
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pg deny " + op.getName()));

        textComponent.addExtra(accept);
        textComponent.addExtra(" ");
        textComponent.addExtra(deny);

        for (String s : Utility.getList(p, MessagesData.PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED)) {
            if (!s.equals("{buttons}")) {
                p.sendMessage(s.replace("{player}", op.getName()));
            } else {
                p.spigot().sendMessage(textComponent);
            }
        }
    }



    public static List<String> getList(Player player, String path) {
        if (support == Support.BEDWARSPROXY) return p(player, PrivateGames.getBwProxyApi().getLanguageUtil().getList(player, path));
        else if (support == Support.BEDWARS1058) return p(player, PrivateGames.getBw1058Api().getPlayerLanguage(player).getList(path));
        else if (support == Support.BEDWARS2023) return p(player, PrivateGames.getBw2023Api().getPlayerLanguage(player).getList(path));
        else if (support == Support.BEDWARSPROXY2023) return p(player, PrivateGames.getBwProxy2023Api().getLanguageUtil().getList(player, path));
        else return List.of(c("&cMISSING"));
    }

    public static void giveSpeedLevel(Player player) {
        if (!api.getPrivateArenaUtil().isPlaying(player)) return;
        IPrivatePlayer owner = api.getPrivatePlayer((Player) api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
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
        if (!api.getPrivateArenaUtil().isPlaying(player)) return;
        IPrivatePlayer owner = api.getPrivatePlayer((Player) api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
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
        if (!api.getPrivateArenaUtil().isPlaying(player)) return;
        IPrivatePlayer owner = api.getPrivatePlayer((Player) api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getPrivateArenaHost().getPlayer());
        if (owner.getPlayerSettings().isLowGravityEnabled())
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
            }, 20L);
    }

    public static ItemStack getSkull(Material mat, String url) {
        ItemStack skull = new ItemStack(mat, 1, (short) 3);

        if (url == null || url.isEmpty())
            return skull;

        ItemMeta skullMeta = skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }

        profileField.setAccessible(true);

        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getName().split("\\.")[3];
    }

    public static String getForCurrentVersion(String v18, String v12, String v13) {
        switch (getServerVersion()) {
            case "v1_8_R3":
                return v18;
            case "v1_12_R1":
                return v12;
            default:
                return v13;
        }
    }
}
