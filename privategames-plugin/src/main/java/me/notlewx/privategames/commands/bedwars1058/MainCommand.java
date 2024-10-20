package me.notlewx.privategames.commands.bedwars1058;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.server.ServerType;
import me.notlewx.privategames.PrivateGames;
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
                            } else {
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
                        if (sender.hasPermission("pg.join") || sender.isOp()) {
                            if (args.length < 2) {
                                sender.sendMessage(Utility.c("&cNot enough args"));
                            } else {
                                if (Bukkit.getPlayer(args[1]) != null) {
                                    Player requested = Bukkit.getPlayer(args[1]);
                                    Player requester = (Player) sender;
                                    if (requested == requester) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_JOIN_SELF));
                                        return false;
                                    }
                                    IPrivatePlayer pp = api.getPrivatePlayer(requested);
                                    IArena a = PrivateGames.getBw1058Api().getArenaUtil().getArenaByPlayer(requested);

                                    if (api.getBedWars1058API().getArenaUtil().isPlaying(requester)) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_IN_GAME));
                                        return false;
                                    }

                                    if (a == null) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN));
                                    } else if (!PrivateGames.api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN));
                                    } else {
                                        if (!pp.getPlayerOptions().isAllowJoin()) {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_COULDNT_JOIN));
                                            return false;
                                        } else {
                                            if (pp.getRequests().contains(requester.getUniqueId())) {
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ALREADY_SENT).replace("{player}", requested.getDisplayName()));
                                                return false;
                                            }
                                            PrivateGameJoinRequestSendEvent event = new PrivateGameJoinRequestSendEvent(requested.getUniqueId(), requester.getUniqueId());
                                            Bukkit.getPluginManager().callEvent(event);
                                            if (event.isCancelled()) return false;
                                            requester.sendMessage(Utility.getMsg(requester, PRIVATE_ARENA_REQUEST_MESSAGE_SENT).replace("{player}", requested.getDisplayName()));
                                            api.getPrivatePlayer(requested).addRequest(requester.getUniqueId());
                                            Utility.sendJoinRequestMessage(requested, requester.getUniqueId());
                                            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                                                if (pp.getRequests().contains(requester.getUniqueId())) {
                                                    requester.sendMessage(Utility.getMsg(requester, PRIVATE_ARENA_REQUEST_EXPIRED).replace("{player}", requested.getDisplayName()));
                                                    pp.removeRequest(requester.getUniqueId());
                                                }
                                            }, 20 * 60);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(Utility.c("&cCouldn't find this player"));
                                }
                            }
                        } else {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
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
                    case "accept":
                        if (sender.hasPermission("pg.accept") || sender.isOp()) {
                            IPrivatePlayer pp = api.getPrivatePlayer((Player) sender);
                            IArena a = PrivateGames.getBw1058Api().getArenaUtil().getArenaByPlayer(pp.getPlayer().getPlayer());
                            if (args.length == 2) {
                                if (pp.getRequestByName(args[1]) == null) {
                                    sender.sendMessage(Utility.c(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", args[1])));
                                    return false;
                                }

                                OfflinePlayer requester = Bukkit.getOfflinePlayer(pp.getRequestByName(args[1]));
                                if (pp.getRequests().contains(requester.getUniqueId())) {
                                    if (a == null) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                    } else if (!PrivateGames.api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_IN_GAME));
                                    } else {
                                        if (PrivateGames.getBw1058Api().getServerType() == ServerType.BUNGEE) {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ACCEPTED).replace("{player}", requester.getName()));
                                            MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("accept", requester.getUniqueId(), ((Player) sender).getUniqueId()));
                                        } else if (PrivateGames.getBw1058Api().getServerType() == ServerType.MULTIARENA || PrivateGames.getBw1058Api().getServerType() == ServerType.SHARED) {
                                            if (requester.isOnline()) {
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ACCEPTED).replace("{player}", ((Player) requester).getDisplayName()));
                                                ((Player) requester).sendMessage(Utility.getMsg(((Player) requester), PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER).replace("{player}", ((Player) sender).getDisplayName()));
                                                PrivateGames.getBw1058Api().getArenaUtil().getArenaByPlayer(pp.getPlayer().getPlayer()).addPlayer(((Player) requester), true);
                                            } else {
                                                sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", requester.getName()));
                                            }
                                        }
                                    }
                                } else {
                                    sender.sendMessage(Utility.c(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", requester.getName())));
                                }
                            } else if (args.length == 1) {
                                if (a == null) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                } else if (!PrivateGames.api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_IN_GAME));
                                } else {
                                    if (pp.getLastJoinRequest() == null) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_NO_PENDING_REQUESTS));
                                        return false;
                                    }
                                    if (PrivateGames.getBw1058Api().getServerType() == ServerType.BUNGEE) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ACCEPTED).replace("{player}", Bukkit.getOfflinePlayer(pp.getLastJoinRequest()).getName()));
                                        MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("accept", Bukkit.getOfflinePlayer(pp.getLastJoinRequest()).getUniqueId(), ((Player) sender).getUniqueId()));
                                    } else if (PrivateGames.getBw1058Api().getServerType() == ServerType.MULTIARENA || PrivateGames.getBw1058Api().getServerType() == ServerType.SHARED) {
                                        if (Bukkit.getPlayer(pp.getLastJoinRequest()) != null) {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_ACCEPTED).replace("{player}", (Bukkit.getPlayer(pp.getLastJoinRequest())).getDisplayName()));
                                            Bukkit.getPlayer(pp.getLastJoinRequest()).sendMessage(Utility.getMsg(Bukkit.getPlayer(pp.getLastJoinRequest()), PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER).replace("{player}", ((Player) sender).getDisplayName()));
                                            PrivateGames.getBw1058Api().getArenaUtil().getArenaByPlayer(pp.getPlayer().getPlayer()).addPlayer(Bukkit.getPlayer(pp.getLastJoinRequest()), true);
                                        } else {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", Bukkit.getPlayer(pp.getLastJoinRequest()).getName()));
                                        }
                                    }
                                }
                            } else {
                                sender.sendMessage(Utility.getMsg((Player) sender, "cmd-not-found"));
                            }
                        } else {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                        }
                        break;
                    case "deny":
                        if (sender.hasPermission("pg.deny") || sender.isOp()) {
                            IPrivatePlayer pp = api.getPrivatePlayer((Player) sender);
                            IArena a = PrivateGames.getBw1058Api().getArenaUtil().getArenaByPlayer(pp.getPlayer().getPlayer());
                            if (args.length == 2) {
                                if (pp.getRequestByName(args[1]) == null) {
                                    sender.sendMessage(Utility.c(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", args[1])));
                                    return false;
                                }
                                OfflinePlayer requester = Bukkit.getOfflinePlayer(pp.getRequestByName(args[1]));
                                if (pp.getRequests().contains(requester.getUniqueId())) {
                                    if (a == null) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                    } else if (!PrivateGames.api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_IN_GAME));
                                    } else {
                                        if (PrivateGames.getBw1058Api().getServerType() == ServerType.BUNGEE) {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_DENIED).replace("{player}", requester.getName()));
                                            MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("deny", requester.getUniqueId(), ((Player) sender).getUniqueId()));
                                        } else if (PrivateGames.getBw1058Api().getServerType() == ServerType.MULTIARENA || PrivateGames.getBw1058Api().getServerType() == ServerType.SHARED) {
                                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_DENIED).replace("{player}", ((Player) requester).getDisplayName()));
                                            if (requester.isOnline()) {
                                                ((Player) requester).sendMessage(Utility.getMsg(((Player) requester), PRIVATE_ARENA_REQUEST_DENIED_REQUESTER).replace("{player}", ((Player) sender).getDisplayName()));
                                            }
                                        }
                                        pp.removeRequest(requester.getUniqueId());
                                    }
                                } else {
                                    sender.sendMessage(Utility.c(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER).replace("{player}", requester.getName())));
                                }
                            } else if (args.length == 1) {
                                if (a == null) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                                } else if (!PrivateGames.api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
                                    sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_CANT_IN_GAME));
                                } else {
                                    if (Bukkit.getOfflinePlayer(pp.getLastJoinRequest()) == null) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_NO_PENDING_REQUESTS));
                                        return false;
                                    }
                                    if (PrivateGames.getBw1058Api().getServerType() == ServerType.BUNGEE) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_DENIED).replace("{player}", Bukkit.getOfflinePlayer(pp.getLastJoinRequest()).getName()));
                                        MessagesUtil.sendMessage(MessagesUtil.formatJoinRequest("deny", Bukkit.getOfflinePlayer(pp.getLastJoinRequest()).getUniqueId(), ((Player) sender).getUniqueId()));
                                    } else if (PrivateGames.getBw1058Api().getServerType() == ServerType.MULTIARENA || PrivateGames.getBw1058Api().getServerType() == ServerType.SHARED) {
                                        sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_ARENA_REQUEST_DENIED).replace("{player}", Bukkit.getPlayer(pp.getLastJoinRequest()).getDisplayName()));
                                        if (Bukkit.getPlayer(pp.getLastJoinRequest()) != null) {
                                            Bukkit.getPlayer(pp.getLastJoinRequest()).sendMessage(Utility.getMsg(Bukkit.getPlayer(pp.getLastJoinRequest()), PRIVATE_ARENA_REQUEST_DENIED_REQUESTER).replace("{player}", ((Player) sender).getDisplayName()));
                                        }
                                    }
                                    pp.removeRequest(pp.getLastJoinRequest());
                                }
                            } else {
                                sender.sendMessage(Utility.getMsg((Player) sender, "cmd-not-found").replace("{prefix}", Utility.getMsg((Player) sender, "prefix")));
                            }
                        } else {
                            sender.sendMessage(Utility.getMsg((Player) sender, PRIVATE_GAME_NO_PERMISSION));
                        }
                        break;
                    default:
                        sender.sendMessage(Utility.getMsg((Player) sender, "cmd-not-found").replace("{prefix}", Utility.getMsg((Player) sender, "prefix")));
                        break;
                }
            }
        } else {
            sender.sendMessage(Utility.c("&cYou must join the server in order to use this command"));
        }
        return false;
    }
}
