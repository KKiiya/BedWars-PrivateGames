package me.notlewx.privategames.menus.submenus;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.sidebar.SidebarService;
import me.notlewx.privategames.PrivateGames;
import me.notlewx.privategames.api.support.VersionSupport;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.lang.reflect.Field;

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GAMEMODE_CHANGER_TITLE;

public class GamemodeChangerMenu implements GUIHolder {

    private final String defaultGroup;
    private final VersionSupport vs;
    private final Object arena;
    private final Player p;
    private Inventory inv;

    public GamemodeChangerMenu(Player p, Object arena) {
        this.p = p;
        this.arena = arena;
        this.vs = PrivateGames.getVersionSupport();
        this.defaultGroup = api.getPrivateArenaUtil().getPrivateArenaByPlayer(p).getDefaultGroup();
        try {
            createInventory();
            addContent();
            p.openInventory(inv);
        } catch (Exception e) {
            throw new RuntimeException("Error while opening the gamemode changer menu", e);
        }
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, mainConfig.getInt("gamemode-changer-menu." + defaultGroup + ".rows") * 9, Utility.getMsg(p, SUBMENU_GAMEMODE_CHANGER_TITLE));
    }

    private void addContent() {
        for (String path : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes").getKeys(true)) {
            String mode = path.split("\\.")[0];
            int maxInTeam;
            if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).contains("max-in-team")) {
                maxInTeam = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getInt("max-in-team");
            } else maxInTeam = 0;

            Material mat = Material.getMaterial(mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getString("material"));
            byte data = (byte) mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getInt("data");
            int position = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getInt("position");
            String headUrl = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getString("head-url");
            if (!mode.equals("back-item")) {
                ItemStack modeStack = new ItemStack(mat, 1, data);
                if (vs.isPlayerHead(modeStack)) modeStack = Utility.getSkull(headUrl);
                ItemMeta modeMeta = modeStack.getItemMeta();

                modeMeta.setDisplayName(Utility.getMsg(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes." + mode + ".name"));
                modeMeta.setLore(Utility.getList(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes." + mode + ".lore"));
                modeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

                modeStack.setItemMeta(modeMeta);

                inv.setItem(position, vs.setItemTag(modeStack, "pg", "gamemode-"+mode+"-"+maxInTeam));
            }
        }

        Material backMat = Material.getMaterial(mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getString("material"));
        byte backData = (byte) mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getInt("data");
        int backPosition = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getInt("position");
        String backHeadUrl = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getString("head-url");
        ItemStack backStack = new ItemStack(backMat, 1, backData);
        if (vs.isPlayerHead(backStack)) backStack = Utility.getSkull(backHeadUrl);
        ItemMeta backMeta = backStack.getItemMeta();

        backMeta.setDisplayName(Utility.getMsg(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes.back-item.name"));
        backMeta.setLore(Utility.getList(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes.back-item.lore"));
        backMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        backStack.setItemMeta(backMeta);

        inv.setItem(backPosition, vs.setItemTag(backStack, "pg", "back-back"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) return;
        String[] mode = tag.split("-");

        e.setCancelled(true);
        if (!e.getView().getTitle().equals(Utility.getMsg(p, SUBMENU_GAMEMODE_CHANGER_TITLE))) return;

        switch (support) {
            case BEDWARS1058:
                IArena a = (IArena) arena;
                Arena aC = (Arena) a;
                if (!mode[1].equalsIgnoreCase("back")) {
                    int maxInTeam = Integer.parseInt(mode[2]);
                    a.setGroup(mode[1]);
                    if (maxInTeam > 0) {
                        Field maxInTeamField;
                        try {
                            maxInTeamField = aC.getClass().getDeclaredField("maxInTeam");
                            maxInTeamField.setAccessible(true);
                            maxInTeamField.set(aC, maxInTeam);
                        } catch (NoSuchFieldException | IllegalAccessException ex) {
                            ex.printStackTrace();
                        }
                    }

                    SidebarService.getInstance().remove(p);
                    SidebarService.getInstance().giveSidebar(p, a, true);

                    ISidebar sidebar = SidebarService.getInstance().getSidebar(p);
                    sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                        if (api.getPrivateArenaUtil().isPlaying(p)) {
                            return Utility.getMsg(p, PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                        } else return "";
                    }));
                    sidebar.getHandle().refreshPlaceholders();
                } else if (mode[0].equals("back")) new SettingsMenu(p);
                break;
            case BEDWARS2023:
                com.tomkeuper.bedwars.api.arena.IArena a2 = (com.tomkeuper.bedwars.api.arena.IArena) arena;
                com.tomkeuper.bedwars.arena.Arena a2C = (com.tomkeuper.bedwars.arena.Arena) a2;
                if (!mode[1].equalsIgnoreCase("back")) {
                    int maxInTeam = Integer.parseInt(mode[2]);
                    a2.setGroup(mode[1]);
                    if (maxInTeam > 0) {
                        Field maxInTeamField;
                        try {
                            maxInTeamField = a2C.getClass().getDeclaredField("maxInTeam");
                            maxInTeamField.setAccessible(true);
                            maxInTeamField.set(a2C, maxInTeam);
                        } catch (NoSuchFieldException | IllegalAccessException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else if (mode[0].equals("back")) new SettingsMenu(p);
                break;
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
