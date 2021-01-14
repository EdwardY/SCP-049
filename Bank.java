/**
 * [Bank.java]
 * A building extending ProductionFacilities that can be used to produce money
 * @author Edward Yang
 * @version 0.1 December 26th 2020
 */

 import java.awt.Graphics;
 import java.awt.Image;
 import java.awt.Color;

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
    Bank(int initialPrice, int maxHealth, int health, Image sprite, int x, int y){
        super(initialPrice, maxHealth, health, sprite, x, y);

        //TODO: that's a lot of things to figure out how the bank will work
    }


    public int earnResource(){
        int resource = 100; 
        //TODO: figure out how bank makes money
        //TODO: for now bank gets 1000 dollars per round

        return resource;
    }


    /** 
     * @param repair the amount to repair 
     */
    //inherited methods
    public void repair(int repair){

        this.setHealth(this.getHealth() + repair);
        //TODO: clear up out repair mechanic
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
        //TODO: maybe different building have different damage taking mechanics
    }
    

    
    /** 
     * @param g the graphics variable to draw the building on
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Color.GREEN, null);
        //Bank bg color is green of course
    }

    /**
     * @return the cost that the building takes to upgrade
     */
    public int upgrade(){
        
        this.setLevel(this.getLevel() + 1);

        return this.getLevel() * 3/2;
    }

}
