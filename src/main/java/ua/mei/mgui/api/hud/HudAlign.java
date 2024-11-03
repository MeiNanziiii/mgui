package ua.mei.mgui.api.hud;

import java.util.function.BiFunction;

public enum HudAlign {
    LEFT(
            (firstElement, element) -> -element.glyph.glyphWidth,
            (firstElement, element) -> -(firstElement.glyph.glyphWidth - element.glyph.glyphWidth)
    ),
    CENTER(
            (firstElement, element) -> -element.glyph.glyphWidth,
            (firstElement, element) -> -((firstElement.glyph.glyphWidth - element.glyph.glyphWidth) / 2)
    ),
    RIGHT(
            (firstElement, element) -> -element.glyph.glyphWidth,
            (firstElement, element) -> 0
    );

    public final BiFunction<HudElement, HudElement, Integer> spaceBefore;
    public final BiFunction<HudElement, HudElement, Integer> spaceAfter;

    HudAlign(BiFunction<HudElement, HudElement, Integer> spaceBefore, BiFunction<HudElement, HudElement, Integer> spaceAfter) {
        this.spaceBefore = spaceBefore;
        this.spaceAfter = spaceAfter;
    }
}
