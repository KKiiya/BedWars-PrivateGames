package me.notlewx.pgames.data;

import me.notlewx.pgames.api.PGamesAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class Party {
    public List<Player> getPartyMembers(Player player) {
        return PGamesAPI.getBwApi().getPartyUtil().getMembers(player);
    }
    public boolean isPartyOwner(Player player) {
        return PGamesAPI.getBwApi().getPartyUtil().isOwner(player);
    }
    public boolean isPartyMember(Player owner, Player checking) {
        return PGamesAPI.getBwApi().getPartyUtil().isMember(owner, checking);
    }
    public boolean hasParty(Player player) {
        return PGamesAPI.getBwApi().getPartyUtil().hasParty(player);
    }
}
