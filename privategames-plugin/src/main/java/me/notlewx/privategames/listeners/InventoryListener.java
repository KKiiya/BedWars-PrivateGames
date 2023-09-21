package me.notlewx.privategames.listeners;

import me.notlewx.privategames.menus.GUIHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof GUIHolder && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
            e.setCancelled(true);
            ((GUIHolder) e.getInventory().getHolder()).onInventoryClick(e);
        }
    }
}
