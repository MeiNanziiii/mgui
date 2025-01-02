package ua.mei.mgui.api.gui.element;

import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import ua.mei.mgui.impl.MGuiImpl;
import ua.mei.pfu.api.FontResource;

public class VanillaElements {
    private static final FontResource resource = MGuiImpl.manager.requestFont("gui/vanilla");

    public static final MGuiElement GENERIC_9X1 = new GlyphElement("gui/container/generic_9x1.png", 256, 0, resource);
    public static final MGuiElement GENERIC_9X2 = new GlyphElement("gui/container/generic_9x2.png", 256, 0, resource);
    public static final MGuiElement GENERIC_9X3 = new GlyphElement("gui/container/generic_9x3.png", 256, 0, resource);
    public static final MGuiElement GENERIC_9X4 = new GlyphElement("gui/container/generic_9x4.png", 256, 0, resource);
    public static final MGuiElement GENERIC_9X5 = new GlyphElement("gui/container/generic_9x5.png", 256, 0, resource);
    public static final MGuiElement GENERIC_9X6 = new GlyphElement("gui/container/generic_9x6.png", 256, 0, resource);

    public static void load() {
    }

    public static @Nullable MGuiElement fromScreenHandler(ScreenHandlerType<?> handler) {
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
            return null;
        }
    }
}
