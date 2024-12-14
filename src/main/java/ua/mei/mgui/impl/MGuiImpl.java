package ua.mei.mgui.impl;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.pfu.api.FontResourceManager;
import ua.mei.pfu.api.util.FontSpaceUtils;

@ApiStatus.Internal
public class MGuiImpl implements ModInitializer {
    public static final String MOD_ID = "mgui";
    public static final FontResourceManager manager = FontResourceManager.create(MOD_ID);

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets(MOD_ID);
        FontSpaceUtils.requestRange(-256, 256);
        VanillaTextures.load();
    }
}
