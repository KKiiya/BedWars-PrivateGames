package me.notlewx.privategames.menus;

import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.menus.submenus.generators.GeneratorsMenu;
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
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class OptionsMenu implements GUIHolder {
    private Inventory inv;
    private Player p;

    public OptionsMenu(Player p) {
        this.p = p;
        createInventory();
        addContent();
        p.openInventory(inv);
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, mainConfig.getInt(OPTIONS_MENU_ROWS) * 9, Utility.getMsg(p, SUBMENU_OPTIONS_TITLE));
    }
    private void addContent() {
        Material generatorSettingsMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_GENERATORS_MATERIAL));
        ItemStack generatorSettings;
        if (generatorSettingsMaterial == Material.SKULL_ITEM) {
            generatorSettings = Utility.getSkull(mainConfig.getString(OPTIONS_GENERATORS_HEAD_URL));
        } else {
            generatorSettings = new ItemStack(generatorSettingsMaterial, 1, (short) mainConfig.getInt(OPTIONS_GENERATORS_ID));
        }
        ItemMeta generatorSettingsMeta = generatorSettings.getItemMeta();

        Material allowJoinMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_ALLOWJOIN_MATERIAL));
        ItemStack allowJoin;
        if (allowJoinMaterial == Material.SKULL_ITEM) {
            allowJoin = Utility.getSkull(mainConfig.getString(OPTIONS_ALLOWJOIN_HEAD_URL));
        } else {
            allowJoin = new ItemStack(allowJoinMaterial, 1, (short) mainConfig.getInt(OPTIONS_ALLOWJOIN_ID));
        }
        ItemMeta allowJoinMeta = allowJoin.getItemMeta();

        Material autoStartMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_ENABLE_AUTOSTART_MATERIAL));
        ItemStack autoStart;
        if (autoStartMaterial == Material.SKULL_ITEM) {
            autoStart = Utility.getSkull(mainConfig.getString(OPTIONS_ENABLE_AUTOSTART_HEAD_URL));
        } else {
            autoStart = new ItemStack(autoStartMaterial, 1, (short) mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_ID));
        }
        ItemMeta autoStartMeta = autoStart.getItemMeta();

        Material backMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_BACK_MATERIAL));
        ItemStack back;
        if (backMaterial == Material.SKULL_ITEM) {
            back = Utility.getSkull(mainConfig.getString(OPTIONS_BACK_HEAD_URL));
        } else {
            back = new ItemStack(backMaterial, 1, (short) mainConfig.getInt(OPTIONS_BACK_ID));
        }
        ItemMeta backMeta = back.getItemMeta();


        IPlayerOptions po = api.getPrivatePlayer(p).getPlayerOptions();

        generatorSettingsMeta.setDisplayName(Utility.getMsg(p, SUBMENU_OPTIONS_GENERATORS_NAME));
        generatorSettingsMeta.setLore(Utility.getList(p, SUBMENU_OPTIONS_GENERATORS_LORE));

        allowJoinMeta.setDisplayName(Utility.getMsg(p, SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME));
        allowJoinMeta.setLore(Utility.getList(p, SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE).stream().map(s -> s.replace("{state}", po.isAllowJoin() ? Utility.getMsg(p, SUBMENU_OPTIONS_MEANING_ENABLED) : Utility.getMsg(p, SUBMENU_OPTIONS_MEANING_DISABLED))).collect(Collectors.toList()));
        allowJoinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        autoStartMeta.setDisplayName(Utility.getMsg(p, SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME));
        autoStartMeta.setLore(Utility.getList(p, SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE).stream().map(s -> s.replace("{state}", po.isAutoStart() ? Utility.getMsg(p, SUBMENU_OPTIONS_MEANING_ENABLED) : Utility.getMsg(p, SUBMENU_OPTIONS_MEANING_DISABLED))).collect(Collectors.toList()));
        autoStartMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        backMeta.setDisplayName(Utility.getMsg(p, SUBMENU_OPTIONS_BACK_NAME));
        backMeta.setLore(Utility.getList(p, SUBMENU_OPTIONS_BACK_LORE));

        generatorSettings.setItemMeta(generatorSettingsMeta);
        allowJoin.setItemMeta(allowJoinMeta);
        autoStart.setItemMeta(autoStartMeta);

        if (po.isAllowJoin()) allowJoin.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else allowJoin.removeEnchantment(Enchantment.DURABILITY);
        if (po.isAutoStart()) autoStart.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else autoStart.removeEnchantment(Enchantment.DURABILITY);

        if (mainConfig.getBoolean(OPTIONS_GENERATORS)) if (api.getPrivateArenaUtil().isPlaying(p)) inv.setItem(mainConfig.getInt(OPTIONS_GENERATORS_POSITION), generatorSettings);
        if (mainConfig.getBoolean(OPTIONS_ALLOW_JOIN)) inv.setItem(mainConfig.getInt(OPTIONS_ALLOWJOIN_POSITION), allowJoin);
        if (mainConfig.getBoolean(OPTIONS_ENABLE_AUTOSTART)) inv.setItem(mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_POSITION), autoStart);
        inv.setItem(mainConfig.getInt(OPTIONS_BACK_POSITION), back);
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(p, SUBMENU_OPTIONS_TITLE))) {
            if (e.getSlot() == mainConfig.getInt(OPTIONS_GENERATORS_POSITION)) {
                new GeneratorsMenu(p, api.getPrivateArenaUtil().getPrivateArenaByPlayer(p).getArenaName());
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_ALLOWJOIN_POSITION)) {
                IPrivatePlayer pp = api.getPrivatePlayer(p);
                IPlayerOptions po = pp.getPlayerOptions();
                po.setAllowJoin(!po.isAllowJoin());
                new OptionsMenu(p);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_POSITION)) {
                IPrivatePlayer pp = api.getPrivatePlayer(p);
                IPlayerOptions po = pp.getPlayerOptions();
                po.setAutoStart(!po.isAutoStart());
                new OptionsMenu(p);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_BACK_POSITION)) {
                new SettingsMenu(p);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
