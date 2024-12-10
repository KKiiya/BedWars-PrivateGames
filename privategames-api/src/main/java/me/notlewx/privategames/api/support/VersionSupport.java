package me.notlewx.privategames.api.support;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class VersionSupport {

    public abstract ItemStack setItemTag(ItemStack item, String key, String value);

    public abstract String getItemTag(ItemStack item, String key);

    public abstract Material getSkullMaterial();

    public abstract boolean isPlayerHead(ItemStack item);
}
