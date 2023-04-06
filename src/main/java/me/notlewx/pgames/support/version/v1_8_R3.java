package me.notlewx.pgames.support.version;

import me.notlewx.pgames.support.IVersion;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class v1_8_R3 implements IVersion {
    @Override
    public void cancelMessageTo(@NotNull Player player) {

    }
}
