import java.awt.Image;

/**
 * [SCP0492.java]
 * A soldier-type NPC that the SCP player uses.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */



public class SCP0492 extends NPC implements Troop{
    /**The level of this unit. */
    static int level;
    /**The damage dealt when this unit attacks.*/
    private int attackDamage;


    /**
     * A constructor for the SPC0492 class.
     * @param health The health of the unit.
     * @param maxHealth The maximum health of the unit.
     * @param x The x-value of the unit's location on the grid.
     * @param y The y-value of the unit's location on the grid.
     * @param sprite The image that the unit will have.
     * @param attackDamage The amount of damage that this unit will deal when attacking.
     */
    public SCP0492 (int health, int maxHealth, int x, int y, Image sprite, int attackDamage){
        super(health, maxHealth, x, y, sprite);
        this.attackDamage = attackDamage;
    }//end of constructor

     /**
     * Attacks a targeted NPC in the game
     * @param npc The target NPC that the unit is attacking.
     */
    public void attack(NPC npc){
        npc.takeDamage(this.attackDamage);     
    }//end of method

    //start of getters


    /**
     * Returns the amount of damage that the unit currently deals.
     * @return The damage that the unit currently deals.
     */
    public int getAttackDamage(){
        return this.attackDamage;        
    }//end of method

    //end of getters


    //start of setters


    /**
     * Set the amount of damage that the unit will deal.
     * @param newDamage The new amount of damage that the unit will deal.
     */
    public void setAttackDamage(int newDamage){
        this.attackDamage = newDamage;        
    }//end of method

    //end of setters





}//end of class
