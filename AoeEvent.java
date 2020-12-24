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
     * @param duration the total duration of the {@code AoeEvent}
     * @param timeLeft time left for the {@code AoeEvent}
     * @param effectAmount the amount of effect the {@code AoeEvent} has
     * @param aoe the area the {@code AoeEvent} can affect
     */
    public AoeEvent(int duration, int timeLeft, int effectAmount, Rectangle aoe){
        super(duration, timeLeft, effectAmount);
        this.aoe = aoe;
    }

    /**
     * <p>
     * Increases the size of the {@code AoeEvent} by xIncrease units horizontally and yIncrease units vertically. The 
     * {@code Rectangle} class also handles centering 
     * </p>
     * @param xIncrease the amount to 
     * @param yIncrease
     */
    public void upgradeSize(int xIncrease, int yIncrease){
        this.aoe.grow(xIncrease, yIncrease);
    }

}
