//graphics imports
import java.awt.Rectangle;

//data structures
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * [Game.java]
 * A class for storing all game parts which the server can access
 * @author Damon Ma, Edward Yang, and Vivian Dai
 * @version 1.0 on December 30, 2020
 */

class Game {
    /** Maximum number of turns before the game comes to a stalemate */
    public static final int MAX_TURNS = 10;
    /** Percent of total NPCs that must be SCP-049-2s in order for the SCP side to achieve world domination and win the game */
    private static final double SCP_WIN_PERCENT = 0.6;
    /** The {@code ArrayList} containing all {@code Buildings} in the {@code Game} */
    private ArrayList<Building> buildings;
    /** The {@code HashMap} of {@code Humans} so that each {@code Human} only needs an ID */
    private HashMap<Integer, Human> humanMap;
    /** The {@code NPCs} belonging to the SCP side */
    private ArrayList<SCP0492> scps;
    /** The {@code ArrayList} of ongoing {@code Events} */
    private ArrayList<Event> events;
    /** The amount of money the town side has */
    private int money;
    /** The amount of food the town side has */
    private int food;
    /** The amount of hume points the SCP side has */
    private int hume;
    /** The current turn of the {@code Game} */
    private int turn;
    /** The amount of money increase the town gets each turn */
    private int moneyPerTurn;
    /** The increase in food each turn */
    private int foodPerTurn;
    /** The ID to assign to the next {@code Human} created */
    private int currentId;
    /**The changes in money per turn*/
    private int moneyChange;
    /**The amount of change in food per Turn */
    private int foodChange;
    /**The amount of change in hume per Turn */
    private int humeChange;
    /**casualties of humans */
    private int casualties;
    /**armour level unlocked */
    private int armourUnlocked = 1;
    /**weapon level unlocked */
    private int weaponUnlocked = 1;
    /**The research progress for a cure */
    private double researchProgress = 0;

    /**
     * Constructor for the {@code Game} class, assigns preset values
     */
    public Game(){
        this.buildings = new ArrayList<Building>();
        this.humanMap = new HashMap<Integer, Human>();
        this.scps = new ArrayList<SCP0492>();
        this.events = new ArrayList<Event>();
        this.money = 500; //TODO: coordinate money amounts
        this.food = 10;
        this.hume = 50;
        this.turn = 1;
        this.moneyPerTurn = 0;
        this.foodPerTurn = 0;
        this.currentId = 0;
        this.moneyChange = 0;
        this.foodChange = 0;
        this.humeChange = 0;
        this.casualties = 0;

        this.buildings.add(new Residency(Residency.INITIAL_PRICE, Residency.INITIAL_HEALTH, Residency.INITIAL_HEALTH, 504, 504, Residency.INITIAL_MAX_CAP));
        this.buildings.add(new FoodBuilding(FoodBuilding.INITIAL_PRICE, FoodBuilding.INITIAL_HEALTH, FoodBuilding.INITIAL_HEALTH, 346, 346));
        this.buildings.add(new Bank(Bank.INITIAL_PRICE, Bank.INITIAL_HEALTH, Bank.INITIAL_HEALTH, 346, 188));

        for(int i = 0; i < 100; i ++){
            Citizen startingCitizen = new Citizen( 18, 100, 504, 504);
            humanMap.put(currentId,startingCitizen);
            currentId ++;
            Residency startingRes = (Residency)findBuilding(504, 504);
            startingRes.createCitizen(startingCitizen);
        }



    }


