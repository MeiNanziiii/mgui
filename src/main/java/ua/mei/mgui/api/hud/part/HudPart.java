package ua.mei.mgui.api.hud.part;

import net.minecraft.text.MutableText;
import ua.mei.mgui.api.hud.HudAlign;
import ua.mei.mgui.api.hud.ServerHud;

@SuppressWarnings({"unchecked"})
public abstract class HudPart<T extends HudPart<T>> {
    public final ServerHud hud;

    public HudAlign align = HudAlign.CENTER;
    public int xOffset = 0;

    protected HudPart(ServerHud hud) {
        this.hud = hud;
    }

    public abstract MutableText render();

    public abstract int getWidth();

    public T align(HudAlign align) {
        this.align = align;
        return (T) this;
    }

    public T xOffset(int xOffset) {
        this.xOffset = xOffset;
        return (T) this;
    }
}
