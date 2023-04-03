package me.notlewx.pgames.api.interfaces;

import org.bukkit.entity.Player;

public interface IPlayerData {
    boolean isPrivateGameEnabled(Player player);
    boolean isPlayerInParty(Player player);
    boolean OHOKisEnabled(Player player);
    boolean LGisEnabled(Player player);
    boolean BIBisEnabled(Player player);
    boolean MTUisEnabled(Player player);
    boolean AMBisEnabled(Player player);
    boolean NDisEnabled(Player player);
    boolean NEisEnabled(Player player);
    boolean setPrivateGameEnabled(Player player);
    boolean setPrivateGameDisabled(Player player);
    int getRETLevel();
    int getHBLevel();
    int getETLevel();
    int getSpeedLevel();
    void setOHOKEnabled(Player player, boolean value);
    void setLGEnabled(Player player, boolean value);
    void setBIBEnabled(Player player, boolean value);
    void setMTUEnabled(Player player, boolean value);
    void setAMBEnabled(Player player, boolean value);
    void setNDEnabled(Player player, boolean value);
    void setNEEnabled(Player player, boolean value);
    void setRETLevel(Player player, int value);
    void setHBLevel(Player player, int value);
    void setETLevel(Player player, int value);
    void setSpeedLevel(Player player, int value);
}
