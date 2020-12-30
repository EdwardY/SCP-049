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
     * @param x The horizontal location of the cadet.
     * @param y The vertical location of the cadet.
     * @param sprite The image of the cadet that is displayed to the players.
     * @param priority The priority of the cadet compared to others.
     */
    public Cadet(int health, int maxHealth, int x, int y, Image sprite, int priority){
        super(health, maxHealth, x, y, sprite, priority);
    }//end of constructor

    

}
