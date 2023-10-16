package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.IArena;
import de.tr7zw.nbtapi.NBTItem;
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
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GENERATORS_TITLE;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GENERATOR_ITEM_LORE;

public class GeneratorsMenu implements GUIHolder {
    private Inventory inv;
    private final Player p;
    private final String arenaName;
    public GeneratorsMenu(Player p, String arenaName) {
        this.p = p;
        this.arenaName = arenaName;
        if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(p) == null) return;
        createInventory();
        addContents();
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
                NBTItem nbti = new NBTItem(mat);
                nbti.setString("package", (a.getOreGenerators().get(i)).getClass().getPackageName());
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
                NBTItem nbti = new NBTItem(mat);
                nbti.setString("package", (a.getOreGenerators().get(i)).getClass().getPackageName());
                inv.setItem(i, mat);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        NBTItem nbti = new NBTItem(e.getCurrentItem());
        if (e.getInventory().getTitle().equals(Utility.getMsg(p, SUBMENU_GENERATORS_TITLE))) {
            Class gen = null;
            try {
                gen = Class.forName(nbti.getString("package"));
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            new GeneratorSettingsMenu(p, gen);
        }
    }
}
