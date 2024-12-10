package me.notlewx.privategames.menus.submenus;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.sidebar.ISidebar;
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

                inv.setItem(position, vs.setItemTag(modeStack, "pg", "gamemode-"+mode));
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

        inv.setItem(backPosition, vs.setItemTag(backStack, "pg", "back"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;
        String tag = vs.getItemTag(item, "pg");
        if (tag == null) return;
        String mode = tag.split("-")[1];

        e.setCancelled(true);
        if (!e.getView().getTitle().equals(Utility.getMsg(p, SUBMENU_GAMEMODE_CHANGER_TITLE))) return;

        switch (support) {
            case BEDWARS1058:
                IArena a = (IArena) arena;
                if (mode != null) {
                    a.setGroup(mode);
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
                } else if (tag.equals("back")) new SettingsMenu(p);
                break;
            case BEDWARS2023:
                com.tomkeuper.bedwars.api.arena.IArena a2 = (com.tomkeuper.bedwars.api.arena.IArena) arena;
                if (mode != null) a2.setGroup(mode);
                else if (tag.equals("back")) new SettingsMenu(p);
                break;
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
