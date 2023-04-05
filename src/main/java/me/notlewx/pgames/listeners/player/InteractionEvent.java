package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.language.Language;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.menu.SettingsMenu;
import me.notlewx.pgames.util.Utility;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
            if (e.getInventory().getTitle().equals(Utility.getMSGLang(player, MAIN_MENU_NAME))) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, MENU_BACK_ITEM_NAME))) {
                    SettingsMenu.closeMenu(player);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_ONE_HIT_ONE_KILL_NAME))) {
                    if (playerData.isOHOKEnabled(player)) {
                        playerData.setOHOKEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isOHOKEnabled(player)) {
                        playerData.setOHOKEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_ALLOW_MAP_BREAK_NAME))) {
                    if (playerData.isAMBEnabled(player)) {
                        playerData.setAMBEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isAMBEnabled(player)) {
                        playerData.setAMBEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_BED_INSTA_BREAK_NAME))) {
                    if (playerData.isBIBEnabled(player)) {
                        playerData.setBIBEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isBIBEnabled(player)) {
                        playerData.setBIBEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_LOW_GRAVITY_NAME))) {
                    if (playerData.isLGEnabled(player)) {
                        playerData.setLGEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isLGEnabled(player)) {
                        playerData.setLGEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_NO_DIAMONDS_NAME))) {
                    if (playerData.isNDEnabled(player)) {
                        playerData.setNDEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isNDEnabled(player)) {
                        playerData.setNDEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMSGLang(player, ITEM_NO_EMERALDS_NAME))) {
                    if (playerData.isNEEnabled(player)) {
                        playerData.setNEEnabled(player, false);
                        e.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                    } else if (!playerData.isNEEnabled(player)) {
                        playerData.setNEEnabled(player, true);
                        e.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                }
                if (e.getInventory().getTitle().equals(Language.getMsg(player, MAIN_MENU_NAME))) e.setCancelled(true);
            }
    }
}
