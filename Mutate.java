/**
 * [Mutate.java]
 * The {@code Mutate} {@code Event} upgrades the {@code SCP0492} level
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */
class Mutate extends WholeGameEvent{
    /**
     * <p>
     * Constructor for the {@code Mutate} {@code Event}. Passes in ones for all parameters since it only takes one turn to accomplish 
     * and it increases the level of {@code SCP0492s} by 1.
     * </p>
     */
    public Mutate(){
        super(1, 1, 1);
    }

    /**
     * The level of {@code SCP0492s} will increase by the affectAmount which is 1
     * @param game the {@code Game} to affect
     */
    @Override
    public void affect(Game game){
        //TODO: implement mutate after game class is created
    }
}
