package ua.mei.mgui.api.hud;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.api.font.FontResourceManager;
import ua.mei.pfu.api.font.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerHud {
    public final HudGroup root = new HudGroup(this, List.of());
    private final List<ServerPlayerEntity> players = new ArrayList<>();

    public abstract void draw();

    public void tick(MinecraftServer server) {
        if (server.getTicks() % 5 == 0) {
            draw();

            for (ServerPlayerEntity player : players) {
                drawToPlayer(player);
            }
        }
    }

    public void show(ServerPlayerEntity player) {
        players.add(player);
        draw();
        drawToPlayer(player);
    }

    public void hide(ServerPlayerEntity player) {
        players.remove(player);
        player.sendMessageToClient(Text.empty(), true);
    }

    public void drawToPlayer(ServerPlayerEntity player) {
        MutableText text = root.render();
        if (root.xOffset != 0) {
            text = new TextFormatter(text).offset(root.xOffset).value;
        }
        player.sendMessageToClient(text, true);
    }

    public abstract FontResourceManager getResourceManager();
}
