package me.notlewx.pgames.support.version;

import me.notlewx.pgames.support.IVersion;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_8_R3 implements IVersion {
    @Override
    public void cancelMessageTo(Player player) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(""));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
