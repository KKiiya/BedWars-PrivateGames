package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import static me.notlewx.privategames.PrivateGames.support;

public class GeneratorSettingsMenu implements GUIHolder {
    private final Player p;
    private final Object gen;
    private Inventory inv;
    public GeneratorSettingsMenu(Player p, Object gen) {
        this.p = p;
        this.gen = gen;
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, 9*4, Utility.getMsg(p, ""));
    }

    private void addContents() {

    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (support == Support.BEDWARS1058) {
            IGenerator g = (IGenerator) gen;
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.generator.IGenerator g = (com.tomkeuper.bedwars.api.arena.generator.IGenerator) gen;
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
