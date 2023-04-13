package me.notlewx.pgames.menu.submenus;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPrivateSettings;
import me.notlewx.pgames.menu.SettingsMenu;
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
import static me.notlewx.pgames.config.bedwars.MessagesData.ITEM_SUBMENU_SPEED_I_LORE;

public class SpeedMenu {
    private static final IPrivateSettings playerData = PGamesAPI.getPlayerData();
    public SpeedMenu(Player player) {
        openSpeedMenu(player);
    }
    public void openSpeedMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36,  Utility.getMSGLang(player, SUBMENU_SPEED_NAME));

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        ItemStack rfoot1 = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfoot1Meta = rfoot1.getItemMeta();
        ItemStack rfoot2 = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfoot2Meta = rfoot2.getItemMeta();
        ItemStack rfoot3 = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfoot3Meta = rfoot3.getItemMeta();
        ItemStack rfoot4 = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfoot4Meta = rfoot4.getItemMeta();


        rfoot1Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME));
        rfoot1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        rfoot1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        rfoot2Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_NAME));
        rfoot2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        rfoot2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        rfoot3Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME));
        rfoot3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        rfoot3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        rfoot4Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME));
        rfoot4Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        rfoot4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMSGLang(player, SUBMENU_SPEED_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getListLang(player, SUBMENU_SPEED_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        rfoot1.setItemMeta(rfoot1Meta);
        rfoot2.setItemMeta(rfoot2Meta);
        rfoot3.setItemMeta(rfoot3Meta);
        rfoot4.setItemMeta(rfoot4Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getSpeedLevel(player)) {
            case 0 :
                break;
            case 1 :
                rfoot1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                rfoot1.setItemMeta(rfoot1Meta);
                rfoot1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                rfoot2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                rfoot2.setItemMeta(rfoot2Meta);
                rfoot2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                rfoot3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                rfoot3.setItemMeta(rfoot3Meta);
                rfoot3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 4 :
                rfoot4Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                rfoot4.setItemMeta(rfoot4Meta);
                rfoot4.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(10, rfoot1);
        inventory.setItem(12, rfoot2);
        inventory.setItem(14, rfoot3);
        inventory.setItem(16, rfoot4);
        inventory.setItem(31, arrow);

        player.openInventory(inventory);
    }
    public static void closeSpeedMenu(Player player) {
        player.closeInventory();
        new SettingsMenu(player);
    }
}
