package ua.mei.mgui.impl;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.mgui.api.font.GUITextureManager;
import ua.mei.pfu.api.FontResource;

@ApiStatus.Internal
public class VanillaTextures {
    public static FontResource resource = MGuiImpl.manager.requestFont("gui/vanilla");

    public static MutableText GENERIC_9X6;
    public static MutableText GENERIC_9X5;
    public static MutableText GENERIC_9X4;
    public static MutableText GENERIC_9X3;
    public static MutableText GENERIC_9X2;
    public static MutableText GENERIC_9X1;

    public static void load() {
        GENERIC_9X6 = GUITextureManager.requestGui("gui/container/generic_9x6.png", resource);
        GENERIC_9X5 = GUITextureManager.requestGui("gui/container/generic_9x5.png", resource);
        GENERIC_9X4 = GUITextureManager.requestGui("gui/container/generic_9x4.png", resource);
        GENERIC_9X3 = GUITextureManager.requestGui("gui/container/generic_9x3.png", resource);
        GENERIC_9X2 = GUITextureManager.requestGui("gui/container/generic_9x2.png", resource);
        GENERIC_9X1 = GUITextureManager.requestGui("gui/container/generic_9x1.png", resource);
    }

    public static MutableText fromScreenHandler(ScreenHandlerType<?> handler) {
        if (handler == ScreenHandlerType.GENERIC_9X1) {
            return GENERIC_9X1;
        } else if (handler == ScreenHandlerType.GENERIC_9X2) {
            return GENERIC_9X2;
        } else if (handler == ScreenHandlerType.GENERIC_9X3) {
            return GENERIC_9X3;
        } else if (handler == ScreenHandlerType.GENERIC_9X4) {
            return GENERIC_9X4;
        } else if (handler == ScreenHandlerType.GENERIC_9X5) {
            return GENERIC_9X5;
        } else if (handler == ScreenHandlerType.GENERIC_9X6) {
            return GENERIC_9X6;
        } else {
            return Text.empty();
        }
    }
}
