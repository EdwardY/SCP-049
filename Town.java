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
    private int armourLevel = 1;
    /**unlocked weaponLevel */
    private int weaponLevel = 1;
    /**the id of humans to put in a humanMap */
    private int currentId = 0; //TODO: coordinate this with the server might be
    //TODO: create more buttons (pain) and display some useful information

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
     * Checks what kind of NPC ({@code SCP0492} or {@code Human}) is being added then adds to the appropriate list
     * @param npc the {@code NPC} to be added
     */
    public void addNpc(NPC npc){
        humanMap.put(currentId, (Human)npc);
        currentId++;
    
    }
    
    public void run(){
        System.out.println("I'm right :D");
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

    /**upgrade weapon leavel */
    public void researchWeapon(){
        this.weaponLevel ++;
        changeMoney(-400);
    }

    /**upgrades armour level */
    public void researchArmour(){
        this.armourLevel ++;
        changeMoney(-400);
    }

    /**
     * 
     * @return The armour level unlocked 
     */
    public int getArmourLevel(){

        return this.armourLevel;
    }   

    /**
     * 
     * @return The weapon level unlocked
     */
    public int getWeaponLevel(){

        return this.weaponLevel;
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
     * Converts an NPC to another type.
     * @param key The key of the NPC being converted.
     * @param type The type of NPC that the NPC will be converted to.
     * @param health The health of the NPC.
     * @param maxHealth The maximum health of the NPC.
     * @param attackDamage The damage of the NPC (if applicable).
     * @param priority The priority of the NPC.
     * @param successRate The success rate of the NPC getting enemy intel (if applicable).
     * @param sus The chance of the NPC getting caught by the enemy (if applicable).
     * @param healingAmount The amount of healing the NPC will be able to heal (if applicable).
     */
    public void convert(int key, String type, int maxHealth, int attackDamage, int priority, double successRate, double sus, int healingAmount){
        Human currentHuman = this.humanMap.get(key);
        int currentXPosition = currentHuman.getX();
        int currentYPosition = currentHuman.getY();
        NPC newNpc = null;
        if(type.equals("Citizen")){
            newNpc = new Citizen(currentXPosition,currentYPosition);
        }else if(type.equals("Cadet")){
            newNpc = new Cadet(currentXPosition, currentYPosition);
        }else if(type.equals("Doctor")){
            newNpc = new Doctor(currentXPosition, currentYPosition, healingAmount);
        }else if(type.equals("Researcher")){
            newNpc = new Researcher(currentXPosition, currentYPosition);
        }else if(type.equals("Soldier")){
            newNpc = new Soldier(maxHealth, currentXPosition, currentYPosition, attackDamage);
        }else if(type.equals("Spy")){
            newNpc = new Spy(currentXPosition, currentYPosition, successRate, sus);
        }else if (type.equals("SCP0492")){
            newNpc = new SCP0492(maxHealth, currentXPosition, currentYPosition, attackDamage);
        }
        
        this.humanMap.replace(key, (Human)newNpc);
        
    }

    /**
     * Relocates the {@code Human} to the proper {@code Building} based on the occupation they now have. 
     * Duberville offers good teleportation services so all {@code Humans} can live in their workplace 
     * as soon as they get hired.
     * @param human the {@code Human} to relocate
     */
    public void locateHumanInProperSpot(Human human){

        ArrayList<Building> buildings = this.getBuildings();
        boolean added = false;
        if(human instanceof Doctor){
            //move to hospital
            for(int i  = 0;i < buildings.size();i++){
                if(buildings.get(i) instanceof Hospital){
                    Hospital hospital = (Hospital)buildings.get(i);
                    if((!added) && (hospital.doctors.size() < hospital.getMaxCapacity())){
                        hospital.addDoctors((Doctor)human);
                        human.setX(hospital.getX());
                        human.setY(hospital.getY());
                        added = true;
                    }
                }
            }
        }else if(human instanceof Researcher){
            //move to research lab
            for(int i = 0;i < buildings.size();i++){
                if(buildings.get(i) instanceof ResearchLab){
                    ResearchLab researchLab = (ResearchLab)buildings.get(i);
                    if(!added){
                        researchLab.researchers.add((Researcher)human);
                        human.setX(researchLab.getX());
                        human.setY(researchLab.getY());
                        added = true;
                    }
                }
            }
        }else if(human instanceof Cadet){
            //move to military
            for(int i = 0;i < buildings.size();i++){
                if(buildings.get(i) instanceof MilitaryBase){
                    MilitaryBase militaryBase = (MilitaryBase)buildings.get(i);
                    if(!added){
                        human.setX(militaryBase.getX());
                        human.setY(militaryBase.getY());
                        added = true;
                    }
                }
            }
        }
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
        
            return Soldier.BASE_SOLDIER_PRICE;
        }else if(type.equals("Spy")){
            return Spy.SPY_PRICE;
        }else{ //nothing proper was given so just send -1
            return -1;
        }
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


    public void requestTrainSoldier(int amount, int x, int y, int soldierLevel){
        String keys = "";
        //send requests to server
        sendMessage("<military soldier>");
        sendMessage(soldierLevel + " " + amount + " " + x + " " + y);

        for(int key: humanMap.keySet()){
            if(humanMap.get(key) instanceof Cadet){
                if(humanMap.get(key).getX() == x && humanMap.get(key).getY() == y);{
                keys = keys + " " + key;

                }
            }
        }

        sendMessage(keys);

        this.getPlayerClient().setLastRequest(("<military soldier>" + " " + soldierLevel + " " + amount + " " + x + " " + y + keys));
    }

    /**
     * request to surrender
     */
    public void requestSurrender(){
        sendMessage("<sur>");
        this.getPlayerClient().setLastRequest("<sur>");

    }

    /**
     * Request to train amount of spies from the citizens from coordinate x,y
     * @param amount Amount of spies to train
     * @param x Coordinate of the residency building
     * @param y coordiante of the residency building 
     */
    public void requestTrainSpy(int amount, int x, int y){
        String keys = "";
        //send requests to server
        sendMessage("<military spy>" );
        sendMessage(amount + " "+ x + " " + y);
        
        //sending the keys along for the npcs to be converted
        for(int key: humanMap.keySet()){
            if(humanMap.get(key) instanceof Cadet){
                if(humanMap.get(key).getX() == x && humanMap.get(key).getY() == y);{
                keys = keys + " " + key;

                }
            }
        }
        sendMessage(keys);

        this.getPlayerClient().setLastRequest("<military spy>" + " " + amount + " "+ x + " " + y + keys);
    }

    /**
     * Request to train certain amount of citizens at coordinates of the residency of x, y
     * @param y  The coordinate of the residency
     * @param x  The x coordinate of the residency
     * @param amount The amount of Citizens to train 
     */
    public void requestTrainCitizen(int amount, int x, int y){
        sendMessage("<residency train>"); 
        sendMessage(x + " " + y + " " + amount);                   
        //Update this class as well
        System.out.println("Your buttons have made it this far congrats");
        String keys = "";
        for(int key: humanMap.keySet()){
            if(humanMap.get(key) instanceof Citizen){
                if(humanMap.get(key).getX() == x && humanMap.get(key).getY() == y);{
                keys = keys + " " + key;

                }
            }
        }
        sendMessage(keys);

        this.getPlayerClient().setLastRequest("<residency train>"+  " " + x + " " + y + " " + amount);
    }

    /**
     * Change Citizens into doctors cadets or researchers
     * @param amount amount of citizens specialized
     * @param x The coordinate of the residency they currently reside in
     * @param y The coordinate of the residency they currently reside in
     * @param type The type to specialize to, Doctor, Cadet or reseracher
     */
    public void requestSpecializeCitizen(int amount, int x, int y , String type){
        String keys = "";
        sendMessage("<residency convert>"); 
        sendMessage(type);
        sendMessage(amount + " " + x + " " + y);


        for(int key: humanMap.keySet()){
            if(humanMap.get(key) instanceof Citizen){
                if(humanMap.get(key).getX() == x && humanMap.get(key).getY() == y);{
                keys = keys + " " + key;

                }
            }
        }

        sendMessage(keys);

        this.getPlayerClient().setLastRequest("<residency convert>" + " " + type + " " + amount + " " + x + " " + y + keys );
    }

    //end of setters

    /**
     * Looks for if a building with the coordinates of (x, y) exist
     * @param x the x coordinate of the building
     * @param y the y coordinate of the building
     * @return null if no such building exists, otherwise, the building in the (x, y) position
     */
    public Building findBuilding(int x, int y){
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
            report += "\nHospitals: " + hospitalCounter + "\nMilitary Bases: " + militaryBaseCounter + "\nResearch Labs: " + researchLabCounter  + "Residencies: " + residencyCounter;

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
        private DuberTextButton hospitalCapacity;
        /** Buttons relating to the research lab */
        private HashMap<String, DuberTextButton> researchLabButtons;
        /** General display buttons that all buildings need */
        private HashMap<String, DuberTextButton> generalButtons;
        /**food income button */
        private DuberTextButton foodIncome;
        /**Money income button */
        private DuberTextButton moneyIncome;
        /** Using the test button object also as text displays*/
        private DuberTextButton[] displays;
        /**a variable for storing how many humans are being trained */
        private int training = 0; 
        /**Keeping track of what type if being trained */
        private String npcType;
        /** the building that is being clicked on */
        private Building clickedBuilding;
        /**Levels button for users to click on */
        private DuberTextButton[] levels;
        /**The level that the soldier user will train */
        private int soldierLevel;
        /**Health of a building */
        private int buildingHealth = 0;
        /**level of the bhuilding */
        private int buildingLevel = 0;
        /**foodIncome */
        private int food = 0;
        /**moneyIncome */
        private int money = 0;
        /**buildingMaxHealth */
        private int buildingMaxHealth = 0;
        /**capacity of building value to display */
        private int capacity = 0;
        /** The max capacity of a building */
        private int maxCapacity = 0;



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


            //Maybe add a back button (returns to menu one)

            // all the buttons
            this.buildingTypesButtons = new DuberTextButton[6];
            this.buildingTypesButtons[0] = new DuberTextButton("MilitaryBase", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30));
            this.buildingTypesButtons[1] = new DuberTextButton("ResearchLab", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30));
            this.buildingTypesButtons[2] = new DuberTextButton("Residency", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30));
            this.buildingTypesButtons[3] = new DuberTextButton("Hospital", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30));
            this.buildingTypesButtons[4] = new DuberTextButton("FoodBuilding", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30));
            this.buildingTypesButtons[5] = new DuberTextButton("Bank", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30));

            
            levels = new DuberTextButton[3];
            for(int i = 0;i < levels.length;i++){
                levels[i] = new DuberTextButton("" + (i + 1), new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + i*80, 900, 60, 30));
            }

            //All residency buildings
            this.residencyButtons = new HashMap<>();
            this.residencyButtons.put("Train citizens" ,new DuberTextButton("Train citizens", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30)));
            this.residencyButtons.put("Specialize citizens",new DuberTextButton("Specialize citizens", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30)));
            this.residencyButtons.put("Researchers",new DuberTextButton("Researchers", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 620, 180, 30)));
            this.residencyButtons.put("Cadet",new DuberTextButton("Cadet", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 660, 180, 30)));
            this.residencyButtons.put("Doctor",new DuberTextButton("Doctor", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30)));
            //this.residencyButtons.put("Move",new DuberTextButton("Move", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30)));
            this.residencyButtons.put("Specialize citizens",new DuberTextButton("Specialize citizens: "  + training +  "  $"+ training * 100  , new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30)));
            this.residencyButtons.put("Add 1",new DuberTextButton("Add 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30)));
            this.residencyButtons.put("Citizens trained: " + training +  "  $"+ training * 100  ,new DuberTextButton("Citizens trained: " + training +  "  $"+ training * 100  , new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 740, 180, 30)));
            this.residencyButtons.put("Subtract 1",new DuberTextButton("Subtract 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 780, 180, 30)));
            this.residencyButtons.put("Train/Specialize", new DuberTextButton("Train/Specialize", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 820, 180, 30)));


            //militaryBase Buildings
            this.militaryBaseButtons = new HashMap<>();
            this.militaryBaseButtons.put("Add 1",new DuberTextButton("Add 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 650, 180, 30)));
            this.militaryBaseButtons.put("Subtract",new DuberTextButton("Subtract 1", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 750, 180, 30)));
            this.militaryBaseButtons.put("Train", new DuberTextButton("Train", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 800, 180, 30)));
            this.militaryBaseButtons.put("Soldier", new DuberTextButton("Soldier", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 540, 180, 30)));
            this.militaryBaseButtons.put("Spy", new DuberTextButton("Spy", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 580, 180, 30)));
            this.militaryBaseButtons.put("Trained", new DuberTextButton("Trained: " + training + "  $" + training*100, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30)));
            

            
            //All researchLab buildings
            this.researchLabButtons = new HashMap<>();
            this.researchLabButtons.put("Upgrade weapon", new DuberTextButton("upgrade next weapon level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 550, 180, 30)));
            this.researchLabButtons.put("Upgrade armour", new DuberTextButton("upgrade next armour level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 600, 180, 30)));
            this.researchLabButtons.put("Progress per turn", new DuberTextButton("unlock next armour level", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 700, 180, 30))); //TODO: do this button's function


            //All individual building buttons buttons
            this.foodIncome = new DuberTextButton("Food income per turn: " + food, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 600, 180, 30));
            this.moneyIncome = new DuberTextButton("Money Income per turn: " + money, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 600, 180, 30));
            this.hospitalCapacity = new DuberTextButton("A Hospital, Capacity:" + capacity + " /" + maxCapacity, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 600, 180, 30));

            //initialize general buttons
            this.generalButtons = new HashMap<>();
            this.generalButtons.put("Level", new DuberTextButton("Level: " + buildingLevel, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 200, 180, 30)));
            this.generalButtons.put("Health", new DuberTextButton("health: " + buildingHealth + "/" + buildingMaxHealth, new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 250, 180, 30)));
            this.generalButtons.put("upgrade", new DuberTextButton("Upgrade", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30)));
            this.generalButtons.put("back", new DuberTextButton("back", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 100, 960, 180, 30)));
            this.generalButtons.put("surrender", new DuberTextButton("surrender", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 960, 180, 30))); 
            this.generalButtons.put("Sure", new DuberTextButton("Are you sure?", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH, 960, 180, 30))); 

            this.buildButton = new DuberTextButton("Build", new Rectangle(GameWindow.GridPanel.GRID_SIZE_WIDTH + 300, 960, 180, 30));
            
            
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
                hospitalCapacity.draw(g);
              
                for(String key:residencyButtons.keySet()){
                    residencyButtons.get(key).draw(g);
                }
                /*
                for(String key:militaryBaseButtons.keySet()){
                    militaryBaseButtons.get(key).draw(g);
                }*/

                for(String key:researchLabButtons.keySet()){
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

                        if(clickedBuilding instanceof FoodBuilding){
                            food = ((FoodBuilding)clickedBuilding).getLevel() * 500 + 1000;
                        }else if(clickedBuilding instanceof Bank){
                            money = ((Bank)clickedBuilding).getLevel() * 500 + 1000;
                        }else if(clickedBuilding instanceof Hospital){
                            capacity = ((Hospital)clickedBuilding).getMaxCapacity();

                        }

                        //Setting the general buttons
                        buildingHealth = clickedBuilding.getHealth();
                        buildingMaxHealth = clickedBuilding.getMaxHealth();
                        buildingLevel = clickedBuilding.getLevel();

                        if(clickedBuilding != null){

                            activateGeneralBuildingButtons();
                            menu = 0;
                            //TODO: helpful reminder to display the upgrade price when activating the upgrade 

                            if(clickedBuilding instanceof Residency){

                                requestResidencyFunction(menu, mouseX, mouseY);

                            }else if(clickedBuilding instanceof MilitaryBase){
                                
                                requestMilitaryBaseFunction(menu, mouseX, mouseY);

                            }else if(clickedBuilding instanceof Hospital){

                                hospitalCapacity.activate();

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

                    if(generalButtons.get("surrender").inBounds(mouseX,mouseY)){
                        requestSurrender();

                    }else if(generalButtons.get("back").inBounds(mouseX, mouseY)){
                        menu = 0; 

                    }else if(clickedBuilding instanceof Residency){

                        requestResidencyFunction(menu, mouseX, mouseY);
                    }else if(clickedBuilding instanceof Hospital){

                        requestHospitalFunction(menu, mouseX, mouseY);
                    }else if(clickedBuilding instanceof MilitaryBase){

                        requestMilitaryBaseFunction(menu, mouseX, mouseY);
                    }else if(clickedBuilding instanceof ResearchLab){

                        requestResearchLabFunction(menu, mouseX, mouseY);
                    }else if(clickedBuilding instanceof FoodBuilding){

                        requestFoodBuildingFunctions(menu, mouseX, mouseY);
                    }else if(clickedBuilding instanceof Bank){

                        requestBankButtonsFunctions(menu, mouseX, mouseY);
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

            public void requestResidencyFunction(int menu, int mouseX,  int mouseY){

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

                        requestTrainCitizen(training, clickedBuilding.getX(), clickedBuilding.getY());
                        
                        //return to default screens
                        menu = 0;
                        training = 0;


                        residencyButtons.get("Add 1").deactivate();
                        residencyButtons.get("Train/Specialize").deactivate();
                        residencyButtons.get("Subtract 1").deactivate();
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


                        requestSpecializeCitizen(training, clickedBuilding.getX(), clickedBuilding.getY(), npcType);
                        

                        System.out.println("Your buttons have made it this far congrats");

                        //return to default screens
                        menu = 0;
                        training = 0;

                        //deactivate current buttons 
                        residencyButtons.get("Add 1").deactivate();
                        residencyButtons.get("Specialize citizens: ").deactivate();
                        residencyButtons.get("Subtract 1").deactivate();

                        
                    }
                    
                }
            }
            
            /**
             * Menus for the when the Use clicks on the 
             * @param menu
             * @param mouseX
             * @param mouseY
             */
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


                        requestTrainSpy(training, clickedBuilding.getX(), clickedBuilding.getY());
                        
                        menu = 0;
                        training = 0;

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


                        requestTrainSoldier(training, clickedBuilding.getX(), clickedBuilding.getY(), soldierLevel);

                        //reset values to defaules
                        menu = 0;
                        soldierLevel = 1;
                        training  = 0;

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
            
            /**
             * Hospital may display current capacity etc removes buttons that aren't supposed to bc there
             *  */
            public void requestHospitalFunction(int menu, int mouseX,  int mouseY ){
                
                deactivateBuildingButtons();
            }

            /**
             * Research lab menu, Allows for users to click on research weapons, armour
             * @param menu The menu that the user is currently on 
             * @param mouseX The x position of the mouseclick
             * @param mouseY The y position of the mouse click
             */
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

            /**
             * Display the current food buildind's income
             */
            public void requestFoodBuildingFunctions(int menu, int mouseX,  int mouseY){
                
                deactivateBuildingButtons();
                foodIncome.activate();
            }   

            /**
             * Display the current bank's income 
             */
            public void requestBankButtonsFunctions(int menu, int mouseX,  int mouseY){
                
                deactivateBuildingButtons();
                moneyIncome.activate();
            }

            /**
             * Deactivate the other buildings buttons that aren't supposed to be displayed on the current menu
             */
            public void deactivateBuildingButtons(){
                
                if( !(clickedBuilding instanceof Residency)){ //if current building is not residencyy, clear all residency buttons
                    for(String key: residencyButtons.keySet()){
                        residencyButtons.get(key).deactivate();
                    }
                }
                if( !(clickedBuilding instanceof Hospital)){
                    hospitalCapacity.deactivate();
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

            /**
             * Activate buttons that every building will have like levels, buildings and healh
             */
            public void activateGeneralBuildingButtons(){
                

                for(String key: generalButtons.keySet()){
                    generalButtons.get(key).activate();

                }
            }

            public void deactivateGeneralBuildingButtons(){


            }

        }

    }//end of inner class
}//end of class
