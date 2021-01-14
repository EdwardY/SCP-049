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
 import java.awt.Toolkit;

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
    Residency(int initialPrice, int maxHealth, int health, int x, int y, int maxCap){

        super(initialPrice, maxHealth, health, x, y);
        this.maxCapacity = maxCap;

        this.setSprite(Toolkit.getDefaultToolkit().getImage("./assets/Residency.png"));
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
        
        return this.getLevel() * 3/2*1000;
    }


    /**
     * 
     * @param training how many adults to be converted 
     * @param type The type of Human to be trained to become
     */
    public void train(int training, String type){

        int trained = 0;

        for(int i = 0; i < residents.size() && trained < training ;i ++){
            
            if(residents.get(i).getAge() >= 18){

                //TODO:convert humans into stuff
            }

        }
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

    /**
     * @param amount Creating a certain amount of citizens
     */
    public void createCitizen(int amount){

        for(int i = 0; i < amount; i ++){

            residents.add(new Citizen(0, 100, this.getX(), this.getY()));
        }
    }

    
    public void specialize(int amount, String type){

        //TODO: I might have to allocate and switch the coordinate of NPCS ask vivian and Damon
        //public void move/allocate()
    }

    /**
     * @return 
     */
    public int getAdults(){

        int adults = 0;

        //Check if they are 18
        for(int i = 0; i < residents.size(); i ++){

            if(residents.get(i).getAge() >= 18){

                adults ++;
            }
        }
        return adults;
    }
}

