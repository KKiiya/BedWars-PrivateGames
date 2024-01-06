package me.notlewx.privategames.listeners;

import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_MENU_ITEM_NAME;

public class PlayerInteraction implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() == null) return;
        if (e.getItem().getType() == Material.AIR) return;
        if (e.getItem().getItemMeta() == null) return;
        if (e.getItem().getItemMeta().getDisplayName() == null) return;
        if (e.getItem().getItemMeta().getDisplayName().equals(Utility.getMsg(p, PRIVATE_GAME_MENU_ITEM_NAME))) {
            new SettingsMenu(p);
        }
    }
}
