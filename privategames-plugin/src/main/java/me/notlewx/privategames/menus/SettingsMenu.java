package me.notlewx.privategames.menus;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.sidebar.SidebarService;
import com.tomkeuper.bedwars.api.arena.IArena;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.support.VersionSupport;
import me.notlewx.privategames.config.bedwars1058.MessagesData;
import me.notlewx.privategames.menus.submenus.*;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.config.MainConfig.*;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class SettingsMenu implements GUIHolder {

    private final IPlayerSettings playerData;
    private final VersionSupport vs;
    private final Player player;
    private Inventory inventory;

    public SettingsMenu(Player p) {
        this.player = p;
        this.vs = PrivateGames.getVersionSupport();
        playerData = api.getPrivatePlayer(p).getPlayerSettings();
        try {
            createInventory();
            addContents(inventory);
            player.openInventory(inventory);
        } catch (Exception ex) {
            throw new RuntimeException("Error while opening the settings menu", ex);
        }
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
        ItemStack oneHitOneKill = new ItemStack(oneHitOneKillMat, 1, (byte) mainConfig.getInt(ONE_HIT_ONE_KILL_ID));
        if (vs.isPlayerHead(oneHitOneKill)) oneHitOneKill = Utility.getSkull(mainConfig.getString(ONE_HIT_ONE_KILL_HEAD_URL));

        ItemMeta oneHitOneKillMeta = oneHitOneKill.getItemMeta();

        Material healthBuffMat = Material.getMaterial(mainConfig.getString(HEALTH_BUFF_MATERIAL));
        ItemStack healthBuff = new ItemStack(healthBuffMat, 1, (byte) mainConfig.getInt(HEALTH_BUFF_ID));
        if (vs.isPlayerHead(healthBuff)) healthBuff = Utility.getSkull(mainConfig.getString(HEALTH_BUFF_HEAD_URL));
        ItemMeta gappleMeta = healthBuff.getItemMeta();

        Material longJumpMat = Material.getMaterial(mainConfig.getString(LONG_JUMP_MATERIAL));
        ItemStack longJump = new ItemStack(longJumpMat, 1, (byte) mainConfig.getInt(LONG_JUMP_ID));
        if (vs.isPlayerHead(longJump)) longJump = Utility.getSkull(mainConfig.getString(LONG_JUMP_HEAD_URL));
        ItemMeta longJumpMeta = longJump.getItemMeta();

        Material speedMat = Material.getMaterial(mainConfig.getString(SPEED_MATERIAL));
        ItemStack speed = new ItemStack(speedMat, 1, (byte) mainConfig.getInt(SPEED_ID));
        if (vs.isPlayerHead(speed)) speed = Utility.getSkull(mainConfig.getString(SPEED_HEAD_URL));
        ItemMeta speedMeta = speed.getItemMeta();


        Material respawnTimeMat = Material.getMaterial(mainConfig.getString(RESPAWN_TIME_MATERIAL));
        ItemStack respawnTime = new ItemStack(respawnTimeMat, 1, (byte) mainConfig.getInt(RESPAWN_TIME_ID));
        if (vs.isPlayerHead(respawnTime)) respawnTime = Utility.getSkull(mainConfig.getString(RESPAWN_TIME_HEAD_URL));
        ItemMeta respawnTimeMeta = respawnTime.getItemMeta();

        Material eventsTimeMat = Material.getMaterial(mainConfig.getString(EVENTS_TIME_MATERIAL));
        ItemStack eventsTime = new ItemStack(eventsTimeMat, 1, (byte) mainConfig.getInt(EVENTS_TIME_ID));
        if (vs.isPlayerHead(eventsTime)) eventsTime = Utility.getSkull(mainConfig.getString(EVENTS_TIME_HEAD_URL));
        ItemMeta eventsTimeMeta = eventsTime.getItemMeta();

        Material noEmeraldsMat = Material.getMaterial(mainConfig.getString(NO_EMERALDS_MATERIAL));
        ItemStack noEmeralds = new ItemStack(noEmeraldsMat, 1, (byte) mainConfig.getInt(NO_EMERALDS_ID));
        if (vs.isPlayerHead(noEmeralds)) noEmeralds = Utility.getSkull(mainConfig.getString(NO_EMERALDS_HEAD_URL));
        ItemMeta noEmeraldsMeta = noEmeralds.getItemMeta();


        Material noDiamondsMat = Material.getMaterial(mainConfig.getString(NO_DIAMONDS_MATERIAL));
        ItemStack noDiamonds = new ItemStack(noDiamondsMat, 1, (byte) mainConfig.getInt(NO_DIAMONDS_ID));
        if (vs.isPlayerHead(noDiamonds)) noDiamonds = Utility.getSkull(mainConfig.getString(NO_DIAMONDS_HEAD_URL));
        ItemMeta noDiamondsMeta = noDiamonds.getItemMeta();

        Material allowMapBreakMat = Material.getMaterial(mainConfig.getString(ALLOW_MAP_BREAK_MATERIAL));
        ItemStack allowMapBreak = new ItemStack(allowMapBreakMat, 1, (byte) mainConfig.getInt(ALLOW_MAP_BREAK_ID));
        if (vs.isPlayerHead(allowMapBreak)) allowMapBreak = Utility.getSkull(mainConfig.getString(ALLOW_MAP_BREAK_HEAD_URL));
        ItemMeta allowMapBreakMeta = allowMapBreak.getItemMeta();

        Material bedInstaBreakMat = Material.getMaterial(mainConfig.getString(BED_INSTA_BREAK_MATERIAL));
        ItemStack bedInstaBreak = new ItemStack(bedInstaBreakMat, 1, (byte) mainConfig.getInt(BED_INSTA_BREAK_ID));
        if (vs.isPlayerHead(bedInstaBreak)) bedInstaBreak = Utility.getSkull(mainConfig.getString(BED_INSTA_BREAK_HEAD_URL));
        ItemMeta bedInstaBreakMeta = bedInstaBreak.getItemMeta();

        Material maxTeamUpgradesMat = Material.getMaterial(mainConfig.getString(MAX_TEAM_UPGRADES_MATERIAL));
        ItemStack maxTeamUpgrades = new ItemStack(maxTeamUpgradesMat, 1, (byte) mainConfig.getInt(MAX_TEAM_UPGRADES_ID));
        if (vs.isPlayerHead(maxTeamUpgrades)) maxTeamUpgrades = Utility.getSkull(mainConfig.getString(MAX_TEAM_UPGRADES_HEAD_URL));
        ItemMeta maxTeamUpgradesMeta = maxTeamUpgrades.getItemMeta();

        Material optionsMat = Material.getMaterial(mainConfig.getString(OPTIONS_MATERIAL));
        ItemStack options = new ItemStack(optionsMat, 1, (byte) mainConfig.getInt(OPTIONS_ID));
        if (vs.isPlayerHead(options)) options = Utility.getSkull(mainConfig.getString(OPTIONS_HEAD_URL));
        ItemMeta optionsMeta = options.getItemMeta();

        Material startMat = Material.getMaterial(mainConfig.getString(START_GAME_MATERIAL));
        ItemStack start = new ItemStack(startMat, 1, (byte) mainConfig.getInt(START_GAME_ID));
        if (vs.isPlayerHead(start)) start = Utility.getSkull(mainConfig.getString(START_GAME_HEAD_URL));
        ItemMeta startMeta = start.getItemMeta();

        Material gamemodeChangerMat = Material.getMaterial(mainConfig.getString(GAMEMODE_CHANGER_MATERIAL));
        ItemStack gamemodeChanger = new ItemStack(gamemodeChangerMat, 1, (byte) mainConfig.getInt(GAMEMODE_CHANGER_ID));
        if (vs.isPlayerHead(gamemodeChanger)) gamemodeChanger = Utility.getSkull(mainConfig.getString(GAMEMODE_CHANGER_HEAD_URL));
        ItemMeta gamemodeChangerMeta = gamemodeChanger.getItemMeta();

        Material matBack = Material.getMaterial(mainConfig.getString(BACK_MATERIAL));
        ItemStack back = new ItemStack(matBack, 1, (byte) mainConfig.getInt(BACK_ID));
        if (vs.isPlayerHead(back)) back = Utility.getSkull(mainConfig.getString(BACK_HEAD_URL));
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

        gamemodeChangerMeta.setDisplayName(Utility.getMsg(player, ITEM_GAMEMODE_CHANGER_NAME));
        String group;
        boolean isPlaying = false;
        switch (support) {
            case BEDWARS2023:
                isPlaying = PrivateGames.getBw2023Api().getArenaUtil().isPlaying(player);
                break;
            case BEDWARS1058:
                isPlaying = PrivateGames.getBw1058Api().getArenaUtil().isPlaying(player);
                break;
        }
        if (isPlaying) {
            if (support == Support.BEDWARS1058) {
                group = api.getBedWars1058API().getArenaUtil().getArenaByPlayer(player).getGroup();
            } else if (support == Support.BEDWARS2023) {
                group = api.getBedWars2023API().getArenaUtil().getArenaByPlayer(player).getGroup();
            } else {
                group = "";
            }

            if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + group) != null) {
                gamemodeChangerMeta.setLore(Utility.getList(player, ITEM_GAMEMODE_CHANGER_LORE).stream().map(s -> s
                                .replace("{state}", Utility.getMsg(player, "display-group-" + group))
                                .replace("{default}", Utility.getMsg(player, "display-group-" + api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getDefaultGroup())))
                        .collect(Collectors.toList()));
            }
        }
        gamemodeChangerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        switch (support) {
            case BEDWARS2023:
                if (api.getPrivateArenaUtil().isPlaying(player)) {
                    IArena a = PrivateGames.getBw2023Api().getArenaUtil().getArenaByIdentifier(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaIdentifier());
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
                    com.andrei1058.bedwars.api.arena.IArena a = PrivateGames.getBw1058Api().getArenaUtil().getArenaByIdentifier(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaIdentifier());
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
        gamemodeChanger.setItemMeta(gamemodeChangerMeta);

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

        if (mainConfig.getBoolean(ONE_HIT_ONE_KILL)) inventory.setItem(mainConfig.getInt(ONE_HIT_ONE_KILL_POSITION), vs.setItemTag(oneHitOneKill, "pg", "oneHitOneKill"));
        if (mainConfig.getBoolean(HEALTH_BUFF)) inventory.setItem(mainConfig.getInt(HEALTH_BUFF_POSITION), vs.setItemTag(healthBuff, "pg", "health"));
        if (mainConfig.getBoolean(LONG_JUMP)) inventory.setItem(mainConfig.getInt(LONG_JUMP_POSITION), vs.setItemTag(longJump, "pg", "lowGravity"));
        if (mainConfig.getBoolean(SPEED)) inventory.setItem(mainConfig.getInt(SPEED_POSITION), vs.setItemTag(speed, "pg", "speed"));

        if (mainConfig.getBoolean(RESPAWN_TIME)) inventory.setItem(mainConfig.getInt(RESPAWN_TIME_POSITION), vs.setItemTag(respawnTime, "pg", "respawnTime"));
        if (mainConfig.getBoolean(EVENTS_TIME)) inventory.setItem(mainConfig.getInt(EVENTS_TIME_POSITION), vs.setItemTag(eventsTime, "pg", "eventsTime"));
        if (mainConfig.getBoolean(NO_EMERALDS)) inventory.setItem(mainConfig.getInt(NO_EMERALDS_POSITION), vs.setItemTag(noEmeralds, "pg", "noEmeralds"));

        if (mainConfig.getBoolean(NO_DIAMONDS)) inventory.setItem(mainConfig.getInt(NO_DIAMONDS_POSITION), vs.setItemTag(noDiamonds, "pg", "noDiamonds"));
        if (mainConfig.getBoolean(ALLOW_MAP_BREAK)) inventory.setItem(mainConfig.getInt(ALLOW_MAP_BREAK_POSITION), vs.setItemTag(allowMapBreak, "pg", "allowMapBreak"));
        if (mainConfig.getBoolean(BED_INSTA_BREAK)) inventory.setItem(mainConfig.getInt(BED_INSTA_BREAK_POSITION), vs.setItemTag(bedInstaBreak, "pg", "bedInstaBreak"));
        if (mainConfig.getBoolean(MAX_TEAM_UPGRADES)) inventory.setItem(mainConfig.getInt(MAX_TEAM_UPGRADES_POSITION), vs.setItemTag(maxTeamUpgrades, "pg", "maxTeamUpgrades"));

        if (mainConfig.getBoolean(OPTIONS_ENABLE)) inventory.setItem(mainConfig.getInt(OPTIONS_POSITION), vs.setItemTag(options, "pg", "options"));
        if (mainConfig.getBoolean(START_GAME)) {
            if (api.getPrivateArenaUtil().isPlaying(player)) inventory.setItem(mainConfig.getInt(START_GAME_POSITION), vs.setItemTag(start, "pg", "start"));
        }

        if (mainConfig.getBoolean(GAMEMODE_CHANGER)) {
            if (api.getPrivateArenaUtil().isPlaying(player)) {
                if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getDefaultGroup()) != null) {
                    inventory.setItem(mainConfig.getInt(GAMEMODE_CHANGER_POSITION), vs.setItemTag(gamemodeChanger, "pg", "gamemodeChanger"));
                }
            }
        }

        if (mainConfig.getBoolean(BACK_ENABLE)) inventory.setItem(mainConfig.getInt(BACK_POSITION), vs.setItemTag(back, "pg", "back"));
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null) return;
        if (item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) {
            e.setCancelled(true);
            return;
        }
        if (!e.getView().getTitle().equals(Utility.getMsg(player, MAIN_MENU_NAME))) return;

        if (tag.equalsIgnoreCase("oneHitOneKill")) {
            playerData.setOneHitOneKillEnabled(!playerData.isOneHitOneKillEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("lowGravity")) {
            playerData.setLowGravityEnabled(!playerData.isLowGravityEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("health")) new HealthMenu(player);
        else if (tag.equalsIgnoreCase("speed")) new SpeedMenu(player);
        else if (tag.equalsIgnoreCase("respawnTime")) new RespawnTimeMenu(player);
        else if (tag.equalsIgnoreCase("eventsTime")) new EventsTimeMenu(player);
        else if (tag.equalsIgnoreCase("noEmeralds")) {
            playerData.setNoEmeraldsEnabled(!playerData.isNoEmeraldsEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("noDiamonds")) {
            playerData.setNoDiamondsEnabled(!playerData.isNoDiamondsEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("allowMapBreak")) {
            playerData.setAllowMapBreakEnabled(!playerData.isAllowMapBreakEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("bedInstaBreak")) {
            playerData.setBedInstaBreakEnabled(!playerData.isBedInstaBreakEnabled());
            new SettingsMenu(player);
        }
        else if (tag.equalsIgnoreCase("maxTeamUpgrades")) {
            playerData.setMaxTeamUpgradesEnabled(!playerData.isMaxTeamUpgradesEnabled());
            new SettingsMenu(player);
        } else if (tag.equalsIgnoreCase("options")) new OptionsMenu(player);
        else if (tag.equalsIgnoreCase("start")) {
            switch (support) {
                case BEDWARS2023:
                    IArena a = PrivateGames.getBw2023Api().getArenaUtil().getArenaByIdentifier(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaIdentifier());
                    if (a.getStatus() != com.tomkeuper.bedwars.api.arena.GameState.playing && a.getStatus() != com.tomkeuper.bedwars.api.arena.GameState.starting) {
                        a.changeStatus(com.tomkeuper.bedwars.api.arena.GameState.starting);
                        a.getStartingTask().setCountdown(bw2023config.getInt("countdowns.game-start-regular"));
                    }
                    break;
                case BEDWARS1058:
                    com.andrei1058.bedwars.api.arena.IArena a1 = PrivateGames.getBw1058Api().getArenaUtil().getArenaByIdentifier(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getArenaIdentifier());
                    if (a1.getStatus() != GameState.playing && a1.getStatus() != GameState.starting) {
                        a1.changeStatus(GameState.starting);
                        a1.getStartingTask().setCountdown(bw1058config.getInt("countdowns.game-start-regular"));
                    }
                    break;
            }
            new SettingsMenu(player);
        } else if (tag.equalsIgnoreCase("gamemodeChanger")) {
            switch (support) {
                case BEDWARS1058:
                    if (e.getClick() == ClickType.RIGHT) {
                        api.getBedWars1058API().getArenaUtil().getArenaByPlayer(player).setGroup(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getDefaultGroup());
                        SidebarService.getInstance().remove(player);
                        SidebarService.getInstance().giveSidebar(player, api.getBedWars1058API().getArenaUtil().getArenaByPlayer(player), true);

                        ISidebar sidebar = SidebarService.getInstance().getSidebar(player);
                        assert sidebar != null;
                        sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                            if (api.getPrivateArenaUtil().isPlaying(player)) {
                                return Utility.getMsg(player, PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                            } else return "";
                        }));
                        sidebar.getHandle().refreshPlaceholders();
                        new SettingsMenu(player);
                    } else new GamemodeChangerMenu(player, api.getBedWars1058API().getArenaUtil().getArenaByPlayer(player));
                    break;
                case BEDWARS2023:
                    if (e.getClick() == ClickType.RIGHT) {
                        IArena a2 = api.getBedWars2023API().getArenaUtil().getArenaByPlayer(player);
                        a2.setGroup(api.getPrivateArenaUtil().getPrivateArenaByPlayer(player).getDefaultGroup());
                        new SettingsMenu(player);
                    } else new GamemodeChangerMenu(player, api.getBedWars2023API().getArenaUtil().getArenaByPlayer(player));
                    break;
            }
        } else if (tag.equalsIgnoreCase("back")) {
            if (mainConfig.getString(BACK_COMMAND).equalsIgnoreCase("close")) player.closeInventory();
            else {
                if (!api.getPrivateArenaUtil().isPlaying(player)) player.performCommand(mainConfig.getString(BACK_COMMAND));
                else player.closeInventory();
            }
        } else e.setCancelled(true);
    }
}
