package me.notlewx.privategames.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CustomItemStack {
    private final static LinkedHashMap<ItemStack, String> customId = new LinkedHashMap<>();
    private final ItemStack item;
    private final ItemMeta meta;
    public CustomItemStack(ItemStack itemStack) {
        this.item = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public void setCustomID(String ID) {
        customId.put(item, ID);
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
        return customId.get(item);
    }
}
