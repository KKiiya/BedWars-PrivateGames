package me.notlewx.pgames.commands;

import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IPlayerData;
import me.notlewx.pgames.api.interfaces.Party;
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
    private final IPlayerData playerData = new PlayerData();
    private final Party party = PGamesAPI.getPartyUtil();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        command.setAliases(Arrays.asList("privategame", "pgame"));
        if (sender instanceof Player) {
            if (args.length < 1) {
                sender.sendMessage(Utility.colorizedString("&cNot enough args"));
            }

            else {
                switch (args[0].toLowerCase()) {
                    case "enable":
                        if (args.length == 2 && sender.hasPermission("pg.admin") || sender.isOp()) {
                            switch (args[1].toLowerCase()) {
                                case "admin":
                                    if (party.hasParty((Player) sender)) {
                                        if (party.isPartyOwner((Player) sender)) {
                                            playerData.setPrivateGameEnabled((Player) sender);
                                            for (Player player : party.getPartyMembers((Player) sender)) {
                                                if (party.isPartyOwner(player))
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED));
                                                if (player != sender) {
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                }
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        playerData.setPrivateGameDisabled((Player) sender);
                                        sender.sendMessage(Utility.getMSGLang((Player) sender, PRIVATE_GAME_ENABLED));
                                    }
                            }
                        }
                        else {
                            if (sender.hasPermission("pg.enable") || sender.isOp()) {
                                if (!playerData.isPrivateGameEnabled(((Player) sender))) {
                                    if (playerData.isPlayerInParty((Player) sender)) {
                                        if (party.isPartyOwner((Player) sender)) {
                                            playerData.setPrivateGameEnabled((Player) sender);
                                            for (Player player : party.getPartyMembers((Player) sender)) {
                                                if (party.isPartyOwner(player))
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED));
                                                if (player != sender) {
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                }
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                    }
                                } else {
                                    sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_ALREADY_ENABLED));
                                }
                            } else {
                                sender.sendMessage(Utility.getMSGLang((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                            }
                        }
                    break;

                    case "disable":
                        if (args.length == 2) {
                            switch (args[1].toLowerCase()) {
                                case "admin":
                                    if (party.hasParty((Player) sender)) {
                                        if (party.isPartyOwner((Player) sender)) {
                                            playerData.setPrivateGameDisabled((Player) sender);
                                            for (Player player : party.getPartyMembers((Player) sender)) {
                                                if (party.isPartyOwner(player))
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_DISABLED));
                                                if (player != sender) {
                                                    player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                }
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        playerData.setPrivateGameDisabled((Player) sender);
                                        sender.sendMessage(Utility.getMSGLang((Player) sender, PRIVATE_GAME_DISABLED));
                                    }
                            }
                        }
                        else {
                            if (sender.hasPermission("pg.disable") || sender.isOp()) {
                                if (party.hasParty((Player) sender)) {
                                    if (playerData.isPrivateGameEnabled(((Player) sender))) {
                                        if (playerData.isPlayerInParty((Player) sender)) {
                                            if (party.isPartyOwner((Player) sender)) {
                                                playerData.setPrivateGameDisabled((Player) sender);
                                                for (Player player : party.getPartyMembers((Player) sender)) {
                                                    if (party.isPartyOwner(player))
                                                        player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_DISABLED));
                                                    if (player != sender) {
                                                        player.sendMessage(Utility.getMSGLang(player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                    }
                                                }
                                            } else {
                                                sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                    }
                                } else {
                                    sender.sendMessage(Utility.getMSGLang((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                }
                            } else {
                                sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                            }
                        }
                    break;

                    case "gui" :
                        if (sender.hasPermission("pg.gui") || sender.isOp()) {
                            if (party.hasParty(((Player) sender))) {
                                if (!party.isPartyOwner(((Player) sender))) {
                                    sender.sendMessage(Utility.getMSGLang(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                } else {
                                    new SettingsMenu((Player) sender);
                                }
                            } else {
                                new SettingsMenu((Player) sender);
                            }
                        } else {
                            sender.sendMessage(Utility.getMSGLang((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                        }
                    break;

                    case "help":
                    break;
                }
            }
        } else {
            sender.sendMessage(Utility.colorizedString("&cYou must join the server in order to use this command"));
        }
        return false;
    }
}
