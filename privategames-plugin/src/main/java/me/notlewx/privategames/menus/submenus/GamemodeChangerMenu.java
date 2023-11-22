package me.notlewx.privategames.menus.submenus;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
import com.andrei1058.bedwars.libs.sidebar.PlaceholderProvider;
import com.andrei1058.bedwars.libs.sidebar.SidebarLine;
import com.andrei1058.bedwars.sidebar.SidebarService;
import me.notlewx.privategames.menus.GUIHolder;
import me.notlewx.privategames.menus.SettingsMenu;
import me.notlewx.privategames.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

import static me.notlewx.privategames.PrivateGames.*;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.ITEM_GAMEMODE_CHANGER_LORE;
import static me.notlewx.privategames.config.bedwars2023.MessagesData.SUBMENU_GAMEMODE_CHANGER_TITLE;

public class GamemodeChangerMenu implements GUIHolder {

    private Inventory inv;
    private final Player p;
    private final Object arena;
    private final HashMap<Integer, String> modePosition;
    private final String defaultGroup;

    public GamemodeChangerMenu(Player p, Object arena) {
        this.p = p;
        this.arena = arena;
        this.modePosition = new HashMap<>();
        this.defaultGroup = api.getPrivateArenaUtil().getPrivateArenaByPlayer(p).getDefaultGroup();
        createInventory();
        addContent();
        p.openInventory(inv);
    }

    private void createInventory() {
        inv = Bukkit.createInventory(this, mainConfig.getInt("gamemode-changer-menu." + defaultGroup + ".rows") * 9, Utility.getMsg(p, SUBMENU_GAMEMODE_CHANGER_TITLE));
    }

    private void addContent() {
        for (String path : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes").getKeys(true)) {
            String mode = path.split("\\.")[0];
            Material mat = Material.getMaterial(mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getString("material"));
            byte data = (byte) mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getInt("data");
            int position = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getInt("position");
            String headUrl = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes." + mode).getString("head-url");
            if (!mode.equals("back-item")) {
                modePosition.put(position, mode);

                ItemStack modeStack;
                if (mat == Material.SKULL_ITEM) {
                    modeStack = Utility.getSkull(headUrl);
                } else {
                    modeStack = new ItemStack(mat, 1, data);
                }
                ItemMeta modeMeta = modeStack.getItemMeta();

                modeMeta.setDisplayName(Utility.getMsg(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes." + mode + ".name"));
                modeMeta.setLore(Utility.getList(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes." + mode + ".lore"));
                modeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

                modeStack.setItemMeta(modeMeta);

                inv.setItem(position, modeStack);
            }
        }

        Material backMat = Material.getMaterial(mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getString("material"));
        byte backData = (byte) mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getInt("data");
        int backPosition = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getInt("position");
        String backHeadUrl = mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + defaultGroup + ".modes.back-item").getString("head-url");
        ItemStack backStack;
        if (backMat == Material.SKULL_ITEM) {
            backStack = Utility.getSkull(backHeadUrl);
        } else {
            backStack = new ItemStack(backMat, 1, backData);
        }
        ItemMeta backMeta = backStack.getItemMeta();

        backMeta.setDisplayName(Utility.getMsg(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes.back-item.name"));
        backMeta.setLore(Utility.getList(p, "addons.private-games.gamemode-changer-menu." + defaultGroup + ".modes.back-item.lore"));
        backMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        backStack.setItemMeta(backMeta);

        inv.setItem(backPosition, backStack);
    }
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.getMsg(p, SUBMENU_GAMEMODE_CHANGER_TITLE))) {
            switch (support) {
                case BEDWARS1058:
                    IArena a = (IArena) arena;
                    if (modePosition.get(e.getSlot()) != null) {
                        a.setGroup(modePosition.get(e.getSlot()));
                        SidebarService.getInstance().remove(p);
                        SidebarService.getInstance().giveSidebar(p, a, true);

                        ISidebar sidebar = SidebarService.getInstance().getSidebar(p);
                        sidebar.getHandle().addPlaceholder(new PlaceholderProvider("{private}", () -> {
                            if (api.getPrivateArenaUtil().isPlaying(p)) {
                                return Utility.getMsg(p, PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER);
                            } else {
                                return "";
                            }
                        }));
                        sidebar.getHandle().refreshPlaceholders();
                    } else {
                        if (e.getSlot() == mainConfig.getInt("gamemode-changer-menu." + defaultGroup + ".modes.back-item.position")) {
                            new SettingsMenu(p);
                        }
                    }
                    break;
                case BEDWARS2023:
                    com.tomkeuper.bedwars.api.arena.IArena a2 = (com.tomkeuper.bedwars.api.arena.IArena) arena;
                    if (modePosition.get(e.getSlot()) != null) {
                        a2.setGroup(modePosition.get(e.getSlot()));
                    } else {
                        if (e.getSlot() == mainConfig.getInt("gamemode-changer-menu." + defaultGroup + ".modes.back-item.position")) {
                            new SettingsMenu(p);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
