package ua.mei.mgui.test;

import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.api.hud.part.GlyphPart;
import ua.mei.pfu.api.font.FontResourceManager;

public class YetAnotherTestHud extends ServerHud {
    public YetAnotherTestHud() {
        root.addPart(GlyphPart.create(this, "gui/sprite_2.png", 192));
    }

    @Override
    public FontResourceManager getResourceManager() {
        return MGuiTest.manager;
    }
}
