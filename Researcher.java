import java.awt.Toolkit;

/**
 * [Researcher.java]
 * A game object that can research and help upgrade various items and people.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */


public class Researcher extends Human{
    /**The price to produce a researcher. */
    static final int RESEARCHER_PRICE = 15;

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
    * @param age The age of the researcher.
    * @param x The horizontal location of the Researcher.
    * @param y The vertical location of the Researcher.
    */
    public Researcher(int age, int health, int x, int y){
        super(age, health, 100, x, y, Toolkit.getDefaultToolkit().getImage("./assets/researcher.png"), 4); 
    }//end of constructor


}//end of class
