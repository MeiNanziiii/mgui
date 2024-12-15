package ua.mei.mgui.api.gui.element;

import net.minecraft.text.Text;
import ua.mei.pfu.api.BitmapGlyph;

public class GuiTexture extends GuiElement {
    public final BitmapGlyph glyph;

    protected GuiTexture(BitmapGlyph glyph) {
        this.glyph = glyph;
    }

    @Override
    public Text render() {
        return glyph.value;
    }
}
