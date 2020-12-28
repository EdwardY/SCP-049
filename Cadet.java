import java.awt.Image;

/**
 * [Cadet.java]
 * A class for an untrained soldier
 * @author Damon Ma
 * @version 1.0 on December 28, 2020
 */





public class Cadet extends Human{
     /**
     * Constructor for a new cadet object.
     * @param health The health of the cadet.
     * @param maxHealth The maximum health of the cadet.
     * @param x The horizontal location of the cadet.
     * @param y The vertical location of the cadet.
     * @param sprite The image of the cadet that is displayed to the players.
     * @param age The age of the cadet.
     * @param priority The priority of the cadet compared to others.
     * @param hunger The hunger of the cadet.
     * @param maxHunger The maximum hunger of the cadet.

     */
    public Cadet(int health, int maxHealth, int x, int y, Image sprite, int age, int priority, int hunger, int maxHunger){
        super(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger);
    }//end of constructor

    

}
