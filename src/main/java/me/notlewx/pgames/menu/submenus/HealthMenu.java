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
import static me.notlewx.pgames.config.bedwars.MessagesData.ITEM_SUBMENU_HEALTH_BUFF_I_LORE;

public class HealthMenu {
    private static final IPrivateSettings playerData = PGamesAPI.getPlayerData();
    public HealthMenu(Player player) {
        openHealthBuffMenu(player);
    }
    public void openHealthBuffMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36,  Utility.getMSGLang(player, SUBMENU_HEALTH_BUFF_NAME));

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        ItemStack gapple1 = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gapple1Meta = gapple1.getItemMeta();
        ItemStack gapple2 = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gapple2Meta = gapple2.getItemMeta();
        ItemStack gapple3 = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gapple3Meta = gapple3.getItemMeta();


        gapple1Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME));
        gapple1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple2Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME));
        gapple2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple3Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_NAME));
        gapple3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMSGLang(player, SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getListLang(player, SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple1.setItemMeta(gapple1Meta);
        gapple2.setItemMeta(gapple2Meta);
        gapple3.setItemMeta(gapple3Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getHBLevel(player)) {
            case 0 :
                break;
            case 1 :
                gapple1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple1.setItemMeta(gapple1Meta);
                gapple1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                gapple2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple2.setItemMeta(gapple2Meta);
                gapple2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                gapple3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple3.setItemMeta(gapple3Meta);
                gapple3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(11, gapple1);
        inventory.setItem(13, gapple2);
        inventory.setItem(15, gapple3);
        inventory.setItem(31, arrow);

        player.openInventory(inventory);
    }
    public static void closeHealthBuffMenu(Player player) {
        player.closeInventory();
        new SettingsMenu(player);
    }
}
