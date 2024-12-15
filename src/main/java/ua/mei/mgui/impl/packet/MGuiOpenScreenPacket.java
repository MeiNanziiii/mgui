package ua.mei.mgui.impl.packet;

import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;

public class MGuiOpenScreenPacket extends OpenScreenS2CPacket {
    private final Text finalName;

    public MGuiOpenScreenPacket(int syncId, ScreenHandlerType<?> screenHandlerId, Text name) {
        super(syncId, screenHandlerId, name);

        this.finalName = name;
    }

    @Override
    public Text getName() {
        return this.finalName;
    }
}
