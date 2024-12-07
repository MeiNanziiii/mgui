package ua.mei.mgui.test;

import net.minecraft.server.network.ServerPlayerEntity;
import ua.mei.mgui.api.hud.HudAlign;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.api.hud.part.NumberPart;
import ua.mei.pfu.api.FontResource;

public class YetAnotherTestHud extends ServerHud {
    public final NumberPart ticks;

    public YetAnotherTestHud() {
        ticks = NumberPart.create(this, 8);
        ticks.xOffset(96).align(HudAlign.LEFT);

        root.addPart(ticks);
    }

    @Override
    public void update(ServerPlayerEntity player) {
        ticks.setNumber(player.server.getTicks());
    }

    @Override
    public void tick(ServerPlayerEntity player) {
        update(player);
    }

    @Override
    public FontResource getResource() {
        return MGuiTest.manager.requestFont("hud/test_2");
    }
}
