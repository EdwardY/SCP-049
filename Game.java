import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;
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
    /** The amount of hume points that are earned each turn */
    private int humePerTurn;

    /**
     * Constructor for the {@code Game} class, assigns preset values
     */
    public Game(){
        this.buildings = new ArrayList<Building>();
        this.humans = new ArrayList<Human>();
        this.scps = new ArrayList<SCP0492>();
        this.events = new ArrayList<Event>();
        this.money = 10;
        this.food = 10;
        this.hume = 10;
        this.turn = 1;
        this.moneyPerTurn = 0;
        this.foodPerTurn = 0;
        this.humePerTurn = 0;
    }

    private void handlePassives(){
        //TODO: deal with passives (moving NPCs, handling events, getting income from buildings, etc.)
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
     * Changes the humePerTurn by change
     * @param change the change in hume points per turn
     */
    public void changeHumePerTurn(int change){
        this.humePerTurn += change;
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
        //temporary for now
        String imageURL = "";
        Image tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);


        for(int i = 0;i < this.humans.size();i++){
            if(this.humans.get(i).equals(npc)){
                int currentXPosition = this.humans.get(i).getX();
                int currentYPosition = this.humans.get(i).getY();
                if(type.equals("Citizen")){
                    humans.add(new Citizen(currentXPosition,currentYPosition,tempImage));
                }else if(type.equals("Cadet")){
                    humans.add(new Cadet(currentXPosition, currentYPosition, tempImage));
                }else if(type.equals("Doctor")){
                    humans.add(new Doctor(currentXPosition, currentYPosition, tempImage, healingAmount));
                }else if(type.equals("Researcher")){
                    humans.add(new Researcher(currentXPosition, currentYPosition, tempImage));
                }else if(type.equals("Soldier")){
                    humans.add(new Soldier(maxHealth, currentXPosition, currentYPosition, tempImage, attackDamage));
                }else if(type.equals("Spy")){
                    humans.add(new Spy(currentXPosition, currentYPosition, tempImage, successRate, sus));
                }else if (type.equals("SCP0492")){
                    scps.add(new SCP0492(maxHealth, currentXPosition, currentYPosition, tempImage, attackDamage));
                }
                humans.remove(i);
                return;
            }
        }

        //TODO: no images for NPC's yet, may need to draw things
    }

    /**
     * Increases turn number by one then lets the {@code Game} handle all other passive stuff
     */
    public void doTurn(){
        this.turn++;
        this.handlePassives();
    }
}
