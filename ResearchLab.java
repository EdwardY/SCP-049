/**
 * [ResearchLab.java]
 * An object building on the game map allowing the plaers to upgrade weapons, armor, and develop a cure
 * @author Edward Yang
 * @version 0.1 December 2020
 */

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;

public class ResearchLab extends Military{
    /** Initial health of the research lab */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the research lab */
    public static final int INITIAL_PRICE = 100;
    
    private int PRICE;
    private int NUMBER_OF_TURNS;
    private double progress = 0;
    private double successRate = 5;

    /**
     * ResearchLab constructor
     * @param initialPrice the initial price to build the building 
     * @param maxHealth max health of the building
     * @param health the initial health of the building
     * @param sprite the image of the building on the game board
     * @param x the x coordinate of the building on the game map
     * @param y the y coordinate of the building on the game map
     */
    ResearchLab(int initialPrice, int maxHealth, int health, Image sprite, int x, int y){
        super(initialPrice, maxHealth, health, sprite, x, y);

        //TODO: determine constructors of the private class variables
        //TODO: figure out Research mechanic
    }

    //Class methods
    public void developCure(){

        double randomInt = Math.random() * 100;

        if (randomInt < successRate){

            progress = progress + 10;
        }
        
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

        String stringToReturn = "toMyString";

        return stringToReturn;

    }

    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }

    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Color.MAGENTA, null);
        //Research lab has a background color of magenta
    }

    //Setters
    public void setProgress(double newProgress){

        this.progress = newProgress;
    }

    public void setSuccessRate(double newRate){

        this.successRate = newRate;
    }
}
