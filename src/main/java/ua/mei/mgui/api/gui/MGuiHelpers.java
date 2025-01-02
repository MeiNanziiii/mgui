package ua.mei.mgui.api.gui;

import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

import java.util.List;

public class MGuiHelpers {
    private static final List<ScreenHandlerType<GenericContainerScreenHandler>> genericScreenHandlers = List.of(
            ScreenHandlerType.GENERIC_9X1,
            ScreenHandlerType.GENERIC_9X2,
            ScreenHandlerType.GENERIC_9X3,
            ScreenHandlerType.GENERIC_9X4,
            ScreenHandlerType.GENERIC_9X5,
            ScreenHandlerType.GENERIC_9X6
    );

    public static boolean isGeneric(ScreenHandlerType<?> screenHandlerType) {
        return genericScreenHandlers.contains(screenHandlerType);
    }
}
