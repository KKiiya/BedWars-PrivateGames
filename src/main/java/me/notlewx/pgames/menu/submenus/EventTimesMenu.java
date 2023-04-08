package me.notlewx.pgames.menu.submenus;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
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

public class EventTimesMenu {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    public EventTimesMenu(Player player) {
        openEventsTimeMenu(player);
    }
    public void openEventsTimeMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36, Utility.getMSGLang(player, SUBMENU_EVENTS_TIME_NAME));
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        ItemStack book1 = new ItemStack(Material.BOOK);
        ItemMeta book1Meta = book1.getItemMeta();
        ItemStack book2 = new ItemStack(Material.BOOK);
        ItemMeta book2Meta = book2.getItemMeta();
        ItemStack book3 = new ItemStack(Material.BOOK);
        ItemMeta book3Meta = book3.getItemMeta();
        ItemStack book4 = new ItemStack(Material.BOOK);
        ItemMeta book4Meta = book4.getItemMeta();


        book1Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_EVENTS_TIME_I_NAME));
        book1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book2Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_EVENTS_TIME_II_NAME));
        book2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book3Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_EVENTS_TIME_III_NAME));
        book3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book4Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_EVENTS_TIME_IV_NAME));
        book4Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMSGLang(player, SUBMENU_EVENTS_TIME_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getListLang(player, SUBMENU_EVENTS_TIME_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book1.setItemMeta(book1Meta);
        book2.setItemMeta(book2Meta);
        book3.setItemMeta(book3Meta);
        book4.setItemMeta(book4Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getETLevel(player)) {
            case 0 :
                break;
            case 1 :
                book1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book1.setItemMeta(book1Meta);
                book1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                book2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book2.setItemMeta(book2Meta);
                book2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                book3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book3.setItemMeta(book3Meta);
                book3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 4 :
                book4Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_EVENTS_TIME_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book4.setItemMeta(book4Meta);
                book4.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(10, book1);
        inventory.setItem(12, book2);
        inventory.setItem(14, book3);
        inventory.setItem(16, book4);
        inventory.setItem(31, arrow);

        player.openInventory(inventory);
    }
    public static void closeEventsTimeMenu(Player player) {
        player.closeInventory();
        new SettingsMenu(player);
    }
}
