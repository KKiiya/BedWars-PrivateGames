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

import java.util.HashMap;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.support;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class GeneratorsMenu implements GUIHolder {
    private Inventory inv;
    private final Player p;
    private final String arenaName;
    private final HashMap<Integer, Object> generatorPos;
    public GeneratorsMenu(Player p, String arenaName) {
        this.p = p;
        this.arenaName = arenaName;
        generatorPos = new HashMap<>();
        if (api.getPrivateArenaUtil().getPrivateArenaByPlayer(p) == null) return;
        createInventory();
        addContents();
        p.openInventory(inv);
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, 9*4, Utility.getMsg(p, SUBMENU_GENERATORS_TITLE));
    }
    private void addContents() {
        if (support == Support.BEDWARS1058) {
            IArena a = api.getBedWars1058API().getArenaUtil().getArenaByIdentifier(arenaName);
            for (int i = 0; i < a.getOreGenerators().size(); i++) {
                String team;
                String location;
                String amount;
                String delay;
                String spawnLimit;
                if (a.getOreGenerators().get(i).getBwt() == null) {
                    team = "No team";
                } else {
                    team = a.getOreGenerators().get(i).getBwt().getName();
                }
                location = "X: " + a.getOreGenerators().get(i).getLocation().getX() + " Y: " + a.getOreGenerators().get(i).getLocation().getY() + " Z: " + a.getOreGenerators().get(i).getLocation().getZ() + " Pitch: " + a.getOreGenerators().get(i).getLocation().getPitch() + " Yaw: " + a.getOreGenerators().get(i).getLocation().getYaw();
                amount = String.valueOf(a.getOreGenerators().get(i).getAmount());
                delay = String.valueOf(a.getOreGenerators().get(i).getDelay());
                spawnLimit = String.valueOf(a.getOreGenerators().get(i).getSpawnLimit());
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName("§a" + a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_ITEM_LORE).stream().map(s -> s
                                .replace("{team}", team)
                                .replace("{location}", location)
                                .replace("{amount}", amount)
                                .replace("{delay}", delay)
                                .replace("{spawnLimit}", spawnLimit))
                        .collect(Collectors.toList()));
                mat.setItemMeta(matMeta);
                generatorPos.put(i, a.getOreGenerators().get(i));
                inv.setItem(i, mat);
            }
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.IArena a = api.getBedWars2023API().getArenaUtil().getArenaByIdentifier(arenaName);
            for (int i = 0; i < a.getOreGenerators().size(); i++) {
                String team;
                String location;
                String amount;
                String delay;
                String spawnLimit;
                if (a.getOreGenerators().get(i).getBedWarsTeam() == null) {
                    team = "No team";
                } else {
                    team = a.getOreGenerators().get(i).getBedWarsTeam().getName();
                }
                location = "X: " + a.getOreGenerators().get(i).getLocation().getX() + " Y: " + a.getOreGenerators().get(i).getLocation().getY() + " Z: " + a.getOreGenerators().get(i).getLocation().getZ() + " Pitch: " + a.getOreGenerators().get(i).getLocation().getPitch() + " Yaw: " + a.getOreGenerators().get(i).getLocation().getYaw();
                amount = String.valueOf(a.getOreGenerators().get(i).getAmount());
                delay = String.valueOf(a.getOreGenerators().get(i).getDelay());
                spawnLimit = String.valueOf(a.getOreGenerators().get(i).getSpawnLimit());
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName("§a" + a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_ITEM_LORE).stream().map(s -> s
                                .replace("{team}", team)
                                .replace("{location}", location)
                                .replace("{amount}", amount)
                                .replace("{delay}", delay)
                                .replace("{spawnLimit}", spawnLimit))
                        .collect(Collectors.toList()));
                mat.setItemMeta(matMeta);
                generatorPos.put(i, a.getOreGenerators().get(i));
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
        if (e.getInventory().getTitle().equals(Utility.getMsg(p, SUBMENU_GENERATORS_TITLE))) {
            new GeneratorSettingsMenu(p, generatorPos.get(e.getSlot()));
        }
    }
}
