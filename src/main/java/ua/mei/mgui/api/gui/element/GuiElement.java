package ua.mei.mgui.api.gui.element;

import net.minecraft.text.Text;

public abstract class GuiElement {
    public int x = 0;

    public abstract Text render();
}
