//data structure
import java.util.ArrayList;

/**
 * [Riot.java]
 * The class for {@code Riot} {@code Events}
 * @author Vivian Dai
 * @version 1.0 on December 24, 2020
 */

class Riot extends WholeGameEvent{
    /**
     * Constructor for the {@code Riot} class, passes values into the superclass
     * @param level the level of the {@code Riot}
     */
    public Riot(int level){
        super(level*2, level*3, level);
    }

    /**
     * Constructor for the {@code Riot} class with a specified time left, passes values into the superclass
     * @param level the level of the {@code Riot}
     * @param timeLeft the time left for thhe {@code Riot}
     */
    public Riot(int level, int timeLeft){
        super(timeLeft, level*3, level);
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
     * {@code Citizens} will become agitated and do damage to nearby {@code Buildings}
     * @param game the {@code Game} class to pass in 
     */
    @Override
    public void affect(Game game){
        ArrayList<Building> buildings = game.getBuildings();
        for(int i = 0;i < buildings.size();i++){
            buildings.get(i).takeDamage(this.getEffectAmount());
        }
    }
}
