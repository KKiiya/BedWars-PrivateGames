package me.notlewx.privategames.party.handlers;

import com.andrei1058.bedwars.proxy.BedWarsProxy;
import com.andrei1058.bedwars.proxy.party.Party;
import me.notlewx.privategames.api.party.IParty;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BedWarsProxyPARTY implements IParty {
    private final Party partyUtil = BedWarsProxy.getParty();
    private final OfflinePlayer player;

    public BedWarsProxyPARTY(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public Player getOwner() {
        return Bukkit.getPlayer(partyUtil.getOwner(player.getUniqueId()));
    }

    @Override
    public List<OfflinePlayer> getPartyMembers() {
        return partyUtil.getMembers(player.getUniqueId()).stream().map(Bukkit::getPlayer).collect(Collectors.toList());
    }

    @Override
    public boolean isOwner() {
        return partyUtil.isOwner(player.getUniqueId());
    }

    @Override
    public boolean hasMember(OfflinePlayer checking) {
        return partyUtil.isMember(player.getUniqueId(), checking.getUniqueId());
    }

    @Override
    public boolean hasParty() {
        return partyUtil.hasParty(player.getUniqueId());
    }

    @Override
    public void addPlayer(Player addedPlayer) {
        partyUtil.addMember(player.getUniqueId(), addedPlayer);
    }

    @Override
    public int partySize() {
        return partyUtil.partySize(player.getUniqueId());
    }
}
