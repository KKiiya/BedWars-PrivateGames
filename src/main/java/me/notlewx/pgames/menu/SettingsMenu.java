package me.notlewx.pgames.menu;

import com.andrei1058.bedwars.api.language.Language;
import me.notlewx.pgames.PrivateGames;
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
        inventory = Bukkit.createInventory(null, 54);

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

        if (PrivateGames.isBwproxy()) {
            dswordMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            dswordMeta.setLore();

            gappleMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            gappleMeta.setLore(com.andrei1058.bedwars.proxy.language.Language.getList());

            quartzMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            quartzMeta.setLore();

            rfootMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            rfootMeta.setLore();

            ichestplateMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            ichestplateMeta.setLore();

            bowMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            bowMeta.setLore();

            iswordMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            iswordMeta.setLore();

            deggMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            deggMeta.setLore();

            sballMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            sballMeta.setLore();

            eblockMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            eblockMeta.setLore();

            epearlMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            epearlMeta.setLore();

            clockMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            clockMeta.setLore();

            sswordMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            sswordMeta.setLore();

            arrowMeta.setDisplayName(com.andrei1058.bedwars.proxy.language.Language.getMsg(player, ));
            arrowMeta.setLore();
        }
        else {
            dswordMeta.setDisplayName(Language.getMsg(player, ));
            dswordMeta.setLore(Language.getList(player, ));

            gappleMeta.setDisplayName(Language.getMsg(player, ));
            gappleMeta.setLore(Language.getList(player, ));

            quartzMeta.setDisplayName(Language.getMsg(player, ));
            quartzMeta.setLore(Language.getList(player, ));

            rfootMeta.setDisplayName(Language.getMsg(player, ));
            rfootMeta.setLore(Language.getList(player, ));

            ichestplateMeta.setDisplayName(Language.getMsg(player, ));
            ichestplateMeta.setLore(Language.getList(player, ));

            bowMeta.setDisplayName(Language.getMsg(player, ));
            bowMeta.setLore(Language.getList(player, ));

            iswordMeta.setDisplayName(Language.getMsg(player, ));
            iswordMeta.setLore(Language.getList(player, ));

            deggMeta.setDisplayName(Language.getMsg(player, ));
            deggMeta.setLore(Language.getList(player, ));

            sballMeta.setDisplayName(Language.getMsg(player, ));
            sballMeta.setLore(Language.getList(player, ));

            eblockMeta.setDisplayName(Language.getMsg(player, ));
            eblockMeta.setLore(Language.getList(player, ));

            epearlMeta.setDisplayName(Language.getMsg(player, ));
            epearlMeta.setLore(Language.getList(player, ));

            clockMeta.setDisplayName(Language.getMsg(player, ));
            clockMeta.setLore(Language.getList(player, ));

            sswordMeta.setDisplayName(Language.getMsg(player, ));
            sswordMeta.setLore(Language.getList(player, ));

            arrowMeta.setDisplayName(Language.getMsg(player, ));
            arrowMeta.setLore(Language.getList(player, ));
        }
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
