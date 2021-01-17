import java.awt.Rectangle;

/**
 * [DuberButton.java]
 * A slightly less painful button than JButtons
 * @author Vivian Dai
 * @version 1.0 on January 16, 2021
 */
abstract class DuberButton implements Drawable{
    /** boundaries of the {@code DuberButton} */
    protected Rectangle boundaries;
    /** Boolean for whether or not the {@code DuberButton} is currently active */
    protected boolean active;

    /**
     * Constructor for the {@code DuberButton}
     * @param boundaries the boundaries of the button
     */
    DuberButton(Rectangle boundaries){
        this.boundaries = boundaries;
        this.active = false;
    }

    /**
     * Checks if a certain x, y value is within the boundaries
     * @param x the x coordinates to check
     * @param y the y coordinates to check
     * @return true if the x and y are within the boundaries and the button is active, false otherwise
     */
    public boolean inBounds(int x, int y){
        return ((active) && boundaries.contains(x, y));
        
    }

    /**
     * Toggles active to the opposite of what it was
     */
    public void toggleActive(){
        this.active = !active;
    }

    /** 
     * Turns the button on
     */
    public void activate(){
        this.active = true;
    }

    /**
     * Turns the button off
     */
    public void deactivate(){
        this.active = false;
    }
}
