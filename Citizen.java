import java.awt.Image;

/**
 * [Citizen.java]
 * An object representing a regular person in society
 * @author Damon Ma
 * @version 1.0 written on December 24, 2020
 */


public class Citizen extends Human{
    
    /**
     * Constructor to make a new citizen object.
     * @param health The health of the citizen.
     * @param maxHealth The maximum health of the citizen. 
     * @param x The horizontal position of the citizen.
     * @param y The vertical position of the citizen.
     * @param sprite The sprite of the citizen.
     * @param age The age of the citizen.
     * @param priority The priority of the citizen over all other humans.
     * @param hunger The hunger level of the citizen.
     * @param maxHunger The maximum hunger level of the citizen.
     */
    public Citizen(int health, int maxHealth, int x, int y, Image sprite, int age, int priority, int hunger, int maxHunger){
        super(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger);
    }//end of constructor



    //TODO: This is an incomplete method since storing game elements still has to be discussed.
    /**
     * Converts the citizen into a specialized type of human.
     * @param newType The new type o-f NPc this citizen will become;
     */
    public void convert(String newType){

        //get the values of this citizen
        int health = this.getHealth();
        int maxHealth = this.getMaxHealth();
        int x = this.getX();
        int y = this.getY();
        Image sprite = this.getSprite();
        int priority = this.getPriority();
        int age = this.getAge();
        int hunger = this.getHunger();
        int maxHunger = this.getMaxHunger();
        //end getting values of citizen


        //check which specialized human this citizen will turn into
        if(newType.equals("Researcher")){
            Researcher newResearcher = new Researcher(health, maxHealth, x, y, sprite, priority, age, hunger, maxHunger);
        }else if(newType.equals("Spy")){
            Spy newSpy = new Spy(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger, 0, 0.0, 0.0);
        }else if(newType.equals("Doctor")){
            Doctor newDoctor = new Doctor(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger, 0);
        }else if(newType.equals("Soldier")){
            Soldier newSoldier = new Soldier(health, maxHealth, x, y, sprite, age, priority, hunger, maxHunger, 0);
        }//end of if statements

    }//end of method

}//end of class
