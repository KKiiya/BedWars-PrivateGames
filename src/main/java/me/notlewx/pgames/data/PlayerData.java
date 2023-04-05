package me.notlewx.pgames.data;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.events.PrivateGameDisableEvent;
import me.notlewx.pgames.api.events.PrivateGameEnableEvent;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.api.interfaces.Party;
import me.notlewx.pgames.data.database.MySQL;
import me.notlewx.pgames.data.database.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerData implements IPlayerData {
    private final MySQL mySQL = new MySQL();
    private final SQLite sqLite = new SQLite();

    @Override
    public boolean isPrivateGameEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "privateGameEnabled"));
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "privateGameEnabled"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "privateGameEnabled"));
            }
        }
        return false;
    }
    public boolean isPlayerInParty(Player player) {
        Party party = PGamesAPI.getPartyUtil();
        return party.hasParty(player);
    }
    @Override
    public boolean isOHOKEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "oneHitOneKill"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "oneHitOneKill"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "oneHitOneKill"));
            }
        }
        return false;
    }
    @Override
    public boolean isLGEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "lowGravity"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "lowGravity"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "lowGravity"));
            }
        }
        return false;
    }
    @Override
    public boolean isBIBEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "bedInstaBreak"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "bedInstaBreak"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "bedInstaBreak"));
            }
        }
        return false;
    }
    @Override
    public boolean isMTUEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "maxTeamUpgrades"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "maxTeamUpgrades"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "maxTeamUpgrades"));
            }
        }
        return false;
    }
    @Override
    public boolean isAMBEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "allowMapBreak"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "allowMapBreak"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "allowMapBreak"));
            }
        }
        return false;
    }
    @Override
    public boolean isNDEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "noDiamonds"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "noDiamonds"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "noDiamonds"));
            }
        }
        return false;
    }
    @Override
    public boolean isNEEnabled(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "noEmeralds"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Boolean.parseBoolean(mySQL.getData(path, "noEmeralds"));
            } else {
                return Boolean.parseBoolean(sqLite.getData(path, "noEmeralds"));
            }
        }
        return false;
    }
    @Override
    public boolean setPrivateGameEnabled(Player player) {
        String path = player.getUniqueId().toString();
        PrivateGameEnableEvent event = new PrivateGameEnableEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "privateGameEnabled", "true");
                return true;
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
               mySQL.setData(path, "privateGameEnabled", "true");
            } else {
                sqLite.setData(path, "privateGameEnabled", "true");
            }
            return true;
        }
        return false;
    }
    @Override
    public boolean setPrivateGameDisabled(Player player) {
        String path = player.getUniqueId().toString();
        PrivateGameDisableEvent event = new PrivateGameDisableEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "privateGameEnabled", "false");
                return true;
            }
        }
        else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "privateGameEnabled", "false");
            } else {
                sqLite.setData(path, "privateGameEnabled", "false");
            }
            return true;
        }
        return false;
    }
    @Override
    public int getRETLevel(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "respawnEventTime"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "respawnEventTime"));
            } else {
                return Integer.parseInt(sqLite.getData(path, "respawnEventTime"));
            }
        }
        return 0;
    }
    @Override
    public int getHBLevel(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "healthBuffLevel"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "healthBuffLevel"));
            } else {
                return Integer.parseInt(sqLite.getData(path, "healthBuffLevel"));
            }
        }
        return 0;
    }
    @Override
    public int getETLevel(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "eventsTime"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "eventsTime"));
            } else {
                return Integer.parseInt(sqLite.getData(path, "eventsTime"));
            }
        }
        return 0;
    }
    @Override
    public int getSpeedLevel(Player player) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "speed"));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                return Integer.parseInt(mySQL.getData(path, "speed"));
            } else {
                return Integer.parseInt(sqLite.getData(path, "speed"));
            }
        }
        return 0;
    }
    @Override
    public void setOHOKEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "oneHitOneKill", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "oneHitOneKill", String.valueOf(value));
            } else {
                sqLite.setData(path, "oneHitOneKill", String.valueOf(value));
            }
        }
    }
    @Override
    public void setLGEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "lowGravity", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "lowGravity", String.valueOf(value));
            } else {
                sqLite.setData(path, "lowGravity", String.valueOf(value));
            }
        }
    }
    @Override
    public void setBIBEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "bedInstaBreak", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "bedInstaBreak", String.valueOf(value));
            } else {
                sqLite.setData(path, "bedInstaBreak", String.valueOf(value));
            }
        }
    }
    @Override
    public void setMTUEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "maxTeamUpgrades", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "maxTeamUpgrades", String.valueOf(value));
            } else {
                sqLite.setData(path, "maxTeamUpgrades", String.valueOf(value));
            }
        }
    }
    @Override
    public void setAMBEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "allowMapBreak", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "allowMapBreak", String.valueOf(value));
            } else {
                sqLite.setData(path, "allowMapBreak", String.valueOf(value));
            }
        }
    }
    @Override
    public void setNDEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "noDiamonds", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "noDiamonds", String.valueOf(value));
            } else {
                sqLite.setData(path, "noDiamonds", String.valueOf(value));
            }
        }
    }
    @Override
    public void setNEEnabled(Player player, boolean value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "noEmeralds", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "noEmeralds", String.valueOf(value));
            } else {
                sqLite.setData(path, "noEmeralds", String.valueOf(value));
            }
        }
    }
    @Override
    public void setRETLevel(Player player, int value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "respawnEventTime", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "respawnEventTime", String.valueOf(value));
            } else {
                sqLite.setData(path, "respawnEventTime", String.valueOf(value));
            }
        }
    }
    @Override
    public void setHBLevel(Player player, int value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "healthBuffLevel", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "healthBuffLevel", String.valueOf(value));
            } else {
                sqLite.setData(path, "healthBuffLevel", String.valueOf(value));
            }
        }
    }
    @Override
    public void setETLevel(Player player, int value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "eventsTime", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "eventsTime", String.valueOf(value));
            } else {
                sqLite.setData(path, "eventsTime", String.valueOf(value));
            }
        }
    }
    @Override
    public void setSpeedLevel(Player player, int value) {
        String path = player.getUniqueId().toString();
        if (PrivateGames.isBwproxy()) {
            if (PrivateGames.isProxyDatabaseEnabled()) {
                mySQL.setData(path, "speed", String.valueOf(value));
            }
        } else {
            if (PrivateGames.isDatabaseEnabled()) {
                mySQL.setData(path, "speed", String.valueOf(value));
            } else {
                sqLite.setData(path, "speed", String.valueOf(value));
            }
        }
    }
}