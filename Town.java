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
    /**research progress */
    private double researchProgress = 0;
    /**unlocked armour level */
    private int armourLevel;
    /**unlocked weaponLevel */
    private int weaponLevel;
    

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


    public TownGameWindow getTownGameWindow(){

        return this.gameWindow;

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
        this.gameWindow.generalButtons.get("upgrade").deactivate();
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
        /** Buttons relating to residency */
        private HashMap<String, DuberTextButton> residencyButtons;
        /** Buttons relating to military base */
        private HashMap<String, DuberTextButton> militaryBaseButtons;
        /** Buttons relating to hospital */
        private DuberTextButton hospitalHeal;
        /** Buttons relating to the research lab */
        private HashMap<String, DuberTextButton> researchLabButtons;
        /** General display buttons that all buildings need */
        private HashMap<String, DuberTextButton> generalButtons;
        /**food income button */
        DuberTextButton foodIncome;
        /**Money income button */
        DuberTextButton moneyIncome;
        /** Using the test button object also as text displays*/
        private DuberTextButton[] displays;
        /**a variable for storing how many humans are being trained */
        private int training = 0; 
        /**Keeping track of what type if being trained */
        private String npcType;
        /** the building that is being clicked on */
        Building clickedBuilding;
        /**Levels button for users to click on */
        private DuberTextButton[] levels;
        /**The level that the soldier user will train */
        private int soldierLevel;

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


            //information buttons 

            // all the buttons
            this.buildingTypesButtons = new DuberTextButton[6];
            this.buildingTypesButtons[0] = new DuberTextButton("MilitaryBase", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30));
            this.buildingTypesButtons[1] = new DuberTextButton("ResearchLab", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30));
            this.buildingTypesButtons[2] = new DuberTextButton("Residency", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30));
            this.buildingTypesButtons[3] = new DuberTextButton("Hospital", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30));
            this.buildingTypesButtons[4] = new DuberTextButton("FoodBuilding", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30));
            this.buildingTypesButtons[5] = new DuberTextButton("Bank", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30));

            //TODO: figure out a lot of buildings stats
            //TODO: add the the actual number e.g. healh  e.g. clickedBuilding.getHealth() in the button

            levels = new DuberTextButton[3];
            for(int i = 0;i < levels.length;i++){
                levels[i] = new DuberTextButton("" + (i + 1), new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + i*80, 900, 60, 30));
            }
            //TODO: place buttons in the right positions
            //All residency buildings
            this.residencyButtons = new HashMap<>();
            this.residencyButtons.put("Train citizens" ,new DuberTextButton("Train citizens", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30)));
            this.residencyButtons.put("Specialize citizens",new DuberTextButton("Specialize citizens", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30)));
            this.residencyButtons.put("Researchers",new DuberTextButton("Researchers", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30)));
            this.residencyButtons.put("Cadet",new DuberTextButton("Cadet", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30)));
            this.residencyButtons.put("Doctor",new DuberTextButton("Doctor", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30)));
            this.residencyButtons.put("Move",new DuberTextButton("Move", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30)));
            this.residencyButtons.put("Specialize citizens",new DuberTextButton("Specialize citizens: "  + training +  "  $"+ training * 100  , new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30)));
            //TODO: figure out how to move the npcs after some of them are trained
            this.residencyButtons.put("Add 1",new DuberTextButton("Add 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.residencyButtons.put("Citizens trained: " + training +  "  $"+ training * 100  ,new DuberTextButton("Citizens trained: " + training +  "  $"+ training * 100  , new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 850, 180, 30)));
            this.residencyButtons.put("Subtract 1",new DuberTextButton("Subtract 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30)));
            this.residencyButtons.put("Train/Specialize", new DuberTextButton("Train/Specialize", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30)));


            //militaryBase Buildings
            this.militaryBaseButtons = new HashMap<>();
            this.militaryBaseButtons.put("Add 1",new DuberTextButton("Add 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Subtract",new DuberTextButton("Subtract 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Train", new DuberTextButton("Train", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Soldier", new DuberTextButton("Train", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Spy", new DuberTextButton("Train", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Trained", new DuberTextButton("Trained: " + training + "  $" + training*100, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            //TODO: maybe display what the militaryBase currently contains

            
            //All researchLab buildings
            this.researchLabButtons = new HashMap<>();
            this.researchLabButtons.put("Upgrade weapon", new DuberTextButton("upgrade next weapon level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.researchLabButtons.put("Upgrade armour", new DuberTextButton("upgrade next armour level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.researchLabButtons.put("Progress per turn", new DuberTextButton("unlock next armour level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));


            //All individual building buttons buttons
            this.foodIncome = new DuberTextButton("Food income per turn: ", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30));
            this.moneyIncome = new DuberTextButton("Money Income per turn: ", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30));
            this.hospitalHeal = new DuberTextButton("heal all occupants in the hospital", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30));

            //initialize general buttons
            this.generalButtons = new HashMap<>();
            this.generalButtons.put("Level", new DuberTextButton("Level: " + clickedBuilding.getLevel(), new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30)));
            this.generalButtons.put("Health", new DuberTextButton("health: " +clickedBuilding.getHealth() + "/" + clickedBuilding.getMaxHealth(), new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 900, 180, 30)));
            this.generalButtons.put("upgrade", new DuberTextButton("Upgrade", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30)));
            this.buildButton = new DuberTextButton("Build", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30));
            //TODO: initialize the buttons for specific buildings
            
            //let user see the window
            gameWindow.setVisible(true);

            Town.this.displaySide("Town");
            super.start();

             }//end of method

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
                foodIncome.draw(g);
                moneyIncome.draw(g); 
                hospitalHeal.draw(g);
              
                //TODO: replace these for loops
                for(String key:residencyButtons.keySet()){
                    residencyButtons.get(key).draw(g);
                }

                for(String key:residencyButtons.keySet()){
                    militaryBaseButtons.get(key).draw(g);
                }

                for(String key:residencyButtons.keySet()){
                    researchLabButtons.get(key).draw(g);
                }

                for(String key: generalButtons.keySet()){
                    generalButtons.get(key).draw(g);
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

            /**The menu that the screen is displaying */
            private int menu = 0;
            

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
                        clickedBuilding = findBuilding(buildingX, buildingY); 
                        if(clickedBuilding != null){

                            activateGeneralBuildingButtons();
                            //TODO: remember to deactivate the buttons 
                            //TODO: helpful reminder to display the upgrade price when activating the upgrade 

                            if(clickedBuilding instanceof Residency){

                                requestResidencyFunction(menu, mouseX, mouseY);

                            }else if(clickedBuilding instanceof MilitaryBase){
                                
                                requestMilitaryBaseFunction(menu, mouseX, mouseY);

                            }else if(clickedBuilding instanceof Hospital){

                                hospitalHeal.activate();

                            }else if(clickedBuilding instanceof ResearchLab){

                                requestResearchLabFunction(menu, mouseX, mouseY);

                            }else if(clickedBuilding instanceof FoodBuilding){
                                
                                foodIncome.activate();

                            }else if(clickedBuilding instanceof Bank){
                                
                                moneyIncome.activate();
                            }
                            
                        }else{
                            //activate any button that has to do with building things
                            for(int i = 0;i < buildingTypesButtons.length;i++){
                                buildingTypesButtons[i].activate();
                            }
                        }
                    }
                }else{
                    
                    if(clickedBuilding == null){
                        //duber buttons on the info bar
                        for(int i = 0;i < buildingTypesButtons.length;i++){
                            if(buildingTypesButtons[i].inBounds(mouseX, mouseY)){
                                type = buildingTypesButtons[i].getText();
                                buildButton.activate();
                            }
                        }

                        //activate the general btttos such as health and capacti
                        for(String key: generalButtons.keySet()){
                            generalButtons.get(key).activate();
                        }

                        if(buildButton.inBounds(mouseX, mouseY)){
                            
                            requestBuilding(type, buildX, buildY);
                            
                        }else if(generalButtons.get("upgrade").inBounds(mouseX, mouseY)){
                            
                            requestUpgrade(buildX,buildY);

                        }
                    }

                    //TODO: check if the buttons are clicked, add if statements for other buildings

                    if(clickedBuilding instanceof Residency){

                        requestResidencyFunction(menu, mouseX, mouseY);
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

            //TODO: ask resource synchronization
            public void requestResidencyFunction(int menu, int mouseX,  int mouseY){

                //TODO: figure out if this is needed
                deactivateBuildingButtons(); 

                //Activate menu buttons
                if(menu == 0){

                    residencyButtons.get("Train citizens");
                    residencyButtons.get("Specialize citizens");

                    this.menu = 1; 

                }else if(menu == 1){ //after the person clicks on a residency on the grid, Check for clicks to specialize or tain
                    
                    if(residencyButtons.get("Train citizens").inBounds(mouseX, mouseY)){ //Display buttons from menu 2 (train)

                        //deactivate previous buttons
                        residencyButtons.get("Train citizens").deactivate();
                        residencyButtons.get("Specialize citizens").deactivate();

                        //activate next needed buttons
                        residencyButtons.get("Add 1").activate();
                        residencyButtons.get("Citizens trained: ").activate();
                        residencyButtons.get("Subtract 1").activate();
                        residencyButtons.get("Train/Specialize").activate();
                        

                        this.menu = 2;

                    }else if(residencyButtons.get("Specialize citizens").inBounds(mouseX, mouseY)){ //Display buttons from menu 3 (specialize)

                        //deactivate previous buttons
                        residencyButtons.get("Train citizens").deactivate();
                        residencyButtons.get("Specialize citizens").deactivate();

                        residencyButtons.get("Doctor").activate();
                        residencyButtons.get("Researchers").activate();
                        residencyButtons.get("Cadet").activate();

                        this.menu = 3;

                    }else{}

                }else if(menu == 2){// train citizens 

                    if(residencyButtons.get("Add 1").inBounds(mouseX, mouseY)){ //add a trainee

                        if(training + ((Residency)clickedBuilding).getCurrentPopulation() < ((Residency)clickedBuilding).getMaxCap()){ //check if condition works
                            training ++;
                        }

                    }else if(residencyButtons.get("Subtract 1").inBounds(mouseX, mouseY) && training > 0){//subtract a training

                        training --;
                    }else if(residencyButtons.get("Train/Specialize").inBounds(mouseX, mouseY)){ //user presses train

                        sendMessage("<residency train>" + " " + clickedBuilding.getX() + " " + clickedBuilding.getY() + " " + training); 
                        //Update this class as well
                        System.out.println("Your buttons have made it this far congrats");

                        //return to default screens
                        menu = 0;
                        training = 0;
                        //TODO: reset the screens

                        residencyButtons.get("Add 1").deactivate();
                        residencyButtons.get("Train/Specialize").deactivate();
                        residencyButtons.get("Subtract 1").deactivate();
                        deactivateGeneralBuildingButtons();
                    }

                }else if(menu == 3){

                    if(residencyButtons.get("Doctor").inBounds(mouseX, mouseY)){
                        npcType = "Doctor";
                    }else if(residencyButtons.get("Researchers").inBounds(mouseX,mouseY)){
                        npcType = "Researcher";
                    }else if(residencyButtons.get("Cadet").inBounds(mouseX,mouseY)){
                        npcType = "Cadet";
                    }

                    //deactivate unneeded buttons needed buttons
                    residencyButtons.get("Add 1").activate();
                    residencyButtons.get("Specialize citizens: ").activate();
                    residencyButtons.get("Subtract 1").activate();

                    menu = 4;

                }else if(menu == 4){

                    if(residencyButtons.get("Add 1").inBounds(mouseX, mouseY)){ //add a trainee 

                        if(training < ((Residency)clickedBuilding).getAdults()){
                            training ++;
                        }

                    }else if(residencyButtons.get("Subtract 1").inBounds(mouseX, mouseY) && training > 0){//subtract a training

                        training --;
                    }else if(residencyButtons.get("Train/Specialize").inBounds(mouseX, mouseY)){ //user presses train

                        sendMessage("<residency convert> " + npcType + " " + training); //TODO: make the requesting different requesting than menu 2
                        //TODO update current class's objects when converting? 

                        System.out.println("Your buttons have made it this far congrats");

                        //return to default screens
                        menu = 0;
                        training = 0;

                        //deactivate current buttons 
                        residencyButtons.get("Add 1").deactivate();
                        residencyButtons.get("Specialize citizens: ").deactivate();
                        residencyButtons.get("Subtract 1").deactivate();
                        deactivateGeneralBuildingButtons();
                        //TODO: reset the screens
                    }
                    
                }
                //TODO: finish the other mnenus for this building
            }
            
            public void requestMilitaryBaseFunction(int menu, int mouseX,  int mouseY){
                
                if(menu == 0){

                    deactivateBuildingButtons();

                    militaryBaseButtons.get("Spy").activate();
                    militaryBaseButtons.get("Soldier").activate();

                    this.menu = 1;

                }else if(menu == 1 ){


                    if(militaryBaseButtons.get("Spy").inBounds(mouseX, mouseY)){
                    
                    
                        //deactivate previous menu buildings
                        militaryBaseButtons.get("Spy").deactivate();
                        militaryBaseButtons.get("Soldier").deactivate();

                        //activate buttons needed
                        militaryBaseButtons.get("Add 1").activate();
                        militaryBaseButtons.get("Subtract 1").activate();
                        militaryBaseButtons.get("Trained").activate();
                        militaryBaseButtons.get("Train").activate();

                        this.menu = 2;
                        npcType = "Spy";
                    
                    
                    }else if(militaryBaseButtons.get("Soldier").inBounds(mouseX,mouseY)){
                        //deactivate previous menu buildings
                        militaryBaseButtons.get("Spy").deactivate();
                        militaryBaseButtons.get("Soldier").deactivate();

                        militaryBaseButtons.get("Add 1").activate();
                        militaryBaseButtons.get("Subtract 1").activate();
                        militaryBaseButtons.get("Trained").activate();
                        militaryBaseButtons.get("Train").activate();
                        for(int i = 0; i < 3; i ++){
                            levels[i].activate();
                        }

                        this.menu = 3;
                        npcType = "Soldier";
                    }
                }else if(menu == 2){

                    if(militaryBaseButtons.get("Add 1").inBounds(mouseX, mouseY)){

                        training ++;
                    }else if(militaryBaseButtons.get("Subtract 1").inBounds(mouseX, mouseY)){

                        training --;
                    }else if(levels[0].inBounds(mouseX, mouseY)){
                        soldierLevel = 1;
                    }else if(levels[1].inBounds(mouseX, mouseY)){
                        soldierLevel = 2;
                    }else if(levels[2].inBounds(mouseX, mouseY)){
                        soldierLevel = 3;
                    }else if ( militaryBaseButtons.get("Train").inBounds(mouseX, mouseY)){

                        //send requests to server
                        sendMessage("<military spy> " + training + " "+ clickedBuilding.getX() + " " + clickedBuilding.getY());
                        menu = 0;

                        //deactivate buttons
                        militaryBaseButtons.get("Add 1").deactivate();
                        militaryBaseButtons.get("Subtract 1").deactivate();
                        militaryBaseButtons.get("Trained").deactivate();
                        militaryBaseButtons.get("Train").deactivate();
                    }
                }else if (menu == 3){

                    if(militaryBaseButtons.get("Add 1").inBounds(mouseX, mouseY)){

                        training ++;
                    }else if(militaryBaseButtons.get("Subtract 1").inBounds(mouseX, mouseY)){

                        training --;
                    }else if(militaryBaseButtons.get("Train").inBounds(mouseX, mouseY)){

                        //send requests to server
                        sendMessage("<military soldier> " + soldierLevel + " " + training + " " + clickedBuilding.getX() + " " + clickedBuilding.getY());

                        //reset values to defaules
                        menu = 0;
                        soldierLevel = 1;

                        //deactivate buttons 
                        militaryBaseButtons.get("Add 1").deactivate();
                        militaryBaseButtons.get("Subtract 1").deactivate();
                        militaryBaseButtons.get("Trained").deactivate();
                        militaryBaseButtons.get("Train").deactivate();
                        for(int i = 0; i < 3; i ++){
                            levels[i].deactivate();
                        }
                    }
                }
            }
            
            public void requestHospitalFunction(int menu, int mouseX,  int mouseY ){
                
                if(menu == 0){

                    deactivateBuildingButtons();
                    hospitalHeal.activate();

                }else if(menu == 1){

                    if(hospitalHeal.inBounds(mouseX, mouseY)){

                        //request healing of all occupants inside
                        sendMessage("<hh> " + clickedBuilding.getX() + " " + clickedBuilding.getY());
                        menu = 0; 

                    }

                }

            }


            public void requestResearchLabFunction(int menu, int mouseX,  int mouseY){
                
                if(menu == 0){

                    researchLabButtons.get("Upgrade weapon").activate();
                    researchLabButtons.get("Upgrade armour").activate();

                    menu = 1;

                }else if(menu == 1){

                    if(researchLabButtons.get("Upgrade weapon").inBounds(mouseX, mouseY)){

                        sendMessage("<research weapon>");
                        menu = 0;

                    }else if(researchLabButtons.get("Upgrade armour").inBounds(mouseX, mouseY)){

                        sendMessage("<research armour>");
                        menu = 0;
                    }
                }
            }

            public void requestFoodBuildingFunctions(int menu, int mouseX,  int mouseY){
                
                deactivateBuildingButtons();
                foodIncome.activate();
            }

            public void requestBankButtonsFunctions(int menu, int mouseX,  int mouseY){
                
                deactivateBuildingButtons();
                moneyIncome.activate();
            }

            public void deactivateBuildingButtons(){
                
                if( !(clickedBuilding instanceof Residency)){ //if current building is not residencyy, clear all residency buttons
                    for(String key: residencyButtons.keySet()){
                        residencyButtons.get(key).deactivate();
                    }
                }
                if( !(clickedBuilding instanceof Hospital)){
                    hospitalHeal.deactivate();
                }
                if( !(clickedBuilding instanceof MilitaryBase)){
                    for(String key: militaryBaseButtons.keySet()){
                        militaryBaseButtons.get(key).deactivate();
                    }
                }
                if( !(clickedBuilding instanceof ResearchLab)){
                    for(String key: researchLabButtons.keySet()){
                        researchLabButtons.get(key).deactivate();
                    }
                }
                if( !(clickedBuilding instanceof FoodBuilding)){
                    foodIncome.deactivate();
                }
                if( !(clickedBuilding instanceof Bank)){
                    moneyIncome.deactivate();
                }

            }

            public void activateGeneralBuildingButtons(){


            }

            public void deactivateGeneralBuildingButtons(){


            }

        }

    }//end of inner class

    //TODO implement a back button by subtracting menu by one or switching the currentBuildingType variable

}//end of class
