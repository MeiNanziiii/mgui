package ua.mei.mgui.test;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import ua.mei.pfu.api.font.FontResourceManager;

public class MGuiTest implements ModInitializer {
    public static FontResourceManager manager;
    public static TestHud hud;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui_test");

        manager = FontResourceManager.create("mgui_test", "mgui_test", "test");
        hud = new TestHud();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            hud.show(handler.player);
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            hud.hide(handler.player);
        });
        ServerTickEvents.END_SERVER_TICK.register(hud::tick);
    }
}
