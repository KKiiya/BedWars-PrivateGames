package me.notlewx.privategames.menus.submenus;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.support.VersionSupport;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.menus.SettingsMenu;
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

public class HealthMenu implements GUIHolder {

    private final VersionSupport vs;
    private final IPlayerSettings playerData;
    private final Player player;
    private Inventory inventory;


    public HealthMenu(Player p) {
        this.player = p;
        this.vs = PrivateGames.getVersionSupport();
        playerData = api.getPrivatePlayer(p).getPlayerSettings();
        try {
            createInventory();
            addContents();
            player.openInventory(inventory);
        } catch (Exception e) {
            throw new RuntimeException("Error while opening the health menu", e);
        }
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(this, 9 * mainConfig.getInt(HEALTH_BUFF_MENU_ROWS), Utility.getMsg(player, SUBMENU_HEALTH_BUFF_NAME));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public void addContents() {
        Material arrowMat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_BACK_MATERIAL));
        ItemStack arrow = new ItemStack(arrowMat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_BACK_ID));
        if (vs.isPlayerHead(arrow)) arrow = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_BACK_HEAD_URL));
        ItemMeta arrowMeta = arrow.getItemMeta();

        Material book1Mat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_I_MATERIAL));
        ItemStack gapple1 = new ItemStack(book1Mat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_LEVEL_I_ID));
        if (vs.isPlayerHead(gapple1)) gapple1 = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_LEVEL_I_HEAD_URL));
        ItemMeta gapple1Meta = gapple1.getItemMeta();

        Material book2Mat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_II_MATERIAL));
        ItemStack gapple2 = new ItemStack(book2Mat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_LEVEL_II_ID));
        if (vs.isPlayerHead(gapple2)) gapple2 = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_LEVEL_II_HEAD_URL));
        ItemMeta gapple2Meta = gapple2.getItemMeta();

        Material book3Mat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_LEVEL_III_MATERIAL));
        ItemStack gapple3 = new ItemStack(book3Mat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_LEVEL_III_ID));
        if (vs.isPlayerHead(gapple3)) gapple3 = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_LEVEL_III_HEAD_URL));
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

        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_I_POSITION), vs.setItemTag(gapple1, "pg", "health-1"));
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_II_POSITION), vs.setItemTag(gapple2, "pg", "health-2"));
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_LEVEL_III_POSITION), vs.setItemTag(gapple3, "pg", "health-3"));
        inventory.setItem(mainConfig.getInt(HEALTH_BUFF_BACK_POSITION), vs.setItemTag(arrow, "pg", "back"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) return;

        e.setCancelled(true);
        if (!e.getView().getTitle().equals(Utility.getMsg(player, SUBMENU_HEALTH_BUFF_NAME))) return;

        if (tag.startsWith("health-")) {
            int level = Integer.parseInt(tag.split("-")[1]);
            playerData.setHealthBuffLevel(level);
            new HealthMenu(player);
        } else if (tag.equalsIgnoreCase("back")) new SettingsMenu(player);
    }
}
