import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.Socket;


import java.util.ArrayList;
import java.util.HashMap;

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
    private StandbyWindow standbyWindow = null;
    /** string storing the last thing the client requested */
    private String lastRequest = "";
  


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
    }


    /**
     * Opens the window telling the player that the game is waiting for the second player to join.
     */
    public void startStandby(){
        standbyWindow = new StandbyWindow();
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
     * @return The standby window.
     */
    public StandbyWindow getStandbyWindow(){
        return this.standbyWindow;
    }

    /**
     * Gets the player object from this client.
     * @return The player object that this client is holding.
     */
    public Player getPlayer(){
        return this.player;
    }
    /**
     * sets the widow used for the player to standby.
     * @standbyWindow The new standby window.
     */
    public void setStandbyWindow(StandbyWindow standbyWindow){
        this.standbyWindow = standbyWindow;
    }

    public void setLastRequest(String newRequest){
        this.lastRequest = newRequest;
    }

    //start of inner classes
        /**
         * An inner class used to receives messages from the server.
         */
        private class MessageHandler implements Runnable{
            /** A string storing the last thing the client requested */
            public void run(){

                while(running){
                    //System.out.println("Running...");
                    try{
                        String prefix;
                        if(input.ready()){
                            prefix = input.readLine();
                            System.out.println(prefix);
                        //TODO: FOR TESTING PURPOSES
                            if(prefix.equals("<w>")){  //waiting for another player to join
                                Client.this.startStandby(); //start standby window
                            }else if(prefix.equals("<s>")){  //start game
                                System.out.println("Game start!"); //TODO: Temp line for game testing, remove later
                                String side = input.readLine();
                                String opponent = input.readLine();
                                int startingCurrency = Integer.parseInt(input.readLine());

                                
                                if(side.equals("s")){ //this player is on the SCP side
                                    Client.this.player = new SCP(username, Client.this, opponent, startingCurrency);
                                    Client.this.player.start();
                                }else if(side.equals("t")){ //this player is on the town side
                                    int startingFood = Integer.parseInt(input.readLine());
                                    Client.this.player = new Town(username, Client.this, opponent, startingCurrency, startingFood);
                                    Client.this.player.start();
                                }
                                System.out.println("You made it this far...");

                                if(Client.this.getStandbyWindow() != null){ //if standby window is open
                                    Client.this.getStandbyWindow().close();  //close the standby window
                                    Client.this.setStandbyWindow(null);
                                }

                            }else if(prefix.equals("<ts>")){ //server says to start the next turn

                                player.startTurn();
                                System.out.println("wth");

                            }else if(prefix.equals("<te>")){ //server says to end the current turn
                                player.endTurn();
                                String objectInfo;
                                String [] objectValues;


                                SCP0492.level = Integer.parseInt(input.readLine());
                                //TODO: PROBLEM HERE, RECEIVES THE NUMBER OF SCP'S BEFORE THE LEVEL OF SCPS.
                                int objectNum = Integer.parseInt(input.readLine());
                                ArrayList<SCP0492> scpList = new ArrayList<SCP0492>();

                                for(int i = 0; i < objectNum; i ++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    scpList.add(new SCP0492(Integer.parseInt(objectValues[0]), Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));   
                                }


                                objectNum = Integer.parseInt(input.readLine());
                                ArrayList<Event> eventList = new ArrayList<Event>();
                                for(int i = 0; i < objectNum; i ++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    String eventType = objectValues[0];

                                    if(eventType.equals("Infect")){
                                        eventList.add(new Infect(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]) ));
                                    }else if (eventType.equals("Earthquake")){
                                        eventList.add(new Earthquake(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]) ));
                                    }else if(eventType.equals("Fire")){
                                        eventList.add(new Fire(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]) ));
                                    }else if(eventType.equals("Mutate")){
                                        eventList.add(new Mutate(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2])));
                                    }else if(eventType.equals("Riot")){
                                        eventList.add(new Riot(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2])));
                                    }else if(eventType.equals("Thunderstorm")){
                                        eventList.add(new Thunderstorm(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]) ));
                                    }else if(eventType.equals("Tornado")){
                                        eventList.add(new Tornado(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]) ));
                                    }else if(eventType.equals("WarpReality")){
                                        eventList.add(new WarpReality(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2])));
                                    }else if(eventType.equals("Stonks")){
                                        eventList.add(new Stonks(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2])));
                                    }
                                }

                                objectNum = Integer.parseInt(input.readLine());
                                ArrayList<Building> buildingList = new ArrayList<Building>();
                                for(int i = 0; i < objectNum; i++){
                                    objectInfo = input.readLine();
                                    objectValues = objectInfo.split(" ");
                                    String buildingType = objectValues[0];
                                    if(buildingType.equals("Bank")){
                                        buildingList.add(new Bank(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5])   ));
                                    }else if(buildingType.equals("FoodBuilding")){
                                        buildingList.add(new FoodBuilding(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5])   ));
                                    }else if(buildingType.equals("Hospital")){
                                        buildingList.add(new Hospital(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]), Integer.parseInt(objectValues[6])));
                                    }else if(buildingType.equals("MilitaryBase")){
                                        buildingList.add(new MilitaryBase(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5])   ));
                                    }else if(buildingType.equals("ResearchLab")){
                                        buildingList.add(new ResearchLab(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5])   ));
                                    }else if(buildingType.equals("Residency")){
                                        buildingList.add(new Residency(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]),  Integer.parseInt(objectValues[6])));
                                    }

                                }

                                objectNum = Integer.parseInt(input.readLine());

                                
                                if(Client.this.getPlayer() instanceof SCP){
                                    ArrayList<Human> humansList = new ArrayList<Human>();
                                    for(int i = 0; i < objectNum; i++){
                                        objectInfo = input.readLine();
                                        objectValues = objectInfo.split(" ");
                                        String humanType = objectValues[0];
                                        if(humanType.equals("Cadet")){
                                            humansList.add(new Cadet(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Citizen")){
                                            humansList.add(new Citizen(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Doctor")){
                                            humansList.add(new Doctor(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]) ));
                                        }else if(humanType.equals("Researcher")){
                                            humansList.add(new Researcher(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Soldier")){
                                            humansList.add(new Soldier(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]), Integer.parseInt(objectValues[6]) ));
                                        }else if(humanType.equals("Spy")){
                                            humansList.add(new Spy(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Double.parseDouble(objectValues[5]), Double.parseDouble(objectValues[6])));
                                        }
                                        ((SCP)Client.this.getPlayer()).updateGameObjects(humansList, buildingList, eventList, scpList); //call method to update the game objects

                                    }
                                }else if (Client.this.getPlayer() instanceof Town){                                
                                    HashMap<Integer, Human> humanMap = new HashMap<>();
                                    for(int i = 0; i < objectNum; i++){
                                        int key = Integer.parseInt(input.readLine());
                                        objectInfo = input.readLine();
                                        objectValues = objectInfo.split(" ");
                                        String humanType = objectValues[0];
                                        if(humanType.equals("Cadet")){
                                            humanMap.put(key, new Cadet(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Citizen")){
                                            humanMap.put(key, new Citizen(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Doctor")){
                                            humanMap.put(key, new Doctor(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]) ));
                                        }else if(humanType.equals("Researcher")){
                                            humanMap.put(key, new Researcher(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4])));
                                        }else if(humanType.equals("Soldier")){
                                            humanMap.put(key, new Soldier(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Integer.parseInt(objectValues[5]), Integer.parseInt(objectValues[6]) ));
                                        }else if(humanType.equals("Spy")){
                                            humanMap.put(key, new Spy(Integer.parseInt(objectValues[1]), Integer.parseInt(objectValues[2]), Integer.parseInt(objectValues[3]), Integer.parseInt(objectValues[4]), Double.parseDouble(objectValues[5]), Double.parseDouble(objectValues[6])));
                                        }
                                        ((Town)Client.this.getPlayer()).updateGameObjects(humanMap, buildingList, eventList, scpList); //call method to update the game objects

                                    }
                                }
    

                            }else if(prefix.equals("<f>")){ //requested transaction could not be completed
                                Client.this.setLastRequest(""); //clear the last request

                            }else if(prefix.equals("<st>")){ //transaction is successful

                                //TODO: EDWARD NEEDS TO VERIFY THIS BLOCK OF PROTOCOL
                                System.out.println("something something congrats purchase successful");
                                String[] requests = lastRequest.split(" ");
                                //Actually implement the last request 
                                if(requests.length > 1){
                                    if(requests[0].equals("<b>")){
                                        ((Town)player).buildBuilding(requests[1], Integer.parseInt(requests[2]), Integer.parseInt(requests[2]));
                                    }else if(requests[0].equals("<e>")){
                                        if(requests.length == 3){
                                            ((SCP)player).startEvent(requests[1], Integer.parseInt(requests[2]));
                                        }else if(requests.length == 5){
                                            ((SCP)player).startEvent(requests[1], Integer.parseInt(requests[2]), Integer.parseInt(requests[3]), Integer.parseInt(requests[4]));
                                        }
                                    }else if(requests[0].equals("<u>")){
                                        ((Town)player).upgradeBuilding(Integer.parseInt(requests[1]), Integer.parseInt(requests[2]));

                                    }else if(requests[0].equals("<residency train>")){ // adding new citizens
                                        int x = Integer.parseInt(requests[1]);
                                        int y = Integer.parseInt(requests[2]);
                                        int amount = Integer.parseInt(requests[3]);
                                        Residency addTo = (Residency)((Town)player).findBuilding(x,y);
                                        Citizen newCitizen;
                                        for(int i = 0; i < amount; i ++){   
                                            newCitizen = new Citizen(0, 100, x, y);
                                            addTo.createCitizen(newCitizen);
                                            ((Town)player).addNpc(newCitizen);

                                        }
                                        ((Town)player).changeMoney(-100*amount);
                                        

                                    }else if(requests[0].equals("<residency convert>")){
                                        
                                        String newType = requests[1];
                                        int x = Integer.parseInt(requests[2]);
                                        int y = Integer.parseInt(requests[3]);
                                        int amount = Integer.parseInt(requests[4]);
                                        int[] keys = new int[amount];
                                        for(int i = 5; i < requests.length - 5;i ++ ){
                                            keys[i] = Integer.parseInt(requests[i]);

                                        }

                                        for(int i = 0; i < amount; i ++){
                                            ((Town)player).convert(keys[i], newType, 100, 10, 1, 1, 1, 100);
                                            ((Town)player).locateHumanInProperSpot(((Town)player).getHumanMap().get(keys[i]));
                                        }
                                        ((Town)player).changeMoney(-100*amount);


                                    }else if(requests[0].equals("<military soldier>")){
                                        int level = Integer.parseInt(requests[1]);
                                        int x = Integer.parseInt(requests[2]);
                                        int y = Integer.parseInt(requests[3]);
                                        int amount = Integer.parseInt(requests[4]);
                                        int[] keys = new int[amount];
                                        for(int i = 5; i < requests.length - 5;i ++ ){
                                            keys[i] = Integer.parseInt(requests[i]);

                                        }

                                        for(int i = 0; i < amount; i++){
                                            ((Town)player).convert(keys[i], "Soldier",level*100, level*10, 1, 1, 1, 100); 

                                        }

                                    }else if(requests[0].equals("<military spy>")){

                                        int x = Integer.parseInt(requests[1]);
                                        int y = Integer.parseInt(requests[2]);
                                        int amount = Integer.parseInt(requests[3]);
                                        int[] keys = new int[amount];
                                        for(int i = 4; i < requests.length - 4;i ++ ){
                                            keys[i] = Integer.parseInt(requests[i]);

                                        }

                                        for(int i = 0; i < amount; i++){
                                            ((Town)player).convert( keys[i],"Spy", 100, 0, 1,1,1, 100);

                                        }
                                    }

                                
                                }else{//this might be one of the research upgrade requests
                                    if(requests[0].equals("<research weapon>")){

                                        ((Town)player).researchWeapon();
                                        
                                    }else if(requests[0].equals("<research armour>")){

                                        ((Town)player).researchArmour();
                                    }

                                }


                            }else if(prefix.equals("<r>")){ //change in resources
                                String resourceType = input.readLine();
                                int resourceChange = Integer.parseInt(input.readLine());
                                if(resourceType.equals("Money")){
                                    ((Town)player).changeMoney(resourceChange);
                                }else if(resourceType.equals("Food")){
                                    ((Town)player).changeFood(resourceChange);
                                }else if(resourceType.equals("Hume")){
                                    ((SCP)player).changeHume(resourceChange);
                                }
                                


                            }else if (prefix.equals("<i>")){
                                int enemyHume = Integer.parseInt(input.readLine());
                                if(Client.this.player instanceof Town){
                                    ((Town)Client.this.player).displayIntel(enemyHume);
                                }
                            }else if(prefix.equals("<ge>")){ //if server says game is finished
                                Client.this.getPlayer().endGame(input.readLine());
                                Client.this.running = false;
                            }//end of if statements

                        }//end of if statement to check if there is input
                    }catch(IOException e){
                        System.out.println("Incorrect input received");
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
         * Constructor for the Login Window
         */
        public LoginWindow(){
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
        }//end of constructor



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
         * Constructor that opens the window to tell user to standby while the next player joins.
         */
        public StandbyWindow(){
        //set JFrame


        window = new JFrame("Welcome to Code-049!");
        window.setSize(400,300);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
