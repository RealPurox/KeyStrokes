package me.purox.mod.keystrokes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import me.purox.mod.keystrokes.listener.KeyStrokesListener;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = "KeyStrokesMod", version = "1.0")
public class KeyStrokesMod {

    /*
        Colors are represented as hexadecimals:
            - the first 2 digits refer to alpha (transparency) (0-255 or 00-FF)
            - the 3rd and 4th digit refer to red (0-255 or 00-FF)
            - the 5th and 6th digit refer to green (0-255 or 00-FF)
            - the 7th and 8th digit refer to blue (0-255 or 00-FF)
     */

    private int backgroundColor = 0x99000000; // Color of the key when inactive
    private int backgroundColorActive = 0x99FFFFFF; // Color of the key when active

    private int textColor = 0xFFFFFFFF; // Color of the text on the key when inactive
    private int textColorActive = 0xFF000000; // Color of the text on the key when active

    private List<Long> attackTimes = new ArrayList<>(); // Timestamps where the player pressed the left mouse button - used to determine CPS on LMB
    private List<Long> useItemTimes = new ArrayList<>(); // " - used to determine CPS on RMB

    private KeyStrokesRenderer renderer;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        this.renderer = new KeyStrokesRenderer(this);

        MinecraftForge.EVENT_BUS.register(new KeyStrokesListener(this));
        FMLCommonHandler.instance().bus().register(new KeyStrokesListener(this));
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundColorActive() {
        return backgroundColorActive;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextColorActive() {
        return textColorActive;
    }

    public List<Long> getAttackTimes() {
        return attackTimes;
    }

    public List<Long> getUseItemTimes() {
        return useItemTimes;
    }

    public KeyStrokesRenderer getRenderer() {
        return renderer;
    }
}