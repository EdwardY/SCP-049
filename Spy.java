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
    /**The price of creating a spy */
    static final int SPY_PRICE = 50;

    /**
     * Constructor to create a new spy.
     * @param x The spy's horizontal location.
     * @param y The spy's vertical position.
     * @param successRate The chance of the spy gaining enemy intel.
     * @param sus The chance that the spy will be caught by the enemy.
     */
    public Spy(int x, int y, double successRate, double sus){
        super(100, x, y);
        this.successRate = successRate;
        this.sus = sus;
    }//end of constructor


    /**
     * Constructor to create a new spy if health needs to be specified.
     * @param age The spy's age.
     * @param health The spy's health
     * @param x The spy's horizontal location.
     * @param y The spy's vertical position.
     * @param successRate The chance of the spy gaining enemy intel.
     * @param sus The chance that the spy will be caught by the enemy.
     */
    public Spy(int age, int health, int x, int y, double successRate, double sus){
        super(age, health, x, y, 3);
        this.successRate = successRate;
        this.sus = sus;
    }//end of constructor


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
