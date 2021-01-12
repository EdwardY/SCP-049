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
    private int money = 0;
    /**an integer for storing the food that the player has */
    private int food = 0;

    /**
     * Constructor for the town side player's class
     * @param username The username of the player
     * @param client The client that will connect to the server
     */
    Town(String username, Client client){
        super(username, client);
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
