import java.awt.Image;

/**
 * [Soldier.java]
 * A soldier object that fights for the MTF player.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */
public class Soldier extends Cadet implements Troop{
    /**The amount of damage the soldier will do to its target. */
    private int attackDamage;


    /**
     * Constructor to create a new soldier.
     * @param health The soldier's health.
     * @param maxHealth The soldier's maximum health.
     * @param x The soldier's horizontal location.
     * @param y The soldier's vertical position.
     * @param sprite The image of the soldier that will appear to the players.
     * @param priority The priority of the soldier over all other humans in the game.
     * @param hunger The hunger level of the soldier.
     * @param maxHunger The maximum hunger level of the soldier.
     */
    public Soldier(int health, int maxHealth, int x, int y, Image sprite, int priority, int attackDamage){
        super(health, maxHealth, x, y, sprite,  priority);
        this.attackDamage = attackDamage;
    }//end of constructor

    /**
     * Attacks a targeted NPC in the game
     * @param npc The target NPC that the soldier is attacking.
     */
    public void attack(NPC npc){
        npc.takeDamage(this.attackDamage);     
    }//end of method

    //start of getters


    /**
     * Returns the amount of damage that the soldier currently deals.
     * @return The damage that the soldier currently deals.
     */
    public int getAttackDamage(){
        return this.attackDamage;        
    }//end of method

    //end of getters


    //start of setters


    /**
     * Set the amount of damage that the soldier will deal.
     * @param newDamage The new amount of damage that the soldier will deal.
     */
    public void setAttackDamage(int newDamage){
        this.attackDamage = newDamage;        
    }//end of method

    //end of setters



}//end of class
