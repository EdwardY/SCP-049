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

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;

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
        //TODO: Implement stonks event for TOWN SIDE, does not belong as an scp event
        if(eventType.equals("Riot")){
            //TODO: add new Riot event to arraylist
        }else if(eventType.equals("Mutate")){
            //TODO: add new Mutate event to arraylist
        }else if(eventType.equals("warpReality")){
            //TODO: add new WarpReality event to arraylist
        }
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

    /**
     * An inner class that will run the game window that the player will use.
     */
    public class SCPGameWindow extends GameWindow{

        /**
         * Constructor that will run the SCP version of the game.
         */
        public SCPGameWindow(){
            JFrame gameWindow = this.getWindow();

            ScpGridPanel gridPanel = new ScpGridPanel();
            gridPanel.setBounds(0, 0, 1080, 1080);
            gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            gridPanel.setBackground(Color.gray);

            ScpInfoBarPanel infoBarPanel = new ScpInfoBarPanel();     
            infoBarPanel.setBounds(1080, 0, 256, 1080);
            infoBarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            infoBarPanel.setBackground(Color.white);

            gameWindow.add(gridPanel);
            gameWindow.add(infoBarPanel);

            //let user see the window
            gameWindow.setVisible(true);


            SCP.this.displaySide("SCP");
        }// end of window

        /**
         * Updates the game screen.
         */
        //TODO: Ask what this method is for, not actually sure what it's supposed to do
        public void update(){
            System.out.println("Hello");
            //TODO: Not complete yet
        }

        /**
         * [ScpGridPanel.java]
         * A {@code GridPanel} that draw SCP specific game graphics
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 15, 2021
         */
        private class ScpGridPanel extends GridPanel{
            /**
             * Constructor for the {@code GridPanel}
             */
            private ScpGridPanel(){
                setFocusable(true);
                requestFocusInWindow();
            }

            /**
             * @param g the {@code Graphics} to draw on
             */
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                //TODO: Draws tiles where the buildings are supposed to be, not sure if this is what we want
                g.setColor(Color.BLACK);
                int emptyLotX = 0; //X-value of the empty lot to be drawn
                for(int i = 0; i < this.GRID_SIZE_BUILDING; i ++){
                    int emptyLotY = 0; //Y-value of the empty lot to be drawn
                    for(int j = 0; j < this.GRID_SIZE_BUILDING; j++){
                        g.drawRect(emptyLotX, emptyLotY, 128, 128);
                        emptyLotY += 158;
                    }
                    emptyLotX += 158;
                }


                //draw humans
                for(int i = 0; i < SCP.this.getHumans().size(); i++){
                    SCP.this.getHumans().get(i).draw(g);
                }


                //draw SCP0492s
                for(int i = 0; i < SCP.this.getSCPs().size(); i++){
                    SCP.this.getSCPs().get(i).draw(g);
                }

                //draw buildings
                for(int i = 0; i < SCP.this.getBuildings().size(); i++){
                    SCP.this.getBuildings().get(i).draw(g);
                }

                //draw events
                for(int i = 0; i < SCP.this.getEvents().size(); i++){
                    if(SCP.this.getEvents().get(i) instanceof PhysicalEvent){
                        ((PhysicalEvent)(SCP.this.getEvents().get(i))).draw(g);
                    }
                }


            }//end of method

            

        }

        /**
         * [ScpInfoBarPanel.java]
         * A {@code InfoBarPanel} that displays SCP side specific information
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 15, 2021
         */
        private class ScpInfoBarPanel extends InfoBarPanel{
            /**
             * Constructor for the {@code InfoBarPanel}
             */
            private ScpInfoBarPanel(){
                setFocusable(true);
                requestFocusInWindow();
            }

            /**
             * @param g the {@code Graphics} to draw on
             */
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                //TODO: Set the backgrounds for the other JPanels on both SCP and Town side too
                this.setBackground(Color.WHITE);

                g.setFont(new Font("Courier", Font.BOLD, 18));
                g.setColor(Color.BLACK);

                g.drawString("Username: " + SCP.this.getUsername(), 10, 125);
                g.drawString("Opponent: " + SCP.this.getOpponent(), 10, 150);

                g.drawString("Hume points: " + SCP.this.getHume(), 10, 325);

                g.drawString("Humans: " + SCP.this.getHumans().size(), 10, 425);
                g.drawString("SCP-049-2s: " + SCP.this.getSCPs().size(), 10, 450);
                

                g.setColor(Color.RED);
                g.drawRect(0,475, 500, 50);
                g.setColor(Color.GREEN);
                g.drawRect(0, 475, SCP.this.getHumans().size()/(SCP.this.getHumans().size() + SCP.this.getSCPs().size()),50);


            }
        }

        /**
         * [SCPMouseHandler.java]
         * Inner class for mouse input.
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 16, 2021
         */
        public class SCPMouseHandler extends DuberMouseHandler{
            /**
             * When the mouse's click button is pressed.
             * @param MouseEvent The action performed by the mouse.
             */
            public void mousePressed(MouseEvent e){
            }

            /**
             * When the mouse's click button is released.
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseReleased(MouseEvent e){
            }

            /**
             * When the mouse enters a component.
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseEntered(MouseEvent e){
                if((this.getMouseX() <= 100) && (this.getMouseX() >= 0) && (this.getMouseY() <= 100) && (this.getMouseY() >= 0)){
                    System.out.println("your button works.");
                }
            }


            /**
             * When the mouse exits a component.
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseExited(MouseEvent e){
            }


            /**
             * When the mouse's button is pressed and released.
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseClicked(MouseEvent e){
            }

            /**
             * When the mouse Moved. Updates mouse coordinates
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                // other things 
            }

            /**
             * When the mouse is moved while its button is pressed. Updates mouse coordinates
             * @param MouseEvent The action performed by the mouse.
             */
            public void mouseDragged(MouseEvent e){
                super.mouseDragged(e);
                //other things
            }

        }

    }//end of inner class

}//end of class