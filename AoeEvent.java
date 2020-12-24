import java.awt.Rectangle;

/**
 * [AoeEvent.java]
 * Class for {@code Events} which have a certain area of effect
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class AoeEvent extends Event{
    /** The {@code Rectangle} defining the boundaries of the {@code AoeEvent} */
    private Rectangle currentSize;

    /**
     * Constructor for the {@code AoeEvent} class
     * @param duration the total duration of the {@code AoeEvent}
     * @param timeLeft time left for the {@code AoeEvent}
     * @param effectAmount the amount of effect the {@code AoeEvent} has
     * @param currentSize the area the {@code AoeEvent} can affect
     */
    public AoeEvent(int duration, int timeLeft, int effectAmount, Rectangle currentSize){
        super(duration, timeLeft, effectAmount);
        this.currentSize = currentSize;
    }

    /**
     * Increases the size of the {@code AoeEvent} by 1 unit both horizontally and vertically
     */
    public void upgradeSize(){
        this.currentSize.grow(1, 1);
    }

}
