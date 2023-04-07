package me.notlewx.pgames.support.version;

import me.notlewx.pgames.support.IVersion;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_12_R1 implements IVersion {
    @Override
    public void cancelMessageTo(Player player) {
        MinecraftServer server = MinecraftServer.getServer();
        NetworkManager networkManager = ((CraftPlayer) player).getHandle().playerConnection.networkManager;
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(""));
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
}
