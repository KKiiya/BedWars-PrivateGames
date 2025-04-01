package me.notlewx.privategames.commands.proxy2023;

import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.events.PrivateGameJoinRequestSendEvent;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.*;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        command.setAliases(Arrays.asList("privategame", "pgame", "private"));
        if (sender instanceof Player) {
            Player p = (Player) sender;
            IPlayerSettings playerData = api.getPrivatePlayer(p).getPlayerSettings();
            IParty party = api.getPrivatePlayer(p).getPlayerParty();
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
                                            for (OfflinePlayer player : party.getPartyMembers()) {
                                                if (player != sender) {
                                                    ((Player) player).sendMessage(Utility.getMsg(((Player) player), PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
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
                                                    for (OfflinePlayer player : party.getPartyMembers()) {
                                                        if (player != sender) {
                                                            ((Player) player).sendMessage(Utility.getMsg(((Player) player), PRIVATE_GAME_ENABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
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
                                                for (OfflinePlayer player : party.getPartyMembers()) {
                                                    if (player != sender) {
                                                        ((Player) player).sendMessage(Utility.getMsg(((Player) player), PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                    }
                                                }
                                            } else {
                                                playerData.setPrivateGameDisabled(false);
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_IN_PARTY));
                                        }
                                    } else {
                                        sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                    }
                                } else {
                                    if (playerData.isPrivateGameEnabled()) {
                                        playerData.setPrivateGameDisabled(false);
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                    } else {
                                        sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                    }
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
                                                    for (OfflinePlayer player : party.getPartyMembers()) {
                                                        if (player != sender) {
                                                            ((Player) player).sendMessage(Utility.getMsg(((Player) player), PRIVATE_GAME_DISABLED_OTHERS).replace("{player}", ((Player) sender).getDisplayName()));
                                                        }
                                                    }
                                                } else {
                                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_NOT_OWNER));
                                                }
                                            } else {
                                                if (playerData.isPrivateGameEnabled()) {
                                                    playerData.setPrivateGameDisabled(false);
                                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_DISABLED));
                                                } else {
                                                    sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                                }
                                            }
                                        } else {
                                            sender.sendMessage(Utility.getMsg(((Player) sender), PRIVATE_GAME_ALREADY_DISABLED));
                                        }
                                        break;
                                    default:
                                        sender.sendMessage(Utility.getMsg((((Player) sender)), "cmd-not-found"));
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
                    case "join":
                        if (sender.hasPermission("pg.join") || sender.isOp()) {
                            if (args.length < 2) {
                                sender.sendMessage(Utility.getMsg(p, NOT_ENOUGH_ARGS));
                            } else {
                                if (Bukkit.getPlayer(args[1]) != null) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", Bukkit.getPlayer(args[1]).getName()));
                                    return false;
                                }

                                IPrivatePlayer requested = null;

                                for (IPrivateArena a : api.getPrivateArenaUtil().getPrivateArenas()) {
                                    if (a.isFull()) continue;

                                    if (a.getPrivateArenaHost().getPlayer().getName().equalsIgnoreCase(args[1])) {
                                        requested = a.getPrivateArenaHost();
                                    }
                                }

                                if (requested == null) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", args[1]));
                                    return false;
                                }

                                if (!requested.getPlayerOptions().isAllowJoin()) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", requested.getPlayer().getName()));
                                    return false;
                                }

                                Player requester = (Player) sender;

                                if (!requested.getPlayerOptions().isAllowJoin()) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN).replace("{player}", requested.getPlayer().getName()));
                                    return false;
                                } else {
                                    if (requested.getRequests().contains(requester.getUniqueId())) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ALREADY_SENT).replace("{player}", requested.getPlayer().getName()));
                                        return false;
                                    }
                                    PrivateGameJoinRequestSendEvent event = new PrivateGameJoinRequestSendEvent(requested.getUniqueId(), requester.getUniqueId());
                                    Bukkit.getPluginManager().callEvent(event);
                                    if (event.isCancelled()) return false;

                                    requester.sendMessage(Utility.getMsg(requester, PRIVATE_ARENA_REQUEST_MESSAGE_SENT).replace("{player}", requested.getPlayer().getName()));
                                    MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("", requester.getUniqueId(), requested.getUniqueId()));

                                    IPrivatePlayer finalRequested = requested;
                                    finalRequested.addRequest(requester.getUniqueId());
                                    Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                                        finalRequested.removeRequest(requester.getUniqueId());
                                        if (!requester.isOnline()) return;
                                        MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("expired", requester.getUniqueId(), finalRequested.getUniqueId()));
                                        requester.sendMessage(Utility.getMsg(requester, PRIVATE_ARENA_REQUEST_EXPIRED).replace("{player}", Bukkit.getOfflinePlayer(finalRequested.getUniqueId()).getName()));
                                    }, 20 * 60);
                                }
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
