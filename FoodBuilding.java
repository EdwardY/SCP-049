/**
 * [FoodBuilding.java]
 * A building extending ProductionFacilities that can be used to produce money
 * @author Edward Yang
 * @version 0.1 December 26th 2020
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

class FoodBuilding extends ProductionFacility{
   
   /**
    * A building that's serves to produce food
    * @param initialPrice The initial price to build the building
    * @param maxHealth max health of the buildinge
    * @param health The initial health of the building
    * @param sprite The image of the building
    * @param x the x coordinate of the building on the game map
    * @param y The y coordinate of teh building on the game map
    */
   FoodBuilding(int initialPrice, int maxHealth, int health, Image sprite, int x, int y){
       super(initialPrice, maxHealth, health, sprite, x, y);

       //TODO: that's a lot of things to figure out how the bank will work
   }


   public int earnResource(){
       int resource = 0; 
       //TODO: figure out how bank makes money
       return resource;
   }


   /** 
    * @param repair the amount to repair 
    */
   //inherited methods
   public void repair(int repair){

       //TODO: figure out repair mechanic
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

    g.drawImage(this.getImage(), this.getX(), this.getY(), Color.ORANGE, null);
    //FoodBUilding has orange background
   }


}
