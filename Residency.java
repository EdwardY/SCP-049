/**
 * [Residency.java]
 * A building that humans live in
 * @author Edward Yang
 * @version 0.1 Devember 26th 2020
 */

 import java.awt.Image;
 import java.awt.Color;
 import java.awt.Graphics;
 import java.util.ArrayList; 

class Residency extends Building{
    /** Initial health of the residency */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the residency */
    public static final int INITIAL_PRICE = 100;
    /** Initial maximum capacity of the residency */
    public static final int INITIAL_MAX_CAP = 100;

    private int maxCapacity;
    private ArrayList<Human> residents = new ArrayList<>();

 
    /**
     * Constructs a residency building that humans need to live in
     * @param initialPrice initial price of the building
     * @param maxHealth max health of the building
     * @param health the initial health of the building
     * @param sprite the image of the building
     * @param x the x coordinate on the game map of building
     * @param y the y coordinate on the game map of the building
     * @param maxCap the maximum capacity of human residents
     */
    Residency(int initialPrice, int maxHealth, int health, Image sprite, int x, int y, int maxCap){

        super(initialPrice, maxHealth, health, sprite, x, y);
        this.maxCapacity = maxCap;
    }
    
    
    /** 
     * @param human the human to be added to the residency if there's space
     */
    public void addPeople(Human human){

        if(residents.size() < maxCapacity){
            residents.add(human);
        }
    }

    
    /** 
     * @param g draw the building on the game board
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Color.BLUE, null);
        //Residency has background color blue
    }

    
    /** 
     * @param repair some repair mechanic
     */
    public void repair(int repair){

        
    }

    
    /** 
     * @return String format of the object
     */
    public String toString(){

        String stringToReturn = "";

        return stringToReturn;
    }

    
    /** 
     * @param damage the damage that the building is taking (health = health - damage)
     */
    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }

    /**
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        
        return this.getLevel() * 3/2;
    }


    /** */
    public void train(){

        //TODO: fill out this method
    }


}

