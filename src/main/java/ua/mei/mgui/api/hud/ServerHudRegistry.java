package ua.mei.mgui.api.hud;

import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ServerHudRegistry {
    private static Map<Identifier, ServerHud> registeredHuds = new LinkedHashMap<>();

    public static ServerHud register(Identifier identifier, Supplier<ServerHud> hudSupplier) {
        if (registeredHuds.containsKey(identifier)) {
            throw new IllegalStateException("This identifier is already in use!");
        }

        ServerHud hud = hudSupplier.get();
        registeredHuds.put(identifier, hud);

        registeredHuds = registeredHuds.entrySet()
                .stream()
                .sorted(Map.Entry.<Identifier, ServerHud>comparingByValue(Comparator.comparingInt(h -> h.root.getWidth())).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        return hud;
    }

    public static Map<Identifier, ServerHud> getRegisteredHuds() {
        return Collections.unmodifiableMap(registeredHuds);
    }
}