    /**
     * Removes defeated NPC's from the building that they are inside.
     */
    private void removeDeadPeopleFromBuildings(){
        for(int i = 0; i < this.buildings.size(); i++){
            if(this.buildings.get(i) instanceof Hospital){
                ArrayList<Doctor> newDoctors = new ArrayList<Doctor>();
                Iterator<Doctor> dIterator = ((Hospital)this.buildings.get(i)).getDoctors().iterator();
                while(dIterator.hasNext()){
                    Doctor currentDoctor = dIterator.next();
                    if(currentDoctor.getHealth() > 0){
                        newDoctors.add(currentDoctor);
                    }
                }
                ((Hospital)this.buildings.get(i)).setDoctors(newDoctors);

                //TODO: help idk how to remove things from queues

            }else if(this.buildings.get(i) instanceof MilitaryBase){
                Iterator<Human> sIterator = ((MilitaryBase)this.buildings.get(i)).getSoldiers().iterator();
                while(sIterator.hasNext()){
                    Human currentSoldier = sIterator.next();
                    if(currentSoldier.getHealth() <= 0){
                        sIterator.remove();
                    }
                }

            }else if (this.buildings.get(i) instanceof ResearchLab){
                Iterator<Researcher> rIterator = ((ResearchLab)this.buildings.get(i)).getResearchers().iterator();
                while(rIterator.hasNext()){
                    Researcher currentResearcher = rIterator.next();
                    if(currentResearcher.getHealth() <= 0){
                        rIterator.remove();
                    }
                }
            }else if (this.buildings.get(i) instanceof Residency){
                Iterator<Human> cIterator = ((Residency)this.buildings.get(i)).getResidents().iterator();
                while(cIterator.hasNext()){
                    Human currentResident = cIterator.next();
                    if(currentResident.getHealth() <= 0){
                        cIterator.remove();
                    }

                }
            }
        }

    }



    /**
     * <p>
     * First loops through buildings and checks if it has more than 0 health. If it has 0 or less health, delete all {@code NPCs} 
     * inside the {@code Building} then delete it from the list of buildings. Loops through the humans and the scps as well to 
     * check for if they should still be alive.
     * </p>
     */
    private void killDeadStuff(){

        //loop through each building's area
        ArrayList<Building> newBuildings = new ArrayList<Building>();
        Iterator<Building> bIterator = this.buildings.iterator();
        while(bIterator.hasNext()){
            Building currentBuilding = bIterator.next();
            if(currentBuilding.getHealth() <= 0){
                Rectangle buildingArea = new Rectangle(currentBuilding.getX(), currentBuilding.getY(), Building.SIZE, Building.SIZE);

                //remove humans in the area
                HashMap<Integer, Human> newHumanMap = new HashMap<Integer, Human>();
                Iterator<Integer> humanKeyIterator = this.humanMap.keySet().iterator();//TOOD: this might be an issue
                while(humanKeyIterator.hasNext()){
                    int key = humanKeyIterator.next();
                    Human currentHuman = this.humanMap.get(key);
                    Rectangle humanArea = new Rectangle(currentHuman.getX(), currentHuman.getY(), NPC.SIZE, NPC.SIZE);
                    if(buildingArea.contains(humanArea)){
                        this.casualties ++;
                    }else{
                        newHumanMap.put(key, currentHuman);
                    }
                }
                this.humanMap = newHumanMap;

                //remove spc in the area
                ArrayList<SCP0492> newScps = new ArrayList<SCP0492>();
                Iterator<SCP0492> scpIterator = this.scps.iterator();
                while(scpIterator.hasNext()){
                    SCP0492 currentScp = scpIterator.next();
                    Rectangle scpArea = new Rectangle(currentScp.getX(), currentScp.getY(), NPC.SIZE, NPC.SIZE);
                    if(!buildingArea.contains(scpArea)){
                        newScps.add(currentScp);
                    }
                }
                this.scps = newScps;
            }else{
                newBuildings.add(currentBuilding);
            }
            this.buildings = newBuildings;
        }

        //remove all humans with health less than 0
        Iterator<Integer> humanKeyIterator = this.humanMap.keySet().iterator(); //TODO: this may be an issue
        while(humanKeyIterator.hasNext()){
            int key = humanKeyIterator.next();
            Human currentHuman = this.humanMap.get(key);
            if(currentHuman.getHealth() <= 0){
                humanKeyIterator.remove();
                this.casualties ++;
            }
        }

        //remove all scps with health less than 0
        ArrayList<SCP0492> newScps = new ArrayList<SCP0492>();
        Iterator<SCP0492> scpIterator = this.scps.iterator();
        while(scpIterator.hasNext()){
            SCP0492 currentScp = scpIterator.next();
            if(currentScp.getHealth() > 0){
                newScps.add(currentScp);
            }
        }
        this.scps = newScps;
    }

    
    /**
     * <p>
     * Sets the amount of moneyPerTurn to zero temporarily to recount how much money the {@code Banks} earned this turn. Sets the 
     * foodPerTurn to zero as well to recount. Loops through all {@code Banks} and adds that much to the moneyPerTurn. Loops through 
     * the {@code FoodBuildings} and adds the food earned by it to foodPerTurn. Adds moneyPerTurn to money and foodPerTurn to food.
     * </p>
     */
    private void getResourcesFromBuildings(){
        this.moneyPerTurn = 0;
        this.foodPerTurn = 0;
        for(int i = 0;i < this.buildings.size();i++){
            if(this.buildings.get(i) instanceof Bank){
                this.moneyPerTurn += ((Bank)this.buildings.get(i)).earnResource();
            }else if(this.buildings.get(i) instanceof FoodBuilding){
                this.foodPerTurn += ((FoodBuilding)this.buildings.get(i)).earnResource();
            }
        }
        this.money += this.moneyPerTurn;
        this.food += this.foodPerTurn;
        this.changeFoodChange( this.foodPerTurn);
        this.changeMoneyChange(this.moneyPerTurn);

    }

