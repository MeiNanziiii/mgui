package ua.mei.mgui.test.gui;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ua.mei.mgui.api.gui.GuiDrawContext;
import ua.mei.mgui.api.gui.TexturedGui;
import ua.mei.mgui.api.gui.element.Elements;
import ua.mei.mgui.api.gui.element.GuiElement;
import ua.mei.mgui.api.gui.element.VanillaElements;
import ua.mei.mgui.test.MGuiTest;

public class TestGui extends TexturedGui {
    public static final GuiElement texture = Elements.texture("gui/test.png", 64, 0, MGuiTest.manager.requestFont("test_gui"));;

    public TestGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X3, player, false);

        setSlot(18, new GuiElementBuilder()
                .setItem(Items.DIAMOND)
                .setCallback(() -> texture.x--)
                .build()
        );
        setSlot(26, new GuiElementBuilder()
                .setItem(Items.DIAMOND)
                .setCallback(() -> texture.x++)
                .build()
        );

        customText = Text.literal("LOL LMAO");
    }

    @Override
    public void drawGui(GuiDrawContext context) {
        context.draw(VanillaElements.GENERIC_9X3);
        context.draw(VanillaElements.GENERIC_9X3);
        context.draw(texture);
        context.draw(VanillaElements.GENERIC_9X3);
    }
}
