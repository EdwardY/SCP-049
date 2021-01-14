import java.awt.Toolkit;

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
    */
   public Researcher(int x, int y){
       super(100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/researcher.png"), 4); 
   }//end of constructor


    /**
    * Another constructor for a new Researcher object when health is specified.
    * @param x The horizontal location of the Researcher.
    * @param y The vertical location of the Researcher.
    */
    public Researcher(int health, int x, int y){
        super(health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/researcher.png"), 4); 
    }//end of constructor


}//end of class
