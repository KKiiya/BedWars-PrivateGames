package me.notlewx.pgames.commands;

import me.notlewx.pgames.api.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class pgames implements CommandExecutor {
    public pgames() {
        new CommandHandler("pgames", true) {
            @Override
            public boolean onCommand(CommandSender sender, String [] arguments) {
                Player player = (Player) sender;
                Inventory i = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&7Private Games Settings"));
                player.openInventory(i);
                return true;
            }
            @Override
            public String getUsage() {
                return "/pgames";
            }
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
