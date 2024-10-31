package ua.mei.mgui.api.font;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import ua.mei.pfu.PolymerFontUtils;
import ua.mei.pfu.font.FontResourceManager;

public class GUITextureManager {
    public static MutableText requestGui(String path, FontResourceManager manager) {
        return Text.empty().append(manager.requestSpace(-8)).append(manager.requestBitmap(path, 256, 13).getText()).append(manager.requestSpace(-169));
    }
}
