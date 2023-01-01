package me.notlewx.pgames.commands;

import com.andrei1058.bedwars.api.language.Language;
import me.notlewx.pgames.api.CommandHandler;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static me.notlewx.pgames.config.MessagesData.*;
import static me.notlewx.pgames.main.bwproxy;
import static me.notlewx.pgames.main.usingdb;

public class pgames implements CommandExecutor {
    private MySQL mySQL;
    private SQLite sqLite;
    private IGame iGame;
    public pgames() {
        new CommandHandler("pg enable", true) {
            @Override
            public boolean onCommand(CommandSender sender, String [] arguments) {
                Player player = (Player) sender;
                String path = player.getName();
                if ((mySQL.getData(path, "playerInParty")).equals("true") && iGame.getMembers().size() > 2) {
                    // BedWars1058 database
                    if (!bwproxy) {
                        if (usingdb) {
                            mySQL.setBooleanData(path, "privateGameEnabled", true);
                        }
                        else {
                            sqLite.setBooleanData(path, "privateGameEnabled", true);
                        }
                    }
                    // BedWarsProxy database
                    else {
                        mySQL.setBooleanData(path, "privateGameEnabled", true);
                    }
                    for (Player p : iGame.getMembers()) {
                        if (!p.getName().equals(sender)) {
                            p.sendMessage(Language.getMsg(p, PRIVATE_GAME_ENABLED_OTHERS));
                        }
                        sender.sendMessage(Language.getMsg((Player) sender, PRIVATE_GAME_ENABLED));
                    }
                }
                else {
                   sender.sendMessage(Language.getMsg(player, NOT_IN_PARTY));
                }
                return true;
            }
            @Override
            public String getUsage() {
                return "/pg enable";
            }
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
