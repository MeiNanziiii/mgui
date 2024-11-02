package ua.mei.mgui.api.hud;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.mgui.api.font.HudElement;
import ua.mei.pfu.impl.PolymerFontUtilsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        MutableText text = Text.empty();

        for (int i = 0; i < elements.size(); i++) {
            HudElement element = elements.get(i);

            if (i != 0) {
                HudElement previousElement = elements.get(i - 1);
                text.append(element.glyph.spaceBefore(-previousElement.x - 1 - (previousElement.glyph.glyphWidth / 2 + element.glyph.glyphWidth / 2) + element.x));
            } else {
                text.append(element.glyph.spaceBefore(element.x));
            }
        }

        return text;
    }
}
