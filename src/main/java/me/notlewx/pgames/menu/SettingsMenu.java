package me.notlewx.pgames.menu;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.stream.Collectors;
import static me.notlewx.pgames.config.bedwars.MessagesData.*;

public class SettingsMenu {
    private static Inventory inventory;
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    public SettingsMenu(Player player) {
        openMenu(player);
    }
    public void openMenu(Player player) {
        inventory = Bukkit.createInventory(null, 54, Utility.getMSGLang(player, MAIN_MENU_NAME));

        ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dswordMeta = dsword.getItemMeta();
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gappleMeta = gapple.getItemMeta();
        ItemStack quartz = new ItemStack(Material.QUARTZ);
        ItemMeta quartzMeta = quartz.getItemMeta();
        ItemStack rfoot = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfootMeta = rfoot.getItemMeta();

        ItemStack ipaper = new ItemStack(Material.PAPER);
        ItemMeta ipaperMeta = ipaper.getItemMeta();
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = book.getItemMeta();
        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemMeta emeraldMeta = emerald.getItemMeta();

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        ItemStack gblock = new ItemStack(Material.GRASS);
        ItemMeta gblockMeta = gblock.getItemMeta();
        ItemStack bed = new ItemStack(Material.BED);
        ItemMeta bedMeta = bed.getItemMeta();
        ItemStack dblock = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta dblockMeta = dblock.getItemMeta();

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();

            dswordMeta.setDisplayName(Utility.getMSGLang(player, ITEM_ONE_HIT_ONE_KILL_NAME));
            if (playerData.isOHOKEnabled(player)) {
                dswordMeta.setLore(Utility.getListLang(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else {
                dswordMeta.setLore(Utility.getListLang(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            dswordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

            gappleMeta.setDisplayName(Utility.getMSGLang(player, ITEM_HEALTH_BUFF_LEVEL_NAME));
            gappleMeta.setLore(Utility.getListLang(player, ITEM_HEALTH_BUFF_LEVEL_LORE));

            quartzMeta.setDisplayName(Utility.getMSGLang(player, ITEM_LOW_GRAVITY_NAME));
            if (playerData.isLGEnabled(player)) {
                quartzMeta.setLore(Utility.getListLang(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isLGEnabled(player)) {
                quartzMeta.setLore(Utility.getListLang(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            quartzMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            rfootMeta.setDisplayName(Utility.getMSGLang(player, ITEM_SPEED_NAME));
            rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE));

            ipaperMeta.setDisplayName(Utility.getMSGLang(player, ITEM_RESPAWN_EVENT_TIME_NAME));
            ipaperMeta.setLore(Utility.getListLang(player, ITEM_RESPAWN_EVENT_TIME_LORE));

            bookMeta.setDisplayName(Utility.getMSGLang(player, ITEM_EVENTS_TIME_LEVEL_NAME));
            bookMeta.setLore(Utility.getListLang(player, ITEM_EVENTS_TIME_LEVEL_LORE));

            gblockMeta.setDisplayName(Utility.getMSGLang(player, ITEM_ALLOW_MAP_BREAK_NAME));
            if (playerData.isAMBEnabled(player)) {
                gblockMeta.setLore(Utility.getListLang(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isAMBEnabled(player)) {
                gblockMeta.setLore(Utility.getListLang(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            gblockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            emeraldMeta.setDisplayName(Utility.getMSGLang(player, ITEM_NO_EMERALDS_NAME));
            if (playerData.isNEEnabled(player)) {
                emeraldMeta.setLore(Utility.getListLang(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isNEEnabled(player)) {
                emeraldMeta.setLore(Utility.getListLang(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            emeraldMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            bedMeta.setDisplayName(Utility.getMSGLang(player, ITEM_BED_INSTA_BREAK_NAME));
            if (playerData.isBIBEnabled(player)) {
                bedMeta.setLore(Utility.getListLang(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isBIBEnabled(player)) {
                bedMeta.setLore(Utility.getListLang(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            bedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            diamondMeta.setDisplayName(Utility.getMSGLang(player, ITEM_NO_DIAMONDS_NAME));
            if (playerData.isNDEnabled(player)) {
                diamondMeta.setLore(Utility.getListLang(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isNDEnabled(player)) {
                diamondMeta.setLore(Utility.getListLang(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            diamondMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            dblockMeta.setDisplayName(Utility.getMSGLang(player, ITEM_MAX_TEAM_UPGRADES_NAME));
            if (playerData.isMTUEnabled(player)) {
                dblockMeta.setLore(Utility.getListLang(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
            } else if (!playerData.isMTUEnabled(player)) {
                dblockMeta.setLore(Utility.getListLang(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
            }
            dblockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            arrowMeta.setDisplayName(Utility.getMSGLang(player, MENU_BACK_ITEM_NAME));
            arrowMeta.setLore(Utility.getListLang(player, MENU_BACK_ITEM_LORE));


        switch (playerData.getRETLevel(player)) {
            case 0 :
            case 2:
                ipaperMeta.setLore(Utility.getListLang(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, RESPAWN_EVENT_TIME_II_MEANING))).collect(Collectors.toList()));
            break;
            case 1:
                ipaperMeta.setLore(Utility.getListLang(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, RESPAWN_EVENT_TIME_I_MEANING))).collect(Collectors.toList()));
            break;
            case 3:
                ipaperMeta.setLore(Utility.getListLang(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, RESPAWN_EVENT_TIME_III_MEANING))).collect(Collectors.toList()));
            break;
        }
        switch (playerData.getHBLevel(player)) {
            case 0:
            case 1:
                gappleMeta.setLore(Utility.getListLang(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, NORMAL_HEALTH_MEANING))).collect(Collectors.toList()));
            break;
            case 2:
                gappleMeta.setLore(Utility.getListLang(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, DOUBLE_HEALTH_MEANING))).collect(Collectors.toList()));
            break;
            case 3:
                gappleMeta.setLore(Utility.getListLang(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, TRIPLE_HEALTH_MEANING))).collect(Collectors.toList()));
            break;
        }
        switch (playerData.getSpeedLevel(player)) {
            case 0:
            case 1:
                rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, NO_SPEED_MEANING))).collect(Collectors.toList()));
                break;
            case 2:
                rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, SPEED_I_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, SPEED_II_MEANING))).collect(Collectors.toList()));
                break;
            case 4:
                rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, SPEED_III_MEANING))).collect(Collectors.toList()));
                break;
        }
        switch (playerData.getETLevel(player)) {
            case 0:
            case 2:
                bookMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, EVENTS_TIME_NORMAL_MEANING))).collect(Collectors.toList()));
            break;
            case 1:
                bookMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, EVENTS_TIME_SLOWER_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                bookMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, EVENTS_TIME_FAST_MEANING))).collect(Collectors.toList()));
                break;
            case 4:
                bookMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, EVENTS_TIME_FASTER_MEANING))).collect(Collectors.toList()));
                break;
        }
        dsword.setItemMeta(dswordMeta);
        gapple.setItemMeta(gappleMeta);
        quartz.setItemMeta(quartzMeta);
        rfoot.setItemMeta(rfootMeta);

        ipaper.setItemMeta(ipaperMeta);
        book.setItemMeta(bookMeta);
        emerald.setItemMeta(emeraldMeta);

        diamond.setItemMeta(diamondMeta);
        gblock.setItemMeta(gblockMeta);
        bed.setItemMeta(bedMeta);
        dblock.setItemMeta(dblockMeta);

        arrow.setItemMeta(arrowMeta);

        if (playerData.isAMBEnabled(player)) gblock.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isAMBEnabled(player)) gblock.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNEEnabled(player)) emerald.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isNEEnabled(player)) emerald.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNDEnabled(player)) diamond.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isNDEnabled(player)) diamond.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isBIBEnabled(player)) bed.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isBIBEnabled(player)) bed.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isOHOKEnabled(player)) dsword.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isOHOKEnabled(player)) dsword.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isLGEnabled(player)) quartz.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isLGEnabled(player)) quartz.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isMTUEnabled(player)) dblock.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isMTUEnabled(player)) dblock.removeEnchantment(Enchantment.DURABILITY);
        inventory.setItem(10, dsword);
        inventory.setItem(12, gapple);
        inventory.setItem(14, quartz);
        inventory.setItem(16, rfoot);

        inventory.setItem(20, ipaper);
        inventory.setItem(22, book);
        inventory.setItem(24, emerald);

        inventory.setItem(28, diamond);
        inventory.setItem(30, gblock);
        inventory.setItem(32, bed);
        inventory.setItem(34, dblock);

        inventory.setItem(49, arrow);

        player.openInventory(inventory);
    }
    public static void closeMenu(Player player) {
        player.closeInventory();
    }
    public static Inventory getInventory() {
        return inventory;
    }
}
