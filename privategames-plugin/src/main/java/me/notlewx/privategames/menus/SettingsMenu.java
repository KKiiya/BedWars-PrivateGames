package me.notlewx.privategames.menus;

import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.config.bedwars1058.MessagesData;
import me.notlewx.privategames.player.PrivatePlayer;
import me.notlewx.privategames.utils.CustomItemStack;
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
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class SettingsMenu implements GUIHolder {
    private Inventory inventory;
    private final Player player;
    private final IPlayerSettings playerData;

    public SettingsMenu(Player p) {
        this.player = p;
        playerData = new PrivatePlayer(player).getPlayerSettings();
        createInventory();
        addContents(inventory);
        player.openInventory(inventory);
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(this, 9 * mainConfig.getInt(SETTINGS_ROWS), Utility.getMsg(player, MessagesData.MAIN_MENU_NAME));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
    private void addContents(Inventory inventory) {
        CustomItemStack oneHitOneKill = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(ONE_HIT_ONE_HILL_MATERIAL))));
        oneHitOneKill.setCustomID("oneHitOneKill");
        ItemMeta oneHitOneKillMeta = oneHitOneKill.getItemMeta();

        CustomItemStack healthBuff = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(HEALTH_BUFF_MATERIAL))));
        healthBuff.setCustomID("healthBuff");
        ItemMeta gappleMeta = healthBuff.getItemMeta();

        CustomItemStack longJump = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(LONG_JUMP_MATERIAL))));
        longJump.setCustomID("lowGravity");
        ItemMeta longJumpMeta = longJump.getItemMeta();

        CustomItemStack speed = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(SPEED_MATERIAL))));
        speed.setCustomID("speed");
        ItemMeta speedMeta = speed.getItemMeta();


        CustomItemStack respawnTime = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(RESPAWN_TIME_MATERIAL))));
        respawnTime.setCustomID("respawnTime");
        ItemMeta respawnTimeMeta = respawnTime.getItemMeta();

        CustomItemStack eventsTime = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(EVENTS_TIME_MATERIAL))));
        eventsTime.setCustomID("eventsTime");
        ItemMeta eventsTimeMeta = eventsTime.getItemMeta();

        CustomItemStack noEmeralds = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(NO_EMERALDS_MATERIAL))));
        noEmeralds.setCustomID("noEmeralds");
        ItemMeta noEmeraldsMeta = noEmeralds.getItemMeta();


        CustomItemStack noDiamonds = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(NO_DIAMONDS_MATERIAL))));
        noDiamonds.setCustomID("noDiamonds");
        ItemMeta noDiamondsMeta = noDiamonds.getItemMeta();

        CustomItemStack allowMapBreak = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(ALLOW_MAP_BREAK_MATERIAL))));
        allowMapBreak.setCustomID("mapBreak");
        ItemMeta allowMapBreakMeta = allowMapBreak.getItemMeta();

        CustomItemStack bedInstaBreak = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(BED_INSTA_BREAK_MATERIAL))));
        bedInstaBreak.setCustomID("bedInstantBreak");
        ItemMeta bedInstaBreakMeta = bedInstaBreak.getItemMeta();

        CustomItemStack maxTeamUpgrades = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(MAX_TEAM_UPGRADES_MATERIAL))));
        maxTeamUpgrades.setCustomID("maxTeamUpgrades");
        ItemMeta maxTeamUpgradesMeta = maxTeamUpgrades.getItemMeta();

        CustomItemStack back = new CustomItemStack(new ItemStack(Material.getMaterial(mainConfig.getString(BACK_MATERIAL))));
        back.setCustomID("backItem");
        ItemMeta backMeta = back.getItemMeta();

        oneHitOneKillMeta.setDisplayName(Utility.getMsg(player, ITEM_ONE_HIT_ONE_KILL_NAME));
        if (playerData.isOneHitOneKillEnabled()) {
            oneHitOneKillMeta.setLore(Utility.getList(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else {
            oneHitOneKillMeta.setLore(Utility.getList(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        oneHitOneKillMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        gappleMeta.setDisplayName(Utility.getMsg(player, ITEM_HEALTH_BUFF_LEVEL_NAME));
        gappleMeta.setLore(Utility.getList(player, ITEM_HEALTH_BUFF_LEVEL_LORE));

        longJumpMeta.setDisplayName(Utility.getMsg(player, ITEM_LOW_GRAVITY_NAME));
        if (playerData.isLowGravityEnabled()) {
            longJumpMeta.setLore(Utility.getList(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isLowGravityEnabled()) {
            longJumpMeta.setLore(Utility.getList(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        longJumpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        speedMeta.setDisplayName(Utility.getMsg(player, ITEM_SPEED_NAME));
        speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE));

        respawnTimeMeta.setDisplayName(Utility.getMsg(player, ITEM_RESPAWN_EVENT_TIME_NAME));
        respawnTimeMeta.setLore(Utility.getList(player, ITEM_RESPAWN_EVENT_TIME_LORE));

        eventsTimeMeta.setDisplayName(Utility.getMsg(player, ITEM_EVENTS_TIME_LEVEL_NAME));
        eventsTimeMeta.setLore(Utility.getList(player, ITEM_EVENTS_TIME_LEVEL_LORE));

        allowMapBreakMeta.setDisplayName(Utility.getMsg(player, ITEM_ALLOW_MAP_BREAK_NAME));
        if (playerData.isAllowMapBreakEnabled()) {
            allowMapBreakMeta.setLore(Utility.getList(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isAllowMapBreakEnabled()) {
            allowMapBreakMeta.setLore(Utility.getList(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        allowMapBreakMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        noEmeraldsMeta.setDisplayName(Utility.getMsg(player, ITEM_NO_EMERALDS_NAME));
        if (playerData.isNoEmeraldsEnabled()) {
            noEmeraldsMeta.setLore(Utility.getList(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isNoEmeraldsEnabled()) {
            noEmeraldsMeta.setLore(Utility.getList(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        noEmeraldsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        bedInstaBreakMeta.setDisplayName(Utility.getMsg(player, ITEM_BED_INSTA_BREAK_NAME));
        if (playerData.isBedInstaBreakEnabled()) {
            bedInstaBreakMeta.setLore(Utility.getList(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isBedInstaBreakEnabled()) {
            bedInstaBreakMeta.setLore(Utility.getList(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        bedInstaBreakMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        noDiamondsMeta.setDisplayName(Utility.getMsg(player, ITEM_NO_DIAMONDS_NAME));
        if (playerData.isNoDiamondsEnabled()) {
            noDiamondsMeta.setLore(Utility.getList(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isNoDiamondsEnabled()) {
            noDiamondsMeta.setLore(Utility.getList(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        noDiamondsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        maxTeamUpgradesMeta.setDisplayName(Utility.getMsg(player, ITEM_MAX_TEAM_UPGRADES_NAME));
        if (playerData.isMaxTeamUpgradesEnabled()) {
            maxTeamUpgradesMeta.setLore(Utility.getList(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isMaxTeamUpgradesEnabled()) {
            maxTeamUpgradesMeta.setLore(Utility.getList(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        maxTeamUpgradesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        backMeta.setDisplayName(Utility.getMsg(player, MENU_BACK_ITEM_NAME));
        backMeta.setLore(Utility.getList(player, MENU_BACK_ITEM_LORE));


        switch (playerData.getRespawnTimeLevel()) {
            case 0 :
            case 2:
                respawnTimeMeta.setLore(Utility.getList(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, RESPAWN_EVENT_TIME_II_MEANING))).collect(Collectors.toList()));
                break;
            case 1:
                respawnTimeMeta.setLore(Utility.getList(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, RESPAWN_EVENT_TIME_I_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                respawnTimeMeta.setLore(Utility.getList(player, ITEM_RESPAWN_EVENT_TIME_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, RESPAWN_EVENT_TIME_III_MEANING))).collect(Collectors.toList()));
                break;
        }
        switch (playerData.getHealthBuffLevel()) {
            case 0:
            case 1:
                gappleMeta.setLore(Utility.getList(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, NORMAL_HEALTH_MEANING))).collect(Collectors.toList()));
                break;
            case 2:
                gappleMeta.setLore(Utility.getList(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, DOUBLE_HEALTH_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                gappleMeta.setLore(Utility.getList(player, ITEM_HEALTH_BUFF_LEVEL_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, TRIPLE_HEALTH_MEANING))).collect(Collectors.toList()));
                break;
        }
        switch (playerData.getSpeedLevel()) {
            case 0:
            case 1:
                speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, NO_SPEED_MEANING))).collect(Collectors.toList()));
                break;
            case 2:
                speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, SPEED_I_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, SPEED_II_MEANING))).collect(Collectors.toList()));
                break;
            case 4:
                speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, SPEED_III_MEANING))).collect(Collectors.toList()));
                break;
        }
        switch (playerData.getEventsTimeLevel()) {
            case 0:
            case 2:
                eventsTimeMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, EVENTS_TIME_NORMAL_MEANING))).collect(Collectors.toList()));
                break;
            case 1:
                eventsTimeMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, EVENTS_TIME_SLOWER_MEANING))).collect(Collectors.toList()));
                break;
            case 3:
                eventsTimeMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, EVENTS_TIME_FAST_MEANING))).collect(Collectors.toList()));
                break;
            case 4:
                eventsTimeMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE).stream().map(s -> s.replace("{selected}", Utility.getMsg(player, EVENTS_TIME_FASTER_MEANING))).collect(Collectors.toList()));
                break;
        }
        oneHitOneKill.setItemMeta(oneHitOneKillMeta);
        healthBuff.setItemMeta(gappleMeta);
        longJump.setItemMeta(longJumpMeta);
        speed.setItemMeta(speedMeta);

        respawnTime.setItemMeta(respawnTimeMeta);
        eventsTime.setItemMeta(eventsTimeMeta);
        noEmeralds.setItemMeta(noEmeraldsMeta);

        noDiamonds.setItemMeta(noDiamondsMeta);
        allowMapBreak.setItemMeta(allowMapBreakMeta);
        bedInstaBreak.setItemMeta(bedInstaBreakMeta);
        maxTeamUpgrades.setItemMeta(maxTeamUpgradesMeta);

        back.setItemMeta(backMeta);

        if (playerData.isAllowMapBreakEnabled()) allowMapBreak.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isAllowMapBreakEnabled()) allowMapBreak.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNoEmeraldsEnabled()) noEmeralds.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isNoEmeraldsEnabled()) noEmeralds.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNoDiamondsEnabled()) noDiamonds.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isNoDiamondsEnabled()) noDiamonds.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isBedInstaBreakEnabled()) bedInstaBreak.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isBedInstaBreakEnabled()) bedInstaBreak.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isOneHitOneKillEnabled()) oneHitOneKill.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isOneHitOneKillEnabled()) oneHitOneKill.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isLowGravityEnabled()) longJump.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isLowGravityEnabled()) longJump.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isMaxTeamUpgradesEnabled()) maxTeamUpgrades.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else if (!playerData.isMaxTeamUpgradesEnabled()) maxTeamUpgrades.removeEnchantment(Enchantment.DURABILITY);

        if (mainConfig.getBoolean(ONE_HIT_ONE_KILL)) inventory.setItem(mainConfig.getInt(ONE_HIT_ONE_HILL_POSITION), oneHitOneKill.getItem());
        if (mainConfig.getBoolean(HEALTH_BUFF)) inventory.setItem(mainConfig.getInt(HEALTH_BUFF_POSITION), healthBuff.getItem());
        if (mainConfig.getBoolean(LONG_JUMP)) inventory.setItem(mainConfig.getInt(LONG_JUMP_POSITION), longJump.getItem());
        if (mainConfig.getBoolean(SPEED)) inventory.setItem(mainConfig.getInt(SPEED_POSITION), speed.getItem());

        if (mainConfig.getBoolean(RESPAWN_TIME)) inventory.setItem(mainConfig.getInt(RESPAWN_TIME_POSITION), respawnTime.getItem());
        if (mainConfig.getBoolean(EVENTS_TIME)) inventory.setItem(mainConfig.getInt(EVENTS_TIME_POSITION), eventsTime.getItem());
        if (mainConfig.getBoolean(NO_EMERALDS)) inventory.setItem(mainConfig.getInt(NO_EMERALDS_POSITION), noEmeralds.getItem());

        if (mainConfig.getBoolean(NO_DIAMONDS)) inventory.setItem(mainConfig.getInt(NO_DIAMONDS_POSITION), noDiamonds.getItem());
        if (mainConfig.getBoolean(ALLOW_MAP_BREAK)) inventory.setItem(mainConfig.getInt(ALLOW_MAP_BREAK_POSITION), allowMapBreak.getItem());
        if (mainConfig.getBoolean(BED_INSTA_BREAK)) inventory.setItem(mainConfig.getInt(BED_INSTA_BREAK_POSITION), bedInstaBreak.getItem());
        if (mainConfig.getBoolean(MAX_TEAM_UPGRADES)) inventory.setItem(mainConfig.getInt(MAX_TEAM_UPGRADES_POSITION), maxTeamUpgrades.getItem());

        inventory.setItem(mainConfig.getInt(BACK_POSITION), back.getItem());
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        System.out.println(CustomItemStack.getItemID(e.getCurrentItem()));
    }
}