package me.notlewx.pgames.menu.submenus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RespawnTimeMenu {
    public RespawnTimeMenu(Player player) {
        openSpeedMenu(player);
    }
    public void openSpeedMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 36);
        player.openInventory(inventory);
    }
    public void closeSpeedMenu(Player player) {
        player.closeInventory();
    }
}
