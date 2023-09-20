package me.notlewx.privategames.party;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.party.handlers.BedWars1058PARTY;
import me.notlewx.privategames.party.handlers.BedWars2023PARTY;
import me.notlewx.privategames.party.handlers.BedWarsProxyPARTY;
import org.bukkit.entity.Player;

public class Party {

    public static IParty getPartyPlayer(Player player) {
        IParty partyProvider = null;
        switch (PrivateGames.support) {
            case BEDWARS1058:
                partyProvider = new BedWars1058PARTY(player);
                break;
            case BEDWARS2023:
                partyProvider = new BedWars2023PARTY(player);
                break;
            case BEDWARSPROXY:
                partyProvider = new BedWarsProxyPARTY(player);
                break;
        }
        return partyProvider;
    }

}
