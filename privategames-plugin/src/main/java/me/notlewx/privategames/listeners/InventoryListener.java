package me.notlewx.privategames.listeners;

import me.notlewx.privategames.config.bedwars2023.MessagesData;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            if (e.getClickedInventory() == e.getWhoClicked().getInventory()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Utility.getMsg((Player) e.getWhoClicked(), MessagesData.PRIVATE_GAME_MENU_ITEM_NAME))) {
                    e.setCancelled(true);
                }
            }
        }

        if (e.getClickedInventory() != null && e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof GUIHolder && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
            e.setCancelled(true);
            ((GUIHolder) e.getInventory().getHolder()).onInventoryClick(e);
        }
    }
}
