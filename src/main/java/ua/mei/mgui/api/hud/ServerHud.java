package ua.mei.mgui.api.hud;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ua.mei.pfu.api.font.FontResourceManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerHud {
    private final List<ServerPlayerEntity> players = new ArrayList<>();

    public abstract void draw(HudDrawContext context);

    public void tick(MinecraftServer server) {
        if (server.getTicks() % 5 == 0) {
            for (ServerPlayerEntity player : players) {
                drawToPlayer(player);
            }
        }
    }

    public void show(ServerPlayerEntity player) {
        players.add(player);
        drawToPlayer(player);
    }

    public void hide(ServerPlayerEntity player) {
        players.remove(player);
        player.sendMessageToClient(Text.empty(), true);
    }

    public void drawToPlayer(ServerPlayerEntity player) {
        HudDrawContext context = new HudDrawContext((player));
        draw(context);
        player.sendMessageToClient(context.draw(), true);
    }

    public abstract FontResourceManager getResourceManager();
}
