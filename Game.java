//graphics imports
import java.awt.Rectangle;

//data structures
import java.util.ArrayList;
import java.util.HashMap;

/**
 * [Game.java]
 * A class for storing all game parts which the server can access
 * @author Damon Ma, Edward Yang, and Vivian Dai
 * @version 1.0 on December 30, 2020
 */

class Game {
    /** The {@code ArrayList} containing all {@code Buildings} in the {@code Game} */
    private ArrayList<Building> buildings;
    /** The {@code NPCs} belonging to the town side */
    private ArrayList<Human> humans;
    /** The {@code NPCs} belonging to the SCP side */
    private ArrayList<SCP0492> scps;
    /** The {@code ArrayList} of ongoing {@code Events} */
    private ArrayList<Event> events;
    /** The {@code HashMap} of {@code Humans} so that each {@code Human} only needs an ID */
    private HashMap<Integer, Human> humanMap;
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
    private int casualties; //TODO: send moneyChange - casualties from the server

    /**
     * Constructor for the {@code Game} class, assigns preset values
     */
    public Game(){
        this.buildings = new ArrayList<Building>();
        this.humans = new ArrayList<Human>();
        this.scps = new ArrayList<SCP0492>();
        this.events = new ArrayList<Event>();
        this.humanMap = new HashMap<Integer, Human>();
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

        this.buildings.add(new Residency(Residency.INITIAL_PRICE, Residency.INITIAL_HEALTH, Residency.INITIAL_HEALTH, 30, 30, Residency.INITIAL_MAX_CAP));
        this.buildings.add(new FoodBuilding(FoodBuilding.INITIAL_PRICE, FoodBuilding.INITIAL_HEALTH, FoodBuilding.INITIAL_HEALTH, 188, 30));
        this.buildings.add(new Bank(Bank.INITIAL_PRICE, Bank.INITIAL_HEALTH, Bank.INITIAL_HEALTH, 346, 30));
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
        for(Building currentBuilding: this.buildings){
            if(currentBuilding.getHealth() <= 0){
                Rectangle buildingArea = new Rectangle(currentBuilding.getX(), currentBuilding.getY(), Building.SIZE, Building.SIZE);

                //remove humans in the area
                for(int key:this.humanMap.keySet()){
                    Human currentHuman = this.humanMap.get(key);
                    Rectangle humanArea = new Rectangle(currentHuman.getX(), currentHuman.getY(), NPC.SIZE, NPC.SIZE);
                    if(buildingArea.contains(humanArea)){

                        this.humanMap.remove(key);
                        this.humans.remove(currentHuman);
                        this.casualties ++;
                    }
                }

                //remove spc in the area
                for(SCP0492 currentScp: this.scps){
                    Rectangle scpArea = new Rectangle(currentScp.getX(), currentScp.getY(), NPC.SIZE, NPC.SIZE);
                    if(buildingArea.contains(scpArea)){
                        this.scps.remove(currentScp);
                    }
                }
                this.buildings.remove(currentBuilding);
            }
        }

        //remove all humans with health less than 0
        for(int key: this.humanMap.keySet()){
            Human currentHuman = this.humanMap.get(key);
            if(currentHuman.getHealth() <= 0){
                this.humanMap.remove(key);
                this.humans.remove(currentHuman);
                this.casualties ++;
            }
        }

        //remove all scps with health less than 0
        for(SCP0492 currentScp: this.scps){
            if(currentScp.getHealth() <= 0){
                scps.remove(currentScp);
            }
        }
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
        for(Event currentEvent:this.events){
            currentEvent.affect(this);
            currentEvent.decreaseTimeLeft();
            if(currentEvent instanceof Tornado){
                int dx = (int)Math.round((Math.random()*2) - 1);
                int dy = (int)Math.round((Math.random()*2) - 1);
                int distX = (int)Math.round(Math.random()*Building.SIZE);
                int distY = (int)Math.round(Math.random()*Building.SIZE);
                ((Tornado)currentEvent).translate(dx*distX, dy*distY);
            }else if((currentEvent instanceof Thunderstorm) && (((Thunderstorm)currentEvent).getStrikesLeft() <= 0)){
                this.events.remove(currentEvent);
            }
            if(currentEvent.getTimeLeft() <= 0){
                this.events.remove(currentEvent);
            }
        }
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
        //TODO: manage food
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
        //TODO: move npcs that need to be moved
    }

