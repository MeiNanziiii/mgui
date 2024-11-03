package ua.mei.mgui.api.hud;

import net.minecraft.server.network.ServerPlayerEntity;
import ua.mei.mgui.api.hud.part.HudGroup;
import ua.mei.mgui.impl.ServerHudRenderer;
import ua.mei.pfu.api.font.FontResourceManager;

@SuppressWarnings({"unused"})
public abstract class ServerHud {
    public final HudGroup root = HudGroup.empty(this);

    public void update(ServerPlayerEntity player) {
    }

    public void tick(ServerPlayerEntity player) {
        if (player.server.getTicks() % 5 == 0) {
            update(player);
        }
    }

    public void show(ServerPlayerEntity player) {
        ServerHudRenderer.addHud(player, this);
    }

    public void hide(ServerPlayerEntity player) {
        ServerHudRenderer.removeHud(player, this);
    }

    public abstract FontResourceManager getResourceManager();
}
