//utility imports
import java.util.ArrayList;

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
     * @param level the level of the {@code Mutate}
     */
    public Mutate(int level){
        super(1, 50, level);
    }

    /**
     * Constructor for {@code Mutate} when there's a specified time left
     * @param level the level of the {@code Mutate}
     * @param timeLeft the time left for the {@code Mutate}
     */
    public Mutate(int level, int timeLeft){
        super(timeLeft, 50, level);
    }

    /**
     * Calculates the cost based on the level
     * @param level the intended level
     * @return the final cost
     */
    public static int getCostByLevel(int level){
        return 30;
    }

    /**
     * The level of {@code SCP0492s} will increase by 1, the attack damage and max health will increase by effectAmount
     * @param game the {@code Game} to affect
     */
    @Override
    public void affect(Game game){
        SCP0492.level++;
        ArrayList<SCP0492> scps = game.getScps();
        for(int i = 0;i < scps.size();i++){
            SCP0492 scp = scps.get(i);
            scp.setAttackDamage(scp.getAttackDamage() + this.getEffectAmount());
            scp.setMaxHealth(scp.getMaxHealth() + this.getEffectAmount());
            
        }
    }
}
