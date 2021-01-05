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
     * @param level the level of the {@code Stonks}
     */
    public Stonks(int level){
        super(level*2, level*3, level);
    }

    /**
     * Increases the money earned for the turn in the game by a factor of effectAmount
     * @param game the {@code Game} being affected
     */
    @Override
    public void affect(Game game){
        double percent = (this.getEffectAmount()/100);
        game.changeMoney((int)(game.getMoneyPerTurn()*percent));
    }
}
