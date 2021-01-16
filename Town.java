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
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * [Town.java]
 * A class that creates the basic class that the "town" player uses
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 11, 2020
 */


public class Town extends Player {
    /**An integer for storing the money the player has */
    private int money;
    /**an integer for storing the food that the player has */
    private int food;
    /**A hashmap of all humans in the game so they can be referenced with a key. */
    private HashMap<Integer, Human> humanMap;
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

        return new ArrayList<JFrame>(); //for now to be able to test run the program.
        //TODO: ask vivian and damon how exactly the graphics are going to work

    }

    
    /** buildingBuilding
     * @param buildingType The type of building to be built
     * @param x the x coordinate of the buildings
     * @param y the y coordinate of the buildings
     */
    public void buildBuilding(String buildingType, int x, int y){

        ArrayList<Building> buildings = this.getBuildings();

        //TODO: decide on the cost of the buildings to initially build

        //add each created building to the ArrayList containing the buildings
        if(buildingType.equals("MilitaryBase")){

            buildings.add(new MilitaryBase(1000, 100, 100, x, y));
            money = money - 1000;

        }else if(buildingType.equals("ResearchLab")){

            buildings.add(new ResearchLab(1000, 100, 100, x, y));
            money = money - 1000;
        
        }else if(buildingType.equals("Residency")){
        
            buildings.add(new Residency(1000, 100, 100,x, y, 100));
            money = money - 1000;

        }else if(buildingType.equals("Hospital")){

            buildings.add(new Hospital(1000, 100, 100,  x, y, 100));
            money = money - 1000;
        
        }else if(buildingType.equals("FoodBuilding")){

            buildings.add(new FoodBuilding(1000, 100, 100,  x, y));
            money = money - 1000;
        
        }else if(buildingType.equals("Bank")){

            buildings.add(new Bank(1000, 100, 100, x, y));
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

    //end of setters



    /**
     * An inner class that will run the main game window that the user will use to play the game.
     */
    public class TowngameWindow extends GameWindow{

        /**
         * Method runs the town version of the game.
         */
        public TowngameWindow(){
            //get the JPanels and JFrame of the game window from parent class
            JFrame gameWindow = this.getWindow();

            TownGridPanel gridPanel = new TownGridPanel();
            gridPanel.setBounds(0, 0, 1080, 1080);
            gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            gridPanel.setBackground(Color.gray);

            TownInfoBarPanel infoBarPanel = new TownInfoBarPanel();
            infoBarPanel.setBounds(1080, 0, 256, 1080);
            infoBarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            infoBarPanel.setBackground(Color.white);

            gameWindow.add(gridPanel);
            gameWindow.add(infoBarPanel);
            
            //let user see the window
            gameWindow.setVisible(true);
            

            Town.this.displaySide("Town");

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
                int emptyLotX = 0; //X-value of the empty lot to be drawn
                for(int i = 0; i < 7; i ++){
                    int emptyLotY = 0; //Y-value of the empty lot to be drawn
                    for(int j = 0; j < 7; j++){
                        g.drawRect(emptyLotX, emptyLotY, 128, 128);
                        emptyLotY += 158;
                    }
                    emptyLotX += 158;
                }


                //draw humans
                //convert the hashmap of humans into an arraylist
                ArrayList<Human> humanList = new ArrayList<Human>(Town.this.getHumanMap().values());
                for(int i = 0; i < Town.this.getHumans().size(); i++){
                    humanList.get(i).draw(g);
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

            }
        }

        /**
         * [TownInfoBarPanel.java]
         * A {@code InfoBarPanel} that displays SCP side specific information
         * @author Damon Ma, Edward Yang, Vivian Dai
         * @version 1.0 on January 15, 2021
         */
        private class TownInfoBarPanel extends InfoBarPanel{
            /**
             * Constructor for the {@code InfoBarPanel}
             */
            private TownInfoBarPanel(){
                setFocusable(true);
                requestFocusInWindow();
            }

            /**
             * @param g the {@code Graphics} to draw on
             */
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                //TODO: draw more things on top
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
