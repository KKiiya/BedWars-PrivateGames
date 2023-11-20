package me.notlewx.privategames.menus.submenus.generators;

import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import me.notlewx.privategames.api.player.IPrivatePlayer;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.MainConfig.*;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

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
        inv = Bukkit.createInventory(this, 9*4, Utility.getMsg(p, SUBMENU_GENERATOR_OPTIONS_TITLE));
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
        geneMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_OPTIONS_ITEM_LORE).stream().map(s -> s
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
        reduceAmountMeta.setLore(Stream.of("", "&7Reduce the amount of minerals dropped", "&7in this generator", "", "&eClick to decrease!").map(Utility::c).collect(Collectors.toList()));
        reduceAmount.setItemMeta(reduceAmountMeta);

        ItemStack increaseAmount = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseAmountMeta = increaseAmount.getItemMeta();
        increaseAmountMeta.setDisplayName(Utility.c("&aIncrease Amount"));
        increaseAmountMeta.setLore(Stream.of("", "&7Increase the amount of minerals dropped", "&7in this generator", "", "&eClick to increase!").map(Utility::c).collect(Collectors.toList()));
        increaseAmount.setItemMeta(increaseAmountMeta);


        ItemStack reduceDelay = Utility.getSkull("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b");
        ItemMeta reduceDelayMeta = reduceDelay.getItemMeta();
        reduceDelayMeta.setDisplayName(Utility.c("&cReduce Delay"));
        reduceDelayMeta.setLore(Stream.of("", "&7Reduce the delay of", "&7this generator", "", "&eClick to decrease!").map(Utility::c).collect(Collectors.toList()));
        reduceDelay.setItemMeta(reduceDelayMeta);

        ItemStack increaseDelay = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseDelayMeta = increaseDelay.getItemMeta();
        increaseDelayMeta.setDisplayName(Utility.c("&aIncrease Delay"));
        increaseDelayMeta.setLore(Stream.of("", "&7Increase the delay of", "&7this generator", "", "&eClick to increase!").map(Utility::c).collect(Collectors.toList()));
        increaseDelay.setItemMeta(increaseDelayMeta);


        ItemStack reduceSpawnLimit = Utility.getSkull("http://textures.minecraft.net/texture/c3e4b533e4ba2dff7c0fa90f67e8bef36428b6cb06c45262631b0b25db85b");
        ItemMeta reduceSpawnLimitMeta = reduceSpawnLimit.getItemMeta();
        reduceSpawnLimitMeta.setDisplayName(Utility.c("&cReduce"));
        reduceSpawnLimitMeta.setLore(Stream.of("", "&7Reduce the amount minerals stacked", "&7in this generator", "", "&eClick to decrease!").map(Utility::c).collect(Collectors.toList()));
        reduceSpawnLimit.setItemMeta(reduceSpawnLimitMeta);

        ItemStack increaseSpawnLimit = Utility.getSkull("http://textures.minecraft.net/texture/60b55f74681c68283a1c1ce51f1c83b52e2971c91ee34efcb598df3990a7e7");
        ItemMeta increaseSpawnLimitMeta = increaseSpawnLimit.getItemMeta();
        increaseSpawnLimitMeta.setDisplayName(Utility.c("&aIncrease Spawn Limit"));
        increaseSpawnLimitMeta.setLore(Stream.of("", "&7Increase the amount minerals stacked", "&7in this generator", "", "&eClick to increase!").map(Utility::c).collect(Collectors.toList()));
        increaseSpawnLimit.setItemMeta(increaseSpawnLimitMeta);

        Material backMaterial = Material.getMaterial(mainConfig.getString(OPTIONS_GENERATOR_OPTIONS_BACK_MATERIAL));
        ItemStack back;
        if (backMaterial == Material.SKULL_ITEM) {
            back = Utility.getSkull(mainConfig.getString(OPTIONS_GENERATOR_OPTIONS_BACK_HEAD_URL));
        } else {
            back = new ItemStack(backMaterial, 1, (short) mainConfig.getInt(OPTIONS_GENERATOR_OPTIONS_BACK_ID));
        }
        ItemMeta backMeta = back.getItemMeta();

        backMeta.setDisplayName(Utility.getMsg(p, SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME));
        backMeta.setLore(Utility.getList(p, SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE));
        back.setItemMeta(backMeta);




        inv.setItem(13, gene);

        inv.setItem(12, reduceAmount);
        inv.setItem(14, increaseAmount);

        inv.setItem(21, reduceDelay);
        inv.setItem(23, increaseDelay);

        inv.setItem(30, reduceSpawnLimit);
        inv.setItem(32, increaseSpawnLimit);

        inv.setItem(mainConfig.getInt(OPTIONS_GENERATOR_OPTIONS_BACK_POSITION), back);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals(Utility.getMsg(p, SUBMENU_GENERATOR_OPTIONS_TITLE))) return;
        GeneratorProperties.Properties properties;
        IPrivatePlayer pp = api.getPrivatePlayer(p);
        if (support == Support.BEDWARS1058) {
            IGenerator g = (IGenerator) gen;
            if (GeneratorProperties.getGeneratorProperties(pp) == null) {
                GeneratorProperties.setGeneratorProperties(pp, new GeneratorProperties(g));
            }
            properties = GeneratorProperties.getGeneratorProperties(pp).getProperties(g);
            if (e.getSlot() == mainConfig.getInt(OPTIONS_GENERATOR_OPTIONS_BACK_POSITION)) {
                new GeneratorsMenu(p, g.getArena().getArenaName());
                return;
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
                    g.setDelay(g.getDelay()/4 - 1);
                    properties.setDelay(g.getDelay());
                    break;
                case 23:
                    g.setDelay(g.getDelay()/4 + 1);
                    properties.setDelay(g.getDelay());
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
        } else if (support == Support.BEDWARS2023) {
            com.tomkeuper.bedwars.api.arena.generator.IGenerator g = (com.tomkeuper.bedwars.api.arena.generator.IGenerator) gen;
            if (GeneratorProperties.getGeneratorProperties(pp) == null) {
                GeneratorProperties.setGeneratorProperties(pp, new GeneratorProperties(g));
            }
            properties = GeneratorProperties.getGeneratorProperties(pp).getProperties(g);
            if (e.getSlot() == mainConfig.getInt(OPTIONS_GENERATOR_OPTIONS_BACK_POSITION)) {
                new GeneratorsMenu(p, g.getArena().getArenaName());
                return;
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
                    g.setDelay(g.getDelay()/4 -1);
                    properties.setDelay((int) g.getDelay());
                    break;
                case 23:
                    g.setDelay(g.getDelay()/4 + 1);
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
