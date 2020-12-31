import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * [Infect.java]
 * Class for {@code Infect} {@code Events}
 * @author Vivian Dai
 * @version 1.0
 */

 class Infect extends AoeEvent{
    /**
     * Passes values into the superclass's constructor, it only takes one turn to infect citizens and effectAmount is not relevant
     * @param level the level of the {@code Infect}
     * @param aoe the area which can be infected
     */
    public Infect(int level, Rectangle aoe){
        super(1, -1, level, aoe);
    }

    /**
     * Turns {@code Humans} within the area of effect into {@code SCP0492s}
     * @param game the {@code Game} to affect
     */
    @Override
    public void affect(Game game){
        ArrayList<Human> humans = game.getHumans();
        for(int i = 0;i < humans.size();i++){
            Human human = humans.get(i);
            if(this.getAoe().contains(human.getX(), human.getY())){
                //TODO: convert the human from a human to a scp-049-2 after convert method is written
            }
        }
    }
}
