package me.notlewx.pgames.commands;

import me.notlewx.pgames.util.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class pgames implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        command.setAliases(Arrays.asList("pg", "privategame", "pgame"));
        if (sender instanceof Player) {
            if (args.length < 1) {
                sender.sendMessage(Utility.colorizedString("&cNot enough args"));
            }
            else {
                switch (args[0].toLowerCase()) {
                    case "enable":

                }
            }
        } else {
            sender.sendMessage(Utility.colorizedString("&cYou must join the server in order to use this command"));
        }
        return false;
    }
}
