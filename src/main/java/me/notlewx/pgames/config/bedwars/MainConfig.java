package me.notlewx.pgames.config.bedwars;

import com.andrei1058.bedwars.api.configuration.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MainConfig extends ConfigManager {
    public MainConfig(Plugin plugin, String name, String dir) {
        super(plugin, name, dir);
        YamlConfiguration yml = getYml();
        yml.options().header("BedWars1058 PrivateGames Addon by Kiiya#9207");
        yml.addDefault(MATERIAL, "REDSTONE_BLOCK");
        yml.addDefault(POSITION, 0);
        yml.addDefault(ENCHANTED, false);
        yml.options().copyDefaults(true);
        save();
    }
    public static final String
            MATERIAL = "private-games-item.material",
            POSITION = "private-games-item.position",
            ENCHANTED = "private-games-item.enchanted";
}
