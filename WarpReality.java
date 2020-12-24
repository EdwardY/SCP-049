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
     */
    WarpReality(int humePointsPerTurn){
        super(5, 5, humePointsPerTurn);
    }
    
    /**
     * Increases hume points in the {@code Game} by its effectAmount for the duration of the Effect
     * @param game the {@code Game} that gets affected
     */
    @Override
    public void affect(Game game){
        //TODO: increase hume points in the game
    }
}
