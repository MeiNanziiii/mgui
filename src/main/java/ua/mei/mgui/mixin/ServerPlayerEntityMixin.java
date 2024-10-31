package ua.mei.mgui.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ua.mei.mgui.impl.VanillaTextures;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @ModifyArg(method = "openHandledScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"))
    private Packet<ClientPlayPacketListener> mgui$replaceName(Packet<ClientPlayPacketListener> par1) {
        OpenScreenS2CPacket packet = (OpenScreenS2CPacket) par1;
        return new OpenScreenS2CPacket(packet.getSyncId(), packet.getScreenHandlerType(), Text.empty().append(VanillaTextures.fromScreenHandler(packet.getScreenHandlerType()).formatted(Formatting.WHITE)).append(packet.getName()));
    }
}
