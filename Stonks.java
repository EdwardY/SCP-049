/**
 * [Stonks.java]
 * An {@code Event} which boosts the economy of the town
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
class Stonks extends WholeGameEvent{

    /**
     * Constructor for the {@code Stonks} class<br>
     * Passes in default values into the superclass's constructor
     * @param effectAmount the percentage to increase town economic production by 
     * @param level the level of the {@code Stonks}
     */
    public Stonks(int effectAmount, int level){
        super(5, effectAmount, level);
    }

    /**
     * Increases the money earned for the turn in the game by a factor of effectAmount
     * @param game the {@code Game} being affected
     */
    @Override
    public void affect(Game game){
        //TODO: implement stonks effect after the game class is defined
    }
}
