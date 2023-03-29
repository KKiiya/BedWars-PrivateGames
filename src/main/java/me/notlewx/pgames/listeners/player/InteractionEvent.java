package me.notlewx.pgames.listeners.player;

import com.andrei1058.bedwars.api.language.Language;
import me.notlewx.pgames.menu.SettingsMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.notlewx.pgames.config.MessagesData.BACK_MEANING;
import static me.notlewx.pgames.config.MessagesData.ITEM_PRIVATE_GAME_NAME;

public class InteractionEvent implements Listener {
    static SettingsMenu menu;
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Language.getMsg(player, ITEM_PRIVATE_GAME_NAME))) {
             menu = new SettingsMenu(player);
        }
    }
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getAction() == InventoryAction.COLLECT_TO_CURSOR && e.getCurrentItem().getItemMeta().getDisplayName().equals(Language.getMsg(player, BACK_MEANING))) {
            SettingsMenu.closeMenu(player);
        }
    }
}
