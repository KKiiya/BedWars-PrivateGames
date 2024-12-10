package me.notlewx.privategames.support.version;

import com.saicone.rtag.RtagItem;
import me.notlewx.privategames.api.support.VersionSupport;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class v1_21_R1 extends VersionSupport {

    @Override
    public ItemStack setItemTag(ItemStack item, String key, String value) {
        return RtagItem.edit(item, tag -> {
            tag.set(value, key);
        });
    }

    @Override
    public String getItemTag(ItemStack item, String key) {
        RtagItem tag = new RtagItem(item);
        return tag.hasTag(key) ? tag.get(key) : null;
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
