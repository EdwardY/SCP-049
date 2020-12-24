import java.awt.Image;

/**
 * [Researcher.java]
 * A game object that can research and help upgrade various items and people.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */


public class Researcher extends Human{
    
    //TODO: methods that have to do with researching/upgrading mechanics.

    /**
    * Constructor for a new Researcher object.
    * @param health The health of the Researcher.
    * @param maxHealth The maximum health of the Researcher.
    * @param x The horizontal location of the Researcher.
    * @param y The vertical location of the Researcher.
    * @param sprite The image of the Researcher that is displayed to the players.
    * @param age The age of the Researcher.
    * @param priority The priority of the Researcher compared to others.
    * @param hunger The hunger of the Researcher.
    * @param maxHunger The maximum hunger of the human.
    */
   public Researcher(int health, int maxHealth, int x, int y, Image sprite, int age, int priority, int hunger, int maxHunger){
       super(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger); 
   }//end of constructor


}//end of class
