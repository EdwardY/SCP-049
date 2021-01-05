/**
 * [ProductionFacility.java]
 * buildings that will be producing resources
 * @author Edward Yang
 * @version 0.1 December 26th 2020
 */


import java.awt.Graphics;
import java.awt.Image;


abstract class ProductionFacility extends Building{
    
    private int resource;

    /**
     * Constructor of ProductionFacility
     * buildings that can produce resources
     * @param initialPrice the intial price to build the building
     * @param maxHealth The max health of a building
     * @param health the health of the inital building
     * @param sprite the image of the building
     * @param x the x coordinate on the game map of the building's lcoation
     * @param y the y coordinate on the gmae map of the building's location
     */
    ProductionFacility(int initialPrice, int maxHealth, int health, Image sprite, int x, int y){
        super(initialPrice, maxHealth, health, sprite, x, y);
    };

    abstract public int earnResource();
}
