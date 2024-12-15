package ua.mei.mgui.api.gui.element;

import ua.mei.pfu.api.FontResource;

public class Elements {
    public static GuiTexture texture(String path, int height, int y, FontResource font) {
        return new GuiTexture(font.requestGlyph(path, height, y + 13));
    }
}
