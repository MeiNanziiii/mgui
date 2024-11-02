package ua.mei.mgui.api.font;

import net.minecraft.text.MutableText;
import ua.mei.pfu.api.font.BitmapGlyph;
import ua.mei.pfu.api.font.FontResourceManager;

public class GUITextureManager {
    public static MutableText requestGui(String path, FontResourceManager manager) {
        BitmapGlyph glyph = manager.requestGlyph(path, 256, 0);
        return glyph.space(-8, -glyph.glyphWidth + 7);
    }
}
