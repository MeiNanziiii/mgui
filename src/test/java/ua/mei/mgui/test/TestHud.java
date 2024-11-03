package ua.mei.mgui.test;

import ua.mei.mgui.api.hud.HudAlign;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.api.hud.part.GlyphPart;
import ua.mei.mgui.api.hud.part.HudGroup;
import ua.mei.pfu.api.font.FontResourceManager;

public class TestHud extends ServerHud {
    public TestHud() {
        HudGroup group = HudGroup.empty(this)
                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 192)
                        .align(HudAlign.LEFT)
                )
                .addPart(HudGroup.empty(this)
                        .addPart(GlyphPart.create(this, "gui/sprite_1.png", 164)
                                .align(HudAlign.RIGHT)
                        )
                        .addPart(GlyphPart.create(this, "gui/sprite_0.png", 128))
                )
                .addPart(HudGroup.empty(this)
                        .addPart(HudGroup.empty(this)
                                .addPart(GlyphPart.create(this, "gui/sprite_1.png", 96))
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 64)
                                        .align(HudAlign.LEFT)
                                )
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 32)
                                        .align(HudAlign.RIGHT)
                                )
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 16))
                                .xOffset(-64)
                        )
                        .addPart(HudGroup.empty(this)
                                .addPart(GlyphPart.create(this, "gui/sprite_1.png", 96))
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 64)
                                        .align(HudAlign.LEFT)
                                )
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 32)
                                        .align(HudAlign.RIGHT)
                                )
                                .addPart(GlyphPart.create(this, "gui/sprite_2.png", 16))
                                .xOffset(64)
                        )
                );

        root.addPart(group);
        root.xOffset(-128);
    }

    @Override
    public FontResourceManager getResourceManager() {
        return MGuiTest.manager;
    }
}
