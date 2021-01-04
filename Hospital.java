/**
 * [Hospital.java]
 * A hostpital building object allowing NPCs to heal
 * @author Edward Yang
 * @version 0.1 december 24 2020
 */

import java.awt.Image;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.Graphics;
import java.awt.Color;

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
     * Heal the paitient inside the hospital
     */
    public void heal(){

        //incorporate levels
        //remove human from queue and heal them based on the amount of doctors
    }

    
    /** 
     * @param human the human to be added to the injured list
     */
    public void addInjured(Human human){

        if(injured.size() < maxCapacity){
            injured.add(human);
        }
    }

    public void setDoctors(){

        //TODO: determine how to figure out and sort doctors
    }

    //Inherited methods
    
    /** 
     * @param repair
     */
    public void repair(int repair){

        //TODO: determine how the repair mechanic is going to work
    }

    
    /** 
     * @param g
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Color.RED, null);
        //hospital has red background
    }

    
    /** 
     * @return String
     */
    public String toString(){

        String stringToReturn = "";

        return stringToReturn;
        //TODO: Figure out how to string format it
    }

    
    /** 
     * @param price
     */
    public void build(int price){
        //TODO: remove or find a function for "build"
    }

    
    /** 
     * @param damage The amount of the damage the building is taking
     */
    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }    

}
