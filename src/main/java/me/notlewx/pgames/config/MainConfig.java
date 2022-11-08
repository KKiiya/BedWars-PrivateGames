package me.notlewx.pgames.config;

import com.andrei1058.bedwars.api.configuration.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MainConfig extends ConfigManager {
    private static YamlConfiguration yml;
    public MainConfig(Plugin plugin, String name, String dir) {
        super(plugin, name, dir);
        YamlConfiguration yml = getYml();
        yml.options().header("BedWars1058 PrivateGames Addon by NotLew_x#9207");
        if (Material.getMaterial(yml.getString(MATERIAL)) == null) {
            yml.addDefault(MATERIAL, "REDSTONE_BLOCK");
        }
        else {
            yml.addDefault(MATERIAL, Material.getMaterial(yml.getString(MATERIAL)));
        }
        yml.addDefault(POSITION, 0);
        yml.addDefault(ENCHANTED, true);
    }
    public static final String
            MATERIAL = "private-games-item.material",
            POSITION = "private-games-item.position",
            ENCHANTED = "private-games-item.enchanted";
}
