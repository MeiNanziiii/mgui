package ua.mei.mgui.impl;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.mgui.api.gui.element.VanillaElements;
import ua.mei.pfu.api.FontResourceManager;
import ua.mei.pfu.api.util.FontSpaceUtils;

import java.util.List;

@ApiStatus.Internal
public class MGuiImpl implements ModInitializer {
    public static final String MOD_ID = "mgui";
    public static final FontResourceManager manager = FontResourceManager.create(MOD_ID);
    public static final List<ScreenHandlerType<GenericContainerScreenHandler>> genericScreenHandlers = List.of(
            ScreenHandlerType.GENERIC_9X1,
            ScreenHandlerType.GENERIC_9X2,
            ScreenHandlerType.GENERIC_9X3,
            ScreenHandlerType.GENERIC_9X4,
            ScreenHandlerType.GENERIC_9X5,
            ScreenHandlerType.GENERIC_9X6
    );

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets(MOD_ID);
        FontSpaceUtils.requestRange(-256, 256);
        VanillaElements.load();
    }
}
