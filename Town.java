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

public class Town extends Player {

    /**an array list storing all the buildings */
    private ArrayList<Building> buildings = new ArrayList<>();
    /**Array list for storing the human population */
    private ArrayList<Human> population = new ArrayList<>();
    /**An integer for storing the money the player has */
    private int money = 0;
    /**an integer for storing the food that the player has */
    private int food = 0;

    Town(String username){
        super(username);
    }

    public ArrayList<JFrame> getGameGraphics(){

        //TODO: ask vivian and damon how exactly the graphics are going to work

    }

    public void buildBuilding(String buildingType, int x, int y){

        //TODO: add images to the constructors

        //add each created building to the ArrayList containing the buildings
        if(buildingType.equals("MilitaryBase")){

            buildings.add(new MilitaryBase(1000, 100, 100, null, x, y));

        }else if(buildingType.equals("ResearchLab")){

            buildings.add(new ResearchLab(1000, 100, 100, null, x, y));
        
        }else if(buildingType.equals("Residency")){
        
            buildings.add(new Residency(1000, 100, 100, null, x, y, 100));

        }else if(buildingType.equals("Hospital")){

            buildings.add(new Hospital(1000, 100, 100, null, x, y, 100));
        
        }else if(buildingType.equals("FoodBuilding")){

            buildings.add(new FoodBuilding(1000, 100, 100, null, x, y));
        
        }else if(buildingType.equals("Bank")){

            buildings.add(new Bank(1000, 100, 100, null, x, y));
            
        }else{  

            System.out.println("hey that's not the right building to build");
        }

    }

    public void upgradeBuilding(int x , int y){

        for(int i = )

    }

}
