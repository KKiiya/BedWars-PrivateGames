package me.notlewx.privategames.listeners;

import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.player.PrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_GAME_DISABLED;

public class PartyDisband implements Listener {
    private IPlayerSettings playerData;
    private IParty party;

    @EventHandler
    public void onPartyLeave(PlayerCommandPreprocessEvent command){
        Player player = command.getPlayer();

        playerData = (new PrivatePlayer(player)).getPlayerSettings();
        party = (new PrivatePlayer(player)).getPlayerParty();
        String[] args = command.getMessage().split(" ");

        if (args.length > 0 && args[0].equalsIgnoreCase("/party") && args.length > 1 && args[1].equalsIgnoreCase("disband")) {
            if (playerData.isPrivateGameEnabled()) {
                if (party.hasParty()) {
                    if (party.isOwner()) {
                        playerData.setPrivateGameDisabled(false);
                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED));
                    }
                }
            }
        }
    }

}
