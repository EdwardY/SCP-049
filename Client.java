import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

import java.net.Socket;


import java.util.ArrayList;

/**
 * [Client.java]
 * The client program used by the players to communicate with the server and launch the game.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 4, 2021 
 */


public class Client {
    /**The port that the client has connected to.*/
    private int port;
    /**Used to write messages to the server.*/
    private PrintWriter output;
    /**Used to receive messages from the server.*/
    private BufferedReader input;
    /**Holds relevant information about the player's game objects and stats.*/
    private Player player;
    /**The IP address of the connected server. */
    private String ipAddress;
    /**The client socket. */
    private Socket socket;
    /**The username of the player. */
    private String username;
    /** Used to send and receive messages with the server*/
    private MessageHandler messageHandler;
    /**Used to check if the program should still run or not. */
    private boolean running;
    /**login window for the game. */
    private LoginWindow loginWindow;
    /**standby window that appears when the player is the first to join and waiting for the next to join. */
    private StandbyWindow standbyWindow;
  


    /**
     * The main method of the program to run.
     * @param args Java command-line arguments
     */
    public static void main(String[] args){
        new Client().login(); //initiate login process
    }

    /**
     * The login process for the client.
     */
    public void login(){
        loginWindow = new LoginWindow();
        loginWindow.run();
    }


    /**
     * Opens the window telling the player that the game is waiting for the second player to join.
     */
    public void startStandby(){
        standbyWindow = new StandbyWindow();
        standbyWindow.run();
    }

      /**
     * Sends a message to the server.
     * @param message The message being sent to the server.
     */
    public void sendMessage(String message){
        messageHandler.sendMessage(message);
    }

    /**
     * Gets the window used to login.
     * @return the login window.
     */
    public LoginWindow getLoginWindow(){
        return this.loginWindow;
    }

    /**
     * Gets the widow used for the player to standby.
     * @return The standby window
     */
    public StandbyWindow getStandbyWindow(){
        return this.standbyWindow;
    }

    //TODO: some method parameters between Town/SCP/Player and client do not match, e.g. .start


