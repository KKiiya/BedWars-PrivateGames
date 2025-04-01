package me.notlewx.privategames.config.bedwars2023;

import com.tomkeuper.bedwars.api.language.Language;
import me.notlewx.privategames.PrivateGames;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;
import java.util.List;

import static me.notlewx.privategames.PrivateGames.mainConfig;
import static me.notlewx.privategames.config.bedwars1058.MessagesData.PRIVATE_GAME_UNABLE_TO_JOIN;

public class MessagesData {
    public final static String PATH = "addons.private-games.";

    public MessagesData() {
        setupMessages();
    }

    public void setupMessages() {
        for (Language l : Language.getLanguages()) {
            YamlConfiguration yml = l.getYml();
            switch (l.getIso()) {
                // Vietnamese language. Special tanks to rin_25.04
                case "vi":
                    yml.addDefault(HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eHelp &7- &8Tác giả: Kiiya, Phiên bản: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg gui &7- &aMở menu game riêng tư",
                            "&7- &e/pg enable &7- &aBật chế độ game riêng tư",
                            "&7- &e/pg disable &7- &aTắt chế độ game riêng tư",
                            "&7- &e/pg join <người chơi> &7- &aTham gia vào một game riêng tư của người chơi khác",
                            "&7- &e/pg accept &7- &aChấp nhận yêu cầu tham gia gần nhất",
                            "&7- &e/pg deny &7- &aTừ chối yêu cầu tham gia gần nhất",
                            "&7- &e/pg accept <player> &7- &aChấp nhận yêu cầu tham gia của 1 người chơi",
                            "&7- &e/pg deny <player> &7- &aTừ chối yêu cầu tham gia của 1 người chơi",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(ADMIN_HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAdmin Help &7- &8Tác giả: Kiiya, Phiên bản: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg reload &7- &aTải lại ngôn ngữ",
                            "&7- &e/pg enable admin &7- &aBật chế độ game riêng tư chỉ cho bạn (party nữa)",
                            "&7- &e/pg disable admin &7- &aTắt chế độ game riêng tư của bạn (party nữa)",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(NOT_ENOUGH_ARGS, "&cKhông đủ tham số!");
                    yml.addDefault(COULDNT_FIND_PLAYER, "&cKhông thể tìm thấy người chơi!");
                    yml.addDefault(MAIN_MENU_NAME, "&8Cài đặt game riêng tư");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aCài đặt game riêng tư");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Mở menu này để cài đặt trò chơi riêng tư", "với tận 11 tùy chọn!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aĐã chọn!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eNhấn để chọn!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cBạn đang không ở trong party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aBạn đã bật chế độ game riêng tư");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} đã bật chế độ game riêng tư");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cBạn đã tắt chế độ game riêng tư");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} đã tắt chế độ game riêng tư");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cBạn không phải chủ party này!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cBạn không có quyền để sử dụng câu lệnh này!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cTính năng game riêng tư đã được bật rồi!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cTính năng game riêng tư đã bị tắt rồi!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cKhông thể vào game riêng tư của người chơi này!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cBạn không thể làm điều này trong game!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lđã bật chức năng tùy chỉnh của game riêng tư!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(PRIVATE_GAME_UNABLE_TO_JOIN, "&cKhông thế vào phòng này. Bạn đã bật chế độ game riêng tư và phòng này không trống!");
                    yml.addDefault(PRIVATE_GAME_CANT_JOIN_SELF, "&cBạn không thể tự vào phòng riêng tư của mình!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED, Arrays.asList(
                            "&6&m-----------------------------------------------------",
                            "&e{player} &7đang yêu cầu tham gia game riêng tư của bạn!",
                            "{buttons}",
                            "&6&m-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_SENT,
                            "&6&m-----------------------------------------------------\n" +
                                    "&7Bạn đã yêu cầu tham gia vào game riêng tư của &e{player}!\n" +
                                    "&6&m-----------------------------------------------------\n");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT, "&a&lChấp nhận");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER, "&aNhấp để chấp nhận!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY, "&c&lTừ chối");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER, "&cNhấp để từ chối!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED, "&cBạn đã từ chối yêu cầu từ &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED, "&aBạn đã chấp nhận yêu cầu của &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER, "&a&l{player} &ađã chấp nhận yêu cầu tham gia của bạn!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED_REQUESTER, "&c&l{player} &cđã từ chối yêu cầu tham gia của bạn!");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(MENU_BACK_ITEM_LORE, List.of("&7Quay lại gameplay bedwars của bạn"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aMột đấm, một mạng");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Chọn vật phẩm nào nên", "&7giết kẻ địch ngay lập tức", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aNâng máu");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Nâng máu của tất cả người chơi", "", "&aĐang chọn: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aTrọng lực thấp");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Trải nghiệm trọng lực thấp... hay", "&7còn gọi là nhảy cao", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aChạy nhanh");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Phải chạy thật nhanh!", "", "&aĐang chọn: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aThời gian hồi sinh");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Điều chỉnh thời gian hồi sinh.", "", "&aĐang chọn: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aThời gian sự kiện");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Điều chỉnh thời gian các sự kiện.", "", "&aĐang chon: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aKhông ngọc lục bảo");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Loại bỏ những viên ngọc lục bảo  ", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aKhông kim cương");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Tạm biệt những viên kim cương", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aTắt bảo vệ block");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Cho phép người chơi phá mọi", "&7khối, ngoại trừ các khối gần", "&7các lò khoáng sản và nơi hồi sinh.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aPhá giường nhanh");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Đập giường chỉ với một phát", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aĐầy đỏ nâng cấp đội");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Chỉ trong game", "", "&7Các đội sẽ bắt đầu với đầy đủ", "&7nâng cấp đội", "", "{state}"));
                    yml.addDefault(ITEM_OPTIONS_NAME, "&aTùy chọn");
                    yml.addDefault(ITEM_OPTIONS_LORE, Arrays.asList("&7Nhấn để mở tùy chọn trận đấu", "", "&7Ở menu này bạn có thể tùy chỉnh", "&7thuộc tính của trận đấu và cài đặt của bạn", "", "&eNhấn để mở!"));
                    yml.addDefault(ITEM_START_NAME, "&aBắt đầu");
                    yml.addDefault(ITEM_START_LORE, Arrays.asList("&7Nhấn để bắt đầu trận đấu", "", "&7Bạn có thể bắt đầu trò chơi nếu bạn", "&7là chủ party này", "", "&eNhấn để bắt đầu!"));
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_NAME, "&aĐổi chế độ chơi");
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_LORE, Arrays.asList("&7Nhấn để đổi chế độ chơi của bạn.", "", "&7Bạn có thể đổi chế độ chơi của bạn nếu", "&7là chủ party", "", "&7Hiện tại: &a{state}", "&7Mặc định: &e{default}", "", "&cChuột phải để dùng chế độ mặc định", "&eChuột trái để đổi!"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Chạy nhanh");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aKhông chạy nhanh");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aChạy nhanh I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aChạy nhanh II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aChạy nhanh III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, List.of("&7Quay lại menu cài đặt"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Nâng máu");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aMáu bình thường");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aGấp đôi máu");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aGấp ba máu");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, List.of("&7Quay lại menu cài đặt"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Thời gian sự kiện");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Chậm");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Bình thường");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Nhanh");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Nhanh hơn");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, List.of("&7Quay lại menu cài đặt"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Thời gian hồi sinh");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 giây");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 giây");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 giây");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, List.of("&7Quay lại menu cài đặt"));
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_NAME, "&bLò khoáng sản");
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_LORE, Arrays.asList("&7Nhấn để mở lò khoáng sản của trận đấu", "", "&7Cài đặt thuộc tính của lò khoáng sản.", "", "&eNhấn để mở!"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aĐã bật");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cĐã tắt");
                    yml.addDefault(NO_SPEED_MEANING, "&7Không chạy nhanh");
                    yml.addDefault(SPEED_I_MEANING, "&7Chạy nhanh I");
                    yml.addDefault(SPEED_II_MEANING, "&7Chạy nhanh II");
                    yml.addDefault(SPEED_III_MEANING, "&7Chạy nhanh III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Máu bình thường");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Gấp đôi máu");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Gấp ba máu");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 giây");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 giây");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 giây");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Chậm");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Bình thường");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Nhanh");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Nhanh hơn");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7Một phát một mạng");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Phá giường nhanh");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Trọng lực thấp");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Đầy đủ nâng cấp đội");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Cho phép phá map");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7Không kim cương");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7Không ngọc lục bảo");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Nâng máu");
                    yml.addDefault(SPEED_MEANING, "&7Chạy nhanh");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Thời gian sự kiện");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Thời gian hồi sinh");
                    yml.addDefault(SUBMENU_GAMEMODE_CHANGER_TITLE, "&8Đổi chế độ game");
                    yml.addDefault(SUBMENU_OPTIONS_TITLE, "Tùy chọn");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME, "&aBật tự động bắt đầu");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE, Arrays.asList("&7Bật tự động bắt đầu trận đấu", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME, "&aCho phép người khác tham gia");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE, Arrays.asList("&7Cho phép người khác tham gia game của bạn", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME, "&aBật game riêng tư");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE, Arrays.asList("&7Bật game riêng tư", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_BACK_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_OPTIONS_BACK_LORE, List.of("&7Quay lại menu cài đặt"));
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_TITLE, "Lò khoáng sản của trận đấu");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_ITEM_LORE, Arrays.asList("&7Đội: {team}", "&7Vị trí: {location}", "&7Số lượng: {amount}", "&7Độ trễ: {delay}",  "§7Giới hạn sản xuất: {spawnLimit}"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_TITLE, "Tùy chọn lò khoáng sản");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE, List.of("&7Quay lại menu tùy chỉnh"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME, "&aQuay lại");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE, List.of("&7Quay lại menu của lò khoáng sản"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aĐã bật");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cĐã tắt");
                    yml.addDefault(MENU_CLICK_TO_START_MEANING, "&eNhấn để bắt đầu!");
                    yml.addDefault(MENU_STARTING_MEANING, "&aĐang bắt đầu...");

                    if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu") != null) {
                        for (String p : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu").getKeys(false)) {
                            for (String group : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + p + ".modes").getKeys(false)) {
                                if (group.equals("back-item")) {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&aQuay lại");
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", List.of("&7Quay lại menu cài đặt"));
                                } else {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&a" + group);
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", Arrays.asList("&7Nhấn để đổi chế độ chơi", "", "&7Bạn có thể đổi chế độ chơi của bạn nếu", "&7bạn là chủ party", "", "&eNhấn để đổi!"));
                                }
                            }
                        }
                    }
                    break;
                // Brazilian portuguese. Special thanks to zypj.
                case "pt":
                    yml.addDefault(HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAjuda &7- &8Autor: Kiiya, Versão: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg gui &7- &aAbrir o menu de jogos privados",
                            "&7- &e/pg enable &7- &aAtivar os jogos privados",
                            "&7- &e/pg disable &7- &aDesativar os jogos privados",
                            "&7- &e/pg join &7- &aEntrar nos jogos privados de algum jogador",
                            "&7- &e/pg accept &7- &aAceitar o último convite recebido",
                            "&7- &e/pg deny &7- &aNegar o último convite recebido",
                            "&7- &e/pg accept &7- &aAceitar o convite de um jogador específico",
                            "&7- &e/pg deny &7- &aNegar o convite de um jogador específico",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(ADMIN_HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAjuda de Admin &7- &8Autor: Kiiya, Versão: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg reload &7- &aRecarregar mensagens",
                            "&7- &e/pg enable admin &7- &aAtivar os jogos privados pra você (party também)",
                            "&7- &e/pg disable admin &7- &aAtivar os jogos privados pra você (party também)",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(NOT_ENOUGH_ARGS, "&cArgumentos insuficientes!");
                    yml.addDefault(COULDNT_FIND_PLAYER, "&cNão foi possível encontrar o jogador!");
                    yml.addDefault(MAIN_MENU_NAME, "&8Configurações Jogos Privados");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aPreferências de Jogos Privados");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Abra esse menu para configurar os", "&7jogos privados com 11 configurações diferentes!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSelecionado!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClique para Selecionar!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cVocê não está em uma party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aVocê ativou os jogos privados");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ativou os jogos privados");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cVocê desativou os jogos privados");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} desativou os jogos privados");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cVocê não é dono de uma party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cVocê não tem permissão para usar esse comando!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cOs jogos privados já estão ativados!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cOs jogos privados já estão desativados!");
                    yml.addDefault(PRIVATE_GAME_UNABLE_TO_JOIN, "&cVocê não pode entrar nesta arena! Você está com os Jogos Privados ativados e essa arena não está vazia!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cVocê não pode entrar nos jogos privados desse jogador!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cVocê não pode fazer isso em jogo!");
                    yml.addDefault(PRIVATE_GAME_CANT_JOIN_SELF, "&cVocê não pode entrar no seu próprio jogos privados!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lativou os algumas modificações!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED, Arrays.asList(
                            "&6&m-----------------------------------------------------",
                            "&e{player} &7pediu para entrar no seu jogo!",
                            "{buttons}",
                            "&6&m-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_SENT,
                            "&6&m-----------------------------------------------------\n" +
                                    "&7Você pediu para entrar no jogo privado de &e{player}!\n" +
                                    "&6&m-----------------------------------------------------\n");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT, "&a&lAceitar");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER, "&aClique para aceitar!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY, "&c&lNegar");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER, "&cClique para negar!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED, "&cVocê negou o pedido de &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED, "&aVocê aceitou o pedido de &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER, "&a&l{player} &aaceitou seu pedido!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED_REQUESTER, "&c&l{player} &cnegou seu pedido!");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(MENU_BACK_ITEM_LORE, List.of("&7Volte para sua gameplay"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aUm golpe, uma morte");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Selecione quais itens devem", "&7matar instantaneamente os inimigos", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aAumento de Vida");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Aumente a vida de todos os jogadores", "", "&aAtualmente Selecionado: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aGravidade Baixa");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Experimente gravidade baixa... também", "&7conhecido como aumento de pulo", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aVelocidade");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Tem que ir rápido!", "", "&aAtualmente Selecionado: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aTempo de Respawn");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Modifique o tempo de respawn dos jogadores", "", "&aAtualmente selecionado: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aTempo dos Eventos");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Modifique o tempo dos eventos.", "", "&aAtualmente selecionado: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aSem Esmeraldas");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Acabe com essas esmeraldas rápidas", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aSem Diamantes");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Que se dane essas coisas brilhantes", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDesabilitar Proteção de Blocos");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Permitir que os jogadores quebrem qualquer", "&7bloco, exceto blocos ao redor", "&7dos geradores e pontos de spawn.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aQuebra Instantânea de Cama");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Camas se quebram com um único soco", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMelhorias Máximas da Equipe");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&8Específico do Jogo", "", "&7Todas as equipes começarão com as", "&7melhorias máximas da equipe", "", "{state}"));
                    yml.addDefault(ITEM_OPTIONS_NAME, "&aOpções");
                    yml.addDefault(ITEM_OPTIONS_LORE, Arrays.asList("&7Clique aqui para abrir as opções da arena", "", "&7Neste menu você pode configurar", "&7atributos da arena e suas configurações", "", "&eClique para abrir!"));
                    yml.addDefault(ITEM_START_NAME, "&aIniciar");
                    yml.addDefault(ITEM_START_LORE, Arrays.asList("&7Clique aqui para iniciar o jogo", "", "&7Você pode iniciar o jogo se for", "&7o dono da festa", "", "&eClique para iniciar!"));
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_NAME, "&aAlterador de Modo de Jogo");
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_LORE, Arrays.asList("&7Clique aqui para alterar o modo de jogo", "", "&7Você pode alterar o modo de jogo se for", "&7o dono da festa", "", "&7Atual: &a{state}", "&7Padrão: &e{default}", "", "&cClique com o botão direito para definir o modo de jogo padrão", "&eClique com o botão esquerdo para mudar!"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Velocidade");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aSem Velocidade");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aVelocidade I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aVelocidade II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aVelocidade III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, List.of("&7Voltar para o menu de configurações"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Aumento de Vida");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aVida Normal");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aVida Dupla");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aVida Tripla");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, List.of("&7Voltar para o menu de configurações"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Tempo dos Eventos");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Mais Lento");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normal");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Rápido");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Mais Rápido");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, List.of("&7Voltar para o menu de configurações"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Tempo de Respawn");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Segundo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Segundos");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Segundos");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, List.of("&7Voltar para o menu de configurações"));
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_NAME, "&bGeradores");
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_LORE, Arrays.asList("&7Clique aqui para abrir os geradores da arena", "", "&7Configure os atributos dos geradores aqui", "", "&eClique para abrir!"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aHabilitado");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDesabilitado");
                    yml.addDefault(NO_SPEED_MEANING, "&7Sem velocidade");
                    yml.addDefault(SPEED_I_MEANING, "&7Velocidade I");
                    yml.addDefault(SPEED_II_MEANING, "&7Velocidade II");
                    yml.addDefault(SPEED_III_MEANING, "&7Velocidade III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Vida Normal");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Vida Dupla");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Vida Tripla");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 Segundo");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 Segundos");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 Segundos");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Devagar");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Normal");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Rápido");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Mais Rápido");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7Um golpe, uma morte");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Quebra Instantânea de Cama");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Gravidade Baixa");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Melhorias Máximas da Equipe");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Permitir Quebra do Mapa");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7Sem Diamantes");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7Sem Esmeraldas");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Aumento de Vida");
                    yml.addDefault(SPEED_MEANING, "&7Velocidade");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Tempo dos Eventos");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Tempo de Respawn");
                    yml.addDefault(SUBMENU_GAMEMODE_CHANGER_TITLE, "&8Alterador de Modo de Jogo");
                    yml.addDefault(SUBMENU_OPTIONS_TITLE, "Opções");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME, "&aHabilitar Início Automático");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE, Arrays.asList("&7Habilitar o início automático do jogo", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME, "&aPermitir Outros a Entrar");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE, Arrays.asList("&7Permitir que outros entrem no seu jogo", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME, "&aHabilitar Jogos Privados");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE, Arrays.asList("&7Habilitar jogos privados", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_BACK_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_OPTIONS_BACK_LORE, List.of("&7Voltar para o menu de configurações"));
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_TITLE, "Geradores da Arena");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_ITEM_LORE, Arrays.asList("&7Equipe: {team}", "&7Localização: {location}", "&7Quantidade: {amount}", "&7Atraso: {delay}", "§7Limite de Geração: {spawnLimit}"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_TITLE, "Opções de Geradores");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE, List.of("&7Voltar para o menu de opções"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME, "&aVoltar");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE, List.of("&7Voltar para o menu de geradores"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aHabilitado");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDesabilitado");
                    yml.addDefault(MENU_CLICK_TO_START_MEANING, "&eClique para iniciar!");
                    yml.addDefault(MENU_STARTING_MEANING, "&aIniciando...");

                    if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu") != null) {
                        for (String p : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu").getKeys(false)) {
                            for (String group : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + p + ".modes").getKeys(false)) {
                                if (group.equals("back-item")) {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&aVoltar");
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", List.of("&7Volte para configurações"));
                                } else {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&a" + group);
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", Arrays.asList("&7Clique aqui para mudar o modo de jogo", "", "&7Você pode mudar o modo de jogo se", "&7for o dono da party", "", "&eClique aqui para mudar!"));
                                }
                            }
                        }
                    }
                    break;
                // Italian. Special thanks to _lunarlex44_.
                case "it":
                    yml.addDefault(HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6PrivateGames &7- &eHelp &7- &8Autore: Kiiya, Versione: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg gui &7- &aApri il menu dei privategames",
                            "&7- &e/pg enable &7- &aAbilita i privategames",
                            "&7- &e/pg disable &7- &aDisabilita i privategames",
                            "&7- &e/pg join <player> &7- &aEntra nel privategame di un altro giocatore",
                            "&7- &e/pg accept &7- &aAccetta l'ultima richiesta di entrata nel tuo privategame",
                            "&7- &e/pg deny &7- &aDisabilita l'ultima richiesta di entrata nel tuo privategame",
                            "&7- &e/pg accept <player> &7- &aAccetta la richiesta di entrata nel tuo privategame di un giocatore",
                            "&7- &e/pg deny <player> &7- &aRifiuta la richiesta di entrata nel tuo privategame di un giocatore",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(ADMIN_HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAiuto per Admin &7- &8Autore: Kiiya, Versione: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg reload &7- &aRicarica i messaggi",
                            "&7- &e/pg enable admin &7- &aAbilita i privategames solo per te (e il tuo party)",
                            "&7- &e/pg disable admin &7- &aDisabilita i privategames solo per te (e il tuo party)",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(NOT_ENOUGH_ARGS, "&cArgomenti insufficienti!");
                    yml.addDefault(COULDNT_FIND_PLAYER, "&cImpossibile trovare il giocatore!");
                    yml.addDefault(MAIN_MENU_NAME, "&8Impostazioni PrivateGames");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_NAME, "&aImpostazioni PrivateGames");
                    yml.addDefault(PRIVATE_GAME_MENU_ITEM_LORE, Arrays.asList("&7Apri questo menu per configurare il tuo privategame", "&7con più di 11 differenti opzioni!"));
                    yml.addDefault(MENU_SELECTED_MEANING, "&aSelezionato!");
                    yml.addDefault(MENU_CLICK_TO_SELECT_MEANING, "&eClicca per selezionare!");
                    yml.addDefault(PRIVATE_GAME_NOT_IN_PARTY, "&cNon sei in un party!");
                    yml.addDefault(PRIVATE_GAME_ENABLED, "&aHai abilitato i privategames!");
                    yml.addDefault(PRIVATE_GAME_ENABLED_OTHERS, "&a{player} ha abilitato i privategames!");
                    yml.addDefault(PRIVATE_GAME_DISABLED, "&cHai disabilitato i privategames!");
                    yml.addDefault(PRIVATE_GAME_DISABLED_OTHERS, "&c{player} ha disabilitato i privategames!");
                    yml.addDefault(PRIVATE_GAME_NOT_OWNER, "&cNon sei l'owner del party!");
                    yml.addDefault(PRIVATE_GAME_NO_PERMISSION, "&cNon hai il permesso di eseguire questo comando!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_ENABLED, "&cI privategames sono già abilitati!");
                    yml.addDefault(PRIVATE_GAME_ALREADY_DISABLED, "&cI privategames sono già disabilitati!");
                    yml.addDefault(PRIVATE_GAME_UNABLE_TO_JOIN, "&cNon puoi entrare in questa arena perchè essa non è vuota e tu sei in un privategame!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cNon puoi entrare nel privategame di questa persona.");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cNon puoi eseguire questo durante una partita!");
                    yml.addDefault(PRIVATE_GAME_CANT_JOIN_SELF, "&cNon puoi entrare nel tuo stesso privategame!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lha abilitato i seguenti modificatori:", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED, Arrays.asList(
                            "&6&m-----------------------------------------------------",
                            "&e{player} &7vuole entrare nel tuo privategame!",
                            "{buttons}",
                            "&6&m-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_SENT,
                            "&6&m-----------------------------------------------------\n" +
                                    "&7Hai mandato una richiesta di entrata nel privategame di &e{player}!\n" +
                                    "&6&m-----------------------------------------------------\n");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT, "&a&lAccetta");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER, "&aClicca per accettare!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY, "&c&lRifiuta");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER, "&cClicca per rifiutare!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED, "&cHai rifiutato la richiesta di &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED, "&aHai accettato la richiesta di &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER, "&a&l{player} &aha accettato la tua richiesta!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED_REQUESTER, "&c&l{player} &cha rifiutato la tua richiesta!");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aVai indietro");
                    yml.addDefault(MENU_BACK_ITEM_LORE, List.of("&7Torna alla partita"));
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_NAME, "&aUn colpo, una kill");
                    yml.addDefault(ITEM_ONE_HIT_ONE_KILL_LORE, Arrays.asList("&7Seleziona se un colpo equivarrà a una kill", "", "{state}"));
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_NAME, "&aAumento della vita");
                    yml.addDefault(ITEM_HEALTH_BUFF_LEVEL_LORE, Arrays.asList("&7Aumenta la vita di tutti i giocatori", "", "&aOra selezionato: &7{selected}"));
                    yml.addDefault(ITEM_LOW_GRAVITY_NAME, "&aGravità bassa");
                    yml.addDefault(ITEM_LOW_GRAVITY_LORE, Arrays.asList("&7Anche conosciuta come ampliazione del salto", "", "{state}"));
                    yml.addDefault(ITEM_SPEED_NAME, "&aVelocità");
                    yml.addDefault(ITEM_SPEED_LORE, Arrays.asList("&7Seleziona il tuo livello di velocità in game!", "", "&aOra selezionato: &7{selected}"));
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_NAME, "&aTempo di respawn");
                    yml.addDefault(ITEM_RESPAWN_EVENT_TIME_LORE, Arrays.asList("&8Modifica il tempo di respawn in game", "", "&aOra selezionato: &7{selected}"));
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_NAME, "&aTempo degli eventi");
                    yml.addDefault(ITEM_EVENTS_TIME_LEVEL_LORE, Arrays.asList("&8Modifica quanto il tempo degli eventi dovrà ", "scorrere veloce.", "", "&aOra selezionato: &7{selected}"));
                    yml.addDefault(ITEM_NO_EMERALDS_NAME, "&aNiente smeraldi");
                    yml.addDefault(ITEM_NO_EMERALDS_LORE, Arrays.asList("&7Sbarazzati di quegli smeraldi ricercati", "", "{state}"));
                    yml.addDefault(ITEM_NO_DIAMONDS_NAME, "&aNiente diamanti");
                    yml.addDefault(ITEM_NO_DIAMONDS_LORE, Arrays.asList("&7E fu così che i diamenti ridivennero carbone", "e scomparirono dalla tua partita", "", "{state}"));
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_NAME, "&aDisabilita la protezione della mappa");
                    yml.addDefault(ITEM_ALLOW_MAP_BREAK_LORE, Arrays.asList("&7Fa distruggere ai giocatori", "&7qualsiasi blocco della mappa tranne quelli intorno a", "&7generatori e spawns.", "", "{state}"));
                    yml.addDefault(ITEM_BED_INSTA_BREAK_NAME, "&aDistruzione istantanea dei letti");
                    yml.addDefault(ITEM_BED_INSTA_BREAK_LORE, Arrays.asList("&7I letti si distruggeranno con un unico colpo", "", "{state}"));
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_NAME, "&aMassimi upgrade per team");
                    yml.addDefault(ITEM_MAX_TEAM_UPGRADES_LORE, Arrays.asList("&7Massimi upgrade per tutti i team", "", "{state}"));
                    yml.addDefault(ITEM_OPTIONS_NAME, "&aOpzioni");
                    yml.addDefault(ITEM_OPTIONS_LORE, Arrays.asList("&7Clicca qui per aprire le impostazioni dell'arena privata", "", "&7Tramite questo menu puoi configurare", "&7le impostazioni dell'arena in cui giocherai il tuo game privato", "", "&eClicca per aprire!"));
                    yml.addDefault(ITEM_START_NAME, "&aAvvia!");
                    yml.addDefault(ITEM_START_LORE, Arrays.asList("&7Cliccando questo pulsante avvierai in 5 secondi la partita", "", "&7Puoi avviare il game solo se sei l'owner del party", "", "&eClicca per iniziare!"));
                            yml.addDefault(ITEM_GAMEMODE_CHANGER_NAME, "&aCambiamento della gamemode");
                    yml.addDefault(ITEM_GAMEMODE_CHANGER_LORE, Arrays.asList("&7Clicca qui per cambiare la gamemode della partita", "", "&7Puoi cambiarla solo se sei", "&7l'owner del party", "", "&7Ora selezionata: &a{state}", "&7Predefinita: &e{default}", "", "&cClick destro per vedere la gamemode predefinita", "&eClick sinistro per cambiarla"));
                    yml.addDefault(SUBMENU_SPEED_NAME, "&8Velocità");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_NAME, "&aNessuna influenza");
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aVelocità I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aVelocità II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&Velocità III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aVai indietro");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Aumento della vita");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aNessun cambiamento");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aDoppia vita");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aTripla vita");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aVai indietro");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Tempo degli eventi");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Più lento");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Predefinito");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Più veloce");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Più veloce");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aIndietro");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Tempo di respawn");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Secondo");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Secondi");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Secondi");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aVai indietro");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_NAME, "&bGeneratori");
                    yml.addDefault(SUBMENU_OPTIONS_GENERATORS_LORE, Arrays.asList("&7Clicca per aprire il menu dei generatori", "", "&7Configura le impostazioni dei generatori qui", "", "&eClicca per aprire!"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aAbilita");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDisabilita");
                    yml.addDefault(NO_SPEED_MEANING, "&7Nessuna influenza");
                    yml.addDefault(SPEED_I_MEANING, "&7Velocità I");
                    yml.addDefault(SPEED_II_MEANING, "&7Velocità II");
                    yml.addDefault(SPEED_III_MEANING, "&7Velocità III");
                    yml.addDefault(NORMAL_HEALTH_MEANING, "&7Nessun cambiamento");
                    yml.addDefault(DOUBLE_HEALTH_MEANING, "&7Doppia vita");
                    yml.addDefault(TRIPLE_HEALTH_MEANING, "&7Tripla vita");
                    yml.addDefault(RESPAWN_EVENT_TIME_I_MEANING, "&71 Secondo");
                    yml.addDefault(RESPAWN_EVENT_TIME_II_MEANING, "&75 Secondi");
                    yml.addDefault(RESPAWN_EVENT_TIME_III_MEANING, "&710 Secondi");
                    yml.addDefault(EVENTS_TIME_SLOWER_MEANING, "&7x0.5 - Più lento");
                    yml.addDefault(EVENTS_TIME_NORMAL_MEANING, "&7x1 - Predefinito");
                    yml.addDefault(EVENTS_TIME_FAST_MEANING, "&7x2 - Più veloce");
                    yml.addDefault(EVENTS_TIME_FASTER_MEANING, "&7x4 - Più veloce");
                    yml.addDefault(ONE_HIT_ONE_KILL_MEANING, "&7On colpo una kill");
                    yml.addDefault(BED_INSTA_BREAK_MEANING, "&7Distruzione istantanea dei letti");
                    yml.addDefault(LOW_GRAVITY_MEANING, "&7Gravità bassa");
                    yml.addDefault(MAX_TEAM_UPGRADES_MEANING, "&7Massimi upgrade per i team");
                    yml.addDefault(ALLOW_MAP_BREAK_MEANING, "&7Disabilita la protezione della mappa");
                    yml.addDefault(NO_DIAMONDS_MEANING, "&7Niente diamanti");
                    yml.addDefault(NO_EMERALDS_MEANING, "&7Niente smeraldi");
                    yml.addDefault(HEALTH_BUFF_MEANING, "&7Aumento della vita");
                    yml.addDefault(SPEED_MEANING, "&7Velocità");
                    yml.addDefault(EVENTS_TIME_MEANING, "&7Tempo degli eventi");
                    yml.addDefault(RESPAWN_EVENT_TIME_MEANING, "&7Tempo di respawn");
                    yml.addDefault(SUBMENU_GAMEMODE_CHANGER_TITLE, "&8Cambio gamemode");
                    yml.addDefault(SUBMENU_OPTIONS_TITLE, "Opzioni");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_NAME, "&aAbilita AutoStart");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_AUTOSTART_LORE, Arrays.asList("&7Abilita l'inizio automatico della partita", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_NAME, "&aPossiblità di entrata");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_ALLOWJOIN_LORE, Arrays.asList("&7Dai agli altri la possibilità di entrare nel tuo privategame", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_NAME, "&aAbilita privategames");
                    yml.addDefault(SUBMENU_OPTIONS_ENABLE_PRIVATEGAMES_LORE, Arrays.asList("&7Abilita la possibiltà di giocare un privategame", "", "{state}"));
                    yml.addDefault(SUBMENU_OPTIONS_BACK_NAME, "&aIndietro");
                    yml.addDefault(SUBMENU_OPTIONS_BACK_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_TITLE, "Generatori");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_ITEM_LORE, Arrays.asList("&7Team: {team}", "&7Posizione: {location}", "&7Quantità: {amount}", "&7Delay: {delay}",  "§7Limite di genererazione: {spawnLimit}"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_TITLE, "Opzioni dei generatori");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME, "&aVai indietro");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME, "&aIndietro");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE, List.of("&7Torna al menu dei modificatori"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aAbilitato");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDisabilitato");
                    yml.addDefault(MENU_CLICK_TO_START_MEANING, "&eClicca per avviare la partita");
                    yml.addDefault(MENU_STARTING_MEANING, "&aAvviando...");

                    if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu") != null) {
                        for (String p : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu").getKeys(false)) {
                            for (String group : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + p + ".modes").getKeys(false)) {
                                if (group.equals("back-item")) {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&aIndietro");
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", List.of("&7Torna al menu dei modificatori"));
                                } else {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&a" + group);
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", Arrays.asList("&eClicca qui per cambiare la gamemode", "", "&7Puoi cambiare la gamemode con cui si giocherà solo se sei", "&7l'owner del party", "", "&eClicca per cambiare!"));
                                }
                            }
                        }
                    }
                    break;
                default:
                    yml.addDefault(HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eHelp &7- &8Author: Kiiya, Version: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg gui &7- &aOpen the private games menu",
                            "&7- &e/pg enable &7- &aEnable private games",
                            "&7- &e/pg disable &7- &aDisable private games",
                            "&7- &e/pg join <player> &7- &aJoin to the private game of a player",
                            "&7- &e/pg accept &7- &aAccept the last join request",
                            "&7- &e/pg deny &7- &aDeny the last join request",
                            "&7- &e/pg accept <player> &7- &aAccept the join request of a player",
                            "&7- &e/pg deny <player> &7- &aDecline the join request of a player",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(ADMIN_HELP_MESSAGE, Arrays.asList(
                            "&8&m-----------------------------------------------------",
                            "&6Private Games &7- &eAdmin Help &7- &8Author: Kiiya, Version: " + PrivateGames.getInstance().getDescription().getVersion(),
                            "",
                            "&7- &e/pg reload &7- &aReload the messages",
                            "&7- &e/pg enable admin &7- &aEnable the private games only for you (party too)",
                            "&7- &e/pg disable admin &7- &aDisable the private games only for you (party too)",
                            "&8&m-----------------------------------------------------"
                    ));
                    yml.addDefault(NOT_ENOUGH_ARGS, "&cNot enough args!");
                    yml.addDefault(COULDNT_FIND_PLAYER, "&cCouldn't find this player");
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
                    yml.addDefault(PRIVATE_GAME_UNABLE_TO_JOIN, "&cUnable to join to this arena! You have private games enabled and this arena isn't empty!");
                    yml.addDefault(PRIVATE_GAME_COULDNT_JOIN, "&cCouldnt join to the private game of this player!");
                    yml.addDefault(PRIVATE_GAME_CANT_IN_GAME, "&cYou cant do this in game!");
                    yml.addDefault(PRIVATE_GAME_CANT_JOIN_SELF, "&cYou cant join to your own private game!");
                    yml.addDefault(PRIVATE_ARENA_SCOREBOARD_PLACEHOLDER, "&7[P]");
                    yml.addDefault(PRIVATE_GAME_ENABLED_MODIFIERS, Arrays.asList("&6-----------------------------------------------------", "{player} &a&lhas enabled private game modifiers!", "{modifiers}", "&6-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_FORMAT, "&6- {modifier}");
                    yml.addDefault(PRIVATE_GAME_MODIFIERS_WITH_OPTION_FORMAT, "&6- {modifier}: &e{selected}");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED, Arrays.asList(
                            "&6&m-----------------------------------------------------",
                            "&e{player} &7is requesting to join your private game!",
                            "{buttons}",
                            "&6&m-----------------------------------------------------"));
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_SENT,
                            "&6&m-----------------------------------------------------\n" +
                                    "&7You've requested to join to the private game of &e{player}!\n" +
                                    "&6&m-----------------------------------------------------\n");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT, "&a&lAccept");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER, "&aClick to accept!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY, "&c&lDecline");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER, "&cClick to decline!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED, "&cYou've declined the request of &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED, "&aYou've accepted the request of &e{player}!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER, "&a&l{player} &ahas accepted your request!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_DENIED_REQUESTER, "&c&l{player} &chas declined your request!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_EXPIRED, "&cYour request has expired!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER, "&c&l{player} didn't request joining or this request has expired!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_NO_PENDING_REQUESTS, "&cYou don't have any pending requests!");
                    yml.addDefault(PRIVATE_ARENA_REQUEST_ALREADY_SENT, "&cYou've already sent a request to this player!");
                    yml.addDefault(MENU_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(MENU_BACK_ITEM_LORE, List.of("&7Go back to your bedwars gameplay"));
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
                    yml.addDefault(ITEM_SUBMENU_SPEED_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_NAME, "&aSpeed I");
                    yml.addDefault(ITEM_SUBMENU_SPEED_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_NAME, "&aSpeed II");
                    yml.addDefault(ITEM_SUBMENU_SPEED_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_NAME, "&aSpeed III");
                    yml.addDefault(ITEM_SUBMENU_SPEED_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_SPEED_BACK_ITEM_LORE, List.of("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_NAME, "&8Health Buff");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_NAME, "&aNormal Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_NAME, "&aDouble Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_NAME, "&aTriple Health");
                    yml.addDefault(ITEM_SUBMENU_HEALTH_BUFF_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_HEALTH_BUFF_BACK_ITEM_LORE, List.of("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_NAME, "&8Events Time");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_NAME, "&ax0.5 - Slower");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_NAME, "&ax1 - Normal");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_NAME, "&ax2 - Fast");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_NAME, "&ax4 - Faster");
                    yml.addDefault(ITEM_SUBMENU_EVENTS_TIME_IV_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_EVENTS_TIME_BACK_ITEM_LORE, List.of("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_NAME, "&8Respawn Time");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_NAME, "&a1 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_I_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_NAME, "&a5 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_II_LORE, List.of("{state}"));
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_NAME, "&a10 Second");
                    yml.addDefault(ITEM_SUBMENU_RESPAWN_TIME_III_LORE, List.of("{state}"));
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_RESPAWN_TIME_BACK_ITEM_LORE, List.of("&7Go Back to the settings menu"));
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
                    yml.addDefault(SUBMENU_OPTIONS_BACK_LORE, List.of("&7Go Back to the settings menu"));
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_TITLE, "Arena generators");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_ITEM_LORE, Arrays.asList("&7Team: {team}", "&7Location: {location}", "&7Amount: {amount}", "&7Delay: {delay}",  "§7Spawn Limit: {spawnLimit}"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_TITLE, "Generator options");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_GENERATORS_OPTIONS_BACK_ITEM_LORE, List.of("&7Go Back to the options menu"));
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_NAME, "&aGo Back");
                    yml.addDefault(SUBMENU_GENERATOR_OPTIONS_BACK_ITEM_LORE, List.of("&7Go Back to the generators menu"));
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_ENABLED, "&aEnabled");
                    yml.addDefault(SUBMENU_OPTIONS_MEANING_DISABLED, "&cDisabled");
                    yml.addDefault(MENU_CLICK_TO_START_MEANING, "&eClick to start!");
                    yml.addDefault(MENU_STARTING_MEANING, "&aStarting...");

                    if (mainConfig.getYml().getConfigurationSection("gamemode-changer-menu") != null) {
                        for (String p : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu").getKeys(false)) {
                            for (String group : mainConfig.getYml().getConfigurationSection("gamemode-changer-menu." + p + ".modes").getKeys(false)) {
                                if (group.equals("back-item")) {
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".name", "&aGo Back");
                                    yml.addDefault("addons.private-games.gamemode-changer-menu." + p + ".modes." + group + ".lore", List.of("&7Go back to the settings menu"));
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
            PRIVATE_ARENA_REQUEST_MESSAGE_RECEIVED = PATH + "game.request-message.request-received",
            PRIVATE_ARENA_REQUEST_MESSAGE_SENT = PATH + "game.request-message.request-sent",
            PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT = PATH + "game.request-message.request-buttons.accept.message",
            PRIVATE_ARENA_REQUEST_MESSAGE_ACCEPT_HOVER = PATH + "game.request-message.request-buttons.accept.hover",
            PRIVATE_ARENA_REQUEST_MESSAGE_DENY = PATH + "game.request-message.request-buttons.deny.message",
            PRIVATE_ARENA_REQUEST_MESSAGE_DENY_HOVER = PATH + "game.request-message.request-buttons.deny.hover",
            PRIVATE_ARENA_REQUEST_DENIED = PATH + "game.request-message.request.denied",
            PRIVATE_ARENA_REQUEST_ACCEPTED = PATH + "game.request-message.request.accepted",
            PRIVATE_ARENA_REQUEST_ACCEPTED_REQUESTER = PATH + "game.request-message.request.accepted-requester",
            PRIVATE_ARENA_REQUEST_DENIED_REQUESTER = PATH + "game.request-message.request.denied-requester",
            PRIVATE_ARENA_REQUEST_EXPIRED = PATH + "game.request-message.request.expired",
            PRIVATE_ARENA_REQUEST_EXPIRED_RECEIVER = PATH + "game.request-message.request.expired-receiver",
            PRIVATE_ARENA_REQUEST_NO_PENDING_REQUESTS = PATH + "game.request-message.request.no-pending-requests",
            PRIVATE_ARENA_REQUEST_ALREADY_SENT = PATH + "game.request-message.request.already-sent",
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
            PRIVATE_GAME_CANT_JOIN_SELF = PATH + "game.cant-join-self",
            PRIVATE_GAME_FEATURES_HELP_INGAME = PATH + "game.features-help-ingame",
            HELP_MESSAGE = PATH + "help-message",
            NOT_ENOUGH_ARGS = PATH + "not-enough-args",
            COULDNT_FIND_PLAYER = PATH + "couldnt-find-player",
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
