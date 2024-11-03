package ua.mei.mgui.api.hud;

import ua.mei.mgui.api.hud.part.HudPart;

@SuppressWarnings({"unused"})
public enum HudAlign {
    LEFT(
            (firstPart, part) -> -part.getWidth(),
            (firstPart, part) -> -firstPart.getWidth() + part.getWidth()
    ),
    CENTER(
            (firstPart, part) -> -part.getWidth(),
            (firstPart, part) -> (firstPart.getWidth() - part.getWidth()) / -2
    ),
    RIGHT(
            (firstPart, part) -> -part.getWidth(),
            (firstPart, part) -> 0
    );

    public final HudAlignFunction spaceBefore;
    public final HudAlignFunction offset;

    HudAlign(HudAlignFunction spaceBefore, HudAlignFunction offset) {
        this.spaceBefore = spaceBefore;
        this.offset = offset;
    }

    @FunctionalInterface
    public interface HudAlignFunction {
        Integer apply(HudPart<?> firstPart, HudPart<?> part);
    }
}
