package ua.mei.mgui.impl;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class MGuiImpl implements ModInitializer {
    public static final String MOD_ID = "mgui";
    public static final boolean GUI_ENABLED = false;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui");

        VanillaTextures.load();

        ServerTickEvents.END_SERVER_TICK.register(ServerHudRenderer::updateAllPlayers);
    }
}
