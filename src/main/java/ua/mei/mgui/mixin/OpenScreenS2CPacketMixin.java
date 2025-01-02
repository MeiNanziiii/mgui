package ua.mei.mgui.mixin;

import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.mei.mgui.api.gui.MGuiHelpers;
import ua.mei.mgui.api.gui.element.MGuiElement;
import ua.mei.mgui.api.gui.element.VanillaElements;
import ua.mei.pfu.api.util.TextBuilder;

@Mixin(OpenScreenS2CPacket.class)
public abstract class OpenScreenS2CPacketMixin {
    @Shadow
    @Final
    private Text name;

    @Shadow
    @Final
    private ScreenHandlerType<?> screenHandlerId;

    @Inject(method = "getName", at = @At("HEAD"), cancellable = true)
    public void mgui$replaceName(CallbackInfoReturnable<Text> cir) {
        if (MGuiHelpers.isGeneric(this.screenHandlerId)) {
            MGuiElement element = VanillaElements.fromScreenHandler(this.screenHandlerId);

            if (element != null) {
                cir.setReturnValue(new TextBuilder()
                        .space(-8)
                        .text(((MutableText) element.render()).formatted(Formatting.WHITE))
                        .space(-element.getWidth() + 7)
                        .text(this.name)
                        .build()
                );
            }
        }
    }
}
