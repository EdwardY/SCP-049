import java.awt.Toolkit;

/**
 * [Soldier.java]
 * A soldier object that fights for the MTF player.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */
public class Soldier extends Cadet implements Troop{
    /**The attack range of this unit. */
    static final int RANGE = 20;
    /**The amount of damage the soldier will do to its target. */
    private int attackDamage;


    /**
     * Constructor to create a new soldier.
     * @param maxHealth The soldier's maximum health.
     * @param x The soldier's horizontal location.
     * @param y The soldier's vertical position.
     * @param priority The priority of the soldier over all other humans in the game.
     */
    public Soldier(int maxHealth, int x, int y, int attackDamage){
        super(maxHealth, x, y, Toolkit.getDefaultToolkit().getImage("./assets/soldier.png"),  1);
        this.attackDamage = attackDamage;
    }//end of constructor

    /**
     * Another constructor to create a new soldier when health is specified.
     * @param age The soldier's age.
     * @param health The soldier's health.
     * @param maxHealth The soldier's maximum health.
     * @param x The soldier's horizontal location.
     * @param y The soldier's vertical position.
     * @param priority The priority of the soldier over all other humans in the game.
     */
    public Soldier(int age, int health, int maxHealth, int x, int y, int attackDamage){
        super(age, health, maxHealth, x, y, Toolkit.getDefaultToolkit().getImage("./assets/soldier.png"),  1);
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
