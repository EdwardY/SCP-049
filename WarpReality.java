/**
 * [WarpReality.java]
 * The {@code WarpReality} {@code Event} generates hume points for the SCP side
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
class WarpReality extends WholeGameEvent{
    /**
     * Passes values into the superclass's constructor
     * @param humePointsPerTurn the number of hume points the SCPs will get per turn
     * @param level the level of the {@code WarpReality} {@code Event}
     */
    WarpReality(int humePointsPerTurn, int level){
        super(5, humePointsPerTurn, level);
    }
    
    /**
     * Calculates the cost based on the level
     * @param level the intended level
     * @return the final cost
     */
    public static int getCostByLevel(int level){
        return level*5;
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
