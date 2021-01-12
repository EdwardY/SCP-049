import java.util.ArrayList;


/**
 * [SCP.java]
 * The SCP version of the Code-049 game.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 11, 2021
 */



public class SCP extends Player{
    /**An arraylist of all SCPs in the game. */
    private ArrayList<SCP0492> scps;
    /**The amount of hume points (SCP currency) that the player has. */
    private int hume;
    

    /**
     * Constructor for a new SCP game
     * @param username The username of the player
     * @param playerClient The client that the player is using the send and receive messages from the server.
     * @param hume The amount of hume points that the player will start off with.
     */
    public SCP(String username, Client playerClient, String opponent, int hume){
        super(username, playerClient, opponent);
        this.hume = hume;
        scps = new ArrayList<SCP0492>();
    }//end of constructor


    /**
     * starts the game as the SCP side.
     */
    public void start(){
        System.out.println("starting game...");
    }


    /**
     * Changes the amount of the player's hume points
     * @param change The amount of hume points that the player will gain or lose.
     */
    public void changeHume(int change){
        this.hume += change;
    }
    
    //TODO: method for client-game transactions


    /**
     * Starts the current turn in the game.
     */
    public void endTurn(){
        System.out.println("Not a functional method yet.");
        //TODO: Not a functional method yet.
    }

    /**
     * Ends the current turn in the game.
     */
    public void startTurn(){
        System.out.println("Not a functional method yet.");
        //TODO: Not a functional method yet.
    }

    /**
     * Creates an SCP event that the player chose to use that is not location-based.
     * @param eventType The name of the event that will be created.
     */
    public void startEvent(String eventType){
        System.out.println("new event!");
    }//end of method

    /**
     * Creates an SCP event that the player chose to use that is location-based.
     * @param eventType The name of the event to be created.
     * @param x The x-value of the event's position.
     * @param y THe y-value of the event's position.
     */
    public void startEvent(String eventType, int x, int y){
        System.out.println("new event!");
    }//end of method



    //start of inner class for the game window
    public class SCPGameWindow extends GameWindow{



        public void update(){
            System.out.println("Game window should open here");
        }
    }

}//end of class