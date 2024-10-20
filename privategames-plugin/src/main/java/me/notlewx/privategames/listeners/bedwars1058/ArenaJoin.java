package me.notlewx.privategames.listeners.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.shop.ShopCache;
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache;
import com.andrei1058.bedwars.sidebar.SidebarService;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.arena.IPrivateArena;
import me.notlewx.privategames.api.party.IParty;
import me.notlewx.privategames.api.player.IPlayerSettings;
import me.notlewx.privategames.api.player.IPrivatePlayer;
import me.notlewx.privategames.arena.PrivateArena;
import me.notlewx.privategames.utils.MessagesUtil;
import me.notlewx.privategames.utils.Utility;
import me.zuyte.admin.storage.Cache_BW1058;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
        Player p = e.getPlayer();
        IPrivatePlayer pp = PrivateGames.api.getPrivatePlayer(p);
        IPlayerSettings ps = pp.getPlayerSettings();
        IArena a = e.getArena();
        IParty party = pp.getPlayerParty();

        ItemStack settings = new ItemStack(Material.valueOf(mainConfig.getString(MATERIAL)));
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(Utility.getMsg(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_NAME));
        settingsMeta.setLore(Utility.getList(((Player) pp.getPlayer()), PRIVATE_GAME_MENU_ITEM_LORE));
        settings.setItemMeta(settingsMeta);

        Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
            ISidebar sidebar = SidebarService.getInstance().getSidebar(p);
            sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                if (api.getPrivateArenaUtil().isPlaying(p)) {
                    return Utility.getMsg(p, PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                } else {
                    return "";
                }
            }));
            sidebar.getHandle().refreshPlaceholders();
        }, 20L);

        if (api.getPrivateArenaUtil().isArenaPrivate(a.getWorldName())) {
            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                IPrivatePlayer ppa = pa.getPrivateArenaHost();
                if (ppa.getRequests().contains(p.getUniqueId())) {
                    ppa.removeRequest(p.getUniqueId());
                    if (pa.getPlayers().size() == a.getMaxPlayers()) return;

                    api.getBedWars1058API().getArenaUtil().getArenas().remove(a);
                }
            }, 5L);

            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                if (a.isSpectator(p) && a.getStatus() == GameState.playing && pa.getPlayers().contains(p)) {
                    if (a.getTeams().stream().anyMatch(t -> t.wasMember(p.getUniqueId()))) return;

                    for (ITeam team : a.getTeams()) {
                        if (team.getSize() == a.getMaxInTeam()) continue;

                        p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        a.getSpectators().remove(p);
                        a.getPlayers().add(p);
                        if (team.getMembers().isEmpty()) {
                            team.spawnNPCs();
                        }
                        team.addPlayers(p);
                        spawnBed(team);
                        team.setBedDestroyed(false);
                        p.getInventory().clear();
                        p.getInventory().forEach(itemStack -> {
                            if (itemStack != null) {
                                if (itemStack.getType() != Material.AIR) {
                                    p.getInventory().remove(itemStack);
                                }
                            }
                        });
                        team.respawnMember(p);

                        new PlayerQuickBuyCache(p);
                        new ShopCache(p.getUniqueId());
                        p.setGameMode(GameMode.SURVIVAL);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.closeInventory();
                        pa.addPlayer(p, true);
                        SidebarService.getInstance().giveSidebar(p, a, true);
                        Utility.debug("Player " + p.getName() + " has joined the private arena " + pa.getArenaIdentifier() + " and has been added to the host's private arena.");
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
                Player owner = (Player) party.getOwner();
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
            if (ps.isPrivateGameEnabled()) {
                if (party.hasParty()) {
                    if (party.isOwner()) {
                        if (pp.hasPermission()) {
                            List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());

                            IPrivateArena pa = new PrivateArena(pp, players, a.getWorldName(), a.getGroup());

                            Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                            MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation",pa));

                            Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 20L);
                            if (pp.getPlayerOptions().isAutoStart()) {
                                a.changeStatus(GameState.starting);
                                a.getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
                            }
                        }
                    }
                } else {
                    boolean admin = pp.getPlayer().isOp() || ((Player) pp.getPlayer()).hasPermission("pg.admin");

                    if (admin && a.getPlayers().size() < 2) {
                        Utility.info("Player " + pp.getPlayer().getName() + " is an admin and the arena has less than 2 players.");
                        List<OfflinePlayer> players = new ArrayList<>(Collections.singletonList(pp.getPlayer()));

                        IPrivateArena pa =  new PrivateArena(pp, players, a.getWorldName(), a.getGroup());

                        Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation", pa));

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getInstance(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 20L);
                        if (pp.getPlayerOptions().isAutoStart()) {
                            a.changeStatus(GameState.starting);
                            a.getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
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
            Cache_BW1058.setArenaBedsCache(team, face);
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
