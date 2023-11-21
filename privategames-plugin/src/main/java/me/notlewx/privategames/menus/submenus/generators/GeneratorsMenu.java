package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.IArena;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.menus.OptionsMenu;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.config.MainConfig.*;
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
        if (!api.getPrivateArenaUtil().isPlaying(p)) return;
        createInventory();
        addContents();
        p.openInventory(inv);
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, 9*4, Utility.getMsg(p, SUBMENU_GENERATORS_OPTIONS_TITLE));
    }
    private void addContents() {
        if (support == Support.BEDWARS1058) {
            IArena a = api.getBedWars1058API().getArenaUtil().getArenaByName(arenaName);
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
                location = "X: " + a.getOreGenerators().get(i).getLocation().getX() + " Y: " + a.getOreGenerators().get(i).getLocation().getY() + " Z: " + a.getOreGenerators().get(i).getLocation().getZ();
                amount = String.valueOf(a.getOreGenerators().get(i).getAmount());
                delay = String.valueOf(a.getOreGenerators().get(i).getDelay());
                spawnLimit = String.valueOf(a.getOreGenerators().get(i).getSpawnLimit());
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName("§a" + a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_OPTIONS_ITEM_LORE).stream().map(s -> s
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
            com.tomkeuper.bedwars.api.arena.IArena a = api.getBedWars2023API().getArenaUtil().getArenaByName(arenaName);
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
                location = "X: " + a.getOreGenerators().get(i).getLocation().getX() + " Y: " + a.getOreGenerators().get(i).getLocation().getY() + " Z: " + a.getOreGenerators().get(i).getLocation().getZ();
                amount = String.valueOf(a.getOreGenerators().get(i).getAmount());
                delay = String.valueOf(a.getOreGenerators().get(i).getDelay()/4);
                spawnLimit = String.valueOf(a.getOreGenerators().get(i).getSpawnLimit());
                ItemStack mat = (a.getOreGenerators().get(i)).getOre();
                ItemMeta matMeta = mat.getItemMeta();
                matMeta.setDisplayName("§a" + a.getOreGenerators().get(i).getType().toString());
                matMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_OPTIONS_ITEM_LORE).stream().map(s -> s
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

        Material backMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_GENERATOR_OPTIONS_BACK_MATERIAL));
        ItemStack back;
        if (backMaterial == Material.SKULL_ITEM) {
            back = Utility.getSkull(mainConfig.getString(OPTIONS_GENERATOR_OPTIONS_BACK_HEAD_URL));
        } else {
            back = new ItemStack(backMaterial, 1, (short) mainConfig.getInt(OPTIONS_GENERATOR_OPTIONS_BACK_ID));
        }
        ItemMeta backMeta = back.getItemMeta();

        backMeta.setDisplayName(Utility.getMsg(p, SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME));
        backMeta.setLore(Utility.getList(p, SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE));

        back.setItemMeta(backMeta);

        inv.setItem(mainConfig.getInt(OPTIONS_GENERATORS_BACK_POSITION), back);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals(Utility.getMsg(p, SUBMENU_GENERATORS_OPTIONS_TITLE))) {
            if (generatorPos.get(e.getSlot()) != null) {
                new GeneratorSettingsMenu(p, generatorPos.get(e.getSlot()));
            } else {
                if (e.getSlot() == mainConfig.getInt(OPTIONS_GENERATORS_BACK_POSITION)) {
                    new OptionsMenu(p);
                }
            }
        }
    }
}
