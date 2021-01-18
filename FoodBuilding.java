import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;

/**
 * [FoodBuilding.java]
 * A building extending ProductionFacilities that can be used to produce money
 * @author Edward Yang
 * @version 0.1 December 26th 2020
 */


class FoodBuilding extends ProductionFacility{
    
    /** Initial health of the food building */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the food building */
    public static final int INITIAL_PRICE = 100;
   
   /**
    * A building that's serves to produce food
    * @param initialPrice The initial price to build the building
    * @param maxHealth max health of the buildinge
    * @param health The initial health of the building
    * @param sprite The image of the building
    * @param x the x coordinate of the building on the game map
    * @param y The y coordinate of teh building on the game map
    */
   FoodBuilding(int initialPrice, int maxHealth, int health, int x, int y){
       super(initialPrice, maxHealth, health, x, y);

       this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/FoodBuilding.png"));
       //TODO: that's a lot of things to figure out how the bank will work
   }


   public int earnResource(){

       int resource = 1000 + this.getLevel()*500;
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
       //TODO: maybe different building have different damage taking mechanics
   }
   

   
   /** 
    * @param g the graphics variable to draw the building on
    */
   public void draw(Graphics g){

    g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.ORANGE, null);
    //FoodBUilding has orange background
   }


    /**
     * Calculates cost of upgrade
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        return this.getLevel() * 3/2*1000;
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
        this.setMaxHealth(this.getHealth() - 500);

        //TODO: what happens when health / capacity goes too low during an downgrade
    }

}
