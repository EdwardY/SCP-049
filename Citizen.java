import java.awt.Toolkit;

/**
 * [Citizen.java]
 * An object representing a regular person in society
 * @author Damon Ma
 * @version 1.0 written on December 24, 2020
 */


public class Citizen extends Human{
    /**The price to create a citizen. */
    static final int CITIZEN_PRICE = 5;
    
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
     * @param age The age of the citizen.
     * @param health The health of the citizen
     * @param x The horizontal position of the citizen.
     * @param y The vertical position of the citizen.
     */
    public Citizen(int age, int health, int x, int y){
        super(age, health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/citizen.png"), 6);
    }//end of constructor


}//end of class
