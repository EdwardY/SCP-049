import java.awt.Image;
import java.awt.Rectangle;

/**
 * [PhysicalEvent.java]
 * A class for all {@code Events} which are physical and need to be drawn
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class PhysicalEvent extends AoeEvent{
    /** The {@code Image} that will be displayed when drawing the {@code PhysicalEvent} */
    private Image sprite;

    /**
     * 
     * @param duration
     * @param timeLeft
     * @param effectAmount
     * @param aoe
     */
    public PhysicalEvent(int duration, int timeLeft, int effectAmount, Rectangle aoe){
        super(duration, timeLeft, effectAmount, aoe);
    }

    /**
     * Gets the sprite 
     * @return sprite, the {@code Image} that's supposed to be drawn for the {@code PhysicalEvent}
     */
    public Image getSprite(){
        return this.sprite;
    }
    
}
