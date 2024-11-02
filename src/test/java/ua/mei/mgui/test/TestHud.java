package ua.mei.mgui.test;

import net.minecraft.text.MutableText;
import ua.mei.mgui.api.font.HudElement;
import ua.mei.mgui.api.hud.HudDrawContext;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.pfu.api.font.FontResourceManager;

public class TestHud extends ServerHud {
    public final HudElement firstElement;
    public final HudElement secondElement;
    public final HudElement thirdElement;

    public TestHud() {
        this.firstElement = HudElement.create(this, "gui/sprite_2.png", 96);
        this.secondElement = HudElement.create(this, "gui/sprite_0.png", 64);
        this.thirdElement = HudElement.create(this, "gui/sprite_1.png", 32);
    }

    @Override
    public MutableText draw(HudDrawContext context) {
        firstElement.x = (int) Math.clamp(context.player.getX(), -256, 256);
        secondElement.x = -firstElement.x;
        thirdElement.x = (int) Math.clamp(context.player.getY(), -256, 256);
        context.drawElement(firstElement);
        context.drawElement(secondElement);
        context.drawElement(thirdElement);
        return super.draw(context);
    }

    @Override
    public FontResourceManager getResourceManager() {
        return MGuiTest.manager;
    }
}
