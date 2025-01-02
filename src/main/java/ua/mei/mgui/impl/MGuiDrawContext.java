package ua.mei.mgui.impl;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.mgui.api.gui.TexturedGui;
import ua.mei.mgui.api.gui.element.MGuiElement;
import ua.mei.pfu.api.util.TextBuilder;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
public class MGuiDrawContext {
    private final List<Object> elements = new ArrayList<>();
    private int x = 0;

    public MGuiDrawContext() {
    }

    public void x(int x) {
        this.x = x;
    }

    public void draw(MGuiElement element) {
        if (!this.elements.isEmpty() && this.elements.getLast() instanceof Integer space) {
            this.elements.set(this.elements.size() - 1, space + this.x);
        } else {
            this.elements.add(this.x - 8);
        }
        this.elements.add(element);
        this.elements.add(-element.getWidth() - this.x - 1);
    }

    public Text render(TexturedGui gui) {
        TextBuilder builder = new TextBuilder();

        if (gui.customTitle == null && !this.elements.isEmpty()) {
            this.elements.removeLast();
        }

        this.elements.forEach(obj -> {
            if (obj instanceof Integer space && space != 0) {
                builder.space(space);
            } else if (obj instanceof MGuiElement element) {
                builder.text(((MutableText) element.render()).formatted(Formatting.WHITE));
            }
        });

        if (gui.customTitle != null) {
            builder.space(8);
            builder.text(gui.customTitle);
        }

        return builder.build();
    }
}
