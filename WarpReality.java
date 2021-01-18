/**
 * [WarpReality.java]
 * The {@code WarpReality} {@code Event} generates hume points for the SCP side
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
class WarpReality extends WholeGameEvent{
    /**
     * Passes values into the superclass's constructor
     * @param level the level of the {@code WarpReality} {@code Event}
     */
    WarpReality(int level){
        super(level*2, level*2, level);
    }

    /**
     * Constructor for the {@code WarpReality} when the time left is known
     * @param level the level of the {@code WarpReality} {@code Event}
     * @param timeLeft the time left for the {@code WarpReality}
     */
    WarpReality(int level, int timeLeft){
        super(timeLeft, level*2, level);
    }
    
    /**
     * Calculates the cost based on the level
     * @param level the intended level
     * @return the final cost
     */
    public static int getCostByLevel(int level){
        return level*3;
    }
    
    /**
     * Increases hume points in the {@code Game} by its effectAmount for the duration of the Effect
     * @param game the {@code Game} that gets affected
     */
    @Override
    public void affect(Game game){
        game.changeHume(this.getEffectAmount());
    }
}
