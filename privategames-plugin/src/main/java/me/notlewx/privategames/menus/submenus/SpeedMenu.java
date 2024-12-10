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

public class SpeedMenu implements GUIHolder {

    private final IPlayerSettings playerData;
    private final VersionSupport vs;
    private final Player player;
    private Inventory inventory;

    public SpeedMenu(Player p) {
        this.player = p;
        this.vs = PrivateGames.getVersionSupport();
        playerData = api.getPrivatePlayer(p).getPlayerSettings();
        try {
            createInventory();
            addContents();
            player.openInventory(inventory);
        } catch (Exception e) {
            throw new RuntimeException("Error while opening the speed menu", e);
        }
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(this, 9 * mainConfig.getInt(SPEED_MENU_ROWS), Utility.getMsg(player, SUBMENU_SPEED_NAME));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public void addContents() {
        Material arrowMat = Material.getMaterial(mainConfig.getString(SPEED_BACK_MATERIAL));
        ItemStack arrow = new ItemStack(arrowMat, 1, (byte) mainConfig.getInt(SPEED_BACK_ID));
        if (vs.isPlayerHead(arrow)) arrow = Utility.getSkull(mainConfig.getString(SPEED_BACK_HEAD_URL));
        ItemMeta arrowMeta = arrow.getItemMeta();

        Material book1Mat = Material.getMaterial(mainConfig.getString(SPEED_LEVEL_I_MATERIAL));
        ItemStack book1 = new ItemStack(book1Mat, 1, (byte) mainConfig.getInt(SPEED_LEVEL_I_ID));
        if (vs.isPlayerHead(book1)) book1 = Utility.getSkull(mainConfig.getString(SPEED_LEVEL_I_HEAD_URL));
        ItemMeta book1Meta = book1.getItemMeta();

        Material book2Mat = Material.getMaterial(mainConfig.getString(SPEED_LEVEL_II_MATERIAL));
        ItemStack book2 = new ItemStack(book2Mat, 1, (byte) mainConfig.getInt(SPEED_LEVEL_II_ID));
        if (vs.isPlayerHead(book2)) book2 = Utility.getSkull(mainConfig.getString(SPEED_LEVEL_II_HEAD_URL));
        ItemMeta book2Meta = book1.getItemMeta();

        Material book3Mat = Material.getMaterial(mainConfig.getString(SPEED_LEVEL_III_MATERIAL));
        ItemStack book3 = new ItemStack(book3Mat, 1, (byte) mainConfig.getInt(SPEED_LEVEL_III_ID));
        if (vs.isPlayerHead(book3)) book3 = Utility.getSkull(mainConfig.getString(SPEED_LEVEL_III_HEAD_URL));
        ItemMeta book3Meta = book1.getItemMeta();

        Material book4Mat = Material.getMaterial(mainConfig.getString(SPEED_LEVEL_IV_MATERIAL));
        ItemStack book4 = new ItemStack(book4Mat, 1, (byte) mainConfig.getInt(SPEED_LEVEL_IV_ID));
        if (vs.isPlayerHead(book4)) book4 = Utility.getSkull(mainConfig.getString(SPEED_LEVEL_IV_HEAD_URL));
        ItemMeta book4Meta = book4.getItemMeta();


        book1Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_SPEED_I_NAME));
        book1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book2Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_SPEED_II_NAME));
        book2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book3Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_SPEED_III_NAME));
        book3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book3Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book4Meta.setDisplayName(Utility.getMsg(player, ITEM_SUBMENU_SPEED_IV_NAME));
        book4Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        book4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        arrowMeta.setDisplayName(Utility.getMsg(player, SUBMENU_SPEED_BACK_ITEM_NAME));
        arrowMeta.setLore(Utility.getList(player, SUBMENU_SPEED_BACK_ITEM_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        arrowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        book1.setItemMeta(book1Meta);
        book2.setItemMeta(book2Meta);
        book3.setItemMeta(book3Meta);
        book4.setItemMeta(book4Meta);

        arrow.setItemMeta(arrowMeta);

        switch (playerData.getSpeedLevel()) {
            case 0 :
                break;
            case 1 :
                book1Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book1.setItemMeta(book1Meta);
                book1.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 2 :
                book2Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book2.setItemMeta(book2Meta);
                book2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 3 :
                book3Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book3.setItemMeta(book3Meta);
                book3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
            case 4 :
                book4Meta.setLore(Utility.getList(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                book4.setItemMeta(book4Meta);
                book4.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                break;
        }

        inventory.setItem(mainConfig.getInt(SPEED_LEVEL_I_POSITION), vs.setItemTag(book1, "pg", "speed-1"));
        inventory.setItem(mainConfig.getInt(SPEED_LEVEL_II_POSITION), vs.setItemTag(book2, "pg", "speed-2"));
        inventory.setItem(mainConfig.getInt(SPEED_LEVEL_III_POSITION), vs.setItemTag(book3, "pg", "speed-3"));
        inventory.setItem(mainConfig.getInt(SPEED_LEVEL_IV_POSITION), vs.setItemTag(book4, "pg", "speed-4"));
        inventory.setItem(mainConfig.getInt(SPEED_BACK_POSITION), vs.setItemTag(arrow, "pg", "back"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) return;

        e.setCancelled(true);
        if (!e.getView().getTitle().equals(Utility.getMsg(player, SUBMENU_SPEED_NAME))) return;

        if (tag.startsWith("speed-")) {
            playerData.setSpeedLevel(Integer.parseInt(tag.split("-")[1]));
            new SpeedMenu(player);
        } else if (tag.equals("back")) new SettingsMenu(player);
    }
}
