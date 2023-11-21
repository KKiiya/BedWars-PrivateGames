package me.notlewx.privategames.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import static me.notlewx.privategames.PrivateGames.isBedWarsServer;

public class MainConfig extends ConfigManager {
    public MainConfig(Plugin plugin, String name, String dir) {
        super(plugin, name, dir);
        YamlConfiguration yml = getYml();
        yml.options().header("PrivateGames Addon by Kiiya#9207");
        if (!isBedWarsServer) {
            yml.addDefault("database.host", "localhost");
            yml.addDefault("database.database", "cheese");
            yml.addDefault("database.username", "root");
            yml.addDefault("database.password", "password");
            yml.addDefault("database.port", 3306);
        }
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
        yml.addDefault(START_GAME, true);
        yml.addDefault(OPTIONS_ENABLE, true);
        yml.addDefault(OPTIONS_ALLOW_JOIN, true);
        yml.addDefault(OPTIONS_GENERATORS, true);
        yml.addDefault(OPTIONS_ENABLE_AUTOSTART, true);
        yml.addDefault(OPTIONS_ENABLE_PRIVATEGAMES, true);
        yml.addDefault(SETTINGS_ROWS, 6);
        yml.addDefault(ONE_HIT_ONE_KILL_MATERIAL, "DIAMOND_SWORD");
        yml.addDefault(ONE_HIT_ONE_KILL_POSITION, 10);
        yml.addDefault(ONE_HIT_ONE_KILL_ID, 0);
        yml.addDefault(ONE_HIT_ONE_KILL_HEAD_URL, "skin-url");
        yml.addDefault(HEALTH_BUFF_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_POSITION, 12);
        yml.addDefault(HEALTH_BUFF_ID, 0);
        yml.addDefault(HEALTH_BUFF_HEAD_URL, 0);
        yml.addDefault(LONG_JUMP_MATERIAL, "QUARTZ");
        yml.addDefault(LONG_JUMP_POSITION, 14);
        yml.addDefault(LONG_JUMP_ID, 0);
        yml.addDefault(LONG_JUMP_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_POSITION, 16);
        yml.addDefault(SPEED_ID, 0);
        yml.addDefault(SPEED_HEAD_URL, "skin-url");
        yml.addDefault(RESPAWN_TIME_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_POSITION, 20);
        yml.addDefault(RESPAWN_TIME_ID, 0);
        yml.addDefault(RESPAWN_TIME_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_POSITION, 22);
        yml.addDefault(EVENTS_TIME_ID, 0);
        yml.addDefault(EVENTS_TIME_HEAD_URL, "skin-url");
        yml.addDefault(NO_EMERALDS_MATERIAL, "EMERALD");
        yml.addDefault(NO_EMERALDS_POSITION, 24);
        yml.addDefault(NO_EMERALDS_ID, 0);
        yml.addDefault(NO_EMERALDS_HEAD_URL, "skin-url");
        yml.addDefault(NO_DIAMONDS_MATERIAL, "DIAMOND");
        yml.addDefault(NO_DIAMONDS_POSITION, 28);
        yml.addDefault(NO_DIAMONDS_ID, 0);
        yml.addDefault(NO_DIAMONDS_HEAD_URL, "skin-url");
        yml.addDefault(ALLOW_MAP_BREAK_MATERIAL, "GRASS");
        yml.addDefault(ALLOW_MAP_BREAK_POSITION, 30);
        yml.addDefault(ALLOW_MAP_BREAK_ID, 0);
        yml.addDefault(ALLOW_MAP_BREAK_HEAD_URL, "skin-url");
        yml.addDefault(BED_INSTA_BREAK_MATERIAL, "BED");
        yml.addDefault(BED_INSTA_BREAK_POSITION, 32);
        yml.addDefault(BED_INSTA_BREAK_ID, 0);
        yml.addDefault(BED_INSTA_BREAK_HEAD_URL, "skin-url");
        yml.addDefault(MAX_TEAM_UPGRADES_MATERIAL, "DIAMOND_BLOCK");
        yml.addDefault(MAX_TEAM_UPGRADES_POSITION, 34);
        yml.addDefault(MAX_TEAM_UPGRADES_ID, 0);
        yml.addDefault(MAX_TEAM_UPGRADES_HEAD_URL, "skin-url");
        yml.addDefault(GAMEMODE_CHANGER_MATERIAL, "REDSTONE_COMPARATOR");
        yml.addDefault(GAMEMODE_CHANGER_POSITION, 53);
        yml.addDefault(GAMEMODE_CHANGER_ID, 0);
        yml.addDefault(GAMEMODE_CHANGER_HEAD_URL, "skin-url");
        yml.addDefault(START_GAME_MATERIAL, "EYE_OF_ENDER");
        yml.addDefault(START_GAME_POSITION, 46);
        yml.addDefault(START_GAME_ID, 0);
        yml.addDefault(START_GAME_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_MATERIAL, "REDSTONE_COMPARATOR");
        yml.addDefault(OPTIONS_POSITION, 45);
        yml.addDefault(OPTIONS_ID, 0);
        yml.addDefault(OPTIONS_HEAD_URL, "skin-url");
        yml.addDefault(BACK_MATERIAL, "ARROW");
        yml.addDefault(BACK_POSITION, 49);
        yml.addDefault(BACK_ENABLE, true);
        yml.addDefault(BACK_COMMAND, "close");
        yml.addDefault(BACK_ID, 0);
        yml.addDefault(BACK_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_MENU_ROWS, 4);
        yml.addDefault(SPEED_BACK_MATERIAL, "ARROW");
        yml.addDefault(SPEED_BACK_POSITION, 31);
        yml.addDefault(SPEED_BACK_ID, 0);
        yml.addDefault(SPEED_BACK_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_LEVEL_I_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_I_POSITION, 10);
        yml.addDefault(SPEED_LEVEL_I_ID, 0);
        yml.addDefault(SPEED_LEVEL_I_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_LEVEL_II_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_II_POSITION, 12);
        yml.addDefault(SPEED_LEVEL_II_ID, 0);
        yml.addDefault(SPEED_LEVEL_II_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_LEVEL_III_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_III_POSITION, 14);
        yml.addDefault(SPEED_LEVEL_III_ID, 0);
        yml.addDefault(SPEED_LEVEL_III_HEAD_URL, "skin-url");
        yml.addDefault(SPEED_LEVEL_IV_MATERIAL, "RABBIT_FOOT");
        yml.addDefault(SPEED_LEVEL_IV_POSITION, 16);
        yml.addDefault(SPEED_LEVEL_IV_ID, 0);
        yml.addDefault(SPEED_LEVEL_IV_HEAD_URL, "skin-url");
        yml.addDefault(RESPAWN_TIME_MENU_ROWS, 4);
        yml.addDefault(RESPAWN_TIME_BACK_MATERIAL, "ARROW");
        yml.addDefault(RESPAWN_TIME_BACK_POSITION, 31);
        yml.addDefault(RESPAWN_TIME_BACK_ID, 0);
        yml.addDefault(RESPAWN_TIME_BACK_HEAD_URL, "skin-url");
        yml.addDefault(RESPAWN_TIME_LEVEL_I_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_I_POSITION, 11);
        yml.addDefault(RESPAWN_TIME_LEVEL_I_ID, 0);
        yml.addDefault(RESPAWN_TIME_LEVEL_I_HEAD_URL, "skin-url");
        yml.addDefault(RESPAWN_TIME_LEVEL_II_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_II_POSITION, 13);
        yml.addDefault(RESPAWN_TIME_LEVEL_II_ID, 0);
        yml.addDefault(RESPAWN_TIME_LEVEL_II_HEAD_URL, "skin-url");
        yml.addDefault(RESPAWN_TIME_LEVEL_III_MATERIAL, "PAPER");
        yml.addDefault(RESPAWN_TIME_LEVEL_III_POSITION, 15);
        yml.addDefault(RESPAWN_TIME_LEVEL_III_ID, 0);
        yml.addDefault(RESPAWN_TIME_LEVEL_III_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_MENU_ROWS, 4);
        yml.addDefault(EVENTS_TIME_BACK_MATERIAL, "ARROW");
        yml.addDefault(EVENTS_TIME_BACK_POSITION, 31);
        yml.addDefault(EVENTS_TIME_BACK_ID, 0);
        yml.addDefault(EVENTS_TIME_BACK_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_LEVEL_I_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_I_POSITION, 10);
        yml.addDefault(EVENTS_TIME_LEVEL_I_ID, 0);
        yml.addDefault(EVENTS_TIME_LEVEL_I_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_LEVEL_II_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_II_POSITION, 12);
        yml.addDefault(EVENTS_TIME_LEVEL_II_ID, 0);
        yml.addDefault(EVENTS_TIME_LEVEL_II_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_LEVEL_III_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_III_POSITION, 14);
        yml.addDefault(EVENTS_TIME_LEVEL_III_ID, 0);
        yml.addDefault(EVENTS_TIME_LEVEL_III_HEAD_URL, "skin-url");
        yml.addDefault(EVENTS_TIME_LEVEL_IV_MATERIAL, "BOOK");
        yml.addDefault(EVENTS_TIME_LEVEL_IV_POSITION, 16);
        yml.addDefault(EVENTS_TIME_LEVEL_IV_ID, 0);
        yml.addDefault(EVENTS_TIME_LEVEL_IV_HEAD_URL, "skin-url");
        yml.addDefault(HEALTH_BUFF_MENU_ROWS, 4);
        yml.addDefault(HEALTH_BUFF_BACK_MATERIAL, "ARROW");
        yml.addDefault(HEALTH_BUFF_BACK_POSITION, 31);
        yml.addDefault(HEALTH_BUFF_BACK_ID, 0);
        yml.addDefault(HEALTH_BUFF_BACK_HEAD_URL, "skin-url");
        yml.addDefault(HEALTH_BUFF_LEVEL_I_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_I_POSITION, 11);
        yml.addDefault(HEALTH_BUFF_LEVEL_I_ID, 0);
        yml.addDefault(HEALTH_BUFF_LEVEL_I_HEAD_URL, "skin-url");
        yml.addDefault(HEALTH_BUFF_LEVEL_II_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_II_POSITION, 13);
        yml.addDefault(HEALTH_BUFF_LEVEL_II_ID, 0);
        yml.addDefault(HEALTH_BUFF_LEVEL_II_HEAD_URL, "skin-url");
        yml.addDefault(HEALTH_BUFF_LEVEL_III_MATERIAL, "GOLDEN_APPLE");
        yml.addDefault(HEALTH_BUFF_LEVEL_III_POSITION, 15);
        yml.addDefault(HEALTH_BUFF_LEVEL_III_ID, 0);
        yml.addDefault(HEALTH_BUFF_LEVEL_III_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_MENU_ROWS, 4);
        yml.addDefault(OPTIONS_ALLOWJOIN_MATERIAL, "DIAMOND");
        yml.addDefault(OPTIONS_ALLOWJOIN_POSITION, 10);
        yml.addDefault(OPTIONS_ALLOWJOIN_ID, 0);
        yml.addDefault(OPTIONS_ALLOWJOIN_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_ENABLE_AUTOSTART_MATERIAL, "EYE_OF_ENDER");
        yml.addDefault(OPTIONS_ENABLE_AUTOSTART_POSITION, 12);
        yml.addDefault(OPTIONS_ENABLE_AUTOSTART_ID, 0);
        yml.addDefault(OPTIONS_ENABLE_AUTOSTART_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_ENABLE_PRIVATEGAMES_MATERIAL, "REDSTONE_BLOCK");
        yml.addDefault(OPTIONS_ENABLE_PRIVATEGAMES_POSITION, 14);
        yml.addDefault(OPTIONS_ENABLE_PRIVATEGAMES_ID, 0);
        yml.addDefault(OPTIONS_ENABLE_PRIVATEGAMES_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_GENERATORS_MATERIAL, "DIAMOND_BLOCK");
        yml.addDefault(OPTIONS_GENERATORS_POSITION, 16);
        yml.addDefault(OPTIONS_GENERATORS_ID, 0);
        yml.addDefault(OPTIONS_GENERATORS_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_GENERATORS_BACK_MATERIAL, "ARROW");
        yml.addDefault(OPTIONS_GENERATORS_BACK_POSITION, 31);
        yml.addDefault(OPTIONS_GENERATORS_BACK_ID, 0);
        yml.addDefault(OPTIONS_GENERATORS_BACK_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_GENERATOR_OPTIONS_BACK_MATERIAL, "ARROW");
        yml.addDefault(OPTIONS_GENERATOR_OPTIONS_BACK_POSITION, 0);
        yml.addDefault(OPTIONS_GENERATOR_OPTIONS_BACK_ID, 0);
        yml.addDefault(OPTIONS_GENERATOR_OPTIONS_BACK_HEAD_URL, "skin-url");
        yml.addDefault(OPTIONS_BACK_MATERIAL, "ARROW");
        yml.addDefault(OPTIONS_BACK_POSITION, 31);
        yml.addDefault(OPTIONS_BACK_ID, 0);
        yml.addDefault(OPTIONS_BACK_HEAD_URL, "skin-url");
        yml.addDefault(MATERIAL, "REDSTONE_BLOCK");
        yml.addDefault(ID, 0);
        yml.addDefault(HEAD_URL, "skin-url");
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
           OPTIONS_ENABLE = "enable-modifiers.options",
           OPTIONS_ENABLE_AUTOSTART = "enable-modifiers.options-expansion.enable-auto-start",
           OPTIONS_ALLOW_JOIN = "enable-modifiers.options-expansion.allow-join",
           OPTIONS_ENABLE_PRIVATEGAMES = "enable-modifiers.options-expansion.enable-privategames",
           OPTIONS_GENERATORS = "enable-modifiers.options-expansion.generator-options",
           START_GAME = "enable-modifiers.start-game",


           SETTINGS_ROWS = "menus.settings.rows",

           ONE_HIT_ONE_KILL_MATERIAL = "menus.settings.contents.one-hit-one-kill.material",
           ONE_HIT_ONE_KILL_POSITION = "menus.settings.contents.one-hit-one-kill.position",
                   ONE_HIT_ONE_KILL_ID = "menus.settings.contents.one-hit-one-kill.id",
                   ONE_HIT_ONE_KILL_HEAD_URL = "menus.settings.contents.one-hit-one-kill.head-url",

           SPEED_MATERIAL = "menus.settings.contents.speed.material",
           SPEED_POSITION = "menus.settings.contents.speed.position",
                   SPEED_ID = "menus.settings.contents.speed.id",
                   SPEED_HEAD_URL = "menus.settings.contents.speed.head-url",
           SPEED_MENU_ROWS = "menus.submenus.speed.rows",
           SPEED_BACK_MATERIAL = "menus.submenus.speed.back-item.material",
           SPEED_BACK_POSITION = "menus.submenus.speed.back-item.position",
                   SPEED_BACK_ID = "menus.submenus.speed.back-item.id",
                   SPEED_BACK_HEAD_URL = "menus.submenus.speed.back-item.head-url",
           SPEED_LEVEL_I_MATERIAL = "menus.submenus.speed.level-1.material",
           SPEED_LEVEL_I_POSITION = "menus.submenus.speed.level-1.position",
                   SPEED_LEVEL_I_ID = "menus.submenus.speed.level-1.id",
                   SPEED_LEVEL_I_HEAD_URL = "menus.submenus.speed.level-1.head-url",
           SPEED_LEVEL_II_MATERIAL = "menus.submenus.speed.level-1.material",
           SPEED_LEVEL_II_POSITION = "menus.submenus.speed.level-2.position",
                   SPEED_LEVEL_II_ID = "menus.submenus.speed.level-2.id",
                   SPEED_LEVEL_II_HEAD_URL = "menus.submenus.speed.level-2.head-url",
           SPEED_LEVEL_III_MATERIAL = "menus.submenus.speed.level-3.material",
           SPEED_LEVEL_III_POSITION = "menus.submenus.speed.level-3.position",
                   SPEED_LEVEL_III_ID = "menus.submenus.speed.level-3.id",
                   SPEED_LEVEL_III_HEAD_URL = "menus.submenus.speed.level-3.head-url",
           SPEED_LEVEL_IV_MATERIAL = "menus.submenus.speed.level-4.material",
           SPEED_LEVEL_IV_POSITION = "menus.submenus.speed.level-4.position",
                   SPEED_LEVEL_IV_ID = "menus.submenus.speed.level-4.id",
                   SPEED_LEVEL_IV_HEAD_URL = "menus.submenus.speed.level-4.head-url",

           LONG_JUMP_MATERIAL = "menus.settings.contents.long-jump.material",
           LONG_JUMP_POSITION = "menus.settings.contents.long-jump.position",
                   LONG_JUMP_ID = "menus.settings.contents.long-jump.id",
                   LONG_JUMP_HEAD_URL = "menus.settings.contents.long-jump.head-url",

           BED_INSTA_BREAK_MATERIAL = "menus.settings.contents.bed-insta-break.material",
           BED_INSTA_BREAK_POSITION = "menus.settings.contents.bed-insta-break.position",
                   BED_INSTA_BREAK_ID = "menus.settings.contents.bed-insta-break.id",
                   BED_INSTA_BREAK_HEAD_URL = "menus.settings.contents.bed-insta-break.head-url",

           RESPAWN_TIME_MATERIAL = "menus.settings.contents.respawn-time.material",
           RESPAWN_TIME_POSITION = "menus.settings.contents.respawn-time.position",
                   RESPAWN_TIME_ID = "menus.settings.contents.respawn-time.id",
                   RESPAWN_TIME_HEAD_URL = "menus.settings.contents.respawn-time.head-url",

           RESPAWN_TIME_MENU_ROWS = "menus.submenus.respawn-time.rows",
           RESPAWN_TIME_BACK_MATERIAL = "menus.submenus.respawn-time.back-item.material",
           RESPAWN_TIME_BACK_POSITION = "menus.submenus.respawn-time.back-item.position",
                   RESPAWN_TIME_BACK_ID = "menus.submenus.respawn-time.back-item.id",
                   RESPAWN_TIME_BACK_HEAD_URL = "menus.submenus.respawn-time.back-item.head-url",
           RESPAWN_TIME_LEVEL_I_MATERIAL = "menus.submenus.respawn-time.level-1.material",
           RESPAWN_TIME_LEVEL_I_POSITION = "menus.submenus.respawn-time.level-1.position",
                   RESPAWN_TIME_LEVEL_I_ID = "menus.submenus.respawn-time.level-1.id",
                   RESPAWN_TIME_LEVEL_I_HEAD_URL = "menus.submenus.respawn-time.level-1.head-url",
           RESPAWN_TIME_LEVEL_II_MATERIAL = "menus.submenus.respawn-time.level-2.material",
           RESPAWN_TIME_LEVEL_II_POSITION = "menus.submenus.respawn-time.level-2.position",
                   RESPAWN_TIME_LEVEL_II_ID = "menus.submenus.respawn-time.level-2.id",
                   RESPAWN_TIME_LEVEL_II_HEAD_URL = "menus.submenus.respawn-time.level-2.head-url",
           RESPAWN_TIME_LEVEL_III_MATERIAL = "menus.submenus.respawn-time.level-3.material",
           RESPAWN_TIME_LEVEL_III_POSITION = "menus.submenus.respawn-time.level-3.position",
                   RESPAWN_TIME_LEVEL_III_ID = "menus.submenus.respawn-time.level-3.id",
                   RESPAWN_TIME_LEVEL_III_HEAD_URL = "menus.submenus.respawn-time.level-3.head-url",

           EVENTS_TIME_MATERIAL = "menus.settings.contents.events-time.material",
           EVENTS_TIME_POSITION = "menus.settings.contents.events-time.position",
                   EVENTS_TIME_ID = "menus.settings.contents.back-item.id",
                   EVENTS_TIME_HEAD_URL = "menus.settings.contents.back-item.head-url",
           EVENTS_TIME_MENU_ROWS = "menus.submenus.events-time.rows",
           EVENTS_TIME_BACK_MATERIAL = "menus.submenus.events-time.back-item.material",
           EVENTS_TIME_BACK_POSITION = "menus.submenus.events-time.back-item.position",
                   EVENTS_TIME_BACK_ID = "menus.submenus.events-time.back-item.id",
                   EVENTS_TIME_BACK_HEAD_URL = "menus.submenus.events-time.back-item.head-url",
           EVENTS_TIME_LEVEL_I_MATERIAL = "menus.submenus.events-time.level-1.material",
           EVENTS_TIME_LEVEL_I_POSITION = "menus.submenus.events-time.level-1.position",
                   EVENTS_TIME_LEVEL_I_ID = "menus.submenus.events-time.level-1.id",
                   EVENTS_TIME_LEVEL_I_HEAD_URL = "menus.submenus.events-time.level-1.head-url",
           EVENTS_TIME_LEVEL_II_MATERIAL = "menus.submenus.events-time.level-2.material",
           EVENTS_TIME_LEVEL_II_POSITION = "menus.submenus.events-time.level-2.position",
                   EVENTS_TIME_LEVEL_II_ID = "menus.submenus.events-time.level-2.id",
                   EVENTS_TIME_LEVEL_II_HEAD_URL = "menus.submenus.events-time.level-2.head-url",
           EVENTS_TIME_LEVEL_III_MATERIAL = "menus.submenus.events-time.level-3.material",
           EVENTS_TIME_LEVEL_III_POSITION = "menus.submenus.events-time.level-3.position",
                   EVENTS_TIME_LEVEL_III_ID = "menus.submenus.events-time.level-3.id",
                   EVENTS_TIME_LEVEL_III_HEAD_URL = "menus.submenus.events-time.level-3.head-url",
           EVENTS_TIME_LEVEL_IV_MATERIAL = "menus.submenus.events-time.level-4.material",
           EVENTS_TIME_LEVEL_IV_POSITION = "menus.submenus.events-time.level-4.position",
                   EVENTS_TIME_LEVEL_IV_ID = "menus.submenus.events-time.level-4.id",
                   EVENTS_TIME_LEVEL_IV_HEAD_URL = "menus.submenus.events-time.level-4.head-url",

           MAX_TEAM_UPGRADES_MATERIAL = "menus.settings.contents.max-team-upgrades.material",
           MAX_TEAM_UPGRADES_POSITION = "menus.settings.contents.max-team-upgrades.position",
                   MAX_TEAM_UPGRADES_ID = "menus.settings.contents.max-team-upgrades.id",
                   MAX_TEAM_UPGRADES_HEAD_URL = "menus.settings.contents.max-team-upgrades.head-url",

           HEALTH_BUFF_MATERIAL = "menus.settings.contents.health-buff.material",
           HEALTH_BUFF_POSITION = "menus.settings.contents.health-buff.position",
                   HEALTH_BUFF_ID = "menus.settings.contents.health-buff.id",
                   HEALTH_BUFF_HEAD_URL = "menus.settings.contents.health-buff.head-url",

           HEALTH_BUFF_MENU_ROWS = "menus.submenus.health-buff.rows",

           HEALTH_BUFF_BACK_MATERIAL = "menus.submenus.health-buff.back-item.material",
           HEALTH_BUFF_BACK_POSITION = "menus.submenus.health-buff.back-item.position",
                   HEALTH_BUFF_BACK_ID = "menus.submenus.health-buff.back-item.id",
                   HEALTH_BUFF_BACK_HEAD_URL = "menus.submenus.health-buff.back-item.head-url",

           HEALTH_BUFF_LEVEL_I_MATERIAL = "menus.submenus.health-buff.level-1.material",
           HEALTH_BUFF_LEVEL_I_POSITION = "menus.submenus.health-buff.level-1.position",
                   HEALTH_BUFF_LEVEL_I_ID = "menus.submenus.health-buff.level-1.id",
                   HEALTH_BUFF_LEVEL_I_HEAD_URL = "menus.submenus.health-buff.level-1.head-url",
           HEALTH_BUFF_LEVEL_II_MATERIAL = "menus.submenus.health-buff.level-2.material",
           HEALTH_BUFF_LEVEL_II_POSITION = "menus.submenus.health-buff.level-2.position",
                   HEALTH_BUFF_LEVEL_II_ID = "menus.submenus.health-buff.level-2.id",
                   HEALTH_BUFF_LEVEL_II_HEAD_URL = "menus.submenus.health-buff.level-2.head-url",
           HEALTH_BUFF_LEVEL_III_MATERIAL = "menus.submenus.health-buff.level-3.material",
           HEALTH_BUFF_LEVEL_III_POSITION = "menus.submenus.health-buff.level-3.position",
                   HEALTH_BUFF_LEVEL_III_ID = "menus.submenus.health-buff.level-3.id",
                   HEALTH_BUFF_LEVEL_III_HEAD_URL = "menus.submenus.health-buff.level-3.head-url",

           OPTIONS_MENU_ROWS = "menus.submenus.options.rows",

           OPTIONS_ENABLE_AUTOSTART_MATERIAL = "menus.submenus.options.auto-start.material",
           OPTIONS_ENABLE_AUTOSTART_POSITION = "menus.submenus.options.auto-start.position",
                   OPTIONS_ENABLE_AUTOSTART_ID = "menus.submenus.options.auto-start.id",
                   OPTIONS_ENABLE_AUTOSTART_HEAD_URL = "menus.submenus.options.auto-start.head-url",

           OPTIONS_ALLOWJOIN_MATERIAL = "menus.submenus.options.allow-join.material",
           OPTIONS_ALLOWJOIN_POSITION = "menus.submenus.options.allow-join.position",
                   OPTIONS_ALLOWJOIN_ID = "menus.submenus.options.allow-join.id",
                   OPTIONS_ALLOWJOIN_HEAD_URL = "menus.submenus.options.allow-join.head-url",

           OPTIONS_ENABLE_PRIVATEGAMES_MATERIAL = "menus.submenus.options.enable-privategames.material",
           OPTIONS_ENABLE_PRIVATEGAMES_POSITION = "menus.submenus.options.enable-privategames.position",
                   OPTIONS_ENABLE_PRIVATEGAMES_ID = "menus.submenus.options.enable-privategames.id",
                   OPTIONS_ENABLE_PRIVATEGAMES_HEAD_URL = "menus.submenus.options.enable-privategames.head-url",

           OPTIONS_GENERATORS_MATERIAL = "menus.submenus.options.generators.material",
           OPTIONS_GENERATORS_POSITION = "menus.submenus.options.generators.position",
                   OPTIONS_GENERATORS_ID = "menus.submenus.options.generators.id",
                   OPTIONS_GENERATORS_HEAD_URL = "menus.submenus.options.generators.head-url",

                   OPTIONS_GENERATORS_BACK_MATERIAL = "menus.submenus.options.generators.back-material",
                   OPTIONS_GENERATORS_BACK_POSITION = "menus.submenus.options.generators.back-position",
                   OPTIONS_GENERATORS_BACK_ID = "menus.submenus.options.generators.back-id",
                   OPTIONS_GENERATORS_BACK_HEAD_URL = "menus.submenus.options.generators.back-head-url",

                   OPTIONS_GENERATOR_OPTIONS_BACK_MATERIAL = "menus.submenus.options.generator-config.back-material",
                   OPTIONS_GENERATOR_OPTIONS_BACK_POSITION = "menus.submenus.options.generator-config.back-position",
                   OPTIONS_GENERATOR_OPTIONS_BACK_ID = "menus.submenus.options.generator-config.back-id",
                   OPTIONS_GENERATOR_OPTIONS_BACK_HEAD_URL = "menus.submenus.options.generator-config.back-head-url",

           OPTIONS_BACK_MATERIAL = "menus.submenus.options.back-item.material",
           OPTIONS_BACK_POSITION = "menus.submenus.options.back-item.position",
                   OPTIONS_BACK_ID = "menus.submenus.options.back-item.id",
                   OPTIONS_BACK_HEAD_URL = "menus.submenus.options.back-item.head-url",

           ALLOW_MAP_BREAK_MATERIAL = "menus.settings.contents.allow-map-break.material",
           ALLOW_MAP_BREAK_POSITION = "menus.settings.contents.allow-map-break.position",
                   ALLOW_MAP_BREAK_ID = "menus.settings.contents.allow-map-break.id",
                   ALLOW_MAP_BREAK_HEAD_URL = "menus.settings.contents.allow-map-break.head-url",

           NO_DIAMONDS_MATERIAL = "menus.settings.contents.no-diamonds.material",
           NO_DIAMONDS_POSITION = "menus.settings.contents.no-diamonds.position",
                   NO_DIAMONDS_ID = "menus.settings.contents.no-diamonds.id",
                   NO_DIAMONDS_HEAD_URL = "menus.settings.contents.no-diamonds.head-url",

           NO_EMERALDS_MATERIAL = "menus.settings.contents.no-emeralds.material",
           NO_EMERALDS_POSITION = "menus.settings.contents.no-emeralds.position",
                   NO_EMERALDS_ID = "menus.settings.contents.no-emeralds.id",
                   NO_EMERALDS_HEAD_URL = "menus.settings.contents.no-emeralds.head-url",

           GAMEMODE_CHANGER_MATERIAL = "menus.settings.contents.gamemode-changer.material",
           GAMEMODE_CHANGER_POSITION = "menus.settings.contents.gamemode-changer.position",
                   GAMEMODE_CHANGER_ID = "menus.settings.contents.gamemode-changer.id",
                   GAMEMODE_CHANGER_HEAD_URL = "menus.settings.contents.gamemode-changer.head-url",

           OPTIONS_MATERIAL = "menus.settings.contents.options.material",
           OPTIONS_POSITION = "menus.settings.contents.options.position",
                   OPTIONS_ID = "menus.settings.contents.options.id",
                   OPTIONS_HEAD_URL = "menus.settings.contents.options.head-url",

           START_GAME_MATERIAL = "menus.settings.contents.start-game.material",
           START_GAME_POSITION = "menus.settings.contents.start-game.position",
                   START_GAME_ID = "menus.settings.contents.start-game.id",
                   START_GAME_HEAD_URL = "menus.settings.contents.start-game.head-url",

           BACK_MATERIAL = "menus.settings.contents.back-item.material",
           BACK_POSITION = "menus.settings.contents.back-item.position",
                    BACK_ENABLE = "menus.settings.contents.back-item.enable",
                    BACK_COMMAND = "menus.settings.contents.back-item.command",
                    BACK_ID = "menus.settings.contents.back-item.id",
                    BACK_HEAD_URL = "menus.settings.contents.back-item.head-url",

           MATERIAL = "private-games-item.material",
           ID = "private-games-item.id",
           HEAD_URL = "private-games-item.head-url",
           POSITION = "private-games-item.position",
           ENCHANTED = "private-games-item.enchanted";


}
