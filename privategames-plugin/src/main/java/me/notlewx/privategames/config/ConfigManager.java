package me.notlewx.privategames.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class ConfigManager {

    private YamlConfiguration yml;
    private File config;
    private String name;
    private boolean firstTime = false;

    /**
     * Create a new configuration file.
     *
     * @param plugin config owner.
     * @param name   config name. Do not include .yml in it.
     */
    public ConfigManager(Plugin plugin, String name, String dir) {
        File d = new File(dir);
        if (!d.exists()) {
            if (!d.mkdirs()) {
                plugin.getLogger().log(Level.SEVERE, "Could not create " + d.getPath());
                return;
            }
        }

        config = new File(dir, name + ".yml");
        if (!config.exists()) {
            firstTime = true;
            plugin.getLogger().log(Level.INFO, "Creating " + config.getPath());
            try {
                if (!config.createNewFile()) {
                    plugin.getLogger().log(Level.SEVERE, "Could not create " + config.getPath());
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        yml = YamlConfiguration.loadConfiguration(config);
        yml.options().copyDefaults(true);
        this.name = name;
    }

    /**
     * Reload configuration.
     */
    public void reload() {
        yml = YamlConfiguration.loadConfiguration(config);
    }


    /**
     * Set and save data to config
     */
    public void set(String path, Object value) {
        yml.set(path, value);
        save();
    }

    /**
     * Get yml instance
     */
    public YamlConfiguration getYml() {
        return yml;
    }

    /**
     * Save config changes to file
     */
    public void save() {
        try {
            yml.save(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get list of strings at given path
     *
     * @return a list of string with colors translated
     */
    public List<String> getList(String path) {
        return yml.getStringList(path).stream().map(s -> s.replace("&", "§")).collect(Collectors.toList());
    }

    /**
     * Get boolean at given path
     */
    public boolean getBoolean(String path) {
        return yml.getBoolean(path);
    }

    /**
     * Get Integer at given path
     */
    public int getInt(String path) {
        return yml.getInt(path);
    }

    /**
     * Get Double at given path
     */
    public double getDouble(String path) {
        return yml.getDouble(path);
    }


    /**
     * Get string at given path
     */
    public String getString(String path) {
        return yml.getString(path);
    }

    /**
     * Check if the config file was created for the first time
     * Can be used to add default values
     */
    public boolean isFirstTime() {
        return firstTime;
    }

    /**
     * Get config name
     */
    public String getName() {
        return name;
    }

    /**
     * Change internal name.
     */
    public void setName(String name) {
        this.name = name;
    }
}

