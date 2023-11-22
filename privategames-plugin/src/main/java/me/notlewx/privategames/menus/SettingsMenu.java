package me.notlewx.privategames.menus;

import com.andrei1058.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.IArena;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.config.bedwars1058.MessagesData;
import me.notlewx.privategames.menus.submenus.EventsTimeMenu;
import me.notlewx.privategames.menus.submenus.HealthMenu;
import me.notlewx.privategames.menus.submenus.RespawnTimeMenu;
import me.notlewx.privategames.menus.submenus.SpeedMenu;
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

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.config.MainConfig.*;
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
        Material oneHitOneKillMat = Material.getMaterial(mainConfig.getString(ONE_HIT_ONE_KILL_MATERIAL));
        ItemStack oneHitOneKill;
        if (oneHitOneKillMat == Material.SKULL_ITEM) {
            oneHitOneKill = Utility.getSkull(mainConfig.getString(ONE_HIT_ONE_KILL_HEAD_URL));
        } else {
            oneHitOneKill = new ItemStack(oneHitOneKillMat, 1, (byte) mainConfig.getInt(ONE_HIT_ONE_KILL_ID));
        }
        ItemMeta oneHitOneKillMeta = oneHitOneKill.getItemMeta();

        Material healthBuffMat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_MATERIAL));
        ItemStack healthBuff;
        if (healthBuffMat == Material.SKULL_ITEM) {
            healthBuff = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_HEAD_URL));
        } else {
            healthBuff = new ItemStack(healthBuffMat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_ID));
        }
        ItemMeta gappleMeta = healthBuff.getItemMeta();

        Material longJumpMat = Material.getMaterial(mainConfig.getString(LONG_JUMP_MATERIAL));
        ItemStack longJump;
        if (longJumpMat == Material.SKULL_ITEM) {
            longJump = Utility.getSkull(mainConfig.getString(LONG_JUMP_HEAD_URL));
        } else {
            longJump = new ItemStack(longJumpMat, 1, (byte) mainConfig.getInt(LONG_JUMP_ID));
        }
        ItemMeta longJumpMeta = longJump.getItemMeta();

        Material speedMat = Material.getMaterial(mainConfig.getString(SPEED_MATERIAL));
        ItemStack speed;
        if (speedMat == Material.SKULL_ITEM) {
            speed = Utility.getSkull(mainConfig.getString(SPEED_HEAD_URL));
        } else {
            speed = new ItemStack(speedMat, 1, (byte) mainConfig.getInt(SPEED_ID));
        }
        ItemMeta speedMeta = speed.getItemMeta();


        Material respawnTimeMat = Material.getMaterial(mainConfig.getString(RESPAWN_TIME_MATERIAL));
        ItemStack respawnTime;
        if (respawnTimeMat == Material.SKULL_ITEM) {
            respawnTime = Utility.getSkull(mainConfig.getString(RESPAWN_TIME_HEAD_URL));
        } else {
            respawnTime = new ItemStack(respawnTimeMat, 1, (byte) mainConfig.getInt(RESPAWN_TIME_BACK_ID));
        }
        ItemMeta respawnTimeMeta = respawnTime.getItemMeta();

        Material eventsTimeMat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_MATERIAL));
        ItemStack eventsTime;
        if (eventsTimeMat == Material.SKULL_ITEM) {
            eventsTime = Utility.getSkull(mainConfig.getString(EVENTS_TIME_HEAD_URL));
        } else {
            eventsTime = new ItemStack(eventsTimeMat, 1, (byte) mainConfig.getInt(EVENTS_TIME_ID));
        }
        ItemMeta eventsTimeMeta = eventsTime.getItemMeta();

        Material noEmeraldsMat = Material.getMaterial(mainConfig.getString(NO_EMERALDS_MATERIAL));
        ItemStack noEmeralds;
        if (noEmeraldsMat == Material.SKULL_ITEM) {
            noEmeralds = Utility.getSkull(mainConfig.getString(NO_EMERALDS_HEAD_URL));
        } else {
            noEmeralds = new ItemStack(noEmeraldsMat, 1, (byte) mainConfig.getInt(NO_EMERALDS_ID));
        }
        ItemMeta noEmeraldsMeta = noEmeralds.getItemMeta();


        Material noDiamondsMat = Material.getMaterial(mainConfig.getString(NO_DIAMONDS_MATERIAL));
        ItemStack noDiamonds;
        if (noDiamondsMat == Material.SKULL_ITEM) {
            noDiamonds = Utility.getSkull(mainConfig.getString(NO_DIAMONDS_HEAD_URL));
        } else {
            noDiamonds =new ItemStack(noDiamondsMat, 1, (byte) mainConfig.getInt(NO_DIAMONDS_ID));
        }
        ItemMeta noDiamondsMeta = noDiamonds.getItemMeta();

        Material allowMapBreakMat = Material.getMaterial(mainConfig.getString(ALLOW_MAP_BREAK_MATERIAL));
        ItemStack allowMapBreak;
        if (allowMapBreakMat == Material.SKULL_ITEM) {
            allowMapBreak = Utility.getSkull(mainConfig.getString(ALLOW_MAP_BREAK_HEAD_URL));
        } else {
            allowMapBreak = new ItemStack(allowMapBreakMat, 1, (byte) mainConfig.getInt(ALLOW_MAP_BREAK_ID));
        }
        ItemMeta allowMapBreakMeta = allowMapBreak.getItemMeta();

        Material bedInstaBreakMat = Material.getMaterial(mainConfig.getString(BED_INSTA_BREAK_MATERIAL));
        ItemStack bedInstaBreak;
        if (bedInstaBreakMat == Material.SKULL_ITEM) {
            bedInstaBreak = Utility.getSkull(mainConfig.getString(BED_INSTA_BREAK_HEAD_URL));
        } else {
            bedInstaBreak = new ItemStack(bedInstaBreakMat, 1, (byte) mainConfig.getInt(BED_INSTA_BREAK_ID));
        }
        ItemMeta bedInstaBreakMeta = bedInstaBreak.getItemMeta();

        Material maxTeamUpgradesMat = Material.getMaterial(mainConfig.getString(MAX_TEAM_UPGRADES_MATERIAL));
        ItemStack maxTeamUpgrades;
        if (maxTeamUpgradesMat == Material.SKULL_ITEM) {
            maxTeamUpgrades = Utility.getSkull(mainConfig.getString(MAX_TEAM_UPGRADES_HEAD_URL));
        } else {
            maxTeamUpgrades = new ItemStack(maxTeamUpgradesMat, 1, (byte) mainConfig.getInt(MAX_TEAM_UPGRADES_ID));
        }
        ItemMeta maxTeamUpgradesMeta = maxTeamUpgrades.getItemMeta();

        Material optionsMat = Material.getMaterial(mainConfig.getString(OPTIONS_MATERIAL));
        ItemStack options;
        if (optionsMat == Material.SKULL_ITEM) {
            options = Utility.getSkull(mainConfig.getString(OPTIONS_HEAD_URL));
        } else {
            options = new ItemStack(optionsMat, 1, (byte) mainConfig.getInt(OPTIONS_ID));
        }
        ItemMeta optionsMeta = options.getItemMeta();

        Material startMat = Material.getMaterial(mainConfig.getString(START_GAME_MATERIAL));
        ItemStack start;
        if (startMat == Material.SKULL_ITEM) {
            start = Utility.getSkull(mainConfig.getString(START_GAME_HEAD_URL));
        } else {
            start = new ItemStack(startMat, 1, (byte) mainConfig.getInt(START_GAME_ID));
        }
        ItemMeta startMeta = start.getItemMeta();

        Material matBack = Material.getMaterial(mainConfig.getString(BACK_MATERIAL));
        ItemStack back;
        if (matBack == Material.SKULL_ITEM) {
            back = Utility.getSkull(mainConfig.getString(BACK_HEAD_URL));
        } else {
            back = new ItemStack(matBack, 1, (byte) mainConfig.getInt(BACK_HEAD_URL));
        }
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
        gappleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        longJumpMeta.setDisplayName(Utility.getMsg(player, ITEM_LOW_GRAVITY_NAME));
        if (playerData.isLowGravityEnabled()) {
            longJumpMeta.setLore(Utility.getList(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isLowGravityEnabled()) {
            longJumpMeta.setLore(Utility.getList(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        longJumpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        speedMeta.setDisplayName(Utility.getMsg(player, ITEM_SPEED_NAME));
        speedMeta.setLore(Utility.getList(player, ITEM_SPEED_LORE));
        speedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        respawnTimeMeta.setDisplayName(Utility.getMsg(player, ITEM_RESPAWN_EVENT_TIME_NAME));
        respawnTimeMeta.setLore(Utility.getList(player, ITEM_RESPAWN_EVENT_TIME_LORE));
        eventsTimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        eventsTimeMeta.setDisplayName(Utility.getMsg(player, ITEM_EVENTS_TIME_LEVEL_NAME));
        eventsTimeMeta.setLore(Utility.getList(player, ITEM_EVENTS_TIME_LEVEL_LORE));
        eventsTimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        allowMapBreakMeta.setDisplayName(Utility.getMsg(player, ITEM_ALLOW_MAP_BREAK_NAME));
        if (playerData.isAllowMapBreakEnabled()) {
            allowMapBreakMeta.setLore(Utility.getList(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isAllowMapBreakEnabled()) {
            allowMapBreakMeta.setLore(Utility.getList(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        allowMapBreakMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        noEmeraldsMeta.setDisplayName(Utility.getMsg(player, ITEM_NO_EMERALDS_NAME));
        if (playerData.isNoEmeraldsEnabled()) {
            noEmeraldsMeta.setLore(Utility.getList(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isNoEmeraldsEnabled()) {
            noEmeraldsMeta.setLore(Utility.getList(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        noEmeraldsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        bedInstaBreakMeta.setDisplayName(Utility.getMsg(player, ITEM_BED_INSTA_BREAK_NAME));
        if (playerData.isBedInstaBreakEnabled()) {
            bedInstaBreakMeta.setLore(Utility.getList(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isBedInstaBreakEnabled()) {
            bedInstaBreakMeta.setLore(Utility.getList(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        bedInstaBreakMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        noDiamondsMeta.setDisplayName(Utility.getMsg(player, ITEM_NO_DIAMONDS_NAME));
        if (playerData.isNoDiamondsEnabled()) {
            noDiamondsMeta.setLore(Utility.getList(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isNoDiamondsEnabled()) {
            noDiamondsMeta.setLore(Utility.getList(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        noDiamondsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        maxTeamUpgradesMeta.setDisplayName(Utility.getMsg(player, ITEM_MAX_TEAM_UPGRADES_NAME));
        if (playerData.isMaxTeamUpgradesEnabled()) {
            maxTeamUpgradesMeta.setLore(Utility.getList(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
        } else if (!playerData.isMaxTeamUpgradesEnabled()) {
            maxTeamUpgradesMeta.setLore(Utility.getList(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
        }
        maxTeamUpgradesMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        optionsMeta.setDisplayName(Utility.getMsg(player, ITEM_OPTIONS_NAME));
        optionsMeta.setLore(Utility.getList(player, ITEM_OPTIONS_LORE));
        optionsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        switch (support) {
            case BEDWARS2023:
                if (api.getPrivateArenaUtil().isPlaying(player)) {
                    IArena a = PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaName());
                    if (a.getStatus() != com.tomkeuper.bedwars.api.arena.GameState.starting) {
                        startMeta.setDisplayName(Utility.getMsg(player, ITEM_START_NAME));
                        startMeta.setLore(Utility.getList(player, ITEM_START_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_START_MEANING))).collect(Collectors.toList()));
                        start.removeEnchantment(Enchantment.DURABILITY);
                    } else {
                        startMeta.setDisplayName(Utility.getMsg(player, ITEM_START_NAME));
                        startMeta.setLore(Utility.getList(player, ITEM_START_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_STARTING_MEANING))).collect(Collectors.toList()));
                        start.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                }
                break;
            case BEDWARS1058:
                if (api.getPrivateArenaUtil().isPlaying(player)) {
                    com.andrei1058.bedwars.api.arena.IArena a = PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaName());
                    if (a.getStatus() != GameState.starting) {
                        startMeta.setDisplayName(Utility.getMsg(player, ITEM_START_NAME));
                        startMeta.setLore(Utility.getList(player, ITEM_START_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_CLICK_TO_START_MEANING))).collect(Collectors.toList()));
                        start.removeEnchantment(Enchantment.DURABILITY);
                    } else {
                        startMeta.setDisplayName(Utility.getMsg(player, ITEM_START_NAME));
                        startMeta.setLore(Utility.getList(player, ITEM_START_LORE).stream().map(s -> s.replace("{state}", Utility.getMsg(player, MENU_STARTING_MEANING))).collect(Collectors.toList()));
                        start.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                }
                break;
        }
        startMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

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
        respawnTimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
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
        gappleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
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
        speedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
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
        eventsTimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

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

        options.setItemMeta(optionsMeta);
        start.setItemMeta(startMeta);

        back.setItemMeta(backMeta);


        if (playerData.isAllowMapBreakEnabled()) allowMapBreak.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else allowMapBreak.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNoEmeraldsEnabled()) noEmeralds.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else noEmeralds.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isNoDiamondsEnabled()) noDiamonds.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else noDiamonds.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isBedInstaBreakEnabled()) bedInstaBreak.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else bedInstaBreak.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isOneHitOneKillEnabled()) oneHitOneKill.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else oneHitOneKill.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isLowGravityEnabled()) longJump.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else longJump.removeEnchantment(Enchantment.DURABILITY);
        if (playerData.isMaxTeamUpgradesEnabled()) maxTeamUpgrades.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else maxTeamUpgrades.removeEnchantment(Enchantment.DURABILITY);

        if (mainConfig.getBoolean(ONE_HIT_ONE_KILL)) inventory.setItem(mainConfig.getInt(ONE_HIT_ONE_KILL_POSITION), oneHitOneKill);
        if (mainConfig.getBoolean(HEALTH_BUFF)) inventory.setItem(mainConfig.getInt(HEALTH_BUFF_POSITION), healthBuff);
        if (mainConfig.getBoolean(LONG_JUMP)) inventory.setItem(mainConfig.getInt(LONG_JUMP_POSITION), longJump);
        if (mainConfig.getBoolean(SPEED)) inventory.setItem(mainConfig.getInt(SPEED_POSITION), speed);

        if (mainConfig.getBoolean(RESPAWN_TIME)) inventory.setItem(mainConfig.getInt(RESPAWN_TIME_POSITION), respawnTime);
        if (mainConfig.getBoolean(EVENTS_TIME)) inventory.setItem(mainConfig.getInt(EVENTS_TIME_POSITION), eventsTime);
        if (mainConfig.getBoolean(NO_EMERALDS)) inventory.setItem(mainConfig.getInt(NO_EMERALDS_POSITION), noEmeralds);

        if (mainConfig.getBoolean(NO_DIAMONDS)) inventory.setItem(mainConfig.getInt(NO_DIAMONDS_POSITION), noDiamonds);
        if (mainConfig.getBoolean(ALLOW_MAP_BREAK)) inventory.setItem(mainConfig.getInt(ALLOW_MAP_BREAK_POSITION), allowMapBreak);
        if (mainConfig.getBoolean(BED_INSTA_BREAK)) inventory.setItem(mainConfig.getInt(BED_INSTA_BREAK_POSITION), bedInstaBreak);
        if (mainConfig.getBoolean(MAX_TEAM_UPGRADES)) inventory.setItem(mainConfig.getInt(MAX_TEAM_UPGRADES_POSITION), maxTeamUpgrades);

        if (mainConfig.getBoolean(OPTIONS_ENABLE)) inventory.setItem(mainConfig.getInt(OPTIONS_POSITION), options);
        if (mainConfig.getBoolean(START_GAME)) if (api.getPrivateArenaUtil().isPlaying(player)) inventory.setItem(mainConfig.getInt(START_GAME_POSITION), start);

        if (mainConfig.getBoolean(BACK_ENABLE)) inventory.setItem(mainConfig.getInt(BACK_POSITION), back);
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(player, MAIN_MENU_NAME))) {
            if (e.getSlot() == mainConfig.getInt(ONE_HIT_ONE_KILL_POSITION)) {
                playerData.setOneHitOneKillEnabled(!playerData.isOneHitOneKillEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(HEALTH_BUFF_POSITION)) {
                new HealthMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(LONG_JUMP_POSITION)) {
                playerData.setLowGravityEnabled(!playerData.isLowGravityEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(SPEED_POSITION)) {
                new SpeedMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(RESPAWN_TIME_POSITION)) {
                new RespawnTimeMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(EVENTS_TIME_POSITION)) {
                new EventsTimeMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(NO_EMERALDS_POSITION)) {
                playerData.setNoEmeraldsEnabled(!playerData.isNoEmeraldsEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(NO_DIAMONDS_POSITION)) {
                playerData.setNoDiamondsEnabled(!playerData.isNoDiamondsEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(ALLOW_MAP_BREAK_POSITION)) {
                playerData.setAllowMapBreakEnabled(!playerData.isAllowMapBreakEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(BED_INSTA_BREAK_POSITION)) {
                playerData.setBedInstaBreakEnabled(!playerData.isBedInstaBreakEnabled());
                new SettingsMenu(player);
            }
            else if (e.getSlot() == mainConfig.getInt(MAX_TEAM_UPGRADES_POSITION)) {
                playerData.setMaxTeamUpgradesEnabled(!playerData.isMaxTeamUpgradesEnabled());
                new SettingsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_POSITION)) {
                new OptionsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(START_GAME_POSITION)) {
                switch (support) {
                    case BEDWARS2023:
                        IArena a = PrivateGames.getBw2023Api().getArenaUtil().getArenaByName(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaName());
                        if (a.getStatus() != com.tomkeuper.bedwars.api.arena.GameState.playing && a.getStatus() != com.tomkeuper.bedwars.api.arena.GameState.starting) {
                            a.changeStatus(com.tomkeuper.bedwars.api.arena.GameState.starting);
                            a.getStartingTask().setCountdown(bw2023config.getInt("countdowns.game-start-regular"));
                        }
                        break;
                    case BEDWARS1058:
                        com.andrei1058.bedwars.api.arena.IArena a1 = PrivateGames.getBw1058Api().getArenaUtil().getArenaByName(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaName());
                        if (a1.getStatus() != GameState.playing && a1.getStatus() != GameState.starting) {
                            a1.changeStatus(GameState.starting);
                            a1.getStartingTask().setCountdown(bw1058config.getInt("countdowns.game-start-regular"));
                        }
                        break;
                }
                new SettingsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(BACK_POSITION)) {
                if (mainConfig.getString(BACK_COMMAND).equalsIgnoreCase("close")) {
                    player.closeInventory();
                } else {
                    if (!api.getPrivateArenaUtil().isPlaying(player)) {
                        player.performCommand(mainConfig.getString(BACK_COMMAND));
                    } else {
                        player.closeInventory();
                    }
                }
            }

        }
    }
}