    //start of inner classes
        /**
         * An inner class used to receives messages from the server.
         */
        private class MessageHandler implements Runnable{
            public void run(){
                while(running){
                    try{
                        String prefix;
                        if(input.ready()){
                            prefix = input.readLine();
                            if(prefix.equals("<w>")){  //waiting for another player to join
                                Client.this.startStandby();
                            }else if(prefix.equals("<s>")){  //start game
                                System.out.println("Game start!");
                                String side = input.readLine();
                                String opponent = input.readLine();
                                int startingCurrency = Integer.parseInt(input.readLine());
                                if(side.equals("s")){ //this player is on the SCP side
                                    player = new SCP(username, Client.this, opponent, startingCurrency);
                                    player.start();
                                }else if(side.equals("t")){ //this player is on the town side
                                    int startingFood = Integer.parseInt(input.readLine());
                                    player = new Town(username, Client.this, opponent, startingCurrency, startingFood);
                                }

                                if(Client.this.getStandbyWindow() != null){
                                    Client.this.getStandbyWindow().close();
                                }

                            }else if(prefix.equals("<ts>")){ //server says to start the next turn

                                player.startTurn();

                            }else if(prefix.equals("<te>")){ //server says to end the current turn
                                String objectInfo;
                                String [] objectValues;
                                int objectNum = Integer.parseInt(input.readLine());
                                ArrayList<SCP0492> scpList = new ArrayList<SCP0492>();
                                for(int i = 0; i < objectNum; i ++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    //scpList.add(new SCP0492(Integer.parseInt(objectValues[0]), Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2])));   
                                    //TODO: fix NPC consctructors later, no longer needs image
                                }


                                objectNum = Integer.parseInt(input.readLine());
                                ArrayList<Event> eventList = new ArrayList<Event>();
                                for(int i = 0; i < objectNum; i ++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    String eventType = objectValues[0];

                                    if(eventType.equals("Infect")){
                                        //TODO: Construct new Infect object
                                    }else if (eventType.equals("Earthquake")){
                                        //TODO: Construct new Earthquake object
                                    }else if(eventType.equals("Fire")){
                                        //TODO: Construct new Fire object
                                    }else if(eventType.equals("Mutate")){
                                        //TODO: Create new Mutate event
                                    }else if(eventType.equals("Riot")){
                                        //TODO: Create new Riot event
                                    }else if(eventType.equals("Snow")){
                                        //TODO: create new Snow event
                                    }else if(eventType.equals("Thunderstorm")){
                                        //TODO: create new Thunderstorm event
                                    }else if(eventType.equals("Tornado")){
                                        //TODO: Create new Tornado event
                                    }else if(eventType.equals("WarpReality")){
                                        //TODO: Create new WarpReality event
                                    }else if(eventType.equals("Stonks")){
                                        //TODO: Create new Stonks event
                                    }
                                }

                                objectNum = Integer.parseInt(input.readLine());
                                ArrayList<Building> buildingList = new ArrayList<Building>();
                                for(int i = 0; i < objectNum; i++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    String buildingType = input.readLine();
                                    if(buildingType.equals("Bank")){
                                        //TODO: Build new bank object
                                    }else if(buildingType.equals("FoodBuilding")){
                                        //TODO: Build new FoodBuilding object.
                                    }else if(buildingType.equals("Hospital")){
                                        //TODO: Build new Hospital object.
                                    }else if(buildingType.equals("MilitaryBase")){
                                        //TODO: Build new MilitaryBase object.
                                    }else if(buildingType.equals("ProductionFacility")){
                                        //TODO: Build new ProductionFacility object.
                                    }else if(buildingType.equals("ResearchLab")){
                                        //TODO: Build new ResearchLab object.
                                    }else if(buildingType.equals("Residency")){
                                        //TODO: Build new Residency object.
                                    }

                                }

                                objectNum = Integer.parseInt(input.readLine());
                                ArrayList<Human> humansList = new ArrayList<Human>();
                                for(int i = 0; i < objectNum; i++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    String humanType = input.readLine();
                                    if(humanType.equals("Cadet")){
                                        //TODO: Build new Cadet object
                                    }else if(humanType.equals("Citizen")){
                                        //TODO: Build new Citizen object.
                                    }else if(humanType.equals("Doctor")){
                                        //TODO: Build new Doctor object.
                                    }else if(humanType.equals("Researcher")){
                                        //TODO: Build new Researcher object.
                                    }else if(humanType.equals("Soldier")){
                                        //TODO: Build new Soldier object.
                                    }else if(humanType.equals("Spy")){
                                        //TODO: Build new Spy object.
                                    }

                                }


                                
    

                            }else if(prefix.equals("<f>")){ //requested transaction could not be completed

                                System.out.println("something something transaction didn't go through");
                                //TODO: Not done properly yet.
                            }else if(prefix.equals("<st>")){ //transaction is successful

                                System.out.println("something something congrats purchase successful");
                                //TODO: Not done properly yet.
                            }else if(prefix.equals("<r>")){ //change in resources
                                String resourceType = input.readLine();
                                int resourceChange = Integer.parseInt(input.readLine());
                                if(resourceType.equals("dubercoin")){
                                    ((Town)player).changeFood(resourceChange);
                                }else if(resourceType.equals("food")){
                                    ((Town)player).changeFood(resourceChange);
                                }else if(resourceType.equals("hume")){
                                    ((SCP)player).changeHume(resourceChange);
                                }             
                            }//end of if statements

                        }
                    }catch(IOException e){
                        System.out.println("Error receiving message from server");
                    }//end of try catch statement
                }//end of while loop
            }//end of method

            /**
             * Sends a message to the server
             * @param message The message being sent to the server.
             */
            public void sendMessage(String message){
                output.println(message);
                output.flush();
            }//end of method

            


        }//end of inner class


    /**
     * An inner class for a window that players will use to login.
     */
    public class LoginWindow{
        /**The JFrame for the login popup window.*/
        private JFrame window;
        /**The JPanel for the login popup window.*/
        private JPanel panel;
        /**JLabel that will ask the user to enter login information. */
        private JLabel loginLabel;
        /**The JLabel that will prompt the user for a username.*/
        private JLabel usernameLabel;
        /**The JLabel that will prompt the user for a the IP address of the server.*/
        private JLabel addressLabel;
        /**The JLabel that will prompt the user for the port.*/
        private JLabel portLabel;
        /**The JLabel that will tell the user if there were any issues connecting. */
        private JLabel connectionErrorLabel;
        /**The JTextField that will receive the user's username. */
        private JTextField usernameEntry;
        /**The JTextField that will receive the server's IP address. */
        private JTextField addressEntry;
        /**The JTextField that will receive the port to connect to. */
        private JTextField portEntry;
        /**The JButton used to submit entered information*/
        private JButton enterButton;

