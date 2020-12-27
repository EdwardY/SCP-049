/**
 * [Riot.java]
 * The class for {@code Riot} {@code Events}
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Riot extends WholeGameEvent{
    /**
     * Constructor for the {@code Riot} class, passes values into the superclass
     * @param effectAmount how much effect the {@code Riot} has, based on citizen count in the area
     */
    public Riot(int effectAmount){
        super(5, effectAmount);
    }

    /**
     * {@code Citizens} will become agitated and do damage to nearby {@code Buildings}
     * @param game the {@code Game class to pass in}
     */
    @Override
    public void affect(Game game){
        //TODO: make riot after game class is created
    }
}
