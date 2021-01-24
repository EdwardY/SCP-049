//graphics imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//jframe swing stuff
import javax.swing.JFrame;
import javax.swing.BorderFactory;

//datastructures
import java.util.ArrayList;


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
    private SCPGameWindow scpGameWindow;
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
        //this.scpGameWindow = new SCPGameWindow();
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

    public void start(){
        this.scpGameWindow = new SCPGameWindow();
        Thread gameRunner = new Thread(new GameWindowRunner(this.scpGameWindow));
        gameRunner.start();
        System.out.println("scp window");
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
        System.out.println("functional");
        //save last request
        this.getPlayerClient().setLastRequest("<e> " + type + " " + level + " " + x + " " + y);
        System.out.println("saved");
        scpGameWindow.reset();
        System.out.println("reset");
    }

    /**
     * Requests a whole game event to the server
     * @param type the type of event
     * @param level the level of the event
     */
    private void requestEvent(String type, String level){
        //request
        sendMessage("<e>");
        sendMessage(type);
        sendMessage(level);
        System.out.println("functional");
        //save request
        this.getPlayerClient().setLastRequest("<e> " + type + " " + level);
        System.out.println("saved");
        scpGameWindow.reset();
        System.out.println("reset");
    }




    /**
     * Changes the amount of the player's hume points
     * @param change The amount of hume points that the player will gain or lose.
     */
    public void changeHume(int change){
        this.hume += change;
    }
    

    /**
     * Starts the current turn in the game.
     */
    public void endTurn(){
        this.getTimer().resetTime(30);
        new SCPReportWindow().generateReport(this.getSCPs(), this.getEvents(), this.hume);
    }

    /**
     * Ends the current turn in the game.
     */
    public void startTurn(){
        this.getTimer().resetTime(60);
    }

    /**
     * Ends the game
     * @param victory Determines if the player won, lost, or tied against the other player.
     */
    public void endGame(String victory){
        new ResultsWindow().getResults(victory, false);
        this.scpGameWindow.getWindow().dispose();
    }

    public void requestSurrender(){
        sendMessage("<sur>");

        this.getPlayerClient().setLastRequest("<sur>");
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
        this.setHumans(humans);
        this.setBuildings(buildings);
        this.setEvents(events);
        this.setSCPs(scps);
    }


    //start of getters

    /**
     * Gets the price of an event given its type and level.
     * @param eventType The type of event.
     * @param level The level of the event.
     * @return The price of the event.
     */
    public int getPrice(String eventType, int level){
        int cost = 0;

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
        return this.scpGameWindow;
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

    //start of inner classes
    /**
     * An inner class that makes an end-of-turn report for the player
     */
    public class SCPReportWindow extends ReportWindow{

        /**
         * Generates and displays and end-of-turn report to the player
         * @param scps The list of the player's SCP0492 NPCs.
         * @param events The list of SCP events.
         * @param hume The amount of hume points (SCP currency) that the player has at the start of the turn.
         */
        public void generateReport(ArrayList<SCP0492> scps, ArrayList<Event> events, int hume){
            //the total amount of SCP game objects
            int scpNum = scps.size();
            int eventNum = events.size();


            //used to count the amount of each event
            int infectCounter = 0;
            int earthquakeCounter = 0;
            int fireCounter = 0;
            int mutateCounter = 0;
            int riotCounter = 0;
            int thunderstormCounter = 0;
            int tornadoCounter = 0;
            int warpRealityCounter = 0;


            //count the amount of each event
            for(int i = 0; i < events.size(); i ++){
                if(events.get(i) instanceof Infect){
                    infectCounter ++;
                }else if (events.get(i) instanceof Earthquake){
                    earthquakeCounter++;
                }else if (events.get(i) instanceof Fire){
                    fireCounter ++;
                }else if(events.get(i) instanceof Mutate){
                    mutateCounter++;
                }else if(events.get(i) instanceof Riot){
                    riotCounter++;
                }else if (events.get(i) instanceof Thunderstorm){
                    thunderstormCounter++;
                }else if(events.get(i) instanceof Tornado){
                    tornadoCounter++;
                }else if(events.get(i) instanceof WarpReality){
                    warpRealityCounter++;
                }//end of block if statements

            }//end of for loop

            String report = "Hume points: " + hume + "\nTotal SCP-049-2: " + scpNum + "\nTotal events: " + eventNum + "\n\nAll events:";
            report += "\n\nEarthquakes: " + earthquakeCounter + "\nFires: " + fireCounter + "\nInfections: " + infectCounter + "\nMutations: " + mutateCounter + "\nRiots: " + riotCounter;
            report += "\nThunderstorms: " + thunderstormCounter + "\nTornadoes: " + tornadoCounter + "\nWarped Realities: " + warpRealityCounter; 

            //update the report window
            this.getReportBox().setText(report);
            this.getReportBox().repaint();
            this.getReportBox().revalidate();

            this.getWindow().repaint();


        }//end of method

    }//end of class


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
        /** boolean for whether or not the price for the event should be displayed */
        private boolean displayPrice;
        /**button to request a surrender */
        private DuberTextButton surrender;

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

            gridPanel.addMouseListener(new SCPMouseHandler());
            gridPanel.addMouseMotionListener(new SCPMouseHandler());
            
            gameWindow.add(gridPanel);

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

            surrender = new DuberTextButton("surrender", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 960, 180, 30));
            surrender.activate();

            //whole game event buttons are active always
            for(int i = 0;i < wholeGameEventButtons.length;i++){
                wholeGameEventButtons[i].activate();
            }


            //let user see the window
            gameWindow.setVisible(true);


            SCP.this.displaySide("SCP");
            //super.start();
            //TODO: Might not actually need this line of code

        }// end of window

        /**
         * Deactivates buttons and resets everything to how it was before
         */
        public void reset(){
            //deactivate buttons
            for(int i = 0;i < this.aoeEventButtons.length;i++){
                aoeEventButtons[i].deactivate();
            }
            for(int i = 0;i < levels.length;i++){
                levels[i].deactivate();
            }
            startEventButton.deactivate();
    
            //reset x and y
            eventX = -1;
            eventY = -1;
    
            //reset displaying price
            displayPrice = false;
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
                surrender.draw(g);

                if(displayPrice){
                    int price = getPrice(type, Integer.parseInt(level));
                    if(price <= hume){
                        g.setColor(Color.GREEN);
                    }else{
                        g.setColor(Color.RED);
                    }
                    g.drawString("Level " + level + " " + type + " costs " + price + " hume", GRID_SIZE_LENGTH + 360, 960);
                }
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
                System.out.println("Mouse pos: " + mouseX + ", " + mouseY);
                if((mouseX <= GridPanel.GRID_SIZE_WIDTH) && (mouseY <= GridPanel.GRID_SIZE_LENGTH)){
                    eventX = mouseX;
                    eventY = mouseY;
                    for(int i = 0;i < aoeEventButtons.length;i++){
                        aoeEventButtons[i].activate();
                    }
                }else{
                    if(surrender.inBounds(mouseX, mouseY)){

                        requestSurrender();
                    }
                    
                    
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
                            displayPrice = true;
                        }
                    }
                    if(startEventButton.inBounds(mouseX, mouseY)){
                        if((eventX == -1) && (eventY == -1)){
                            requestEvent(type, level);
                        }else{
                            requestEvent(type, level, eventX, eventY);
                        }
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