package me.notlewx.pgames.api.interfaces;

import org.bukkit.entity.Player;

public interface IPrivateSettings {
    boolean isPrivateGameEnabled(Player player);
    boolean isPlayerInParty(Player player);
    boolean isOHOKEnabled(Player player);
    boolean isLGEnabled(Player player);
    boolean isBIBEnabled(Player player);
    boolean isMTUEnabled(Player player);
    boolean isAMBEnabled(Player player);
    boolean isNDEnabled(Player player);
    boolean isNEEnabled(Player player);
    boolean setPrivateGameEnabled(Player player);
    boolean setPrivateGameDisabled(Player player, boolean leaving);
    int getRETLevel(Player player);
    int getHBLevel(Player player);
    int getETLevel(Player player);
    int getSpeedLevel(Player player);
    void setOHOKEnabled(Player player, boolean value);
    void setLGEnabled(Player player, boolean value);
    void setBIBEnabled(Player player, boolean value);
    void setMTUEnabled(Player player, boolean value);
    void setAMBEnabled(Player player, boolean value);
    void setNDEnabled(Player player, boolean value);
    void setNEEnabled(Player player, boolean value);
    void setRETLevel(Player player, int value) throws IllegalArgumentException;
    void setHBLevel(Player player, int value) throws IllegalArgumentException;
    void setETLevel(Player player, int value) throws IllegalArgumentException;
    void setSpeedLevel(Player player, int value) throws IllegalArgumentException;
}
