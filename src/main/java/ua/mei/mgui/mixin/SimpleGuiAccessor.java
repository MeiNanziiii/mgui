package ua.mei.mgui.mixin;

import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SimpleGui.class)
public interface SimpleGuiAccessor {
    @Accessor
    Text getTitle();

    @Accessor
    void setTitle(Text title);
}
