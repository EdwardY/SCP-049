import java.awt.Image;
import java.awt.Rectangle;

/**
 * [PhysicalEvent.java]
 * A class for all {@code Events} which are physical and need to be drawn
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class PhysicalEvent extends AoeEvent implements Drawable{
    /** The {@code Image} that will be displayed when drawing the {@code PhysicalEvent} */
    private Image sprite;

    /**
     * Constructor for the {@code PhysicalEvent} class, passes all paramters into the superclass's constructor
     * @param timeLeft how much time is left for the {@code PhysicalEvent}
     * @param effectAmount the amount the {@code PhysicalEvent} can affect
     * @param level the level of the {@code PhysicalEvent}
     * @param aoe the area that the {@code PhysicalEvent} can affect
     * @param sprite the {@code Image} to display when drawing {@code PhysicalEvents}
     */
    public PhysicalEvent(int timeLeft, int effectAmount, int level, Rectangle aoe, Image sprite){
        super(timeLeft, effectAmount, level, aoe);
        this.sprite = sprite;
    }

    /**
     * Gets the sprite 
     * @return sprite, the {@code Image} that's supposed to be drawn for the {@code PhysicalEvent}
     */
    public Image getSprite(){
        return this.sprite;
    }
    
}
