import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
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
    /**An ArrayList of enemy humans that the SCP must infect or destroy. */
    private ArrayList<Human> humanList;

    /** Type of event to start */
    private String type;
    /** Level of event to start */
    private String level;
    /** Location to start events */
    private int eventX, eventY;


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
        this.humanList = new ArrayList<Human>();
        this.eventX = -1;
        this.eventY = -1;
    }//end of constructor

    /**
     * Sends a message to the server.
     * @param message The message to be sent.
     */
    public void sendMessage(String message){
        SCP.this.getPlayerClient().sendMessage(message);
    }


    /**
     * Requests a location-based event to the server.
     * @param type The type of event.
     * @param level The level of the event.
     * @param x The x-value of the event's coordinates.
     * @param y The y-value of the event's coordinates.
     */
    private void requestEvent(String type, String level, int x, int y){
        //request 
        sendMessage("<e>");
        sendMessage(type);
        sendMessage(level);
        sendMessage(x + " " + y);

        //save last request
        this.getPlayerClient().setLastRequest("<e> " + type + " " + level + " " + x + " " + y);

        //deactivate buttons
        for(int i = 0;i < this.gameWindow.aoeEventButtons.length;i++){
            this.gameWindow.aoeEventButtons[i].deactivate();
        }
        for(int i = 0;i < this.gameWindow.levels.length;i++){
            this.gameWindow.levels[i].deactivate();
        }
        this.gameWindow.startEventButton.deactivate();
    }


    private void requestEvent(String type, String level){
        //request
        sendMessage("<e>");
        sendMessage(type);
        sendMessage(level);

        //save request
        this.getPlayerClient().setLastRequest("<e> " + type + " " + level);

        //deactivate buttons
        for(int i = 0;i < this.gameWindow.aoeEventButtons.length;i++){
            this.gameWindow.aoeEventButtons[i].deactivate();
        }
        for(int i = 0;i < this.gameWindow.levels.length;i++){
            this.gameWindow.levels[i].deactivate();
        }
        this.gameWindow.startEventButton.deactivate();
    }

    /**
     * starts the game as the SCP side.
     */
    public void start(){
        this.gameWindow = new SCPGameWindow();
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
        if(eventType.equals("Riot")){
            this.getEvents().add(new Riot(level));
        }else if(eventType.equals("Mutate")){
            this.getEvents().add(new Mutate(level));
        }else if(eventType.equals("WarpReality")){
            this.getEvents().add(new WarpReality(level));
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
            this.getEvents().add(new Earthquake(level, x, y));
        }else if(eventType.equals("Fire")){
            this.getEvents().add(new Fire(level, x, y));
        }else if(eventType.equals("Thunderstorm")){
            this.getEvents().add(new Thunderstorm(level, x, y));
        }else if(eventType.equals("Tornado")){
            this.getEvents().add(new Tornado(level, x, y));
        }else if(eventType.equals("Infect")){
            this.getEvents().add(new Infect(level, x, y));
        }
    }//end of method

    /**
     * Updates all game objects at the end of the turn.
     * @param humans The new list of humans.
     * @param buildings The new list of buildings.
     * @param events The new list of events.
     * @param scps The new list of SCP0492s.
     */
    public void updateGameObjects(ArrayList<Human> humans, ArrayList<Building> buildings, ArrayList<Event> events, ArrayList<SCP0492> scps){
        this.setHumans((ArrayList<Human>)humans.clone());
        this.setBuildings((ArrayList<Building>)buildings.clone());
        this.setEvents((ArrayList<Event>)events.clone());
        this.setSCPs((ArrayList<SCP0492>)scps.clone());
    }


    //start of getters

    /**
     * Gets the price of an event given its type and level.
     * @param eventType The type of event.
     * @param level The level of the event.
     * @return The price of the event.
     */
    public int getPrice(String eventType, int level){
        int cost;

        if(eventType.equals("Riot")){
            cost = Riot.getCostByLevel(level);
        }else if(eventType.equals("Mutate")){
            cost = Mutate.getCostByLevel(level);
        }else if(eventType.equals("WarpReality")){
            cost = WarpReality.getCostByLevel(level);
        }else if(eventType.equals("Earthquake")){
            cost = Earthquake.getCostByLevel(level);
        }else if (eventType.equals("Fire")){
            cost = Fire.getCostByLevel(level);
        }else if (eventType.equals("Thunderstorm")){
            cost = Thunderstorm.getCostByLevel(level);
        }else if (eventType.equals("Tornado")){
            cost = Tornado.getCostByLevel(level);
        }else if (eventType.equals("Infect")){
            cost = Infect.getCostByLevel(level);
        }

        return cost;
    }//end of method

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

    /**
     * Gets the list of humans.
     * @return The list of humans.
     */
    public ArrayList<Human> getHumans(){
        return this.humanList;
    }
    //end of getters




    //start of setters

    /**
     * Sets the ArrayList of humans.
     * @param humanList The new list of humans.
     */
    public void setHumans(ArrayList<Human> humanList){
        this.humanList = humanList;
    }

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
        /** Stores buttons for aoe events */
        private DuberTextButton[] aoeEventButtons;
        /** Stores buttons for whole game events */
        private DuberTextButton[] wholeGameEventButtons;
        /** Buttons to store the levels */
        private DuberTextButton[] levels;
        /** The button to start the event */
        private DuberTextButton startEventButton;

        /**
         * Constructor that will run the SCP version of the game.
         */
        public SCPGameWindow(){
            JFrame gameWindow = this.getWindow();
            //gameWindow.setLayout(new GridLayout(0, 2));

            ScpGridPanel gridPanel = new ScpGridPanel();
            gridPanel.setBounds(0, 0, 1336, 1080);

            gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            gridPanel.setBackground(Color.gray);

            gameWindow.add(gridPanel);
            gameWindow.addMouseListener(new SCPMouseHandler());
            gameWindow.addMouseMotionListener(new SCPMouseHandler());

            //all buttons
            aoeEventButtons = new DuberTextButton[5];
            aoeEventButtons[0] = new DuberTextButton("Earthquake", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30));
            aoeEventButtons[1] = new DuberTextButton("Fire", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30));
            aoeEventButtons[2] = new DuberTextButton("Thunderstorm", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30));
            aoeEventButtons[3] = new DuberTextButton("Tornado", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30));
            aoeEventButtons[4] = new DuberTextButton("Infect", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30));

            wholeGameEventButtons = new DuberTextButton[3];
            wholeGameEventButtons[0] = new DuberTextButton("Riot", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 780, 180, 30));
            wholeGameEventButtons[1] = new DuberTextButton("Mutate", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 820, 180, 30));
            wholeGameEventButtons[2] = new DuberTextButton("WarpReality", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 860, 180, 30));

            levels = new DuberTextButton[3];
            for(int i = 0;i < levels.length;i++){
                levels[i] = new DuberTextButton("" + (i + 1), new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + i*80, 900, 60, 30));
            }

            startEventButton = new DuberTextButton("Start", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30));

            //whole game event buttons are active always
            for(int i = 0;i < wholeGameEventButtons.length;i++){
                wholeGameEventButtons[i].activate();
            }


            //let user see the window
            gameWindow.setVisible(true);


            SCP.this.displaySide("SCP");
            super.start();
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
                int emptyLotX = 30; //X-value of the empty lot to be drawn
                for(int i = 0; i < this.GRID_SIZE_BUILDING_WIDTH; i ++){
                    int emptyLotY = 30; //Y-value of the empty lot to be drawn
                    for(int j = 0; j < this.GRID_SIZE_BUILDING_LENGTH; j++){
                        g.fillRect(emptyLotX, emptyLotY, 128, 128);
                        emptyLotY += (Building.SIZE + ROAD_SIZE);
                    }
                    emptyLotX += (Building.SIZE + ROAD_SIZE);
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

                //draw infobar
                g.setColor(Color.WHITE);
                g.fillRect(GRID_SIZE_WIDTH, 0, 500, GRID_SIZE_LENGTH);
                g.setFont(new Font("Courier", Font.BOLD, 18));
                g.setColor(Color.BLACK);

                g.drawString("Time left: " + SCP.this.getTimer().getTime(), 10 + GRID_SIZE_WIDTH, 20);


                g.drawString("Username: " + SCP.this.getUsername(), 10 + GRID_SIZE_WIDTH, 125);
                g.drawString("Opponent: " + SCP.this.getOpponent(), 10 + GRID_SIZE_WIDTH, 150);

                g.drawString("Hume points: " + SCP.this.getHume(), 10 + GRID_SIZE_WIDTH, 325);

                g.drawString("Humans: " + SCP.this.getHumans().size(), 10 + GRID_SIZE_WIDTH, 425);
                g.drawString("SCP-049-2s: " + SCP.this.getSCPs().size(), 10 + GRID_SIZE_WIDTH, 450);
                

                g.setColor(Color.RED);
                g.fillRect(10 + GRID_SIZE_WIDTH, 475, 460, 25);
                g.setColor(Color.GREEN);
                if(SCP.this.getSCPs().size() > 0){
                    g.fillRect(10 + GRID_SIZE_WIDTH, 475, (SCP.this.getHumans().size()/(SCP.this.getHumans().size() + SCP.this.getSCPs().size()))*460, 25);
                }else{
                    g.fillRect(10 + GRID_SIZE_WIDTH,475,460, 25);
                }

                //buttons
                for(int i = 0;i < aoeEventButtons.length;i++){
                    aoeEventButtons[i].draw(g);
                }
                for(int i = 0;i < wholeGameEventButtons.length;i++){
                    wholeGameEventButtons[i].draw(g);
                }
                for(int i = 0;i < levels.length;i++){
                    levels[i].draw(g);
                }
                startEventButton.draw(g);
            }//end of method

        }
        
        private class SCPMouseHandler extends DuberMouseHandler{
            /**
             * When the mouse's click button is pressed.
             * @param MouseEvent The action performed by the mouse.
             */
            @Override
            public void mousePressed(MouseEvent e){
            }

            /**
             * When the mouse's click button is released.
             * @param MouseEvent The action performed by the mouse.
             */
            @Override
            public void mouseReleased(MouseEvent e){
            }

            /**
             * When the mouse enters a component.
             * @param MouseEvent The action performed by the mouse.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                if((this.getMouseX() <= 100) && (this.getMouseX() >= 0) && (this.getMouseY() <= 100) && (this.getMouseY() >= 0)){
                    //System.out.println("your button works.");
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
            @Override
            public void mouseClicked(MouseEvent e){
                int mouseX = e.getX();
                int mouseY = e.getY();
                if((mouseX <= GridPanel.GRID_SIZE_WIDTH) && (mouseY <= GridPanel.GRID_SIZE_LENGTH)){
                    eventX = mouseX;
                    eventY = mouseY;
                    for(int i = 0;i < aoeEventButtons.length;i++){
                        aoeEventButtons[i].activate();
                    }
                }else{
                    for(int i = 0;i < wholeGameEventButtons.length;i++){
                        if(wholeGameEventButtons[i].inBounds(mouseX, mouseY)){
                            type = wholeGameEventButtons[i].getText();
                            for(int j = 0;j < levels.length;j++){
                                levels[j].activate();
                            }
                        }
                    }
                    for(int i = 0;i < aoeEventButtons.length;i++){
                        if(aoeEventButtons[i].inBounds(mouseX, mouseY)){
                            type = aoeEventButtons[i].getText();
                            for(int j = 0;j < levels.length;j++){
                                levels[j].activate();
                            }
                        }
                    }
                    for(int i = 0;i < levels.length;i++){
                        if(levels[i].inBounds(mouseX, mouseY)){
                            level = levels[i].getText();
                            startEventButton.activate();
                        }
                    }
                    if(startEventButton.inBounds(mouseX, mouseY)){
                        //TODO: send a request to start the event
                    }

                    //clicking the info bar, not sure if we'll make this do anything
                }
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