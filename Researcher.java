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
    * @param x The horizontal location of the Researcher.
    * @param y The vertical location of the Researcher.
    * @param sprite The image of the Researcher that is displayed to the players.
    * @param priority The priority of the Researcher compared to others.
    */
   public Researcher(int x, int y, Image sprite, int priority){
       super(100, 100, x, y, sprite, priority); 
   }//end of constructor


}//end of class
