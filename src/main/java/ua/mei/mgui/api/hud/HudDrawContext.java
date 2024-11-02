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
                int midSize = previousElement.glyph.glyphWidth / 2 + element.glyph.glyphWidth / 2;
                int requiredSize = previousElement.glyph.glyphWidth - midSize;
                int leftSize = requiredSize < 0 ? -requiredSize : 0;
                int rightSize = requiredSize > 0 ? requiredSize : 0;
                text.append(element.glyph.formatter().space(-previousElement.x - 1 - midSize + element.x + leftSize, rightSize).value);
            } else {
                text.append(element.glyph.formatter().spaceBefore(element.x).value);
            }
        }

        return text;
    }
}
