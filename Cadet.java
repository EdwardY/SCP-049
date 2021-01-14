import java.awt.Image;
import java.awt.Toolkit;

/**
 * [Cadet.java]
 * A class for an untrained soldier
 * @author Damon Ma
 * @version 1.0 on December 28, 2020
 */





public class Cadet extends Human{
     /**
     * Constructor for a new cadet object that is also a spy.
     * @param x The horizontal location of the cadet/spy.
     * @param y The vertical location of the cadet/spy.
     */
    public Cadet(int x, int y){
        super(100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/cadet.png"), 5);
    }//end of constructor

    /**
     * A constructor for a new cadet object that is also a soldier.
     * @param health The health of the cadet/soldier.
     * @param maxHealth The maxHealth of the cadet/soldier.
     * @param x The horizontal location of the cadet/soldier.
     * @param y The vertical location of the cadet/soldier.
     * @param sprite The image of the cadet/soldier that is displayed to the player.
     * @param priority The priority of the healing in a hospital.
     */
    public Cadet(int maxHealth, int x, int y, Image sprite, int priority){
        super(maxHealth, x, y, sprite, priority);
    }

    /**
     * Constructor for a new cadet object.
     * @param x The horizontal location of the cadet.
     * @param y The vertical location of the cadet.
     * @param sprite The image of the cadet displayed to the players
     */
    public Cadet(int x, int y, Image sprite){
        super(100, x, y, sprite, 5);
    }

    

}
