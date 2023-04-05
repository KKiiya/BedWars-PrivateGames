package me.notlewx.pgames.data;

import com.andrei1058.bedwars.api.BedWars;
import me.notlewx.pgames.api.PGamesAPI;
import org.bukkit.entity.Player;

import java.util.List;

public class Party implements me.notlewx.pgames.api.interfaces.Party {
    private static final BedWars api = PGamesAPI.getBwApi();
    @Override
    public List<Player> getPartyMembers(Player player) {
        return api.getPartyUtil().getMembers(player);
    }
    @Override
    public boolean isPartyOwner(Player player) {
        return PGamesAPI.getBwApi().getPartyUtil().isOwner(player);
    }
    @Override
    public boolean isPartyMember(Player owner, Player checking) {
        return api.getPartyUtil().isMember(owner, checking);
    }
    @Override
    public boolean hasParty(Player player) {
        return api.getPartyUtil().hasParty(player);
    }
    @Override
    public void addPlayer(Player partyPlayer, Player added) {
        api.getPartyUtil().addMember(partyPlayer, added);
    }
    @Override
    public int partySize(Player player) {
        return api.getPartyUtil().partySize(player);
    }
}
