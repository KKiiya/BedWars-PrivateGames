package me.notlewx.privategames.party.handlers;

import com.tomkeuper.bedwars.api.party.Party;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.party.IParty;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BedWars2023PARTY implements IParty {
    private final Party partyUtil = PrivateGames.getBw2023Api().getPartyUtil();
    private final OfflinePlayer player;

    public BedWars2023PARTY(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public Player getOwner() {
        return partyUtil.getOwner((Player) player);
    }

    @Override
    public List<OfflinePlayer> getPartyMembers() {
        return partyUtil.getMembers((Player) player).stream().map(p -> (OfflinePlayer) p).collect(Collectors.toList());
    }

    @Override
    public boolean isOwner() {
        return partyUtil.isOwner((Player) player);
    }

    @Override
    public boolean hasMember(OfflinePlayer checking) {
        return partyUtil.isMember((Player) player, (Player) checking);
    }

    @Override
    public boolean hasParty() {
        return partyUtil.hasParty((Player) player);
    }

    @Override
    public void addPlayer(Player addedPlayer) {
        partyUtil.addMember((Player) player, addedPlayer);
    }

    @Override
    public int partySize() {
        return partyUtil.partySize((Player) player);
    }
}
