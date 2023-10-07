package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.IArena;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.support;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GENERATOR_ITEM_LORE;

public class GeneratorsMenu implements GUIHolder {
    private Inventory inv;
    private final Player p;
    private final String arenaName;
    public GeneratorsMenu(Player p, String arenaName) {
        this.p = p;
        this.arenaName = arenaName;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {

    }
    private void createInventory() {
        inv = Bukkit.createInventory(this, 9*4, Utility.getMsg(p, ""));
    }
    private void addContents() {
        if (support == Support.BEDWARS1058) {
            IArena a = api.getBedWars1058API().getArenaUtil().getArenaByIdentifier(arenaName);
            for (int i = 0; i < a.getOreGenerators().size(); i++) {
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName(a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, ""));
                mat.setItemMeta(matMeta);
                inv.setItem(i, mat);
            }
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.IArena a = api.getBedWars2023API().getArenaUtil().getArenaByIdentifier(arenaName);
            for (int i = 0; i < a.getOreGenerators().size(); i++) {
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName(a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_ITEM_LORE));
                mat.setItemMeta(matMeta);
                inv.setItem(i, mat);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
