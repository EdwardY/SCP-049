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
     * @param effectAmount how much effect the {@code Riot} has, based on citizen count in the area
     * @param level the level of the {@code Riot}
     */
    public Riot(int effectAmount, int level){
        super(5, effectAmount, level);
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