        /**
         * Runs the window so that players can login.
         */
        public void run(){
        //set JFrame
        window = new JFrame("Welcome to Code-049!");
        window.setSize(400,300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //set JPanel
        panel = new JPanel();
        panel.setLayout(null);
        window.add(panel);
        
        //Label to show players where to login
        loginLabel = new JLabel("Login:");
        loginLabel.setBounds(10,20,200,25); 
        panel.add(loginLabel);
    
        //Prompt player for a username
        usernameLabel =  new JLabel("Select your username:");
        usernameLabel.setBounds(10, 65, 210, 25);
        panel.add(usernameLabel);
    
        //Prompt player for  IP Address
        addressLabel =  new JLabel("Enter IP Address:");
        addressLabel.setBounds(10, 95, 210, 25); 
        panel.add(addressLabel); 
    
        //Prompt player for port
        portLabel =  new JLabel("Enter Port #:");
        portLabel.setBounds(10, 125, 210, 25);
        panel.add(portLabel); 
    
        //Label Will only appear if there is an error when attempting to connect
        connectionErrorLabel = new JLabel("");
        connectionErrorLabel.setBounds(10, 200, 300, 25 ); 
        connectionErrorLabel.setForeground(Color.red); 
        panel.add(connectionErrorLabel); 

        //Receives the selected username from the player.
        usernameEntry = new JTextField(20);
        usernameEntry.setBounds(140,65, 165, 25); 
        panel.add(usernameEntry); 

        //get IP address from the player.
        addressEntry = new JTextField(20); 
        addressEntry.setBounds(140, 95, 165,25); 
        panel.add(addressEntry);

        //Receive port from user
        portEntry = new JTextField(20);
        portEntry.setBounds(140,125, 165, 25);
        panel.add(portEntry);
    
        //Submit button
        enterButton = new JButton("Connect");
        enterButton.setBounds(150,165, 100, 25); 
        panel.add(enterButton); 
        enterButton.addActionListener(new EnterButtonListener());
    
        window.setVisible(true); //make the window visible after adding everything            

        }//end of method

         /**
        * Method connects the player's client program to the server.
        * @param address The IP address of the server.
        * @param port The port.
        */
        public void connect(String username, String address, int port){
            try {
      
                Client.this.socket = new Socket(address, port); //attempt socket connection
            
                //input to server
                InputStreamReader stream1= new InputStreamReader(socket.getInputStream()); 
                Client.this.input = new BufferedReader(stream1);
                //output to server
                Client.this.output = new PrintWriter(socket.getOutputStream()); //assign printwriter to network stream
            
                //get messages from server
                try{
              
                //if there is input
                while(!input.ready()){}
                sendMessage("<c>");
                sendMessage(username);              

                window.dispose();

                }catch(IOException e){ 
                    connectionErrorLabel.setText("A communications error has occured.");
                }
            } catch (IOException e) {  //connection error occured
                connectionErrorLabel.setText("Error: Could not connect to server.");
            }
            //close the login window since there's no need for it anymore

        }//end of method



        /**
        * Inner class for the submit button on the login page.
        */
        class EnterButtonListener implements ActionListener { 
    
            /**
            * Attempts to login when the button is pressed.
            */
            public void actionPerformed(ActionEvent event)  {

                //set the username the client selected
                    username = usernameEntry.getText();
      
      
                //attempt to get the port 
                try { 
                    port = Integer.parseInt(portEntry.getText()); //set the port the user selected
                } catch(NumberFormatException e) { //if the input was not an integer tyoe
                    connectionErrorLabel.setText("Error: Invalid port entered."); //tell user that they didn't give the right information
                    return; //end method
                }
      
                //store the IP Address the user entered
                ipAddress = addressEntry.getText();
                
                //attempt to connect to the server
                messageHandler = new MessageHandler();
                connect(username, ipAddress, port);
                running = true; 
                
                Thread t = new Thread(messageHandler); //start the server communication in a new thread
                t.start(); //start thread*/
            }

        }//end of inner class for submit button






    }//end of inner class


    /**
     * An inner class for a window that displays when a player is waiting for another player to join the game.
     */
    public class StandbyWindow{
        /**Window that is opened to tell player to wait while another player connects. */
        private JFrame window;
        /**The JPanel for the window. */
        private JPanel panel;
        /**The JLabel to tell user to wait. */
        private JLabel standbyLabel;


        /**
         * Opens the window to tell user to standby while the next player joins.
         */
        public void run(){
        //set JFrame


        window = new JFrame("Welcome to Code-049!");
        window.setSize(400,300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set JPanel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,400,300); 
        window.add(panel);
        
        //Label to show players where to login
        standbyLabel = new JLabel("Waiting for player 2 to join...");
        standbyLabel.setBounds(20,30,200,25); 
        panel.add(standbyLabel);



        window.setVisible(true);
        }//end of method



        /**
         * Closes the standby window if the other user has joined.
         */
        public void close(){
            window.dispose();
        }

    }//end of inner class

    //end of inner classes

}//end of class
