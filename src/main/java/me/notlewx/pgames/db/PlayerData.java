package me.notlewx.pgames.db;

import me.notlewx.pgames.api.interfaces.IPlayerData;
import org.bukkit.entity.Player;

public class PlayerData implements IPlayerData {
    MySQL mySQL = new MySQL();
    SQLite sqLite = new SQLite();
    @Override
    public Player getPlayer(Player player) {
        return player;
    }
    public String isPlayerInParty(Player player) {
        String path = player.getUniqueId().toString();

    }
}
