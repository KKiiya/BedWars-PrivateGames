package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
        ItemStack gene = new ItemStack(Material.AIR);
        String team = "";
        String location = "";
        String amount = "";
        String delay = "";
        String spawnLimit = "";
        if (support == Support.BEDWARS1058) {
            IGenerator g = (IGenerator) gen;
            if (g.getBwt() == null) {
                team = "No team";
            } else {
                team = g.getBwt().getName();
            }
            location = "X: " + g.getLocation().getX() + " Y: " + g.getLocation().getY() + " Z: " + g.getLocation().getZ() + " Pitch: " + g.getLocation().getPitch() + " Yaw: " + g.getLocation().getYaw();
            amount = String.valueOf(g.getAmount());
            delay = String.valueOf(g.getDelay());
            spawnLimit = String.valueOf(g.getSpawnLimit());
            gene = g.getOre();
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.generator.IGenerator g = (com.tomkeuper.bedwars.api.arena.generator.IGenerator) gen;
            if (g.getBedWarsTeam() == null) {
                team = "No team";
            } else {
                team = g.getBedWarsTeam().getName();
            }
            location = "X: " + g.getLocation().getX() + " Y: " + g.getLocation().getY() + " Z: " + g.getLocation().getZ() + " Pitch: " + g.getLocation().getPitch() + " Yaw: " + g.getLocation().getYaw();
            amount = String.valueOf(g.getAmount());
            delay = String.valueOf(g.getDelay());
            spawnLimit = String.valueOf(g.getSpawnLimit());
            gene = g.getOre();
        }
        String name = gene.getType().toString();
        ItemMeta geneMeta = gene.getItemMeta();
        geneMeta.setDisplayName(Utility.c("&a" + name + " generator"));
        geneMeta.setLore(Arrays.asList(
                "§7Team: " + team,
                "§7Location: " + location,
                "§7Amount: " + amount,
                "§7Delay: " + delay,
                "§7Spawn Limit: " + spawnLimit));
        gene.setItemMeta(geneMeta);
        inv.setItem(13, gene);
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
