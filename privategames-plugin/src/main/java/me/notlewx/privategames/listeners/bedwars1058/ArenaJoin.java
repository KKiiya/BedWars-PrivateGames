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
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;

import java.util.ArrayList;
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

    @EventHandler
    public void onArenaJoin(PlayerJoinArenaEvent e) {
        IPrivatePlayer pp = PrivateGames.api.getPrivatePlayer(e.getPlayer());
        IPlayerSettings p = pp.getPlayerSettings();
        IArena a = e.getArena();
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
            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                IPrivatePlayer ppa = pa.getPrivateArenaHost();
                if (ppa.getRequests().contains(e.getPlayer().getUniqueId())) {
                    ppa.removeRequest(e.getPlayer().getUniqueId());
                    if (pa.getPlayers().size() == a.getMaxPlayers()) return;

                    api.getBedWars1058API().getArenaUtil().getArenas().remove(a);
                }
            }, 5L);

            Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> {
                IPrivateArena pa = api.getPrivateArenaUtil().getPrivateArenaByIdentifier(a.getWorldName());
                if (a.isSpectator(e.getPlayer()) && a.getStatus() == GameState.playing && pa.getPlayers().contains(e.getPlayer())) {
                    if (a.getTeams().stream().anyMatch(t -> t.wasMember(e.getPlayer().getUniqueId()))) return;

                    for (ITeam team : a.getTeams()) {
                        if (team.getSize() == a.getMaxInTeam()) continue;

                        e.getPlayer().getActivePotionEffects().forEach(potionEffect -> e.getPlayer().removePotionEffect(potionEffect.getType()));
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        team.addPlayers(e.getPlayer());
                        spawnBed(team);
                        team.setBedDestroyed(false);
                        team.spawnNPCs();
                        e.getPlayer().getInventory().clear();
                        team.respawnMember(e.getPlayer());

                        a.getSpectators().remove(e.getPlayer());
                        a.getPlayers().add(e.getPlayer());
                        new PlayerQuickBuyCache(e.getPlayer());
                        new ShopCache(e.getPlayer().getUniqueId());
                        e.getPlayer().setGameMode(org.bukkit.GameMode.SURVIVAL);
                        e.getPlayer().setAllowFlight(false);
                        e.getPlayer().setFlying(false);
                        e.getPlayer().closeInventory();
                        pa.addPlayer(e.getPlayer(), true);
                        Utility.debug("Player " + e.getPlayer().getName() + " has joined the private arena " + pa.getArenaIdentifier() + " and has been added to the host's private arena.");
                        break;
                    }
                }
            }, 10L);
            return;
        }

        if (a.isSpectator(((Player) pp.getPlayer()))) return;
        if (a.getStatus() == GameState.playing || a.getStatus() == GameState.restarting) return;

        if (p.isPrivateGameEnabled()) {
            if (party.hasParty()) {
                if (party.isOwner()) {
                    if (pp.hasPermission()) {
                        List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());

                        IPrivateArena pa = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                        Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation",pa));

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 35L);
                        if (pp.getPlayerOptions().isAutoStart()) {
                            a.changeStatus(GameState.starting);
                            a.getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
                        }
                    }
                }
            } else if (pp.getPlayer().isOp() || ((Player) pp.getPlayer()).hasPermission("pg.admin")) {
                if (party.hasParty()) {
                    if (party.isOwner()) {
                        List<OfflinePlayer> players = new ArrayList<>(party.getPartyMembers());

                        IPrivateArena pa =  new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                        Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                        MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation",pa));

                        Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 35L);
                    }
                } else {
                    List<OfflinePlayer> players = new ArrayList<>();
                    players.add(pp.getPlayer());

                    IPrivateArena pa = new PrivateArena(pp, players, e.getArena().getWorldName(), e.getArena().getGroup());

                    Utility.debug("Private Arena created (" + pa.getArenaIdentifier() + ") by " + pp.getPlayer().getName() + " with " + pa.getPlayers().size() + " players.");
                    MessagesUtil.sendMessage(MessagesUtil.formatPrivateArena("privateArenaCreation",pa));

                    Bukkit.getScheduler().runTaskLater(PrivateGames.getPlugins(), () -> ((Player) pp.getPlayer()).getInventory().setItem(mainConfig.getInt(POSITION), settings), 35L);
                }
                if (pp.getPlayerOptions().isAutoStart()) {
                    a.changeStatus(GameState.starting);
                    a.getStartingTask().setCountdown(PrivateGames.bw1058config.getInt("countdowns.game-start-regular"));
                }
            }
        }
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
