package ua.mei.mgui.api.hud;

import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ServerHudRegistry {
    private static final Map<Identifier, ServerHud> registeredHuds = new LinkedHashMap<>();

    public static ServerHud register(Identifier identifier, Supplier<ServerHud> hudSupplier) {
        if (registeredHuds.containsKey(identifier)) {
            throw new IllegalStateException("This identifier is already in use!");
        }

        ServerHud hud = hudSupplier.get();
        registeredHuds.put(identifier, hud);

        registeredHuds.entrySet()
                .stream()
                .sorted(Map.Entry.<Identifier, ServerHud>comparingByValue(Comparator.comparingInt(h -> h.root.getWidth())).reversed())
                .forEachOrdered(entry -> {
                    registeredHuds.remove(entry.getKey());
                    registeredHuds.put(entry.getKey(), entry.getValue());
                });

        return hud;
    }

    public static Map<Identifier, ServerHud> getRegisteredHuds() {
        return Collections.unmodifiableMap(registeredHuds);
    }
}
