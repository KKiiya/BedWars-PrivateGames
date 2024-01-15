package me.notlewx.privategames.party.handlers;

import com.tomkeuper.bedwars.api.party.Party;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.party.IParty;
import org.bukkit.entity.Player;

import java.util.List;

public class BedWars2023PARTY implements IParty {
    private final Party partyUtil = PrivateGames.getBw2023Api().getPartyUtil();
    private final Player player;
    public BedWars2023PARTY(Player player) {
        this.player = player;
    }

    @Override
    public Player getOwner() {
        return partyUtil.getOwner(player);
    }

    @Override
    public List<Player> getPartyMembers() {
        return partyUtil.getMembers(player);
    }

    @Override
    public boolean isOwner() {
        return partyUtil.isOwner(player);
    }

    @Override
    public boolean hasMember(Player checking) {
        return partyUtil.isMember(player, checking);
    }

    @Override
    public boolean hasParty() {
        return partyUtil.hasParty(player);
    }

    @Override
    public void addPlayer(Player addedPlayer) {
        partyUtil.addMember(player, addedPlayer);
    }

    @Override
    public int partySize() {
        return partyUtil.partySize(player);
    }
}
