package me.notlewx.pgames.commands;

import me.notlewx.pgames.api.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pgames implements CommandExecutor {
    public pgames() {
        new CommandHandler("pg", true) {
            @Override
            public boolean onCommand(CommandSender sender, String [] arguments) {
                Player player = (Player) sender;
                return true;
            }
            @Override
            public String getUsage() {
                return "/pg";
            }
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
