package ua.mei.mgui.api.gui.element;

import net.minecraft.text.Text;
import ua.mei.pfu.api.BitmapGlyph;
import ua.mei.pfu.api.FontResource;

public class GlyphElement extends MGuiElement {
    public final BitmapGlyph glyph;

    public GlyphElement(BitmapGlyph glyph) {
        this.glyph = glyph;
    }

    public GlyphElement(String path, int height, int y, FontResource resource) {
        this(resource.requestGlyph(path, height, y + 13));
    }

    @Override
    public Text render() {
        return this.glyph.value;
    }

    @Override
    public int getWidth() {
        return this.glyph.glyphWidth;
    }
}
