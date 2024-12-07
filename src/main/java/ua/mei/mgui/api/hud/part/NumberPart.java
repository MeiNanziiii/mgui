package ua.mei.mgui.api.hud.part;

import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.impl.MGuiImpl;
import ua.mei.pfu.api.FontResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberPart extends HudGroup {
    public static final FontResource resource = MGuiImpl.manager.requestFont("numbers");

    public final Map<Character, GlyphPart> glyphs;
    public final int y;

    protected NumberPart(ServerHud hud, int y) {
        super(hud, List.of());

        this.glyphs = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            glyphs.put((char) ('0' + i), GlyphPart.create(hud, "hud/five/number_" + i + ".png", 128, y, resource));
        }
        glyphs.put('-', GlyphPart.create(hud, "hud/five/number_minus.png", 128, y, resource));

        this.y = y;

        setNumber(0);
    }

    public static NumberPart create(ServerHud hud, int y) {
        return new NumberPart(hud, y);
    }

    public NumberPart setNumber(int number) {
        this.parts.clear();

        int offset = 0;

        for (Character digitChar : String.valueOf(number).toCharArray()) {
            GlyphPart glyph = glyphs.get(digitChar);
            glyph = new GlyphPart(glyph.hud, glyph.glyph).xOffset(offset);
            parts.add(glyph);
            offset += glyph.getWidth() + 1;
        }

        return this;
    }
}
