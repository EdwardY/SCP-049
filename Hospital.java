import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * [Hospital.java]
 * A hostpital building object allowing NPCs to heal
 * @author Edward Yang
 * @version 0.1 december 24 2020
 */

class Hospital extends Building{
    
    /** Initial health of the hospital */
    public static final int INITIAL_HEALTH = 100;
    /** Initial price of the hospital */
    public static final int INITIAL_PRICE = 100;
    /** Initial max capacity of the hospital */
    public static final int INITIAL_MAX_CAP = 100;

    private int maxCapacity;
    private PriorityQueue<Human> injured;
    ArrayList<Doctor> doctors;

    /**
     * Hospital Constructor
     * @param initialPrice the initial price of the building
     * @param maxHealth max health of the building
     * @param health the health of the building to begin with
     * @param sprite the image of the building
     * @param x the x coordinate of the building
     * @param y the y coordinate of the building
     * @param maxCapacity the max capacity of the building
     */
    Hospital(int initialPrice, int maxHealth, int health, int x, int y, int maxCapacity){
        
        super(initialPrice, maxHealth, health,  x, y);
        this.maxCapacity = maxCapacity;
        doctors = new ArrayList<>();
        injured = new PriorityQueue<>();
        this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/Hospital.png"));

    }

    //class methods

    /**
     * Heal the paitient inside the hospital
     */
    public void heal(){

       for(int i = 0; i < doctors.size() && i < injured.size(); i ++){

            if(injured.peek() != null){
                Human patient = injured.remove();

                //Doctors heall 100o
                patient.repair(1000);
            }
       }
    }

    
    /** 
     * @param human the human to be added to the injured list
     */
    public void addInjured(Human human){
        
        injured.add(human);

    }


    /**
     * 
     * @param doctor The doctor to be added
     */
    public void addDoctors(Doctor doctor){

        if(doctors.size() < maxCapacity){
            doctors.add(doctor);
        }
    }

    /**
     * Removing doctors in case of SCP attacking and converting
     * @param numberRemoved number of doctors removed
     */
    public void removeDoctor(int numberRemoved){
        //TODO: figure out if the same object needs to be removed or just the number of doctors need to be the same
    }

    //Inherited methods
    
    /** 
     * @param repair The amount of health the building it going to be repaired by
     */
    public void repair(int repair){

        this.setHealth(this.getHealth() + repair);
    }


    /** 
     * @param g
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.RED, null);
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

    /**
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        
        return this.getLevel() * 3/2*1000;
    }

    /**
     * 
     * @return The arrayList of doctors 
     */
    public ArrayList<Doctor> getDoctors(){

        return this.doctors;
    }

    /**
     * 
     * @return the max capacity of doctors that can be inside the building
     */
    public int getMaxCapacity(){

        return this.maxCapacity;
    }

    /**
     * the current capacity of injured people
     * @return the number of injured people
     */
    public int  getCurrentCapacity(){
        return injured.size();
    }

    /**
     * Upgrade the bulding's level and its stats
     */
    public void upgrade(){

        //upgrade stats
        this.setLevel(this.getLevel() + 1);
        this.setMaxHealth(this.getHealth() + 500);
        this.maxCapacity += 20;

    }

    /**
     * Downgrade a building and its stats
     */
    public void downgrade(){

        //downgrade a buildings stats 
        this.setLevel(this.getLevel() - 1);
        this.setMaxHealth(this.getHealth() - 500);
        this.maxCapacity -= 20;

        //TODO: what happens when health / capacity goes too low during an downgrade
    }
}
