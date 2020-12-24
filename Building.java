/**
 * Building.java
 * An abstract class, templating for the building that can be built
 * @author Edward Yang
 * @version 1.0 on December 24th 2020
 */


import java.awt.*;


abstract class Building implements Buildable, Drawable, DestroyableAndRepairable {
    
    /**initial price of the building */
    private int initialPrice;
    /**current level of the building */
    private int level;
    /**the width and height of the building? not sure if we need it right now */
    private int width, height;
    /**The x and y coordinate of the building on the game map */
    private int x,y;
    /** the price per level of upgrade for the building */
    private int[] pricesPerLevel;
    /**The health of the building */
    private int health;
    /**THe image of the building */
    private Image sprite;

    /**
     * Constructs the object building including all the variable necessary
     * @param initialPrice initial price of the building
     * @param pricesPerLevel The price per level of upgrade for the building
     * @param health The health of the building
     * @param sprite The image of the building
     * @param x The x coordinate of the building
     * @param y The y Coordinate of the building
     */
    Building(int initialPrice, int [] pricesPerLevel, int health, Image sprite, int x, int y){

        this.initialPrice = initialPrice;
        this.pricesPerLevel = pricesPerLevel;
        this.health = health;
        this.x = x;
        this.y = y;
        this.sprite = sprite; 
        this.level = 1;

    }

    /**
     * drawing the building on the main drawing board
     * @param g Graphics to be drawn on
     */
    abstract public void draw(Graphics g);

    /**
     * Converts the building information to string to print out in some form
     * 
     */
    abstract public String toString();

    public void upgrade(){
        //figure out how to upgrade
    }

    /**SETTERS */

    /**
     * Change the image of the building destroyed, different level
     * @param sprite The image of the building
     */
    public void setSprite(Image sprite){

        this.sprite = sprite;
    }

    /**
     * Setting the level of the building
     * @param level The level of the building
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Changes the health of the building currently
     * @param newHealth the new health that the building jas
     */
    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    /**GETTERS */
    /**
     * getX()
     * @return The x coordinate on the gameboard
     */
    public int getX(){
        return this.x;
    }

    /**
     * getX()
     * @return The y coordinate on the gameboard
     */
    public int getY(){
        return this.y;
    }

    /**
     * getPricesPerLevel
     * @return an int array of the prices it take to upgrade per level, index 0 is to upgrade from the first level to second
     */
    public int[] getPricesPerLevel(){

        return this.pricesPerLevel;
    }

    /**
     * getHealth()
     * @return The amount of health the current building has, returns an int
     */
    public int getHealth(){
        return this.health;
    }   

    /**
     * getImage()
     * @return Returns the image of the current object, in a Image object
     */
    public Image getImage(){
        return this.sprite;
    }
    

}
