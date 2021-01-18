//graphics imports
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * [DuberDrawableButton.java]
 * A slightly less painful button than JButtons
 * @author Vivian Dai
 * @version 1.0 on January 16, 2021
 */
class DuberDrawableButton extends DuberButton{
    /** The thing to draw since it'll have to be drawn on a graphics surface */
    private Drawable sprite;

    /**
     * Constructor for the {@code DuberDrawableButton}
     * @param sprite the drawable thing to draw
     * @param boundaries the boundaries of the button
     */
    DuberDrawableButton(Drawable sprite, Rectangle boundaries){
        super(boundaries);
        this.sprite = sprite;
    }

    /**
     * Draws the {@code DuberDrawableButton} but only if the button is active 
     * @param g the {@code Graphics} to draw on
     */
    public void draw(Graphics g){
        if(active){
            sprite.draw(g);
        }
    }
}
