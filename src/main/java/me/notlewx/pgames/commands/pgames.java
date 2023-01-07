package me.notlewx.pgames.commands;

import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.api.party.Party;
import me.notlewx.pgames.api.CommandHandler;
import me.notlewx.pgames.db.MySQL;
import me.notlewx.pgames.db.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.UUID;
import static me.notlewx.pgames.config.MessagesData.*;
import static me.notlewx.pgames.main.*;

public class pgames implements CommandExecutor {
    private MySQL mySQL;
    private SQLite sqLite;
    private Party dparty;
    private PartiesAPI partis;
    private int psize;
    public pgames() {
        new CommandHandler("pg", true) {
            @Override
            public boolean onCommand(CommandSender sender, String [] arguments) {
                Player player = (Player) sender;
                UUID pe = player.getUniqueId();
                String path = player.getName();
                if (parties) {
                    psize = partis.getPartyOfPlayer(pe).getMembers().size();
                }
                else {
                    psize = dparty.getMembers(player).size();
                }
                if ((mySQL.getBooleanData(path, "playerInParty")) && psize >= 2) {
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
                    // Party system of Parties plugin
                    if (parties) {
                        for (UUID per : partis.getPartyOfPlayer(pe).getMembers()) {
                            Player pert = (Bukkit.getPlayer(per));
                            if (!pert.getName().equals(player.getName())) {
                                pert.sendMessage(Language.getMsg(pert, PRIVATE_GAME_ENABLED_OTHERS));
                            }
                            player.sendMessage(Language.getMsg(player, PRIVATE_GAME_ENABLED));
                        }
                    }
                    // Default party system of BedWars1058
                    else {
                        for (Player p : dparty.getMembers(player)) {
                            if (!p.getName().equals(player.getName())) {
                                p.sendMessage(Language.getMsg(p, PRIVATE_GAME_ENABLED_OTHERS));
                            }
                            player.sendMessage(Language.getMsg(player, PRIVATE_GAME_ENABLED));
                        }
                    }
                }
                else {
                   player.sendMessage(Language.getMsg(player, NOT_IN_PARTY));
                }
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
