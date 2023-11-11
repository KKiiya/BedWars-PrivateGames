package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.support.Support;
import me.notlewx.privategames.utils.GeneratorProperties;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.stream.Collectors;

import static me.notlewx.privategames.PrivateGames.support;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GENERATOR_ITEM_LORE;

public class GeneratorSettingsMenu implements GUIHolder {
    private final Player p;
    private final Object gen;
    private Inventory inv;
    public GeneratorSettingsMenu(Player p, Object gen) {
        this.p = p;
        this.gen = gen;
        createInventory();
        addContents();
        p.openInventory(inv);
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, 9*4, "Generator settings");
    }

    private void addContents() {
        ItemStack gene = new ItemStack(Material.AIR);
        String team;
        String location;
        String amount;
        String delay;
        String spawnLimit;
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
        } else {
            spawnLimit = "";
            delay = "";
            amount = "";
            location = "";
            team = "";
        }
        String name = gene.getType().toString();
        ItemMeta geneMeta = gene.getItemMeta();
        geneMeta.setDisplayName(Utility.c("&a" + name + " generator"));
        geneMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_ITEM_LORE).stream().map(s -> s
                .replace("{team}", team)
                .replace("{location}", location)
                .replace("{amount}", amount)
                .replace("{delay}", delay)
                .replace("{spawnLimit}", spawnLimit))
                .collect(Collectors.toList()));
        gene.setItemMeta(geneMeta);


        ItemStack reduceAmount = Utility.getSkull("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b");
        ItemMeta reduceAmountMeta = reduceAmount.getItemMeta();
        reduceAmountMeta.setDisplayName(Utility.c("&cReduce Amount"));
        reduceAmountMeta.setLore((Arrays.asList("", "&7Reduce the amount of minerals dropped", "&7in this generator", "", "&eClick to decrease!")).stream().map(Utility::c).collect(Collectors.toList()));
        reduceAmount.setItemMeta(reduceAmountMeta);

        ItemStack increaseAmount = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseAmountMeta = increaseAmount.getItemMeta();
        increaseAmountMeta.setDisplayName(Utility.c("&aIncrease Amount"));
        increaseAmountMeta.setLore((Arrays.asList("", "&7Increase the amount of minerals dropped", "&7in this generator", "", "&eClick to increase!")).stream().map(Utility::c).collect(Collectors.toList()));
        increaseAmount.setItemMeta(increaseAmountMeta);


        ItemStack reduceDelay = Utility.getSkull("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b");
        ItemMeta reduceDelayMeta = reduceDelay.getItemMeta();
        reduceDelayMeta.setDisplayName(Utility.c("&cReduce Delay"));
        reduceDelayMeta.setLore((Arrays.asList("", "&7Reduce the delay of", "&7this generator", "", "&eClick to decrease!")).stream().map(Utility::c).collect(Collectors.toList()));
        reduceDelay.setItemMeta(reduceDelayMeta);

        ItemStack increaseDelay = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseDelayMeta = increaseDelay.getItemMeta();
        increaseDelayMeta.setDisplayName(Utility.c("&aIncrease Delay"));
        increaseDelayMeta.setLore((Arrays.asList("", "&7Increase the delay of", "&7this generator", "", "&eClick to increase!")).stream().map(Utility::c).collect(Collectors.toList()));
        increaseDelay.setItemMeta(increaseDelayMeta);


        ItemStack reduceSpawnLimit = Utility.getSkull("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b");
        ItemMeta reduceSpawnLimitMeta = reduceSpawnLimit.getItemMeta();
        reduceSpawnLimitMeta.setDisplayName(Utility.c("&cReduce"));
        reduceSpawnLimitMeta.setLore((Arrays.asList("", "&7Reduce the amount minerals stacked", "&7in this generator", "", "&eClick to decrease!")).stream().map(Utility::c).collect(Collectors.toList()));
        reduceSpawnLimit.setItemMeta(reduceSpawnLimitMeta);

        ItemStack increaseSpawnLimit = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseSpawnLimitMeta = increaseSpawnLimit.getItemMeta();
        increaseSpawnLimitMeta.setDisplayName(Utility.c("&aIncrease Spawn Limit"));
        increaseSpawnLimitMeta.setLore((Arrays.asList("", "&7Increase the amount minerals stacked", "&7in this generator", "", "&eClick to increase!")).stream().map(Utility::c).collect(Collectors.toList()));
        increaseSpawnLimit.setItemMeta(increaseSpawnLimitMeta);




        inv.setItem(13, gene);

        inv.setItem(12, reduceAmount);
        inv.setItem(14, increaseAmount);

        inv.setItem(21, reduceDelay);
        inv.setItem(23, increaseDelay);

        inv.setItem(30, reduceSpawnLimit);
        inv.setItem(32, increaseSpawnLimit);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals("Generator settings")) return;
        GeneratorProperties.Properties properties;
        if (support == Support.BEDWARS1058) {
            IGenerator g = (IGenerator) gen;
            if (GeneratorProperties.getGenProps().get(g) == null) {
                properties = new GeneratorProperties(g).getProperties();
            } else {
                properties = GeneratorProperties.getGenProps().get(g);
            }
            switch (e.getSlot()) {
                case 12:
                    if (g.getAmount() == 1) return;
                    g.setAmount(g.getAmount() - 1);
                    properties.setAmount(g.getAmount());
                    break;
                case 14:
                    g.setAmount(g.getAmount() + 1);
                    properties.setAmount(g.getAmount());
                    break;
                case 21:
                    if (g.getDelay() == 1) return;
                    g.setDelay(g.getDelay() - 1);
                    properties.setDelay((int) g.getDelay());
                    break;
                case 23:
                    g.setDelay(g.getDelay() + 1);
                    properties.setDelay((int) g.getDelay());
                    break;
                case 30:
                    if (g.getSpawnLimit() == 1) return;
                    g.setSpawnLimit(g.getSpawnLimit() - 1);
                    properties.setSpawnLimit(g.getSpawnLimit());
                    break;
                case 32:
                    g.setSpawnLimit(g.getSpawnLimit() + 1);
                    properties.setSpawnLimit(g.getSpawnLimit());
                    break;
            }
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.generator.IGenerator g = (com.tomkeuper.bedwars.api.arena.generator.IGenerator) gen;
            if (GeneratorProperties.getGenProps().get(g) == null) {
                properties = new GeneratorProperties(g).getProperties();
            } else {
                properties = GeneratorProperties.getGenProps().get(g);
            }
            switch (e.getSlot()) {
                case 12:
                    if (g.getAmount() == 1) return;
                    g.setAmount(g.getAmount() - 1);
                    properties.setAmount(g.getAmount());
                    break;
                case 14:
                    g.setAmount(g.getAmount() + 1);
                    properties.setAmount(g.getAmount());
                    break;
                case 21:
                    if (g.getDelay() == 1) return;
                    g.setDelay(g.getDelay() - 1);
                    properties.setDelay((int) g.getDelay());
                    break;
                case 23:
                    g.setDelay(g.getDelay() + 1);
                    properties.setDelay((int) g.getDelay());
                    break;
                case 30:
                    if (g.getSpawnLimit() == 1) return;
                    g.setSpawnLimit(g.getSpawnLimit() - 1);
                    properties.setSpawnLimit(g.getSpawnLimit());
                    break;
                case 32:
                    g.setSpawnLimit(g.getSpawnLimit() + 1);
                    properties.setSpawnLimit(g.getSpawnLimit());
                    break;
            }
            new GeneratorSettingsMenu(p, gen);
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
