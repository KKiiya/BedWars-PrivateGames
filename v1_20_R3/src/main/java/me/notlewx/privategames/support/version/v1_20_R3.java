package me.notlewx.privategames.support.version;

import me.notlewx.privategames.api.support.VersionSupport;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class v1_20_R3 extends VersionSupport {

    @Override
    public ItemStack setItemTag(ItemStack item, String key, String value) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.v() == null ? new NBTTagCompound() : nmsItem.v();
        tag.a(key, value);
        nmsItem.c(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public String getItemTag(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsItem.v();
        return tag == null ? null : tag.e(key) ? tag.l(key) : null;
    }

    @Override
    public Material getSkullMaterial() {
        return Material.LEGACY_SKULL_ITEM;
    }

    @Override
    public boolean isPlayerHead(ItemStack item) {
        return item.getType() == Material.LEGACY_SKULL_ITEM && item.getDurability() == 3;
    }
}
