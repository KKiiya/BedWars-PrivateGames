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
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSelected!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClick to Select!");
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
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "x0.5 - Slow");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "x1 - Normal");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "x2 - Faster");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "x4 - Faster");
                    break;
                case "es":
                    yml.addDefault(MAIN_MENU_NAME, "&8Ajustes de partida privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&cAjustes de partida privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Aquí puedes configurar tu partida privada", "&7con más de 11 ajustes diferentes!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSeleccionado!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClick para seleccionar!");
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
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Específico para el juego", "", "&7Modifica el tiempo de reaparición", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aTiempo de Eventos");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Juego específico", "", "&7Modifica el tiempo de los eventos.", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aSin esmeraldas");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Específico para el juego", "", "&7Deshazte de esas esmeraldas", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aSin diamantes");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Juego específico", "", "&7Al infierno con esas cosas brillantes", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDeshabilitar protección de bloques");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Permite a los jugadores romper cualquier", "&7bloque, excluyendo bloques alrededor de", "&7los generadores y spawns", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aRotura instantánea");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Las camas se romperán con un solo golpe", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMejoras al máximo");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Juego específico", "", "&7Todos los equipos empezarán", "&7con sus mejoras al máximo", "", "{state}"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Velocidad");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aSin Velocidad");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aVelocidad I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aVelocidad II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aVelocidad III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aVolver");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7Vuelve al menú de ajustes"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Potenciador de vida");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aVida Normal");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aVida Doble");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aVida Triple");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aVolver");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7Vuelve al menú de ajustes"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Tiempo de los Eventos");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Más Lento");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normal");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Rápido");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - SuperRápido");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aVolver");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7Vuelve al menú de ajustes"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Tiempo de reaparición");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Segundo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Segundos");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Segundos");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aVolver");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7Vuelve al menú de ajustes"));
                    yml.addDefault(NO_SPEED_MEANING, "&7Sin velocidad");
                    yml.addDefault(SPEED_I_MEANING, "&7Velocidad I");
                    yml.addDefault(SPEED_II_MEANING, "&7Velocidad II");
                    yml.addDefault(SPEED_III_MEANING, "&7Velocidad III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Vida Normal");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Vida Doble");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Vida Triple");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 Segundo");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 Segundos");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 Segundos");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "x0.5 - Lento");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "x1 - Normal");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "x2 - Rápido");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "x4 - SuperRápido");
                    break;
            }
            l.getYml().options().copyDefaults(true);
            l.save();
        }
    }
    public static final String
            MAIN_MENU_NAME = PATH + "menu.menu-name",
            MENU_SELECTED_MEANING = PATH + "menu.selected-meaning",
            MENU_CLICK_TO_SELECT_MEANING = PATH + "menu.click-to-select-meaning",
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
            LEVEL_I_MEANING = PATH + "meanings.level1",
            LEVEL_II_MEANING = PATH + "meanings.level2",
            LEVEL_III_MEANING = PATH + "meanings.level3",
            LEVEL_IV_MEANING = PATH + "meanings.level4",
            NO_SPEED_MEANING = PATH + "meanings.speed.no-speed",
            SPEED_I_MEANING = PATH + "meanings.speed.no-speed",
            SPEED_II_MEANING = PATH + "meanings.speed.no-speed",
            SPEED_III_MEANING = PATH + "meanings.speed.no-speed",
            BED_INSTA_BREAK_MEANING = PATH + "meanings.bed-insta-break",
            LOW_GRAVITY_MEANING = PATH + "meanings.low-gravity",
            MAX_TEAM_UPGRADES_MEANING = PATH + "meanings.max-team-upgrades",
            ALLOW_MAP_BREAK_MEANING = PATH + "meanings.allow-map-break",
            NO_DIAMONDS_MEANING = PATH + "meanings.no-diamonds",
            NO_EMERALDS_MEANING = PATH + "meanings.no-emeralds",
            RESPAWN_EVENT_TIME_I_MEANING = PATH + "meanings.respawn-event-time.1",
            RESPAWN_EVENT_TIME_II_MEANING = PATH + "meanings.respawn-event-time.2",
            RESPAWN_EVENT_TIME_III_MEANING = PATH + "meanings.respawn-event-time.3",
            HEALTH_BUFF_MEANING = PATH + "meanings.health-buff",
            NORMAL_HEALTH_MEANING = PATH + "meanings.health.normal",
            DOUBLE_HEALTH_MEANING = PATH + "meanings.health.double",
            TRIPLE_HEALTH_MEANING = PATH + "meanings.health.triple",
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
            SUBMENU_EVENTS_TIME_BACK_ITEM_LORE = PATH + "menu.submenu.events-time.back-item.lore";
}
