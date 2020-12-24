import java.awt.Graphics;

/**
 * [Drawable.java]
 * Interface that defines methods for classes which have stuff that need to be drawn
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

interface Drawable{
    /**
     * Draws the things that need to be drawn on the {@code Graphics} g
     * @param g the {@code Graphics} surface to be drawing on
     */
    public void draw(Graphics g);
}