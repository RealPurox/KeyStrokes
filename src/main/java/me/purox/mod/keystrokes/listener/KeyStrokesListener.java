package me.purox.mod.keystrokes.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import me.purox.mod.keystrokes.KeyStrokesMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Arrays;

public class KeyStrokesListener {

    private KeyStrokesMod keyStrokesMod;

    public KeyStrokesListener(KeyStrokesMod keyStrokesMod) {
        this.keyStrokesMod = keyStrokesMod;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
        // Don't render KeyStrokes if the user is showing debug info ("F3")
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return;
        // Render the KeyStrokes
        this.keyStrokesMod.getRenderer().renderKeyStrokes();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // Remove all timestamps from our cps lists that happened over a second ago
        Arrays.asList(this.keyStrokesMod.getAttackTimes(), this.keyStrokesMod.getUseItemTimes()).forEach(longs -> longs.removeIf(time -> time + 1000 < System.currentTimeMillis()));
    }

    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        // Adds the current time to the attackTimes list if the user used the left mouse button and it wasn't pressed before (releasing the button)
        if (event.button == 0 && event.buttonstate) {
            this.keyStrokesMod.getAttackTimes().add(System.currentTimeMillis());
        }
        // Adds the current time to the useItemTimes list if the user used the right mouse button and it wasn't pressed before
        else if (event.button == 1 && event.buttonstate) {
            this.keyStrokesMod.getUseItemTimes().add(System.currentTimeMillis());
        }
    }

}
