package ua.mei.mgui.test;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.util.Identifier;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.api.hud.ServerHudRegistry;
import ua.mei.pfu.api.FontResourceManager;

public class MGuiTest implements ModInitializer {
    public static FontResourceManager manager;
    public static ServerHud hud;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui_test");

        manager = FontResourceManager.create("mgui_test");
        hud = ServerHudRegistry.register(Identifier.of("mgui_test:test_hud"), YetAnotherTestHud::new);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            hud.show(handler.player);
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            hud.hide(handler.player);
        });
    }
}
