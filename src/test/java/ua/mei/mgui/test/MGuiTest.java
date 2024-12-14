package ua.mei.mgui.test;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import ua.mei.pfu.api.FontResourceManager;

public class MGuiTest implements ModInitializer {
    public static FontResourceManager manager;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui_test");

        manager = FontResourceManager.create("mgui_test");
    }
}
