import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * [SCP.java]
 * The SCP version of the Code-049 game.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 11, 2021
 */



public class SCP extends Player{

    /**The amount of hume points (SCP currency) that the player has. */
    private int hume;
    /**The game window that the player will use to play the game. */
    private SCPGameWindow gameWindow;


    /**
     * Constructor for a new SCP game
     * @param username The username of the player
     * @param playerClient The client that the player is using the send and receive messages from the server.
     * @param opponent The username of the opponent that the player will play against.
     * @param hume The amount of hume points that the player will start off with.
     */
    public SCP(String username, Client playerClient, String opponent, int hume){
        super(username, playerClient, opponent);
        this.hume = hume;
    }//end of constructor


    /**
     * starts the game as the SCP side.
     */
    public void start(){
        gameWindow = new SCPGameWindow();
        gameWindow.run();
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
     * @param level The level of the event.
     */
    public void startEvent(String eventType, int level){
        if(eventType.equals("Stonks")){
            //TODO: add new Stonks event to arraylist
        }else if(eventType.equals("Riot")){
            //TODO: add new Riot event to arraylist
        }else if(eventType.equals("Mutate")){
            //TODO: add new Mutate event to arraylist
        }else if(eventType.equals("warpReality"){
            //TODO: add new WarpReality event to arraylist
        })
    }//end of method

    /**
     * Creates an SCP event that the player chose to use that is location-based.
     * @param eventType The name of the event to be created.
     * @param level The level of the event.
     * @param x The x-value of the event's position.
     * @param y The y-value of the event's position.
     */
    public void startEvent(String eventType, int level, int x, int y){
        if(eventType.equals("Earthquake")){
            //TODO: Create new Earthquake event and add it to the arraylist
        }else if(eventType.equals("Fire")){
            //TODO: Create new Fire event and add it to the arrayList
        }else if(eventType.equals("Thunderstorm")){
            //TODO: Create new Thunderstorm event and add it to the arraylist
        }else if(eventType.equals("Snow")){
            //TODO: Create new Snow event and add it to the arraylist.
        }else if(eventType.equals("tornado")){
            //TODO: Create new tornado event and add it to the arraylist.
        }else if(eventType.equals("Infect")){
            //TODO: Create new Infect event and add it to the arraylist.
        }
    }//end of method



    //start of getters

    /**
     * Gets the player's game window.
     * @return The player's game window.
     */
    public SCPGameWindow getSCPGameWindow(){
        return this.gameWindow;
    }
        /**
     * Gets the amount of hume points that the player has.
     * @return the number of hume points.
     */
    public int getHume(){
        return this.hume;
    }

    //end of getters


    //start of setters

    /**
     * Changes the amount of the player's hume points.
     * @param hume The player's new hume points.
     */
    public void setHume(int hume){
        this.hume = hume;
    }

    //end of setters

    //start of inner class for the game window
    public class SCPGameWindow extends GameWindow{

        /**
         * Runs the SCP version of the game.
         */
        public void run(){
            JFrame gameWindow = this.getWindow();
            gameWindow.setSize(50,50); //TODO: Discuss game window sizes
            gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            JPanel gridPanel = this.getGrid();
            gridPanel.setBounds(0, 0, 0, 0); //TODO: Discuss game window sizes and dimensions of JPanels
            gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            gameWindow.add(gridPanel);


            JPanel infoBarPanel = this.getInfoBar();
            infoBarPanel.setBounds(0,0,0,0); //TODO: Discuss game window sizes and dimensions of JPanels
            infoBarPanel.setBounds(0,0,0,0); //TODO: Discuss game window sizes and dimensions of JPanels
            gameWindow.add(infoBarPanel);

            gameWindow.setVisible(true);
        }// end of window

        /**
         * Updates the game screen.
         */
        public void update(){
            System.out.println("Hello");
            //TODO: Not complete yet
        }

    }//end of inner class

}//end of class