    /**
     * <p>
     * Generates a random value. Find the chance of a {@code Stonks} occuring by multiplying 1% by the current turn number. The 
     * chance of {@code Stonks} increases as time goes on. A {@code Stonks} is created if the first random value is lower than 
     * the chance of {@code Stonks}. If this happens, a random number between 1 and 3 is generated for the level of the 
     * {@code Stonks}.
     * </p>
     */
    private void calculateStonks(){
        double randomStonksChance = Math.random();
        double stonksChance = 0.01 * this.turn;
        if(randomStonksChance <= stonksChance){
            int level = (int)Math.round(Math.random()*2) + 1;
            startEvent(new Stonks(level));
        }
    }

    /**
     * <p>
     * Loops through the list of events and allows the {@code Event} to affect this {@code Game}. Decreases how much time is left in 
     * the {@code Event}. Check if the currentEvent should still be alive, if not delete it.
     * </p>
     */
    private void dealWithEvents(){
        ArrayList<Event> newEventList = new ArrayList<Event>();
        Iterator<Event> eIterator = this.events.iterator();
        while(eIterator.hasNext()){
            Event currentEvent = eIterator.next();
            currentEvent.affect(this);
            currentEvent.decreaseTimeLeft();
            if(currentEvent instanceof Tornado){
                int dx = (int)Math.round((Math.random()*2) - 1);
                int dy = (int)Math.round((Math.random()*2) - 1);
                int distX = (int)Math.round(Math.random()*Building.SIZE);
                int distY = (int)Math.round(Math.random()*Building.SIZE);
                ((Tornado)currentEvent).translate(dx*distX, dy*distY);
            }else if((currentEvent instanceof Thunderstorm) && (((Thunderstorm)currentEvent).getStrikesLeft() > 0)){
                newEventList.add(currentEvent);
            }
            if(currentEvent.getTimeLeft() > 0){
                newEventList.add(currentEvent);
            }
        }
        this.events = newEventList;
    }

    /**
     * Consumes 10 food for every human each turn
     */
    private void eatFood(){

        int humanNum = humanMap.size();
        
        if(this.getFood() >= humanNum*10){

            this.changeFood(-10*humanNum);
            this.changeFoodChange(-10*humanNum);

        }else{

            for(int key: humanMap.keySet()){

                if(this.getFood() >= 10){

                    this.changeFood(-10);
                    this.changeFoodChange(-10);

                //no more food so anyone now will starve
                }else{
                    humanMap.get(key).changeHunger(-10);
                }
            }
        }
    }

