package ua.mei.mgui.test;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import ua.mei.mgui.api.gui.element.GlyphElement;
import ua.mei.mgui.test.gui.TestGui;
import ua.mei.pfu.api.FontResourceManager;

public class MGuiTest implements ModInitializer {
    public static FontResourceManager manager;

    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mgui_test");

        manager = FontResourceManager.create("mgui_test");

        new GlyphElement("gui/test.png", 64, 0, TestGui.resource);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("test_gui")
                    .requires(player -> player.hasPermissionLevel(4))
                    .executes(ctx -> {
                        new TestGui(ctx.getSource().getPlayer()).open();
                        return 1;
                    })
            );
        });
    }
}
