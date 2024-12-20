package ua.mei.mgui.api.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import eu.pb4.sgui.virtual.SguiScreenHandlerFactory;
import eu.pb4.sgui.virtual.inventory.VirtualScreenHandler;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import ua.mei.mgui.impl.packet.MGuiOpenScreenPacket;
import ua.mei.mgui.mixin.SimpleGuiAccessor;

import java.util.OptionalInt;

public abstract class TexturedGui extends SimpleGui {
    public Text customText = Text.empty();

    public TexturedGui(ScreenHandlerType<GenericContainerScreenHandler> type, ServerPlayerEntity player, boolean manipulatePlayerSlots) {
        super(type, player, manipulatePlayerSlots);
    }

    public abstract void drawGui(GuiDrawContext context);

    @Override
    public void setTitle(Text title) {
        ((SimpleGuiAccessor) this).titleSet(title);

        if (this.isOpen()) {
            this.player.networkHandler.connection.send(new MGuiOpenScreenPacket(this.syncId, this.type, title));
            this.screenHandler.syncState();
        }
    }

    @Override
    public void onTick() {
        GuiDrawContext context = new GuiDrawContext();

        drawGui(context);

        Text text = context.render(this);

        if (this.isOpen() && !text.equals(this.getTitle())) {
            setTitle(text);
        }
    }

    @Override
    protected boolean sendGui() {
        this.reOpen = true;

        if (this.player.currentScreenHandler != this.player.playerScreenHandler) {
            this.player.closeHandledScreen();
        }
        this.player.incrementScreenHandlerSyncId();

        OptionalInt temp = OptionalInt.empty();

        if (this.player.currentScreenHandler == this.player.playerScreenHandler) {
            NamedScreenHandlerFactory factory = SguiScreenHandlerFactory.ofDefault(this);
            ScreenHandler screenHandler = factory.createMenu(this.player.screenHandlerSyncId, this.player.getInventory(), this.player);

            if (screenHandler == null) {
                if (this.player.isSpectator()) {
                    this.player.sendMessage(Text.translatable("container.spectatorCantOpen").formatted(Formatting.RED), true);
                }
            } else {
                GuiDrawContext context = new GuiDrawContext();

                drawGui(context);

                this.player.networkHandler.connection.send(new MGuiOpenScreenPacket(screenHandler.syncId, screenHandler.getType(), context.render(this)));
                this.player.onScreenHandlerOpened(screenHandler);
                this.player.currentScreenHandler = screenHandler;

                temp = OptionalInt.of(this.player.screenHandlerSyncId);
            }
        }
        this.reOpen = false;

        if (temp.isPresent()) {
            this.syncId = temp.getAsInt();

            if (this.player.currentScreenHandler instanceof VirtualScreenHandler) {
                this.screenHandler = (VirtualScreenHandler) this.player.currentScreenHandler;
                return true;
            }
        }

        return false;
    }
}
