package me.notlewx.privategames.menus.submenus;

import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.config.bedwars1058.MessagesData;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.player.PrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.MainConfig.*;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;

public class EventsTimeMenu implements GUIHolder {
    private Inventory inventory;
    private final Player player;
    private final IPlayerSettings playerData;

    public EventsTimeMenu(Player p) {
        this.player = p;
        playerData = api.getPrivatePlayer(p).getPlayerSettings();
        try {
            createInventory();
            addContents();
            player.openInventory(inventory);
        } catch (Exception e) {
            throw new RuntimeException("Error while opening the events time menu", e);
        }
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(this, 9 * mainConfig.getInt(EVENTS_TIME_MENU_ROWS), Utility.getMsg(player, MessagesData.SUBMENU_EVENTS_TIME_NAME));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public void addContents() {
        Material arrowMat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_BACK_MATERIAL));
        ItemStack arrow = new ItemStack(arrowMat, 1, (byte) mainConfig.getInt(EVENTS_TIME_BACK_ID));
        if (arrow.getType().toString().equals("SKULL_ITEM") || arrow.getType().toString().equals("LEGACY_SKULL_ITEM") && arrow.getDurability() == 3) {
            arrow = Utility.getSkull(arrowMat, mainConfig.getString(EVENTS_TIME_BACK_HEAD_URL));
        }
        ItemMeta arrowMeta = arrow.getItemMeta();

        Material book1Mat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_LEVEL_I_MATERIAL));
        ItemStack book1 = new ItemStack(book1Mat, 1, (byte) mainConfig.getInt(EVENTS_TIME_LEVEL_I_ID));
        if (book1.getType().toString().equals("SKULL_ITEM") || book1.getType().toString().equals("LEGACY_SKULL_ITEM") && book1.getDurability() == 3) {
            book1 = Utility.getSkull(book1Mat, mainConfig.getString(EVENTS_TIME_LEVEL_I_HEAD_URL));
        }
        ItemMeta book1Meta = book1.getItemMeta();

        Material book2Mat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_LEVEL_II_MATERIAL));
        ItemStack book2 = new ItemStack(book2Mat, 1, (byte) mainConfig.getInt(EVENTS_TIME_LEVEL_II_ID));
        if (book2.getType().toString().equals("SKULL_ITEM") || book2.getType().toString().equals("LEGACY_SKULL_ITEM") && book2.getDurability() == 3) {
            book2 = Utility.getSkull(book2Mat, mainConfig.getString(EVENTS_TIME_LEVEL_II_HEAD_URL));
        }
        ItemMeta book2Meta = book1.getItemMeta();

        Material book3Mat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_LEVEL_III_MATERIAL));
        ItemStack book3 = new ItemStack(book3Mat, 1, (byte) mainConfig.getInt(EVENTS_TIME_LEVEL_III_ID));
        if (book3.getType().toString().equals("SKULL_ITEM") || book3.getType().toString().equals("LEGACY_SKULL_ITEM") && book3.getDurability() == 3) {
            book3 = Utility.getSkull(book3Mat, mainConfig.getString(EVENTS_TIME_LEVEL_III_HEAD_URL));
        }
        ItemMeta book3Meta = book1.getItemMeta();

        Material book4Mat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_LEVEL_IV_MATERIAL));
        ItemStack book4 = new ItemStack(book4Mat, 1, (byte) mainConfig.getInt(EVENTS_TIME_LEVEL_IV_ID));
        if (book4.getType().toString().equals("SKULL_ITEM") || book4.getType().toString().equals("LEGACY_SKULL_ITEM") && book4.getDurability() == 3) {
            book4 = Utility.getSkull(book4Mat, mainConfig.getString(EVENTS_TIME_LEVEL_IV_HEAD_URL));
        }
        ItemMeta book4Meta = book4.getItemMeta();


        book1Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_EVENTS_TIME_I_NAME));
        book1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book2Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_EVENTS_TIME_II_NAME));
        book2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book3Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_EVENTS_TIME_III_NAME));
        book3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book4Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_EVENTS_TIME_IV_NAME));
        book4Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMsg(player, SUBMENU_EVENTS_TIME_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getList(player, SUBMENU_EVENTS_TIME_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book1.setItemMeta(book1Meta);
        book2.setItemMeta(book2Meta);
        book3.setItemMeta(book3Meta);
        book4.setItemMeta(book4Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getEventsTimeLevel()) {
            case 0 :
                break;
            case 1 :
                book1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book1.setItemMeta(book1Meta);
                book1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                book2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book2.setItemMeta(book2Meta);
                book2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                book3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book3.setItemMeta(book3Meta);
                book3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 4 :
                book4Meta.setLore(Utility.getList(player, ITEM_SUBMENU_EVENTS_TIME_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book4.setItemMeta(book4Meta);
                book4.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(mainConfig.getInt(EVENTS_TIME_LEVEL_I_POSITION), book1);
        inventory.setItem(mainConfig.getInt(EVENTS_TIME_LEVEL_II_POSITION), book2);
        inventory.setItem(mainConfig.getInt(EVENTS_TIME_LEVEL_III_POSITION), book3);
        inventory.setItem(mainConfig.getInt(EVENTS_TIME_LEVEL_IV_POSITION), book4);
        inventory.setItem(mainConfig.getInt(EVENTS_TIME_BACK_POSITION), arrow);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(player, SUBMENU_EVENTS_TIME_NAME))) {
            if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_LEVEL_I_POSITION)) {
                playerData.setEventsTimeLevel(1);
                new EventsTimeMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_LEVEL_II_POSITION)) {
                playerData.setEventsTimeLevel(2);
                new EventsTimeMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_LEVEL_III_POSITION)) {
                playerData.setEventsTimeLevel(3);
                new EventsTimeMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_LEVEL_IV_POSITION)) {
                playerData.setEventsTimeLevel(4);
                new EventsTimeMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_BACK_POSITION)) {
                new SettingsMenu(player);
            }
        }
    }
}
