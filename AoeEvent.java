import java.awt.Rectangle;

/**
 * [AoeEvent.java]
 * Class for {@code Events} which have a certain area of effect
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class AoeEvent extends Event{
    /** The {@code Rectangle} defining the boundaries of the {@code AoeEvent} */
    private Rectangle aoe;

    /**
     * Constructor for the {@code AoeEvent} class
     * @param timeLeft time left for the {@code AoeEvent}
     * @param effectAmount the amount of effect the {@code AoeEvent} has
     * @param aoe the area the {@code AoeEvent} can affect
     */
    public AoeEvent(int timeLeft, int effectAmount, Rectangle aoe){
        super(timeLeft, effectAmount);
        this.aoe = aoe;
    }

    /**
     * Gets the area of effect of the {@code AoeEvent}
     * @return aoe, the {@code AoeEvent's} area of effect
     */
    public Rectangle getAoe(){
        return this.aoe;
    }
}
