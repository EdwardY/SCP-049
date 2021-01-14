/**
 * [Military.java]
 * Abstract class for military buildings 
 * @author Edward Yang
 * @Version 0.1 December 24th 2020
 */

import java.awt.Image;

abstract class Military extends Building{
    
    private static boolean[] unlockedTroops;
    private static int weaponLevel;

    /**
     * Military Constructor
     * @param initialPrice the initial price to build the building
     * @param maxHealth max health of the building
     * @param health The health of the biulding that it initially has
     * @param sprite The image of the current building
     * @param x The x coordinate of the building on the game map
     * @param y The y coordinate of the building on the game map
     */
    Military(int initialPrice, int maxHealth, int health, int x, int y){
        super(initialPrice, maxHealth, health, x, y);

        //TODO: figure out all these thingies
    }
}
