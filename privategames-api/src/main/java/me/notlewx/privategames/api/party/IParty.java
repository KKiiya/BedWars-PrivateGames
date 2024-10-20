package me.notlewx.privategames.api.party;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface IParty {
    /**
     * Get the owner of the party
     * @return - Player
     */
    OfflinePlayer getOwner();

    /**
     * Get the party members
     * @return - List of players
     */
    List<OfflinePlayer> getPartyMembers();

    /**
     * Check if the player is the owner
     * of the party
     * @return - boolean
     */
    boolean isOwner();

    /**
     * check if the party of the player
     * contains a specific member
     * @param checking - player that will be checked
     * @return - Boolean
     */
    boolean hasMember(OfflinePlayer checking);

    /**
     * Check if the player has a party
     * @return - boolean
     */
    boolean hasParty();

    /**
     * Add a player to the party
     * @param added - Player that will be added
     */
    void addPlayer(Player added);

    /**
     * Get the party size
     * @return - int
     */
    int partySize();

}

