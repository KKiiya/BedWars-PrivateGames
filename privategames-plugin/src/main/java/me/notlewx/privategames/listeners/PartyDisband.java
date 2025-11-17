package me.notlewx.privategames.listeners;

import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_GAME_DISABLED;

public class PartyDisband implements Listener {

    // Thanks to ClydeSan-2593 (clydsan) for the pull request!
    @EventHandler
    public void onPartyLeave(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();

        IPrivatePlayer pp = api.getPrivatePlayer(p);
        IPlayerSettings playerData = pp.getPlayerSettings();
        IParty party = pp.getPlayerParty();
        String[] args = e.getMessage().split(" ");

        if (args.length > 0 && args[0].equalsIgnoreCase("/party") && args.length > 1 && args[1].equalsIgnoreCase("disband")) {
            if (!playerData.isPrivateGameEnabled()) return;
            if (!party.hasParty()) return;
            if (!party.isOwner()) return;
            playerData.setPrivateGameDisabled(false);
            p.sendMessage(Utility.getMsg(p, PRIVATE_GAME_DISABLED));
        }
    }

}
