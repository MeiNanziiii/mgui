package ua.mei.mgui.api.font;

import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.pfu.api.font.BitmapGlyph;

@SuppressWarnings({"unused"})
public class HudElement {
    public final BitmapGlyph glyph;

    public final int y;
    public int x = 0;

    private HudElement(BitmapGlyph glyph) {
        this.glyph = glyph;
        this.y = glyph.provider.ascent();
    }

    public static HudElement create(ServerHud hud, String path, int y) {
        return new HudElement(hud.getResourceManager().requestGlyph(path, y - 64));
    }

    public static HudElement create(ServerHud hud, String path, int height, int y) {
        return new HudElement(hud.getResourceManager().requestGlyph(path, height, y - 64));
    }
}
