package ua.mei.mgui.api.font;

import net.minecraft.text.MutableText;
import ua.mei.pfu.api.BitmapGlyph;
import ua.mei.pfu.api.FontResource;
import ua.mei.pfu.api.util.TextFormatter;

public class GUITextureManager {
    public static MutableText requestGui(String path, FontResource resource) {
        BitmapGlyph glyph = resource.requestGlyph(path, 256, 13);
        return new TextFormatter(glyph.value).space(-8, -glyph.glyphWidth + 7).value;
    }
}
