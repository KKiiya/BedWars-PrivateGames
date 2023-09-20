package me.notlewx.privategames.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CustomItemStack {
    private final ItemStack item;
    private final ItemMeta meta;
    private static HashMap<ItemStack, String> customItemID;
    public CustomItemStack(ItemStack itemStack) {
        if (customItemID == null) customItemID = new HashMap<>();
        this.item = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public void setCustomID(String customID) {
        customItemID.put(item, customID);
    }
    public ItemStack getItem() {
        return item;
    }
    public ItemMeta getItemMeta() {
        return meta;
    }
    public void setItemMeta(ItemMeta meta) {
        item.setItemMeta(meta);
    }
    public void addUnsafeEnchantment(Enchantment ench, int level) {
        item.addUnsafeEnchantment(ench, level);
    }
    public void removeEnchantment(Enchantment ench) {
        item.removeEnchantment(ench);
    }
    public static String getItemID(ItemStack item) {
        return customItemID.get(item);
    }
}
