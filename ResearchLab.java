
import java.awt.Graphics;
import java.awt.Color;
import java.lang.Math;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * [ResearchLab.java]
 * An object building on the game map allowing the plaers to upgrade weapons, armor, and develop a cure
 * @author Edward Yang
 * @version 0.1 December 2020
 */


public class ResearchLab extends Military{
    /** Initial health of the research lab */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the research lab */
    public static final int INITIAL_PRICE = 100;
    
    private int PRICE;
    private int NUMBER_OF_TURNS;
    private double progress = 0;
    private double successRate = 0.05;
    private int weaponLevel = 1;
    private int armourLevel = 1;
    ArrayList<Researcher> researchers = new ArrayList<>(); 


    /**
     * ResearchLab constructor
     * @param initialPrice the initial price to build the building 
     * @param maxHealth max health of the building
     * @param health the initial health of the building
     * @param sprite the image of the building on the game board
     * @param x the x coordinate of the building on the game map
     * @param y the y coordinate of the building on the game map
     */
    ResearchLab(int initialPrice, int maxHealth, int health, int x, int y){
        super(initialPrice, maxHealth, health, x, y);


        this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/ResearchLab.png"));

    }

    //Class methods

    public double developCure(){

        setSuccessRate(researchers.size() * 5);
        double randomInt = Math.random() * 100;

        if (randomInt < successRate){

            return 25;
        }else{
            return 0; 
        }
        
    }

    public int researchWeapon(){
        
        this.weaponLevel ++;
        return 1500 + this.weaponLevel * 500;
        
    }

    public int researchArmour(){
        this.armourLevel ++;
        return 1500 + this.weaponLevel * 500;
    }



    //Inherited methods

    public void repair(int repair){
        
        this.setHealth(this.getHealth() + repair);
    }

    public String toString(){

        String stringToReturn = "toMyString";

        return stringToReturn;

    }

    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }

    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.MAGENTA, null);
        //Research lab has a background color of magenta
    }

    /**
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        

        return this.getLevel() * 3/2*100;
    }


    //Setters
    public void setProgress(double newProgress){

        this.progress = newProgress;
    }

    public void setSuccessRate(double newRate){

        this.successRate = newRate;
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

    /**
     * 
     * @return the weapon level unlocked by research
     */
    public int getWeaponLevel(){

        return this.weaponLevel;
    }

    /**
     * 
     * @return the armour level unlocked by research
     */
    public int getArmourLevel(){

        return this.armourLevel;
    }
    
    /**
     * Gets the researchers in the research lab
     * @return The list of researchers
     */
    public ArrayList<Researcher> getResearchers(){
        return this.researchers;
    } 


    /**
     * Adds the researcher to the lab
     * @Param researcher the researcher npc
     */
    public void add(Researcher researcher){
        researchers.add(researcher);

    }


}
