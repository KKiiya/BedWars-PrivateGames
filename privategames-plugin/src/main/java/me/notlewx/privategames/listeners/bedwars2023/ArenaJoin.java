package me.notlewx.privategames.listeners.bedwars2023;

import com.tomkeuper.bedwars.BedWars;
import com.tomkeuper.bedwars.api.arena.GameState;
import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.arena.team.ITeam;
import com.tomkeuper.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.tomkeuper.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.tomkeuper.bedwars.shop.ShopCache;
import com.tomkeuper.bedwars.shop.quickbuy.PlayerQuickBuyCache;
import com.tomkeuper.bedwars.sidebar.BoardManager;
import me.neznamy.tab.api.TabAPI;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import me.zuyte.admin.storage.Cache_BW2023;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.api;
import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.MainConfig.MATERIAL;
import static me.notlewx.privategames.config.MainConfig.POSITION;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.*;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER;

public class ArenaJoin implements Listener {
    public static HashMap<ITeam, BlockFace> bedDirection = new HashMap<>();

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent e) {
        if (e.getNewState() == GameState.starting) {
            for (ITeam team : e.getArena().getTeams()) {
                bedDirection.put(team, ((Bed) team.getBed().getBlock().getState().getData()).getFacing());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onArenaJoin(PlayerJoinArenaEvent e) {
        IPrivatePlayer pp = PrivateGames.api.getPrivatePlayer(e.getPlayer());
        IPlayerSettings p = pp.getPlayerSettings();
        IParty party = pp.getPlayerParty();
        IArena a = e.getArena();

        ItemStack settings = new ItemStack(Material.valueOf(mainConfig.getString(MATERIAL)));
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(Utility.getMsg(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_NAME));
        settingsMeta.setLore(Utility.getList(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_LORE));
        settings.setItemMeta(settingsMeta);

        int placeholderRefresh = BedWars.config.getInt("scoreboard-settings.scoreboard.placeholders-refresh-interval");
        TabAPI.getInstance().getPlaceholderManager().registerPlayerPlaceholder("%bw_private%", placeholderRefresh, tp -> {
            if (api.getPrivateArenaUtil().isPlaying((Player) tp.getPlayer())) {
                return Utility.getMsg((Player) tp.getPlayer(), PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
            } else {
                return "";
            }
        });

        if (api.getPrivateArenaUtil().isArenaPrivate(e.getArena().getWorldName())) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                IPrivatePlayer ppa = pa.getPrivateArenaHost();
                if (ppa.getRequests().contains(e.getPlayer().getUniqueId())) {
                    ppa.removeRequest(e.getPlayer().getUniqueId());
                    if (pa.getPlayers().size() == a.getMaxPlayers()) return;

                    api.getBedWars2023API().getArenaUtil().getArenas().remove(a);
                }
            }, 5L);

            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                if (a.isSpectator(e.getPlayer()) && a.getStatus() == GameState.playing && pa.getPlayers().contains(e.getPlayer())) {
                    if (a.getTeams().stream().anyMatch(t -> t.wasMember(e.getPlayer().getUniqueId()))) return;

                    for (ITeam team : a.getTeams()) {
                        if (team.getSize() == a.getMaxInTeam()) continue;

                        e.getPlayer().getActivePotionEffects().forEach(potionEffect -> e.getPlayer().removePotionEffect(potionEffect.getType()));
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        a.getSpectators().remove(e.getPlayer());
                        a.getPlayers().add(e.getPlayer());
                        if (team.getMembers().isEmpty()) {
                            team.spawnNPCs();
                        }
                        team.addPlayers(e.getPlayer());
                        spawnBed(team);
                        team.setBedDestroyed(false);
                        e.getPlayer().getInventory().clear();
                        e.getPlayer().getInventory().forEach(itemStack -> {
                            if (itemStack != null) {
                                if (itemStack.getType() != Material.AIR) {
                                    e.getPlayer().getInventory().remove(itemStack);
                                }
                            }
                        });
                        team.respawnMember(e.getPlayer());

                        new PlayerQuickBuyCache(e.getPlayer());
                        new ShopCache(e.getPlayer().getUniqueId());
                        e.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        e.getPlayer().closeInventory();
                        pa.addPlayer(e.getPlayer(), true);
                        BoardManager.getInstance().giveTabFeatures(e.getPlayer(), e.getArena(), true);
                        Utility.debug("Player " + e.getPlayer().getName() + " has joined the private arena " + pa.getArenaIdentifier() + " and has been added to the host's private arena.");
                        break;
                    }
                }
            }, 10L);
            return;
        }

        if (a.isSpectator(((Player) pp.getPlayer()))) return;
        if (a.getStatus() == GameState.playing || a.getStatus() == GameState.restarting) return;

        if (party.hasParty()) {
            if (!party.isOwner()) {
                Player owner = party.getOwner();
                IPrivatePlayer ppo = api.getPrivatePlayer(owner);
                if (ppo.getPlayerSettings().isPrivateGameEnabled()) {
                    if (!a.getPlayers().isEmpty()) {
                        ppo.getPlayerSettings().setPrivateGameDisabled(false);
                        return;
                    }
                }
            }
        } else {
            if (pp.getPlayer().isOp() || ((Player) pp.getPlayer()).hasPermission("pg.admin")) {
                if (a.getPlayers().size() > 1) {
                    e.setCancelled(true);
                    ((Player) pp.getPlayer()).sendMessage(Utility.getMsg(((Player) pp.getPlayer()), PRIVATE_GAME_UNABLE_TO_JOIN));
                    return;
                }
            }
        }

        Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
            if (p.isPrivateGameEnabled()) {
                if (party.hasParty()) {
                    if (party.isOwner()) {
                        if (pp.hasPermission()) {
                            List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());

                            IPrivateArena pa = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                            Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                            MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation",pa));

                            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 20L);
                            if (pp.getPlayerOptions().isAutoStart()) {
                                a.changeStatus(GameState.starting);
                                a.getStartingTask().setCountdown(PrivateGames.bw2023config.getInt("countdowns.game-start-regular"));
                            }
                        }
                    }
                } else {
                    boolean admin = pp.getPlayer().isOp() || ((Player) pp.getPlayer()).hasPermission("pg.admin");

                    if (admin && a.getPlayers().size() < 2) {
                        List<OfflinePlayer> players = new ArrayList<>(Collections.singletonList(pp.getPlayer()));

                        IPrivateArena pa =  new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                        Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation", pa));

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 20L);
                        if (pp.getPlayerOptions().isAutoStart()) {
                            a.changeStatus(GameState.starting);
                            a.getStartingTask().setCountdown(PrivateGames.bw2023config.getInt("countdowns.game-start-regular"));
                        }
                    }
                }
            }
        }, 10L);
    }

    public void spawnBed(ITeam team) {
        Bed bed = new Bed();
        BlockFace face = getBedFacing(team);
        if (Bukkit.getPluginManager().getPlugin("BedWars-AdminAddon") != null) {
            Cache_BW2023.setArenaBedsCache(team, face);
        }
        BlockState bedFoot = team.getBed().getBlock().getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(face.getOppositeFace()).getState();
        bedFoot.setType(bed.toItemStack().getType());
        bedHead.setType(bed.toItemStack().getType());
        bedFoot.setRawData((byte) face.ordinal());
        bedHead.setRawData((byte) (face.ordinal() + 8));
        bedFoot.update(true, false);
        bedHead.update(true, true);
    }

    public static BlockFace getBedFacing(ITeam team) {
        return bedDirection.get(team);
    }
}
