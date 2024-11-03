package ua.mei.mgui.api.hud.part;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.mgui.api.hud.ServerHud;
import ua.mei.pfu.api.font.TextFormatter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused"})
public class HudGroup extends HudPart<HudGroup> {
    public final List<HudPart<?>> parts = new ArrayList<>();

    protected HudGroup(ServerHud hud, List<HudPart<?>> parts) {
        super(hud);
        this.parts.addAll(parts);
    }

    public static HudGroup empty(ServerHud hud) {
        return new HudGroup(hud, List.of());
    }

    public HudGroup addPart(HudPart<?> part) {
        if (parts.contains(part)) return this;

        this.parts.add(part);
        this.parts.sort((a, b) -> Integer.compare(b.getWidth(), a.getWidth()));

        return this;
    }

    public HudGroup removePart(HudPart<?> part) {
        this.parts.remove(part);
        this.parts.sort((a, b) -> Integer.compare(b.getWidth(), a.getWidth()));

        return this;
    }

    @Override
    public MutableText render() {
        MutableText text = Text.empty();

        if (!parts.isEmpty()) {
            HudPart<?> firstPart = parts.getFirst();

            for (HudPart<?> part : parts) {
                TextFormatter formatter = new TextFormatter(part.render());

                if (firstPart == part) {
                    formatter.offset(part.xOffset);
                } else {
                    formatter.spaceBefore(part.align.spaceBefore.apply(firstPart, part) - 1)
                            .offset(part.xOffset + part.align.offset.apply(firstPart, part));
                }

                text.append(formatter.value);
            }
        }

        return text;
    }

    @Override
    public int getWidth() {
        return this.parts
                .stream()
                .mapToInt(HudPart::getWidth)
                .max()
                .orElse(0);
    }
}
