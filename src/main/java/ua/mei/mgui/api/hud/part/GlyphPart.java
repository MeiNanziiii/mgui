package ua.mei.mgui.api.hud.part;

import net.minecraft.text.MutableText;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.pfu.api.font.BitmapGlyph;
import ua.mei.pfu.api.font.FontResourceManager;

@SuppressWarnings({"unused"})
public class GlyphPart extends HudPart<GlyphPart> {
    public final BitmapGlyph glyph;
    public final int y;

    protected GlyphPart(ServerHud hud, BitmapGlyph glyph) {
        super(hud);

        this.glyph = glyph;
        this.y = glyph.provider.ascent();
    }

    public static GlyphPart create(ServerHud hud, String path, int y) {
        BitmapGlyph glyph = hud.getResourceManager().requestGlyph(path, y - 64);
        return new GlyphPart(hud, glyph);
    }

    public static GlyphPart create(ServerHud hud, String path, int height, int y) {
        BitmapGlyph glyph = hud.getResourceManager().requestGlyph(path, height, y - 64);
        return new GlyphPart(hud, glyph);
    }

    public static GlyphPart create(ServerHud hud, String path, int y, FontResourceManager manager) {
        BitmapGlyph glyph = manager.requestGlyph(path, y - 64);
        return new GlyphPart(hud, glyph);
    }

    @Override
    public MutableText render() {
        return this.glyph.value;
    }

    @Override
    public int getWidth() {
        return this.glyph.glyphWidth;
    }
}
