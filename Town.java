//Java Swing imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//graphics imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//data structures 
import java.util.ArrayList;
import java.util.HashMap;

//jframe
import javax.swing.JFrame;
import javax.swing.BorderFactory;

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
     * Displays the intel that a spy has recovered.
     * @param hume The enemy's hume points (SCP currency).
     */
    public void displayIntel(int hume){
        new IntelWindow().displayIntel(hume);
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
        this.getTimer().resetTime(30);
        new TownReportWindow().generateReport(this.humanMap, this.getBuildings(), this.money);

    }

    /**
     * Ends the current turn in the game.
     */
    public void startTurn(){
        this.getTimer().resetTime(60);
    }

    /**
     * Ends the game when server indicates that the game is finished.
     * @param victory Determines if the player won, lost, or tied against the other player.
     */
    public void endGame(String victory){
        new ResultsWindow().getResults(victory, true);
        this.gameWindow.getWindow().dispose();
    }


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

    //start of inner classes

    /**
     * An inner class that displays intel gathered by a spy.
     */
    public class IntelWindow {
        /**The JFrame of the intel window. */
        private JFrame window;
        /**The JPanel of the intel window. */
        private JPanel mainPanel;
        /**The textbox that will display the intel. */
        private JTextArea intelBox;
    
    
        /**
         * Constructor for the end-of-game window.
         */
        public IntelWindow(){
            this.window = new JFrame("An agent has valuable intel for you!");
            this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.window.setSize(500, 500);
            this.window.setBackground(Color.WHITE);
            this.mainPanel = new JPanel();
            this.mainPanel.setBounds(10, 10, 450, 450);
            this.window.add(mainPanel);
            this.intelBox = new JTextArea();
            this.intelBox.setLineWrap(true);
            this.intelBox.setEditable(false);
            this.intelBox.setBounds(5, 0, 445, 445);
            this.window.add(intelBox);
            this.window.setVisible(true);
        }
    
        /**
         * Shows the user that one of their spies gathered.
         * @param hume The enemy's amount of hume points(SCP Currency) that the spy found.
         */
        public void displayIntel(int hume){
    
            //update the window
            this.intelBox.setText("CLASSIFIED TOP-SECRET INTEL:\n\nYour spy has discovered that your enemy has " + hume + " hume points!");
            this.intelBox.repaint();
            this.intelBox.revalidate();
            this.window.repaint();
    
        }//end of method
    
    
    
    }//end of class

    /**
     * An inner class the displays and end-of-turn report to the player.
     */
    public class TownReportWindow extends ReportWindow{
        /**
         * Generates and displays and end-of-turn report to the player
         * @param humans The list of the player's human NPCs.
         * @param buildings The list of buildings.
         * @param duberCoins The amount of duberCoins (Town currency) that the player has at the start of the turn.
         */
        public void generateReport(HashMap<Integer, Human> humans, ArrayList<Building> buildings, int duberCoins){
            //the total amount of SCP game objects
            int humanNum = humans.size();
            int buildingNum = buildings.size();


            //used to count the amount of each human NPC
            int cadetCounter = 0;
            int citizenCounter = 0;
            int doctorCounter = 0;
            int researcherCounter = 0;
            int soldierCounter = 0;
            int spyCounter = 0;




            //used to count the amount of buildings
            int bankCounter = 0;
            int foodBuildingCounter = 0;
            int hospitalCounter = 0;
            int militaryBaseCounter = 0;
            int researchLabCounter = 0;
            int residencyCounter = 0;


            //count the amount of each human NPC
            for(int i = 0; i < humans.size(); i ++){
                if(humans.get(i) instanceof Citizen){
                    citizenCounter ++;
                }else if (humans.get(i) instanceof Cadet){
                    cadetCounter++;
                }else if (humans.get(i) instanceof Doctor){
                    doctorCounter ++;
                }else if(humans.get(i) instanceof Researcher){
                    researcherCounter++;
                }else if(humans.get(i) instanceof Soldier){
                    soldierCounter++;
                }else if (humans.get(i) instanceof Spy){
                    spyCounter++;
                }//end of block if statements

            }//end of for loop


            //count the amount of each human NPC
            for(int i = 0; i < buildings.size(); i ++){
                if(buildings.get(i) instanceof Bank){
                    bankCounter ++;
                }else if (buildings.get(i) instanceof FoodBuilding){
                    foodBuildingCounter++;
                }else if (buildings.get(i) instanceof Hospital){
                    hospitalCounter ++;
                }else if(buildings.get(i) instanceof MilitaryBase){
                    militaryBaseCounter++;
                }else if(buildings.get(i) instanceof ResearchLab){
                    researchLabCounter++;
                }else if (buildings.get(i) instanceof Residency){
                    residencyCounter++;
                }//end of block if statements
            
            }//end of for loop

            String report = "DuberCoins: " + duberCoins + "\nTotal population: " + humanNum + "\nBuildings: " + buildingNum + "\n\nAll humans:";
            report += "\n\nCitizens: " + citizenCounter + "\nCadets: " + cadetCounter + "\nDoctors: " + doctorCounter + "\nResearchers: " + researcherCounter;
            report += "\nSoldiers: " + soldierCounter + "\nSpies: " + spyCounter + "\n\nAll Buildings:\n\nBanks: " + bankCounter + "\nFood Buildings: " + foodBuildingCounter;
            report += "\nHospitals: " + hospitalCounter + "\nMilitary Bases: " + militaryBaseCounter + "\nResearch Labs: " + researchLabCounter  + "Reseidencies: " + residencyCounter;

            //update the report window
            this.getReportBox().setText(report);
            this.getReportBox().repaint();
            this.getReportBox().revalidate();

            this.getWindow().repaint();


        }//end of method

    }//end of class

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
        /** Buttons relating to residency */
        private DuberTextButton[] residencyButtons;
        /** Buttons relating to military base */
        private DuberTextButton[] militaryBaseButtons;
        /** Buttons relating to hospital */
        private DuberTextButton[] hospitalButtons;
        /** Buttons relating to the research lab */
        private DuberTextButton[] researchLabButtons;
        /** Buttons related to the food building */
        private DuberTextButton[] foodBuildingButtons;
        /** Buttons related to the bank */
        private DuberTextButton[] bankButtons;

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
            //TODO: initialize the buttons for specific buildings
            
            
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
                for(int i = 0;i < residencyButtons.length;i++){
                    residencyButtons[i].draw(g);
                }
                for(int i = 0;i < militaryBaseButtons.length;i++){
                    militaryBaseButtons[i].draw(g);
                }
                for(int i = 0;i < hospitalButtons.length;i++){
                    hospitalButtons[i].draw(g);
                }
                for(int i = 0;i < researchLabButtons.length;i++){
                    researchLabButtons[i].draw(g);
                }
                for(int i = 0;i < foodBuildingButtons.length;i++){
                    foodBuildingButtons[i].draw(g);
                }
                for(int i = 0;i < bankButtons.length;i++){
                    bankButtons[i].draw(g);
                }

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
                    buildingX = (int)(mouseX/(Building.SIZE + GameWindow.GridPanel.ROAD_SIZE)) * (Building.SIZE + GameWindow.GridPanel.ROAD_SIZE) + GameWindow.GridPanel.ROAD_SIZE;
                    buildingY = (int)(mouseY/(Building.SIZE + GameWindow.GridPanel.ROAD_SIZE)) * (Building.SIZE + GameWindow.GridPanel.ROAD_SIZE) + GameWindow.GridPanel.ROAD_SIZE;
                    if((mouseX - buildingX <= Building.SIZE) && (mouseY - buildingY <= Building.SIZE)){ //make sure not clicking a road
                        Building clickedBuilding = findBuilding(buildingX, buildingY);
                        if(clickedBuilding != null){
                            System.out.println("upgrade");
                            upgradeButton.activate();
                            //TODO: helpful reminder to display the upgrade price when activating the upgrade button
                            //TODO: open up windows for each building
                            if(clickedBuilding instanceof Residency){
                                for(int i = 0;i < residencyButtons.length;i++){
                                    residencyButtons[i].activate();
                                }
                            }else if(clickedBuilding instanceof MilitaryBase){
                                for(int i = 0;i < militaryBaseButtons.length;i++){
                                    militaryBaseButtons[i].activate();
                                }
                            }else if(clickedBuilding instanceof Hospital){
                                for(int i = 0;i < hospitalButtons.length;i++){
                                    hospitalButtons[i].activate();
                                }
                            }else if(clickedBuilding instanceof ResearchLab){
                                for(int i = 0;i < researchLabButtons.length;i++){
                                    researchLabButtons[i].activate();
                                }
                            }else if(clickedBuilding instanceof FoodBuilding){
                                for(int i = 0;i < foodBuildingButtons.length;i++){
                                    foodBuildingButtons[i].activate();
                                }
                            }else if(clickedBuilding instanceof Bank){
                                for(int i = 0;i < bankButtons.length;i++){
                                    bankButtons[i].activate();
                                }
                            }
                            
                        }else{
                            //activate any button that has to do with building things
                            for(int i = 0;i < buildingTypesButtons.length;i++){
                                buildingTypesButtons[i].activate();
                            }
                        }
                    }
                }else{
                    //duber buttons on the info bar
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
                    //TODO: honestly should probably make methods for this stuff
                    for(int i = 0;i < residencyButtons.length;i++){
                        if(residencyButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
                    }
                    for(int i = 0;i < militaryBaseButtons.length;i++){
                        if(militaryBaseButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
                    }
                    for(int i = 0;i < hospitalButtons.length;i++){
                        if(hospitalButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
                    }
                    for(int i = 0;i < researchLabButtons.length;i++){
                        if(researchLabButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
                    }
                    for(int i = 0;i < foodBuildingButtons.length;i++){
                        if(foodBuildingButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
                    }
                    for(int i = 0;i < bankButtons.length;i++){
                        if(bankButtons[i].inBounds(mouseX, mouseY)){
                            //TODO: fun
                        }
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
