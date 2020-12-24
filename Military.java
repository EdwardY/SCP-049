/**
 * [Military.java]
 * Abstract class for military buildings 
 * @author Edward Yang
 * @Version 0.1 December 24th 2020
 */

import java.util.*;
import java.awt.*;

abstract class Military extends Building{
    
    private static boolean[] unlockedTroops;
    private static int weaponLevel;

    /**
     * Military Constructor
     * @param initialPrice the initial price to build the building
     * @param pricesPerLevel The price per level to upgrade the building
     * @param health The health of the biulding that it initially has
     * @param sprite The image of the current building
     * @param x The x coordinate of the building on the game map
     * @param y The y coordinate of the building on the game map
     */
    Military(int initialPrice, int [] pricesPerLevel, int health, Image sprite, int x, int y){
        super(initialPrice, pricesPerLevel, health, sprite, x, y);

    }
}
