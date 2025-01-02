package ua.mei.mgui.test.gui;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ua.mei.mgui.api.gui.TexturedGui;
import ua.mei.mgui.api.gui.element.GlyphElement;
import ua.mei.mgui.api.gui.element.MGuiElement;
import ua.mei.mgui.api.gui.element.VanillaElements;
import ua.mei.mgui.impl.MGuiDrawContext;
import ua.mei.mgui.test.MGuiTest;
import ua.mei.pfu.api.FontResource;

public class TestGui extends TexturedGui {
    public static final FontResource resource = MGuiTest.manager.requestFont("test_gui");
    public final MGuiElement texture = new GlyphElement("gui/test.png", 64, 0, resource);
    public int x = 0;

    public TestGui(ServerPlayerEntity player) {
        super(ScreenHandlerType.GENERIC_9X3, player, false);

        setSlot(18, new GuiElementBuilder()
                .setItem(Items.DIAMOND)
                .setCallback(() -> x--)
                .build()
        );
        setSlot(26, new GuiElementBuilder()
                .setItem(Items.DIAMOND)
                .setCallback(() -> x++)
                .build()
        );

        customTitle = Text.literal("LOL LMAO");
    }

    @Override
    public void draw(MGuiDrawContext context) {
        context.x(x);
        context.draw(VanillaElements.GENERIC_9X3);
        context.draw(VanillaElements.GENERIC_9X3);
        context.draw(texture);
        context.x(0);
        context.draw(VanillaElements.GENERIC_9X3);
    }
}
