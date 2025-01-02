package ua.mei.mgui.api.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import eu.pb4.sgui.virtual.SguiScreenHandlerFactory;
import eu.pb4.sgui.virtual.inventory.VirtualScreenHandler;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import ua.mei.mgui.impl.MGuiDrawContext;

import java.util.OptionalInt;

public abstract class TexturedGui extends SimpleGui {
    public @Nullable Text customTitle;
    private @Nullable Text mguiTitle;

    public TexturedGui(ScreenHandlerType<GenericContainerScreenHandler> type, ServerPlayerEntity player, boolean manipulatePlayerSlots) {
        super(type, player, manipulatePlayerSlots);
    }

    public abstract void draw(MGuiDrawContext context);

    @Override
    public void setTitle(Text title) {
        this.mguiTitle = title;

        if (this.isOpen()) {
            this.player.networkHandler.sendPacket(new OpenScreenS2CPacket(this.syncId, this.type, title) {
                @Override
                public Text getName() {
                    return title;
                }
            });
            this.screenHandler.syncState();
        }
    }

    @Override
    public void onTick() {
        MGuiDrawContext context = new MGuiDrawContext();

        draw(context);

        Text title = context.render(this);

        if (this.isOpen() && !title.equals(this.mguiTitle)) {
            setTitle(title);
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
                MGuiDrawContext context = new MGuiDrawContext();

                draw(context);

                Text title = context.render(this);
                this.mguiTitle = title;

                this.player.networkHandler.sendPacket(new OpenScreenS2CPacket(screenHandler.syncId, screenHandler.getType(), title) {
                    @Override
                    public Text getName() {
                        return title;
                    }
                });
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
