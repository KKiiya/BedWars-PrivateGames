package me.notlewx.pgames.commands;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.data.Party;
import me.notlewx.pgames.data.PlayerData;
import me.notlewx.pgames.menu.SettingsMenu;
import me.notlewx.pgames.util.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;
import static me.notlewx.pgames.config.MessagesData.*;

public class MainCommand implements CommandExecutor {
    private final PlayerData playerData = new PlayerData();
    private final Party party = new Party();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        command.setAliases(Arrays.asList("privategame", "pgame"));
        if (sender instanceof Player) {
            if (args.length < 1) {
                sender.sendMessage(Utility.colorizedString("&cNot enough args"));
            }
            else {
                if (args.length > 1) {
                    switch (args[0].toLowerCase()) {
                        case "enable":
                            if (playerData.isPlayerInParty((Player) sender)) {
                                if (party.isPartyOwner((Player) sender)) {
                                    playerData.setPrivateGameEnabled((Player) sender);
                                    for (Player player : party.getPartyMembers((Player) sender)) {
                                        if (party.isPartyOwner(player)) player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED));
                                        player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED_OTHERS)
                                                .replace("{player}", ((Player) sender).getDisplayName())
                                        );
                                    }
                                } else {
                                    sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                }
                            } else {
                                sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                            }
                        break;
                        case "disable":
                            if (playerData.isPlayerInParty((Player) sender)) {
                                if (party.isPartyOwner((Player) sender)) {
                                    playerData.setPrivateGameDisabled((Player) sender);
                                } else {
                                    sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                }
                            } else {
                                sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                            }
                        break;
                        case "help":
                        break;
                    }
                    if (PrivateGames.isBwproxy()) {
                        if (args[0].equalsIgnoreCase("gui")) {
                            new SettingsMenu((Player) sender);
                        }
                    }
                } else {
                    sender.sendMessage(Utility.colorizedString("&cNot enough args!"));
                }
            }
        } else {
            sender.sendMessage(Utility.colorizedString("&cYou must join the server in order to use this command"));
        }
        return false;
    }
}
