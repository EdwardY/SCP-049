import java.util.ArrayList;

/**
 * [Game.java]
 * A class for storing all game parts which the server can access
 * @author Damon Ma, Edward Yang, and Vivian Dai
 * @version 1.0 on December 30, 2020
 */

class Game {
    /** The {@code ArrayList} containing all {@code Buildings} in the {@code Game} */
    private ArrayList<Building> buildings;
    /** The {@code NPCs} belonging to the town side */
    private ArrayList<Human> humans;
    /** The {@code NPCs} belonging to the SCP side */
    private ArrayList<SCP0492> scps;
    /** The {@code ArrayList} of ongoing {@code Events} */
    private ArrayList<Event> events;
    /** The amount of money the town side has */
    private int money;
    /** The amount of food the town side has */
    private int food;
    /** The amount of hume points the SCP side has */
    private int hume;
    /** The current turn of the {@code Game} */
    private int turn;
    /** The amount of money increase the town gets each turn */
    private int moneyPerTurn;
    /** The increase in food each turn */
    private int foodPerTurn;
    /** The amount of hume points that are earned each turn */
    private int humePerTurn;

    /**
     * Constructor for the {@code Game} class, assigns preset values
     */
    public Game(){
        this.buildings = new ArrayList<Building>();
        this.humans = new ArrayList<Human>();
        this.scps = new ArrayList<SCP0492>();
        this.events = new ArrayList<Event>();
        this.money = 10;
        this.food = 10;
        this.hume = 10;
        this.turn = 1;
        this.moneyPerTurn = 0;
        this.foodPerTurn = 0;
        this.humePerTurn = 0;
    }

    private void handlePassives(){
        //TODO: deal with passives (moving NPCs, handling events, getting income from buildings, etc.)
    }

    /**
     * Gets the buildings 
     * @return buildings, an {@code ArrayList} of all {@code Building} objects
     */
    public ArrayList<Building> getBuildings(){
        return this.buildings;
    }

    /**
     * Gets the SCP-049-2s
     * @return scps which contains all {@code SCP0492} objects
     */
    public ArrayList<SCP0492> getScps(){
        return this.scps;
    }

    /**
     * Gets the {@code Human} objects 
     * @return humans, a list of all game {@code Human} objects
     */
    public ArrayList<Human> getHumans(){
        return this.humans;
    }

    /**
     * Gets the amount of money the town has
     * @return money, the amount of money the town side has
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * Gets how much food the town has
     * @return food, the amount of food the town has
     */
    public int getFood(){
        return this.food;
    }

    /**
     * Gest how many hume points the SCPs have
     * @return hume, the amount of hume points the SCPs have
     */
    public int getHume(){
        return this.hume;
    }

    /**
     * Gets the current turn
     * @return turn, the number of turns that have passed so far
     */
    public int getTurn(){
        return this.turn;
    }

    //TOOD: literally all the not getter methods

    /**
     * Increases turn number by one then lets the {@code Game} handle all other passive stuff
     */
    public void doTurn(){
        this.turn++;
        this.handlePassives();
    }
}
