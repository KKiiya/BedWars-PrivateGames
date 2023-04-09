package me.notlewx.pgames.config.proxy;

import me.notlewx.pgames.api.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ProxyConfig extends ConfigManager {
    public ProxyConfig(Plugin plugin, String name) {
        super(plugin, name, Bukkit.getWorldContainer().getPath() + "/plugins/BedWarsProxy/Addons/PrivateGames/");
        YamlConfiguration yml = getYml();
        yml.options().header("BedWars1058 PrivateGames Addon by Kiiya#9207");
        yml.options().copyDefaults(true);
        save();
    }
}
