package me.notlewx.privategames.config;

import me.notlewx.privategames.config.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MainConfig extends ConfigManager {
    public MainConfig(Plugin plugin, String name, String dir) {
        super(plugin, name, dir);
        YamlConfiguration yml = getYml();
        yml.options().header("BedWars1058 PrivateGames Addon by Kiiya#9207");
        yml.addDefault(SPEED, true);
        yml.addDefault(ONE_HIT_ONE_KILL, true);
        yml.addDefault(HEALTH_BUFF, true);
        yml.addDefault(LONG_JUMP, true);
        yml.addDefault(BED_INSTA_BREAK, true);
        yml.addDefault(RESPAWN_TIME, true);
        yml.addDefault(EVENTS_TIME, true);
        yml.addDefault(MAX_TEAM_UPGRADES, true);
        yml.addDefault(ALLOW_MAP_BREAK, true);
        yml.addDefault(NO_DIAMONDS, true);
        yml.addDefault(NO_EMERALDS, true);
        yml.addDefault(GAMEMODE_CHANGER, true);
        yml.addDefault(SETTINGS_ROWS, 6);
        yml.addDefault(ONE_HIT_ONE_HILL_MATERIAL, "DIAMOND_SWORD");
        yml.addDefault(ONE_HIT_ONE_HILL_POSITION, 10);
        yml.addDefault(HEALTH_BUFF_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_POSITION, 12);
        yml.addDefault(LONG_JUMP_MATERIAL, "QUARTZ");
        yml.addDefault(LONG_JUMP_POSITION, 14);
        yml.addDefault(SPEED_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_POSITION, 16);
        yml.addDefault(RESPAWN_TIME_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_POSITION, 20);
        yml.addDefault(EVENTS_TIME_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_POSITION, 22);
        yml.addDefault(NO_EMERALDS_MATERIAL, "EMERALD");
        yml.addDefault(NO_EMERALDS_POSITION, 24);
        yml.addDefault(NO_DIAMONDS_MATERIAL, "DIAMOND");
        yml.addDefault(NO_DIAMONDS_POSITION, 28);
        yml.addDefault(ALLOW_MAP_BREAK_MATERIAL, "GRASS");
        yml.addDefault(ALLOW_MAP_BREAK_POSITION, 30);
        yml.addDefault(BED_INSTA_BREAK_MATERIAL, "BED");
        yml.addDefault(BED_INSTA_BREAK_POSITION, 32);
        yml.addDefault(MAX_TEAM_UPGRADES_MATERIAL, "DIAMOND_BLOCK");
        yml.addDefault(MAX_TEAM_UPGRADES_POSITION, 34);
        yml.addDefault(GAMEMODE_CHANGER_MATERIAL, "REDSTONE_COMPARATOR");
        yml.addDefault(GAMEMODE_CHANGER_POSITION, 53);
        yml.addDefault(BACK_MATERIAL, "ARROW");
        yml.addDefault(BACK_POSITION, 49);
        yml.addDefault(SPEED_MENU_ROWS, 4);
        yml.addDefault(SPEED_BACK_MATERIAL, "ARROW");
        yml.addDefault(SPEED_BACK_POSITION, 31);
        yml.addDefault(SPEED_LEVEL_I_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_I_POSITION, 10);
        yml.addDefault(SPEED_LEVEL_II_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_II_POSITION, 12);
        yml.addDefault(SPEED_LEVEL_III_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_III_POSITION, 14);
        yml.addDefault(SPEED_LEVEL_IV_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_IV_POSITION, 16);
        yml.addDefault(RESPAWN_TIME_MENU_ROWS, 4);
        yml.addDefault(RESPAWN_TIME_BACK_MATERIAL, "ARROW");
        yml.addDefault(RESPAWN_TIME_BACK_POSITION, 31);
        yml.addDefault(RESPAWN_TIME_LEVEL_I_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_I_POSITION, 11);
        yml.addDefault(RESPAWN_TIME_LEVEL_II_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_II_POSITION, 13);
        yml.addDefault(RESPAWN_TIME_LEVEL_III_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_III_POSITION, 15);
        yml.addDefault(EVENTS_TIME_MENU_ROWS, 4);
        yml.addDefault(EVENTS_TIME_BACK_MATERIAL, "ARROW");
        yml.addDefault(EVENTS_TIME_BACK_POSITION, 31);
        yml.addDefault(EVENTS_TIME_LEVEL_I_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_I_POSITION, 10);
        yml.addDefault(EVENTS_TIME_LEVEL_II_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_II_POSITION, 12);
        yml.addDefault(EVENTS_TIME_LEVEL_III_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_III_POSITION, 14);
        yml.addDefault(EVENTS_TIME_LEVEL_IV_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_IV_POSITION, 16);
        yml.addDefault(HEALTH_BUFF_MENU_ROWS, 4);
        yml.addDefault(HEALTH_BUFF_BACK_MATERIAL, "ARROW");
        yml.addDefault(HEALTH_BUFF_BACK_POSITION, 31);
        yml.addDefault(HEALTH_BUFF_LEVEL_I_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_I_POSITION, 11);
        yml.addDefault(HEALTH_BUFF_LEVEL_II_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_II_POSITION, 13);
        yml.addDefault(HEALTH_BUFF_LEVEL_III_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_III_POSITION, 15);
        yml.addDefault(MATERIAL, "REDSTONE_BLOCK");
        yml.addDefault(POSITION, 0);
        yml.addDefault(ENCHANTED, false);
        yml.options().copyDefaults(true);
        save();
    }
    public static final String
           SPEED = "enable-modifiers.speed",
           ONE_HIT_ONE_KILL = "enable-modifiers.one-hit-one-kill",
           LONG_JUMP = "enable-modifiers.long-jump",
           BED_INSTA_BREAK = "enable-modifiers.bed-insta-break",
           RESPAWN_TIME = "enable-modifiers.respawn-time",
           EVENTS_TIME = "enable-modifiers.events-time",
           MAX_TEAM_UPGRADES = "enable-modifiers.max-team-upgrades",
           HEALTH_BUFF = "enable-modifiers.health-buff",
           ALLOW_MAP_BREAK = "enable-modifiers.allow-map-break",
           NO_DIAMONDS = "enable-modifiers.no-diamonds",
           NO_EMERALDS = "enable-modifiers.no-emeralds",
           GAMEMODE_CHANGER = "enable-modifiers.gamemode-changer",

           SETTINGS_ROWS = "menus.settings.rows",

           ONE_HIT_ONE_HILL_MATERIAL = "menus.settings.contents.one-hit-one-kill.material",
           ONE_HIT_ONE_HILL_POSITION = "menus.settings.contents.one-hit-one-kill.position",

           SPEED_MATERIAL = "menus.settings.contents.speed.material",
           SPEED_POSITION = "menus.settings.contents.speed.position",
           SPEED_MENU_ROWS = "menus.submenus.speed.rows",
           SPEED_BACK_MATERIAL = "menus.submenus.speed.back-item.material",
           SPEED_BACK_POSITION = "menus.submenus.speed.back-item.position",
           SPEED_LEVEL_I_MATERIAL = "menus.submenus.speed.level-1.material",
           SPEED_LEVEL_I_POSITION = "menus.submenus.speed.level-1.position",
           SPEED_LEVEL_II_MATERIAL = "menus.submenus.speed.level-1.material",
           SPEED_LEVEL_II_POSITION = "menus.submenus.speed.level-2.position",
           SPEED_LEVEL_III_MATERIAL = "menus.submenus.speed.level-3.material",
           SPEED_LEVEL_III_POSITION = "menus.submenus.speed.level-3.position",
           SPEED_LEVEL_IV_MATERIAL = "menus.submenus.speed.level-4.material",
           SPEED_LEVEL_IV_POSITION = "menus.submenus.speed.level-4.position",

           LONG_JUMP_MATERIAL = "menus.settings.contents.long-jump.material",
           LONG_JUMP_POSITION = "menus.settings.contents.long-jump.position",

           BED_INSTA_BREAK_MATERIAL = "menus.settings.contents.bed-insta-break.material",
           BED_INSTA_BREAK_POSITION = "menus.settings.contents.bed-insta-break.position",

           RESPAWN_TIME_MATERIAL = "menus.settings.contents.respawn-time.material",
           RESPAWN_TIME_POSITION = "menus.settings.contents.respawn-time.position",
           RESPAWN_TIME_MENU_ROWS = "menus.submenus.respawn-time.rows",
           RESPAWN_TIME_BACK_MATERIAL = "menus.submenus.respawn-time.back-item.material",
           RESPAWN_TIME_BACK_POSITION = "menus.submenus.respawn-time.back-item.position",
           RESPAWN_TIME_LEVEL_I_MATERIAL = "menus.submenus.respawn-time.level-1.material",
           RESPAWN_TIME_LEVEL_I_POSITION = "menus.submenus.respawn-time.level-1.position",
           RESPAWN_TIME_LEVEL_II_MATERIAL = "menus.submenus.respawn-time.level-2.material",
           RESPAWN_TIME_LEVEL_II_POSITION = "menus.submenus.respawn-time.level-2.position",
           RESPAWN_TIME_LEVEL_III_MATERIAL = "menus.submenus.respawn-time.level-3.material",
           RESPAWN_TIME_LEVEL_III_POSITION = "menus.submenus.respawn-time.level-3.position",

           EVENTS_TIME_MATERIAL = "menus.settings.contents.events-time.material",
           EVENTS_TIME_POSITION = "menus.settings.contents.events-time.position",
           EVENTS_TIME_MENU_ROWS = "menus.submenus.events-time.rows",
           EVENTS_TIME_BACK_MATERIAL = "menus.submenus.events-time.back-item.material",
           EVENTS_TIME_BACK_POSITION = "menus.submenus.events-time.back-item.position",
           EVENTS_TIME_LEVEL_I_MATERIAL = "menus.submenus.events-time.level-1.material",
           EVENTS_TIME_LEVEL_I_POSITION = "menus.submenus.events-time.level-1.position",
           EVENTS_TIME_LEVEL_II_MATERIAL = "menus.submenus.events-time.level-2.material",
           EVENTS_TIME_LEVEL_II_POSITION = "menus.submenus.events-time.level-2.position",
           EVENTS_TIME_LEVEL_III_MATERIAL = "menus.submenus.events-time.level-3.material",
           EVENTS_TIME_LEVEL_III_POSITION = "menus.submenus.events-time.level-3.position",
           EVENTS_TIME_LEVEL_IV_MATERIAL = "menus.submenus.events-time.level-4.material",
           EVENTS_TIME_LEVEL_IV_POSITION = "menus.submenus.events-time.level-4.position",

           MAX_TEAM_UPGRADES_MATERIAL = "menus.settings.contents.max-team-upgrades.material",
           MAX_TEAM_UPGRADES_POSITION = "menus.settings.contents.max-team-upgrades.position",

           HEALTH_BUFF_MATERIAL = "menus.settings.contents.health-buff.material",
           HEALTH_BUFF_POSITION = "menus.settings.contents.health-buff.position",
           HEALTH_BUFF_MENU_ROWS = "menus.submenus.health-buff.rows",
           HEALTH_BUFF_BACK_MATERIAL = "menus.submenus.health-buff.back-item.material",
           HEALTH_BUFF_BACK_POSITION = "menus.submenus.health-buff.back-item.position",
           HEALTH_BUFF_LEVEL_I_MATERIAL = "menus.submenus.health-buff.level-1.material",
           HEALTH_BUFF_LEVEL_I_POSITION = "menus.submenus.health-buff.level-1.position",
           HEALTH_BUFF_LEVEL_II_MATERIAL = "menus.submenus.health-buff.level-2.material",
           HEALTH_BUFF_LEVEL_II_POSITION = "menus.submenus.health-buff.level-2.position",
           HEALTH_BUFF_LEVEL_III_MATERIAL = "menus.submenus.health-buff.level-3.material",
           HEALTH_BUFF_LEVEL_III_POSITION = "menus.submenus.health-buff.level-3.position",

           ALLOW_MAP_BREAK_MATERIAL = "menus.settings.contents.allow-map-break.material",
           ALLOW_MAP_BREAK_POSITION = "menus.settings.contents.allow-map-break.position",

           NO_DIAMONDS_MATERIAL = "menus.settings.contents.no-diamonds.material",
           NO_DIAMONDS_POSITION = "menus.settings.contents.no-diamonds.position",

           NO_EMERALDS_MATERIAL = "menus.settings.contents.no-emeralds.material",
           NO_EMERALDS_POSITION = "menus.settings.contents.no-emeralds.position",

           GAMEMODE_CHANGER_MATERIAL = "menus.settings.contents.gamemode-changer.material",
           GAMEMODE_CHANGER_POSITION = "menus.settings.contents.gamemode-changer.position",

           BACK_MATERIAL = "menus.settings.contents.back-item.material",
           BACK_POSITION = "menus.settings.contents.back-item.position",

           MATERIAL = "private-games-item.material",
           POSITION = "private-games-item.position",
           ENCHANTED = "private-games-item.enchanted";
}