    /**
     * Loops through each SCP0492 and generates a random amount to move it by
     */
    private void moveSpcs(){

        for(int i = 0;i < this.scps.size();i++){

            int dx = (int)Math.round((Math.random()*2) - 1);
            int dy = (int)Math.round((Math.random()*2) - 1);
            int distX = (int)Math.round(Math.random()*NPC.SIZE);
            int distY = (int)Math.round(Math.random()*NPC.SIZE);

            int xMove;
            int yMove;
            
            //check and adjust for npcs moving out of bounds
            if( dx*distX + this.scps.get(i).getX() > 1080){

                //will move x coordinate to 1080
                xMove = 1080 - this.scps.get(i).getX();
            }else if( dx*distX + this.scps.get(i).getX() < 0){

                //move npc to exactly 0
                xMove =  dx*distX + this.scps.get(i).getX();
            }else{

                xMove = dx*distX;
            }

            if( dy*distY + this.scps.get(i).getY() > 1080){

                //will move x coordinate to 1080
                yMove = 1080 - this.scps.get(i).getY();
            }else if( dy*distY + this.scps.get(i).getY() < 0){

                //move npc to exactly 0
                yMove =  dy*distY + this.scps.get(i).getY();
            }else{

                yMove = dy*distY;
            }

            this.scps.get(i).translate(xMove, yMove);
        }
    }

    /**
     * Handles attacking mechanics with a quadtree for SCP0492s attacking humans and another for Soldiers attacking SCP0492s.
     */
    private void handleAttacks(){

        QuadTree scpAttack = new QuadTree(1080, 1420, 710, 540, 0);
        for(int i = 0;i < scps.size();i++){
            scpAttack.insertAttacker(scps.get(i));
        }
        for(int key: humanMap.keySet()){
            scpAttack.insertTarget(humanMap.get(key));
        }
        scpAttack.startCombat();

        QuadTree soldierAttack = new QuadTree(1080, 1420, 710, 540, 0);
        for(int key: humanMap.keySet()){
            if(humanMap.get(key) instanceof Soldier){
                soldierAttack.insertAttacker((Soldier)humanMap.get(key));
            }
        }
        for(int i = 0;i < scps.size();i++){
            soldierAttack.insertTarget(scps.get(i));
        }
        soldierAttack.startCombat();
    }

    /**
     * Loops through every citizen and makes them get older
     */
    private void ageCitizens(){
        for(int key: humanMap.keySet()){
            humanMap.get(key).gotOld();
        }
    }

    /**
     * <p>
     * First loops through the buildings to find all the hospitals. Then loops through all the hospitals to heal 
     * the people in the hospital. Next loops through all the humans. If the human's health isn't maximized, add 
     * it to the hospital queue and it will be healed next turn.
     * </p>
     */
    private void dealWithMedicalStuff(){
        ArrayList<Hospital> hospitals = new ArrayList<Hospital>();

        for(Building building: buildings){
            if(building instanceof Hospital){
                hospitals.add((Hospital)building);
            }
        }

        for(Hospital hospital: hospitals){
            hospital.heal();
        }
        if(hospitals.size() > 0){
            for(int key: humanMap.keySet()){
                Human human = humanMap.get(key);
                if(human.getHealth() < human.getMaxHealth()){
                    Hospital hospital = hospitals.get((int)Math.round(Math.random() * hospitals.size())); //adds human to a random hospital
                    hospital.addInjured(human);
                }
            }
        }
    }

