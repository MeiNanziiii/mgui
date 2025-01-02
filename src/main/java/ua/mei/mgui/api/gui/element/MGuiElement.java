package ua.mei.mgui.api.gui.element;

import net.minecraft.text.Text;

public abstract class MGuiElement {
    public abstract Text render();

    public abstract int getWidth();
}
