package me.notlewx.privategames.commands.proxy;

import com.andrei1058.bedwars.proxy.api.ArenaStatus;
import com.andrei1058.bedwars.proxy.api.CachedArena;
import com.andrei1058.bedwars.proxy.arenamanager.ArenaManager;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.player.PrivatePlayer;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.ADMIN_HELP_MESSAGE;

public class MainCommand implements CommandExecutor {
    private IPlayerSettings playerData;
    private IParty party;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        command.setAliases(Arrays.asList("privategame", "pgame", "private"));
        if (sender instanceof Player) {
            playerData = (new PrivatePlayer((Player) sender)).getPlayerSettings();
            party = (new PrivatePlayer((Player) sender)).getPlayerParty();
            if (args.length < 1) {
                for (String m : Utility.getList((Player) sender, HELP_MESSAGE)) {
                    sender.sendMessage(m);
                }
            }

            else {
                switch (args[0].toLowerCase()) {
                    case "enable":
                        if (args.length == 1) {
                            if (sender.hasPermission("pg.enable") || sender.isOp()) {
                                if (!playerData.isPrivateGameEnabled()) {
                                    if (party.hasParty()) {
                                        if (party.isOwner()) {
                                            playerData.setPrivateGameEnabled();
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_ENABLED));
                                            for (Player player : party.getPartyMembers()) {
                                                if (player != sender) {
                                                    player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                }
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                    }
                                } else {
                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_ENABLED));
                                }
                            } else {
                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                            }
                        }
                        if (args.length == 2) {
                            switch (args[1].toLowerCase()) {
                                case "admin":
                                    if (sender.hasPermission("pg.admin") || sender.isOp()) {
                                        if (!playerData.isPrivateGameEnabled()) {
                                            if (party.hasParty()) {
                                                if (party.isOwner()) {
                                                    playerData.setPrivateGameEnabled();
                                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_ENABLED));
                                                    for (Player player : party.getPartyMembers()) {
                                                        if (player != sender) {
                                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                        }
                                                    }
                                                } else {
                                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                                }
                                            } else {
                                                playerData.setPrivateGameEnabled();
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_ENABLED));
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_ENABLED));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                    }
                            }
                        }
                        break;

                    case "disable":
                        if (args.length == 1) {
                            if (sender.hasPermission("pg.disable") || sender.isOp()) {
                                if (party.hasParty()) {
                                    if (playerData.isPrivateGameEnabled()) {
                                        if (party.hasParty()) {
                                            if (party.isOwner()) {
                                                playerData.setPrivateGameDisabled(false);
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                                for (Player player : party.getPartyMembers()) {
                                                    if (player != sender) {
                                                        player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                    }
                                                }
                                            } else {
                                                sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                    }
                                } else {
                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                }
                            } else {
                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                            }
                        }
                        if (args.length == 2) {
                            if (sender.hasPermission("pg.admin") || sender.isOp()){
                                switch (args[1].toLowerCase()) {
                                    case "admin":
                                        if (playerData.isPrivateGameEnabled()) {
                                            if (party.hasParty()) {
                                                if (party.isOwner()) {
                                                    playerData.setPrivateGameDisabled(false);
                                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                                    for (Player player : party.getPartyMembers()) {
                                                        if (player != sender) {
                                                            player.sendMessage(Utility.getMsg(player, PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                        }
                                                    }
                                                } else {
                                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                                }
                                            } else {
                                                playerData.setPrivateGameDisabled(false);
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                        }
                                        break;
                                    default:
                                        sender.sendMessage(Utility.getMsg((((Player) sender)), ""));
                                        break;
                                }
                            } else {
                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                            }
                        }
                        break;

                    case "gui" :
                        if (sender.hasPermission("pg.gui") || sender.isOp()) {
                            if (party.hasParty()) {
                                if (!party.isOwner()) {
                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                } else {
                                    new SettingsMenu((Player) sender);
                                }
                            }
                            else {
                                new SettingsMenu((Player) sender);
                            }
                        } else {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                        }
                        break;

                    case "help":
                        List<String> message;
                        if (sender.hasPermission("pg.help") || sender.isOp()) {
                            message = Utility.getList((Player) sender, ADMIN_HELP_MESSAGE);
                        } else {
                            message = Utility.getList((Player) sender, HELP_MESSAGE);
                        }
                        for (String m : message) {
                            sender.sendMessage(m);
                        }
                        break;
                    case "join":
                        if (args.length > 2) return false;
                        IPrivatePlayer host = null;
                        for (CachedArena a : ArenaManager.getArenas()) {
                            if (!api.getPrivateArenaUtil().isArenaPrivate(a.getRemoteIdentifier())) continue;
                            if (a.getCurrentPlayers() >= a.getMaxPlayers()) continue;

                            if (api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getRemoteIdentifier()).getPrivateArenaHost().getPlayer().getName().equals(args[1])) {
                                host = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getRemoteIdentifier()).getPrivateArenaHost();
                                if (!host.getPlayerOptions().isAllowJoin()) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", host.getPlayer().getName()));
                                    return false;
                                }
                                if (a.getStatus() == ArenaStatus.WAITING || a.getStatus() == ArenaStatus.STARTING) {
                                    a.addPlayer((Player) sender, null);
                                } else if (a.getStatus() == ArenaStatus.PLAYING) {
                                    a.addSpectator((Player) sender, null);
                                } else {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", host.getPlayer().getName()));
                                }
                            }
                        }
                        if (host == null) {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", args[1]));
                        }
                        break;
                    case "reload":
                        if (!sender.hasPermission("pg.reload") || !sender.isOp()) {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                            return false;
                        }
                        sender.sendMessage(Utility.c("&eReloading config..."));
                        mainConfig.reload();
                        sender.sendMessage(Utility.c("&aConfig reloaded successfully!"));
                        break;
                }
            }
        } else {
            sender.sendMessage(Utility.c("&cYou must join the server in order to use this command"));
        }
        return false;
    }
}
