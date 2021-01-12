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

    /**
     * Constructor for the {@code Player} class
     * @param username the username of the {@code Player}
     * @param playerClient The client program of the player that connected to the server.
     */
    public Player(String username, Client playerClient, String opponent){
        this.username = username;
        this.playerClient = playerClient;
        this.opponent = opponent;
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


}//end of class