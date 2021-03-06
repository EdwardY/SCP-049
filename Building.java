
 //imports 
 import java.awt.Image;
 import java.awt.Graphics;
 
 /**
 * Building.java
 * An abstract class, templating for the building that can be built
 * @author Edward Yang
 * @version 1.0 on December 24th 2020
 */




abstract class Building implements Drawable, DestroyableAndRepairable {
    /**The size of each building */
    public static final int SIZE = 128;
    
    /**initial price of the building */
    private int initialPrice;
    /**current level of the building */
    private int level;

    /**The x and y coordinate of the building on the game map */
    private int x,y;
    /** the price per level of upgrade for the building */
    private int maxHealth;
    /**The health of the building */
    private int health;
    /**THe image of the building */
    private Image sprite;

    /**
     * Constructs the object building including all the variable necessary
     * @param initialPrice initial price of the building
     * @param maxHealth max health of the building
     * @param health The health of the building
     * @param sprite The image of the building
     * @param x The x coordinate of the building
     * @param y The y Coordinate of the building
     */
    Building(int initialPrice, int maxHealth, int health, int x, int y){

        this.initialPrice = initialPrice;
        this.maxHealth = maxHealth;
        this.health = health;
        this.x = x;
        this.y = y;
        this.level = (maxHealth + 400)/500;

    }

    /**
     * drawing the building on the main drawing board
     * @param g Graphics to be drawn on
     */
    abstract public void draw(Graphics g);

    /**
     * calculates how much money it takes to upgrade the building
     * @return upgrade price
     */
    public abstract int getUpgradePrice();

    /**
     * upgrades the building
     */
    abstract public void upgrade();
        
    abstract public void downgrade();

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
    public void setLevel(int newLevel){
        this.level = newLevel;
    }

    /**
     * Changes the health of the building currently
     * @param newHealth the new health that the building jas
     */
    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    /**
     * @param newMax the maxHealth of the building is set to newMax
     */
    public void setMaxHealth(int newMax){

        this.maxHealth = newMax;
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
    public int getMaxHealth(){

        return this.maxHealth;
    }

    /**
     * getHealth()
     * @return The amount of health the current building has, returns an int
     */
    public int getHealth(){
        return this.health;
    }   

    /**
     * gets the initial price of the building
     * @return initialPrice, the initial price of the building
     */
    public int getInitialPrice(){
        return this.initialPrice;
    }

    /**
     * getImage()
     * @return Returns the image of the current object, in a Image object
     */
    public Image getImage(){
        return this.sprite;
    }

    /**
     * 
     * @return the level of the building
     */
    public int getLevel(){

        return this.level;
    }
    
    

}
