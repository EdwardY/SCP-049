import java.awt.Image;


/**
 * [Spy.java]
 * A special type of soldier that can acquire enemy information.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */



public class Spy extends Cadet{
    /**The chance of the spy gaining enemy intel */
    private double successRate;
    /**The chance of the spy being caught by the enemy */
    private double sus;

    /**
     * Constructor to create a new spy.
     * @param health The spy's health.
     * @param maxHealth The spy's maximum health.
     * @param x The spy's horizontal location.
     * @param y The spy's vertical position.
     * @param sprite The image of the spy that will appear to the players.
     * @param age The age of the spy.
     * @param priority The priority of the spy over all other humans in the game.
     * @param hunger The hunger level of the spy.
     * @param maxHunger The maximum hunger level of the spy.
     * @param successRate The chance of the spy gaining enemy intel.
     * @param sus The chance that the spy will be caught by the enemy.
     */
    public Spy(int health, int maxHealth, int x, int y, Image sprite, int age, int priority, int hunger, int maxHunger, double successRate, double sus){
        super(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger);
        this.successRate = successRate;
        this.sus = sus;
    }//end of constructor

    //TODO: Empty method for getINtel, mechanics not thoroughly discussed yet.


    /**
     * Gains intel from the enemy side.
     * @return The intel that was gathered.
     */
    public String getIntel(){
        return("sample intel, figure out this method later.");
    }

    //start of getters

    /**
     * Returns the success rate of getting enemy intel.
     * @return The success rate of getting enemy intel.
     */
    public double getSuccessRate(){
        return this.successRate;
    }

    /**
     * Returns the chance of the spy getting caught by the enemy.
     * @return The chance of the spy getting caught by the enemy.
     */
    public double getSus(){
        return this.sus;
    }


    //end of getters



    //start of setters

    /**
     * Sets the success rate of getting enemy intel.
     * @param  newSuccessRate The newsuccess rate of getting enemy intel.
     */
    public void setSuccessRate(double newSuccessRate){
        this.successRate = newSuccessRate;
    }

    /**
     * Sets the chance of the spy getting caught by the enemy.
     * @param newSus The new chance of the spy getting caught by the enemy.
     */
    public void setSus(double newSus){
        this.sus = newSus;
    }


    //end of setters


}
