package me.notlewx.pgames.listeners.player;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.menu.SettingsMenu;
import me.notlewx.pgames.menu.submenus.EventTimesMenu;
import me.notlewx.pgames.menu.submenus.HealthMenu;
import me.notlewx.pgames.menu.submenus.RespawnTimeMenu;
import me.notlewx.pgames.menu.submenus.SpeedMenu;
import me.notlewx.pgames.util.Utility;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static me.notlewx.pgames.config.MessagesData.*;

public class InteractionEvent implements Listener {
    private static final IPlayerData playerData = PGamesAPI.getPlayerData();
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        try {
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, PRIVATE_GAME_MENU_ITEM_NAME)))
                new SettingsMenu(player);
        } catch (Throwable ex) {

        }
    }
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        try {
            if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, MAIN_MENU_NAME))) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, MENU_BACK_ITEM_NAME))) {
                    SettingsMenu.closeMenu(player);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_ONE_HIT_ONE_KILL_NAME))) {
                    if (playerData.isOHOKEnabled(player)) {
                        playerData.setOHOKEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isOHOKEnabled(player)) {
                        playerData.setOHOKEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_ONE_HIT_ONE_KILL_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_ALLOW_MAP_BREAK_NAME))) {
                    if (playerData.isAMBEnabled(player)) {
                        playerData.setAMBEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isAMBEnabled(player)) {
                        playerData.setAMBEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_ALLOW_MAP_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_BED_INSTA_BREAK_NAME))) {
                    if (playerData.isBIBEnabled(player)) {
                        playerData.setBIBEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isBIBEnabled(player)) {
                        playerData.setBIBEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_BED_INSTA_BREAK_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_LOW_GRAVITY_NAME))) {
                    if (playerData.isLGEnabled(player)) {
                        playerData.setLGEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isLGEnabled(player)) {
                        playerData.setLGEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_LOW_GRAVITY_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_NO_DIAMONDS_NAME))) {
                    if (playerData.isNDEnabled(player)) {
                        playerData.setNDEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isNDEnabled(player)) {
                        playerData.setNDEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_NO_DIAMONDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_NO_EMERALDS_NAME))) {
                    if (playerData.isNEEnabled(player)) {
                        playerData.setNEEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isNEEnabled(player)) {
                        playerData.setNEEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_NO_EMERALDS_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_MAX_TEAM_UPGRADES_NAME))) {
                    if (playerData.isMTUEnabled(player)) {
                        playerData.setMTUEnabled(player, false);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isMTUEnabled(player)) {
                        playerData.setMTUEnabled(player, true);
                        ItemMeta meta = e.getCurrentItem().getItemMeta();
                        meta.setLore(Utility.getListLang(player, ITEM_MAX_TEAM_UPGRADES_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                        e.getCurrentItem().setItemMeta(meta);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_HEALTH_BUFF_LEVEL_NAME))) {
                    SettingsMenu.closeMenu(player);
                    new HealthMenu(player);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SPEED_NAME))) {
                    SettingsMenu.closeMenu(player);
                    new SpeedMenu(player);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_EVENTS_TIME_LEVEL_NAME))) {
                    SettingsMenu.closeMenu(player);
                    new EventTimesMenu(player);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_RESPAWN_EVENT_TIME_NAME))) {
                    SettingsMenu.closeMenu(player);
                    new RespawnTimeMenu(player);
                }
                if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, MAIN_MENU_NAME)))
                    e.setCancelled(true);
            }

            else if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, SUBMENU_SPEED_NAME))) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, SUBMENU_SPEED_BACK_ITEM_NAME))) {
                    SpeedMenu.closeSpeedMenu(player);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME))) {
                    playerData.setSpeedLevel(player, 1);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_NAME))) {
                    playerData.setSpeedLevel(player, 2);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                        }
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                    playerData.setSpeedLevel(player, 3);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_IV_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME))) {
                    playerData.setSpeedLevel(player, 4);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_IV_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_I_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_II_LORE))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
            }

            else if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, SUBMENU_HEALTH_BUFF_NAME))) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME))) {
                    SpeedMenu.closeSpeedMenu(player);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME))) {
                    playerData.setHBLevel(player, 1);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.GOLDEN_APPLE).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME))) {
                    playerData.setHBLevel(player, 2);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_SPEED_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_III_NAME))) {
                    e.setCancelled(true);
                    playerData.setHBLevel(player, 3);
                    ItemMeta meta = e.getCurrentItem().getItemMeta();
                    meta.setLore(e.getCurrentItem().getItemMeta().getLore().stream().map(s -> s.replace("{selected}", Utility.getMSGLang(player, MENU_SELECTED_MEANING))).collect(Collectors.toList()));
                    Arrays.stream(e.getInventory().getContents()).forEach(i -> i.removeEnchantment(Enchantment.DURABILITY));
                    e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    List<ItemStack> books = Arrays.stream(e.getInventory().getContents()).filter(i -> i.getType() == Material.RABBIT_FOOT).collect(Collectors.toList());
                    for (ItemStack material : books) {
                        if (!material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_SPEED_III_NAME))) {
                            ItemMeta materialMeta = material.getItemMeta();
                            if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_I_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            else if (material.getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_NAME))) {
                                materialMeta.setLore(Utility.getListLang(player, ITEM_SUBMENU_HEALTH_BUFF_II_LORE).stream().map(s -> s.replace("{state}", Utility.getMSGLang(player, MENU_CLICK_TO_SELECT_MEANING))).collect(Collectors.toList()));
                                material.setItemMeta(materialMeta);
                            }
                            material.removeEnchantment(Enchantment.DURABILITY);
                        }
                    }
                }
            }

            else if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, SUBMENU_EVENTS_TIME_NAME))) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, SUBMENU_EVENTS_TIME_BACK_ITEM_NAME))) {
                    SpeedMenu.closeSpeedMenu(player);
                }
                e.setCancelled(true);
            }

            else if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, SUBMENU_RESPAWN_TIME_NAME))) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME))) {
                    SpeedMenu.closeSpeedMenu(player);
                }
                e.setCancelled(true);
            }
        } catch (Throwable ex) {

        }
    }
}
