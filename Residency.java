 import java.awt.Color;
 import java.awt.Graphics;
 import java.util.ArrayList; 
 import java.awt.Toolkit;
 
 /**
 * [Residency.java]
 * A building that humans live in
 * @author Edward Yang
 * @version 0.1 Devember 26th 2020
 */

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
     * @param g draw the building on the game board
     */
    public void draw(Graphics g){

        g.drawImage(this.getImage(), this.getX(), this.getY(), Building.SIZE, Building.SIZE, Color.BLUE, null);
        //Residency has background color blue
    }

    
    /** 
     * @param repair some repair mechanic
     */
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
     * @param damage the damage that the building is taking (health = health - damage)
     */
    public void takeDamage(int damage){

        this.setHealth(this.getHealth() - damage);
    }

    /**
     * @return the cost that the building takes to upgrade
     */
    public int getUpgradePrice(){
        
        return this.getLevel() * 3/2*100;
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
        this.setMaxHealth(this.getMaxHealth() - 500);
        this.maxCapacity -= 20;

    }

    /**
     * @param amount Creating a certain amount of citizens
     */
    public void createCitizen(Citizen citizen){

        residents.add(citizen);

    }

    /** */
    public ArrayList<NPC> getAdultPop(){
        ArrayList<NPC> adults = new ArrayList<>();
        for(int i = 0; i < residents.size(); i ++){

            if(residents.get(i).getAge() >= 18){
                adults.add(residents.get(i));
            }
        }

        return adults;
    }

    /**
     * @return the amount of adults available to be converted to a certain type of person
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

    /**
     * A turn has passed and everyone in the residency ages
     */
    public void gotOld(){

        for (int i = 0; i < residents.size(); i ++){

            residents.get(i).gotOld();
        }
    }

    /**
     * 
     * @return The max capacity of this residency
     */
    public int getMaxCap(){

        return this.maxCapacity;
    }

    /**
     * 
     * @return the number of people in this residency
     */
    public int getCurrentPopulation(){

        return residents.size(); 
    }


    /**
     * Gets the residents in this building
     * @return The list of residents
     */
    public ArrayList<Human> getResidents(){
        return this.residents;
    }
}

