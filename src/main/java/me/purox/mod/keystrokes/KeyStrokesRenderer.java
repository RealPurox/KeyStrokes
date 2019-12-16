package me.purox.mod.keystrokes;

import me.purox.mod.keystrokes.util.GraphicUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class KeyStrokesRenderer {

    private KeyStrokesMod keyStrokesMod;

    public KeyStrokesRenderer(KeyStrokesMod keyStrokesMod) {
        this.keyStrokesMod = keyStrokesMod;
    }

    /**
     * Renders the KeyStrokes.
     */
    public void renderKeyStrokes() {
        Minecraft minecraft = Minecraft.getMinecraft(); // Minecraft instance
        FontRenderer fontRenderer = minecraft.fontRenderer; // Default FontRenderer instance

        int gap = 1; // Gap between each key
        int buttonSize = 17; // Size of the rectangular button backgrounds

        int spaceBarVertical = (int) (buttonSize * 0.5); // Space bar height
        int spaceBarHorizontal = gap * 2 + buttonSize * 3; // Space bar width

        int mouseButtonVertical = (int) (buttonSize * 0.95); // Mouse button height
        int mouseButtonHorizontal = buttonSize + (buttonSize / 2) + gap; // Mouse button width

        GL11.glPushMatrix(); // Create 1 matrix on the stack so we don't transform other matrices
        GL11.glScaled(0.75, 0.75, 0.75); // Scale everything down by 25% so the render is not too big

        // Forward key
        KeyBinding key = minecraft.gameSettings.keyBindForward; // Get the correct KeyBind
        String keyName = getKeyBindingName(key); // Get a 1-char-name for the KeyBind

        // Draw the rectangle for the Forward Key
        GraphicUtil.drawRectangle(gap * 2 + buttonSize, gap, gap * 2 + buttonSize * 2, buttonSize + gap, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());
        // Draw the name of the KeyBind on top of the rectangle we just drew
        fontRenderer.drawString(keyName, gap * 3 + buttonSize +  buttonSize / 2 - fontRenderer.getStringWidth(keyName) / 2, gap * 2 + buttonSize / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        // Backward key
        key = minecraft.gameSettings.keyBindBack;
        keyName = getKeyBindingName(key);

        GraphicUtil.drawRectangle(gap * 2 + buttonSize, gap * 2 + buttonSize, gap * 2 + buttonSize * 2, gap * 2 + buttonSize * 2, minecraft.gameSettings.keyBindBack.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());
        fontRenderer.drawString(keyName, gap * 3 + buttonSize +  buttonSize / 2 - fontRenderer.getStringWidth(keyName) / 2, gap * 3 + buttonSize + buttonSize / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        // Left key
        key = minecraft.gameSettings.keyBindLeft;
        keyName = getKeyBindingName(key);

        GraphicUtil.drawRectangle(gap, gap * 2 + buttonSize, gap + buttonSize, gap * 2 + buttonSize * 2, minecraft.gameSettings.keyBindLeft.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());
        fontRenderer.drawString(keyName, gap * 2 + buttonSize / 2 - fontRenderer.getStringWidth(keyName) / 2, gap * 3 + buttonSize + buttonSize / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        // Right key
        key = minecraft.gameSettings.keyBindRight;
        keyName = getKeyBindingName(key);

        GraphicUtil.drawRectangle(gap * 3 + buttonSize * 2, gap * 2 + buttonSize, gap * 3 + buttonSize * 3, gap * 2 + buttonSize * 2, minecraft.gameSettings.keyBindRight.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());
        fontRenderer.drawString(keyName, gap * 4 + buttonSize * 2 +  buttonSize / 2 - fontRenderer.getStringWidth(keyName) / 2, gap * 3 + buttonSize + buttonSize / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        // Left Mouse Button
        key = minecraft.gameSettings.keyBindAttack;
        String text = this.keyStrokesMod.getAttackTimes().size() + " CPS"; // Text to draw on the button

        GraphicUtil.drawRectangle(gap, gap * 3 + buttonSize * 2, gap + mouseButtonHorizontal, gap * 3 + mouseButtonVertical * 3, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        GL11.glPushMatrix(); // Create a new matrix
        GL11.glScaled(0.755, 0.755, 0.755); // Scale down a little more so the text fits in the box
        fontRenderer.drawString(text, gap * 2 + buttonSize - fontRenderer.getStringWidth(text) / 2, gap * 3 + mouseButtonVertical * 3 + mouseButtonVertical / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());
        GL11.glPopMatrix(); // Delete matrix again

        // Right Mouse Button
        key = minecraft.gameSettings.keyBindUseItem;
        text = this.keyStrokesMod.getUseItemTimes().size() + " CPS";

        GraphicUtil.drawRectangle(gap * 2 + mouseButtonHorizontal, gap * 3 + buttonSize * 2, gap * 2 + mouseButtonHorizontal * 2, gap * 3 + mouseButtonVertical * 3, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        GL11.glPushMatrix();
        GL11.glScaled(0.755, 0.755, 0.755);
        fontRenderer.drawString(text, gap * 3 + mouseButtonHorizontal * 2 - fontRenderer.getStringWidth(text) / 2, gap * 3 + mouseButtonVertical * 3 + mouseButtonVertical / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());
        GL11.glPopMatrix();


        // Jump
        key = minecraft.gameSettings.keyBindJump;
        GraphicUtil.drawRectangle(gap, gap * 4 + mouseButtonVertical * 3, gap + spaceBarHorizontal, gap * 4 + buttonSize * 2 + mouseButtonVertical + spaceBarVertical, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        int insideRectHorizontal = (int) (spaceBarHorizontal * 0.4); // Width of the little rectangle inside the background box

        int insideRectGapHor = (spaceBarHorizontal - insideRectHorizontal) / 2; // Gap to the left & right of the background box
        int insideRectGapVer = (spaceBarVertical - gap) / 2; // Gap to the top of the background box

        GraphicUtil.drawRectangle(gap + insideRectGapHor, gap * 5 + mouseButtonVertical * 3 + insideRectGapVer, gap + spaceBarHorizontal - insideRectGapHor, gap * 5 + mouseButtonVertical * 3 + insideRectGapVer + gap, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        GL11.glPopMatrix(); // Remove the matrix we created
    }

    /**
     *
     * Returns a 1-char-long name for a KeyBinding
     *
     * @param keyBinding the KeyBinding
     * @return name for the KeyBinding
     */
    private String getKeyBindingName(KeyBinding keyBinding) {
        String name = Keyboard.getKeyName(keyBinding.getKeyCode());

        if (name.length() > 1) {
            if (name.startsWith("NUMPAD")) return name.charAt(6) + "";
            return name.charAt(0) + "";
        }

        return name;
    }

}
