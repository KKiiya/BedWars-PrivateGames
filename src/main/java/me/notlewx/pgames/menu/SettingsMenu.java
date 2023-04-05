package me.notlewx.pgames.menu;

import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static me.notlewx.pgames.config.MessagesData.*;

public class SettingsMenu {
    private static Inventory inventory;
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
            dswordMeta.setLore(Utility.getListLang(player, ITEM_ONE_HIT_ONE_KILL_LORE));
            dswordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            gappleMeta.setDisplayName(Utility.getMSGLang(player, ITEM_HEALTH_BUFF_LEVEL_NAME));
            gappleMeta.setLore(Utility.getListLang(player, ITEM_HEALTH_BUFF_LEVEL_LORE));

            quartzMeta.setDisplayName(Utility.getMSGLang(player, ITEM_LOW_GRAVITY_NAME));
            quartzMeta.setLore(Utility.getListLang(player, ITEM_LOW_GRAVITY_LORE));
            quartzMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            rfootMeta.setDisplayName(Utility.getMSGLang(player, ITEM_SPEED_NAME));
            rfootMeta.setLore(Utility.getListLang(player, ITEM_SPEED_LORE));

            ipaperMeta.setDisplayName(Utility.getMSGLang(player, ITEM_RESPAWN_EVENT_TIME_NAME));
            ipaperMeta.setLore(Utility.getListLang(player, ITEM_RESPAWN_EVENT_TIME_LORE));

            bookMeta.setDisplayName(Utility.getMSGLang(player, ITEM_EVENTS_TIME_LEVEL_NAME));
            bookMeta.setLore(Utility.getListLang(player, ITEM_EVENTS_TIME_LEVEL_LORE));

            gblockMeta.setDisplayName(Utility.getMSGLang(player, ITEM_ALLOW_MAP_BREAK_NAME));
            gblockMeta.setLore(Utility.getListLang(player, ITEM_ALLOW_MAP_BREAK_LORE));
            gblockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            emeraldMeta.setDisplayName(Utility.getMSGLang(player, ITEM_NO_EMERALDS_NAME));
            emeraldMeta.setLore(Utility.getListLang(player, ITEM_NO_EMERALDS_LORE));
            emeraldMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            bedMeta.setDisplayName(Utility.getMSGLang(player, ITEM_BED_INSTA_BREAK_NAME));
            bedMeta.setLore(Utility.getListLang(player, ITEM_BED_INSTA_BREAK_LORE));
            bedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            diamondMeta.setDisplayName(Utility.getMSGLang(player, ITEM_NO_DIAMONDS_NAME));
            diamondMeta.setLore(Utility.getListLang(player, ITEM_NO_DIAMONDS_LORE));
            diamondMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            dblockMeta.setDisplayName(Utility.getMSGLang(player, ITEM_MAX_TEAM_UPGRADES_NAME));
            dblockMeta.setLore(Utility.getListLang(player, ITEM_MAX_TEAM_UPGRADES_LORE));

            arrowMeta.setDisplayName(Utility.getMSGLang(player, MENU_BACK_ITEM_NAME));
            arrowMeta.setLore(Utility.getListLang(player, MENU_BACK_ITEM_LORE));


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
