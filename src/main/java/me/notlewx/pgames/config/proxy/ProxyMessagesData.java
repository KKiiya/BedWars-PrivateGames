package me.notlewx.pgames.config.proxy;

import com.andrei1058.bedwars.proxy.api.Language;
import me.notlewx.pgames.api.PGamesAPI;
import java.util.List;

public class ProxyMessagesData {
    private static final List<Language> languages = PGamesAPI.getBwProxyApi().getLanguageUtil().getLanguages();
    public ProxyMessagesData() {
        setupMessages();
    }
    public void setupMessages() {
        for (Language l : languages) {
            switch (l.getIso()) {
                default :

            }
        }
    }
}
