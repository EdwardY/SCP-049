import java.awt.Image;

/**
 * [Citizen.java]
 * An object representing a regular person in society
 * @author Damon Ma
 * @version 1.0 written on December 24, 2020
 */


public class Citizen extends Human{
    
    /**
     * Constructor to make a new citizen object.
     * @param health The health of the citizen.
     * @param maxHealth The maximum health of the citizen. 
     * @param x The horizontal position of the citizen.
     * @param y The vertical position of the citizen.
     * @param sprite The sprite of the citizen.
     * @param age The age of the citizen.
     * @param priority The priority of the citizen over all other humans.
     * @param hunger The hunger level of the citizen.
     * @param maxHunger The maximum hunger level of the citizen.
     */
    public Citizen(int health, int maxHealth, int x, int y, Image sprite, int age, int priority, int hunger, int maxHunger){
        super(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger);
    }//end of constructor


}//end of class
