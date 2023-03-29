package me.notlewx.pgames.commands;

import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.data.PlayerData;
import me.notlewx.pgames.menu.SettingsMenu;
import me.notlewx.pgames.util.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class MainCommand implements CommandExecutor {
    private final PlayerData playerData = new PlayerData();
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
                                playerData.setPrivateGameEnabled((Player) sender);
                            }
                            break;
                        case "disable":
                            if (playerData.isPlayerInParty((Player) sender)) {
                                playerData.setPrivateGameDisabled((Player) sender);
                            } else {

                            }
                            break;
                        case "help":
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
