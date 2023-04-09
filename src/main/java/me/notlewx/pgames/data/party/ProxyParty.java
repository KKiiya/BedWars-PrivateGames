package me.notlewx.pgames.data.party;

import com.andrei1058.bedwars.proxy.party.Internal;
import me.notlewx.pgames.api.interfaces.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ProxyParty implements Party {
    private final Internal internal = new Internal();
    @Override
    public List<Player> getPartyMembers(Player player) {
        return internal.getMembers(player.getUniqueId()).stream().map(Bukkit::getPlayer).collect(Collectors.toList());
    }
    @Override
    public boolean hasParty(Player player) {
        return internal.hasParty(player.getUniqueId());
    }
    @Override
    public boolean isPartyOwner(Player partyPlayer) {
        return internal.isOwner(partyPlayer.getUniqueId());
    }
    @Override
    public boolean isPartyMember(Player partyPlayer, Player checking) {
        return internal.isMember(partyPlayer.getUniqueId(), checking.getUniqueId());
    }
    @Override
    public void addPlayer(Player partyPlayer, Player added) {
        internal.addMember(partyPlayer.getUniqueId(), added);
    }
    @Override
    public int partySize(Player player) {
        return internal.partySize(player.getUniqueId());
    }
}
