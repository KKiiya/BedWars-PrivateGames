package me.notlewx.pgames.config;

import org.bukkit.configuration.file.YamlConfiguration;
import com.andrei1058.bedwars.api.language.Language;

import java.util.Arrays;

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
                    yml.addDefault(MAIN_MENU_NAME, "&8Private game settings");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&cPrivate game settings");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Open this menu to configure your private game", "with up to 11 different options!"));
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cYou are not in a party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aYou've enabled private games");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} has enabled private games");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cYou've disabled private games disabled");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} has disabled private games");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cYou're not the owner of this party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cYou don't have permission to use this command!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cThe private game is already enabled!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cThe private game is already disabled!");
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
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Game Specific", "", "&7Modify player respawn time", "", "{state}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aEvents Time");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Game Specific", "", "&7Modify the time for events.", "", "{state}"));
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
                    break;
                case "es":
                    yml.addDefault(MAIN_MENU_NAME, "&8Ajustes de partida privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&cAjustes de partida privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Aquí puedes configurar tu partida privada", "con más de 11 ajustes diferentes!"));
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cNo estas en una party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aPartida privada habilitada");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ha habilitado la partida privada habilitada");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cPartida privada deshabilitada");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} ha deshabilitado la partida privada");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cNo eres el owner de esta party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cNo tienes permisos para usar este comando");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cLa partida privada ya esta habilitada!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cLa partida privada ya esta deshabilitada!");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aAtrás");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7Vuelve a jugar bedwars"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aUn golpe, una muerte");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Elige que items matan de", "&7un solo golpe", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aPotenciador de vida");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Potencia la vida de todos", "&7los jugadores", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aGravedad baja");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Experiencia la gravedad baja... o también", "&7conocido como salto elevado", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aVelocidad");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Toca ir rápido", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aTiempo de reaparición");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Específico para el juego", "", "&7Modifica el tiempo de reaparición", "", "{state}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aTiempo de Eventos");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Juego específico", "", "&7Modifica el tiempo de los eventos.", "", "{state}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aSin esmeraldas");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Específico para el juego", "", "&7Deshazte de esas esmeraldas", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aSin diamantes");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Juego específico", "", "&7Al infierno con esas cosas brillantes"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDeshabilitar protección de bloques");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Permite a los jugadores romper cualquier", "&7bloque, excluyendo bloques alrededor de", "&7los generadores y spawns", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aRotura instantánea");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Las camas se romperán con un solo golpe", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMejoras al máximo");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Juego específico", "", "&7Todos los equipos empezarán", "&7con sus mejoras al máximo", "", "{state}"));
                    break;
            }
            l.getYml().options().copyDefaults(true);
            l.save();
        }
    }
    public static final String
            MAIN_MENU_NAME = PATH + "menu.menu-name",
            MENU_BACK_ITEM_NAME = PATH + "menu.back-item.name",
            MENU_BACK_ITEM_LORE = PATH + "menu.back-item.lore",
            PRIVATE_GAME_MENU_ITEM_NAME = PATH + "menu.private-game-item.name",
            PRIVATE_GAME_MENU_ITEM_LORE = PATH + "menu.private-game-item.lore",
            PRIVATE_GAME_NOT_IN_PARTY = PATH + "not-in-party",
            PRIVATE_GAME_ENABLED = PATH + "game.enabled",
            PRIVATE_GAME_NOT_OWNER = PATH + "game.not-owner",
            PRIVATE_GAME_ENABLED_OTHERS = PATH + "game.enabled-others",
            PRIVATE_GAME_DISABLED = PATH + "game.disabled",
            PRIVATE_GAME_DISABLED_OTHERS = PATH + "game.disabled-others",
            PRIVATE_GAME_NO_PERMISSION = PATH + "game.no-permission",
            PRIVATE_GAME_ALREADY_ENABLED = PATH + "game.already-enabled",
            PRIVATE_GAME_ALREADY_DISABLED = PATH + "game.already-disabled",
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
            ITEM_PRIVATE_GAME_LORE = PATH + "menu.item.lore",

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

            ITEM_SUBMENU_SPEED_I_NAME = PATH + "menu.submenu.speed.level1.name",
            ITEM_SUBMENU_SPEED_I_LORE = PATH + "menu.submenu.speed.level1.lore",
            ITEM_SUBMENU_SPEED_II_NAME = PATH + "menu.submenu.speed.level2.name",
            ITEM_SUBMENU_SPEED_II_LORE = PATH + "menu.submenu.speed.level2.lore",
            ITEM_SUBMENU_SPEED_III_NAME = PATH + "menu.submenu.speed.level3.name",
            ITEM_SUBMENU_SPEED_III_LORE = PATH + "menu.submenu.speed.level3.lore",

            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_I_NAME = PATH + "menu.submenu.speed.level1.name",
            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_I_LORE = PATH + "menu.submenu.speed.level1.lore",
            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_II_NAME = PATH + "menu.submenu.speed.level2.name",
            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_II_LORE = PATH + "menu.submenu.speed.level2.lore",
            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_III_NAME = PATH + "menu.submenu.speed.level3.name",
            ITEM_SUBMENU_HEALTH_BUFF_LEVEL_III_LORE = PATH + "menu.submenu.speed.level3.lore",

            ITEM_SUBMENU_RESPAWN_EVENT_TIME_I_NAME = PATH + "menu.submenu.speed.level1.name",
            ITEM_SUBMENU_RESPAWN_EVENT_TIME_I_LORE = PATH + "menu.submenu.speed.level1.lore",
            ITEM_SUBMENU_RESPAWN_EVENT_TIME_II_NAME = PATH + "menu.submenu.speed.level2.name",
            ITEM_SUBMENU_RESPAWN_EVENT_TIME_II_LORE = PATH + "menu.submenu.speed.level2.lore",
            ITEM_SUBMENU_RESPAWN_EVENT_TIME_III_NAME = PATH + "menu.submenu.speed.level3.name",
            ITEM_SUBMENU_RESPAWN_EVENT_TIME_III_LORE = PATH + "menu.submenu.speed.level3.lore",

            ITEM_SUBMENU_EVENTS_TIME_LEVEL_I_NAME = PATH + "menu.submenu.speed.level1.name",
            ITEM_SUBMENU_EVENTS_TIME_LEVEL_I_LORE = PATH + "menu.submenu.speed.level1.lore",
            ITEM_SUBMENU_EVENTS_TIME_LEVEL_II_NAME = PATH + "menu.submenu.speed.level2.name",
            ITEM_SUBMENU_EVENTS_TIME_LEVEL_II_LORE = PATH + "menu.submenu.speed.level2.lore",
            ITEM_SUBMENU_EVENTS_TIME_LEVEL_III_NAME = PATH + "menu.submenu.speed.level3.name",
            ITEM_SUBMENU_EVENTS_TIME_LEVEL_III_LORE = PATH + "menu.submenu.speed.level3.lore";
}
