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
     * Constructor for a new cadet object.
     * @param x The horizontal location of the cadet.
     * @param y The vertical location of the cadet.
     * @param sprite The image of the cadet displayed to the players
     */
    public Cadet(int x, int y){
        super(100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/cadet.png"), 5);
    }

    /**
     * Constructor for a new cadet object when health is specified.
     * @param x The horizontal location of the cadet.
     * @param y The vertical location of the cadet.
     * @param sprite The image of the cadet displayed to the players.
     * @param health The health of the cadet.
     */
    public Cadet(int age, int health, int x, int y){
        super(age, health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/cadet.png"), 5);
    }

     /**
     * Constructor for a new cadet object that is also a spy.
     * @param maxHealth The health of the spy
     * @param x The horizontal location of the cadet/spy.
     * @param y The vertical location of the cadet/spy.
     * @param priority The spy's priority over other npc's
     */
    public Cadet(int maxHealth, int x, int y){
        super(maxHealth, x, y, Toolkit.getDefaultToolkit().getImage("./assets/spy.png"), 3);
    }//end of constructor

    /**
     * Another constructor for a new cadet object that is also a spy when health is specified.
     * @param age The spy's age.
     * @param health The spy's health
     * @param x The horizontal location of the cadet/spy.
     * @param y The vertical location of the cadet/spy.
     * @param priority THe spy's priority over other NPC's.
     */
    public Cadet(int age, int health, int x, int y, int priority){
        super(age, health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/spy.png"), priority);
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
     * Another constructor for a new cadet object that is also a soldier when health is specified.
     * @param age The age of the soldier.
     * @param health The health of the cadet/soldier.
     * @param maxHealth The maxHealth of the cadet/soldier.
     * @param x The horizontal location of the cadet/soldier.
     * @param y The vertical location of the cadet/soldier.
     * @param sprite The image of the cadet/soldier that is displayed to the player.
     * @param priority The priority of the healing in a hospital.
     */
    public Cadet(int age, int health, int maxHealth, int x, int y, Image sprite, int priority){
        super(age, health, maxHealth, x, y, sprite, priority);
    }



    

}
