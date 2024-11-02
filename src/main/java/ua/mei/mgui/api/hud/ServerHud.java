package ua.mei.mgui.api.hud;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.font.FontResourceManager;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerHud {
    private final List<ServerPlayerEntity> players = new ArrayList<>();

    public MutableText draw(HudDrawContext context) {
        return context.draw();
    }

    public void tick(MinecraftServer server) {
        // if (server.getTicks() % 5 == 0) {
            for (ServerPlayerEntity player : players) {
                player.sendMessageToClient(draw(new HudDrawContext((player))), true);
            }
        // }
    }

    public void show(ServerPlayerEntity player) {
        players.add(player);
        player.sendMessageToClient(draw(new HudDrawContext((player))), true);
    }

    public void hide(ServerPlayerEntity player) {
        players.remove(player);
        player.sendMessageToClient(Text.empty(), true);
    }

    public abstract FontResourceManager getResourceManager();
}
