package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.shop.ShopCache;
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache;
import com.andrei1058.bedwars.sidebar.SidebarService;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.MainConfig.MATERIAL;
import static me.notlewx.privategames.config.MainConfig.POSITION;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;

public class ArenaJoin implements Listener {

    @EventHandler
    public void onArenaJoin(PlayerJoinArenaEvent e) {
        IPrivatePlayer pp = PrivateGames.api.getPrivatePlayer(e.getPlayer());
        IPlayerSettings p = pp.getPlayerSettings();
        IParty party = pp.getPlayerParty();

        ItemStack settings = new ItemStack(Material.valueOf(mainConfig.getString(MATERIAL)));
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(Utility.getMsg(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_NAME));
        settingsMeta.setLore(Utility.getList(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_LORE));
        settings.setItemMeta(settingsMeta);

        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
            ISidebar sidebar = SidebarService.getInstance().getSidebar(e.getPlayer());
            sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                if (api.getPrivateArenaUtil().isPlaying(e.getPlayer())) {
                    return Utility.getMsg(e.getPlayer(), PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                } else {
                    return "";
                }
            }));
            sidebar.getHandle().refreshPlaceholders();
        }, 20L);

        if (api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) {
            IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName());
            pa.addPlayer(e.getPlayer());

            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                if (e.getArena().isSpectator(e.getPlayer())) {
                    for (ITeam team : e.getArena().getTeams()) {
                        if (team.getSize() == e.getArena().getMaxInTeam()) continue;

                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        team.addPlayers(e.getPlayer());
                        team.setBedDestroyed(false);
                        team.spawnNPCs();
                        e.getPlayer().getInventory().clear();
                        team.respawnMember(e.getPlayer());

                        e.getArena().getSpectators().remove(e.getPlayer());
                        e.getArena().getPlayers().add(e.getPlayer());
                        new PlayerQuickBuyCache(e.getPlayer());
                        new ShopCache(e.getPlayer().getUniqueId());
                        e.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                        e.getPlayer().getActivePotionEffects().forEach(potionEffect -> e.getPlayer().removePotionEffect(potionEffect.getType()));
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        e.getPlayer().closeInventory();
                        break;
                    }
                }
            }, 10L);
            return;
        }

        if (e.getArena().getPlayers().size() > 1) return;
        if (e.getArena().isSpectator(((Player) pp.getPlayer()))) return;
        if (api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) {
            IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(e.getArena().getWorldName());
            pa.addPlayer((Player) pp.getPlayer());
            return;
        }
        if (e.getArena().getStatus() == GameState.playing || e.getArena().getStatus() == GameState.restarting) return;

        if (p.isPrivateGameEnabled()) {
            if (party.hasParty()) {
                if (party.isOwner()) {
                    if (pp.hasPermission()) {
                        List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());
                        players.add(pp.getPlayer());
                        IPrivateArena a = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena(a));

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                            ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings);
                        }, 20L);
                        if (pp.getPlayerOptions().isAutoStart()) {
                            e.getArena().changeStatus(GameState.starting);
                            e.getArena().getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
                        }
                    }
                }
            } else if (pp.getPlayer().isOp() || ((Player) pp.getPlayer()).hasPermission("pg.admin")) {
                if (party.hasParty()) {
                    if (party.isOwner()) {
                        List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());
                        players.add(pp.getPlayer());

                        IPrivateArena a = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                        try {
                            MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena(a));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                            ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings);
                        }, 20L);
                    }
                } else {
                    List<OfflinePlayer> players = new ArrayList<>();
                    players.add(pp.getPlayer());

                    IPrivateArena a = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                    try {
                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena(a));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                        ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings);
                    }, 20L);
                }

                if (pp.getPlayerOptions().isAutoStart()) {
                    e.getArena().changeStatus(GameState.starting);
                    e.getArena().getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
                }
            }
        }
    }
}
