package ua.mei.mgui.api.gui;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.mei.mgui.api.gui.element.GuiElement;
import ua.mei.mgui.api.gui.element.GuiTexture;
import ua.mei.pfu.api.util.TextBuilder;
import ua.mei.pfu.api.util.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class GuiDrawContext {
    private final List<GuiElement> elements = new ArrayList<>();

    protected GuiDrawContext() {
    }

    public void draw(GuiElement element) {
        elements.add(element);
    }

    protected Text render(TexturedGui gui) {
        TextBuilder builder = new TextBuilder();
        builder.space(-8);

        elements.forEach(element -> {
            builder.space(element.x);
            builder.text(((MutableText) element.render()).formatted(Formatting.WHITE));
            builder.space(-element.x);

            if (element instanceof GuiTexture texture) {
                builder.space(-texture.glyph.glyphWidth - 1);
            }
        });

        builder.space(8);
        builder.text(gui.customText);

        return builder.build();
    }
}
