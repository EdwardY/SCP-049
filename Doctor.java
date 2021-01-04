import java.awt.Image;


/**
 * [Doctor.java]
 * A doctor object that can heal other NPC's
 * @author Damon Ma
 * @version 1.0 written on December 24, 2020
 */


public class Doctor extends Human{
    /**The amount of health a doctor can replenish to the tartget per turn. */
    private int healingAmount;

      /**
     * Constructor to make a new doctor object. 
     * @param x The horizontal position of the Doctor.
     * @param y The vertical position of the Doctor.
     * @param sprite The sprite of the Doctor.
     * @param priority The priority of the doctor over all other humans.
     */
    public Doctor(int x, int y, Image sprite, int priority, int healingAmount){
        super(100, x, y, sprite, priority);
        this.healingAmount = healingAmount;
    }//end of constructor

    //start of getters
    /**
     * returns the amount a doctor can heal.
     * @return the Healing amount.
     */
    public int getHealingAmount(){
        return this.healingAmount;
    }//end of method

    //end of getters


    //start of setters

    /**
     * Set the new healing amount of the doctor.
     * @param newHealingAmount The new amount of healing a doctor can perform.
     */
    public void setHealingAmount(int newHealingAmount){
        this.healingAmount = newHealingAmount;
    }//end of method

    //end of setters

}//end of class
