import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.Color;
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
 * [Town.java]
 * A class that creates the basic class that the "town" player uses
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 11, 2020
 */


public class Town extends Player {
    /**The game window that the player will use to play the game. */
    private TownGameWindow gameWindow;
    /**An integer for storing the money the player has */
    private int money;
    /**an integer for storing the food that the player has */
    private int food;
    /**A hashmap of all humans in the game so they can be referenced with a key. */
    private HashMap<Integer, Human> humanMap;
    /** The x and y location of the last "building area" clicked */
    private int buildX, buildY;
    /** The string for the type of building */
    private String type;
    
    /** obj for reference of the classes during comparison */
    Residency residency = new Residency(0,0,0,0,0,0); //TODO: find a better way to compare classes of object
    MilitaryBase militaryBase = new MilitaryBase(0,0,0,0,0);
    Hospital hospital = new Hospital(0,0,0,0,0,0);
    ResearchLab researchLab = new ResearchLab(0,0,0,0,0);
    Bank bank = new Bank(0,0,0,0,0);
    FoodBuilding foodBuilding = new FoodBuilding(0,0,0,0,0);

    

    /**
     * Constructor for the town side player's class
     * @param username The username of the player
     * @param playerClient The client that will connect to the server
     * @param opponent The username of the opponent that the player will play against.
     * @param money the amount of money the user will begin with
     * @param food the amount of food the user wil begin with
     */
    public Town(String username, Client playerClient, String opponent, int money, int food){
        super(username, playerClient, opponent);
        this.money = money;
        this.food = food; 
        humanMap = new HashMap<Integer, Human>();
    }


    /**
     * Starts the town version of the game.
     */
    public void start(){
        this.gameWindow = new TownGameWindow();
    }

    /**
     * Changes the amount of money that the player has.
     * @param change The amount of money that the player will gain or lose.
     */
    public void changeMoney(int change){
        this.money += change;
    }

    /**
     * Changes the amount of food that the player has.
     * @param change The amount of food that the player will gain or lose;
     */
    public void changeFood(int change){
        this.food += change;
    }

