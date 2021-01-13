//swing imports
import javax.swing.JFrame;

//util imports
import java.util.ArrayList;

/**
 * [Player.java]
 * Stores information about the player
 * @author Damon Ma, Edward Yang, and Vivian Dai
 * @version 1.0 on January 5, 2021
 */

abstract class Player {
    /** Username of the player */
    private String username;
    /** client that this player needs to communicate to */
    private Client playerClient;
    /**The opponent this player will be playing against. */
    private String opponent;
    /** A list of all frames */
    private ArrayList<JFrame> gameFrames;
    /**An arrayList of enemy humans. */
    private ArrayList<Human> humans;
    /**An arrayList of enemy buildings. */
    private ArrayList<Building> buildings;
    /** An ArrayList of all events.*/
    private ArrayList<Event> events;
    /**An arraylist of all SCPs in the game. */
    private ArrayList<SCP0492> scps;

    /**
     * Constructor for the {@code Player} class
     * @param username the username of the {@code Player}
     * @param playerClient The client program of the player that connected to the server.
     * @param opponent The username of the opponent that the player will play against.
     */
    public Player(String username, Client playerClient, String opponent){
        this.username = username;
        this.playerClient = playerClient;
        this.opponent = opponent;
        this.gameFrames = new ArrayList<JFrame>();
        this.humans = new ArrayList<Human>();
        this.buildings = new ArrayList<Building>();
        this.events = new ArrayList<Event>();
        this.scps = new ArrayList<SCP0492>();
    }

    /**
     * Gets the JFrames in the game
     * @return gameFrames, the frames in the game
     */
    public ArrayList<JFrame> getGameGraphics(){
        return this.gameFrames;
    }

    /**
     * Abstract method starts the game.
     */
    public abstract void start();

    /**
     * Abstract method starts the next turn of the game.
     */
    public abstract void startTurn();

    /**
     * Abstract method ends the current turn of the game.
     */
    public abstract void endTurn();

    //TODO: abstract method for client-game transactions.


    //start of getters




    /**
     * Gets all of the player's SCP0492's.
     * @return The player's SCP0492's.
     */
    public  ArrayList<SCP0492> getSCPs(){
        return this.scps;
    }




    /**
     * Gets the list of human NPC's.
     * @return The list of humans.
     */
    public ArrayList<Human> getHumans(){
        return this.humans;
    }

    /**
     * Gets the list of buildings.
     * @return The list of buildings.
     */
    public ArrayList<Building> getBuildings(){
        return this.buildings;
    }

    /**
     * Gets the list of events in the game.
     * @return The list of game events.
     */
    public ArrayList<Event> getEvents(){
        return this.events;
    }
    //end of getters


    //start of setters



    /**
     * Sets all of the human NPC's.
     * @param humans The list of humans.
     */
    public void setHumans(ArrayList<Human> humans){
        this.humans = humans;
    }

    /**
     * Sets the list of all SCP0492 NPC's.
     * @param scps The list of SCP0492's.
     */
    public void setSCPs(ArrayList<SCP0492> scps){
        this.scps = scps;
    }


    /**
     * Sets the list of all game events.
     * @param events The list of events.
     */
    public void setEvents(ArrayList<Event> events){
        this.events = events;
    }

    /**
     * Sets the list of all buildings.
     * @param buildings The list of buildings.
     */
    public void setBuildings(ArrayList<Building> buildings){
        this.buildings = buildings;
    }

    //end of setters

}//end of class