    /**
     * Checks for if the SCP side has satisfied their winning conditions (60% of total population is SCP-049-2s)
     * @return true if the winning conditions have been satisfied, false otherwise
     */
    public boolean checkScpWin(){
        int humanCount = this.getHumanMap().size();
        int scpCount = this.getScps().size();
        int total = humanCount + scpCount;
        if(total == 0){//check for dividing by 0 math errors
            return false;
        }else{
            if(scpCount/total >= SCP_WIN_PERCENT){
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean checkTownWin(){
        boolean win = false; 

        if(researchProgress >= 100){

            win = true;
        }

        return win;
    }
    
    /**
     * <p>
     * Loops through all {@code Humans} in the game. If the current {@code Human} is a {@code Spy}, generate two random numbers. 
     * The first random number is the sus rate, if the {@code Spy} is too sus, then the enemy captures the {@code Spy} and it 
     * gets removed. The second randomly generated number is the success rate. If the {@code Spy} has a success rate higher than 
     * the generated number, true is returned so that super secret info can be revealed to the town. If no {@code Spy} manages 
     * to get any info, false is returned.
     * </p>
     * @return true if successful, false if not
     */
    public boolean gotIntel(){
        Iterator<Integer> humanKeyIterator = this.humanMap.keySet().iterator();
        while(humanKeyIterator.hasNext()){
            int key = humanKeyIterator.next();
            Human currentHuman = this.humanMap.get(key);
            if(currentHuman instanceof Spy){
                Spy spy = (Spy)currentHuman;
                double sus = Math.random();
                double success = Math.random();
                if(sus < spy.getSus()){
                    humanKeyIterator.remove();//TOOD: may be problematic
                }else if(success < spy.getSuccessRate()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the buildings 
     * @return buildings, an {@code ArrayList} of all {@code Building} objects
     */
    public ArrayList<Building> getBuildings(){
        return this.buildings;
    }

    /**
     * Gets the SCP-049-2s
     * @return scps which contains all {@code SCP0492} objects
     */
    public ArrayList<SCP0492> getScps(){
        return this.scps;
    }

    /**
     * Gets the {@code Event} objects
     * @return events, a list of all game {@code Event} objects
     */
    public ArrayList<Event> getEvents(){
        return this.events;
    }

    /**
     * Gets the {@code Event} objects excluding the {@code Stonk} objects
     * @return
     */
    public ArrayList<Event> getEventsWithoutStonks(){
        ArrayList<Event> toReturn = new ArrayList<Event>();
        for(int i = 0;i < this.events.size();i++){
            if(!(this.events.get(i) instanceof Stonks)){
                toReturn.add(this.events.get(i));
            }
        }
        return toReturn;
    }

    /**
     * Gets the {@code Human} objects but in the map
     * @return humanMap, a map of all {@code Human} objects
     */
    public HashMap<Integer, Human> getHumanMap(){
        return this.humanMap;
    }
    
    /**
     * Gets the amount of money the town has
     * @return money, the amount of money the town side has
     */
    public int getMoney(){
        return this.money;
    }

    /**
     * Gets how much food the town has
     * @return food, the amount of food the town has
     */
    public int getFood(){
        return this.food;
    }

    /**
     * Gets how many hume points the SCPs have
     * @return hume, the amount of hume points the SCPs have
     */
    public int getHume(){
        return this.hume;
    }

    /**
     * Gets the amount of money made per turn
     * @return moneyPerTurn, the money made per turn
     */
    public int getMoneyPerTurn(){
        return this.moneyPerTurn;
    }

    /**
     * Gets the amount of change in money this turn
     * @return moneyChange
     */
    public int getMoneyChange(){
        return this.moneyChange;
    }

    /**
     * Gets the amount of hume changed this turn
     * @return HumeChange
     */
    public int getHumeChange(){
        return this.humeChange;
    }

    /**
     * Gets the amount of food changed this turn
     * @return foodChang
     */
    public int getFoodChange(){
        return this.foodChange;
    }

    /**
     * Gets the current turn
     * @return turn, the number of turns that have passed so far
     */
    public int getTurn(){
        return this.turn;
    }

    /**
     * Adds building to the list of {@code Buildings}
     * @param building the {@code Building} to add
     */
    public void addBuilding(Building building){
        this.buildings.add(building);
    }

    /**
     * Checks what kind of NPC ({@code SCP0492} or {@code Human}) is being added then adds to the appropriate list
     * @param npc the {@code NPC} to be added
     */
    public void addNpc(NPC npc){

 
        if(npc instanceof SCP0492){
            scps.add((SCP0492)npc);
        }else{
            humanMap.put(currentId, (Human)npc);
            currentId++;
        }
    }

    /**
     * Adds the event to the list of events and starts its affect
     * @param event the {@code Event} started
     */
    public void startEvent(Event event){
        this.events.add(event);
        event.affect(this);
    }

    /**
     * Changes the amount of money by change amount
     * @param change the change in money to be made
     */
    public void changeMoney(int change){
        this.money += change;
    }

    /**
     * Changes the amount of food by change amount
     * @param change the change in food
     */
    public void changeFood(int change){
        this.food += change;
    }

    /**
     * Changes the amount of hume points by change amount
     * @param change the amount of change in hume points
     */
    public void changeHume(int change){
        this.hume += change;
    }

    /**
     * Changes the moneyPerTurn by change amount
     * @param change the change in the money per turn
     */
    public void changeMoneyPerTurn(int change){
        this.moneyPerTurn += change;
    }

    /**
     * Changes the foodPerTurn by change amount
     * @param change the change in food per turn
     */
    public void changeFoodPerTurn(int change){
        this.foodPerTurn += change;
    }

    /**
     * Changes the humeChange by change
     * @param change the change in hume
     */
    public void changeHumeChange(int change){
        this.humeChange += change;
    }

    /**
     * Changes foodChange by change
     * @param change
     */
    public void changeFoodChange(int change){
        this.foodChange += change;
    }

    /**
     * Changes moneyChange by change
     * @param change
     */
    public void changeMoneyChange(int change){
        this.moneyChange += change;
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
        if(newNpc instanceof SCP0492){
            this.humanMap.remove(key); //TODO: might have thread conflicts, will deal with if run into (maybe)
            this.scps.add((SCP0492)newNpc);
        }else{
            this.humanMap.replace(key, (Human)newNpc);
        }
    }

    /**
     * Adding a specialized citizen
     * @param type The type of specialization
     * @param amount how many to specialize 
     * @param x The residency the building came from 
     * @param y the residency coorediante the building came from
     * @return Whether this was successful
     */
    public boolean specializeCitizen(String type, int amount, int x, int y, ArrayList<Integer> myKeys){

        boolean success = false;
        boolean buildingExist = false;
        ArrayList<Building> buildingList = this.getBuildings();
        int availableSpace = 0;
        //TODO synchronize activities on both sides

        //if adding Researchers
        if(type.equals("Researcher")){

            for(int i = 0; i < buildingList.size(); i ++){
                if(buildingList.get(i) instanceof ResearchLab) {
                    buildingExist = true;
                }
            }

            if(buildingExist){
                success = randomAdd("Researcher", amount, x, y, myKeys);
            }
        //if we are adding Doctors
        }else if(type.equals("Doctor")){
            
            for(int i = 0; i < buildingList.size(); i ++){
                if(buildingList.get(i) instanceof Hospital) {
                    buildingExist = true;

                    availableSpace = ((Hospital)buildingList.get(i)).getMaxCapacity() - ((Hospital)buildingList.get(i)).getDoctors().size();
                }
            }

            if(availableSpace >= amount){

                randomAdd("Doctor", amount, x, y, myKeys);
            }
        //if we are adding Cadets
        }else if (type.equals("Cadet")){
            for(int i = 0; i < buildingList.size(); i ++){
                if(buildingList.get(i) instanceof MilitaryBase) {
                    buildingExist = true;
                }
            }

            if(buildingExist){
                success = randomAdd("Cadet", amount, x, y, myKeys);
            }
        }
        return success;
    }

    //TODOO: win condition for town side, research lab cure stuff also modify

    /**
     * randomly add the npc to one of the building that it bleongs in
     * @param type The type of npc
     * @param amount the amount of npc
     * @param x the coordinate of the building that it came from
     * @param y The coordiante of the building that it came from
     * @return Success or not
     */
    private boolean randomAdd(String type, int amount, int x, int y,  ArrayList<Integer> myKeys){
        
        boolean success = false;
        Building building = findBuilding( x, y);
        ArrayList<NPC> adults = new ArrayList<>();

        if(building instanceof Residency){


            for(int i = 0; i < amount; i ++){
                
                convert(myKeys.get(i), type, 100, 10, 1, 1, 1, 100);
                //TODO: figure out the numbers
                locateHumanInProperSpot(this.humanMap.get(myKeys.get(i)));
            }

            changeMoney(-100*amount);
        }
        return success;
    }

    /**
     * Relocates the {@code Human} to the proper {@code Building} based on the occupation they now have. 
     * Duberville offers good teleportation services so all {@code Humans} can live in their workplace 
     * as soon as they get hired.
     * @param human the {@code Human} to relocate
     */
    private void locateHumanInProperSpot(Human human){
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
     * add a citizen npc that's being requested by the user
     * @param x x coordinate of building
     * @param y y coordinate of building 
     * @param amount amount of citizens to train
     * @return True is transaction success false is transaction was not successful
     */
    public boolean trainCitizen(int x, int y, int amount){
        
        boolean success = false;

        Building building = findBuilding(x, y);

        System.out.println(((Residency)building).getMaxCap() + " is max cap");
        System.out.println(((Residency)building).getCurrentPopulation() + " is current population");
        System.out.println("This is the amounnt of citizens to be trained: " + amount); 

        if( ((Residency)building).getMaxCap() - ((Residency)building).getCurrentPopulation() >= amount ){

            for(int i = 0; i < amount; i ++){
                Citizen add = new Citizen(28, 100, x, y);
                //TODO: they are all considered adults as of right now
                //TODO: they do not need to be trained right now, make sure to hange that when not game balancing

                addNpc(add);
                ((Residency)building).createCitizen(add);
            }
            System.out.println("Surely they've been added now right?");

            success = true;
            this.changeMoney(-50*amount); //TODO: synchronize moneyy amount, make sure it's the same
        }

        return success;

    }

    /**
     * Train a cadet to soldier
     * @param amount Amount of soldiers to train
     * @param level the level to train them to 
     * @param x coordiante of the Base
     * @param y coordiante of the base
     * @param myKeys list of keys of cadets
     * @return The success
     */
    public boolean trainSoldier(int amount, int level, int x, int y, ArrayList<Integer> myKeys){
        boolean success = false;
        if(weaponUnlocked >= level && armourUnlocked >= level){
            for(int i = 0; i < amount; i ++){
                convert(myKeys.get(i), "Soldier",level*100, level*10, 1, 1, 1, 100); //Level with attack damage 
                
            }
            success = true;

            changeMoney(-100*amount*level);
        }
        return success;
    }

    /**
     * Train a cadet toa spy
     * @param amount Amount of spies to train 
     * @param x 
     * @param y
     * @param myKeys
     * @return
     */
    public boolean trainSpy(int amount, int x, int y, ArrayList<Integer> myKeys){
        boolean success = false; 

        for(int i = 0; i < amount; i ++){
            convert(myKeys.get(i),"Spy", 100, 0, 1,1,1, 100);

        }

        changeMoney(-100*amount);
        success = true;

        return success;
    }
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
     * Unlock a new armour level
     */
    public boolean upgradeArmour(){
        boolean success = false;
        armourUnlocked ++;
        changeMoney(-400);
        return success;
    }

    /**
     * Unlock a new weapon level
     */
    public boolean upgradeWeapon(){
        boolean success = false;
        armourUnlocked ++;
        changeMoney(-400);

        return success;
    }

    /**
     * Conducts research for a cure in the research lab, has a chance to increases the progress for a cure
     * When a cure is found town side win 
     */
    public void doResearch(){

        for(int i = 0; i < buildings.size(); i ++){

            if(buildings.get(i) instanceof ResearchLab){

                researchProgress += ((ResearchLab)buildings.get(i)).developCure(); //TODO: balance research lab numbers
            }
        }
    }

    /**
     * <p>
     * Checks for if the current turn count is still valid. If so, increase turn number by one. Calls 
     * getResourcesFromBuildings to collect resources. Calls dealWithEvents to handle the {@code Events}. 
     * Calls on moveNpcs to move the mindless {@code NPCs} Calls handleAttacks to deal with {@code SCP0492s} 
     * attacking {@code Humans} and {@code Soldiers} attacking {@code SCP0492s}. Calls on eatFood to handle the fact 
     * {@code Humans} need to consume objects of nutritious value if they wish to continue metabolising. Calls on 
     * killDeadStuff to kill off anything that should be dead. dealWithMedicalStuff deals with healing people 
     * and sticking more injured patients into the {@code Hospitals}. ageCitizens loops through each citizen 
     * to make them get older.
     * </p>
     */
    public synchronized void doTurn(){
        if(this.turn <= MAX_TURNS){
            this.turn++;

            getResourcesFromBuildings();
            calculateStonks();
            dealWithEvents();
            moveSpcs();
            handleAttacks();
            eatFood();
            killDeadStuff();
            removeDeadPeopleFromBuildings();
            dealWithMedicalStuff();
            ageCitizens();
            changeHume(15);
            changeMoney(50);
        }

    }

    /**
     * Resets the values that got changed per turn
     */
    public void resetTurnChanges(){
        //resets turn changes 
        this.foodChange = 0;
        this.moneyChange = 0;
        this.humeChange = 0;
        this.casualties = 0;
    }

    
}
