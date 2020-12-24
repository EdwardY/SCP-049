/**
 * [Hospital.java]
 * A hostpital building object allowing NPCs to heal
 * @author Edward Yang
 * @version 0.1 december 24 2020
 */

import java.awt.*;
import java.util.*;

class Hospital extends Building{
    
    private int maxCapacity;
    private PriorityQueue<Human> injured;
    ArrayList<Doctor> doctors;

    /**
     * Hospital Constructor
     * @param initialPrice the initial price of the building
     * @param pricesPerLevel the price per level to upgrade the building
     * @param health the health of the building to begin with
     * @param sprite the image of the building
     * @param x the x coordinate of the building
     * @param y the y coordinate of the building
     * @param maxCapacity the max capacity of the building
     */
    Hospital(int initialPrice, int [] pricesPerLevel, int health, Image sprite, int x, int y, int maxCapacity){
        
        super(initialPrice, pricesPerLevel, health, sprite, x, y);
        this.maxCapacity = maxCapacity;
        doctors = new ArrayList<>();
        injured = new PriorityQueue<>();
    }

    //class methods

    /**
     * Heal the paitient inside the 
     */
    public void heal(){

        //incorporate levels
        //remove human from queue and heal them based on the amount of doctors
    }

    
    /** 
     * @param human the human to be added to the injured list
     */
    public void addInjured(Human human){

        injured.add(human);
    }

    public void setDoctors(){

        //determine how this functions
    }

    //Inherited methods
    
    /** 
     * @param repair
     */
    public void repair(int repair){

        //determine how the repair mechanic is going to work
    }

    
    /** 
     * @param g
     */
    public void draw(Graphics g){

        //figure our how thingy is going to draw
    }

    
    /** 
     * @return String
     */
    public String toString(){

        String stringToReturn = "";

        return stringToReturn;
        //figure out what type of stuff to return
    }

    
    /** 
     * @param price
     */
    public void build(int price){

    }

    
    /** 
     * @param damage The amount of the damage the building is taking
     */
    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }    

}
