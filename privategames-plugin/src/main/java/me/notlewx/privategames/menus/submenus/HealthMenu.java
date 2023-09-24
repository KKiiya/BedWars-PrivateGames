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

import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.bedwars1058.MainConfig.*;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;

public class HealthMenu implements GUIHolder {
    private Inventory inventory;
    private final Player player;
    private final IPlayerSettings playerData;

    public HealthMenu(Player p) {
        this.player = p;
        playerData = new PrivatePlayer(player).getPlayerSettings();
        createInventory();
        addContents();
        player.openInventory(inventory);
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(this, 9 * mainConfig.getInt(HEALTH_BUFF_MENU_ROWS), Utility.getMsg(player, SUBMENU_HEALTH_BUFF_NAME));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public void addContents() {
        ItemStack arrow = new ItemStack(Material.getMaterial(mainConfig.getString(HEALTH_BUFF_BACK_MATERIAL )));
        ItemMeta arrowMeta = arrow.getItemMeta();
        ItemStack gapple1 = new ItemStack(Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_I_MATERIAL)));
        ItemMeta gapple1Meta = gapple1.getItemMeta();
        ItemStack gapple2 = new ItemStack(Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_II_MATERIAL)));
        ItemMeta gapple2Meta = gapple2.getItemMeta();
        ItemStack gapple3 = new ItemStack(Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_III_MATERIAL)));
        ItemMeta gapple3Meta = gapple3.getItemMeta();


        gapple1Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME));
        gapple1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple2Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME));
        gapple2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple3Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_HEALTH_BUFF_III_NAME));
        gapple3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        gapple3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMsg(player, SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getList(player, SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gapple1.setItemMeta(gapple1Meta);
        gapple2.setItemMeta(gapple2Meta);
        gapple3.setItemMeta(gapple3Meta);
        arrow.setItemMeta(arrowMeta);

        switch (playerData.getHealthBuffLevel()) {
            case 0 :
                break;
            case 1 :
                gapple1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple1.setItemMeta(gapple1Meta);
                gapple1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                gapple2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple2.setItemMeta(gapple2Meta);
                gapple2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                gapple3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                gapple3.setItemMeta(gapple3Meta);
                gapple3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_I_POSITION), gapple1);
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_II_POSITION), gapple2);
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_III_POSITION), gapple3);
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_BACK_POSITION), arrow);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(player, SUBMENU_HEALTH_BUFF_NAME))) {
            if (e.getSlot() == mainConfig.getInt(HEALTH_BUFF_LEVEL_I_POSITION)) {
                playerData.setHealthBuffLevel(1);
                new HealthMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(HEALTH_BUFF_LEVEL_II_POSITION)) {
                playerData.setHealthBuffLevel(2);
                new HealthMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(HEALTH_BUFF_LEVEL_III_POSITION)) {
                playerData.setHealthBuffLevel(3);
                new HealthMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(HEALTH_BUFF_BACK_POSITION)) {
                new SettingsMenu(player);
            }
        }
    }
}
