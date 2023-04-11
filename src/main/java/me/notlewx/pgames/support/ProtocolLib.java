package me.notlewx.pgames.support;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.netty.WirePacket;
import me.notlewx.pgames.PrivateGames;
import me.notlewx.pgames.util.Utility;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class ProtocolLib {
    public static void cancelMessageTo(Player player) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(PrivateGames.getPlugins(), ListenerPriority.HIGHEST, PacketType.Play.Server.CHAT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                String json = event.getPacket().getChatComponents().read(0).getJson();
                System.out.println(json);
            }
        });
    }
}
