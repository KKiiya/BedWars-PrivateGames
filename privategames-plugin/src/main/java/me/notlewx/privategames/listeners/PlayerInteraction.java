package me.notlewx.privategames.listeners;

import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;

public class PlayerInteraction implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Utility.getMsg(p, PRIVATE_GAME_MENU_ITEM_NAME)))
                new SettingsMenu(p);
        } catch (Throwable ex) {
            // Do nothing
        }
    }
}
