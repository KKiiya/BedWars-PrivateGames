package me.notlewx.privategames.config.bedwars2023;

import com.tomkeuper.bedwars.api.language.Language;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.Arrays;

import static me.notlewx.privategames.PrivateGames.mainConfig;

public class MessagesData {
    public final static String PATH = "addons.private-games.";

    public MessagesData() {
        setupMessages();
    }

    public void setupMessages() {
        for (Language l : Language.getLanguages()) {
            YamlConfiguration yml = l.getYml();
            switch (l.getIso()) {
                default:
                    yml.addDefault(HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eHelp",
                            "",
                            "&7- &e/pg gui &7- &aOpen the private games menu",
                            "&7- &e/pg enable &7- &aEnable private games",
                            "&7- &e/pg disable &7- &aDisable private games",
                            "&7- &e/pg join <player> &7- &aJoin to the private game of a player",
                            "&7- &e/pg leave &7- &aLeave the private game of a player",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(ADMIN_HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAdmin Help",
                            "",
                            "&7- &e/pg reload &7- &aReload the messages",
                            "&7- &e/pg enable admin &7- &aEnable the private games only for you (party too)",
                            "&7- &e/pg disable admin &7- &aDisable the private games only for you (party too)",
                            "&8&m-----------------------------------------------------"

                    ));
                    yml.addDefault(MAIN_MENU_NAME, "&8Private game settings");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aPrivate Game Settings");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Open this menu to configure your private game", "&7with up to 11 different options!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSelected!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClick to Select!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cYou are not in a party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aYou've enabled private games");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} has enabled private games");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cYou've disabled private games");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} has disabled private games");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cYou're not the owner of this party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cYou don't have permission to use this command!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cThe private game is already enabled!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cThe private game is already disabled!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cCouldnt join to the private game of this player!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cYou cant do this in game!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lhas enabled private game modifiers!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7Go back to your bedwars gameplay"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aOne hit, one kill");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Select which items should", "&7instantly kill enemies", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aHealth Buff");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Buff all players health", "", "&aCurrently Selected: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aLow Gravity");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Experience low gravity... also", "&7known as jump boost", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aSpeed");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Gotta go fast!", "", "&aCurrently Selected: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aRespawn Time");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Game Specific", "", "&7Modify player respawn time", "", "&aCurrently selected: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aEvents Time");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Game Specific", "", "&7Modify the time for events.", "", "&aCurrently selected: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aNo Emeralds");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Game Specific", "", "&7Get rif of em hasty emeralds  ", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aNo Diamonds");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Game Specific", "", "&7To hell with those shiny things", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDisable Block Protection");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Game Specific", "", "&7Allow players to break any", "&7blocks, excluding blocks around", "&7generators and spawns.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aBed Instabreak");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Game Specific", "", "&7Beds break with a single punch", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMax Team Upgrades");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Game Specific", "", "&7All teams will start with maxed", "&7out team upgrade", "", "{state}"));
                    yml.addDefault(ITEM_OPTIONS_NAME, "&aOptions");
                    yml.addDefault(ITEM_OPTIONS_LORE, Arrays.asList("&7Click here to open arena options", "", "&7On this menu you can configure", "&7arena attributes and your settings", "", "&eClick to open!"));
                    yml.addDefault(ITEM_START_NAME, "&aStart");
                    yml.addDefault(ITEM_START_LORE, Arrays.asList("&7Click here to start the game", "", "&7You can start the game if you are", "&7the owner of the party", "", "&eClick to start!"));
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_NAME, "&aGamemode Changer");
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_LORE, Arrays.asList("&7Click here to change the gamemode", "", "&7You can change the gamemode if you are", "&7the owner of the party", "", "&7Current: &a{state}", "&7Default: &e{default}", "", "&cRight Click to set the default Game Mode", "&eLeft Click to change!"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Speed");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aNo Speed");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aSpeed I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aSpeed II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aSpeed III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Health Buff");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aNormal Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aDouble Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aTriple Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Events Time");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Slower");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normal");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Fast");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Faster");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Respawn Time");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_NAME, "&bGenerators");
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_LORE, Arrays.asList("&7Click here to open arena generators", "", "&7Configure generator attributes here", "", "&eClick to open!"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aEnabled");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDisabled");
                    yml.addDefault(NO_SPEED_MEANING, "&7No speed");
                    yml.addDefault(SPEED_I_MEANING, "&7Speed I");
                    yml.addDefault(SPEED_II_MEANING, "&7Speed II");
                    yml.addDefault(SPEED_III_MEANING, "&7Speed III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Normal Health");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Double Health");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Triple Health");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 Second");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 Seconds");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 Seconds");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Slow");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Normal");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Fast");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Faster");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7One Hit One Kill");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Bed InstaBreak");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Low Gravity");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Max Team Upgrades");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Allow Map Break");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7No Diamonds");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7No Emeralds");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Health Buff");
                    yml.addDefault(SPEED_MEANING, "&7Speed");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Events Time");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Respawn Time");
                    yml.addDefault(SUBMENU_GAMEMODE_CHANGER_TITLE, "&8Gamemode Changer");
                    yml.addDefault(SUBMENU_OPTIONS_TITLE, "Options");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME, "&aEnable AutoStart");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE, Arrays.asList("&7Enable the auto start of the game", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME, "&aAllow Others to Join");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE, Arrays.asList("&7Allow others to join your game", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME, "&aEnable Private Games");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE, Arrays.asList("&7Enable private games", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_BACK_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_OPTIONS_BACK_LORE, Arrays.asList("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_TITLE, "Arena generators");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_ITEM_LORE, Arrays.asList("&7Team: {team}", "&7Location: {location}", "&7Amount: {amount}", "&7Delay: {delay}",  "§7Spawn Limit: {spawnLimit}"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_TITLE, "Generator options");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the options menu"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE, Arrays.asList("&7Go Back to the generators menu"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aEnabled");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDisabled");
                    yml.addDefault(MENU_CLICK_TO_START_MEANING, "&eClick to start!");
                    yml.addDefault(MENU_STARTING_MEANING, "&aStarting...");

                    if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu") != null) {
                        for (String p : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu").getKeys(false)) {
                            for (String group : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + p + ".modes").getKeys(false)) {
                                if (group.equals("back-item")) {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&aGo Back");
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", Arrays.asList("&7Go back to the settings menu"));
                                } else {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&a" + group);
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", Arrays.asList("&7Click here to change the gamemode", "", "&7You can change the gamemode if you are", "&7the owner of the party", "", "&eClick to change!"));
                                }
                            }
                        }
                    }
                    break;
            }
            l.getYml().options().copyDefaults(true);
            l.save();
        }
    }
    public static final String
            MAIN_MENU_NAME = PATH + "menu.menu-name",
            MENU_SELECTED_MEANING = PATH + "menu.selected-meaning",
            PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER = PATH + "game.scoreboard-placeholder",
            MENU_CLICK_TO_SELECT_MEANING = PATH + "menu.click-to-select-meaning",
            MENU_CLICK_TO_START_MEANING = PATH + "menu.click-to-start-meaning",
            MENU_STARTING_MEANING = PATH + "menu.starting-meaning",
            MENU_BACK_ITEM_NAME = PATH + "menu.back-item.name",
            MENU_BACK_ITEM_LORE = PATH + "menu.back-item.lore",
            PRIVATE_GAME_MENU_ITEM_NAME = PATH + "menu.private-game-item.name",
            PRIVATE_GAME_MENU_ITEM_LORE = PATH + "menu.private-game-item.lore",
            PRIVATE_GAME_NOT_IN_PARTY = PATH + "game.not-in-party",
            PRIVATE_GAME_ENABLED = PATH + "game.enabled",
            PRIVATE_GAME_NOT_OWNER = PATH + "game.not-owner",
            PRIVATE_GAME_ENABLED_OTHERS = PATH + "game.enabled-others",
            PRIVATE_GAME_DISABLED = PATH + "game.disabled",
            PRIVATE_GAME_ENABLED_MODIFIERS = PATH + "game.modifiers.message",
            PRIVATE_GAME_MODIFIERS_FORMAT = PATH + "game.modifiers.modifier-format",
            PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT = PATH + "game.modifiers.modifier-with-option-format",
            PRIVATE_GAME_DISABLED_OTHERS = PATH + "game.disabled-others",
            PRIVATE_GAME_NO_PERMISSION = PATH + "game.no-permission",
            PRIVATE_GAME_ALREADY_ENABLED = PATH + "game.already-enabled",
            PRIVATE_GAME_ALREADY_DISABLED = PATH + "game.already-disabled",
            PRIVATE_GAME_COULDNT_JOIN = PATH + "game.couldnt-join",
            PRIVATE_GAME_CANT_IN_GAME = PATH + "game.cant-in-game",
            HELP_MESSAGE = PATH + "help-message",
            ADMIN_HELP_MESSAGE = PATH + "admin-help-message",
            NO_SPEED_MEANING = PATH + "meanings.speed.no-speed",
            SPEED_MEANING = PATH + "meanings.speed.normal",
            SPEED_I_MEANING = PATH + "meanings.speed.speed1",
            SPEED_II_MEANING = PATH + "meanings.speed.speed2",
            SPEED_III_MEANING = PATH + "meanings.speed.speed3",
            ONE_HIT_ONE_KILL_MEANING = PATH + "meanings.one-hit-one-kill",
            BED_INSTA_BREAK_MEANING = PATH + "meanings.bed-insta-break",
            LOW_GRAVITY_MEANING = PATH + "meanings.low-gravity",
            MAX_TEAM_UPGRADES_MEANING = PATH + "meanings.max-team-upgrades",
            ALLOW_MAP_BREAK_MEANING = PATH + "meanings.allow-map-break",
            NO_DIAMONDS_MEANING = PATH + "meanings.no-diamonds",
            NO_EMERALDS_MEANING = PATH + "meanings.no-emeralds",
            HEALTH_BUFF_MEANING = PATH + "meanings.health-buff",
            RESPAWN_EVENT_TIME_MEANING = PATH + "meanings.respawn-event-time.meaning",
            RESPAWN_EVENT_TIME_I_MEANING = PATH + "meanings.respawn-event-time.1second",
            RESPAWN_EVENT_TIME_II_MEANING = PATH + "meanings.respawn-event-time.5seconds",
            RESPAWN_EVENT_TIME_III_MEANING = PATH + "meanings.respawn-event-time.10seconds",
            NORMAL_HEALTH_MEANING = PATH + "meanings.health.normal",
            DOUBLE_HEALTH_MEANING = PATH + "meanings.health.double",
            TRIPLE_HEALTH_MEANING = PATH + "meanings.health.triple",
            EVENTS_TIME_MEANING = PATH + "meanings.events-time.meaning",
            EVENTS_TIME_SLOWER_MEANING = PATH + "meanings.events-time.slower",
            EVENTS_TIME_NORMAL_MEANING = PATH + "meanings.events-time.normal",
            EVENTS_TIME_FAST_MEANING = PATH + "meanings.events-time.fast",
            EVENTS_TIME_FASTER_MEANING = PATH + "meanings.events-time.faster",

    ITEM_SPEED_NAME = PATH + "menu.speed.name",
            ITEM_SPEED_LORE = PATH + "menu.speed.lore",

    ITEM_ONE_HIT_ONE_KILL_NAME = PATH + "menu.one-hit-one-kill.name",
            ITEM_ONE_HIT_ONE_KILL_LORE = PATH + "menu.one-hit-one-kill.lore",

    ITEM_BED_INSTA_BREAK_NAME = PATH + "menu.bed-insta-break.name",
            ITEM_BED_INSTA_BREAK_LORE = PATH + "menu.bed-insta-break.lore",

    ITEM_LOW_GRAVITY_NAME = PATH + "menu.low-gravity.name",
            ITEM_LOW_GRAVITY_LORE = PATH + "menu.low-gravity.lore",

    ITEM_MAX_TEAM_UPGRADES_NAME = PATH + "menu.max-team-upgrades.name",
            ITEM_MAX_TEAM_UPGRADES_LORE = PATH + "menu.max-team-upgrades.lore",

    ITEM_NO_DIAMONDS_NAME = PATH + "menu.no-diamonds.name",
            ITEM_NO_DIAMONDS_LORE = PATH + "menu.no-diamonds.lore",

    ITEM_NO_EMERALDS_NAME = PATH + "menu.no-emeralds.name",
            ITEM_NO_EMERALDS_LORE = PATH + "menu.no-emeralds.lore",

    ITEM_RESPAWN_EVENT_TIME_NAME = PATH + "menu.respawn-event-time.name",
            ITEM_RESPAWN_EVENT_TIME_LORE = PATH + "menu.respawn-event-time.lore",

    ITEM_HEALTH_BUFF_LEVEL_NAME = PATH + "menu.health-buff-level.name",
            ITEM_HEALTH_BUFF_LEVEL_LORE = PATH + "menu.health-buff-level.lore",

    ITEM_EVENTS_TIME_LEVEL_NAME = PATH + "menu.events-time-level.name",
            ITEM_EVENTS_TIME_LEVEL_LORE = PATH + "menu.events-time-level.lore",

    ITEM_ALLOW_MAP_BREAK_NAME = PATH + "menu.allow-map-break.name",
            ITEM_ALLOW_MAP_BREAK_LORE = PATH + "menu.allow-map-break.lore",

    ITEM_OPTIONS_NAME = PATH + "menu.options.name",
            ITEM_OPTIONS_LORE = PATH + "menu.options.lore",

    ITEM_START_NAME = PATH + "menu.start-game.name",
            ITEM_START_LORE = PATH + "menu.start-game.lore",

    ITEM_GAMEMODE_CHANGER_NAME = PATH + "menu.gamemode-changer.name",
            ITEM_GAMEMODE_CHANGER_LORE = PATH + "menu.gamemode-changer.lore",

    SUBMENU_SPEED_NAME = PATH + "menu.submenu.speed.name",
            ITEM_SUBMENU_SPEED_I_NAME = PATH + "menu.submenu.speed.level1.name",
            ITEM_SUBMENU_SPEED_I_LORE = PATH + "menu.submenu.speed.level1.lore",
            ITEM_SUBMENU_SPEED_II_NAME = PATH + "menu.submenu.speed.level2.name",
            ITEM_SUBMENU_SPEED_II_LORE = PATH + "menu.submenu.speed.level2.lore",
            ITEM_SUBMENU_SPEED_III_NAME = PATH + "menu.submenu.speed.level3.name",
            ITEM_SUBMENU_SPEED_III_LORE = PATH + "menu.submenu.speed.level3.lore",
            ITEM_SUBMENU_SPEED_IV_NAME = PATH + "menu.submenu.speed.level4.name",
            ITEM_SUBMENU_SPEED_IV_LORE = PATH + "menu.submenu.speed.level4.lore",
            SUBMENU_SPEED_BACK_ITEM_NAME = PATH + "menu.submenu.speed.back-item.name",
            SUBMENU_SPEED_BACK_ITEM_LORE = PATH + "menu.submenu.speed.back-item.lore",

    SUBMENU_HEALTH_BUFF_NAME = PATH + "menu.submenu.health-buff.name",
            ITEM_SUBMENU_HEALTH_BUFF_I_NAME = PATH + "menu.submenu.health-buff.level1.name",
            ITEM_SUBMENU_HEALTH_BUFF_I_LORE = PATH + "menu.submenu.health-buff.level1.lore",
            ITEM_SUBMENU_HEALTH_BUFF_II_NAME = PATH + "menu.submenu.health-buff.level2.name",
            ITEM_SUBMENU_HEALTH_BUFF_II_LORE = PATH + "menu.submenu.health-buff.level2.lore",
            ITEM_SUBMENU_HEALTH_BUFF_III_NAME = PATH + "menu.submenu.health-buff.level3.name",
            ITEM_SUBMENU_HEALTH_BUFF_III_LORE = PATH + "menu.submenu.health-buff.level3.lore",
            SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME = PATH + "menu.submenu.health-buff.back-item.name",
            SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE = PATH + "menu.submenu.health-buff.back-item.lore",

    SUBMENU_RESPAWN_TIME_NAME = PATH + "menu.submenu.respawn-time.name",
            ITEM_SUBMENU_RESPAWN_TIME_I_NAME = PATH + "menu.submenu.respawn-time.level1.name",
            ITEM_SUBMENU_RESPAWN_TIME_I_LORE = PATH + "menu.submenu.respawn-time.level1.lore",
            ITEM_SUBMENU_RESPAWN_TIME_II_NAME = PATH + "menu.submenu.respawn-time.level2.name",
            ITEM_SUBMENU_RESPAWN_TIME_II_LORE = PATH + "menu.submenu.respawn-time.level2.lore",
            ITEM_SUBMENU_RESPAWN_TIME_III_NAME = PATH + "menu.submenu.respawn-time.level3.name",
            ITEM_SUBMENU_RESPAWN_TIME_III_LORE = PATH + "menu.submenu.respawn-time.level3.lore",
            SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME = PATH + "menu.submenu.respawn-time.back-item.name",
            SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE = PATH + "menu.submenu.respawn-time.back-item.lore",

    SUBMENU_EVENTS_TIME_NAME = PATH + "menu.submenu.events-time.name",
            ITEM_SUBMENU_EVENTS_TIME_I_NAME = PATH + "menu.submenu.events-time.level1.name",
            ITEM_SUBMENU_EVENTS_TIME_I_LORE = PATH + "menu.submenu.events-time.level1.lore",
            ITEM_SUBMENU_EVENTS_TIME_II_NAME = PATH + "menu.submenu.events-time.level2.name",
            ITEM_SUBMENU_EVENTS_TIME_II_LORE = PATH + "menu.submenu.events-time.level2.lore",
            ITEM_SUBMENU_EVENTS_TIME_III_NAME = PATH + "menu.submenu.events-time.level3.name",
            ITEM_SUBMENU_EVENTS_TIME_III_LORE = PATH + "menu.submenu.events-time.level3.lore",
            ITEM_SUBMENU_EVENTS_TIME_IV_NAME = PATH + "menu.submenu.events-time.level4.name",
            ITEM_SUBMENU_EVENTS_TIME_IV_LORE = PATH + "menu.submenu.events-time.level4.lore",
            SUBMENU_EVENTS_TIME_BACK_ITEM_NAME = PATH + "menu.submenu.events-time.back-item.name",
            SUBMENU_EVENTS_TIME_BACK_ITEM_LORE = PATH + "menu.submenu.events-time.back-item.lore",

    SUBMENU_GAMEMODE_CHANGER_TITLE = PATH + "menu.submenu.gamemode-changer.title",

    SUBMENU_OPTIONS_TITLE = PATH + "menu.submenu.options.title",
        SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME = PATH + "menu.submenu.options.auto-start.name",
        SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE = PATH + "menu.submenu.options.auto-start.lore",
        SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME = PATH + "menu.submenu.options.allow-join.name",
        SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE = PATH + "menu.submenu.options.allow-join.lore",
        SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME = PATH + "menu.submenu.options.private-games.name",
        SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE = PATH + "menu.submenu.options.private-games.lore",
        SUBMENU_OPTIONS_MEANING_ENABLED = PATH + "menu.submenu.options.enabled-meaning",
        SUBMENU_OPTIONS_MEANING_DISABLED = PATH + "menu.submenu.options.disabled-meaning",
        SUBMENU_OPTIONS_GENERATORS_NAME = PATH + "menu.submenu.options.generators.name",
        SUBMENU_OPTIONS_GENERATORS_LORE = PATH + "menu.submenu.options.generators.lore",
        SUBMENU_OPTIONS_BACK_NAME = PATH + "menu.submenu.options.back-item.name",
        SUBMENU_OPTIONS_BACK_LORE = PATH + "menu.submenu.options.back-item.lore",

    SUBMENU_GENERATORS_OPTIONS_TITLE = PATH + "menu.submenu.generators.title",
            SUBMENU_GENERATOR_OPTIONS_TITLE = PATH + "menu.submenu.generators.config.title",
            SUBMENU_GENERATOR_OPTIONS_ITEM_LORE = PATH + "menu.submenu.generators.config.gen-item.lore",
            SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME = PATH + "menu.submenu.generators.config.content.back-item.name",
            SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE = PATH + "menu.submenu.generators.config.content.back-item.lore",

            SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME = PATH + "menu.submenu.generator-settings.content.back-item.name",

            SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE = PATH + "menu.submenu.generator-settings.content.back-item.lore";
}
