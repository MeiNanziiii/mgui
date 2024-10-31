package ua.mei.mgui.impl;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.screen.NamedScreenHandlerFactory;

public class MGuiImpl implements ModInitializer {
    public static final String MOD_ID = "mgui";

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui");
        
        VanillaTextures.load();
    }
}
