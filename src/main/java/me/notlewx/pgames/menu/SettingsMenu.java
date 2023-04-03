package me.notlewx.pgames.menu;

import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsMenu {
    private static Inventory inventory;
    public SettingsMenu(Player player) {
        openMenu(player);
    }
    public void openMenu(Player player) {
        inventory = Bukkit.createInventory(null, 54, Utility.getMSGLang(player, ));

        ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dswordMeta = dsword.getItemMeta();
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta gappleMeta = gapple.getItemMeta();
        ItemStack quartz = new ItemStack(Material.QUARTZ);
        ItemMeta quartzMeta = quartz.getItemMeta();
        ItemStack rfoot = new ItemStack(Material.RABBIT_FOOT);
        ItemMeta rfootMeta = rfoot.getItemMeta();
        ItemStack ichestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ichestplateMeta = ichestplate.getItemMeta();
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        ItemStack isword = new ItemStack(Material.IRON_SWORD);
        ItemMeta iswordMeta = bow.getItemMeta();
        ItemStack degg = new ItemStack(Material.DRAGON_EGG);
        ItemMeta deggMeta = degg.getItemMeta();
        ItemStack sball = new ItemStack(Material.SLIME_BALL);
        ItemMeta sballMeta = sball.getItemMeta();
        ItemStack eblock = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta eblockMeta = eblock.getItemMeta();
        ItemStack epearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta epearlMeta = epearl.getItemMeta();
        ItemStack clock = new ItemStack(Material.WATCH);
        ItemMeta clockMeta = clock.getItemMeta();
        ItemStack ssword = new ItemStack(Material.STONE_SWORD);
        ItemMeta sswordMeta = ssword.getItemMeta();
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();

            dswordMeta.setDisplayName(Utility.getMSGLang(player, ));
            dswordMeta.setLore(Utility.getListLang(player, ));

            gappleMeta.setDisplayName(Utility.getMSGLang(player, ));
            gappleMeta.setLore(Utility.getListLang(player, ));

            quartzMeta.setDisplayName(Utility.getMSGLang(player, ));
            quartzMeta.setLore(Utility.getListLang(player, ));

            rfootMeta.setDisplayName(Utility.getMSGLang(player, ));
            rfootMeta.setLore(Utility.getListLang(player, ));

            ichestplateMeta.setDisplayName(Utility.getMSGLang(player, ));
            ichestplateMeta.setLore(Utility.getListLang(player, ));

            bowMeta.setDisplayName(Utility.getMSGLang(player, ));
            bowMeta.setLore(Utility.getListLang(player, ));

            iswordMeta.setDisplayName(Utility.getMSGLang(player, ));
            iswordMeta.setLore(Utility.getListLang(player, ));

            deggMeta.setDisplayName(Utility.getMSGLang(player, ));
            deggMeta.setLore(Utility.getListLang(player, ));

            sballMeta.setDisplayName(Utility.getMSGLang(player, ));
            sballMeta.setLore(Utility.getListLang(player, ));

            eblockMeta.setDisplayName(Utility.getMSGLang(player, ));
            eblockMeta.setLore(Utility.getListLang(player, ));

            epearlMeta.setDisplayName(Utility.getMSGLang(player, ));
            epearlMeta.setLore(Utility.getListLang(player, ));

            clockMeta.setDisplayName(Utility.getMSGLang(player, ));
            clockMeta.setLore(Utility.getListLang(player, ));

            sswordMeta.setDisplayName(Utility.getMSGLang(player, ));
            sswordMeta.setLore(Utility.getListLang(player, ));

            arrowMeta.setDisplayName(Utility.getMSGLang(player, ));
            arrowMeta.setLore(Utility.getListLang(player, ));

        dsword.setItemMeta(dswordMeta);
        gapple.setItemMeta(gappleMeta);
        quartz.setItemMeta(quartzMeta);
        rfoot.setItemMeta(rfootMeta);
        ichestplate.setItemMeta(ichestplateMeta);
        bow.setItemMeta(bowMeta);
        isword.setItemMeta(iswordMeta);
        degg.setItemMeta(deggMeta);
        sball.setItemMeta(sballMeta);
        eblock.setItemMeta(eblockMeta);
        epearl.setItemMeta(epearlMeta);
        clock.setItemMeta(clockMeta);
        ssword.setItemMeta(sswordMeta);
        arrow.setItemMeta(arrowMeta);

        inventory.setItem(11, dsword);
        inventory.setItem(13, gapple);
        inventory.setItem(15, quartz);
        inventory.setItem(17, rfoot);
        inventory.setItem(19, ichestplate);
        inventory.setItem(21, bow);
        inventory.setItem(23, isword);
        inventory.setItem(25, degg);
        inventory.setItem(27, sball);
        inventory.setItem(29, eblock);
        inventory.setItem(31, epearl);
        inventory.setItem(33, clock);
        inventory.setItem(35, ssword);
        inventory.setItem(50, arrow);
        player.openInventory(inventory);
    }
    public static void closeMenu(Player player) {
        player.closeInventory();
    }
    public static Inventory getInventory() {
        return inventory;
    }
}
