package me.notlewx.privategames.party;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.party.handlers.BedWars1058PARTY;
import me.notlewx.privategames.party.handlers.BedWars2023PARTY;
import me.notlewx.privategames.party.handlers.BedWarsProxy2023PARTY;
import me.notlewx.privategames.party.handlers.BedWarsProxyPARTY;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Party {

    public static IParty getPartyPlayer(UUID player) {
        IParty partyProvider = null;
        switch (PrivateGames.support) {
            case BEDWARS1058:
                partyProvider = new BedWars1058PARTY(Bukkit.getOfflinePlayer(player));
                break;
            case BEDWARS2023:
                partyProvider = new BedWars2023PARTY(Bukkit.getOfflinePlayer(player));
                break;
            case BEDWARSPROXY:
                partyProvider = new BedWarsProxyPARTY(Bukkit.getOfflinePlayer(player));
                break;
            case BEDWARSPROXY2023:
                partyProvider = new BedWarsProxy2023PARTY(Bukkit.getOfflinePlayer(player));
                break;
        }
        return partyProvider;
    }

}
