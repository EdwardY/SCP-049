import java.awt.Image;

/**
 * [Human.java]
 * An abstract class for a human type object.
 * @author Damon Ma
 * @version 1.0 on December 24, 2020
 */


public abstract class Human extends NPC{
    /**The age of the human */
    private int age;
    /**The priority or importance of the human over all other humans in a hospital. */
    private int priority;
    //The ranking in priority is: Soldier, Doctor, Spy, Researcher, Cadet, Citizen
    /**The hunger of the human, with 0 meaning they are starving to death. */
    private int hunger = 100;    
    /**The maximum fullness a human can have. */
    private int maxHunger = 100;
    /**
     * Constructor for a new Human object.
     * @param maxHealth The maximum health of the human.
     * @param x The horizontal location of the human.
     * @param y The vertical location of the human.
     * @param sprite The image of the human that is displayed to the players.
     * @param priority The priority of the human compared to others.
     * @param hunger The hunger of the human.
     * @param maxHunger The maximum hunger of the human.

     */
    public Human(int maxHealth, int x, int y, Image sprite, int priority){
        super(maxHealth, x, y, sprite);
        this.age = 0;
        this.priority = priority;    
    }//end of constructor

    /**
     * Constructor for a new Human object when health is specified.
     * @param age The age of the human.
     * @param health The health of the human.
     * @param maxHealth The maximum health of the human.
     * @param x The horizontal location of the human.
     * @param y The vertical location of the human.
     * @param sprite The image of the human that is displayed to the players.
     * @param priority The priority of the human compared to others.
     * @param hunger The hunger of the human.
     * @param maxHunger The maximum hunger of the human.

     */
    public Human(int age, int health, int maxHealth, int x, int y, Image sprite, int priority){
        super(health, maxHealth, x, y, sprite);
        this.age = age;
        this.priority = priority;    
    }//end of constructor


    /**
     * Increases the age of the Human.
     */
    public void gotOld(){
        this.age+= 6;        
    }//end of method

    /**
     * Changes the hunger level of the Human.
     * @param change The change in hunger level.
     */
    public void changeHunger(int change){
        this.hunger += change;

        if(this.hunger > maxHunger){ //check if the change in hunger is beyond the limits
            this.hunger = maxHunger;
        }else if (this.hunger <0 ){
            this.hunger = 0;            
        }//end of if statements

        //check if the human is starving to death
        if(this.hunger <= 0){
            this.takeDamage(10);
        }
    }//end of method


    //start of getters

    /**
     * Returns the age of the human.
     * @return The age of the human.
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Returns the priority of the human.
     * @return The priority of the human.
     */
    public int getPriority(){
        return this.priority;
    }

    /**
     * Returns the hunger level of the human.
     * @return The hunger level of the human.
     */
    public int getHunger(){
        return this.hunger;
    }

    /**
     * Returns the maximum hunger level of the human.
     * @return the maximum hunger level of the human.
     */
    public int getMaxHunger(){
        return this.maxHunger;
    }

    //end of getters



    //start of setters

    /**
     * Sets the priority of this human.
     * @param newPriority The new priority of this human.
     */
    public void setPriority(int newPriority){
        this.priority = newPriority;
    }//end of method


    //end of setters

}//end of class