    /**
     * Updates all game objects at the end of the turn.
     * @param humans The new HashMap of humans.
     * @param buildings The new list of buildings.
     * @param events The new list of events.
     * @param scps The new list of SCP0492s.
     */
    public void updateGameObjects(HashMap<Integer, Human> humans, ArrayList<Building> buildings, ArrayList<Event> events, ArrayList<SCP0492> scps){
        this.setHumanMap((HashMap<Integer, Human>)humans.clone());
        this.setBuildings((ArrayList<Building>)buildings.clone());
        this.setEvents((ArrayList<Event>)events.clone());
        this.setSCPs((ArrayList<SCP0492>)scps.clone());
    }


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
        //TODO: do turn stuff
    }

    //TODO: method for client-game transactions


    public ArrayList<JFrame> getGameGraphics(){

        return new ArrayList<JFrame>(); //for now to be able to test run the program.
        //TODO: ask vivian and damon how exactly the graphics are going to work

    }

    
    /** 
     * buildingBuilding
     * @param buildingType The type of building to be built
     * @param x the x coordinate of the buildings
     * @param y the y coordinate of the buildings
     */
    public void buildBuilding(String buildingType, int x, int y){

        ArrayList<Building> buildings = this.getBuildings();

        //add each created building to the ArrayList containing the buildings
        if(buildingType.equals("MilitaryBase")){

            buildings.add(new MilitaryBase(1000, 100, 100, x, y));

        }else if(buildingType.equals("ResearchLab")){

            buildings.add(new ResearchLab(1000, 100, 100, x, y));
        
        }else if(buildingType.equals("Residency")){
        
            buildings.add(new Residency(1000, 100, 100,x, y, 100));

        }else if(buildingType.equals("Hospital")){

            buildings.add(new Hospital(1000, 100, 100,  x, y, 100));
        
        }else if(buildingType.equals("FoodBuilding")){

            buildings.add(new FoodBuilding(1000, 100, 100,  x, y));
        
        }else if(buildingType.equals("Bank")){

            buildings.add(new Bank(1000, 100, 100, x, y));
            
        }else{  

            System.out.println("hey that's not the right building to build");
        }

    }

    
    /** 
     * Upgrades the building at locations x, y
     * @param x the x coordinates of the building to upgrade
     * @param y the y coordinates of the building to upgrade
     */
    public void upgradeBuilding(int x , int y){
        ArrayList<Building> buildings = this.getBuildings();
        //TODO: figure out if I want to optimize efficiency
        for(int i = 0; i < buildings.size(); i ++){

            if(buildings.get(i).getX() == x && buildings.get(i).getY() == y){

                buildings.get(i).upgrade();
            }
        }

    }

    //start of getters

    /**
     * Gets the price of an NPC.
     * @param type The type of NPC.
     * @return The price of the NPC.
     */
    public int getPrice(String type){
        if(type.equals("Cadet")){
            return Cadet.CADET_PRICE;
        }else if(type.equals("Citizen")){
            return Citizen.CITIZEN_PRICE;
        }else if(type.equals("Doctor")){
            return Doctor.DOCTOR_PRICE;
        }else if(type.equals("Researcher")){
            return Researcher.RESEARCHER_PRICE;
        }else if(type.equals("Soldier")){
            //TODO: May need to do more stuff to calculate armor and weapon prices.
            return Soldier.BASE_SOLDIER_PRICE;
        }else if(type.equals("Spy")){
            return Spy.SPY_PRICE;
        }else{ //nothing proper was given so just send -1
            return -1;
        }
    }

    /**
     * Gets the price of a building.
     * @param type The type of the building
     * @param level The level of the building
     * @return The price of the building
     */
    public int getPrice(String type, int level){
        //TODO: Building Prices/calculations here.
        return 0;
    }


    /** 
     * Gets the amount of money the player has.
     * @return The player's money.
     */
    public int getMoney(){
        return this.money;

    }

    
    /** 
     * Gets the amount of food that the player has.
     * @return The player's food.
     */
    public int getFood(){
        return this.food;
    }

    /**
     * Gets the hash map of humans.
     * @return The hash map of humans on the game.
     */
    public HashMap<Integer, Human> getHumanMap(){
        return this.humanMap;
    }
    //end of getters

  

    //start of setters

    /**
     * Sets the HashMap of humans.
     * @param humans The new HashMap of humans.
     */
    public void setHumanMap(HashMap<Integer, Human> humans){
        this.humanMap = humans;
    }

    /**
     * Sets the amount of money that the player has.
     * @param money The new amount of money.
     */
    public void setMoney(int money){
        this.money = money;
    }

    /**
     * Sets the amount of food that the player has.
     * @param food The new amount of food.
     */
    public void setFood(int food){
        this.food = food;
    }

    /**
     * Request to build a building at coordinates x y
     * @param type The type of building to be built
     * @param x the x coordinate to build the building
     * @param y the y coordiante to build the building
     */
    public void requestBuilding(String type, int x, int y){
        //send message
        super.sendMessage("<b>");
        super.sendMessage(type);
        super.sendMessage(x + " " + y);
        
        //set last request
        this.getPlayerClient().setLastRequest("<b> " + type + " " + x + " " + y);

        //deactivate the buttons
        this.gameWindow.buildButton.deactivate();
        for(int i = 0;i < this.gameWindow.buildingTypesButtons.length;i++){
            this.gameWindow.buildingTypesButtons[i].deactivate();
        }
    }

    /**
     * Request to upgrade a building at corodinate x,y
     * @param x the x coordinate of the requested upgrade
     * @param y the y coordiante of the requested upgrade 
     */
    public void requestUpgrade(int x, int y){
        //send message
        super.sendMessage("<u>");
        sendMessage(x + " " + y);

        //set last request
        this.getPlayerClient().setLastRequest("<u>> " + x + " " + y);

        //deactivate the buttons
        this.gameWindow.upgradeButton.deactivate();
    }

    //end of setters

    /**
     * Looks for if a building with the coordinates of (x, y) exist
     * @param x the x coordinate of the building
     * @param y the y coordinate of the building
     * @return null if no such building exists, otherwise, the building in the (x, y) position
     */
    private Building findBuilding(int x, int y){
        Building buildingToReturn = null;
        for(int i = 0;i < this.getBuildings().size();i++){
            if((this.getBuildings().get(i).getX() == x) && (this.getBuildings().get(i).getY() == y)){
                buildingToReturn = this.getBuildings().get(i);
            }
        }
        return buildingToReturn;
    }

    /**
     * An inner class that will run the main game window that the user will use to play the game.
     */
    public class TownGameWindow extends GameWindow{
        /** Stores the types of buildings that can be built */
        private DuberTextButton[] buildingTypesButtons;
        /** Button to build */
        private DuberTextButton buildButton;
        /** Button to upgrade */
        private DuberTextButton upgradeButton;

        /**
         * Method runs the town version of the game.
         */
        public TownGameWindow(){
            //get the JPanels and JFrame of the game window from parent class
            JFrame gameWindow = this.getWindow();

            TownGridPanel gridPanel = new TownGridPanel();
            gridPanel.setBounds(0, 0, 1080, 1336);
            gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            gridPanel.setBackground(Color.gray);

            gameWindow.add(gridPanel);
            gameWindow.addMouseListener(new TownMouseHandler());
            gameWindow.addMouseMotionListener(new TownMouseHandler());

            // all the buttons
            this.buildingTypesButtons = new DuberTextButton[6];
            this.buildingTypesButtons[0] = new DuberTextButton("MilitaryBase", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30));
            this.buildingTypesButtons[1] = new DuberTextButton("ResearchLab", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30));
            this.buildingTypesButtons[2] = new DuberTextButton("Residency", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30));
            this.buildingTypesButtons[3] = new DuberTextButton("Hospital", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30));
            this.buildingTypesButtons[4] = new DuberTextButton("FoodBuilding", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30));
            this.buildingTypesButtons[5] = new DuberTextButton("Bank", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30));

            this.upgradeButton = new DuberTextButton("Upgrade", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30));
            this.buildButton = new DuberTextButton("Build", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30));
            
            
            //let user see the window
            gameWindow.setVisible(true);

            Town.this.displaySide("Town");
            super.start();

             }//end of method


            /**
             * Updates the game screen
             */
            //TODO: Ask what this method is for, not actually sure what it's supposed to do
             public void update(){
                 System.out.println("Method not complete yet");
                 //TODO: Incomplete method
             }
        /**
         * [TownGridPanel.java]
         * A {@code GridPanel} that draw SCP specific game graphics
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 15, 2021
         */
        private class TownGridPanel extends GridPanel{
            /**
             * Constructor for the {@code GridPanel}
             */
            private TownGridPanel(){
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
                for(int i = 0; i < GRID_SIZE_BUILDING_WIDTH; i ++){
                    int emptyLotY = 30; //Y-value of the empty lot to be drawn
                    for(int j = 0; j < GRID_SIZE_BUILDING_LENGTH; j++){
                        g.fillRect(emptyLotX, emptyLotY, Building.SIZE, Building.SIZE);
                        emptyLotY += (Building.SIZE + ROAD_SIZE);
                    }
                    emptyLotX += (Building.SIZE + ROAD_SIZE);
                }


                //draw humans
                for(int key: Town.this.getHumanMap().keySet()){
                    Town.this.getHumanMap().get(key).draw(g);
                }


                //draw SCP0492s
                for(int i = 0; i < Town.this.getSCPs().size(); i++){
                    Town.this.getSCPs().get(i).draw(g);
                }

                //draw buildings
                for(int i = 0; i < Town.this.getBuildings().size(); i++){
                    Town.this.getBuildings().get(i).draw(g);
                }

                //draw events
                for(int i = 0; i < Town.this.getEvents().size(); i++){
                    if(Town.this.getEvents().get(i) instanceof PhysicalEvent){
                        ((PhysicalEvent)(Town.this.getEvents().get(i))).draw(g);
                    }
                }

                //draw infobar
                g.setColor(Color.WHITE);
                g.fillRect(GRID_SIZE_WIDTH, 0, 500, GRID_SIZE_LENGTH);

                g.setFont(new Font("Courier", Font.BOLD, 18));
                g.setColor(Color.BLACK);

                g.drawString("Time left: " + Town.this.getTimer().getTime(), 10 + GRID_SIZE_WIDTH, 20);
                System.out.println(Town.this.getTimer().getTime());
                g.drawString("Username: " + Town.this.getUsername(), 10 + GRID_SIZE_WIDTH, 125);
                g.drawString("Opponent: " + Town.this.getOpponent(), 10 + GRID_SIZE_WIDTH, 150);

                g.drawString("DuberCoin: " + Town.this.getMoney(), 10 + GRID_SIZE_WIDTH, 325);

                g.drawString("Humans: " + Town.this.getHumanMap().size(), 10 + GRID_SIZE_WIDTH, 425);
                g.drawString("SCP-049-2s: " + Town.this.getSCPs().size(), 10 + GRID_SIZE_WIDTH, 450);
                

                g.setColor(Color.RED);
                g.fillRect( 10 + GRID_SIZE_WIDTH, 475, 460, 25);
                g.setColor(Color.GREEN);
                if(Town.this.getSCPs().size() > 0){
    
                    g.fillRect(10 + GRID_SIZE_WIDTH, 475, (Town.this.getHumanMap().size()/(Town.this.getHumanMap().size() + Town.this.getSCPs().size()))*460, 25);
                }else{
                    g.fillRect(10 + GRID_SIZE_WIDTH, 475, 460, 25);
                }

                //buttons
                for(int i = 0;i < buildingTypesButtons.length;i++){
                    buildingTypesButtons[i].draw(g);
                }
                buildButton.draw(g);
                upgradeButton.draw(g);

            }
        }

        /**
         * [TownMouseHandler.java]
         * Inner class for mouse input.
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 16, 2021
         */
        public class TownMouseHandler extends DuberMouseHandler{
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
                int mouseX = e.getX();
                int mouseY = e.getY();
                int buildingX;
                int buildingY;
                if((mouseX <= GridPanel.GRID_SIZE_WIDTH) && (mouseY <= GridPanel.GRID_SIZE_LENGTH)){
                    //inside the grid area, clamps the values down to the x and y of the top left corner of where the building would be
                    buildingX = (int)(mouseX/(Building.SIZE + GameWindow.GridPanel.ROAD_SIZE)) * (Building.SIZE + GameWindow.GridPanel.ROAD_SIZE);
                    buildingY = (int)(mouseY/(Building.SIZE + GameWindow.GridPanel.ROAD_SIZE)) * (Building.SIZE + GameWindow.GridPanel.ROAD_SIZE);
                    if((mouseX - buildingX <= Building.SIZE) && (mouseY - buildingY <= Building.SIZE)){ //make sure not clicking a road
                        Building clickedBuilding = findBuilding(buildingX, buildingY);
                        if(clickedBuilding != null){
                            System.out.println("upgrade");
                            upgradeButton.activate();
                            //TODO: helpful reminder to display the upgrade price when activating the upgrade button
                            //TODO: open up windows for each building
                            if(clickedBuilding.getClass().equals(residency.getClass())){

                            }else if(clickedBuilding.getClass().equals(militaryBase.getClass())){
                            
                            }else if(clickedBuilding.getClass().equals(hospital.getClass())){
                            
                            }else if(clickedBuilding.getClass().equals(researchLab.getClass())){
                            
                            }else if(clickedBuilding.getClass().equals(foodBuilding.getClass())){
                            
                            }else if(clickedBuilding.getClass().equals(bank.getClass())){
                            
                            }
                            
                        }else{
                            //activate any button that has to do with building things
                            for(int i = 0;i < buildingTypesButtons.length;i++){
                                buildingTypesButtons[i].activate();
                            }
                        }
                    }
                }else{
                    for(int i = 0;i < buildingTypesButtons.length;i++){
                        if(buildingTypesButtons[i].inBounds(mouseX, mouseY)){
                            type = buildingTypesButtons[i].getText();
                            buildButton.activate();
                        }
                    }
                    if(buildButton.inBounds(mouseX, mouseY)){
                        
                        requestBuilding(type, buildX, buildY);
                        

                    }else if(upgradeButton.inBounds(mouseX, mouseY)){
                        
                        requestUpgrade(buildX,buildY);
                        

                    }
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
