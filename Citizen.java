import java.awt.Toolkit;

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
     */
    public Citizen(int x, int y){
        super(100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/citizen.png"), 6);
    }//end of constructor


    /**
     * Another constructor to make a new citizen object when health is specified.
     * @param x The horizontal position of the citizen.
     * @param y The vertical position of the citizen.
     */
    public Citizen(int health, int x, int y){
        super(health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/citizen.png"), 6);
    }//end of constructor


}//end of class
