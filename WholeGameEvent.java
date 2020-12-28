/**
 * [WholeGameEvent.java]
 * The class for {@code Events} that affect the entire game
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

abstract class WholeGameEvent extends Event{
    /**
     * Passes all parameters into the superclass to be constructed
     * @param timeLeft the time left in the {@code WholeGameEvent}
     * @param effectAmount the amount of effect the {@code WholeGameEvent} has
     * @param level the level of the {@code WholeGameEvent}
     */
    public WholeGameEvent(int timeLeft, int effectAmount, int level){
        super(timeLeft, effectAmount, level);
    }

}
