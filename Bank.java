import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * [Bank.java]
 * A building extending ProductionFacilities that can be used to produce money
 * @author Edward Yang
 * @version 0.1 December 26th 2020
 */


class Bank extends ProductionFacility{
    /** Initial health of the bank */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the bank */
    public static final int INITIAL_PRICE = 100;
    
    /**
     * A building that's serves to produce money
     * @param initialPrice The initial price to build the building
     * @param maxHealth max health of the building
     * @param health The initial health of the building
     * @param sprite The image of the building
     * @param x the x coordinate of the building on the game map
     * @param y The y coordinate of teh building on the game map
     */
    Bank(int initialPrice, int maxHealth, int health, int x, int y){
        super(initialPrice, maxHealth, health, x, y);

        this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/Bank.png"));
    }


    public int earnResource(){
        
        int resource = 500 + this.getLevel()*400; 

        return resource;
    }


    /** 
     * @param repair the amount to repair 
     */
    //inherited methods
    public void repair(int repair){

        this.setHealth(this.getHealth() + repair);
    }

    
    /** 
     * @return String format of the object
     */
    public String toString(){

        String stringToReturn = "";

        return stringToReturn;

    }
    
    
    /** 
     * @param damage the damage that the building is going to take
     */
    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);

    }
    

    
    /** 
     * @param g the graphics variable to draw the building on
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.GREEN, null);
        //Bank bg color is green of course
    }

    /**
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        
        return this.getLevel() * 3/2*100;
    }

    /**
     * Upgrade the bulding's level and its stats
     */
    public void upgrade(){

        //upgrade stats
        this.setLevel(this.getLevel() + 1);
        this.setMaxHealth(this.getMaxHealth() + 500);
        this.setHealth(this.getHealth() + 500);


    }

    /**
     * Downgrade a building and its stats
     */
    public void downgrade(){

        //downgrade a buildings stats 
        this.setLevel(this.getLevel() - 1);
        this.setMaxHealth(this.getMaxHealth() - 500);
        this.setHealth(this.getHealth() - 500);

    }
}
