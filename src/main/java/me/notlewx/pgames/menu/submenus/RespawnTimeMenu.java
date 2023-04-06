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

import static me.notlewx.pgames.config.MessagesData.*;
import static me.notlewx.pgames.config.MessagesData.ITEM_SUBMENU_RESPAWN_TIME_I_LORE;

public class RespawnTimeMenu {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    public RespawnTimeMenu(Player player) {
        openRespawnTimeMenu(player);
    }
    public void openRespawnTimeMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36,  Utility.getMSGLang(player, SUBMENU_RESPAWN_TIME_NAME));

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        ItemStack paper1 = new ItemStack(Material.PAPER);
        ItemMeta paper1Meta = paper1.getItemMeta();
        ItemStack paper2 = new ItemStack(Material.PAPER);
        ItemMeta paper2Meta = paper2.getItemMeta();
        ItemStack paper3 = new ItemStack(Material.PAPER);
        ItemMeta paper3Meta = paper3.getItemMeta();


        paper1Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_RESPAWN_TIME_I_NAME));
        paper1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        paper1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        paper2Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_RESPAWN_TIME_II_NAME));
        paper2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        paper2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        paper3Meta.setDisplayName(Utility.getMSGLang(player, ITEM_SUBMENU_RESPAWN_TIME_III_NAME));
        paper3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        paper3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMSGLang(player, SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getListLang(player, SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        paper1.setItemMeta(paper1Meta);
        paper2.setItemMeta(paper2Meta);
        paper3.setItemMeta(paper3Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getRETLevel(player)) {
            case 0 :
                break;
            case 1 :
                paper1Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                paper1.setItemMeta(paper1Meta);
                paper1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                paper2Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                paper2.setItemMeta(paper1Meta);
                paper2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                paper3Meta.setLore(Utility.getListLang(player, ITEM_SUBMENU_RESPAWN_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                paper3.setItemMeta(paper1Meta);
                paper3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(11, paper1);
        inventory.setItem(13, paper2);
        inventory.setItem(15, paper3);
        inventory.setItem(31, arrow);

        player.openInventory(inventory);
    }
    public static void closeRespawnTimeMenu(Player player) {
        player.closeInventory();
        new SettingsMenu(player);
    }
}
