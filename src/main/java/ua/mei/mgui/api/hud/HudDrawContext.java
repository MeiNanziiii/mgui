package ua.mei.mgui.api.hud;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.mgui.api.font.HudElement;
import ua.mei.pfu.api.font.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class HudDrawContext {
    public final ServerPlayerEntity player;
    public final List<HudElement> elements;

    public HudDrawContext(ServerPlayerEntity player) {
        this.player = player;
        this.elements = new ArrayList<>();
    }

    public void drawElement(HudElement element) {
        this.elements.add(element);
    }

    public MutableText draw() {
        elements.sort((a, b) -> Integer.compare(b.glyph.glyphWidth, a.glyph.glyphWidth));

        MutableText text = Text.empty();

        for (int i = 0; i < elements.size(); i++) {
            HudElement element = elements.get(i);
            TextFormatter formatter = element.glyph.formatter();

            if (i != 0) {
                HudElement firstElement = elements.getFirst();

                text.append(formatter.spaceBefore(-element.glyph.glyphWidth - 1).offset(element.x - (firstElement.glyph.glyphWidth - element.glyph.glyphWidth) / 2).value);
            } else {
                text.append(formatter.offset(element.x).value);
            }
        }

        return text;
    }
}
