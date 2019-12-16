package me.purox.mod.keystrokes.util;

import net.minecraft.client.gui.Gui;

public class GraphicUtil {

    /**
     * Draws a rectangle onto the screen using two points
     *
     * @param point1x X-Coordinate of the first point
     * @param point1y Y-Coordinate of the first point
     * @param point2x X-Coordinate of the second point
     * @param point2y Y-Coordinate of the second point
     * @param color 8 digit hexadecimal number representing a color to draw the rectangle in
     */
    public static void drawRectangle(int point1x, int point1y, int point2x, int point2y, int color) {
        Gui.drawRect(point1x, point1y, point2x, point2y, color);
    }

}
