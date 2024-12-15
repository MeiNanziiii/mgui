package ua.mei.mgui.test.gui;

import eu.pb4.sgui.api.GuiHelpers;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ua.mei.mgui.api.gui.TexturedGui;

public class TestGui extends TexturedGui {
    public TestGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X3, player, false);

        setSlot(13, new GuiElementBuilder()
                .setItem(Items.DIAMOND)
                .setCallback(() -> player.sendMessage(Text.literal("Hello world!")))
                .build()
        );
    }
}
