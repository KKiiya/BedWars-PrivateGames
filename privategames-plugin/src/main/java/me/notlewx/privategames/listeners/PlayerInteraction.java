package me.notlewx.privategames.listeners;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.support.VersionSupport;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_MENU_ITEM_NAME;

public class PlayerInteraction implements Listener {

    private final VersionSupport vs;

    public PlayerInteraction() {
        this.vs = PrivateGames.getVersionSupport();
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if (item == null) return;
        if (item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) return;

        if (tag.equals("lobby-menu")) new SettingsMenu(p);
    }
}
