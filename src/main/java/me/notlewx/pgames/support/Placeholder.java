package me.notlewx.pgames.support;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.notlewx.pgames.PrivateGames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {
    PrivateGames privateGames;
    public Placeholder(PrivateGames plugin) {
        this.privateGames = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "PrivateGames";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Kiiya";
    }

    @Override
    public @NotNull String getVersion() {
        return PrivateGames.getPlugins().getDescription().getVersion();
    }
    @Override
    public String getRequiredPlugin() {
        return "TurboKartRacers";
    }
    @Override
    public boolean canRegister() {
        return (privateGames = (PrivateGames) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }
    @Override
    public boolean persist() {
        return true;
    }
    public String onPlaceholderRequest(Player player, String placeholder) {
        switch (placeholder.toLowerCase()) {
            default:
                return null;
        }
    }
}
