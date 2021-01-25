/**
 * [MilitaryBase.java]
 * A MilitaryBase building that can train store and move soldiers
 * @author Edward Yang
 * @version 0.1 Decemeber 26th 2020
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Toolkit;

class MilitaryBase extends Military{
    /** Initial health of the military base */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the military base */
    public static final int INITIAL_PRICE = 100;

    private ArrayList<Human> soldiers = new ArrayList<>();

    /**
     * Military base constructor
     * Creates a new MilitaryBase building
     * @param initialPrice initial price of building
     * @param maxHealth max health of the building
     * @param health current health of building
     * @param sprite image of building
     * @param x x coordinate of building
     * @param y y coordinate of building
     */
    MilitaryBase(int initialPrice, int maxHealth, int health, int x, int y){

        super(initialPrice, maxHealth, health, x, y);

        this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/MilitaryBase.png"));
    }

    
    //class methods

    /** 
     * @param number The number of troops to train 
     * @param type The type of troop to train 
     */
    public void trainTroops(int number, String type, int armourLevel, int weaponLevel){
        
        if(type.equals("Soldier")){ 

        }else if(type.equals("Spy")){

        }else{
            
        }

    }

    /**
     * Add a Cadet to the soldiers list
     */
    public void add(Human Cadet){

        soldiers.add(Cadet);
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

        g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.BLACK, null);
        //Military base has a black background 
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
        this.setMaxHealth(this.getHealth() + 500);

    }

    /**
     * Downgrade a building and its stats
     */
    public void downgrade(){

        //downgrade a buildings stats 
        this.setLevel(this.getLevel() - 1);
        this.setMaxHealth(this.getMaxHealth() - 500);
    }

    /**
     * Gets the list of soldier in the military base
     * @return The list of soldiers
     */
    public ArrayList<Human> getSoldiers(){
        return this.soldiers;
    }
}
