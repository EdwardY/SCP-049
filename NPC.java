import java.awt.Image;
import java.awt.Graphics;
/**
 * [NPC.java]
 * An abstract class for an NPC (Non-player character).
 * @author Damon Ma
 * @version 1.0 written on December 24, 2020
 */


public abstract class NPC implements Moveable, Drawable, DestroyableAndRepairable{
    /**The current health of the NPC. */
    private int health;
    /**The maximum amount of health the NPC can have. */
    private int maxHealth;
    /**The x-value of the NPC's location on the grid. */
    private int x;
    /**The y-value of the NPC's location on the grid.*/
    private int y;
    /**The image of the NPC that will be shown to the user. */
    private Image sprite;

    /**
     * A constructor for the NPC class.
     * @param health The health of the NPC.
     * @param maxHealth The maximum health of the NPC.
     * @param x The x-value of the NPC's location on the grid.
     * @param y The y-value of the NPC's location on the grid.
     * @param sprite The image that the NPC will have.
     */
    public NPC (int health, int maxHealth, int x, int y, Image sprite){
        this.health = health;
        this.maxHealth = maxHealth;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }//end of constructor



    /**
     * Method checks if the NPC has died or not
     * @return The boolean stating whether or not the NPC is dead.
     */
    public boolean isDead(){
        if (health <= 0){
            return true;           
        }
        return false; //if the NPC is not dead
    }//end of method

    /**
     * Method used for when the NPC takes damage.
     * @param damage The amount of damage that the NPC will take.
     */
    public void takeDamage(int damage){
        this.health -= damage;
        if(this.health < 0){ //checks if the health needs to be set to 0, because you cannot have negative health
            this.health = 0;            
        }//end of if statement
    }//end of method

    /**
     * Method used for when the NPC is healed.
     * @param repair The amount of health that the NPC will recover.
     */
    public void repair(int repair){
        this.health += repair;     
        if(this.health > this.maxHealth){ //checks if the health exceeds the maximum amount of health the NPC can have, since this cannot be surpassed
            this.health = this.maxHealth;
        }   //end of if statement
    }//end of method

    /**
     * Method to draw the NPC.
     * @param g The object that will draw the NPC.
     */
    public void draw(Graphics g){
        g.drawImage(this.sprite, this.x, this.y, null);
    }//end of method

    /**
     * Method moves the NPC's position.
     * @param xDistance The amount of units the NPC will move horizontally.
     * @param yDistance The amount of units the NPC will move vertically.
     */
    public void translate(int xDistance, int yDistance){
        this.x += xDistance;
        this.y += yDistance;
    }//end of method

    //start of getters

    /**
     * Returns the health of the NPC.
     * @return The health of the NPC.
     */
    public int getHealth(){
        return this.health;        
    }

    /**
     * Returns the maximum health of the NPC.
     * @return The maximum health of the NPC.
     */
    public int getMaxHealth(){
        return this.maxHealth;
    }

    /**
     * Returns the x-value of the NPC's location.
     * @return The x-value of the NPC's location.
     */
    public int getX(){
        return this.x;
    }

    /**
     * Returns the y-value of the NPC's location.
     * @return the y-value of the NPC's location.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Returns the sprite of this NPC.
     * @return the NPC's sprite.
     */
    public Image getSprite(){
        return this.sprite;
    }


    //end of getters





    //start of setters

    /**
     * Updates the maximum health of the NPC if they level up.
     * @param newMaxHealth The new Maximum health of the NPC.
     */
    public void setMaxHealth(int newMaxHealth){
        this.maxHealth = newMaxHealth;
    }

    //end of setters

}//end of class
