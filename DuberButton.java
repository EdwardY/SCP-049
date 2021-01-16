import java.awt.Rectangle;

/**
 * [DuberButton.java]
 * A slightly less painful button than JButtons
 * @author Vivian Dai
 * @version 1.0 on January 16, 2021
 */
abstract class DuberButton implements Drawable{
    /** boundaries of the {@code DuberButton} */
    private Rectangle boundaries;

    /**
     * Constructor for the {@code DuberButton}
     * @param boundaries the boundaries of the button
     */
    DuberButton(Rectangle boundaries){
        this.boundaries = boundaries;
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
