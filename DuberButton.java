import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * [DuberButton.java]
 * A slightly less painful button than JButtons
 * @author Vivian Dai
 * @version 1.0 on January 16, 2021
 */
class DuberButton {
    /** The thing to draw since it'll have to be drawn on a graphics surface */
    private Drawable sprite;
    /** boundaries of the {@code DuberButton} */
    private Rectangle boundaries;

    /**
     * Constructor for the {@code DuberButton}
     * @param sprite the drawable thing to draw
     * @param boundaries the boundaries of the button
     */
    DuberButton(Drawable sprite, Rectangle boundaries){
        this.sprite = sprite;
        this.boundaries = boundaries;
    }

    /**
     * Draws the {@code DuberButton}
     * @param g the {@code Graphics} to draw on
     */
    public void draw(Graphics g){
        sprite.draw(g);
    }

    /**
     * Checks if a certain x, y value is within the boundaries
     * @param x the x coordinates to check
     * @param y the y coordinates to check
     * @return true if the x and y are within the boundaries, false otherwise
     */
    public boolean inBounds(int x, int y){
        return boundaries.contains(x, y);
    }
}
