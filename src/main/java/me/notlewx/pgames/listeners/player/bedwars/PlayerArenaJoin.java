package me.notlewx.pgames.listeners.player.bedwars;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.api.PGamesAPI;
import me.notlewx.pgames.api.interfaces.IGame;
import me.notlewx.pgames.api.interfaces.Party;
import me.notlewx.pgames.data.PlayerData;
import me.notlewx.pgames.util.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

import static me.notlewx.pgames.PrivateGames.mainConfig;
import static me.notlewx.pgames.config.MainConfig.MATERIAL;
import static me.notlewx.pgames.config.MainConfig.POSITION;
import static me.notlewx.pgames.config.MessagesData.PRIVATE_GAME_MENU_ITEM_LORE;
import static me.notlewx.pgames.config.MessagesData.PRIVATE_GAME_MENU_ITEM_NAME;

public class PlayerArenaJoin implements Listener {
    private static final PlayerData playerData = new PlayerData();
    private static final Party party = PGamesAPI.getPartyUtil();
    private static final IGame game = PrivateGames.getGameUtil();
    @EventHandler
    public static void onPlayerJoin(PlayerJoinArenaEvent e) {
        Player player = e.getPlayer();
        if (e.getArena().isSpectator(player)) return;
        ItemStack settings = new ItemStack(Material.valueOf(mainConfig.getString(MATERIAL)));
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(Utility.getMSGLang(player, PRIVATE_GAME_MENU_ITEM_NAME));
        settingsMeta.setLore(Utility.getListLang(player, PRIVATE_GAME_MENU_ITEM_LORE));
        settings.setItemMeta(settingsMeta);
        if (party.hasParty(player)) {
            if (playerData.isPrivateGameEnabled(player)) {
                if (party.isPartyOwner(player) || player.isOp()) {
                    Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                        game.setArenaPrivate(e.getArena(), true);
                        game.setPrivateArenaOwner(e.getArena(), player);
                        player.getInventory().setItem(mainConfig.getInt(POSITION), settings);
                    }, 35L);
                    for (Player members : party.getPartyMembers(player)) {
                        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                            PGamesAPI.getBwApi().getArenaUtil().getArenaByPlayer(player).addPlayer(members, true);
                        }, 35L);
                    }
                }
                if (!party.getPartyMembers(player).contains(player)) {
                    e.setCancelled(true);
                }
                e.getArena().changeStatus(GameState.starting);
                e.getArena().getStartingTask().setCountdown(60);
            }
        }
        else if (!party.hasParty(player) && playerData.isPrivateGameEnabled(player) && player.isOp() || player.hasPermission("pg.admin")) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                game.setArenaPrivate(e.getArena(), true);
                game.setPrivateArenaOwner(e.getArena(), player);
                player.getInventory().setItem(mainConfig.getInt(POSITION), settings);
            }, 35L);
            e.getArena().changeStatus(GameState.starting);
            e.getArena().getStartingTask().setCountdown(60);
        }
    }
}
