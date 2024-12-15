package ua.mei.mgui.api.gui.element;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.mgui.impl.MGuiImpl;
import ua.mei.pfu.api.FontResource;

@ApiStatus.Internal
public class VanillaElements {
    private static final FontResource resource = MGuiImpl.manager.requestFont("gui/vanilla");

    public static final GuiElement GENERIC_9X1 = Elements.texture("gui/container/generic_9x1.png", 256, 0, resource);
    public static final GuiElement GENERIC_9X2 = Elements.texture("gui/container/generic_9x2.png", 256, 0, resource);
    public static final GuiElement GENERIC_9X3 = Elements.texture("gui/container/generic_9x3.png", 256, 0, resource);
    public static final GuiElement GENERIC_9X4 = Elements.texture("gui/container/generic_9x4.png", 256, 0, resource);
    public static final GuiElement GENERIC_9X5 = Elements.texture("gui/container/generic_9x5.png", 256, 0, resource);
    public static final GuiElement GENERIC_9X6 = Elements.texture("gui/container/generic_9x6.png", 256, 0, resource);

    public static void load() {

    }

    public static Text fromScreenHandler(ScreenHandlerType<?> handler) {
        if (handler == ScreenHandlerType.GENERIC_9X1) {
            return GENERIC_9X1.render();
        } else if (handler == ScreenHandlerType.GENERIC_9X2) {
            return GENERIC_9X2.render();
        } else if (handler == ScreenHandlerType.GENERIC_9X3) {
            return GENERIC_9X3.render();
        } else if (handler == ScreenHandlerType.GENERIC_9X4) {
            return GENERIC_9X4.render();
        } else if (handler == ScreenHandlerType.GENERIC_9X5) {
            return GENERIC_9X5.render();
        } else if (handler == ScreenHandlerType.GENERIC_9X6) {
            return GENERIC_9X6.render();
        } else {
            return Text.empty();
        }
    }
}
