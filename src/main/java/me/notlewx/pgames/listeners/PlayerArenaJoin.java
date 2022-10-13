package me.notlewx.pgames.listeners;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class PlayerArenaJoin implements Listener {
    static ItemStack rblock = new ItemStack(Material.REDSTONE_BLOCK);
    public static boolean join = false;
    public static int conf = main.getPlugin(main.class).getConfig().getInt("private-games-item.slot");
    @EventHandler
    public static void onPlayerJoin(PlayerJoinArenaEvent e) {
       rblock.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aPrivate Game Settings &7(Right Click)"));
       List<String> lore = new ArrayList<>();
       boolean enchanted = (main.getPlugin(main.class).getConfig().getBoolean("private-games-item.enchanted"));

       if (enchanted) {
         rblock.getItemMeta().addEnchant(null,1,true);
       }
       else {
         rblock.getItemMeta().removeEnchant(null);
       }

       lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right click me to open the"));
       lore.add(ChatColor.translateAlternateColorCodes('&', "&7private game settings menu"));
       rblock.getItemMeta().setLore(lore);
       join = true;
       Player p = e.getPlayer();
       Inventory i = p.getInventory();
       i.setItem(conf, rblock);
    }
}
