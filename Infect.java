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
     * @param x the middle x position of where the {@code Infect} will affect
     * @param y the middle y position of where the {@code Infect} will affect
     */
    public Infect(int level, int x, int y){
        super(1, -1, level, new Rectangle(x - level, y - level, level*2, level*2));
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
     * Turns {@code Humans} within the area of effect into {@code SCP0492s}
     * @param game the {@code Game} to affect
     */
    @Override
    public void affect(Game game){
        ArrayList<Human> humans = game.getHumans();
        for(int i = 0;i < humans.size();i++){
            Human human = humans.get(i);
            if(this.getAoe().contains(human.getX(), human.getY())){
                game.convert(human, "SCP0492", 100, 10, 0, 0.0, 0.0, 0);//temp values added
                // Parameters for convert: NPC npc, String type, int health, int maxHealth, int attackDamage, int priority, double successRate, double sus, int healingAmount
            }
        }
    }
}
