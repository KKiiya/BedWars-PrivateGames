package me.notlewx.pgames.support;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.notlewx.pgames.PrivateGames;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {
    PrivateGames privateGames;

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
        return Tu;
    }
}
