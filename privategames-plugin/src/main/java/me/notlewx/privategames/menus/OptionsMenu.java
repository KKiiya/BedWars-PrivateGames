package me.notlewx.privategames.menus;

import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerOptions;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.menus.submenus.generators.GeneratorsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_ENABLED;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_ENABLED_OTHERS;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_NOT_IN_PARTY;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_NOT_OWNER;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_GAME_CANT_IN_GAME;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_GAME_DISABLED;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_GAME_DISABLED_OTHERS;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class OptionsMenu implements GUIHolder {
    private final Player player;
    private Inventory inv;

    public OptionsMenu(Player player) {
        this.player = player;
        try {
            createInventory();
            addContent();
            player.openInventory(inv);
        } catch (Exception e) {
            throw new RuntimeException("Error while opening the settings menu", e);
        }
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, mainConfig.getInt(OPTIONS_MENU_ROWS) * 9, Utility.getMsg(player, SUBMENU_OPTIONS_TITLE));
    }

    private void addContent() {
        Material generatorSettingsMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_GENERATORS_MATERIAL));
        ItemStack generatorSettings = new ItemStack(generatorSettingsMaterial, 1, (byte) mainConfig.getInt(OPTIONS_GENERATORS_ID));
        if (generatorSettings.getType().toString().equals("SKULL_ITEM") || generatorSettings.getType().toString().equals("LEGACY_SKULL_ITEM") && generatorSettings.getDurability() == 3) {
            generatorSettings = Utility.getSkull(generatorSettingsMaterial, mainConfig.getString(OPTIONS_GENERATORS_HEAD_URL));
        }
        ItemMeta generatorSettingsMeta = generatorSettings.getItemMeta();

        Material allowJoinMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_ALLOWJOIN_MATERIAL));
        ItemStack allowJoin = new ItemStack(allowJoinMaterial, 1, (short) mainConfig.getInt(OPTIONS_ALLOWJOIN_ID));
        if (allowJoin.getType().toString().equals("SKULL_ITEM") || allowJoin.getType().toString().equals("LEGACY_SKULL_ITEM") && allowJoin.getDurability() == 3) {
            allowJoin = Utility.getSkull(allowJoinMaterial, mainConfig.getString(OPTIONS_ALLOWJOIN_HEAD_URL));
        }
        ItemMeta allowJoinMeta = allowJoin.getItemMeta();

        Material autoStartMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_ENABLE_AUTOSTART_MATERIAL));
        ItemStack autoStart = new ItemStack(autoStartMaterial, 1, (short) mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_ID));
        if (autoStart.getType().toString().equals("SKULL_ITEM") || autoStart.getType().toString().equals("LEGACY_SKULL_ITEM") && autoStart.getDurability() == 3) {
            autoStart = Utility.getSkull(autoStartMaterial, mainConfig.getString(OPTIONS_ENABLE_AUTOSTART_HEAD_URL));
        }
        ItemMeta autoStartMeta = autoStart.getItemMeta();

        Material privateGamesMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_ENABLE_PRIVATEGAMES_MATERIAL));
        ItemStack privateGames = new ItemStack(privateGamesMaterial, 1, (short) mainConfig.getInt(OPTIONS_ENABLE_PRIVATEGAMES_ID));
        if (privateGames.getType().toString().equals("SKULL_ITEM") || privateGames.getType().toString().equals("LEGACY_SKULL_ITEM") && privateGames.getDurability() == 3) {
            privateGames = Utility.getSkull(privateGamesMaterial, mainConfig.getString(OPTIONS_ENABLE_PRIVATEGAMES_HEAD_URL));
        }
        ItemMeta privateGamesMeta = privateGames.getItemMeta();

        Material backMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_BACK_MATERIAL));
        ItemStack back = new ItemStack(backMaterial, 1, (short) mainConfig.getInt(OPTIONS_BACK_ID));
        if (back.getType().toString().equals("SKULL_ITEM") || back.getType().toString().equals("LEGACY_SKULL_ITEM") && back.getDurability() == 3) {
            back = Utility.getSkull(backMaterial, mainConfig.getString(OPTIONS_BACK_HEAD_URL));
        }
        ItemMeta backMeta = back.getItemMeta();


        IPlayerOptions po = api.getPrivatePlayer(player).getPlayerOptions();
        IPlayerSettings ps = api.getPrivatePlayer(player).getPlayerSettings();

        generatorSettingsMeta.setDisplayName(Utility.getMsg(player, SUBMENU_OPTIONS_GENERATORS_NAME));
        generatorSettingsMeta.setLore(Utility.getList(player, SUBMENU_OPTIONS_GENERATORS_LORE));
        generatorSettingsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        allowJoinMeta.setDisplayName(Utility.getMsg(player, SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME));
        allowJoinMeta.setLore(Utility.getList(player, SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE).stream().map(s -> s.replace("{state}", po.isAllowJoin() ? Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_ENABLED) : Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_DISABLED))).collect(Collectors.toList()));
        allowJoinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        autoStartMeta.setDisplayName(Utility.getMsg(player, SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME));
        autoStartMeta.setLore(Utility.getList(player, SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE).stream().map(s -> s.replace("{state}", po.isAutoStart() ? Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_ENABLED) : Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_DISABLED))).collect(Collectors.toList()));
        autoStartMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        privateGamesMeta.setDisplayName(Utility.getMsg(player, SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME));
        privateGamesMeta.setLore(Utility.getList(player, SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE).stream().map(s -> s.replace("{state}", ps.isPrivateGameEnabled() ? Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_ENABLED) : Utility.getMsg(player, SUBMENU_OPTIONS_MEANING_DISABLED))).collect(Collectors.toList()));
        privateGamesMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        backMeta.setDisplayName(Utility.getMsg(player, SUBMENU_OPTIONS_BACK_NAME));
        backMeta.setLore(Utility.getList(player, SUBMENU_OPTIONS_BACK_LORE));
        backMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        generatorSettings.setItemMeta(generatorSettingsMeta);
        allowJoin.setItemMeta(allowJoinMeta);
        autoStart.setItemMeta(autoStartMeta);
        privateGames.setItemMeta(privateGamesMeta);

        back.setItemMeta(backMeta);

        if (po.isAllowJoin()) allowJoin.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else allowJoin.removeEnchantment(Enchantment.DURABILITY);
        if (po.isAutoStart()) autoStart.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        else autoStart.removeEnchantment(Enchantment.DURABILITY);

        if (mainConfig.getBoolean(OPTIONS_GENERATORS)) if (api.getPrivateArenaUtil().isPlaying(player)) inv.setItem(mainConfig.getInt(OPTIONS_GENERATORS_POSITION), generatorSettings);
        if (mainConfig.getBoolean(OPTIONS_ALLOW_JOIN)) inv.setItem(mainConfig.getInt(OPTIONS_ALLOWJOIN_POSITION), allowJoin);
        if (mainConfig.getBoolean(OPTIONS_ENABLE_AUTOSTART)) inv.setItem(mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_POSITION), autoStart);
        if (mainConfig.getBoolean(OPTIONS_ENABLE_PRIVATEGAMES)) inv.setItem(mainConfig.getInt(OPTIONS_ENABLE_PRIVATEGAMES_POSITION), privateGames);
        inv.setItem(mainConfig.getInt(OPTIONS_BACK_POSITION), back);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(player, SUBMENU_OPTIONS_TITLE))) {
            if (e.getSlot() == mainConfig.getInt(OPTIONS_GENERATORS_POSITION)) {
                new GeneratorsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_ALLOWJOIN_POSITION)) {
                IPrivatePlayer pp = api.getPrivatePlayer(player);
                IPlayerOptions po = pp.getPlayerOptions();
                po.setAllowJoin(!po.isAllowJoin());
                new OptionsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_ENABLE_AUTOSTART_POSITION)) {
                IPrivatePlayer pp = api.getPrivatePlayer(player);
                IPlayerOptions po = pp.getPlayerOptions();
                po.setAutoStart(!po.isAutoStart());
                new OptionsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_BACK_POSITION)) {
                new SettingsMenu(player);
            } else if (e.getSlot() == mainConfig.getInt(OPTIONS_ENABLE_PRIVATEGAMES_POSITION)) {
                IPrivatePlayer pp = api.getPrivatePlayer(player);
                IPlayerSettings ps = pp.getPlayerSettings();
                IParty party = pp.getPlayerParty();
                switch (support) {
                    case BEDWARS1058:
                    case BEDWARS2023:
                        if (pp.isPlaying()) player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_CANT_IN_GAME));
                        else {
                            if (!ps.isPrivateGameEnabled()) {
                                if (player.hasPermission("pg.admin")) {
                                    ps.setPrivateGameEnabled();
                                    player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED));
                                    if (party.hasParty() && party.isOwner()) {
                                        for (OfflinePlayer player : party.getPartyMembers()) {
                                            if (player != this.player) {
                                                ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                            }
                                        }
                                    }
                                } else {
                                    if (party.hasParty()) {
                                        if (party.isOwner()) {
                                            ps.setPrivateGameEnabled();
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED));
                                            for (OfflinePlayer player : party.getPartyMembers()) {
                                                if (player != this.player) {
                                                    ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                                }
                                            }
                                        } else {
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_IN_PARTY));
                                    }
                                }
                            } else {
                                if (player.hasPermission("pg.admin")) {
                                    ps.setPrivateGameDisabled(false);
                                    player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                    if (party.hasParty() && party.isOwner()) {
                                        for (OfflinePlayer player : party.getPartyMembers()) {
                                            if (player != this.player) {
                                                ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                            }
                                        }
                                    }
                                } else {
                                    if (party.hasParty()) {
                                        if (party.isOwner()) {
                                            ps.setPrivateGameDisabled(false);
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                            for (OfflinePlayer player : party.getPartyMembers()) {
                                                if (player != this.player && player.isOnline()) {
                                                    ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                                }
                                            }
                                        } else player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_OWNER));

                                    } else {
                                        ps.setPrivateGameDisabled(false);
                                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                    }
                                }
                            }
                        }
                        break;
                    case BEDWARSPROXY:
                    case BEDWARSPROXY2023:
                            if (!ps.isPrivateGameEnabled()) {
                                if (player.hasPermission("pg.admin")) {
                                    ps.setPrivateGameEnabled();
                                    player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED));
                                    if (party.hasParty() && party.isOwner()) {
                                        for (OfflinePlayer player : party.getPartyMembers()) {
                                            if (player != this.player) {
                                                ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                            }
                                        }
                                    }
                                } else {
                                    if (party.hasParty()) {
                                        if (party.isOwner()) {
                                            ps.setPrivateGameEnabled();
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED));
                                            for (OfflinePlayer player : party.getPartyMembers()) {
                                                if (player != this.player) {
                                                    ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                                }
                                            }
                                        } else {
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_IN_PARTY));
                                    }
                                }
                            } else {
                                if (player.hasPermission("pg.admin")) {
                                    ps.setPrivateGameDisabled(false);
                                    player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                    if (party.hasParty() && party.isOwner()) {
                                        for (OfflinePlayer player : party.getPartyMembers()) {
                                            if (player != this.player) {
                                                ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                            }
                                        }
                                    }
                                } else {
                                    if (party.hasParty()) {
                                        if (party.isOwner()) {
                                            ps.setPrivateGameDisabled(false);
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                            for (OfflinePlayer player : party.getPartyMembers()) {
                                                if (player != this.player) {
                                                    ((Player) player).sendMessage(Utility.getMsg((Player) player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", this.player.getDisplayName()));
                                                }
                                            }
                                        } else {
                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        ps.setPrivateGameDisabled(false);
                                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                                    }
                                }
                            }
                        break;
                }
                new OptionsMenu(player);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
