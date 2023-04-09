package me.notlewx.pgames.api.interfaces;

import org.bukkit.entity.Player;
import java.util.List;

public interface Party {
    List<Player> getPartyMembers(Player player);
    boolean hasParty(Player player);
    boolean isPartyOwner(Player partyPlayer);
    boolean isPartyMember(Player partyPlayer, Player checking);
    void addPlayer(Player partyLeader, Player added);
    int partySize(Player player);

}
