package ua.mei.mgui.api.hud;

@SuppressWarnings({"unused"})
public enum HudAlign {
    LEFT(
            (firstElement, element, context) -> -element.getWidth(context),
            (firstElement, element, context) -> -firstElement.getWidth(context) + element.getWidth(context)
    ),
    CENTER(
            (firstElement, element, context) -> -element.getWidth(context),
            (firstElement, element, context) -> (firstElement.getWidth(context) - element.getWidth(context)) / -2
    ),
    RIGHT(
            (firstElement, element, context) -> -element.getWidth(context),
            (firstElement, element, context) -> 0
    );

    public final HudAlignFunction spaceBefore;
    public final HudAlignFunction offset;

    HudAlign(HudAlignFunction spaceBefore, HudAlignFunction offset) {
        this.spaceBefore = spaceBefore;
        this.offset = offset;
    }

    @FunctionalInterface
    public interface HudAlignFunction {
        Integer apply(HudElement firstElement, HudElement element, HudDrawContext context);
    }
}
