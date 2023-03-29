package me.notlewx.pgames.config;

import org.bukkit.configuration.file.YamlConfiguration;
import com.andrei1058.bedwars.api.language.Language;

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
                    yml.addDefault(MAIN_MENU_NAME, "&7Private game settings");
                    yml.addDefault(NOT_IN_PARTY, "&cYou are not in a party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aYou've enabled private games");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} has enabled private games");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cYou've disabled private games disabled");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} has disabled private games");
                    break;
                case "es":
                    yml.addDefault(MAIN_MENU_NAME, "&7Ajustes de partida privada");
                    yml.addDefault(NOT_IN_PARTY, "&cNo estas en una party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aPartida privada habilitada");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ha habilitado la partida privada habilitada");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cPartida privada deshabilitada");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} ha deshabilitado la partida privada");
                    break;
            }
            l.getYml().options().copyDefaults(true);
            l.save();
        }
    }
    public static final String
            MAIN_MENU_NAME = PATH + "menu.menu-name",
            NOT_IN_PARTY = PATH + "not-in-party",
            PRIVATE_GAME_ENABLED = PATH + "game.enabled",
            PRIVATE_GAME_ENABLED_OTHERS = PATH + "game.enabled-others",
            PRIVATE_GAME_DISABLED = PATH + "game.disabled",
            PRIVATE_GAME_DISABLED_OTHERS = PATH + "game.disabled-others",
            ONE_HIT_ONE_KILL_MEANING = PATH + "meanings.one-hit-one-kill",
            SPEED_MEANING = PATH + "meanings.speed",
            LEVEL_I_MEANING = PATH + "meanings.level1",
            LEVEL_II_MEANING = PATH + "meanings.level2",
            LEVEL_III_MEANING = PATH + "meanings.level3",
            BED_INSTA_BREAK_MEANING = PATH + "meanings.bed-insta-break",
            LOW_GRAVITY_MEANING = PATH + "meanings.low-gravity",
            MAX_TEAM_UPGRADES_MEANING = PATH + "meanings.max-team-upgrades",
            ALLOW_MAP_BREAK = PATH + "meanings.allow-map-break",
            NO_DIAMONDS_MEANING = PATH + "meanings.no-diamonds",
            NO_EMERALDS_MEANING = PATH + "meanings.no-emeralds",
            RESPAWN_EVENT_TIME_MEANING = PATH + "meanings.respawn-event-time-meaning",
            RESPAWN_EVENT_TIME_LONG_MEANING = PATH + "meanings.respawn-event-time.long",
            RESPAWN_EVENT_TIME_SHORT_MEANING = PATH + "meanings.respawn-event-time.short",
            RESPAWN_EVENT_TIME_INSTANT_MEANING = PATH + "meanings.respawn-event-time.instant",
            HEALTH_BUFF_MEANING = PATH + "meanings.health-buff",
            EVENTS_TIME_MEANING = PATH + "meanings.events-time-meaning",
            EVENTS_TIME_NORMAL_MEANING = PATH + "meanings.events-time.normal",
            EVENTS_TIME_FAST_MEANING = PATH + "meanings.events-time.fast",
            EVENTS_TIME_FASTER_MEANING = PATH + "meanings.events-time.faster",
            BACK_MEANING = PATH + "meanings.back",
            ITEM_PRIVATE_GAME_NAME = PATH + "menu.item.name",
            ITEM_PRIVATE_GAME_LORE = PATH + "menu.item.lore";
}
