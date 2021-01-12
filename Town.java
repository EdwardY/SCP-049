/**
 * [Town.java]
 * A class that creates the basic class that the "town" player uses
 * @author Edward Yang
 * @version 1.0 2021/1/11
 */

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

public class Town extends Player {

    /**an array list storing all the buildings */
    private ArrayList<Building> buildings = new ArrayList<>();
    /**Array list for storing the human population */
    private ArrayList<Human> population = new ArrayList<>();
    /**An integer for storing the money the player has */
    private int money;
    /**an integer for storing the food that the player has */
    private int food;

    /**
     * Constructor for the town side player's class
     * @param username The username of the player
     * @param client The client that will connect to the server
     */
    Town(String username, Client client){
        super(username, client);

    Town(String username, Client playerClient, String opponent, int money, int food){
        super(username, playerClient, opponent);
        this.money = money;
        this.food = food; 
    }


    /**
     * Starts the town version of the game.
     */
    public void start(){
        System.out.println("Starting town game...");
        //TODO: nothing is actually here yet.

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

    //TODO: method for client-game transactions


    public ArrayList<JFrame> getGameGraphics(){

        //TODO: ask vivian and damon how exactly the graphics are going to work

    }

    
    /** buildingBuilding
     * @param buildingType The type of building to be built
     * @param x the x coordinate of the buildings
     * @param y the y coordinate of the buildings
     */
    public void buildBuilding(String buildingType, int x, int y){

        //TODO: decide on the cost of the buildings to initially build
        Image sprite = Toolkit.getDefaultToolkit().getImage("./assets/" + buildingType + ".png");

        //add each created building to the ArrayList containing the buildings
        if(buildingType.equals("MilitaryBase")){

            buildings.add(new MilitaryBase(1000, 100, 100, sprite, x, y));
            money = money - 1000;

        }else if(buildingType.equals("ResearchLab")){

            buildings.add(new ResearchLab(1000, 100, 100, sprite, x, y));
            money = money - 1000;
        
        }else if(buildingType.equals("Residency")){
        
            buildings.add(new Residency(1000, 100, 100, sprite, x, y, 100));
            money = money - 1000;

        }else if(buildingType.equals("Hospital")){

            buildings.add(new Hospital(1000, 100, 100, sprite, x, y, 100));
            money = money - 1000;
        
        }else if(buildingType.equals("FoodBuilding")){

            buildings.add(new FoodBuilding(1000, 100, 100, sprite, x, y));
            money = money - 1000;
        
        }else if(buildingType.equals("Bank")){

            buildings.add(new Bank(1000, 100, 100, sprite, x, y));
            money = money - 1000;
            
        }else{  

            System.out.println("hey that's not the right building to build");
        }

    }

    
    /** 
     * @param x
     * @param y
     */
    public void upgradeBuilding(int x , int y){

        //TODO: figure out if I want to optimize efficiency
        for(int i = 0; i < buildings.size(); i ++){

            if(buildings.get(i).getX() == x && buildings.get(i).getY() == y){

                buildings.get(i).upgrade();
            }
        }

    }

    
    /** 
     * @return int
     */
    public int getMoney(){

        return this.money;

    }

    
    /** 
     * @return int
     */
    public int getFood(){

        return this.food;
    }

    
    /** 
     * @param newMoney
     */
    public void setMoney(int newMoney){

        this.money = newMoney;
    }

    
    /** 
     * @param newFood
     */
    public void setFood(int newFood){

        this.food = newFood;
    }

}
