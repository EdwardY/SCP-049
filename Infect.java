import java.awt.Rectangle;

/**
 * [Infect.java]
 * Class for {@code Infect} {@code Events}
 * @author Vivian Dai
 * @version 1.0
 */

 class Infect extends AoeEvent{
    /**
     * Passes values into the superclass's constructor, it only takes one turn to infect citizens and effectAmount is not relevant
     * @param aoe the area which can be infected
     */
    public Infect(Rectangle aoe){
        super(1, 1, -1, aoe);
    }

    /**
     * Turns {@code Humans} within the area of effect into {@code SCP0492s}
     * @param game the {@code Game} to affect
     */
    @Override
    public void affect(Game game){
        //TODO: write the infect's affect after game class is made
    }
}
