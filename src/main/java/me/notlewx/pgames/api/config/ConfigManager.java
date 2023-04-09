package me.notlewx.pgames.api.config;

import me.notlewx.pgames.util.Utility;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private File file;
    private YamlConfiguration yml;
    private String name;
    public ConfigManager(Plugin plugin, String name, String dir) {
        File d = new File(dir);
        if (!d.exists()) {
            if (!d.mkdirs()) {
                plugin.getLogger().severe("Couldn't create " + d.getPath());
                return;
            }
        }
        file = new File(name + ".yml", dir);
        if (!file.exists()) {
            plugin.getLogger().info(Utility.colorizedString("&eLoading config files..."));
            plugin.getLogger().info("Creating " + file.getPath());
            try {
                if (!file.createNewFile()) {
                    plugin.getLogger().severe("Couldn't create " + file.getPath());
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            plugin.getLogger().info(Utility.colorizedString("&aConfig files generated successfully"));
        }
        yml = YamlConfiguration.loadConfiguration(file);
        yml.options().copyDefaults(true);
        this.name = name;
    }
    public void reload() {
        yml = YamlConfiguration.loadConfiguration(file);
    }
    public YamlConfiguration getYml() {
        return yml;
    }
    public void save() {
        try {
            yml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void set(String path, Object value) {
        yml.set(path, value);
    }
    public String getString(String path) {
        return Utility.colorizedString(yml.getString(path));
    }
    public boolean getBoolean(String path) {
        return yml.getBoolean(path);
    }
    public int getInt(String path) {
        return yml.getInt(path);
    }
    public List<String> getList(String path) {
        return yml.getStringList(path).stream().map(s -> (s.replace("&", "§"))).collect(Collectors.toList());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
