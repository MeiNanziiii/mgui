package ua.mei.mgui.impl;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.ApiStatus;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.mgui.api.hud.ServerHudRegistry;
import ua.mei.mgui.api.hud.part.HudGroup;

import java.util.*;

@ApiStatus.Internal
@SuppressWarnings({"unused"})
public class ServerHudRenderer {
    private static final Map<ServerPlayerEntity, List<ServerHud>> pendingHudUpdates = new HashMap<>();

    public static void renderHudForPlayer(ServerPlayerEntity player) {
        List<ServerHud> playerHudUpdates = pendingHudUpdates.getOrDefault(player, Collections.emptyList());

        HudGroup hudGroup = HudGroup.empty(null);

        List<ServerHud> filteredHuds = ServerHudRegistry.getRegisteredHuds().values()
                .stream()
                .filter(playerHudUpdates::contains)
                .toList();

        for (ServerHud hud : filteredHuds) {
            hud.tick(player);
            hudGroup.addPart(hud.root);
        }

        player.sendMessageToClient(hudGroup.render(), true);
    }

    public static void updateAllPlayers(MinecraftServer server) {
        pendingHudUpdates.keySet().forEach(ServerHudRenderer::renderHudForPlayer);
    }

    public static void addHud(ServerPlayerEntity player, ServerHud hud) {
        pendingHudUpdates.computeIfAbsent(player, p -> new ArrayList<>()).add(hud);
        renderHudForPlayer(player);
    }

    public static void removeHud(ServerPlayerEntity player, ServerHud hud) {
        List<ServerHud> playerHuds = pendingHudUpdates.get(player);

        if (playerHuds != null && playerHuds.remove(hud)) {
            if (playerHuds.isEmpty()) {
                player.sendMessageToClient(Text.empty(), true);
            } else {
                renderHudForPlayer(player);
            }
        }
    }
}