    /**
     * <p>
     * Creates a {@code QuadTree} to handle the {@code SCP0492s} attacking {@code Humans}. Creates a second {@code QuadTree} to 
     * handle {@code Soldiers} attacking {@code SCP0492s}
     * </p>
     */
    private void handleAttacks(){

        QuadTree scpAttack = new QuadTree(1420, 1080, 540, 540, 0);
        for(int i = 0;i < scps.size();i++){
            scpAttack.insertAttacker(scps.get(i));
        }
        for(int i = 0;i < humans.size();i++){
            scpAttack.insertTarget(humans.get(i));
        }
        scpAttack.startCombat();

        QuadTree soldierAttack = new QuadTree(1420, 1080, 540, 540, 0);
        for(int i = 0;i < humans.size();i++){
            if(humans.get(i) instanceof Soldier){
                soldierAttack.insertAttacker((Soldier)humans.get(i));
            }
        }
        for(int i = 0;i < scps.size();i++){
            soldierAttack.insertTarget(scps.get(i));
        }
        soldierAttack.startCombat();
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
        for(int key: this.humanMap.keySet()){
            Human currentHuman = this.humanMap.get(key);
            if(currentHuman instanceof Spy){
                Spy spy = (Spy)currentHuman;
                double sus = Math.random();
                double success = Math.random();
                if(sus < spy.getSus()){
                    this.humans.remove(currentHuman);
                    this.humanMap.remove(key);
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
     * Gets the {@code Human} objects 
     * @return humans, a list of all game {@code Human} objects
     */
    public ArrayList<Human> getHumans(){
        return this.humans;
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
            humans.add((Human)npc);
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
     * @param npc The NPC being converted.
     * @param type The type of NPC that the NPC will be converted to.
     * @param health The health of the NPC.
     * @param maxHealth The maximum health of the NPC.
     * @param attackDamage The damage of the NPC (if applicable).
     * @param priority The priority of the NPC.
     * @param successRate The success rate of the NPC getting enemy intel (if applicable).
     * @param sus The chance of the NPC getting caught by the enemy (if applicable).
     * @param healingAmount The amount of healing the NPC will be able to heal (if applicable).
     */
    public void convert(NPC npc, String type, int maxHealth, int attackDamage, int priority, double successRate, double sus, int healingAmount){

        for(int key: this.humanMap.keySet()){
            Human currentHuman = humanMap.get(key);
            if(currentHuman.equals(npc)){
                convert(key, type, maxHealth, attackDamage, priority, successRate, sus, healingAmount);
            }
        }
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
        Human currentHuman = humanMap.get(key);
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
        this.humans.remove(currentHuman);
        if(newNpc instanceof SCP0492){
            scps.add((SCP0492)newNpc);
        }else{
            this.humans.add((Human)newNpc);
            this.humanMap.replace(key, (Human)newNpc);
        }
        return;
    }

    /**
     * <p>
     * Increases turn number by one. Calls on killDeadStuff to kill off anything that should be dead. Calls getResourcesFromBuildings 
     * to collect resources. Calls dealWithEvents to handle the {@code Events}. Calls on moveNpcs to move the mindless {@code NPCs} 
     * Calls handleAttacks to deal with {@code SCP0492s} attacking {@code Humans} and {@code Soldiers} attacking {@code SCP0492s}
     * </p>
     */
    public void doTurn(){

        this.turn++;
        //end of turn methods
        getResourcesFromBuildings();
        calculateStonks();
        dealWithEvents();
        moveSpcs();
        handleAttacks();
        killDeadStuff();
        eatFood();

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
