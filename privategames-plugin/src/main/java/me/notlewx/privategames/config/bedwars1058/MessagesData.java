package me.notlewx.privategames.config.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import org.bukkit.configuration.file.YamlConfiguration;
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
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aPrivate Game Settings");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Open this menu to configure your private game", "with up to 11 different options!"));
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
                    break;
                case "es":
                    yml.addDefault(MAIN_MENU_NAME, "&8Ajustes de Partida Privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aAjustes de Partida Privada");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Abre este menú para configurar tu partida", "&7con más de 11 modificadores!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSeleccionado!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClick para Seleccionar!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cNo estas en una fiesta!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aHas habilitado las partidas privadas");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ha habilitado las partidas privadas");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cHas deshabilitado las partidas privadas");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} ha habilitado las partidas privadas");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cNo eres el owner de esta party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cNo tienes permiso para usar este comando!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cLa partida privada ya está habilitada!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cLa partida privada ya está deshabilitada!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cNo fue posible unirse a la partida privada de este jugador!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cNo puedes hacer esto en una partida!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lha habilitado los modificadores!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aVolver atrás");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7Vuelve a jugar BedWars"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aUn Golpe, Una Muerte");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Selecciona que items deberían", "&7matar enemigos instantáneamente", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aPotenciador de Vida");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Potencia la vida de los jugadores", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aGravedad Baja");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Prueba la gravedad baja... tamién", "&7conocido como salto alto", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&Velocidad");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Toca ir rápido!", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aTiempo de Reaparición");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Juego específico", "", "&7Modifica el tiempo de reaparición", "", "&aSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aTiempo de Eventos");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Juego específico", "", "&7Modifica el tiempo de los eventos.", "", "&aaSeleccionado: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aSin Esmeraldas");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Juego específico", "", "&7Deshazte de esas malditas esmeraldas", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aSin Diamantes");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Juego específico", "", "&7Al infierno con esas cosas brillantes", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDeshabilitar la protección de bloques");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Permite a los jugadores romper cualquier", "&7bloque, excluyendo bloques alrededor", "&7de generadores y spawns.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aRotura instantánea");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Juego específico", "", "&7Las camas se romperán con un solo golpe", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMejoras de Equipos al Máximo");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Juego específico", "", "&7Todos los equipos comenzarán con las", "&7mejoras del equipo al máximo", "", "{state}"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&Velocidad");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aSin Velocidad");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aVelocidad I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aVelocidad II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aVelocidad III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aVolver Atrás");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7Al menú de ajustes de partida privada"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Potenciador de Vida");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aVida Normal");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aVida Doble");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aVida Triple");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aVolver Atrás");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7Al menú de ajustes de partida privada"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Tiempo de Eventos");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Lento");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normal");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Rápido");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Rapidísimo");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aVolver Atrás");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7Al menú de ajustes de partida privada"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Tiempo de Reaparición");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Segundo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Segundo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Segundo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aVolver Atrás");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7Al menú de ajustes de partida privada"));
                    yml.addDefault(NO_SPEED_MEANING, "&7Sin Velocidad");
                    yml.addDefault(SPEED_I_MEANING, "&7Velocidad I");
                    yml.addDefault(SPEED_II_MEANING, "&7Velocidad II");
                    yml.addDefault(SPEED_III_MEANING, "&7Velocidad III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Vida Normal");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Vida Normal");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Vida Normal");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 Segundo");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 Segundo");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 Segundo");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Lento");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Normal");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Rápido");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Rapidísimo");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7Un Golpe Una Muerte");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Rotura Instantánea");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Gravedad Baja");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Mejoras del Equipo al Máximo");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Permitir rotura de bloques");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7Sin Diamantes");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7Sin Esmeraldas");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Potenciador de Vida");
                    yml.addDefault(SPEED_MEANING, "&7Velocidad");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Tiempo de Eventos");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Tiempo de Reaparición");
                    break;
                // SIMPLIFIED CHINESE ADDED BY PixelEast (pixeleast)
                case "zn_ch":
                    yml.addDefault(MAIN_MENU_NAME, "&8私人游戏设定");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&a私人游戏设定");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7在菜单中编辑", "&711项不同的私人游戏设定！"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&a已选中！");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&e点击选择！");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&c你当前并不在一个队伍中！");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&a你已启用私人游戏");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} 启用了私人游戏.");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&c你关闭了私人游戏");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} 关闭了私人游戏");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&c你并不是这个队伍的队长！");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&c你没有权限这样做！");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&c私人游戏已经启用了！");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&c私人游戏当前并未启用！");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&c无法加入这名玩家的私人游戏！");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&c你不能在游戏中这样做！");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&l已启用私人游戏修改器！", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&a返回");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7返回起床战争游戏"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&a一击必杀");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7选择哪些物品", "&7可以一击必杀敌人.", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&a生命值加成");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7所有玩家的生命值加成", "", "&a当前选择: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&a低重力");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7体验一下低重力起床...", "&7所有人都可以跳的很高", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&a速度加成");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7必须要再快点！", "", "&a当前选择: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&a重生时间");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8游戏特性", "", "&7修改玩家的重生时间", "", "&a当前选择: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&a事件倍速");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8游戏特性", "", "&7修改事件的时间速度", "", "&a当前选择: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&a无绿宝石资源");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8游戏特性", "", "&7岛屿中心不会再生成绿宝石", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&a无钻石资源");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8游戏特性", "", "&7让这些亮闪闪的东西见鬼去吧", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&a地图保护关闭");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8游戏特性", "", "&7开启此设置将会允许玩家", "&7破坏竞技场的地图方块", "&7包括生成点与出生点.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&a易碎的床");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8游戏特性", "", "&7所有的床一触即碎", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&a满级队伍升级");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8游戏特性", "", "&7所有队伍初始", "&7将拥有满级队伍升级", "", "{state}"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8速度");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&a无速度");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&a速度 I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&a速度 II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&a速度 III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&a返回");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7返回至设置菜单"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8生命值加成");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&a无加成");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&a双倍生命值");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&a三倍生命值");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&a返回");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7返回至设置菜单"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8事件倍速");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - 缓慢");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - 正常");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - 快速");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - 疾速");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&a返回");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7返回至设置菜单"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8重生时间");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1秒");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5秒");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10秒");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&a返回 ");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7返回至设置菜单"));
                    yml.addDefault(NO_SPEED_MEANING, "&7无速度");
                    yml.addDefault(SPEED_I_MEANING, "&7速度 I");
                    yml.addDefault(SPEED_II_MEANING, "&7速度 II");
                    yml.addDefault(SPEED_III_MEANING, "&7速度 III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7无加成");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7双倍生命值");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7三倍生命值");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71秒");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75秒");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710秒");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - 缓慢");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - 正常");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - 快速");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - 疾速");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7一击必杀");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7易碎的床");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7低重力");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7满级队伍升级");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7地图保护关闭");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7无钻石资源");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7无绿宝石资源");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7生命值加成");
                    yml.addDefault(SPEED_MEANING, "&7速度加成");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7事件倍速");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7重生事件");
                    break;
                // HINDI ADDED BY Agent (agentnoobff)
                case "hi":
                    yml.addDefault(MAIN_MENU_NAME, "&8गुप्त खेल की समायोजन");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&8गुप्त खेल की समायोजन");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7अपने गुप्त खेल को क़ायम करने के लिए इस मेनू को खोलें", "अधिकतम 11 विभिन्न विकल्पों के साथ!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aचुना हुआ!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eचुनने के लिए दबाये!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cआप किसी पार्टी में नहीं हैं!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aआपने गुप्त खेल सक्षम कर दिए हैं");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ने गुप्त खेल सक्षम कर दिया हैं");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cआपने गुप्त खेल अक्षम कर दिए हैं");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} ने गुप्त खेल अक्षम कर दिया हैं");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cआप इस पार्टी के मालिक नहीं हैं!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cआपके पास इस आदेश का उपयोग करने की अनुमति नहीं है!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cगुप्त खेल पहले से ही सक्षम है!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cगुप्त खेल पहले से ही अक्षम है!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cआप इस खिलाड़ी के निजी गेम में प्रवेश नहीं कर सके!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cआप गेम में ऐसा नहीं कर सकते!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lने गुप्त खेल संशोधक सक्षम कर दिया है", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aवापस जाएँ");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7अपने बेडवॉर्स गेमप्ले पर वापस जाएं"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aएक प्रहार, एक मार");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7चुनें कि कौन सी वस्तुएँ", "& दुश्मनों को तुरंत मार डालेंगी", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aस्वास्थ्य वृद्धि");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7सभी खिलाड़ियों का स्वास्थ्य बफ़ करें", "", "&aवर्तमान में चयनित: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aकम गुरुत्वाकर्षण");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7कम गुरुत्वाकर्षण अनुभव करें... इसे", "&7जंप बूस्ट के रूप में भी जाना जाता है", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aरफ़्तार");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7जल्दी जाना है!", "", "&aवर्तमान में चयनित: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aपुनर्जन्म तक का समय");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7खिलाड़ी के पुनर्जन्म का समय संशोधित करें", "", "&aवर्तमान में चयनित: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aघटनाओं का समय");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7आयोजनों के लिए समय संशोधित करें", "", "&aवर्तमान में चयनित: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aपन्ने अक्षम करें");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7पन्ने से छुटकारा पाएं", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aहीरे अक्षम करें");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7हीरे से छुटकारा पाएं", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aब्लॉकों की सुरक्षा करना अक्षम करें");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7खिलाड़ियों को जनरेटर और स्पॉन के", "&7आसपास के ब्लॉक को छोड़कर, किसी", "&7भी ब्लॉक को तोड़ने की अनुमति दें।", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aबिस्तर इंस्टाब्रेक");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7एक ही मुक्के से बिस्तर टूट जाते हैं", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aअधिकतम टीम उन्नयन");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8खेल विशिष्ट", "", "&7सभी टीमें अधिकतम टीम अपग्रेड", "&7के साथ शुरुआत करेंगी", "", "{state}"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8रफ़्तार");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aरफ़्तार 0");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aरफ़्तार I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aरफ़्तार II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aरफ़्तार III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aवापस जाएँ");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7सेटिंग्स का मेनू पर वापस जाएं"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8स्वास्थ्य वृद्धि");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aसाधारण स्वास्थ्य");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aडुगना स्वास्थ्य");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aतिग्ना स्वास्थ्य");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aवापस जाएँ");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7सेटिंग्स का मेनू पर वापस जाएं"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Events Time");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - धीमा");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - साधारण");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - तेज़");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - और तेज");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aवापस जाएँ");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7सेटिंग्स का मेनू पर वापस जाएं"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8पुनर्जन्म का समय");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 सेकंड");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 सेकंड");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 सेकंड");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aवापस जाएँ");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7सेटिंग्स का मेनू पर वापस जाएं"));
                    yml.addDefault(NO_SPEED_MEANING, "&7रफ़्तार 0");
                    yml.addDefault(SPEED_I_MEANING, "&7रफ़्तार I");
                    yml.addDefault(SPEED_II_MEANING, "&7रफ़्तार II");
                    yml.addDefault(SPEED_III_MEANING, "&7रफ़्तार III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7साधारण स्वास्थ्य");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7डुगना स्वास्थ्य");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7तिग्ना स्वास्थ्य");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 सेकंड");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 सेकंडs");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 सेकंडs");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - धीमा");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - साधारण");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - तेज़");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - और तेज");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7एक प्रहार, एक मार");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7बिस्तर इंस्टाब्रेक");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7कम गुरुत्वाकर्षण");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7अधिकतम टीम उन्नयन");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7ब्लॉकों की सुरक्षा करना अक्षम करें");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7हीरे अक्षम करें");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7पन्ने अक्षम करें");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7स्वास्थ्य वृद्धि");
                    yml.addDefault(SPEED_MEANING, "&7रफ़्तार");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7घटनाओं का समय");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7पुनर्जन्म तक का समय");
                    break;
                // POLISH ADDED BY Endern (endernxd)
                case "pl":
                    yml.addDefault(MAIN_MENU_NAME, "&8Ustawienia prywatnych gier");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aUstawienia prywatnych gier");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Otwórz to menu, aby skonfigurować prywatną grę", "z wyborem nawet do 11 różnych opcji!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aWybrano!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eKliknij, aby wybrać!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cNie jesteś w party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aWłączyłeś prywatne gry");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} włączył prywatne gry");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cWyłączyłeś prywatne gry");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} wyłączył prywatne gry");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cNie jesteś właścicielem tego party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cNie masz uprawnień potrzebnych do użycia tej komendy!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cPrywatne gry są już włączone!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cPrywatne gry są już wyłączone!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cNie możesz dołączyć do tej prywatnej rozgrywki!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cNie możesz tego zrobić w grze!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lwłączył modyfikacje prywatnej gry!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aPowrót");
                    yml.addDefault(MENU_BACK_ITEM_LORE, Arrays.asList("&7Wróć do rozgrywki"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aJedno dotknięcie, śmierć");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Wybierz jakim przedmiotem", "&7będzie można zabijać natychmiastowo", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aIlość serc");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Zwiększ limit serc każdego gracza", "", "&aAktualnie wybrane: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aNiska grawitacja");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Doświadcz niskiej grawitacji... również", "&7znane jako efekt skoku", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aSzybkość");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Muszę być szybszy!", "", "&aAktualnie wybrane: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aCzas odrodzenia");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Zmodyfikuj czas odrodzenia gracza", "", "&aAktualnie wybrane: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aCzas eventów");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Zmodyfikuj czas trwania eventów.", "", "&aAktualnie wybrane: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aBrak emeraldów");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Pozbądź się emeraldów  ", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aBrak diamentów");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Do diabła z tymi błyszczącymi rzeczami", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aWyłączona ochrona mapy");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Zezwala graczowi na zniszczenie każdego", "&7bloku, wyłączając bloki wokół", "&7generatorów i miejsca odrodzenia.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aNatychmiastowe niszczenie łóżek");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Łożka niszczą się jednym dotknięciem", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMaksymalne ulepszenia");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Specyficzne dla gry", "", "&7Wszystkie drużyny rozpoczynają grę", "&7z wszystkimi maksymalnymi ulepszeniami", "", "{state}"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Szybkość");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aBrak szybkości");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aSzybkość I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aSzybkość II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aSzybkość III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aPowrót");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, Arrays.asList("&7Powrót do menu ustawień"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Ilość serc");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aNormalna ilość serc");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aPodwójna ilość serc");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aPotrójna ilość serc");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aPowrót");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, Arrays.asList("&7Powrót do menu ustawień"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Czas eventów");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Wolno");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normalnie");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Szybko");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Bardzo szybko");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aPowrót");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, Arrays.asList("&7Powrót do menu ustawień"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Czas odrodzenia");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 sekund");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 sekund");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, Arrays.asList("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 sekund");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, Arrays.asList("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aPowrót");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, Arrays.asList("&7Powrót do menu ustawień"));
                    yml.addDefault(NO_SPEED_MEANING, "&7Brak szybkości");
                    yml.addDefault(SPEED_I_MEANING, "&7Szybkość I");
                    yml.addDefault(SPEED_II_MEANING, "&7Szybkość II");
                    yml.addDefault(SPEED_III_MEANING, "&7Szybkość III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Normalna ilość serc");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Podwojona ilość serc");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Potrojona ilość serc");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 sekunda");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 sekund");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 sekund");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Wolno");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Normalnie");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Szybko");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Bardzo szybko");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7Jedno dotknięcie, śmierć");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Natychmiastowe niszczenie łóżek");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Niska grawitacja");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Maksymalne ulepszenia");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Wyłączona ochrona mapy");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7Brak diamentów");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7Brak emeraldów");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Ilość serc");
                    yml.addDefault(SPEED_MEANING, "&7Szybkość");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Czas eventów");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Czas odrodzenia");
                    break;
            }
            l.getYml().options().copyDefaults(true);
            l.save();
        }
    }
    public static final String
            MAIN_MENU_NAME = PATH + "menu.menu-name",
            MENU_SELECTED_MEANING = PATH + "menu.selected-meaning",
            PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER = PATH + "scoreboard-placeholder",
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
            RESPAWN_EVENT_TIME_I_MEANING = PATH + "meanings.respawn-event-time.1",
            RESPAWN_EVENT_TIME_II_MEANING = PATH + "meanings.respawn-event-time.2",
            RESPAWN_EVENT_TIME_III_MEANING = PATH + "meanings.respawn-event-time.3",
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
