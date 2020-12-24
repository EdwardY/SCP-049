/**
 * [ResearchLab.java]
 * An object building on the game map allowing the plaers to upgrade weapons, armor, and develop a cure
 * @author Edward Yang
 * @version 0.1 December 2020
 */

import java.awt.*;

public class ResearchLab extends Military{
    
    private int PRICE;
    private int NUMBER_OF_TURNS;
    private double progress;
    private double successRate;

    /**
     * ResearchLab constructor
     * @param initialPrice the initial price to build the building 
     * @param pricesPerLevel the price per level to upgrade the building
     * @param health the initial health of the building
     * @param sprite the image of the building on the game board
     * @param x the x coordinate of the building on the game map
     * @param y the y coordinate of the building on the game map
     */
    ResearchLab(int initialPrice, int [] pricesPerLevel, int health, Image sprite, int x, int y){
        super(initialPrice, pricesPerLevel, health, sprite, x, y);

        //TODO: determine constructors of the private class variables
    }

    //Class methods
    public void developCure(){

        //TODO: figure out the developCure mechanic
    }

    public void researchWeapon(){

        //TODO: figure out the reserachWeapon and armour mechanic
    }

    public void researchArmour(){

    }



    //Inherited methods

    public void repair(int repair){

    }

    public String toString(){

        String stringToReturn = "";

        return stringToReturn;

    }

    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }

    public void draw(Graphics g){

        //TODO: figure out how to draw this
    }
}
