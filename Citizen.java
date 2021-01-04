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
     * @param x The horizontal position of the citizen.
     * @param y The vertical position of the citizen.
     * @param sprite The sprite of the citizen.
     * @param age The age of the citizen.
     */
    public Citizen(int x, int y, Image sprite){
        super(100, x, y, sprite, 6);
    }//end of constructor


}//end of class
